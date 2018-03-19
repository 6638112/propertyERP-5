package com.cnfantasia.server.api.ebuy.entity;

/* import java.io.Serializable;*/
import java.io.Serializable;
import java.lang.String;
/**
 * 描述:() 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyAdvertise implements Serializable{
*/


public class EbuySalesPromotion implements Serializable {

	private static final long serialVersionUID = -1154128162289201167L;

	private Long id;
		private String name;
	
	private Integer canBuyNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCanBuyNum() {
		return canBuyNum;
	}

	public void setCanBuyNum(Integer canBuyNum) {
		this.canBuyNum = canBuyNum;
	}

}
