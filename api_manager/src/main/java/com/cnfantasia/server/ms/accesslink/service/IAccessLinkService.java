package com.cnfantasia.server.ms.accesslink.service;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.ms.accesslink.dto.BlackListDto;
import com.cnfantasia.server.ms.accesslink.dto.ParkingRecordDto;

/**
 * 车禁-易豪生客户端连接管理service
 * 
 * @author liyulin
 * @version 1.0 2016年6月28日 下午1:38:02
 */
public interface IAccessLinkService {
	
	/**
	 * 查询停车记录
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<ParkingRecordDto> selectParkingRecordForList(Map<String, Object> paramMap);
	
	/**
	 * 查询停车记录条数
	 * 
	 * @param paramMap
	 * @return
	 */
	public int selectParkingRecordForCount(Map<String, Object> paramMap);
	
	/**
	 * 查询黑名单记录
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<BlackListDto> selectBlackListForList(Map<String, Object> paramMap);
	
	/**
	 * 查询黑名单记录条数
	 * 
	 * @param paramMap
	 * @return
	 */
	public int selectBlackListForCount(Map<String, Object> paramMap);
}
