package com.cnfantasia.server.api.cache.node;

public final class CacheConstants {

	/** 消息channel */
	public static final String Cache_Channel = "refresh_cache";
	/** 刷缓存间隔时间限制 ：60分钟*/
	public static final long Refresh_Time_Limit = 1000 * 60 * 60;

	/**
	 * 消息类型
	 */
	public static final class MsgType {
		public static final int Refresh_All = 1;
		public static final int Refresh_SysParam = 2;
		public static final int Refresh_Mybatis_Cache = 3;
		public static final int Refresh_All_HtCarGb_Cache = 4;
	}
	
	public static final class User{
		public static final String username = "jfq";
		public static final String password = "jfq";
	}
}
