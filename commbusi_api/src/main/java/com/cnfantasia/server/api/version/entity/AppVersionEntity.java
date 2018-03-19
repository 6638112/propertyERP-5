/**   
* Filename:    AppVersionEntity.java   
* @version:    1.0  
* Create at:   2014年6月18日 上午7:55:49   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月18日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.version.entity;

import java.math.BigInteger;

import com.cnfantasia.server.api.version.constant.VersionDict;
import com.cnfantasia.server.domainbase.appBaseInfo.entity.AppBaseInfo;
import com.cnfantasia.server.domainbase.appVersion.entity.AppVersion;

/**
 * Filename:    AppVersionEntity.java
 * @version:    1.0.0
 * Create at:   2014年6月18日 上午7:55:49
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月18日       shiyl             1.0             1.0 Version
 */
public class AppVersionEntity extends AppVersion{
	private static final long serialVersionUID = 1L;
	public static final String DEFAULT_START_VERSION="1.0.0";
	/**
	 * 应用基本信息
	 */
	private AppBaseInfo appBaseInfo;
	public AppBaseInfo getAppBaseInfo() {
		return appBaseInfo;
	}
	public void setAppBaseInfo(AppBaseInfo appBaseInfo) {
		this.appBaseInfo = appBaseInfo;
	}
	@Override
	public BigInteger gettAppBaseInfoFId() {
		if(appBaseInfo==null){return null;}
		return appBaseInfo.getId();
	}
	@Override
	public void settAppBaseInfoFId(BigInteger tAppBaseInfoFId) {
		if(appBaseInfo==null){appBaseInfo = new AppBaseInfo();}
		appBaseInfo.setId(tAppBaseInfoFId);
	}
	
	/**
	 * 获取下载地址
	 * @return
	 */
	public String fetchDownloadUrl(){
//		return getVersionDwonUrl();
		return appBaseInfo.getDownUrl();//使用这个地址，目的是便于记录用户的下载日志
	}
	
	/**
	 * 是否需要强制更新
	 * @return
	 */
	public boolean fetchIsForceUpdate(){
		if(VersionDict.AppVersion_ForceUpdate.TRUE.compareTo(getForceUpdate())==0){
			return true;
		}else{
			return false;
		}
	}
	
	
}
