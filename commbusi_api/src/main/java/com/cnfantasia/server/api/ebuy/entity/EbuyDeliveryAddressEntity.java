/**   
* Filename:    EbuyDeliveryAddressEntity.java   
* @version:    1.0  
* Create at:   2014年6月6日 上午6:24:42   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月6日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.entity;

import com.cnfantasia.server.api.commonBusiness.util.CommonRoomUtil;
import com.cnfantasia.server.api.room.entity.RoomEntity;
import com.cnfantasia.server.domainbase.ebuyDeliveryAddress.entity.EbuyDeliveryAddress;
import com.cnfantasia.server.domainbase.ebuyHandleAddress.entity.EbuyHandleAddress;

import java.math.BigInteger;

/**
 * Filename:    EbuyDeliveryAddressEntity.java
 * @version:    1.0.0
 * Create at:   2014年6月6日 上午6:24:42
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月6日       shiyl             1.0             1.0 Version
 */
public class EbuyDeliveryAddressEntity extends EbuyDeliveryAddress{
	private static final long serialVersionUID = 1L;
	/**具体收货地址的信息对象,此处只能使用Object对象类型*/
	private Object  singalDetail;

	private BigInteger gbId;
	private String gbName;
	private BigInteger blockId;
	private String blockName;
	private BigInteger cityId;
	private String cityName;
	private BigInteger provinceId;
	private String provinceName;
	private String addressDetail;

	public Object getSingalDetail() {
		return singalDetail;
	}

	public void setSingalDetail(Object singalDetail) {
		this.singalDetail = singalDetail;
	}
	
	public SimpleDelivAddress getSimpleDelivAddress(){
		SimpleDelivAddress simpleDelivAddress = null;
		if(singalDetail!=null){
			if(singalDetail instanceof RoomEntity){
				RoomEntity roomEntity = (RoomEntity)singalDetail;
				if(roomEntity!=null){
					String addressArea=CommonRoomUtil.getAddressArea(roomEntity);
					String addressDetail = CommonRoomUtil.getAddressDetail(roomEntity);
					BigInteger targetId=roomEntity.getId();
					simpleDelivAddress = new SimpleDelivAddress(addressArea, addressDetail, targetId);
				}
			}else if(singalDetail instanceof EbuyHandleAddress){
				EbuyHandleAddress ebuyHandleAddress = (EbuyHandleAddress)singalDetail;
				String addressArea=ebuyHandleAddress.getAddressArea();
				String addressDetail = ebuyHandleAddress.getAddressDetail();
				BigInteger targetId=ebuyHandleAddress.getId();
				simpleDelivAddress = new SimpleDelivAddress(addressArea, addressDetail, targetId);
			}
		}
		return simpleDelivAddress;
	}

	public BigInteger getGbId() {
		return gbId;
	}

	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}

	public String getGbName() {
		return gbName;
	}

	public void setGbName(String gbName) {
		this.gbName = gbName;
	}

	public BigInteger getBlockId() {
		return blockId;
	}

	public void setBlockId(BigInteger blockId) {
		this.blockId = blockId;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public BigInteger getCityId() {
		return cityId;
	}

	public void setCityId(BigInteger cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public BigInteger getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(BigInteger provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
}
