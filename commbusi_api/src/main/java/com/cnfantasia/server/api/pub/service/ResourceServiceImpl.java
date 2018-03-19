/**   
* Filename:    ResourceServiceImpl.java   
* @version:    1.0  
* Create at:   2014年5月19日 上午10:18:38   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月19日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.pub.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Service;

import com.cnfantasia.server.business.pub.service.IResourceService;

/**
 * Filename:    ResourceServiceImpl.java
 * @version:    1.0.0
 * Create at:   2014年5月19日 上午10:18:38
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月19日       shiyl             1.0             1.0 Version
 */
@Service
public class ResourceServiceImpl implements IResourceService{
	/**日志对象*/
  private Log logger = LogFactory.getLog(ResourceServiceImpl.class);

	@Override
	public Map<String, Collection<ConfigAttribute>> getAllSourceAndRole() {
		//List<PermiFunctionEntity> functionList = permiDao.selectFuncList(PermiDict.PermiFunction_Status.IN_USE);
		// 资源及其对应权限集合
		final Map<String, Collection<ConfigAttribute>> srcMap = new HashMap<String, Collection<ConfigAttribute>>();
/*		for (PermiFunctionEntity tmpPermi : functionList) {
			String funcAction = tmpPermi.getAction();
			List<PermiRole> roleList = tmpPermi.getPermiRoleList();
			for (PermiRole tmpRole : roleList) {
				BigInteger roleId = tmpRole.getId();
				String roleName = tmpRole.getName();
				logger.info("funcAction = " + funcAction + ",roleId=" + roleId + ", roleName=" + roleName);
				Collection<ConfigAttribute> atts = null;
				// 判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中。
				if (srcMap.get(funcAction) == null) {
					atts = new ArrayList<ConfigAttribute>();
					ConfigAttribute ca = new SecurityConfig(roleId.toString());
					atts.add(ca);
					srcMap.put(funcAction, atts);
				} else {
					atts = srcMap.get(funcAction);
					ConfigAttribute ca = new SecurityConfig(roleId.toString());
					atts.add(ca);
					srcMap.put(funcAction, atts);
				}
			}
		}*/
		return srcMap;
	}
	
}
