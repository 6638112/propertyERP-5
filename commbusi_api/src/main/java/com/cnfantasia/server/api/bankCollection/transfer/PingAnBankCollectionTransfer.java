package com.cnfantasia.server.api.bankCollection.transfer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
import com.cnfantasia.server.common.utils.HSSFCellUtil;
import com.cnfantasia.server.common.utils.UUIDGenerater;
import com.cnfantasia.server.domainbase.bcFileDefine.entity.BcFileDefine;
import com.cnfantasia.server.domainbase.bcGroupBuildingCycle.entity.BcGroupBuildingCycle;
import com.cnfantasia.server.domainbase.bcOfferRecord.entity.BcOfferRecord;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.constant.PathConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;

/**
 * 平安银行出盘回盘
 * @author wenfq 2017-04-20
 *
 */
public class PingAnBankCollectionTransfer implements IBankCollectionTransfer {
	private org.apache.commons.logging.Log logger = LogFactory.getLog(getClass());
	@Override
	public String exportDetailFile(List<BcFileDefine> bcFileDefineList, List<PPBankCollectionWithPayBill> bcPayBillList,
			BcGroupBuildingCycle bcGBCycle, List<BcOfferRecord> bcOfferRecordList) throws IOException {
//		String filePath = SessionManager.getSession().getServletContext().getRealPath("/docs/pingAnOffer_template.xls");
		
		String fileServiceBasePath = CnfantasiaCommbusiUtil.getFileServiceBasePath();
		String filePath = fileServiceBasePath + ("docs/pingAnOffer_template.xls");
		
		FileInputStream fin = new FileInputStream(new File(filePath));
		HSSFWorkbook wb = new HSSFWorkbook(fin);
		HSSFSheet sheet = wb.getSheetAt(0);

		for(int i = 0; i <  bcPayBillList.size(); i++){
			PPBankCollectionWithPayBill pb = bcPayBillList.get(i);
			HSSFRow row = sheet.createRow(i+1);
			int j = 0;
			row.createCell(j++).setCellValue(pb.getBankAccount());
			row.createCell(j++).setCellValue(pb.getPpName());
			row.createCell(j++).setCellValue(pb.getPbAmountStr());
			row.createCell(j++).setCellValue(pb.getBankName());
			
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

		String absPath = PathConstants.BANK_COLLECTION + "DFGZ_" + UUIDGenerater.getFileName() + ".xls";
		String fileFullPath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + absPath;
		FileOutputStream fout = new FileOutputStream(new File(fileFullPath));
		wb.write(fout);
		return absPath;
	}

	@Override
	public String exportSumFile(int timesInThisMonth, PropertyCompanyBCInfo bcInfo, List<BcFileDefine> bcFileDefineList,
			List<PPBankCollectionWithPayBill> bcPayBillList) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMsg importDetailFile(MultipartFile uploadfile, List<BcRebackDetailEntity> rebackDetailEntityList,
			List<BcFileDefine> bcFileDefineList, BigInteger bcgroupBuildingCycleId) throws IOException {
		if(!uploadfile.getOriginalFilename().contains(".xls")) return new ResultMsg("文件格式不对，必须为.xls格式", false);

		HSSFWorkbook wb = new HSSFWorkbook(uploadfile.getInputStream());
		HSSFSheet sheet = wb.getSheetAt(1);
		int startIndex = 12;//开始行为12
		List<BigInteger> ids = new ArrayList<BigInteger>();
		for (int i = startIndex; i < sheet.getLastRowNum(); i++) {
			String lineStr = "";
			//序号为空则不进行计算
			if(DataUtil.isEmpty(sheet.getRow(i))) continue;
			if(DataUtil.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(1)))) continue;

			String bankNo = HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(2));
			if(DataUtil.isEmpty(bankNo)) continue;

			Long amount = null;
			try {
				String strAmount = sheet.getRow(i).getCell(4).toString();
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
			lineStr += "账号：" + bankNo + " ," +
					"户名：" + HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(3)) + " ,";

			String mark = null;
			try {
				String markStr = HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(5)).trim();
				mark = "成功".equals(markStr) ? "Y" : "E";
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
