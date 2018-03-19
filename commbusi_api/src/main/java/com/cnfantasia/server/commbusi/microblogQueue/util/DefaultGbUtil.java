package com.cnfantasia.server.commbusi.microblogQueue.util;

import java.math.BigInteger;

import com.cnfantasia.server.api.room.constant.RoomConstants;

public class DefaultGbUtil {
	
	public static boolean checkIsDefaultGb(BigInteger gbId){
		if(gbId==null||gbId.compareTo(RoomConstants.DEFAULT_GROUP_BUILDING_ID)==0){
			return true;
		}
		return false;
	}
	
}
