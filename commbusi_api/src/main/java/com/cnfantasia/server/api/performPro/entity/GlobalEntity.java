/**   
* Filename:    GlobalEntity.java   
* @version:    1.0  
* Create at:   2015年7月9日 上午1:41:21   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年7月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.performPro.entity;

/**
 * Filename:    GlobalEntity.java
 * @version:    1.0.0
 * Create at:   2015年7月9日 上午1:41:21
 * Description:全局变量实体类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年7月9日       shiyl             1.0             1.0 Version
 */
public class GlobalEntity {
	
	private Boolean g_IsFamalyMember;//是否有其他家庭成员	
	private Boolean g_IsMultDevice;//是否有多个设备	
	private Boolean g_IsBizType;//商家类别是否有更新
	private Long g_bizTypeUpdTime;//商家类别最近更新时间
	private Integer g_DiscountNum;//剩余抽奖次数	
	private Boolean g_IsVersion;//是否有新版本	
	private Boolean g_IsForceUpd;//客户端是否需要强制更新	
	private String g_DownloadAdress;//新版本下载地址
	private String g_VersionDesc;//版本描述
	private Boolean g_IsNewUser;//是否为新注册用户	
	
	public GlobalEntity(){}
	public GlobalEntity(Boolean g_IsFamalyMember,Boolean g_IsMultDevice,Boolean g_IsBizType,Long g_bizTypeUpdTime
			,Integer g_DiscountNum,Boolean g_IsVersion,Boolean g_IsForceUpd,String g_DownloadAdress,String g_VersionDesc,Boolean g_IsNewUser){
		this.g_IsFamalyMember = g_IsFamalyMember;
		this.g_IsMultDevice = g_IsMultDevice;
		this.g_IsBizType = g_IsBizType;
		this.g_bizTypeUpdTime = g_bizTypeUpdTime;
		this.g_DiscountNum = g_DiscountNum;
		this.g_IsVersion = g_IsVersion;
		this.g_IsForceUpd = g_IsForceUpd;
		this.g_DownloadAdress = g_DownloadAdress;
		this.g_VersionDesc = g_VersionDesc;
		this.g_IsNewUser = g_IsNewUser;
	}
	
	public Boolean getG_IsFamalyMember() {
		return g_IsFamalyMember;
	}
	public void setG_IsFamalyMember(Boolean g_IsFamalyMember) {
		this.g_IsFamalyMember = g_IsFamalyMember;
	}
	public Boolean getG_IsMultDevice() {
		return g_IsMultDevice;
	}
	public void setG_IsMultDevice(Boolean g_IsMultDevice) {
		this.g_IsMultDevice = g_IsMultDevice;
	}
	public Boolean getG_IsBizType() {
		return g_IsBizType;
	}
	public void setG_IsBizType(Boolean g_IsBizType) {
		this.g_IsBizType = g_IsBizType;
	}
	public Integer getG_DiscountNum() {
		return g_DiscountNum;
	}
	public void setG_DiscountNum(Integer g_DiscountNum) {
		this.g_DiscountNum = g_DiscountNum;
	}
	public Boolean getG_IsVersion() {
		return g_IsVersion;
	}
	public void setG_IsVersion(Boolean g_IsVersion) {
		this.g_IsVersion = g_IsVersion;
	}
	public Boolean getG_IsForceUpd() {
		return g_IsForceUpd;
	}
	public void setG_IsForceUpd(Boolean g_IsForceUpd) {
		this.g_IsForceUpd = g_IsForceUpd;
	}
	public String getG_DownloadAdress() {
		return g_DownloadAdress;
	}
	public void setG_DownloadAdress(String g_DownloadAdress) {
		this.g_DownloadAdress = g_DownloadAdress;
	}
	public Boolean getG_IsNewUser() {
		return g_IsNewUser;
	}
	public void setG_IsNewUser(Boolean g_IsNewUser) {
		this.g_IsNewUser = g_IsNewUser;
	}
	public String getG_VersionDesc() {
		return g_VersionDesc;
	}
	public void setG_VersionDesc(String g_VersionDesc) {
		this.g_VersionDesc = g_VersionDesc;
	}
	public Long getG_bizTypeUpdTime() {
		return g_bizTypeUpdTime;
	}
	public void setG_bizTypeUpdTime(Long g_bizTypeUpdTime) {
		this.g_bizTypeUpdTime = g_bizTypeUpdTime;
	}
	
}
