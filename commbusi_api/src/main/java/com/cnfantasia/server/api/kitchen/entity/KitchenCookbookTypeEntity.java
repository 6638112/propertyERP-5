/**   
* Filename:    KitchenCookbookTypeEntity.java   
* @version:    1.0  
* Create at:   2014年9月15日 上午2:39:15   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月15日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.kitchen.entity;

import java.math.BigInteger;

import com.cnfantasia.server.api.kitchen.constant.KitchenDict;
import com.cnfantasia.server.domainbase.kitchenCookbookTopType.entity.KitchenCookbookTopType;
import com.cnfantasia.server.domainbase.kitchenCookbookType.entity.KitchenCookbookType;

/**
 * Filename:    KitchenCookbookTypeEntity.java
 * @version:    1.0.0
 * Create at:   2014年9月15日 上午2:39:15
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月15日       shiyl             1.0             1.0 Version
 */
public class KitchenCookbookTypeEntity extends KitchenCookbookType {
	private static final long serialVersionUID = 1L;
	
	private KitchenCookbookTopType kitchenCookbookTopType;
	public KitchenCookbookTopType getKitchenCookbookTopType() {
		return kitchenCookbookTopType;
	}

	public void setKitchenCookbookTopType(KitchenCookbookTopType kitchenCookbookTopType) {
		this.kitchenCookbookTopType = kitchenCookbookTopType;
	}

	@Override
	public BigInteger gettKitchenCookbookTopTypeFId() {
		if(kitchenCookbookTopType==null){return null;}
		return kitchenCookbookTopType.getId();
	}
	@Override
	public void settKitchenCookbookTopTypeFId(BigInteger tKitchenCookbookTopTypeFId) {
		if(kitchenCookbookTopType==null){
			kitchenCookbookTopType = new KitchenCookbookTopType();
		}
		kitchenCookbookTopType.setId(tKitchenCookbookTopTypeFId);
	}
	
	/**
	 * 用户是否已收藏标识,0否1是
	 */
	private Integer collectFlag;
	public Integer getCollectFlag() {
		return collectFlag;
	}
	public void setCollectFlag(Integer collectFlag) {
		this.collectFlag = collectFlag;
	}
	
	public boolean fetchIsCollect(){
		if(collectFlag!=null&&KitchenDict.Kitchen_Cookbook_Type_CollectFlag.YES.compareTo(collectFlag)==0){
			return true;
		}else{
			return false;
		}
	}
	
}
