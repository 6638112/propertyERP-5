package com.cnfantasia.server.commbusi.microblogQueue.cache;

public class PushState {
	public static final int maxHour = 99;
	public static final PushState stateStart = new PushState(0, 0, null);
	public static final PushState stateEnd = new PushState(maxHour, Long.MAX_VALUE, null);
	
	/**可推送时间*/
	private int hour;
	/**最大允许推送消息数*/
	private Long allowMaxCount;
	/**next处理状态*/
	private PushState nextState;
	
	@Override
	public String toString() {
		return hour+","+allowMaxCount+";"+nextState;
	}
	public PushState(int hour,Long allowMaxCount,PushState nextState){
		this.hour = hour;
		this.allowMaxCount = allowMaxCount;
		this.nextState = nextState;
	}
	public PushState(int hour,int allowMaxCount,PushState nextState){
		this.hour = hour;
		this.allowMaxCount = (long) allowMaxCount;
		this.nextState = nextState;
	}
	
	public void setNextState(PushState nextState) {
		this.nextState = nextState;
	}

	/**
	 * @param currHour 当前时间
	 * @param pushCount 已推送次数
	 * @return 返回是否处理
	 */
	public boolean handle(int currHour,Long pushCount){
		if(currHour<=hour){
			return pushCount.compareTo(allowMaxCount)<0?true:false;
		}
		return nextState==null?false:nextState.handle(currHour, pushCount);//为空则不处理
	}
	
	/**
	 * 获取当前允许推送的最大次数
	 * @param currHour
	 * @return
	 */
	public Long getCurrAllowMaxCount(int currHour){
		if(currHour<=hour){
//			return (allowMaxCount-1)<=0L?0L:(allowMaxCount-1);
			return allowMaxCount;
		}
		return nextState==null?0L:nextState.getCurrAllowMaxCount(currHour);
	}
	
	/**
	 * 清空关联关系
	 */
	public void clear(){
		PushState nextState = this.nextState;
		this.setNextState(null);
		if(nextState!=null){
			nextState.clear();
		}
	}
	
}
