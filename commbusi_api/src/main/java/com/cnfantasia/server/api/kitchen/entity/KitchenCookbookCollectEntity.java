/**   
* Filename:    KitchenCookbookCollectEntity.java   
* @version:    1.0  
* Create at:   2014年9月16日 上午8:47:27   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月16日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.kitchen.entity;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.domainbase.kitchenCookbookCollect.entity.KitchenCookbookCollect;

/**
 * Filename:    KitchenCookbookCollectEntity.java
 * @version:    1.0.0
 * Create at:   2014年9月16日 上午8:47:27
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月16日       shiyl             1.0             1.0 Version
 */
public class KitchenCookbookCollectEntity extends KitchenCookbookCollect{
	private static final long serialVersionUID = 1L;
	
	private KitchenEntity kitchenEntity;
	
	public KitchenEntity getKitchenEntity() {
		return kitchenEntity;
	}
	public void setKitchenEntity(KitchenEntity kitchenEntity) {
		this.kitchenEntity = kitchenEntity;
	}

	@Override
	public BigInteger gettKitchenCookbookFId() {
		if(kitchenEntity==null){
			return null;
		}
		return kitchenEntity.getId();
	}

	@Override
	public void settKitchenCookbookFId(BigInteger tKitchenCookbookFId) {
		if(kitchenEntity==null){
			kitchenEntity = new KitchenEntity();
		}
		kitchenEntity.setId(tKitchenCookbookFId);
	}
	
	/**当前用户是否想吃*/
	private Boolean fml_isLikeEat;
	public Boolean getFml_isLikeEat() {
		return fml_isLikeEat;
	}
	public void setFml_isLikeEat(Boolean fml_isLikeEat) {
		this.fml_isLikeEat = fml_isLikeEat;
	}
	
	/**家庭总的想吃人数*/
	private Integer fml_totalLikeEatCount;
	public Integer getFml_totalLikeEatCount() {
		return fml_totalLikeEatCount;
	}
	public void setFml_totalLikeEatCount(Integer fml_totalLikeEatCount) {
		this.fml_totalLikeEatCount = fml_totalLikeEatCount;
	}
	
	/**家庭总的想吃的用户列表*/
	private List<UserSimpleEntity> fml_isLikeEat_userList;
	public List<UserSimpleEntity> getFml_isLikeEat_userList() {
		return fml_isLikeEat_userList;
	}
	public void setFml_isLikeEat_userList(List<UserSimpleEntity> fml_isLikeEat_userList) {
		this.fml_isLikeEat_userList = fml_isLikeEat_userList;
	}
	
}
