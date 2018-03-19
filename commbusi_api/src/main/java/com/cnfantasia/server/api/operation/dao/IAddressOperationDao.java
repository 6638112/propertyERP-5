/**   
 * Filename:    IAddressOperationDao.java   
 * @version:    1.0  
 * Create at:   2015年7月1日 下午1:13:25   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2015年7月1日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.operation.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.operation.entity.AddressIdEntity;
import com.cnfantasia.server.api.operation.entity.MultiSaIdEntity;
import com.cnfantasia.server.api.operation.entity.MultiUniqueCodeEntity;
import com.cnfantasia.server.domainbase.communityAds.entity.CommunityAds;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.operationHomeSupplyType.entity.OperationHomeSupplyType;
import com.cnfantasia.server.domainbase.operationServiceArea.entity.OperationServiceArea;

/**
 * Filename: IAddressOperationDao.java
 * 
 * @version: 1.0.0 Create at: 2015年7月1日 下午1:13:25 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2015年7月1日 shiyl 1.0 1.0 Version
 */
public interface IAddressOperationDao {
	/**
	 * 清除Mybatis缓存
	 */
	public void cleanCache();
	
	/**
	 * 获取编码Id列表
	 * @param countryId
	 * @param provinceId
	 * @param cityId
	 * @param blockId
	 * @param gbId
	 * @return
	 */
	public List<BigInteger> selectCodeIdList(BigInteger countryId,BigInteger provinceId,BigInteger cityId,BigInteger blockId,BigInteger gbId);
	/**
	 * 获取当前具体编码信息
	 * @param countryId
	 * @param provinceId
	 * @param cityId
	 * @param blockId
	 * @param gbId
	 * @return
	 */
	public MultiSaIdEntity selectCodeIdMultiEntity(BigInteger countryId,BigInteger provinceId,BigInteger cityId,BigInteger blockId,BigInteger gbId);
	
	/**
	 * 查询所有可用的服务范围数据
	 * @return
	 */
	public List<OperationServiceArea> selectOperationServiceAreaAll();
	
	/**
	 * demo-like-this
	 * 查询商家列表
	 * @param codeIdList
	 * @return
	 */
	public List<EbuySupplyMerchant> selectEbuySupplyMerchantList(List<BigInteger> codeIdList);
	
	/**
	 * 查询街坊广告列表
	 * @param codeIdList
	 * @return
	 */
	public List<CommunityAds> selectCommunityAdsList(List<BigInteger> codeIdList);
	
	/**
	 * 查询首页
	 * @param codeIdList
	 * @return
	 */
	public List<OperationHomeSupplyType> selectOperationHomeSupplyTypeList(List<BigInteger> codeIdList);

	/**
	 * 获取唯一编码
	 * @param countryId
	 * @param provinceId
	 * @param cityId
	 * @param blockId
	 * @param gbId
	 * @return
	 */
	public MultiUniqueCodeEntity selectUniqueCodeList(BigInteger countryId, BigInteger provinceId, BigInteger cityId,
			BigInteger blockId, BigInteger gbId);

	AddressIdEntity getAddressIdEntity(String city, String block);
}
