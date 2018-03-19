/**   
* Filename:    OriginDbOperateInvoker.java   
* @version:    1.0  
* Create at:   2014年6月15日 上午10:49:30   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月15日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.pub.datasource;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.business.pub.datasource.DataSourceContextHolder;

/**
 * Filename:    OriginDbOperateInvoker.java
 * @version:    1.0.0
 * Create at:   2014年6月15日 上午10:49:30
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月15日       shiyl             1.0             1.0 Version
 */
public class OriginDbOperateInvoker {
	private static final Log logger = LogFactory.getLog(OriginDbOperateInvoker.class);
	private static final String DATASOURCE_ORIGIN="dataSourceOrigin";
	
	public static  <T> T invokeDb(Object daoObj,String methodName) {
		return invokeDb(daoObj, methodName, new Object[]{});
	}
  @SuppressWarnings("unchecked")
	public static  <T> T invokeDb(Object daoObj,String methodName,Object[] params) {
    Object resDataValue=null;
    //获取要执行的方法
    Method currMethod=null;
    {
      Method[] allMethods=daoObj.getClass().getMethods();
      for (int i = 0; i < allMethods.length; i++) {
        if(allMethods[i].getName().equals(methodName)){
          currMethod=allMethods[i];
          break;
        }
      }
    }
    //提交要执行的方法
    if(currMethod==null){
    	logger.error("OriginDbOperateInvoker invokeDb method is null!");
      throw new RuntimeException("OriginDbOperateInvoker invokeDb method is null!");
    }else{
      try {
      	DataSourceContextHolder.setDbType(DATASOURCE_ORIGIN);
        if(params==null||params.length<=0){
          resDataValue=currMethod.invoke(daoObj);
        }else{
          resDataValue=currMethod.invoke(daoObj,params);
        }
      } catch (Exception e) {
      	logger.debug("OriginDbOperateInvoker.invokeDb cause error:class is "+daoObj.getClass()+";method is "+currMethod.getName()+";params is"+params,e);
        throw new RuntimeException("OriginDbOperateInvoker.invokeDb cause error:class is"+daoObj.getClass()+",method is "+currMethod.getName()+"params is"+params,e);
      }finally{
      	DataSourceContextHolder.setDbType(null);
      }
    }
    //返回
    return (T)resDataValue;
  }
}
