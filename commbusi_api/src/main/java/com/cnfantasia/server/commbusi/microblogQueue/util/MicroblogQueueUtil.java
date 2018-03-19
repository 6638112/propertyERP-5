package com.cnfantasia.server.commbusi.microblogQueue.util;

import com.cnfantasia.server.commbusi.microblogQueue.constant.MicroblogQueueConstant;
import com.cnfantasia.server.domainbase.microblogQueue.entity.MicroblogQueue;

public class MicroblogQueueUtil {
	
	public static boolean isOnlyForGB(MicroblogQueue mq){
		if(mq!=null&&mq.getGroupBuildingId()!=null&&mq.getGroupBuildingId().compareTo(MicroblogQueueConstant.DEFAULT_NULL_GroupBuild)!=0){
			return true;
		}
		return false;
	}
	
}
