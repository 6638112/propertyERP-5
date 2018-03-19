/**   
* Filename:    EncryptService.java   
* @version:    1.0  
* Create at:   2016年2月19日 上午11:08:09   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2016年2月19日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.encrypt.service;

import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.cnfantasia.server.commbusi.encrypt.constant.EncryptDict;
import com.cnfantasia.server.commbusi.encrypt.dao.IEncryptDao;
import com.cnfantasia.server.commbusi.encrypt.entity.EncryptUrlEntity;
import com.cnfantasia.server.commbusi.encrypt.util.EncryptUtil;
import com.cnfantasia.server.domainbase.encyptLog.dao.IEncyptLogBaseDao;
import com.cnfantasia.server.domainbase.encyptLog.entity.EncyptLog;

/**
 * Filename:    EncryptService.java
 * @version:    1.0.0
 * Create at:   2016年2月19日 上午11:08:09
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2016年2月19日       shiyl             1.0             1.0 Version
 */
public class EncryptService implements IEncryptService{
	
	private IEncryptDao encryptDao;
	public void setEncryptDao(IEncryptDao encryptDao) {
		this.encryptDao = encryptDao;
	}
	
	private IEncyptLogBaseDao encyptLogBaseDao;
	public void setEncyptLogBaseDao(IEncyptLogBaseDao encyptLogBaseDao) {
		this.encyptLogBaseDao = encyptLogBaseDao;
	}


	@Override
	public List<EncryptUrlEntity> getEncryptUrlAll() {
		return encryptDao.selectEncryptUrlAll();
	}
	
	
	@Override
	public EncryptUrlEntity getEncrypt(String url, Long version) {
		if(StringUtils.isEmpty(url)){
			return null;
		}
		List<EncryptUrlEntity> existUrlList = getEncryptUrlAll();
		if(existUrlList!=null&&existUrlList.size()>0){
			for(EncryptUrlEntity tmpEntity:existUrlList){
				//结合版本号获取对应的配置 url相同,且版本区间匹配
				if(tmpEntity.getUrl().equals(url)){
					if(version==null){
						if(tmpEntity.getVer()==0){
							return tmpEntity;
						}
					}else if(tmpEntity.getVer().compareTo(version)<=0&&tmpEntity.getVerEnd().compareTo(version)>=0){
						return tmpEntity;
					}
				}
			}
		}
		return null;
	}
	
	
	@Override
	public boolean validate(String url,Map<String,String> paramMap,Integer signType,String toValidSign) {
		boolean res = false;
		Map<String, String> result = EncryptUtil.paraFilter(paramMap);
		String srcStr = EncryptUtil.createLinkString(url, result);
		String sign = null;
		if(EncryptDict.EncryptUrl_SignType.MD5.compareTo(signType)==0){
			sign = EncryptUtil.md5Hex(srcStr);
		}
		if(toValidSign!=null&&sign!=null&&sign.equals(toValidSign)){
			res = true;
		}
		if(!res){//失败日志
			EncyptLog encyptLog = new EncyptLog();
			encyptLog.setErrInfo("failed");
			encyptLog.setSignParam(toValidSign);
			encyptLog.setSignServer(sign);
			encyptLog.setSignType(signType);
			encyptLog.setSrcStr(srcStr);
			encyptLogBaseDao.insertEncyptLog(encyptLog);
		}
		return res;
	}


	@Override
	public void freshCache() {
		encryptDao.cleanCache();
	}
	
	
}
