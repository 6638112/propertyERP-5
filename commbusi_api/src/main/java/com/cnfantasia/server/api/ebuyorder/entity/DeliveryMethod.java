package com.cnfantasia.server.api.ebuyorder.entity;

public class DeliveryMethod {
	long id;
	String name;
	long fee; 
	Long price_amount_start;
	Long price_amount_end;
	
	int type;//配送类型=={"1":"快递","2":"定点自提","3":"上门"}
	String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getFee() {
		return fee;
	}
	public void setFee(long fee) {
		this.fee = fee;
	}
	public Long getPrice_amount_start() {
		return price_amount_start;
	}
	public void setPrice_amount_start(Long price_amount_start) {
		this.price_amount_start = price_amount_start;
	}
	public Long getPrice_amount_end() {
		return price_amount_end;
	}
	public void setPrice_amount_end(Long price_amount_end) {
		this.price_amount_end = price_amount_end;
	}
}
