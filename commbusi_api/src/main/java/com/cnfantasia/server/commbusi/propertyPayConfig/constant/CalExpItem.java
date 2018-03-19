package com.cnfantasia.server.commbusi.propertyPayConfig.constant;

/**
 * 计算元素
 * 
 * @author liyulin
 * @version 1.0 2016年10月26日 下午4:31:45
 */
public class CalExpItem {
	/** 代码 */
	private String code;
	/** 名称 */
	private String name;
	/** 前端页面是否可见 */
	private boolean isVisible;
	/** 公式校验用 */
	private int check;

	public CalExpItem() {
		super();
	}

	public CalExpItem(String name, boolean isVisible, int check) {
		super();
		this.name = name;
		this.isVisible = isVisible;
		this.check = check;
	}

	public CalExpItem(String code, String name, boolean isVisible) {
		super();
		this.code = code;
		this.name = name;
		this.isVisible = isVisible;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isVisible() {
		return isVisible;
	}

	/** 此方法必须加上，否则jsp页面报错！！！ */
	public boolean getVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}
}
