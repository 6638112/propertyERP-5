package com.cnfantasia.server.api.access.service;

/**
 * 车禁job接口
 * 
 * @author liyulin
 * @version 1.0 2017年12月14日 下午4:56:58
 */
public interface ICarTaskService {

	/**
	 * 释放task锁
	 */
	public void releaseTaskLock();

	/**
	 * 线下月卡缴费同步
	 */
	public void synMonthCarPayLog();

	/**
	 * 线上月卡缴费推送
	 */
	public void pushMonthCarPayLog();

	/**
	 * 线上临停缴费推送
	 */
	public void pushTempCarPayLog();

	/**
	 * 车辆信息同步
	 */
	public void synCarInfo();

	/**
	 * 停车记录同步（进场、出场）
	 */
	public void synParkingRecord();

}
