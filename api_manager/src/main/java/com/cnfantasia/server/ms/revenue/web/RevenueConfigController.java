package com.cnfantasia.server.ms.revenue.web;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.exception.ExceptionParseUtil;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.revenue.entity.AreaTime;
import com.cnfantasia.server.ms.revenue.entity.LeftRightTime;
import com.cnfantasia.server.ms.revenue.entity.PropCompanyWithRevenue;
import com.cnfantasia.server.ms.revenue.entity.RevenueConfigDetail;
import com.cnfantasia.server.ms.revenue.entity.RevenueConfigForList;
import com.cnfantasia.server.ms.revenue.service.IRevenueService;

/**
 * 收益配置
 * @author shiyl
 *
 */
@Controller
@RequestMapping("/revenueConfig")
public class RevenueConfigController extends BaseController{
	@Resource
	private IRevenueService revenueService;
	@Resource
	private RevenueConfigCommonController revenueConfigCommonController;
	
	
	/**
	 * 配置概要预览列表信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/previewList.html")
	public ModelAndView previewList(HttpServletRequest request){
		//1.搜集参数
		BigInteger companyId = revenueConfigCommonController.getParamCompanyId(request, "companyId");
		String companyName = revenueConfigCommonController.getParamCompanyName(request, "companyName");
		PageModel pageModel = ControllerUtils.getPageModel(request,1,10);
		//2.交互
		List<PropCompanyWithRevenue> resList = revenueService.getPropCompanyWithRevenueList(companyId,companyName, pageModel);
		//3.结果处理
		request.setAttribute("resList", resList);
		request.setAttribute("page", pageModel.toMap());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/revenueConfig/previewList");
		return mav;
	}
	
	
	/**
	 * 配置列表信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/configList.html")
	public ModelAndView configList(HttpServletRequest request){
		//1.搜集参数
		BigInteger companyId = revenueConfigCommonController.getParamCompanyId(request, "companyId");
		String companyName = revenueConfigCommonController.getParamCompanyName(request, "companyName");
		
		Integer projectType = ParamUtils.getInteger(request, "projectType", null);
		PageModel pageModel = ControllerUtils.getPageModel(request,1,5);
		//2.交互
		List<RevenueConfigForList> resList = revenueService.getRevenueConfigList(companyId, companyName, projectType, pageModel);
		//3.结果处理
		request.setAttribute("resList", resList);
		request.setAttribute("page", pageModel.toMap());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/revenueConfig/configList");
		return mav;
	}
	
	/**
	 * 配置新增页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/configAdd.html")
	public ModelAndView configAdd(HttpServletRequest request){
		revenueConfigCommonController.getUserWithPropCompanyByUserId(request);
		//1.搜集参数
		BigInteger companyId = revenueConfigCommonController.getParamCompanyId(request, "companyId");
		String companyName = revenueConfigCommonController.getParamCompanyName(request, "companyName");
		//2.交互
		//3.结果处理
		request.setAttribute("companyId", companyId);
		request.setAttribute("companyName", companyName);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/revenueConfig/configAdd");
		return mav;
	}
	
	/**
	 * 配置修改页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/configUpd.html")
	public ModelAndView configUpd(HttpServletRequest request){
		BigInteger companyId = revenueConfigCommonController.getParamCompanyId(request, null);
		//1.搜集参数
		BigInteger dataId = ParamUtils.getBigInteger(request, "dataId", null);
		//2.交互
		RevenueConfigDetail detail = revenueService.getRevenueConfigDetail(dataId);//根据Id查询详情
		BigInteger detailCompanyId = detail==null?null:detail.getCompanyId();
		Integer detailProjectType = detail==null?null:detail.getProjectType();
		AreaTime leftTime = null;
		AreaTime rightTime = null;
		if(detail==null){
			throw new BusinessRuntimeException("revenueConfig.configUpd.detailNull");
		}else if(companyId!=null&&companyId.compareTo(detailCompanyId)!=0){
			throw new BusinessRuntimeException("revenueConfig.configUpd.detailNEqual");
		}else{
			LeftRightTime areaInfo = revenueService.getLeftRightTime(detailCompanyId, detailProjectType, detail);
			leftTime = areaInfo.getLeftTime();
			rightTime = areaInfo.getRightTime();
		}
		//3.结果处理
		request.setAttribute("detail", detail);
		request.setAttribute("leftTime", leftTime);
		request.setAttribute("rightTime", rightTime);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/revenueConfig/configUpd");
		return mav;
	}


	@Override
	public ModelAndView handleException(Exception ex, HttpServletRequest request) {
		if(BusinessRuntimeException.class.equals(ex.getClass()) || ValidateRuntimeException.class.equals(ex.getClass())){//业务处理异常
			String errouMsg = ExceptionParseUtil.parseErrorMsg(ex, true);
			request.setAttribute("errouMsg", errouMsg);
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/revenueConfig/showError");
			return mav;
		}
		return super.handleException(ex, request);
	}
	
	
}
