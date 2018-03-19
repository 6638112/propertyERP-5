package com.cnfantasia.server.ms.revenue.web;

import java.math.BigInteger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.entity.UserWithPropCompany;
import com.cnfantasia.server.ms.revenue.service.IRevenueService;

@Controller
public class RevenueConfigCommonController {
	@Resource
	private IRevenueService revenueService;
	
	public UserWithPropCompany getUserWithPropCompanyByUserId(HttpServletRequest request){
		BigInteger omsUserId = UserContext.getOperIdBigIntegerMustExist();
		HttpSession session = request.getSession(true);
		UserWithPropCompany s_uwp = (UserWithPropCompany)session.getAttribute("s_uwp");
		if(s_uwp==null){
			s_uwp = revenueService.getUserWithPropCompanyByUserId(omsUserId);
			session.setAttribute("s_uwp", s_uwp);
			session.setAttribute("companyName", s_uwp.getPropertyCompanyName());
			session.setAttribute("companyId", s_uwp.getPropertyCompanyId());
		}
		return s_uwp;
	}
	
	public String getParamCompanyName(HttpServletRequest request,String paramKey){
		UserWithPropCompany s_uwp = getUserWithPropCompanyByUserId(request);
		if(s_uwp.getUserRole().compareTo(RevenueDict.UserRole.PropertyCompany)==0){
			return s_uwp.getPropertyCompanyName();
		}else{
			return ParamUtils.getStringTrim(request, paramKey);
		}
	}
	
	public BigInteger getParamCompanyId(HttpServletRequest request,String paramKey){
		UserWithPropCompany s_uwp = getUserWithPropCompanyByUserId(request);
		if(s_uwp.getUserRole().compareTo(RevenueDict.UserRole.PropertyCompany)==0){
			return s_uwp.getPropertyCompanyId();
		}else{
			return  ParamUtils.getBigInteger(request, paramKey, null);//权限角色判断
		}
	}
	
	
//	public Set<UserRole> getUserRoleListByUserId(HttpServletRequest request,UserRole expectRole){
//		BigInteger omsUserId = UserContext.getOperIdBigIntegerMustExist();
//		HttpSession session = request.getSession(true);
//		@SuppressWarnings("unchecked")
//		Set<UserRole> s_roleList = (Set<UserRole>)session.getAttribute("s_roleList");
//		if(s_roleList==null){
//			Set<UserRole> userRoleList = revenueService.getOmsUserRoleListByUserId(omsUserId);
//			session.setAttribute("s_roleList", userRoleList);
//		}
//		return s_roleList;
//	}
	
	
}
