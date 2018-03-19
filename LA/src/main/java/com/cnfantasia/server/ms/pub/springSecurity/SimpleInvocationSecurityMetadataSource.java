package com.cnfantasia.server.ms.pub.springSecurity;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import com.cnfantasia.server.ms.pub.service.IResourceService;

/**
 * 描述: 在初始化时，从数据库提取权限和资源，装配到HashMap中。
 * 
 * 开放loadResourceDefine()方法，供增加角色、分配角色权限程序调用
 */

@Service
public class SimpleInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
  
  /**日志对象*/
  private static Log logger = LogFactory.getLog(SimpleInvocationSecurityMetadataSource.class);

  private static Map<String, Collection<ConfigAttribute>> resourcesMap = null;

  private static IResourceService resourceService; 

  public SimpleInvocationSecurityMetadataSource() {
  }
  /**
   * 注入数据操作对象
   * @param resourcesDao
   */
  public SimpleInvocationSecurityMetadataSource(IResourceService resourceService) {
    SimpleInvocationSecurityMetadataSource.resourceService = resourceService;
    loadResourceDefine();
  }

  /**
   * 读取功能资源与权限的对应关系
   */
  public static void loadResourceDefine() {
    resourcesMap = resourceService.getAllSourceAndRole();
    logger.info("----Security：Read DB RoleResources ok.");
  }

  /**
   * 取得资源对应的权限
   */
  public Collection<ConfigAttribute> getAttributes(Object object)
  throws IllegalArgumentException {
    //获取请求url
    String url = ((FilterInvocation)object).getRequestUrl();
    Iterator<String> ite = resourcesMap.keySet().iterator();
    while (ite.hasNext()) {
      String resURL = ite.next();
      //资源比对
      if (url.indexOf(resURL) >= 0) {
        //取得资源对应的权限
        return resourcesMap.get(resURL);
      }
    }
    logger.error("SimpleInvocationSecurityMetadataSource Undefined permi:"+url);
    
//    if(url.trim().startsWith("/")){url=url.trim().substring(1);}
//    if(url.indexOf("?")>0){url=url.substring(0, url.indexOf("?"));} 
//    
//    HashMap<String,Object> qryMap=new HashMap<String, Object>();
//    qryMap.put("funcAction",url);
//    int count=managerFunctionService.getManagerFunctionCount(qryMap);
//    if(count<=0){
//      //存入功能表
//      HashMap<String,Object> tmpFuncInfo=new HashMap<String, Object>();
//      String uuid=ControllerUtils.getPrimaryKey(SEQConstants.FOC_MANAGER_FUNCTION);
//      tmpFuncInfo.put("funcId", "tmp_"+uuid);
//      tmpFuncInfo.put("funcName", "未使用");
//      tmpFuncInfo.put("status", EbhConstants.Status.STATUS_IN_USE);
//      tmpFuncInfo.put("funcAction",url);
//      tmpFuncInfo.put("menuflag",EbhConstants.Menu.MENU_NO);
//      managerFunctionService.insertManagerFunction(tmpFuncInfo);
//      throw new AccessDeniedException("--权限未定义"+url);
//    }
    
    return null;
    
//    throw new AccessDeniedException("SimpleInvocationSecurityMetadataSource Undefined permi:"+url);
    
  }

  public boolean supports(Class<?> clazz) {
    return true;
  }

  public Collection<ConfigAttribute> getAllConfigAttributes() {
    return null;
  }

}