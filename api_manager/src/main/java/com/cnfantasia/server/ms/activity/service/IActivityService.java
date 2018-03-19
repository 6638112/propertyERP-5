package com.cnfantasia.server.ms.activity.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cnfantasia.server.domainbase.ebuyAdvertise.entity.EbuyAdvertise;
import com.cnfantasia.server.ms.advertise.entity.AdvertiseDto;

/**
 * 活动管理
 * 
 * @author liyulin
 * @version 1.0 2016年12月27日 下午2:54:25
 */
public interface IActivityService {

	/**
	 * 查询活动列表（code=ACTIVITY_ENTRANCE）
	 * 
	 * @return
	 */
	public List<EbuyAdvertise> selectActivities();
	
	/**
	 * 更新活动
	 * 
	 * @param advertiseDto
	 * @param request
	 * @return
	 */
	public boolean updateActivity(AdvertiseDto advertiseDto, HttpServletRequest request);
	
}
