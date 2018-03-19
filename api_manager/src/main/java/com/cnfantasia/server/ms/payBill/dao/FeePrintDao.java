package com.cnfantasia.server.ms.payBill.dao;


import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.room.entity.RealRoomEntity;
import com.cnfantasia.server.ms.payBill.entity.FeePrint;
import com.cnfantasia.server.ms.payBill.entity.SelectDo;
import com.cnfantasia.server.ms.pub.dao.AbstractBaseDao;

public class FeePrintDao extends AbstractBaseDao {
	
	private Log logger = LogFactory.getLog(getClass());
	
	public List<FeePrint> getFeePrintList (Map<String, Object> paramMap) {
		return sqlSession.selectList("feePrint.select_fee_print_list", paramMap);
	}
	
	public int insertFeePrint (FeePrint feePrint) {
		return sqlSession.insert("feePrint.insert_fee_print", feePrint);
	}

	public int getFeePrintListCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("feePrint.select_fee_print_count", paramMap);
	}
	
	public List<SelectDo> getSelectBuildingList(Long parentId) {
		return sqlSession.selectList("feePrint.getSelectBuildingList", parentId);
	}
	
	public List<SelectDo> getSelectUnitNameList(Long parentId) {
		return sqlSession.selectList("feePrint.getSelectUnitNameList", parentId);
	}
	
	public List<SelectDo> getSelectRoomNumList(Map<String, Object> paramMap) {
		return sqlSession.selectList("feePrint.getSelectRoomNumList", paramMap);
	}
	
	public RealRoomEntity selectRealRoomById(BigInteger realRoomId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("realRoomId", realRoomId);
		return sqlSession.selectOne("feePrint.select_RealRoom_ById", tmpMap);
	}
	
	
//	public List<SelectDo> getSelectBuildingList(Long parentId) {
//		return sqlSession.selectList("feePrint.getSelectBuildingList", parentId);
//	}
//	
//	public List<SelectDo> getSelectBuildingList(Long parentId) {
//		return sqlSession.selectList("feePrint.getSelectBuildingList", parentId);
//	}

}
