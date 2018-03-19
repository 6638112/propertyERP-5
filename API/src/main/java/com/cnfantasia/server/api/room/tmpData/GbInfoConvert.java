package com.cnfantasia.server.api.room.tmpData;

import java.math.BigInteger;
import java.util.Map;

import com.cnfantasia.server.api.commonBusiness.entity.SimpleResultMap;
import com.cnfantasia.server.api.room.util.RoomEntityConvertUtil;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;

public class GbInfoConvert {
	private static GbInfoConvert signal = null;
	private GbInfoConvert(){}
	public static GbInfoConvert getSignalInstance(){
		if(signal==null){
			synchronized (GbInfoConvert.class) {
				if(signal==null){
					signal = new GbInfoConvert();
				}
			}
		}
		return signal;
	}
	public Map<String, Object> stack01(RealRoom realRoom,BigInteger realRoomId,String realRoomNum){
		Map<String, Object> tmpMap = new SimpleResultMap<String, Object>();
		tmpMap.put("id", realRoom.getId());
		String nameShow = RoomEntityConvertUtil.getRealRoomShowName(realRoom);
		tmpMap.put("name", nameShow);
		tmpMap.put("num", nameShow);//保留，为了兼容以前，其实取值跟name相同
		if(realRoomId!=null&&realRoom.getId().compareTo(realRoomId)==0){
			tmpMap.put("select", true);
		}
		if(realRoomNum!=null&&realRoomNum.trim().equals(realRoom.getNum())){
			tmpMap.put("select", true);
		}
		return tmpMap;
	}
	
	
}
