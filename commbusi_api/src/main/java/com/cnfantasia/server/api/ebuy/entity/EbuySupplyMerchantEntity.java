/**   
* Filename:    EbuySupplyMerchantEntity.java   
* @version:    1.0  
* Create at:   2014年6月9日 下午1:56:03   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.ebuyorder.entity.MerchantLicense;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;

/**
 * Filename:    EbuySupplyMerchantEntity.java
 * @version:    1.0.0
 * Create at:   2014年6月9日 下午1:56:03
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月9日       shiyl             1.0             1.0 Version
 */
public class EbuySupplyMerchantEntity extends EbuySupplyMerchant{
	
	private static final long serialVersionUID = 1L;
	
	List<GroupBuilding> groupBuildingList;
	
	List<MerchantLicense> merchantLicenseList;

	/***
	 * 是否派发停车优惠券
	 */
	private Integer isSendCarCoupon;

	/**
	 * 店铺图片 
	 * @return
	 */
	public List<String> getShopPhotoeList() {
		List<String> shopPhotoeList = new ArrayList<String>();
		
		if(StringUtils.isNotEmpty(getShopPhotoes())){
			for(String s: getShopPhotoes().split(";")){
				shopPhotoeList.add(CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.EBUY_STORE_PIC, s, DateUtils.strToDateTime(getSys0UpdTime())));
			}
		}
		
		return shopPhotoeList;
	}
	
	/**
	 * 店铺头像
	 * @return
	 */
	public String getStorePicStr() {
		return CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.EBUY_STORE_PIC, getStorePic(), DateUtils.strToDateTime(getSys0UpdTime()));
	}
	
	/**
	 * 配送方式
	 */
	public List<EbuySupplyMerchantHasTEbuyDeliveryMethodEntity_DeliveryMethod> merchantHasDeliveryMethodList;
	public List<EbuySupplyMerchantHasTEbuyDeliveryMethodEntity_DeliveryMethod> getMerchantHasDeliveryMethodList() {
		return merchantHasDeliveryMethodList;
	}
	public void setMerchantHasDeliveryMethodList(
			List<EbuySupplyMerchantHasTEbuyDeliveryMethodEntity_DeliveryMethod> merchantHasDeliveryMethodList) {
		this.merchantHasDeliveryMethodList = merchantHasDeliveryMethodList;
	}
	public List<GroupBuilding> getGroupBuildingList() {
		return groupBuildingList;
	}
	public void setGroupBuildingList(
			List<GroupBuilding> groupBuildingList) {
		this.groupBuildingList = groupBuildingList;
	}
	public List<MerchantLicense> getMerchantLicenseList() {
		return merchantLicenseList;
	}
	public void setMerchantLicenseList(List<MerchantLicense> merchantLicenseList) {
		this.merchantLicenseList = merchantLicenseList;
	}

	public Integer getIsSendCarCoupon() {
		return isSendCarCoupon;
	}

	public void setIsSendCarCoupon(Integer isSendCarCoupon) {
		this.isSendCarCoupon = isSendCarCoupon;
	}
}
