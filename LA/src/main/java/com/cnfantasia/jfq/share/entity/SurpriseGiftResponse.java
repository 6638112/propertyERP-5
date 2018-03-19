package com.cnfantasia.jfq.share.entity;

public class SurpriseGiftResponse {
	String id;
	String name;
	int type;//派奖类型=={1:"解放区粮票,消费券";2:"商品";3:"怡宝券";4:"58家政券"}
	double hbAmount;
	String exchCode;//兑换码
	long createTimeLong;
	long expiryTimeLong;
	int fetchStatus;
	String detailUrl;
	int useStatus;
	String iconUrl;
	String showCount;
	
	public String getExchCode() {
		return exchCode;
	}

	public void setExchCode(String exchCode) {
		this.exchCode = exchCode;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getHbAmount() {
		return hbAmount;
	}

	public void setHbAmount(double hbAmount) {
		this.hbAmount = hbAmount;
	}

	public long getCreateTimeLong() {
		return createTimeLong;
	}

	public void setCreateTimeLong(long createTimeLong) {
		this.createTimeLong = createTimeLong;
	}

	public long getExpiryTimeLong() {
		return expiryTimeLong;
	}

	public void setExpiryTimeLong(long expiryTimeLong) {
		this.expiryTimeLong = expiryTimeLong;
	}

	public int getFetchStatus() {
		return fetchStatus;
	}

	public void setFetchStatus(int fetchStatus) {
		this.fetchStatus = fetchStatus;
	}

	public String getDetailUrl() {
		return detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}

	public int getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(int useStatus) {
		this.useStatus = useStatus;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getShowCount() {
		return showCount;
	}

	public void setShowCount(String showCount) {
		this.showCount = showCount;
	}
	
}
