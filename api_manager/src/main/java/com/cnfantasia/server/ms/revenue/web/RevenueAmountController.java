package com.cnfantasia.server.ms.revenue.web;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domainbase.channelPartner.entity.ChannelPartner;
import com.cnfantasia.server.domainbase.propertyCompany.entity.PropertyCompany;
import com.cnfantasia.server.ms.channelPartner.service.IChannelPartnerService;
import com.cnfantasia.server.ms.propertyCompany.service.IPropertyCompanyService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict.MiniRoleType;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict.UserRole;
import com.cnfantasia.server.ms.revenue.entity.BeginEndDate;
import com.cnfantasia.server.ms.revenue.entity.RevenueAmountPrevEntity;
import com.cnfantasia.server.ms.revenue.entity.RevenueApplyParam;
import com.cnfantasia.server.ms.revenue.service.IRevenueService;

/**
 * 收益金额管理
 * @author shiyl
 *
 */
@Controller
@RequestMapping("/revenueAmount")
public class RevenueAmountController extends BaseController{
	@Resource
	private IRevenueService revenueService;
	@Resource
	private RevenueConfigCommonController revenueConfigCommonController;
	@Resource
	private IPropertyCompanyService propertyCompanyService;
	@Resource
	private IChannelPartnerService channelPartnerService;
	
	/**
	 * 收益金额预览列表信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/previewListAdmin.html")
	public ModelAndView previewListAdmin(HttpServletRequest request){
		return previewListBase(request,RevenueDict.MiniRoleType.SysAdmin,"/revenueAmount/previewListAdmin.html");
	}
	@RequestMapping("/previewListPropertyAgent.html")
	public ModelAndView previewListPropertyAgent(HttpServletRequest request){
		return previewListBase(request,RevenueDict.MiniRoleType.PropertyAgent,"/revenueAmount/previewListPropertyAgent.html");
	}
	@RequestMapping("/previewListPropertyCompany.html")
	public ModelAndView previewListPropertyCompany(HttpServletRequest request){
		return previewListBase(request,RevenueDict.MiniRoleType.PropertyCompany,"/revenueAmount/previewListPropertyCompany.html");
	}
	@RequestMapping("/previewListFinancer.html")
	public ModelAndView previewListFinancer(HttpServletRequest request){
		return previewListBase(request,RevenueDict.MiniRoleType.Financer,"/revenueAmount/previewListFinancer.html");
	}
	@RequestMapping("/previewListEbuy.html")
	public ModelAndView previewListEbuy(HttpServletRequest request){
		return previewListBase(request,RevenueDict.MiniRoleType.DownstairStore,"/revenueAmount/previewListEbuy.html");
	}
	@RequestMapping("/previewListManagement.html")
	public ModelAndView previewListManagement(HttpServletRequest request){
		return previewListBase(request,RevenueDict.MiniRoleType.PCManagement,"/revenueAmount/previewListManagement.html");
	}
//	@RequestMapping("/previewListDredge.html")
//	public ModelAndView previewListDredge(HttpServletRequest request){
//		return previewListBase(request,RevenueDict.MiniRoleType.DownstairStore,"/revenueAmount/previewListDredge.html");
//	}
	private ModelAndView previewListBase(HttpServletRequest request,Integer role,String formUrl){
		request.setAttribute("formUrl", formUrl);
//		BigInteger companyId = revenueConfigCommonController.getParamCompanyId(request, "companyId");
		String searchName = ParamUtils.getStringTrim(request, "searchName");
		PageModel pageModel = ControllerUtils.getPageModel(request,1,10);   
		BigInteger omsUserId = UserContext.getOperIdBigIntegerMustExist();
		//2.交互
		List<RevenueAmountPrevEntity> resList = revenueService.getRevenueAmountPrevList(omsUserId, UserRole.createUserRole(role), searchName, pageModel);
		
		// 物业、代理商，检查银行卡信息是否为空
		checkBankInfo(role, request); //FIXME 检查管理处银行卡信息
		
		//3.结果处理
		request.setAttribute("resList", resList);
		request.setAttribute("role", role);
		request.setAttribute("page", pageModel.toMap());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/revenueAmount/previewList");
		return mav;
	}
	
	/**
	 * 物业、代理商、管理处，检查银行卡信息是否为空
	 * 
	 * @param role
	 * @param request
	 */
	private void checkBankInfo(Integer role, HttpServletRequest request){
		if(RevenueDict.MiniRoleType.PropertyCompany.compareTo(role)==0 || RevenueDict.MiniRoleType.PropertyAgent.compareTo(role)==0){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			boolean isNull = false;
			if(RevenueDict.MiniRoleType.PropertyCompany.compareTo(role)==0){// 物业公司
				paramMap.put("adminId", UserContext.getOperIdBigInteger());
				List<PropertyCompany> propertyCompanys = propertyCompanyService.getPropertyCompanyByCondition(paramMap);
				if(propertyCompanys!=null){
					for(PropertyCompany propertyCompany:propertyCompanys){
						if(propertyCompany!=null && (propertyCompany.getAccountNo()==null || propertyCompany.getAccountNo().trim().length()==0)){
							isNull = true;
							break;
						} 
					}
				}
				if(isNull){
					request.setAttribute("bank_msg", "请先到物业工作台完善银行卡信息，以免影响提款！");
					request.setAttribute("bank_url", "/propertyCompany/workbench.html");
				}
			} else if(RevenueDict.MiniRoleType.PropertyAgent.compareTo(role)==0){// 代理
				paramMap.put("tOmsUserFId", UserContext.getOperIdBigInteger());
				List<ChannelPartner> channelPartners = channelPartnerService.getChannelPartnerByCondition(paramMap);
				for(ChannelPartner channelPartner:channelPartners){
					if(channelPartner!=null && (channelPartner.getBankCardNo()==null || channelPartner.getBankCardNo().trim().length()==0)){
						isNull = true;
						break;
					} 
				}
				if(isNull){
					request.setAttribute("bank_msg", "请先到代理商维护中完善银行卡信息，以免影响提款！");
					request.setAttribute("bank_url", "/channelPartner/cpCompanyView.html");
				}
			} 
		}
	}
	
	/**
	 * 物业、代理检查银行卡信息是否为空
	 * 
	 * @param miniRoleId
	 * @param miniRoleType
	 * @return
	 */
	@RequestMapping("/checkBankInfo.html")
	@ResponseBody
	public JsonResponse checkBankInfo(BigInteger miniRoleId, Integer miniRoleType){
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setStatus(CommConstants.ResponseStatus.SUCCESS);
		// 如果为物业，银行卡信息为空，则跳转提示
		if(RevenueDict.MiniRoleType.PropertyCompany.compareTo(miniRoleType)==0){// 物业
			PropertyCompany propertyCompany = propertyCompanyService.getPropertyCompanyBySeqId(miniRoleId);
			if(propertyCompany!=null && (propertyCompany.getAccountNo()==null || propertyCompany.getAccountNo().trim().length()==0)){
				jsonResponse.setMessage("申请提款失败，请先到物业工作台完善银行卡信息!");
				jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
			} 
		} else if(RevenueDict.MiniRoleType.PropertyAgent.compareTo(miniRoleType)==0){// 代理
			ChannelPartner channelPartner = channelPartnerService.getChannelPartnerBySeqId(miniRoleId);
			if(channelPartner!=null && (channelPartner.getBankCardNo()==null || channelPartner.getBankCardNo().trim().length()==0)){
				jsonResponse.setMessage("申请提款失败，请先到代理商维护中完善银行卡信息!");
				jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
			} 
		}
		
		return jsonResponse;  
	}
	
	/**
	 * 跳转到确认提款页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toApplyRevenue.html")
	public ModelAndView toApplyRevenue(HttpServletRequest request){
		//1.搜集参数
//		BigInteger omsUserId = UserContext.getOperIdBigIntegerMustExist();
//		UserRole expectedRole = UserRole.PropertyCompany;
		UserContext.getOperIdBigIntegerMustExist();
		Integer projectType = ParamUtils.getInteger(request, "projectType", null);
		BigInteger miniRoleId = ParamUtils.getBigInteger(request, "roleId", null);
		Integer miniRoleType = ParamUtils.getInteger(request, "roleType", null);
		
		//2.交互
		RevenueApplyParam applyData = revenueService.getRevenueApplyParam(miniRoleId, miniRoleType, projectType);
		//3.结果处理
		String formUrl = "";
		if(miniRoleType!=null&&miniRoleType.compareTo(RevenueDict.MiniRoleType.PropertyCompany)==0){
			formUrl = "/revenueAmountJson/applyRevenuePropertyCompany.json";
		} else if(miniRoleType!=null&&miniRoleType.compareTo(RevenueDict.MiniRoleType.DownstairStore)==0){
			formUrl = "/revenueAmountJson/applyRevenueEbuy.json";
		} else if(miniRoleType!=null&&miniRoleType.compareTo(RevenueDict.MiniRoleType.PCManagement)==0) {
			formUrl = "/revenueAmountJson/applyRevenuePropertyCompany.json";
		} else {
			throw new BusinessRuntimeException("RevenueAmount.toApplyRevenue.roleType.notSupport");
		}
		request.setAttribute("formUrl", formUrl);
		request.setAttribute("applyData", applyData);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/revenueAmount/revenueApply");
		return mav;
	}
	
	
	/**
	 * 标记历史数据为已结算
	 * @param request
	 * @return
	 */
	@RequestMapping("/toMarkPage.html")
	public ModelAndView toMarkPage(HttpServletRequest request){
		//1.搜集参数
		BigInteger companyId = ParamUtils.getBigInteger(request, "companyId", null);
		//2.交互
		BeginEndDate beginEndDate = revenueService.getPropertyRealPayHistoryMonth(companyId);
		//3.结果处理
		String minMonth = "";
		String maxMonth = "";
		boolean haveMonth = false;
		if(beginEndDate!=null){
			minMonth = beginEndDate.getBegin(DateUtil.formatMonth_Split.get());
			maxMonth = beginEndDate.getEnd(DateUtil.formatMonth_Split.get());
			if(minMonth!=null&&maxMonth!=null){haveMonth = true;}
		}
		request.setAttribute("haveMonth", haveMonth);
		request.setAttribute("minMonth", minMonth);
		request.setAttribute("maxMonth", maxMonth);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/revenueAmount/markPage");
		return mav;
	}
	
	
}
