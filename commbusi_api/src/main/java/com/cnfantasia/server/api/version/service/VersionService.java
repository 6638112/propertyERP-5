/**   
* Filename:    VersionService.java   
* @version:    1.0  
* Create at:   2014年6月18日 上午7:46:18   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月18日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.version.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.cnfantasia.server.api.version.constant.VersionDict;
import com.cnfantasia.server.api.version.dao.IVersionDao;
import com.cnfantasia.server.api.version.entity.AppVersionChannelEntity;
import com.cnfantasia.server.api.version.entity.AppVersionEntity;

/**
 * Filename:    VersionService.java
 * @version:    1.0.0
 * Create at:   2014年6月18日 上午7:46:18
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月18日       shiyl             1.0             1.0 Version
 */
public class VersionService implements IVersionService{
	private IVersionDao versionDao;
	public void setVersionDao(IVersionDao versionDao) {
		this.versionDao = versionDao;
	}
	
	private List<Integer> getContainGateTypeList(BigInteger userId){
		List<Integer> containGateTypeList = new ArrayList<Integer>();
		containGateTypeList.add(VersionDict.AppVersion_GateType.ALL);//默认加载全部类别
		if(userId!=null){//查询用户是否为特使用户
			boolean resBool = versionDao.selectIsGateUserByUserId(userId);
			if(resBool){
				containGateTypeList.add(VersionDict.AppVersion_GateType.GateUser);
			}
		}
		return containGateTypeList;
	}
	
	@Override
	public AppVersionEntity getLastVersionInfo(BigInteger appId,BigInteger userId) {
		List<Integer> containGateTypeList = getContainGateTypeList(userId);
		return versionDao.selectLastVersionInfoWithGate(appId,containGateTypeList, null);
	}
	
	@Override
	public AppVersionEntity getLastVersionInfo(BigInteger appId,BigInteger userId, int isForceUpd) {
		List<Integer> containGateTypeList = getContainGateTypeList(userId);
		return versionDao.selectLastVersionInfoWithGate(appId,containGateTypeList, isForceUpd);
	}

	@Override
	public AppVersionChannelEntity getLastVersionInfo(BigInteger appId,
			BigInteger userId, String channelCode) {
		List<Integer> containGateTypeList = getContainGateTypeList(userId);
		return versionDao.selectLastVersionInfoWithGateByChannelCode(appId, containGateTypeList, channelCode);
	}
	
}
