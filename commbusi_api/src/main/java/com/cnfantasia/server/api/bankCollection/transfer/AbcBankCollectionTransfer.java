package com.cnfantasia.server.api.bankCollection.transfer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.cnfantasia.server.api.bankCollection.entity.BcRebackDetailEntity;
import com.cnfantasia.server.api.bankCollection.entity.PPBankCollectionWithPayBill;
import com.cnfantasia.server.api.bankCollection.entity.PropertyCompanyBCInfo;
import com.cnfantasia.server.api.bankCollection.entity.ResultMsg;
import com.cnfantasia.server.api.bankCollection.service.BankCollectionService;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.UUIDGenerater;
import com.cnfantasia.server.domainbase.bcFileDefine.entity.BcFileDefine;
import com.cnfantasia.server.domainbase.bcGroupBuildingCycle.entity.BcGroupBuildingCycle;
import com.cnfantasia.server.domainbase.bcOfferRecord.entity.BcOfferRecord;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.constant.PathConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;

/**
 * 中国农业银行 出盘回盘
 * @author wenfq 2017-08-09
 */
public class AbcBankCollectionTransfer implements IBankCollectionTransfer {
	private org.apache.commons.logging.Log logger = LogFactory.getLog(getClass());
	@Override
	public String exportDetailFile(List<BcFileDefine> bcFileDefineList, List<PPBankCollectionWithPayBill> bcPayBillList,
			BcGroupBuildingCycle bcGBCycle, List<BcOfferRecord> bcOfferRecordList) throws IOException {
		//String filePath = SessionManager.getSession().getServletContext().getRealPath("/docs/abcBankOffer_template.xls");
		String fileServiceBasePath = CnfantasiaCommbusiUtil.getFileServiceBasePath();
		String filePath = fileServiceBasePath + ("docs/abcBankOffer_template.xls");
		
		FileInputStream fin = new FileInputStream(new File(filePath));
		HSSFWorkbook wb = new HSSFWorkbook(fin);
		HSSFSheet sheet = wb.getSheetAt(0);
		int dataStartRow = 1;
		for(int i = 0; i <  bcPayBillList.size(); i++){
			PPBankCollectionWithPayBill pb = bcPayBillList.get(i);
			HSSFRow row = sheet.createRow(i+dataStartRow);
			int j = 0;
			row.createCell(j++).setCellValue(pb.getRoomNo());
			row.createCell(j++).setCellValue(pb.getBankOwnerName());
			row.createCell(j++).setCellValue(pb.getBankAccount());
			row.createCell(j++).setCellValue(pb.getPbAmountStr());
			
			BcOfferRecord bcor = new BcOfferRecord();
			bcor.settBcGroupBuildingCycleFId(bcGBCycle.getId());
			bcor.setOfferContent(pb.getBankAccount() + "  " + pb.getPpName() + "  " 
					+ pb.getPbAmountStr() + "  " + pb.getBankName());
			bcor.setPaybillId(pb.getPbId());
			bcor.setOfferTime(DateUtils.getCurrentDate());
			bcOfferRecordList.add(bcor);
		}
		
		File fileDir = new File(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) +
				PathConstants.BANK_COLLECTION);
		if (!fileDir.exists()) {
			fileDir.mkdir();
		}

		String absPath = PathConstants.BANK_COLLECTION + bcGBCycle.getGbNames() + UUIDGenerater.getFileName() + ".xls";
		String fileFullPath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + absPath;
		FileOutputStream fout = new FileOutputStream(new File(fileFullPath));
		wb.write(fout);
		return absPath;
	}

	@Override
	public String exportSumFile(int timesInThisMonth, PropertyCompanyBCInfo bcInfo, List<BcFileDefine> bcFileDefineList,
			List<PPBankCollectionWithPayBill> bcPayBillList) throws IOException {
		return null;
	}

	@Override
	public ResultMsg importDetailFile(MultipartFile uploadfile, List<BcRebackDetailEntity> rebackDetailEntityList,
			List<BcFileDefine> bcFileDefineList, BigInteger bcgroupBuildingCycleId) throws IOException {
		
		BankCollectionService bankCollectionService = (BankCollectionService) CnfantasiaCommbusiUtil.getBeanManager("bankCollectionService");
		//文件名校验
		//if(!uploadfile.getOriginalFilename().contains(".txt")) return new ResultMsg("文件格式不对，必须为.txt格式", false);

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(uploadfile.getInputStream()));
		String lineStr = "";
		int i = 0;
		List<BigInteger> ids = new ArrayList<BigInteger>();
		while ((lineStr = bufferedReader.readLine()) != null && lineStr.trim().length() > 0) {
	  /*      03635840138010151282         293.10E
	          10位空格  03意义不明
	                      635840138010151282银行卡号
	          			                  9位空格  293.10托收金额
	          							                 E托收结果														00000000成功，其它失败*/
			logger.debug("第" + (++i) + "行回盘记录:" + lineStr);
			// String rebackContent = lineStr;
			
			int start = 12;
			
			String bankNo = lineStr.substring(start, start + 22);
			if (DataUtil.isEmpty(bankNo))
				continue;

			Long amount = null;
			try {
				start = 34;
				String strAmount = lineStr.substring(start, start+11).trim();
				BigDecimal dbAmount = new BigDecimal(strAmount);
				BigDecimal chengshu = new BigDecimal("100");
				BigDecimal resout = dbAmount.multiply(chengshu);
				amount = resout.longValue();
				lineStr += "金额："+strAmount + "元 ,";
			} catch (NumberFormatException e) {
				e.printStackTrace();
				continue;
			}
			logger.debug("第"+i+"行回盘记录(回盘金额)--amount:"+amount);

			//账单id
			BigInteger paybillId = null;
			try {
				//数据库查询账单id--根据银行账号和金额进行查询
				List<BigInteger> payBillIdList = bankCollectionService.getPayBillCheckForPingAnBankCollection(bankNo, amount, ids, bcgroupBuildingCycleId);
				if(DataUtil.isEmpty(payBillIdList)) continue;
				paybillId = payBillIdList.get(0);
				if(paybillId == null) continue;
				ids.add(paybillId);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			logger.debug("第"+i+"行回盘记录--paybillId:"+paybillId);

			//托收结果标识
			String mark = null;
			try {
				start = 45;
				String markStr = lineStr.substring(start, start + 1).trim();
				mark = "Y".equals(markStr) ? "Y" : "E";
				lineStr += "处理结果："+markStr;
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			logger.debug("第"+i+"行回盘记录(银行标识)--mark:"+mark);

			BcRebackDetailEntity rebackDetailEntity = new BcRebackDetailEntity();
			rebackDetailEntity.setPaybillId(paybillId);
			rebackDetailEntity.setRebackContent(lineStr);
			rebackDetailEntity.setAmount(amount);
			rebackDetailEntity.setStatus(mark);

			rebackDetailEntityList.add(rebackDetailEntity);
			
			if(DataUtil.isEmpty(rebackDetailEntityList)) {
				return new ResultMsg("回盘文件格式不匹配，或回盘文件内容有误", false);
			}
		}

		return new ResultMsg(null, true);
	}

}
