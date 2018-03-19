package com.cnfantasia.server.domainbase.ebuyHomeType.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(首页分类) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyHomeType implements Serializable{
*/


public class EbuyHomeType extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 名称，对应女 */	private String name1;	/** 名称,对应性别男 */	private String name2;	/** 名称，对应性别不详显示的运营主题名称 */	private String name3;	/** 排序 */	private String tSort;	/** 类型 */	private Integer type;	/** 显示颜色 */	private String color;	/** 0男;1女;2不分性别 */	private Integer sex;
	public EbuyHomeType(){
	}
	public EbuyHomeType(EbuyHomeType arg){
		this.id = arg.getId();		this.name1 = arg.getName1();		this.name2 = arg.getName2();		this.name3 = arg.getName3();		this.tSort = arg.gettSort();		this.type = arg.getType();		this.color = arg.getColor();		this.sex = arg.getSex();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param name1 名称，对应女	 * @param name2 名称,对应性别男	 * @param name3 名称，对应性别不详显示的运营主题名称	 * @param tSort 排序	 * @param type 类型	 * @param color 显示颜色	 * @param sex 0男;1女;2不分性别	 * @param sys0AddTime 	 * @param sys0UpdTime 	 * @param sys0DelTime 	 * @param sys0AddUser 	 * @param sys0UpdUser 	 * @param sys0DelUser 	 * @param sys0DelState 	 */
	public EbuyHomeType(BigInteger id,String name1,String name2,String name3,String tSort,Integer type,String color,Integer sex,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.name1 = name1;		this.name2 = name2;		this.name3 = name3;		this.tSort = tSort;		this.type = type;		this.color = color;		this.sex = sex;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("name1=").append(name1).append(";");		sbf.append("name2=").append(name2).append(";");		sbf.append("name3=").append(name3).append(";");		sbf.append("tSort=").append(tSort).append(";");		sbf.append("type=").append(type).append(";");		sbf.append("color=").append(color).append(";");		sbf.append("sex=").append(sex).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getName1() {		return name1;	}	public void setName1(String name1) {		this.name1 = name1;	}	public String getName2() {		return name2;	}	public void setName2(String name2) {		this.name2 = name2;	}	public String getName3() {		return name3;	}	public void setName3(String name3) {		this.name3 = name3;	}	public String gettSort() {		return tSort;	}	public void settSort(String tSort) {		this.tSort = tSort;	}	public Integer getType() {		return type;	}	public void setType(Integer type) {		this.type = type;	}	public String getColor() {		return color;	}	public void setColor(String color) {		this.color = color;	}	public Integer getSex() {		return sex;	}	public void setSex(Integer sex) {		this.sex = sex;	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EbuyHomeType other = (EbuyHomeType) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
