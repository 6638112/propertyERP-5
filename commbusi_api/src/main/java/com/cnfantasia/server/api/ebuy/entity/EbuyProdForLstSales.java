package com.cnfantasia.server.api.ebuy.entity;

public class EbuyProdForLstSales extends EbuyProdForLst {

	private static final long serialVersionUID = 3387769878070848554L;

	private Integer opType; //运营类型，1为新用户专享
	
	private String opName; //运营活动名称,如新用户专享
	
	private Integer canBuyNum; //此类活动最多可以买多少个商品
	
	private String desc;// 商品描述



	public Integer getOpType() {
		if(opType == null) {
			return 0;
		}
		return opType;
	}

	public void setOpType(Integer opType) {
		this.opType = opType;
	}

	public String getOpName() {
		return opName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

	public Integer getCanBuyNum() {
		if(canBuyNum == null) {
			return 0;
		}
		return canBuyNum;
	}

	public void setCanBuyNum(Integer canBuyNum) {
		this.canBuyNum = canBuyNum;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
