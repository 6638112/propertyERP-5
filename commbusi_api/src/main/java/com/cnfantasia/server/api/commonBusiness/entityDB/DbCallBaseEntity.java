/**   
* Filename:    DbCallBaseEntity.java   
* @version:    1.0  
* Create at:   2015年4月8日 上午2:45:08   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.entityDB;

import java.io.Serializable;

/**
 * Filename:    DbCallBaseEntity.java
 * @version:    1.0.0
 * Create at:   2015年4月8日 上午2:45:08
 * Description:调用DB存储过程返回数据公共格式
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月8日       shiyl             1.0             1.0 Version
 */
public abstract class DbCallBaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**记录唯一标识*/
	private String id;
	/**结果状态*/
	protected String status;
	
	public DbCallBaseEntity(){}
	public DbCallBaseEntity(String id,String status){
		this.id = id;
		this.status = status;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public boolean isDataAvailable(){
		/**有类别且状态正常*/
		if(getStatus()!=null&&getStatus().equals("0")){//不要使用id或者status判断是否有值
			return true;
		}else{
			return false;
		}
	}
}
