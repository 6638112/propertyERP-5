package com.cnfantasia.server.api.property.dto;

/**
 * 账单顶部信息
 * 
 * @author liyulin
 * @version 1.0 2017年3月10日 下午1:53:57
 */
public class BillBaseDto {
	/** 账单标题 */
	private String title;
	/** 描述 */
	private String desc;
	/** 账单金额（单位：元） */
	private String amount;
	/** 物业公司电话号码 */
	private String tel;

	//是否收往期欠费，只有2才收
	private Integer collectionArrearsType;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getCollectionArrearsType() {
		return collectionArrearsType;
	}

	public void setCollectionArrearsType(Integer collectionArrearsType) {
		this.collectionArrearsType = collectionArrearsType;
	}

}
