package com.cnfantasia.server.ms.revenue.entity;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.domainbase.propertyCompany.entity.PropertyCompany;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict.UserRole;

/**
 * 用户信息及
 * 其管理的物业公司信息
 * @author shiyl
 *
 */
public class UserWithPropCompany extends OmsUser{
	private static final long serialVersionUID = 1L;
	/**物业公司Id*/
	private PropertyCompany propertyCompany;
//	public PropertyCompany getPropertyCompany() {
//		return propertyCompany;
//	}
	public void setPropertyCompany(PropertyCompany propertyCompany) {
		this.propertyCompany = propertyCompany;
	}
	
	/**
	 * 1:管理员,2物业公司角色,3其它角色
	 * @return
	 */
	public UserRole getUserRole(){
		if(getIsadmin()!=null&&getIsadmin().equals(1)){
			return RevenueDict.UserRole.SysAdmin;
		}else{
			if(propertyCompany!=null&&propertyCompany.getId()!=null){
				return RevenueDict.UserRole.PropertyCompany;
			}else{
				return RevenueDict.UserRole.Default;
			}
		}
	}
	
	public BigInteger getPropertyCompanyId(){
		return propertyCompany==null?null:propertyCompany.getId();
	}
	
	public String getPropertyCompanyName(){
		return propertyCompany==null?null:propertyCompany.getName();
	}
	
//	private static String IFNULL(String first,String second){
//		if(StringUtils.isEmpty(first)){
//			return second;
//		}
//		return first;
//	}
}
