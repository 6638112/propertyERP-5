package com.cnfantasia.server.ms.notice.dto;

import java.util.List;

import com.cnfantasia.server.domainbase.message.entity.Message;

/**
 * 公告列表
 * 
 * @author liyulin
 * @version 1.0 2016年5月11日 下午1:40:05
 */
public class NoticeListBean extends Message {

	private List<String> gbNames;// 小区名称
	private String gbNamesTip;// 提示

	public NoticeListBean() {
		super();
	}

	public NoticeListBean(List<String> gbNames, String gbNamesTip) {
		super();
		this.gbNames = gbNames;
		this.gbNamesTip = gbNamesTip;
	}

	public List<String> getGbNames() {
		return gbNames;
	}

	public void setGbNames(List<String> gbNames) {
		this.gbNames = gbNames;
	}

	public String getGbNamesTip() {
		return gbNamesTip;
	}

	public void setGbNamesTip(String gbNamesTip) {
		this.gbNamesTip = gbNamesTip;
	}

}
