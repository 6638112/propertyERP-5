/**   
* Filename:    VersionDao.java   
* @version:    1.0  
* Create at:   2014年6月18日 上午7:46:56   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月18日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.version.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.api.version.entity.AppVersionChannelEntity;
import com.cnfantasia.server.api.version.entity.AppVersionEntity;


/**
 * Filename:    VersionDao.java
 * @version:    1.0.0
 * Create at:   2014年6月18日 上午7:46:56
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月18日       shiyl             1.0             1.0 Version
 */
public class VersionDao extends AbstractBaseDao implements IVersionDao{

	@Override
	public AppVersionEntity selectLastVersionInfo(BigInteger appId) {
		Map<String,Object> tmpMap = new HashMap<String,Object>();
		tmpMap.put("appId", appId);
		return sqlSession.selectOne("version.select_VersionEntity_LastInfo",tmpMap);
	}
	
	@Override
	public boolean selectIsGateUserByUserId(BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String,Object>();
		tmpMap.put("userId", userId);
		Integer resCount = 0;
		resCount = sqlSession.selectOne("version.select_IsGateUser_ByUserId",tmpMap);
		return resCount>0?true:false;
	}
	
	@Override
	public AppVersionEntity selectLastVersionInfoWithGate(BigInteger appId, List<Integer> containGateTypeList, Integer isForceUpd) {
		Map<String,Object> tmpMap = new HashMap<String,Object>();
		tmpMap.put("appId", appId);
		tmpMap.put("containGateTypeList", containGateTypeList);
		tmpMap.put("isForceUpd", isForceUpd);
		return sqlSession.selectOne("version.select_LastVersionInfo_WithGate",tmpMap);
	}
	
	@Override
	public AppVersionChannelEntity selectLastVersionInfoWithGateByChannelCode(BigInteger appId, List<Integer> containGateTypeList,String channelCode) {
		Map<String,Object> tmpMap = new HashMap<String,Object>();
		tmpMap.put("appId", appId);
		tmpMap.put("containGateTypeList", containGateTypeList);
		tmpMap.put("channelCode", channelCode);
		return sqlSession.selectOne("version.select_LastVersionInfo_WithGate_byChannelCode",tmpMap);
	}

	
}
