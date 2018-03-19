/**   
* Filename:    DynamicDataSource.java   
* @version:    1.0  
* Create at:   2014年6月12日 上午12:56:45   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月12日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.pub.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.cnfantasia.server.business.pub.datasource.DataSourceContextHolder;

/**
 * Filename:    DynamicDataSource.java
 * @version:    1.0.0
 * Create at:   2014年6月12日 上午12:56:45
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月12日       shiyl             1.0             1.0 Version
 */
public class DynamicDataSource extends AbstractRoutingDataSource{

	@Override  
  protected Object determineCurrentLookupKey() {
    return DataSourceContextHolder.getDbType();  
  }
	
}
