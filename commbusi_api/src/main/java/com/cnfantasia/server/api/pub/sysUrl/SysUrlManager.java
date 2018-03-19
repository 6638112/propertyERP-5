/**   
* Filename:    SysUrlManager.java   
* @version:    1.0  
* Create at:   2014年8月5日 上午2:48:20   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月5日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.pub.sysUrl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cnfantasia.server.api.commonBusiness.util.RequestParseUtil;
import com.cnfantasia.server.business.pub.sysUrl.ISysUrlManager;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.commSysUrl.entity.CommSysUrl;
import com.cnfantasia.server.domainbase.commSysUrl.service.ICommSysUrlBaseService;

/**
 * Filename:    SysUrlManager.java
 * @version:    1.0.0
 * Create at:   2014年8月5日 上午2:48:20
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月5日       shiyl             1.0             1.0 Version
 */
public class SysUrlManager implements ISysUrlManager<CommSysUrl>{
	private static Map<String,CommSysUrl> commSysUrlMap;//url信息Map
	
	private ICommSysUrlBaseService commSysUrlBaseService;
	public void setCommSysUrlBaseService(ICommSysUrlBaseService commSysUrlBaseService) {
		this.commSysUrlBaseService = commSysUrlBaseService;
	}
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	@Override
	public void init() {
		List<CommSysUrl> resList = commSysUrlBaseService.getCommSysUrlByCondition(null);
		commSysUrlMap = new HashMap<String, CommSysUrl>();
		if(resList!=null&&resList.size()>0){
			for(CommSysUrl tmoUrl:resList){
				commSysUrlMap.put(tmoUrl.getActionUrl(), tmoUrl);
			}
		}
	}

	@Override
	public CommSysUrl getUrlDetail(String url) {
		return commSysUrlMap.get(url);
	}

	@Override
	public void updUrlCatche() {
		init();
	}
	
	@Override
	public String parseUrlFromRequest(HttpServletRequest request) {
		return RequestParseUtil.parseUrlFromRequest(request);
//		String srcUri = request.getRequestURI();
//		String contextPath = request.getContextPath();
//    if(contextPath!=null&&contextPath.startsWith("/")){
//      contextPath=contextPath.substring(1);
//    }
//    if (srcUri.indexOf(contextPath) != -1) {
//      srcUri = srcUri.substring(contextPath.length() + 1);//=2去掉‘/’线
//    }
//    {//增加分号过滤筛选
//    	int tmpSplit = srcUri.indexOf(";");
//      if(tmpSplit!=-1){
//      	srcUri = srcUri.substring(0, tmpSplit);
//      }
//    }
//    return srcUri;
	}

	@Override
	public CommSysUrl addUrl(String name, String url, String desc) {
		BigInteger addId = uuidManager.getNextUuidBigInteger(SEQConstants.t_comm_sys_url);
		CommSysUrl commSysUrlAdd = new CommSysUrl();
		commSysUrlAdd.setActionUrl(url);
		commSysUrlAdd.setDesc(desc);
		commSysUrlAdd.setId(addId);
		commSysUrlAdd.setName(name);
		commSysUrlBaseService.insertCommSysUrl(commSysUrlAdd);
		updUrlCatche();
		return commSysUrlAdd;
	}
	
}
