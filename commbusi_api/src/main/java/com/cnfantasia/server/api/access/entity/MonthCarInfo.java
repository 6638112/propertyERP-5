package com.cnfantasia.server.api.access.entity;

/**
 * 月卡信息
 * 
 * @author liyulin
 * @version 1.0 2017年12月12日 上午10:33:10
 */
public class MonthCarInfo extends BaseCarResult {

	/** 总费用（分） */
	private long amt;
	/** 实收费用（分） */
	private long realAmt;
	/** 有效期（毫秒） */
	private long expire;
	/** 锁住状态 */
	private boolean lock;
	/** 月卡ID（小蜜蜂使用） */
	private String carId;
	/** 月卡类型ID（小蜜蜂使用） */
	private String carTypeId;

	public MonthCarInfo() {
		super();
	}

	public MonthCarInfo(boolean state) {
		super();
		super.setState(state);
	}

	public MonthCarInfo(long amt, long realAmt, long expire, boolean lock, String carId, String carTypeId) {
		super();
		this.amt = amt;
		this.realAmt = realAmt;
		this.expire = expire;
		this.lock = lock;
		this.carId = carId;
		this.carTypeId = carTypeId;
	}

	public long getAmt() {
		return amt;
	}

	public void setAmt(long amt) {
		this.amt = amt;
	}

	public long getRealAmt() {
		return realAmt;
	}

	public void setRealAmt(long realAmt) {
		this.realAmt = realAmt;
	}

	public long getExpire() {
		return expire;
	}

	public void setExpire(long expire) {
		this.expire = expire;
	}

	public boolean isLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(String carTypeId) {
		this.carTypeId = carTypeId;
	}

}
