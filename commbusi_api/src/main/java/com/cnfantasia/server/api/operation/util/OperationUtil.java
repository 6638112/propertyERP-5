/**   
* Filename:    OperationUtil.java   
* @version:    1.0  
* Create at:   2015年7月7日 上午7:09:52   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年7月7日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.operation.util;

import java.math.BigInteger;

import com.cnfantasia.server.api.operation.constant.OperationConstant;
import com.cnfantasia.server.api.operation.entity.AddressIdEntity;
import com.cnfantasia.server.api.operation.entity.MultiUniqueCodeEntity;

/**
 * Filename:    OperationUtil.java
 * @version:    1.0.0
 * Create at:   2015年7月7日 上午7:09:52
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年7月7日       shiyl             1.0             1.0 Version
 */
public class OperationUtil {
	
	public static MultiUniqueCodeEntity generateUniqueCodeList(AddressIdEntity addressIdEntity){
		if(addressIdEntity==null){return null;}
		BigInteger countryId = addressIdEntity.getCountryId();
		BigInteger provinceId = addressIdEntity.getProvinceId();
		BigInteger cityId = addressIdEntity.getCityId();
		BigInteger blockId = addressIdEntity.getBlockId();
		BigInteger gbId = addressIdEntity.getGbId();
		return generateUniqueCodeList(countryId, provinceId, cityId, blockId, gbId);
	}
	
	public static MultiUniqueCodeEntity generateUniqueCodeList(BigInteger countryId, BigInteger provinceId, BigInteger cityId,
			BigInteger blockId, BigInteger gbId){
		MultiUniqueCodeEntity resEntity = new MultiUniqueCodeEntity();
		if(countryId!=null){
			resEntity.setCountryUniqueCode(generateUniqueCode(countryId));
			if(provinceId!=null){
				resEntity.setProvinceUniqueCode(generateUniqueCode(countryId, provinceId));
				if(cityId!=null){
					resEntity.setCityUniqueCode(generateUniqueCode(countryId, provinceId, cityId));
					if(blockId!=null){
						resEntity.setBlockUniqueCode(generateUniqueCode(countryId, provinceId, cityId, blockId));
						if(gbId!=null){
							resEntity.setGbUniqueCode(generateUniqueCode(countryId, provinceId, cityId, blockId, gbId));
						}
					}
				}
			}
		}
		return resEntity;
	}
	
	private static String generateUniqueCode(BigInteger countryId){
		BigInteger provinceId = null;
		BigInteger cityId = null;
		BigInteger blockId = null;
		BigInteger gbId = null;
		return generateUniqueCode(countryId, provinceId, cityId, blockId, gbId);
	}
	private static String generateUniqueCode(BigInteger countryId, BigInteger provinceId){
		BigInteger cityId = null;
		BigInteger blockId = null;
		BigInteger gbId = null;
		return generateUniqueCode(countryId, provinceId, cityId, blockId, gbId);
	}
	private static String generateUniqueCode(BigInteger countryId, BigInteger provinceId, BigInteger cityId){
		BigInteger blockId = null;
		BigInteger gbId = null;
		return generateUniqueCode(countryId, provinceId, cityId, blockId, gbId);
	}
	private static String generateUniqueCode(BigInteger countryId, BigInteger provinceId, BigInteger cityId,
			BigInteger blockId){
		BigInteger gbId = null;
		return generateUniqueCode(countryId, provinceId, cityId, blockId, gbId);
	}
	
	private static String generateUniqueCode(BigInteger countryId, BigInteger provinceId, BigInteger cityId,
			BigInteger blockId, BigInteger gbId){
		countryId = countryId==null?OperationConstant.defaultAddressId:countryId;
		provinceId = provinceId==null?OperationConstant.defaultAddressId:provinceId;
		cityId = cityId==null?OperationConstant.defaultAddressId:cityId;
		blockId = blockId==null?OperationConstant.defaultAddressId:blockId;
		gbId = gbId==null?OperationConstant.defaultAddressId:gbId;
		
		StringBuffer uniqueCodeSbf = new StringBuffer();
		uniqueCodeSbf.append(countryId).append(".");
		uniqueCodeSbf.append(provinceId).append(".");
		uniqueCodeSbf.append(cityId).append(".");
		uniqueCodeSbf.append(blockId).append(".");
		{//自定义片区Id
			BigInteger selfBlockId = OperationConstant.defaultAddressId;
			uniqueCodeSbf.append(selfBlockId).append(".");
		}
		uniqueCodeSbf.append(gbId).append("");
		
		return uniqueCodeSbf.toString();
	}
	/**
	 * 
	 	update t_operation_service_area set f_address_unique = 
		CONCAT(t_address_country_f_id
		,'.',t_address_province_f_id
		,'.',t_address_city_f_id
		,'.',t_address_block_f_id
		,'.',t_address_block_self_f_id
		,'.',t_group_building_f_id
		);
	 */
}
