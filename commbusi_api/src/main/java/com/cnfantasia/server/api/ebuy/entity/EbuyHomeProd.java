package com.cnfantasia.server.api.ebuy.entity;

import java.io.Serializable;
import java.util.List;

public class EbuyHomeProd implements Serializable {
	
	public static int HOME_TYPE_PACKET = 1;
	
	public static int HOME_TYPE_COMMON = 2;

	private static final long serialVersionUID = -7956753371357372918L;
	
	private Long id;
	
//	private String homeType;
	
	private String name1;
	
	private String name2;
	
	private String name3;
	
	private Integer sex;
	
	private String color;
	
	@SuppressWarnings("unused")
	private Integer sort;
	
	private List<EbuyProdForLst> ebuyProdForLstList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHomeType() {
		// 0男，1女，3未知性别
		if(sex == null) {
			return name3;
		} else if(sex == 0) {
			return name1;
		} else if(sex == 1) {
			return name2;
		}
		return name3;
	}

//	public void setHomeType(String homeType) {
//		this.homeType = homeType;
//	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getName3() {
		return name3;
	}

	public void setName3(String name3) {
		this.name3 = name3;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<EbuyProdForLst> getEbuyProdForLstList() {
		return ebuyProdForLstList;
	}

	public void setEbuyProdForLstList(List<EbuyProdForLst> ebuyProdForLstList) {
		this.ebuyProdForLstList = ebuyProdForLstList;
	}

//	public Integer getSort() {
//		return sort;
//	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
