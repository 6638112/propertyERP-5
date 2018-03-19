/**   
 * Filename:    MerchantResult.java   
 * @version:    1.0  
 * Create at:   2014年11月26日 上午7:28:30   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年11月26日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.communitySupply.entity02;

import java.math.BigInteger;

import com.alibaba.fastjson.JSON;

/**
 * Filename: MerchantResult.java
 * 
 * @version: 1.0.0 Create at: 2014年11月26日 上午7:28:30 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年11月26日 shiyl 1.0 1.0 Version
 */
public class MerchantResult {

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	private BigInteger supplyTypeId;
	public BigInteger getSupplyTypeId() {
		return supplyTypeId;
	}
	public void setSupplyTypeId(BigInteger supplyTypeId) {
		this.supplyTypeId = supplyTypeId;
	}

	private String name;
	private Location location;
	private String address;
	private String street_id;
	private String telephone;
	private String uid;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStreet_id() {
		return street_id;
	}

	public void setStreet_id(String street_id) {
		this.street_id = street_id;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

}
