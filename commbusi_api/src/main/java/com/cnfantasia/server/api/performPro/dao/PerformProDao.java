package com.cnfantasia.server.api.performPro.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import com.cnfantasia.server.api.performPro.entity.GlobalEntity;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.business.pub.utils.DateUtil;

/**
 * 
* Filename:    PerformProDao.java
* @version:    1.0.0
* Create at:   2015年6月23日 上午3:04:30
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年6月23日       shiyl             1.0             1.0 Version
 */
public class PerformProDao extends AbstractBaseDao implements IPerformProDao{

	@Override
	public boolean selectIsNewUser(String deviceId,Long deviceType) {
		boolean res = true;
		if(deviceId!=null&&deviceType!=null){
			Map<String,Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("deviceId", deviceId);
			tmpMap.put("deviceType", deviceType);
			Integer resCount = sqlSession.selectOne("performPro.select_Is_NewUser", tmpMap);
			res = (resCount!=null&&resCount>0)?false:true;//用户已经注册过则为老用户
		}
		return res;
	}

	@Override
	public GlobalEntity selectGlobalDataAll(BigInteger userId, String deviceId, Long deviceType, Long lastBizTypeUpdTime,
			Long version) {
		Boolean g_IsFamalyMember;//是否有其他家庭成员	
		Boolean g_IsMultDevice;//是否有多个设备	
		Boolean g_IsBizType;//商家类别是否有更新
		Long g_bizTypeUpdTime;//商家类别更新时间
		
		Boolean g_IsVersion;//是否有新版本
		Boolean g_IsForceUpd;//客户端是否需要强制更新	
		String g_DownloadAdress;//新版本下载地址
		String g_VersionDesc;//版本描述
		
		Boolean g_IsNewUser;//是否为新注册用户
		Integer g_DiscountNum;//剩余抽奖次数
		{//用户+门牌 是否有其他家庭成员
			if(userId==null){
				g_IsFamalyMember = false;
			}else{
				Map<String,Object> tmpMap = new HashMap<String, Object>();
				tmpMap.put("userId", userId);
				Integer otherCount = sqlSession.selectOne("performPro.select_Is_FamalyMember", tmpMap);
				if(otherCount!=null&&otherCount>0){
					g_IsFamalyMember = true;
				}else{
					g_IsFamalyMember = false;
				}
			}
			
		}
		{//用户 是否有多个设备
			if(userId==null){
				g_IsMultDevice = false;
			}else{
				Map<String,Object> tmpMap = new HashMap<String, Object>();
				tmpMap.put("userId", userId);
				tmpMap.put("deviceId", deviceId);
				tmpMap.put("deviceType", deviceType);
				Integer otherCount = sqlSession.selectOne("performPro.select_Is_MultDevice", tmpMap);
				if(otherCount!=null&&otherCount>0){
					g_IsMultDevice = true;
				}else{
					g_IsMultDevice = false;
				}
			}
		}
		{//最近更新时间戳 商家类别是否有更新
			String lastUpdTime = sqlSession.selectOne("performPro.select_Is_BizType");
			Long lastUpdTimeLong = DateUtil.timeToLong(lastUpdTime, DateUtil.formatSecond.get());
			if(lastUpdTimeLong!=null&&lastBizTypeUpdTime!=null&&lastUpdTimeLong.compareTo(lastBizTypeUpdTime)==0){
				//若客户端上送的更新时间与服务器获得的时间相同则直接返回更新时间
				g_IsBizType = false;
				g_bizTypeUpdTime = null;
			}else{
				g_IsBizType = true;
				g_bizTypeUpdTime = lastUpdTimeLong;
			}
		}
		{//ok--当前版本号 是否有新版本 另处处理
			g_IsVersion = null;//是否有新版本	
			g_IsForceUpd = null;//客户端是否需要强制更新	
			g_DownloadAdress = null;//新版本下载地址
			g_VersionDesc = null;//版本描述
		}
		{//设备 是否为新注册用户	
			if(userId==null){//未登录用户则默认为新用户
				g_IsNewUser = true;
			}else{
				g_IsNewUser = selectIsNewUser(deviceId, deviceType);
			}
		}
		{//ok-- TODO 用户+门牌
			g_DiscountNum = null;//剩余抽奖次数
		}
		//返回结果
		GlobalEntity resGlobalEntity = new GlobalEntity(g_IsFamalyMember, g_IsMultDevice, g_IsBizType,g_bizTypeUpdTime, g_DiscountNum, g_IsVersion, g_IsForceUpd, g_DownloadAdress,g_VersionDesc, g_IsNewUser);
		return resGlobalEntity;
	}
	
}
