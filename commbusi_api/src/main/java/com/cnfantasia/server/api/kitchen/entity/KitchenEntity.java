/**   
 * Filename:    KitchenEntity.java   
 * @version:    1.0  
 * Create at:   2014年7月28日 上午9:59:52   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年7月28日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.kitchen.entity;

import java.util.List;

import com.cnfantasia.server.api.kitchen.constant.KitchenDict;
import com.cnfantasia.server.domainbase.kitchenCookbook.entity.KitchenCookbook;
import com.cnfantasia.server.domainbase.kitchenDetail.entity.KitchenDetail;

/**
 * Filename: KitchenEntity.java
 * 
 * @version: 1.0.0 Create at: 2014年7月28日 上午9:59:52 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年7月28日 shiyl 1.0 1.0 Version
 */
public class KitchenEntity extends KitchenCookbook {
	private static final long serialVersionUID = 1L;
	/**是否想吃*/
	private Integer isLikeEat;
	public Integer getIsLikeEat() {
		return isLikeEat;
	}
	public void setIsLikeEat(Integer isLikeEat) {
		this.isLikeEat = isLikeEat;
	}
	
	/**
	 * 总共想吃的人数
	 */
	private Integer totalLikeEatCount;

	public Integer getTotalLikeEatCount() {
		return totalLikeEatCount;
	}

	public void setTotalLikeEatCount(Integer totalLikeEatCount) {
		this.totalLikeEatCount = totalLikeEatCount;
	}

	/**
	 * 厨房菜谱参数列表
	 */
	private List<KitchenDetail> kitchenDetailList;

	public List<KitchenDetail> getKitchenDetailList() {
		return kitchenDetailList;
	}

	public void setKitchenDetailList(List<KitchenDetail> kitchenParamList) {
		this.kitchenDetailList = kitchenParamList;
	}

	/**
	 * 所属厨房菜谱类别
	 */
	private List<KitchenCookbookTypeEntity> kitchenCookbookTypeList;

	public List<KitchenCookbookTypeEntity> getKitchenCookbookTypeList() {
		return kitchenCookbookTypeList;
	}
	public void setKitchenCookbookTypeList(List<KitchenCookbookTypeEntity> kitchenCookbookTypeList) {
		this.kitchenCookbookTypeList = kitchenCookbookTypeList;
	}

	/**
	 * 步骤详情
	 */
	private List<KitchenCookbookStepEntity> kitchenCookbookStepList;
	public List<KitchenCookbookStepEntity> getKitchenCookbookStepList() {
		return kitchenCookbookStepList;
	}
	public void setKitchenCookbookStepList(List<KitchenCookbookStepEntity> kitchenCookbookStepList) {
		this.kitchenCookbookStepList = kitchenCookbookStepList;
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
