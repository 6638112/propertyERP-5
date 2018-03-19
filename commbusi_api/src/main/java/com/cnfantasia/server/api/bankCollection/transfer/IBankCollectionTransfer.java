package com.cnfantasia.server.api.bankCollection.transfer;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cnfantasia.server.api.bankCollection.entity.BcRebackDetailEntity;
import com.cnfantasia.server.api.bankCollection.entity.PPBankCollectionWithPayBill;
import com.cnfantasia.server.api.bankCollection.entity.PropertyCompanyBCInfo;
import com.cnfantasia.server.api.bankCollection.entity.ResultMsg;
import com.cnfantasia.server.domainbase.bcFileDefine.entity.BcFileDefine;
import com.cnfantasia.server.domainbase.bcGroupBuildingCycle.entity.BcGroupBuildingCycle;
import com.cnfantasia.server.domainbase.bcOfferRecord.entity.BcOfferRecord;

/**
 * 出盘回盘转换接口
 * @author wenfq 2017-04-11
 *
 */
public interface IBankCollectionTransfer {
	/**
	 * 导出明细出盘文件
	 * @author wenfq
	 * @param bcFileDefineList 文件定义列表
	 * @param bcPayBillList 账单列表
	 * @param bcGBCycle 
	 * @param bcOfferRecordList 出盘记录
	 * @return 文件所在全路径
	 * @throws IOException 
	 */

	String exportDetailFile(List<BcFileDefine> bcFileDefineList, List<PPBankCollectionWithPayBill> bcPayBillList, BcGroupBuildingCycle bcGBCycle, List<BcOfferRecord> bcOfferRecordList) throws IOException;

	/**
	 * 导出sum出盘文件
	 * @author wenfq
	 * @param timesInThisMonth 本月第几天出盘
	 * @return
	 */
	String exportSumFile(int timesInThisMonth, PropertyCompanyBCInfo bcInfo, List<BcFileDefine> bcFileDefineList, List<PPBankCollectionWithPayBill> bcPayBillList) throws IOException;
	
	/**
	 * 导入明细回盘文件
	 * @param uploadfile
	 * @param rebackDetailEntityList
	 * @param bcFileDefineList
	 */
	ResultMsg importDetailFile(MultipartFile uploadfile, List<BcRebackDetailEntity> rebackDetailEntityList, List<BcFileDefine> bcFileDefineList, BigInteger bcgroupBuildingCycleId) throws IOException;
}
