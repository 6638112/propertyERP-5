package com.cnfantasia.server.api.bankCollection.transfer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;

import com.cnfantasia.server.api.bankCollection.service.BankCollectionService;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.utils.DataUtil;
import org.apache.commons.io.input.BOMInputStream;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

import com.cnfantasia.server.api.bankCollection.entity.BcRebackDetailEntity;
import com.cnfantasia.server.api.bankCollection.entity.PPBankCollectionWithPayBill;
import com.cnfantasia.server.api.bankCollection.entity.PropertyCompanyBCInfo;
import com.cnfantasia.server.api.bankCollection.entity.ResultMsg;
import com.cnfantasia.server.api.bankCollection.utils.BankCollectionUtils;
import com.cnfantasia.server.business.pub.utils.CommonMultiFileUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.bcFileDefine.entity.BcFileDefine;
import com.cnfantasia.server.domainbase.bcGroupBuildingCycle.entity.BcGroupBuildingCycle;
import com.cnfantasia.server.domainbase.bcOfferRecord.entity.BcOfferRecord;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.constant.PathConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;

/**
 * 工商银行 出盘回盘
 * @author wenfq 2017-04-20
 */
public class ICBCBankCollectionTransfer implements IBankCollectionTransfer {
	private org.apache.commons.logging.Log logger = LogFactory.getLog(getClass());
	@Override
	public String exportDetailFile(List<BcFileDefine> bcFileDefineList, List<PPBankCollectionWithPayBill> bcPayBillList,
			BcGroupBuildingCycle bcGBCycle, List<BcOfferRecord> bcOfferRecordList) throws IOException {
		StringBuilder fileContent = new StringBuilder();
		for(PPBankCollectionWithPayBill pb: bcPayBillList){
			StringBuffer oneDetail = new StringBuffer();
			/**
			 * 特殊处理逻辑：如果房间编号为空，则取账单ID，并在前面加特殊字符@区分（与杨华的约定，回盘时要用到）
			 */
			String roomNo = StringUtils.isEmpty(pb.getRoomNo()) ? "@" + pb.getPbId() : pb.getRoomNo();
			oneDetail.append(BankCollectionUtils.getOneFieldValue(roomNo, bcFileDefineList.get(0)));//编号--用账单id代替
			oneDetail.append(BankCollectionUtils.getOneFieldValue(pb.getBankNumber(), bcFileDefineList.get(1)));//银行代号
			oneDetail.append(BankCollectionUtils.getOneFieldValue(pb.getBankAccount(), bcFileDefineList.get(2)));//银行账号
			oneDetail.append(BankCollectionUtils.getOneFieldValue(pb.getPbAmountStr(), bcFileDefineList.get(3)));//账单金额
			oneDetail.append(BankCollectionUtils.getOneFieldValue("X", bcFileDefineList.get(4)));//托收标志，出盘时用X
			
			BcOfferRecord bcor = new BcOfferRecord();
			bcor.settBcGroupBuildingCycleFId(bcGBCycle.getId());
			bcor.setOfferContent(oneDetail.toString());
			bcor.setPaybillId(pb.getPbId());
			bcor.setOfferTime(DateUtils.getCurrentDate());
			bcOfferRecordList.add(bcor);
			
			oneDetail.append("\r\n");//换一行
			fileContent.append(oneDetail);
		}
		
		String fileFullPath = CommonMultiFileUtil.uploadFile(".txt", fileContent.toString(), 
				OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH), PathConstants.BANK_COLLECTION, "001");
		return fileFullPath;
	}

	@Override
	public String exportSumFile(int timesInThisMonth, PropertyCompanyBCInfo bcInfo, List<BcFileDefine> bcFileDefineList,
			List<PPBankCollectionWithPayBill> bcPayBillList) throws IOException {
		StringBuilder fileContent = new StringBuilder();
		int i = 0;
		fileContent.append(BankCollectionUtils.getOneFieldValue(bcInfo.getContractNo(), bcFileDefineList.get(i++)));//合同号（协议号）
		fileContent.append(BankCollectionUtils.getOneFieldValue(timesInThisMonth +"", bcFileDefineList.get(i++)));//编号
		fileContent.append(BankCollectionUtils.getOneFieldValue((Calendar.getInstance().get(Calendar.MONTH) + 1) + "" + timesInThisMonth,bcFileDefineList.get(i++)));//第几月第几次
		fileContent.append(BankCollectionUtils.getOneFieldValue(bcPayBillList.size() +"", bcFileDefineList.get(i++)));//明细记录数
		
		BigDecimal totalAmt = BigDecimal.ZERO;
		
		for(PPBankCollectionWithPayBill pb: bcPayBillList){
			totalAmt = totalAmt.add(pb.getPbAmount());
		}
		totalAmt = totalAmt.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
		fileContent.append(BankCollectionUtils.getOneFieldValue(totalAmt.toString(), bcFileDefineList.get(i++)));//金额合计
		fileContent.append(BankCollectionUtils.getOneFieldValue(DateUtils.getCurrentDateStr() + DateUtils.getCurrentDateStr(), bcFileDefineList.get(i++)));//托收年月
		fileContent.append(BankCollectionUtils.getOneFieldValue("", bcFileDefineList.get(i++)));//末尾空格
		
		String fileFullPath = CommonMultiFileUtil.uploadFile(".txt", fileContent.toString(), 
				OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH), PathConstants.BANK_COLLECTION, 
				BankCollectionUtils.getOneFieldValue(timesInThisMonth +"", bcFileDefineList.get(1)) + "_sum");
		return fileFullPath;
	}

	@Override
	public ResultMsg importDetailFile(MultipartFile uploadfile, List<BcRebackDetailEntity> rebackDetailEntityList,
			List<BcFileDefine> bcFileDefineList, BigInteger bcgroupBuildingCycleId) throws IOException {
		BankCollectionService bankCollectionService = (BankCollectionService) CnfantasiaCommbusiUtil.getBeanManager("bankCollectionService");
		//文件名校验
		if(!uploadfile.getOriginalFilename().contains(".txt")) return new ResultMsg("文件格式不对，必须为.txt格式", false);

		InputStream inputStream = uploadfile.getInputStream();

		checkFile(inputStream, rebackDetailEntityList, bcFileDefineList, bankCollectionService, bcgroupBuildingCycleId);

		if(DataUtil.isEmpty(rebackDetailEntityList)) {
			return new ResultMsg("回盘文件格式不匹配，或回盘文件内容有误", false);
		}

		return new ResultMsg(null, true);
	}

	private void checkFile(InputStream in,List<BcRebackDetailEntity> rebackDetailEntityList, List<BcFileDefine> bcFileDefineList, BankCollectionService bankCollectionService, BigInteger bcgroupBuildingCycleId) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BOMInputStream(in), "UTF-8"));
		String lineStr = "";
		int i = 1;
		while ((lineStr = bufferedReader.readLine()) != null && lineStr.trim().length() > 0) {
			logger.debug("第"+i+"行回盘记录:"+lineStr);
			String rebackContent = lineStr;
			lineStr = lineStr.trim();
			lineStr = lineStr.replaceAll(" {2,}", " ");
			lineStr = lineStr.replaceAll(" ", "@");
			//01-01-011016523000243497               154.50Y
			//前10位为账单id
			String[] lineArr = lineStr.split("@");
			if(lineArr.length > 2) {
				//01-01-01  16523000243497     154.50Y
				//01-01-011216523000243497     154.50Y
				//如果账单id长度需要空格补齐，那么将数组第二位缓存金额，防止金额取错
				lineArr[1] = lineArr[2];
			}

			Long amount = null;
			try {
				String amountStr = lineArr[1];
				amountStr = amountStr.substring(0, amountStr.length() - 1);
				BigDecimal dbAmount = new BigDecimal(amountStr);
				BigDecimal chengshu = new BigDecimal("100");
				BigDecimal resout = dbAmount.multiply(chengshu);
				amount = resout.longValue();
			} catch (NumberFormatException e) {
				e.printStackTrace();
				continue;
			}
			logger.debug("第"+i+"行回盘记录(回盘金额)--amount:"+amount);

			BigInteger paybillId = null;
			try {
				String payBillIdStr = "";
				if(lineArr.length > 2) {
					payBillIdStr = lineArr[0];
				} else {
					payBillIdStr = lineArr[0].substring(0, bcFileDefineList.get(0).getWidth().intValue()).trim();
				}
				paybillId = getPayBillId(payBillIdStr, bcgroupBuildingCycleId, amount, bankCollectionService);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			logger.debug("第"+i+"行回盘记录--paybillId:"+paybillId);

			String mark = null;
			try {
				String markStr = lineArr[1];
				markStr = markStr.substring(markStr.length() - 1, markStr.length());
				mark = markStr.trim();
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			logger.debug("第"+i+"行回盘记录(银行标识)--mark:"+mark);

			BcRebackDetailEntity rebackDetailEntity = new BcRebackDetailEntity();
			rebackDetailEntity.setPaybillId(paybillId);
			rebackDetailEntity.setRebackContent(rebackContent);
			rebackDetailEntity.setAmount(amount);
			rebackDetailEntity.setStatus(mark);
			if(paybillId != null) {
				rebackDetailEntityList.add(rebackDetailEntity);
			}
			i++;
		}
	}
	/**
	 * 获取账单id
	 */
	private BigInteger getPayBillId(String str, BigInteger bcgroupBuildingCycleId, Long amount, BankCollectionService bankCollectionService) {
		if(DataUtil.isEmpty(str)) return null;

		//判断是使用房间编码还是使用账单id  账单id前面第一位"@"
		BigInteger paybillid = null;
		if(str.indexOf("@") == 1) {
			str = str.substring(1, str.length());
			try {
				paybillid = new BigInteger(str);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				return paybillid;
			}
		} else {
			List<BigInteger> payBillCheckForJinRongAndICBCCollection = bankCollectionService.getPayBillCheckForJinRongAndICBCCollection(str, bcgroupBuildingCycleId, amount);
			if(!DataUtil.isEmpty(payBillCheckForJinRongAndICBCCollection) && payBillCheckForJinRongAndICBCCollection.size() == 1) {
				paybillid = payBillCheckForJinRongAndICBCCollection.get(0);
			}
		}

		return paybillid;
	}


}
