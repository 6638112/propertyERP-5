/**   
* Filename:    CommDataUpgradeDao.java   
* @version:    1.0  
* Create at:   2015年4月22日 上午6:23:22   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月22日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.loginNo.entity.LoginNo;
import com.cnfantasia.server.domainbase.loginNoOldUpgradeDate.entity.LoginNoOldUpgradeDate;

/**
 * Filename:    CommDataUpgradeDao.java
 * @version:    1.0.0
 * Create at:   2015年4月22日 上午6:23:22
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月22日       shiyl             1.0             1.0 Version
 */
public class CommDataUpgradeDao extends AbstractBaseDao implements ICommDataUpgradeDao{
	
	@Override
	public LoginNo selectExistLoginNoForWeiXin(Long accType, String accountNo, String unionId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("accType", accType);
		tmpMap.put("accountNo", accountNo);
		tmpMap.put("unionId", unionId);
		return sqlSession.selectOne("commDataUpgrade.select_ExistLoginNo_For_WeiXin",tmpMap);
	}

//	@Override
//	public Integer updateWeiXinUnionIdIfNotExist(Long accType, String accountNo, String unionId) {
//		Map<String,Object> tmpMap = new HashMap<String, Object>();
//		tmpMap.put("accType", accType);
//		tmpMap.put("accountNo", accountNo);
//		tmpMap.put("unionId", unionId);
//		return sqlSession.update("commDataUpgrade.update_WeiXinUnionId_IfNotExist",tmpMap);
//	}
	
	@Override
	public LoginNo selectDefaultLoginNoByWeiXinUnionId(String unionId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("unionId", unionId);
		return sqlSession.selectOne("commDataUpgrade.select_DefaultLoginNo_By_WeiXinUnionId",tmpMap);
	}

	@Override
	public List<LoginNo> selectLoginNoListByWeiXinUnionId(String unionId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("unionId", unionId);
		return sqlSession.selectList("commDataUpgrade.select_LoginNoList_By_WeiXinUnionId",tmpMap);
	}

//	@Override
//	public Integer updateSurpriseGiftFromSrc2Goal(BigInteger srcUserId, BigInteger goalUserId) {
//		Map<String,Object> tmpMap = new HashMap<String, Object>();
//		tmpMap.put("srcUserId", srcUserId);
//		tmpMap.put("goalUserId", goalUserId);
//		return sqlSession.update("commDataUpgrade.update_SurpriseGift_From_Src2Goal", tmpMap);
//	}

	@Override
	public Integer updateOldSurpriseGift2NewRoomId(BigInteger oldUserId, BigInteger oldRoomId,BigInteger newUserId, BigInteger newRoomId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("oldUserId", oldUserId);
		tmpMap.put("oldRoomId", oldRoomId);
		tmpMap.put("newUserId", newUserId);
		tmpMap.put("newRoomId", newRoomId);
		return sqlSession.update("commDataUpgrade.update_Old_SurpriseGift_2NewRoomId", tmpMap);
	}
	
	@Override
	public int updatePrizeRecFromOne2Another(BigInteger oldUserId, BigInteger oldRoomId,BigInteger newUserId, BigInteger newRoomId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("oldUserId", oldUserId);
		tmpMap.put("oldRoomId", oldRoomId);
		tmpMap.put("newUserId", newUserId);
		tmpMap.put("newRoomId", newRoomId);
		return sqlSession.update("commDataUpgrade.update_prizeRec_fromOne2Another", tmpMap);
	}
	
	@Override
	public Integer updateShenMaHbFromOne2Another(BigInteger oldUserId, BigInteger oldRoomId,BigInteger newUserId, BigInteger newRoomId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("oldUserId", oldUserId);
		tmpMap.put("oldRoomId", oldRoomId);
		tmpMap.put("newUserId", newUserId);
		tmpMap.put("newRoomId", newRoomId);
		return sqlSession.update("commDataUpgrade.update_ShenMaHb_FromOne2Another", tmpMap);
	}
	
	@Override
	public List<LoginNoOldUpgradeDate> selectLoginNoOldUpgradeDateByDevice(Long subChannelId, String deviceId,
			Long accType) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("subChannelId", subChannelId);
		map.put("deviceId", deviceId);
//		map.put("accType", accType);
		return sqlSession.selectList("commDataUpgrade.select_login_no_old_upgrade_date_by_device", map);
	}
	
	@Override
	public List<LoginNo> selectOldWeiXinByDeviceInfo(BigInteger oldWeixinDataId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("oldWeixinDataId", oldWeixinDataId);
		return sqlSession.selectList("commDataUpgrade.select_OldWeiXin_ByDeviceInfo", map);
	}

	@Override
	public Integer updateAndCheckRealRoomValidateInfo(BigInteger srcAdminUserId, BigInteger goalAdminUserId, BigInteger roomId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("srcAdminUserId", srcAdminUserId);
		map.put("goalAdminUserId", goalAdminUserId);
		map.put("roomId", roomId);
		return sqlSession.update("commDataUpgrade.update_AndCheck_RealRoom_ValidateInfo", map);
	}

	@Override
	public LoginNo selectLoginNoByAccountNoAndType(String accountNo, Long accType) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("accountNo", accountNo);
		map.put("accType", accType);
		return sqlSession.selectOne("commDataUpgrade.select_LoginNo_ByAccountNoAndType", map);
	}


}
