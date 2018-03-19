package com.cnfantasia.server.business.pub.springSecurity;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

/**
 * 描述: 在初始化时，从数据库提取权限和资源，装配到HashMap中。
 * 
 * 开放loadResourceDefine()方法，供增加角色、分配角色权限程序调用
 */

@Service
public abstract class AbstractInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	/** 日志对象 */
	private static Log logger = LogFactory.getLog(AbstractInvocationSecurityMetadataSource.class);

	/**
	 * 取得资源对应的权限
	 */
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// 获取请求url
		String url = ((FilterInvocation) object).getRequestUrl();
		Iterator<String> ite = getResourcesMap().keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			// 资源比对
			if (url.indexOf(resURL) >= 0) {
				// 取得资源对应的权限
				return getResourcesMap().get(resURL);
			}
		}
		//logger.info("The request url's permi info is undefined:" + url);

		// if(url.trim().startsWith("/")){url=url.trim().substring(1);}
		// if(url.indexOf("?")>0){url=url.substring(0, url.indexOf("?"));}
		//
		// HashMap<String,Object> qryMap=new HashMap<String, Object>();
		// qryMap.put("funcAction",url);
		// int count=managerFunctionService.getManagerFunctionCount(qryMap);
		// if(count<=0){
		// //存入功能表
		// HashMap<String,Object> tmpFuncInfo=new HashMap<String, Object>();
		// String
		// uuid=ControllerUtils.getPrimaryKey(SEQConstants.FOC_MANAGER_FUNCTION);
		// tmpFuncInfo.put("funcId", "tmp_"+uuid);
		// tmpFuncInfo.put("funcName", "未使用");
		// tmpFuncInfo.put("status", EbhConstants.Status.STATUS_IN_USE);
		// tmpFuncInfo.put("funcAction",url);
		// tmpFuncInfo.put("menuflag",EbhConstants.Menu.MENU_NO);
		// managerFunctionService.insertManagerFunction(tmpFuncInfo);
		// throw new AccessDeniedException("--权限未定义"+url);
		// }
		return null;

		// throw new AccessDeniedException("--权限未定义"+url);

	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public abstract Map<String, Collection<ConfigAttribute>> getResourcesMap();
}