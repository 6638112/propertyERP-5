package com.cnfantasia.server.ms.pub.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;

/**
 * 描述:系统用户表 //TODO 注意HashCode和equals方法
 * 
 * @version 1.00
 * @author syl
 * 
 */
public class SysUser implements Serializable{
	private static final long serialVersionUID = 1L;
	public SysUser(OmsUser loginNoEntity){
		this.loginNoEntity = loginNoEntity;
		this.isAdmin = false;
	}
	
	/**登录信息存储*/
	private OmsUser loginNoEntity;
	
	/** 是否为超级管理员标识 true.是 false.否 */
  private boolean isAdmin = false;
	/** 用户角色集合 */
  private List<BigInteger> roleIds;
  
  	private List<EbuySupplyMerchant> supplyMerchantList;
  	
  	private BigInteger propertyCompanyAdminId;
  	
  	private BigInteger sourceUserId;
  	
  	List<BigInteger> gbIdList;
  	
  	private BigInteger propertyCompanyId;
  	
	/**
	 * 物业公司的合作状态：1初级，2高级，3全面
	 */
	private int cooperationType = 0;

	public int getCooperationType() {
		return cooperationType;
	}

	public void setCooperationType(int cooperationType) {
		this.cooperationType = cooperationType;
	}
	public OmsUser getOmsUser() {
		return loginNoEntity;
	}
	public void setOmsUser(OmsUser loginNoEntity) {
		this.loginNoEntity = loginNoEntity;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public List<BigInteger> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<BigInteger> roleIds) {
		this.roleIds = roleIds;
	}
	/**
	 * @return
	 */
	public String getUsername() {
		return loginNoEntity.getUserAccount();
	}
	/**
	 * @return
	 */
	public String getPassword() {
		return loginNoEntity.getPassword();
	}
	public List<EbuySupplyMerchant> getSupplyMerchantList() {
		return supplyMerchantList;
	}
	public void setSupplyMerchantList(List<EbuySupplyMerchant> supplyMerchantList) {
		this.supplyMerchantList = supplyMerchantList;
	}

	public BigInteger getPropertyCompanyAdminId() {
		return propertyCompanyAdminId;
	}

	public void setPropertyCompanyAdminId(BigInteger propertyCompanyAdminId) {
		this.propertyCompanyAdminId = propertyCompanyAdminId;
	}

	public BigInteger getSourceUserId() {
		return sourceUserId;
	}

	public void setSourceUserId(BigInteger sourceUserId) {
		this.sourceUserId = sourceUserId;
	}

	/**
	 * 返回有权小区idList
	 * @return 
	 * 分三种情况：<br>
	 * 1)OOS管理员，返回null；<br>
	 * 2)物业或代理账号，返回其所管辖小区idList，其size>=0; <br>
	 * 3)OOS运营人员，isadmin是false，返回gbIdList.size()=0 <br>
	 */
	public List<BigInteger> getGbIdList() {
		/*if((gbIdList == null || gbIdList.size() < 0) && !isAdmin) {
			gbIdList = new ArrayList<BigInteger>();
		}*/
		//运营后台管理员进来，是没有gbIdList的，这种情况无需验权，直接返回null，sql里对null有忽略处理  added by wenfq 2017-03-07 
		if(this.loginNoEntity.getIsadmin() == 1 && gbIdList.size() == 0){
			return null;
		}
		
		return gbIdList;
	}

	public void setGbIdList(List<BigInteger> gbIdList) {
		this.gbIdList = gbIdList;
	}

	public BigInteger getPropertyCompanyId() {
		return propertyCompanyId;
	}

	public void setPropertyCompanyId(BigInteger propertyCompanyId) {
		this.propertyCompanyId = propertyCompanyId;
	}

}
