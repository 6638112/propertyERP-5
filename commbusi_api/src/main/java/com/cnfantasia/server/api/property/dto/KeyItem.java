package com.cnfantasia.server.api.property.dto;

public class KeyItem {

	private String name;
	private Object value;
	/** 是否着色 */
	private boolean isColor;
	/** 后面是否追加横线 */
	private boolean isAppendLine;
	/** 是否显示明细（如：上次度数、本次读数） *//*
	private boolean isShowDetail;
	*//** 抄水表开始值 *//*
	private String startValue;
	*//** 抄水表结束值 *//*
	private String endValue;*/

	public KeyItem() {
		super();
	}
	
	public KeyItem(String name, Object value, boolean isColor,
			boolean isAppendLine) {
		super();
		this.name = name;
		this.value = value;
		this.isColor = isColor;
		this.isAppendLine = isAppendLine;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public boolean isColor() {
		return isColor;
	}

	public void setColor(boolean isColor) {
		this.isColor = isColor;
	}

	public boolean isAppendLine() {
		return isAppendLine;
	}

	public void setAppendLine(boolean isAppendLine) {
		this.isAppendLine = isAppendLine;
	}

}
