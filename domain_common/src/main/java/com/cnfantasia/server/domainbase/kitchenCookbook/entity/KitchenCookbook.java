package com.cnfantasia.server.domainbase.kitchenCookbook.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;import java.lang.Long;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(厨房菜谱) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class KitchenCookbook implements Serializable{
*/


public class KitchenCookbook extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 菜谱名称 */	private String name;	/** 图片描述地址 */	private String picUrl;	/** 菜谱描述 */	private String desc;
    /** 功效 */
    private String effect;	/** 份量 */	private String eatWeight;	/** 亨饪时间 */	private String cookTime;	/** 亨饪步骤 */	private String cookStep;	/** 口感 */	private String taste;	/** 工艺 */	private String cookTech;	/** 小贴士 */	private String tips;	/** 菜谱上传时间 */	private String createTime;	/** 总的点赞数 */	private Long totalSupportCount;
	public KitchenCookbook(){
	}
	public KitchenCookbook(KitchenCookbook arg){
		this.id = arg.getId();		this.name = arg.getName();		this.picUrl = arg.getPicUrl();		this.desc = arg.getDesc();
        this.effect = arg.getEffect();		this.eatWeight = arg.getEatWeight();		this.cookTime = arg.getCookTime();		this.cookStep = arg.getCookStep();		this.taste = arg.getTaste();		this.cookTech = arg.getCookTech();		this.tips = arg.getTips();		this.createTime = arg.getCreateTime();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.totalSupportCount = arg.getTotalSupportCount();
	}
	/**	 * 	 * @param id 	 * @param name 菜谱名称	 * @param picUrl 图片描述地址	 * @param desc 菜谱描述	 * @param eatWeight 份量	 * @param cookTime 亨饪时间	 * @param cookStep 亨饪步骤	 * @param taste 口感	 * @param cookTech 工艺	 * @param tips 小贴士	 * @param createTime 菜谱上传时间	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param totalSupportCount 总的点赞数	 */
	public KitchenCookbook(BigInteger id,String name,String picUrl,String desc,String effect,String eatWeight,String cookTime,String cookStep,String taste,String cookTech,String tips,String createTime,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,Long totalSupportCount){
		this.id = id;		this.name = name;		this.picUrl = picUrl;		this.desc = desc;
        this.effect = effect;		this.eatWeight = eatWeight;		this.cookTime = cookTime;		this.cookStep = cookStep;		this.taste = taste;		this.cookTech = cookTech;		this.tips = tips;		this.createTime = createTime;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.totalSupportCount = totalSupportCount;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("picUrl=").append(picUrl).append(";");		sbf.append("desc=").append(desc).append(";");
        sbf.append("effect=").append(effect).append(";");		sbf.append("eatWeight=").append(eatWeight).append(";");		sbf.append("cookTime=").append(cookTime).append(";");		sbf.append("cookStep=").append(cookStep).append(";");		sbf.append("taste=").append(taste).append(";");		sbf.append("cookTech=").append(cookTech).append(";");		sbf.append("tips=").append(tips).append(";");		sbf.append("createTime=").append(createTime).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("totalSupportCount=").append(totalSupportCount).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getPicUrl() {		return picUrl;	}	public void setPicUrl(String picUrl) {		this.picUrl = picUrl;	}	public String getDesc() {		return desc;	}	public void setDesc(String desc) {		this.desc = desc;	}
    public String getEffect() {
        return effect;
    }
    public void setEffect(String effect) {
        this.effect = effect;
    }	public String getEatWeight() {		return eatWeight;	}	public void setEatWeight(String eatWeight) {		this.eatWeight = eatWeight;	}	public String getCookTime() {		return cookTime;	}	public void setCookTime(String cookTime) {		this.cookTime = cookTime;	}	public String getCookStep() {		return cookStep;	}	public void setCookStep(String cookStep) {		this.cookStep = cookStep;	}	public String getTaste() {		return taste;	}	public void setTaste(String taste) {		this.taste = taste;	}	public String getCookTech() {		return cookTech;	}	public void setCookTech(String cookTech) {		this.cookTech = cookTech;	}	public String getTips() {		return tips;	}	public void setTips(String tips) {		this.tips = tips;	}	public String getCreateTime() {		return createTime;	}	public void setCreateTime(String createTime) {		this.createTime = createTime;	}	public Long getTotalSupportCount() {		return totalSupportCount;	}	public void setTotalSupportCount(Long totalSupportCount) {		this.totalSupportCount = totalSupportCount;	}
	
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
		KitchenCookbook other = (KitchenCookbook) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
