/**   
* Filename:    IAddressOperationService.java   
* @version:    1.0  
* Create at:   2015年7月1日 下午12:46:45   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年7月1日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.operation.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.operation.entity.AddressIdEntity;
import com.cnfantasia.server.api.operation.entity.MultiSaIdEntity;
import com.cnfantasia.server.api.operation.entity.OperationHomeSupplyTypeEntity;
import com.cnfantasia.server.domainbase.communityAds.entity.CommunityAds;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;

/**
 * Filename:    IAddressOperationService.java
 * @version:    1.0.0
 * Create at:   2015年7月1日 下午12:46:45
 * Description:地理位置运营服务
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年7月1日       shiyl             1.0             1.0 Version
 */
public interface IAddressOperationService {
	
	/**
	 * 获取对应的编码Id列表
	 * @param countryId
	 * @param provinceId
	 * @param cityId
	 * @param blockId
	 * @param gbId
	 * @return
	 */
	public List<BigInteger> getCodeIdList(BigInteger countryId,BigInteger provinceId,BigInteger cityId,BigInteger blockId,BigInteger gbId);
	
	/**
	 * 获取当前具体编码信息
	 * @param countryId
	 * @param provinceId
	 * @param cityId
	 * @param blockId
	 * @param gbId
	 * @return
	 */
	public MultiSaIdEntity getCodeIdMultiEntity(BigInteger countryId,BigInteger provinceId,BigInteger cityId,BigInteger blockId,BigInteger gbId);
	
	/**
	 * demo-like-this
	 * 查询周边商家列表
	 * 需要获取到哪一级数据则设定对应数据不为空
	 * 例如：查询小区级别的数据则 getEbuySupplyMerchantList(null,null,null,null,gbId)
	 * 例如：查询国家市基本的数据则 getEbuySupplyMerchantList(null,null,cityId,null,null)
	 * 例如：查询国家级别的数据则 getEbuySupplyMerchantList(countryId,null,null,null,null)
	 * @param countryId
	 * @param provinceId
	 * @param cityId
	 * @param blockId
	 * @param gbId
	 * @return
	 */
	public List<EbuySupplyMerchant> getEbuySupplyMerchantList(BigInteger countryId,BigInteger provinceId,BigInteger cityId,BigInteger blockId,BigInteger gbId);
	
	
	/**
	 * 根据小区Id获取周边商家列表
	 * @param gbId
	 * @return
	 */
	public List<EbuySupplyMerchant> getEbuySupplyMerchantListByGbId(BigInteger gbId);
	
	/**
	 * 根据小区查询对应的街坊广告信息
	 * @param gbId
	 * @return
	 */
	public List<CommunityAds> getCommunityAdsListByGbId(BigInteger gbId);
	
	/**
	 * 根据Code获取首页类别列表
	 * @param codeIdList
	 * @return
	 */
	public List<OperationHomeSupplyTypeEntity> getOperationHomeSupplyTypeListByGbId(BigInteger gbId);

	/**
	 * 根据Code获取首页类别列表通用方法
	 * @param countryId
	 * @param provinceId
	 * @param cityId
	 * @param blockId
	 * @param gbId
	 * @return
	 */
	public List<OperationHomeSupplyTypeEntity> getOperationHomeSupplyTypeList(BigInteger countryId, BigInteger provinceId,
			BigInteger cityId, BigInteger blockId, BigInteger gbId);

	AddressIdEntity getAddressIdEntity(String city, String block);
}
