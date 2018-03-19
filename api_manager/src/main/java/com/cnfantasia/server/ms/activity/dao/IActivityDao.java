package com.cnfantasia.server.ms.activity.dao;

import java.util.List;

import com.cnfantasia.server.domainbase.ebuyAdvertise.entity.EbuyAdvertise;

/**
 * 活动管理
 * 
 * @author liyulin
 * @version 1.0 2016年12月27日 下午2:54:25
 */
public interface IActivityDao {
	
	/**
	 * 查询活动列表（code=ACTIVITY_ENTRANCE）
	 * 
	 * @return
	 */
	public List<EbuyAdvertise> selectActivities();
}
