package com.cnfantasia.server.ms.revenue.entity;

import com.cnfantasia.server.ms.revenue.entity.MinMaxDate.Location;

public class LeftRightTime {
	/**允许的开始时间*/
	private AreaTime leftTime;
	/**允许的结束时间*/
	private AreaTime rightTime;
	/**使用的位置*/
	private Location usedLocat;
	/**是否为第一个*/
	private boolean isFirst;
	/**是否为最后*/
	private boolean isLast;
	
	public LeftRightTime(AreaTime leftTime,AreaTime rightTime,Location usedLocat,boolean isFirst,boolean isLast){
		this.leftTime = leftTime;
		this.rightTime = rightTime;
		this.usedLocat = usedLocat;
		this.isFirst = isFirst;
		this.isLast = isLast;
	}
	
	
	
	public AreaTime getLeftTime() {
		return leftTime;
	}
	public void setLeftTime(AreaTime leftTime) {
		this.leftTime = leftTime;
	}
	public AreaTime getRightTime() {
		return rightTime;
	}
	public void setRightTime(AreaTime rightTime) {
		this.rightTime = rightTime;
	}

	public Location getUsedLocat() {
		return usedLocat;
	}

	public void setUsedLocat(Location usedLocat) {
		this.usedLocat = usedLocat;
	}

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	public boolean isLast() {
		return isLast;
	}

	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}
	
}
