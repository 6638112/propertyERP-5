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
package com.cnfantasia.server.ms.pub.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Service;

import com.cnfantasia.server.ms.permi.dao.IPermiDao;
import com.cnfantasia.server.ms.permi.entity.PermiFunctionEntity;
import com.cnfantasia.server.ms.pub.constant.DictConstants;
import com.cnfantasia.server.domainbase.omsPermiRole.entity.OmsPermiRole;

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
  
	private IPermiDao permiDao;
	public void setPermiDao(IPermiDao permiDao) {
		this.permiDao = permiDao;
	}

	@Override
	public Map<String, Collection<ConfigAttribute>> getAllSourceAndRole() {
		List<PermiFunctionEntity> functionList = permiDao.selectFuncList(DictConstants.PermiFunction_Status.IN_USE);
		//资源及其对应权限集合
    final Map<String, Collection<ConfigAttribute>> srcMap  = new HashMap<String, Collection<ConfigAttribute>>();
		for(PermiFunctionEntity tmpPermi:functionList){
	    String funcAction = tmpPermi.getAction();
	    List<OmsPermiRole> roleList = tmpPermi.getPermiRoleList();
	    for(OmsPermiRole tmpRole:roleList){
	    	BigInteger roleId = tmpRole.getId();
		    String roleName = tmpRole.getName();
		    logger.info("funcAction = " + funcAction + ",roleId=" + roleId + ", roleName=" + roleName);
		    Collection<ConfigAttribute> atts = null;
		    //判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中。
		    if (srcMap.get(funcAction) == null){
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
		}
		return srcMap;
	}
	
}
