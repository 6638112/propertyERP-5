/**   
* Filename:    RedpointDao.java   
* @version:    1.0  
* Create at:   2015年3月25日 上午8:07:05   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月25日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redpoint.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.api.redpoint.constant.RedpointConstant;
import com.cnfantasia.server.api.redpoint.entity.RedpointContentEntity;
import com.cnfantasia.server.api.redpoint.entity.RedpointModelcodeConfigEntity;
import com.cnfantasia.server.domainbase.redpointContent.entity.RedpointContent;

/**
 * Filename:    RedpointDao.java
 * @version:    1.0.0
 * Create at:   2015年3月25日 上午8:07:05
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月25日       shiyl             1.0             1.0 Version
 */
public class RedpointDao extends AbstractBaseDao implements IRedpointDao{
	
	private List<String> getRedpointModelcodeWithSubList(String modelCode){
		List<String> resList = new ArrayList<String>();
		List<RedpointModelcodeConfigEntity> tmpList = selectRedpointModelcodeWithSubList(modelCode);
		if(tmpList!=null&&tmpList.size()>0){
			for(RedpointModelcodeConfigEntity tmpEntity:tmpList){
				resList.add(tmpEntity.getCode());
			}
		}else{//使用本身
			resList.add(modelCode);
		}
		return resList;
	}
	
	@Override
	public List<RedpointModelcodeConfigEntity> selectRedpointModelcodeWithSubList(String modelCode) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("modelCode", modelCode);
		return sqlSession.selectList("redpoint.select_RedpointModelcode_WithSub_List",tmpMap);
	}
	
	@Override
	public RedpointContentEntity selectRedpointContentByModelCode(BigInteger userId, Integer userType,BigInteger roomId, String modelCode) {
		List<String> modelCodeList = getRedpointModelcodeWithSubList(modelCode);
		if(modelCodeList==null||modelCodeList.size()<=0){return null;}
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("userType", userType);
		tmpMap.put("roomId", roomId);
		tmpMap.put("modelCodeList", modelCodeList);
		return sqlSession.selectOne("redpoint.select_RedpointContent_By_ModelCode",tmpMap);
	}

	@Override
	public List<RedpointContentEntity> selectRedpointContentByModelCodeList(BigInteger userId, Integer userType,BigInteger roomId,
			List<String> modelCodeList) {
		if(modelCodeList==null||modelCodeList.size()<=0){return null;}
		List<RedpointContentEntity> resList = new ArrayList<RedpointContentEntity>();
		for(String tmpCode:modelCodeList){
			RedpointContentEntity tmpEntity = null;
			if (RedpointConstant.RedpointContent_ModelCode.USER_HAS_NEW_COUPON.equals(tmpCode)) {
				tmpEntity = selectRedpointContentByModelCode(userId, userType, null, tmpCode);
			} else {
				tmpEntity = selectRedpointContentByModelCode(userId, userType, roomId, tmpCode);
			}
			if (tmpEntity != null) {
				resList.add(tmpEntity);
			}
		}
		return resList;
	}

	@Override
	public Integer updateRedpointContentClicked(BigInteger userId, Integer userType,BigInteger roomId, String modelCode) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("userType", userType);
		tmpMap.put("modelCode", modelCode);
		tmpMap.put("roomId", roomId);
		return sqlSession.update("redpoint.update_RedpointContent_Clicked", tmpMap);
	}

	@Override
	public Integer freshRedpointContentClickStatus(BigInteger userId, Integer userType,BigInteger roomId, String modelCode) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("userType", userType);
		tmpMap.put("modelCode", modelCode);
		tmpMap.put("roomId", roomId);
		return sqlSession.update("redpoint.fresh_RedpointContent_ClickStatus", tmpMap);
	}
	
	@Override
	public Integer freshRedpointContentClickStatus2(RedpointContent rc) {
		return sqlSession.update("redpoint.fresh_RedpointContent_ClickStatus2", rc);
	}	
}
