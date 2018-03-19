/**   
* Filename:    PrizeRecordTmpDataDao.java   
* @version:    1.0  
* Create at:   2015年6月8日 上午11:24:02   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年6月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.domainbase.prizeRecordTmpData.entity.PrizeRecordTmpData;

/**
 * Filename:    PrizeRecordTmpDataDao.java
 * @version:    1.0.0
 * Create at:   2015年6月8日 上午11:24:02
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年6月8日       shiyl             1.0             1.0 Version
 */
public class PrizeRecordTmpDataDao extends AbstractBaseDao implements IPrizeRecordTmpDataDao{
	private ISysParamManager sysParamManager;
	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}
	
	@Override
	public Integer selectPrizeRecordTmpData2DealCount(BigInteger userId, Integer userType, BigInteger roomId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("userType", userType);
		tmpMap.put("roomId", roomId);
		return sqlSession.selectOne("prizeRecordTmpData.select_PrizeRecordTmpData_2Deal_Count", tmpMap);
	}
	
	@Override
	public Integer updatePrizeRecordTmpDataAsFailed(BigInteger userId,Integer userType,Integer prizeNum,BigInteger roomId,String prizeTime) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("userType", userType);
		tmpMap.put("prizeNum", prizeNum);
		tmpMap.put("roomId", roomId);
//		tmpMap.put("prizeTime", prizeTime);
		return sqlSession.update("prizeRecordTmpData.update_PrizeRecordTmpData_As_Failed", tmpMap);
	}

	@Override
	public List<PrizeRecordTmpData> selectTmpPrizeDataListNotFailedByUserInfo(BigInteger userId, Integer userType,Boolean isTimeOut) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("userType", userType);
		tmpMap.put("isTimeOut", isTimeOut);
		tmpMap.put("timeOutSecond", getTimeOutSecond());
		return sqlSession.selectList("prizeRecordTmpData.select_TmpPrizeData_List_NotFailed_ByUserInfo", tmpMap);
	}
	
	private Integer getTimeOutSecond(){
		String value = sysParamManager.getSysParaValue(SysParamKey.PrizeTmpData_TimeOut);
		return Integer.valueOf(value);
	}

	@Override
	public Integer updatePrizeRecordTmpDataAsSuccess(BigInteger userId,Integer userType,Integer prizeNum,BigInteger roomId,String prizeTime) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("userType", userType);
		tmpMap.put("prizeNum", prizeNum);
		tmpMap.put("roomId", roomId);
//		tmpMap.put("prizeTime", prizeTime);
		return sqlSession.update("prizeRecordTmpData.update_PrizeRecordTmpData_As_Success", tmpMap);
	}

	@Override
	public Integer updatePrizeRecordTmpDataAsFinished(BigInteger recordTmpDataId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
//		tmpMap.put("userId", userId);
//		tmpMap.put("userType", userType);
		tmpMap.put("recordTmpDataId", recordTmpDataId);
		return sqlSession.update("prizeRecordTmpData.update_PrizeRecordTmpData_As_Finished", tmpMap);
	}

	@Override
	public List<PrizeRecordTmpData> selectTmpPrizeDataListNotFailedAndTimeOut() {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("timeOutSecond", getTimeOutSecond());
		return sqlSession.selectList("prizeRecordTmpData.select_TmpPrizeDataList_NotFailed_AndTimeOut", tmpMap);
	}

	@Override
	public PrizeRecordTmpData selectPrizeRecordTmpDataDetail(BigInteger recordTmpDataId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("recordTmpDataId", recordTmpDataId);
		return sqlSession.selectOne("prizeRecordTmpData.select_PrizeRecordTmpData_Detail", tmpMap);
	}

}
