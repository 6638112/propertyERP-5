/**   
* Filename:    RequestParseUtil.java   
* @version:    1.0  
* Create at:   2014年6月11日 上午10:07:58   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月11日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.util;

import javax.servlet.http.HttpServletRequest;

import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename:    RequestParseUtil.java
 * @version:    1.0.0
 * Create at:   2014年6月11日 上午10:07:58
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月11日       shiyl             1.0             1.0 Version
 */
public class RequestParseUtil {
	
	public static String getRealUrl(HttpServletRequest request){
		String srcUri = request.getRequestURI();
		if (StringUtils.isStrEmpty(srcUri)) { // 地址为空，不记录
      return null;
    }
		String contextPath = request.getContextPath();
    if(contextPath!=null&&contextPath.startsWith("/")){
      contextPath=contextPath.substring(1);
    }
    if (srcUri.indexOf(contextPath) != -1) {
      srcUri = srcUri.substring(contextPath.length() + 1);//=2去掉‘/’线，=1保留‘/’线
    }
    return srcUri;
	}
	
	
	public static String parseUrlFromRequest(HttpServletRequest request) {
		String srcUri = request.getRequestURI();
		String contextPath = request.getContextPath();
    if(contextPath!=null&&contextPath.startsWith("/")){
      contextPath=contextPath.substring(1);
    }
    if (srcUri.indexOf(contextPath) != -1) {
      srcUri = srcUri.substring(contextPath.length() + 1);//=2去掉‘/’线
    }
    {//增加分号过滤筛选
    	int tmpSplit = srcUri.indexOf(";");
      if(tmpSplit!=-1){
      	srcUri = srcUri.substring(0, tmpSplit);
      }
    }
    return srcUri;
	}
	
}
