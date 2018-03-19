package com.cnfantasia.server.api.gaode.entity;

public class POI {
	/*
	 	"id": "B02EA017Y7",
		"name": "御景华城(荣昌路)",
		"type": "商务住宅;住宅区;住宅小区",
		"typecode": "120302",
		"biz_type": [],
		"address": "珍珠路与通化街交汇处",
		"location": "111.589170,26.447647",
		"tel": [],
		"distance": [],
		"biz_ext": [],
		"pname": "湖南省",
		"cityname": "永州市",
		"adname": "冷水滩区",
		"importance": [],
		"shopid": [],
		"shopinfo": "0",
		"poiweight": []
	 */
	String id;
	String name;
	String type;
	String typecode;
	String address;
	String location;
	String tel;
	String pname;
	String cityname;
	String adname;
	String shopid;
	String shopinfo;
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypecode() {
		return typecode;
	}
	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getAdname() {
		return adname;
	}
	public void setAdname(String adname) {
		this.adname = adname;
	}
	public String getShopid() {
		return shopid;
	}
	public void setShopid(String shopid) {
		this.shopid = shopid;
	}
	public String getShopinfo() {
		return shopinfo;
	}
	public void setShopinfo(String shopinfo) {
		this.shopinfo = shopinfo;
	}
}
