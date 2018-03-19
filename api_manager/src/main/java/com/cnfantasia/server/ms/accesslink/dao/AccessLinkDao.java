package com.cnfantasia.server.ms.accesslink.dao;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.ms.accesslink.dto.BlackListDto;
import com.cnfantasia.server.ms.accesslink.dto.ParkingRecordDto;
import com.cnfantasia.server.ms.pub.dao.AbstractBaseDao;

/**
 * 车禁-易豪生客户端连接管理dao
 * 
 * @author liyulin
 * @version 1.0 2016年6月28日 下午1:38:02
 */
public class AccessLinkDao extends AbstractBaseDao implements IAccessLinkDao {

	/**
	 * 查询停车记录
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<ParkingRecordDto> selectParkingRecordForList(Map<String, Object> paramMap){
		return sqlSession.selectList("accesslink.select_parkingListIndex_withPage", paramMap);
	}
	
	/**
	 * 查询停车记录条数
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public int selectParkingRecordForCount(Map<String, Object> paramMap){
		return sqlSession.selectOne("accesslink.select_parkingListIndex_count", paramMap);
	}
	
	/**
	 * 查询黑名单记录
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<BlackListDto> selectBlackListForList(Map<String, Object> paramMap){
		return sqlSession.selectList("accesslink.select_blackListIndex_withPage", paramMap);
	}
	
	/**
	 * 查询黑名单记录条数
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public int selectBlackListForCount(Map<String, Object> paramMap){
		return sqlSession.selectOne("accesslink.select_blackListIndex_count", paramMap);
	}
}
