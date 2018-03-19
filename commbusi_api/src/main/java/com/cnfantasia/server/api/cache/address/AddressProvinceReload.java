/**   
 * Filename:    AddressProvinceReload.java   
 * @version:    1.0  
 * Create at:   2015年7月31日 上午10:37:54   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2015年7月31日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.cache.address;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.cache.constant.CacheConstant;
import com.cnfantasia.server.domainbase.addressProvince.dao.IAddressProvinceBaseDao;
import com.cnfantasia.server.domainbase.addressProvince.entity.AddressProvince;

/**
 * Filename: AddressProvinceReload.java
 * 
 * @version: 1.0.0 Create at: 2015年7月31日 上午10:37:54 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2015年7月31日 shiyl 1.0 1.0 Version
 */
public class AddressProvinceReload extends AbstractAddressDataReLoad{
	private Log logger = LogFactory.getLog(getClass());
	@Override
	Log getLogger() {
		return logger;
	}
	
	private IAddressProvinceBaseDao addressProvinceBaseDao;
	public void setAddressProvinceBaseDao(IAddressProvinceBaseDao addressProvinceBaseDao) {
		this.addressProvinceBaseDao = addressProvinceBaseDao;
	}

	@Override
	public String getModelKey() {
		return CacheConstant.ModelCode.hset_ap_info;
	}
	
	@Override
	public List<AddressDataInfo> getInitData() {
		List<AddressDataInfo> resList = null;
		// 初始化各个模块的数据
		List<AddressProvince> dataList = addressProvinceBaseDao.selectAddressProvinceByCondition(null, true);
		if (dataList != null && dataList.size() > 0) {
			resList = new ArrayList<AddressDataInfo>();
			for (AddressProvince tmpData : dataList) {
				AddressDataInfo  addressDataInfo= new AddressDataInfo();
				addressDataInfo.setId(tmpData.getId());
				addressDataInfo.setName(tmpData.getName());
				addressDataInfo.setParentId(tmpData.gettAddressCountryFId());
				resList.add(addressDataInfo);
			}
		}
		return resList;
	}

}
