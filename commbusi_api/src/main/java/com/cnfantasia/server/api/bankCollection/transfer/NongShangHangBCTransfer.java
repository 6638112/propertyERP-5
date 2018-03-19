package com.cnfantasia.server.api.bankCollection.transfer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.input.BOMInputStream;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

import com.cnfantasia.server.api.bankCollection.entity.BcRebackDetailEntity;
import com.cnfantasia.server.api.bankCollection.entity.PPBankCollectionWithPayBill;
import com.cnfantasia.server.api.bankCollection.entity.PropertyCompanyBCInfo;
import com.cnfantasia.server.api.bankCollection.entity.ResultMsg;
import com.cnfantasia.server.api.bankCollection.utils.BankCollectionUtils;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.utils.CommonMultiFileUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.UUIDGenerater;
import com.cnfantasia.server.domainbase.bcFileDefine.entity.BcFileDefine;
import com.cnfantasia.server.domainbase.bcGroupBuildingCycle.entity.BcGroupBuildingCycle;
import com.cnfantasia.server.domainbase.bcOfferRecord.entity.BcOfferRecord;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;
import com.cnfantasia.server.domainbase.payBill.service.PayBillBaseService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.constant.PathConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;

/**
 * 深圳农商行 出盘回盘
 * @author wenfq 2017-04-19
 *
 */
public class NongShangHangBCTransfer implements IBankCollectionTransfer{
	private org.apache.commons.logging.Log logger = LogFactory.getLog(getClass());
	@Override
	public String exportDetailFile(List<BcFileDefine> bcFileDefineList, List<PPBankCollectionWithPayBill> bcPayBillList,
			BcGroupBuildingCycle bcGBCycle, List<BcOfferRecord> bcOfferRecordList) throws IOException {
		StringBuilder fileContent = new StringBuilder();
		for(PPBankCollectionWithPayBill pb: bcPayBillList){
			StringBuffer oneDetail = new StringBuffer();
			String pbShortCode = UUIDGenerater.generateShortUuid(7);
			logger.info("---bc---pbShortCode: "  + pbShortCode);
			oneDetail.append(BankCollectionUtils.getOneFieldValue(pbShortCode, bcFileDefineList.get(0)));//编号
			oneDetail.append(BankCollectionUtils.getOneFieldValue("16", bcFileDefineList.get(1)));//未定义列名，产品经理也不知道16是什么，湾厦物业出盘都是16
			oneDetail.append(BankCollectionUtils.getOneFieldValue("", bcFileDefineList.get(2)));//空格列
			oneDetail.append(BankCollectionUtils.getOneFieldValue(pb.getBankAccount(), bcFileDefineList.get(3)));//银行账号
			oneDetail.append(BankCollectionUtils.getOneFieldValue(pb.getPbAmountStr(), bcFileDefineList.get(4)));//账单金额
			oneDetail.append(BankCollectionUtils.getOneFieldValue("X", bcFileDefineList.get(5)));//托收标志，出盘时用X
			
			BcOfferRecord bcor = new BcOfferRecord();
			bcor.settBcGroupBuildingCycleFId(bcGBCycle.getId());
			bcor.setOfferContent(oneDetail.toString());
			bcor.setPaybillId(pb.getPbId());
			bcor.setShortCode(pbShortCode);
			bcor.setOfferTime(DateUtils.getCurrentDate());
			bcOfferRecordList.add(bcor);

            logger.info("---bc---oneDetail:  " + oneDetail);
			oneDetail.append("\r\n");//换一行
			fileContent.append(oneDetail);
		}

        logger.info("---bc---fileContent:   " + fileContent);
		String fileFullPath = CommonMultiFileUtil.uploadFile(".DAT", fileContent.toString(), 
				OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH), PathConstants.BANK_COLLECTION, "SRCC");
		return fileFullPath;
	}

	@Override
	public String exportSumFile(int timesInThisMonth, PropertyCompanyBCInfo bcInfo, List<BcFileDefine> bcFileDefineList,
			List<PPBankCollectionWithPayBill> bcPayBillList) throws IOException {
		StringBuilder fileContent = new StringBuilder();
		int i = 0;
		fileContent.append(BankCollectionUtils.getOneFieldValue(bcInfo.getContractNo(), bcFileDefineList.get(i++)));//合同号（协议号）
		fileContent.append(BankCollectionUtils.getOneFieldValue(bcPayBillList.size()+"", bcFileDefineList.get(i++)));//托收户数
		
		BigDecimal totalAmt = BigDecimal.ZERO;
		for(PPBankCollectionWithPayBill pb: bcPayBillList){
			totalAmt = totalAmt.add(pb.getPbAmount());
		}
		totalAmt = totalAmt.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
		fileContent.append(BankCollectionUtils.getOneFieldValue(totalAmt.toString(), bcFileDefineList.get(i++)));//金额合计
		fileContent.append(BankCollectionUtils.getOneFieldValue("0", bcFileDefineList.get(i++)));//成功户数
		fileContent.append(BankCollectionUtils.getOneFieldValue("0.00", bcFileDefineList.get(i++)));//成功总金额
		
		fileContent.append(BankCollectionUtils.getOneFieldValue(DateUtils.convertDateToStr(new Date(), "yyyy/MM/dd") + DateUtils.convertDateToStr(new Date(), "yyyy/MM/dd"),
				bcFileDefineList.get(i++)));//托收月份:2017/05/102017/05/10
		
		fileContent.append(BankCollectionUtils.getOneFieldValue("0 ", bcFileDefineList.get(i++)));//17位未知标志列，末尾有一个空格

		String fileFullPath = CommonMultiFileUtil.uploadFile(".SUM", fileContent.toString(), 
				OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH), PathConstants.BANK_COLLECTION, "SRCC");
		return fileFullPath;
	}

	@Override
	public ResultMsg importDetailFile(MultipartFile uploadfile, List<BcRebackDetailEntity> rebackDetailEntityList,
			List<BcFileDefine> bcFileDefineList, BigInteger bcgroupBuildingCycleId) throws IOException {
		//上传文件处理--解压zip文件
		InputStream inputStream = uploadfile.getInputStream();
		if(!uploadfile.getOriginalFilename().contains(".RET")) return new ResultMsg("文件格式不对，必须为.RET格式", false);

		checkFile(inputStream, rebackDetailEntityList);

		if(DataUtil.isEmpty(rebackDetailEntityList)) {
			return new ResultMsg("回盘文件格式不匹配，或回盘文件内容有误", false);
		}

		return new ResultMsg(null, true);
	}

	private void checkFile(InputStream in,List<BcRebackDetailEntity> rebackDetailEntityList) throws IOException {
		PayBillBaseService payBillBaseService = (PayBillBaseService) CnfantasiaCommbusiUtil.getBeanManager("payBillBaseService");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BOMInputStream(in), "UTF-8"));
		String lineStr = "";
		int i = 1;
		while ((lineStr = bufferedReader.readLine()) != null && lineStr.trim().length() > 0) {
			logger.debug("第"+i+"行回盘记录:"+lineStr);
			String rebackContent = lineStr;
			lineStr = lineStr.trim();
			lineStr = lineStr.replaceAll(" {2,}", " ");
			lineStr = lineStr.replaceAll(" ", "@");
			//  1  16  523000173110      200.96Y
			String[] lineArr = lineStr.split("@");
			String pbShortCode = lineArr[0].trim();
			logger.debug("第"+i+"行回盘记录--pbShortCode:"+ pbShortCode);

			//账单id
			BigInteger paybillId = null;
			try {
				//数据库查询账单id--根据账单短编码
				PayBill pbQry = new PayBill();
				pbQry.setShortCode(pbShortCode);
				
				List<PayBill> payBillList = payBillBaseService.getPayBillByCondition(MapConverter.convertBean(pbQry));
				
				if (payBillList.isEmpty())
					continue;
				
				paybillId = payBillList.get(0).getId();
				
				if (paybillId == null)
					continue;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				continue;
			}
			
			Long amount = null;
			try {
				String amountStr = lineArr[3];
				amountStr = amountStr.substring(0, amountStr.length() - 1);
				BigDecimal dbAmount = new BigDecimal(amountStr.trim());
				BigDecimal chengshu = new BigDecimal("100");
				BigDecimal resout = dbAmount.multiply(chengshu);
				amount = resout.longValue();
			} catch (NumberFormatException e) {
				e.printStackTrace();
				continue;
			}
			logger.debug("第"+i+"行回盘记录(回盘金额)--amount:"+amount);

			String mark = null;
			try {
				String markStr = lineArr[3];
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

			rebackDetailEntityList.add(rebackDetailEntity);
			i++;
		}
	}

}
