package com.cnfantasia.server.api.payment.entity;

/**
 * 双乾支付补贴请求所需额外参数
 * 
 * @author liyulin
 * @version 1.0 2016年9月8日 下午7:41:55
 */
public class SqPayBtRequest extends SqPayRequest {

	/** 订单类型=={"1":"电商商品","2":物业费","3":"维修","4":"停车费","5":"其他代收费"} */
	private Integer orderType;
	/** 省 */
	private String province;
	/** 市 */
	private String city;
	/** 行政区 */
	private String block;
	/** 小区 */
	private String gbName;
	/** 小区id */
	private String gbId;

	public SqPayBtRequest() {
		super();
	}

	public SqPayBtRequest(Integer orderType, String province, String city,
			String block, String gbName, String gbId) {
		super();
		this.orderType = orderType;
		this.province = province;
		this.city = city;
		this.block = block;
		this.gbName = gbName;
		this.gbId = gbId;
	}


	/** 订单类型=={"1":"电商商品","2":物业费","3":"维修","4":"停车费","5":"其他代收费"} */
	public Integer getOrderType() {
		return orderType;
	}

	/** 订单类型=={"1":"电商商品","2":物业费","3":"维修","4":"停车费","5":"其他代收费"} */
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getGbName() {
		return gbName;
	}

	public void setGbName(String gbName) {
		this.gbName = gbName;
	}

	public String getGbId() {
		return gbId;
	}

	public void setGbId(String gbId) {
		this.gbId = gbId;
	}
	
	@Override
	public String toString() {
		return "SqPayBtRequest [orderType=" + orderType + ", province="
				+ province + ", city=" + city + ", block=" + block
				+ ", gbName=" + gbName + ", gbId=" + gbId+ ", toString()=" + super.toString()
				+ "]";
	}

}
