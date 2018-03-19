package com.cnfantasia.server.api.ebuy.entity;

import java.math.BigInteger;
import java.util.List;


public class PushEguOrderEntity {
	
	private BigInteger edo_id;//配送单id
	private String order_id;//解放区订单号
	private List<EguProduct> goods;//依谷网商品列表
	private String member_name;//联系人姓名
	private String member_mobile;//联系人电话
	private String area;//地区
	private String address;//详细地址
	private String remark = "无订单备注";//现在无订单备注功能
	
	public BigInteger getEdo_id() {
		return edo_id;
	}
	public void setEdo_id(BigInteger edo_id) {
		this.edo_id = edo_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public List<EguProduct> getGoods() {
		return goods;
	}
	public void setGoods(List<EguProduct> goods) {
		this.goods = goods;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_mobile() {
		return member_mobile;
	}
	public void setMember_mobile(String member_mobile) {
		this.member_mobile = member_mobile;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
