package com.cnfantasia.server.api.bankCollection.transfer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
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
import com.cnfantasia.server.common.utils.HSSFCellUtil;
import com.cnfantasia.server.common.utils.UUIDGenerater;
import com.cnfantasia.server.domainbase.bcFileDefine.entity.BcFileDefine;
import com.cnfantasia.server.domainbase.bcGroupBuildingCycle.entity.BcGroupBuildingCycle;
import com.cnfantasia.server.domainbase.bcOfferRecord.entity.BcOfferRecord;
import com.cnfantasia.server.domainbase.propertyCompany.dao.IPropertyCompanyBaseDao;
import com.cnfantasia.server.domainbase.propertyCompany.entity.PropertyCompany;
import com.cnfantasia.server.domainbase.propertyCompanyBankCollectionInfo.dao.IPropertyCompanyBankCollectionInfoBaseDao;
import com.cnfantasia.server.domainbase.propertyCompanyBankCollectionInfo.entity.PropertyCompanyBankCollectionInfo;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.constant.PathConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;

/**
 * 农商行支行
 * @author liyulin
 */
public class NongShangHangBranchCollectionTransfer implements IBankCollectionTransfer {
	private final Logger logger = Logger.getLogger(getClass());
	
	private IPropertyCompanyBankCollectionInfoBaseDao propertyCompanyBankCollectionInfoBaseDao;
	private IPropertyCompanyBaseDao propertyCompanyBaseDao;

	public IPropertyCompanyBankCollectionInfoBaseDao getPropertyCompanyBankCollectionInfoBaseDao() {
		if(propertyCompanyBankCollectionInfoBaseDao==null) {
			propertyCompanyBankCollectionInfoBaseDao = (IPropertyCompanyBankCollectionInfoBaseDao)CnfantasiaCommbusiUtil.getBeanManager("propertyCompanyBankCollectionInfoBaseDao");
		}
		return propertyCompanyBankCollectionInfoBaseDao;
	}

	public IPropertyCompanyBaseDao getPropertyCompanyBaseDao() {
		if(propertyCompanyBaseDao==null) {
			propertyCompanyBaseDao = (IPropertyCompanyBaseDao)CnfantasiaCommbusiUtil.getBeanManager("propertyCompanyBaseDao");
		}
		return propertyCompanyBaseDao;
	}
	

	/**
	 * 导出明细出盘文件
	 */
	@Override
	public String exportDetailFile(List<BcFileDefine> bcFileDefineList, List<PPBankCollectionWithPayBill> bcPayBillList,
			BcGroupBuildingCycle bcGBCycle, List<BcOfferRecord> bcOfferRecordList) throws IOException {
		String fileServiceBasePath = CnfantasiaCommbusiUtil.getFileServiceBasePath();
		String filePath = fileServiceBasePath + ("docs/NongShangHangBranchOffer_template.xls");
		
		FileInputStream fin = new FileInputStream(new File(filePath));
		HSSFWorkbook wb = new HSSFWorkbook(fin);
		HSSFSheet sheet = wb.getSheetAt(0);
		
		HSSFCellStyle leftStyle = wb.createCellStyle();
		leftStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		HSSFCellStyle rightStyle = wb.createCellStyle();
		rightStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		
		int dataStartRow = 5;
		BigDecimal total = BigDecimal.ZERO;
		for(int i = 0; i <  bcPayBillList.size(); i++){
			PPBankCollectionWithPayBill pb = bcPayBillList.get(i);
			HSSFRow row = sheet.createRow(i+dataStartRow);
			int j = 0;
			HSSFCell cellNo = row.createCell(j++);
			cellNo.setCellStyle(rightStyle);
			cellNo.setCellValue(i+1);
			
			HSSFCell cellBon = row.createCell(j++);
			cellBon.setCellStyle(leftStyle);
			cellBon.setCellValue(pb.getBankOwnerName());
			
			HSSFCell cellBc = row.createCell(j++);
			cellBc.setCellStyle(rightStyle);
			cellBc.setCellValue(pb.getBankAccount());
			
			HSSFCell cellPa = row.createCell(j++);
			cellPa.setCellStyle(rightStyle);
			cellPa.setCellValue(pb.getPbAmountStr());
			
			total = total.add(new BigDecimal(pb.getPbAmountStr()));
			
			BcOfferRecord bcor = new BcOfferRecord();
			bcor.settBcGroupBuildingCycleFId(bcGBCycle.getId());
			bcor.setOfferContent(pb.getBankAccount() + "  " + pb.getPpName() + "  " + pb.getPbAmountStr() + "  " + pb.getBankName());
			bcor.setPaybillId(pb.getPbId());
			bcor.setOfferTime(DateUtils.getCurrentDate());
			bcOfferRecordList.add(bcor);
		}
		
		if(bcGBCycle.gettPropertyCompanyBankCollectionInfoFId()!=null) {
			PropertyCompanyBankCollectionInfo propertyCompanyBankCollectionInfo = getPropertyCompanyBankCollectionInfoBaseDao().selectPropertyCompanyBankCollectionInfoBySeqId(bcGBCycle.gettPropertyCompanyBankCollectionInfoFId());
			PropertyCompany pc = getPropertyCompanyBaseDao().selectPropertyCompanyBySeqId(propertyCompanyBankCollectionInfo.gettPropertyCompanyFId());
			if(StringUtils.isNotBlank(pc.getLinkman()) && StringUtils.isNotBlank(pc.getMobilePhone())) {
				String linkInfo = "联系人："+pc.getLinkman()+"　　"+pc.getMobilePhone();
				HSSFRow row = sheet.getRow(1);
				if(null==row) {
					row = sheet.createRow(1);
				}
				HSSFCell cell = row.createCell(2);
				cell.setCellStyle(leftStyle);
				cell.setCellValue(linkInfo);
			}
			if(StringUtils.isNotBlank(propertyCompanyBankCollectionInfo.getBankAccount())) {
				String accountInfo = "托收帐号："+propertyCompanyBankCollectionInfo.getBankAccount();
				HSSFRow row = sheet.getRow(2);
				if(null==row) {
					row = sheet.createRow(2);
				}
				HSSFCell cell = row.createCell(2);
				cell.setCellStyle(leftStyle);
				cell.setCellValue(accountInfo);
			}
		}
		
		// totalTxt
		HSSFRow row = sheet.createRow(dataStartRow+(bcPayBillList.size()+2));
		HSSFCell cellTotalTxt = row.createCell(2);
		cellTotalTxt.setCellStyle(leftStyle);
		cellTotalTxt.setCellValue("Total:");
		// total
		HSSFCell cellTotal = row.createCell(3);
		cellTotal.setCellStyle(rightStyle);
		cellTotal.setCellValue(total.doubleValue());
		
		File fileDir = new File(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + PathConstants.BANK_COLLECTION);
		if (!fileDir.exists()) {
			fileDir.mkdir();
		}

		String absPath = PathConstants.BANK_COLLECTION + bcGBCycle.getGbNames() + "_"+UUIDGenerater.getFileName() + ".xls";
		String fileFullPath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + absPath;
		FileOutputStream fout = new FileOutputStream(new File(fileFullPath));
		wb.write(fout);
		return absPath;
	}

	/**
	 * 导出sum出盘文件
	 */
	@Override
	public String exportSumFile(int timesInThisMonth, PropertyCompanyBCInfo bcInfo, List<BcFileDefine> bcFileDefineList,
			List<PPBankCollectionWithPayBill> bcPayBillList) throws IOException {
		return null;
	}

	/**
	 * 导入明细回盘文件
	 */
	@Override
	public ResultMsg importDetailFile(MultipartFile uploadfile, List<BcRebackDetailEntity> rebackDetailEntityList,
			List<BcFileDefine> bcFileDefineList, BigInteger bcgroupBuildingCycleId) throws IOException {
		if(!uploadfile.getOriginalFilename().contains(".xls")) return new ResultMsg("文件格式不对，必须为.xls格式", false);

		HSSFWorkbook wb = new HSSFWorkbook(uploadfile.getInputStream());
		HSSFSheet sheet = wb.getSheetAt(0);
		int startIndex = 1;//开始行为12
		List<BigInteger> ids = new ArrayList<BigInteger>();
		for (int i = startIndex; i <= sheet.getLastRowNum(); i++) {
			String lineStr = "";
			//序号为空则不进行计算
			if(null==sheet.getRow(i)) continue;
			if(StringUtils.isBlank(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(0)))) continue;

			String bankNo = HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(2));
			if(StringUtils.isBlank(bankNo)) continue;

			Long amount = null;
			try {
				String strAmount = sheet.getRow(i).getCell(3).toString();
				BigDecimal dbAmount = new BigDecimal(strAmount);
				BigDecimal chengshu = new BigDecimal("100");
				BigDecimal resout = dbAmount.multiply(chengshu);
				amount = resout.longValue();
				lineStr += "金额："+strAmount + "元 ,";
			} catch (NumberFormatException e) {
				e.printStackTrace();
				continue;
			}
			logger.debug("第"+(i + 1)+"行回盘记录(回盘金额)--amount:"+amount);

			//账单id
			BigInteger paybillId = null;
			try {
				//数据库查询账单id--根据银行账号和金额进行查询
				BankCollectionService bankCollectionService = (BankCollectionService) CnfantasiaCommbusiUtil.getBeanManager("bankCollectionService");
				List<BigInteger> payBillIdList = bankCollectionService.getPayBillCheckForPingAnBankCollection(bankNo, amount, ids, bcgroupBuildingCycleId);
				if(DataUtil.isEmpty(payBillIdList)) continue;
				paybillId = payBillIdList.get(0);
				if(paybillId == null) continue;
				ids.add(paybillId);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			logger.debug("第"+(i + 1)+"行回盘记录--paybillId:"+paybillId);

			//其他信息
			lineStr += "账号：" + bankNo + " ," + "户名：" + HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(1)) + " ,";

			String mark = null;
			try {
				String markStr = HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(4)).trim();
				mark = "过账成功".equals(markStr) ? "Y" : "E";
				lineStr += "处理结果："+markStr;
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			logger.debug("第"+(i + 1)+"行回盘记录(银行标识)--mark:"+mark);

			BcRebackDetailEntity rebackDetailEntity = new BcRebackDetailEntity();
			rebackDetailEntity.setPaybillId(paybillId);
			rebackDetailEntity.setRebackContent(lineStr);
			rebackDetailEntity.setAmount(amount);
			rebackDetailEntity.setStatus(mark);

			rebackDetailEntityList.add(rebackDetailEntity);
		}

		if(DataUtil.isEmpty(rebackDetailEntityList)) {
			return new ResultMsg("回盘文件格式不匹配，或回盘文件内容有误", false);
		}

		return new ResultMsg(null, true);
	}

}
