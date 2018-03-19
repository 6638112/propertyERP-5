package com.cnfantasia.server.api.ebuy.entity;

import java.math.BigInteger;

public class EguProduct {
	private BigInteger goods_id;//依谷网商品id
	private String goods_name;//商品名
	private Long buy_num;
	public BigInteger getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(BigInteger goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public Long getBuy_num() {
		return buy_num;
	}
	public void setBuy_num(Long buy_num) {
		this.buy_num = buy_num;
	}
}
