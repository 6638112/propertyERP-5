package com.cnfantasia.server.api.payment.serviceUntran;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyOrder.dao.IEbuyOrderBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyOrderRelation.dao.IEbuyOrderRelationBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrderRelation.entity.EbuyOrderRelation;
import com.cnfantasia.server.domainbase.ebuyPayRecord.dao.IEbuyPayRecordBaseDao;
import com.cnfantasia.server.domainbase.ebuyPayRecord.entity.EbuyPayRecord;

@Service
public class CommEbuyPayRecordService {
	
	private IEbuyOrderRelationBaseDao ebuyOrderRelationBaseDao;
	private IUuidManager uuidManager;
	private IEbuyPayRecordBaseDao ebuyPayRecordBaseDao;
	private IEbuyOrderBaseDao ebuyOrderBaseDao;

	public void setEbuyOrderRelationBaseDao(
			IEbuyOrderRelationBaseDao ebuyOrderRelationBaseDao) {
		this.ebuyOrderRelationBaseDao = ebuyOrderRelationBaseDao;
	}

	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	public void setEbuyPayRecordBaseDao(IEbuyPayRecordBaseDao ebuyPayRecordBaseDao) {
		this.ebuyPayRecordBaseDao = ebuyPayRecordBaseDao;
	}

	public void setEbuyOrderBaseDao(IEbuyOrderBaseDao ebuyOrderBaseDao) {
		this.ebuyOrderBaseDao = ebuyOrderBaseDao;
	}

	public boolean insertEbuyPayRecord(EbuyPayRecord totalEbuyPayRecord){
		List<EbuyPayRecord> insertEbuyPayRecordList = new ArrayList<EbuyPayRecord>();
		BigInteger totalEbuyPayRecordId = null;
		
		BigInteger totalOrderId = totalEbuyPayRecord.gettEbuyOrderFId();
		EbuyOrder ebuyOrder = ebuyOrderBaseDao.selectEbuyOrderBySeqId(totalOrderId);
		if(ebuyOrder!=null && ebuyOrder.getType()!=null && ebuyOrder.getType()==EbuyDict.EbuyOrder_Type.Total_Property_Bill){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("parentId", totalOrderId);
			List<EbuyOrderRelation> ebuyOrderRelations = ebuyOrderRelationBaseDao.selectEbuyOrderRelationByCondition(paramMap, false);
			
			List<BigInteger> eprIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_pay_record, ebuyOrderRelations.size()+1);
			for(int i=0; i<ebuyOrderRelations.size(); i++){
				EbuyOrderRelation ebuyOrderRelation = ebuyOrderRelations.get(i);
				BigInteger subOrderId = ebuyOrderRelation.getSubId();
				
				EbuyPayRecord ebuyPayRecord = new EbuyPayRecord();
				BeanUtils.copyProperties(totalEbuyPayRecord, ebuyPayRecord);
				
				ebuyPayRecord.setId(eprIds.get(i));
				ebuyPayRecord.settEbuyOrderFId(subOrderId);
				
				insertEbuyPayRecordList.add(ebuyPayRecord);
			}
			totalEbuyPayRecordId = eprIds.get(eprIds.size()-1);
		} else {
			totalEbuyPayRecordId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_pay_record);
		}
		
		totalEbuyPayRecord.setId(totalEbuyPayRecordId);
		insertEbuyPayRecordList.add(totalEbuyPayRecord);
		
		int count = ebuyPayRecordBaseDao.insertEbuyPayRecordBatch(insertEbuyPayRecordList);
		return count>0;
	}
}
