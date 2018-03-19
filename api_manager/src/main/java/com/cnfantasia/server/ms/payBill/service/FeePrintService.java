package com.cnfantasia.server.ms.payBill.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.room.entity.RealRoomEntity;
import com.cnfantasia.server.ms.payBill.dao.FeePrintDao;
import com.cnfantasia.server.ms.payBill.entity.FeePrint;
import com.cnfantasia.server.ms.payBill.entity.SelectDo;

public class FeePrintService {
	
	private FeePrintDao feePrintDao;
	
	public List<FeePrint> getFeePrintList (Map<String, Object> paramMap) {
		return feePrintDao.getFeePrintList(paramMap);
	}
	
	public int insertFeePrint(FeePrint feePrint) {
		return feePrintDao.insertFeePrint(feePrint);
	}

	public void setFeePrintDao(FeePrintDao feePrintDao) {
		this.feePrintDao = feePrintDao;
	}

	public int getFeePrintListCount(Map<String, Object> paramMap) {
		return feePrintDao.getFeePrintListCount(paramMap);
	}
	
	public List<SelectDo> getSelectBuildingList(Long parentId) {
		return feePrintDao.getSelectBuildingList(parentId);
	}
	
	public List<SelectDo> getSelectUnitNameList(Long parentId) {
		return feePrintDao.getSelectUnitNameList(parentId);
	}
	
	public List<SelectDo> getSelectRoomNumList(Map<String, Object> paramMap) {
		return feePrintDao.getSelectRoomNumList(paramMap);
	}
	
	public RealRoomEntity selectRealRoomById(BigInteger realRoomId) {
		return feePrintDao.selectRealRoomById(realRoomId);
	}
	
}
