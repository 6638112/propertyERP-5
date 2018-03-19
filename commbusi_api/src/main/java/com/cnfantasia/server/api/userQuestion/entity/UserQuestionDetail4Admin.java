package com.cnfantasia.server.api.userQuestion.entity;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserQuestionDetail4Admin  extends UserQuestion4Admin {
	private BigInteger roomId; //当前门牌Id

	/**
	 * 图片集，以分号分隔的
	 */
	String pics;
	/**
	 * 图片列表
	 */
	List<String> picList;

	public BigInteger getRoomId() {
		return roomId;
	}
	
	public void setRoomId(BigInteger roomId) {
		this.roomId = roomId;
	}
	public List<String> getPicList() {
		if (this.pics != null && this.pics.length() > 0) {
			List<String> tmp = Arrays.asList(pics.split(";"));
			picList = new ArrayList<>(tmp.size());
			for (String s : tmp) {
				picList.add(CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.DredgePicBasePath, s, null));
			}
		}
		return picList;
	}

	public void setPicList(List<String> picList) {
		this.picList = picList;
	}

	public String getPics() {
		return pics;
	}

	public void setPics(String pics) {
		this.pics = pics;
	}
}
