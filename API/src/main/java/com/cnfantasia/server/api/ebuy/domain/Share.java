package com.cnfantasia.server.api.ebuy.domain;

import java.io.Serializable;
import java.lang.String;


public class Share implements Serializable {

	private static final long serialVersionUID = -3182801340954929809L;

	private String url;
	
	private String pic;
	
	private String tittle;

	private String friendTitle;
	private String cycleTitle;
	private String sharePic;
	private String sharePushPic;
	
	private String desc;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getFriendTitle() {
		return friendTitle;
	}

	public void setFriendTitle(String friendTitle) {
		this.friendTitle = friendTitle;
	}

	public String getCycleTitle() {
		return cycleTitle;
	}

	public void setCycleTitle(String cycleTitle) {
		this.cycleTitle = cycleTitle;
	}

	public String getSharePic() {
		return sharePic;
	}

	public void setSharePic(String sharePic) {
		this.sharePic = sharePic;
	}

	public String getSharePushPic() {
		return sharePushPic;
	}

	public void setSharePushPic(String sharePushPic) {
		this.sharePushPic = sharePushPic;
	}
}
