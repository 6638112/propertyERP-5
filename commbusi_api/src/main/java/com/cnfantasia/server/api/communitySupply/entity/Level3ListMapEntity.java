/**   
* Filename:    Level3ListMapEntity.java   
* @version:    1.0  
* Create at:   2015年8月19日 上午9:49:04   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年8月19日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.entity;

import java.util.List;
import java.util.Map;

/**
 * Filename:    Level3ListMapEntity.java
 * @version:    1.0.0
 * Create at:   2015年8月19日 上午9:49:04
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年8月19日       shiyl             1.0             1.0 Version
 */
public class Level3ListMapEntity {
	
	private List<Map<String,Object>> level00List;
	private List<Map<String,Object>> level01List;
	private List<Map<String,Object>> level02List;
	
	public Level3ListMapEntity(List<Map<String,Object>> level01List,List<Map<String,Object>> level02List){
		this.level01List = level01List;
		this.level02List = level02List;
	}
	public Level3ListMapEntity(List<Map<String,Object>> level00List,List<Map<String,Object>> level01List,List<Map<String,Object>> level02List){
		this.level00List = level00List;
		this.level01List = level01List;
		this.level02List = level02List;
	}
	
	public List<Map<String, Object>> getLevel00List() {
		return level00List;
	}
	public void setLevel00List(List<Map<String, Object>> level00List) {
		this.level00List = level00List;
	}
	public List<Map<String, Object>> getLevel01List() {
		return level01List;
	}
	public void setLevel01List(List<Map<String, Object>> level01List) {
		this.level01List = level01List;
	}
	public List<Map<String, Object>> getLevel02List() {
		return level02List;
	}
	public void setLevel02List(List<Map<String, Object>> level02List) {
		this.level02List = level02List;
	}
	
	
	
}
