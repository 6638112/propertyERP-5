package com.cnfantasia.server.ms.communitySupply.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.msg.common.util.ShortMsgUtil;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.commonBusiness.util.SendSmsRunnable;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domainbase.communitySupplyBelong.entity.CommunitySupplyBelong;
import com.cnfantasia.server.domainbase.communitySupplyBelong.service.CommunitySupplyBelongBaseService;
import com.cnfantasia.server.domainbase.communitySupplyCompanyPic.entity.CommunitySupplyCompanyPic;
import com.cnfantasia.server.domainbase.communitySupplyCompanyPic.service.ICommunitySupplyCompanyPicBaseService;
import com.cnfantasia.server.domainbase.communitySupplyContect.entity.CommunitySupplyContect;
import com.cnfantasia.server.domainbase.communitySupplyContect.service.ICommunitySupplyContectBaseService;
import com.cnfantasia.server.domainbase.communitySupplyPic.entity.CommunitySupplyPic;
import com.cnfantasia.server.domainbase.communitySupplyPic.service.ICommunitySupplyPicBaseService;
import com.cnfantasia.server.domainbase.communitySupplyTmp.entity.CommunitySupplyTmp;
import com.cnfantasia.server.domainbase.communitySupplyTmp.service.ICommunitySupplyTmpBaseService;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;
import com.cnfantasia.server.domainbase.groupBuilding.service.IGroupBuildingBaseService;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.domainbase.omsUser.service.IOmsUserBaseService;
import com.cnfantasia.server.domainbase.propertySuggestCommsupply.entity.PropertySuggestCommsupply;
import com.cnfantasia.server.domainbase.propertySuggestCommsupply.service.PropertySuggestCommsupplyBaseService;
import com.cnfantasia.server.domainbase.user.entity.User;
import com.cnfantasia.server.domainbase.user.service.IUserBaseService;
import com.cnfantasia.server.ms.communitySupply.constant.CommunitySupplyConstant;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyBelongEntity;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyBelongViewEntity;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyDetailEntity;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyEntity;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyTmpEntity;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyTmpViewEntity;
import com.cnfantasia.server.ms.communitySupply.entity.PropertySuggestCommsupplyEntity;
import com.cnfantasia.server.ms.communitySupply.service.ICommunitySupplyService;
import com.cnfantasia.server.ms.communitySupplyCompany.entity.CommunitySupplyCompanyEntity;
import com.cnfantasia.server.ms.communitySupplyTmp.constant.CommunitySupplyTmpConstant;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleViewEntity;
import com.cnfantasia.server.ms.groupBuilding.service.IGroupBuildingService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.omsPermiRole.service.IOmsPermiRoleService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.DateUtil;

@Controller
@RequestMapping("/communitySupply")
public class CommunitySupplyController extends BaseController {
	private Log logger = LogFactory.getLog(getClass());
	
	private ICommunitySupplyService communitySupplyService;
	
	private ICommunitySupplyTmpBaseService communitySupplyTmpBaseService;

	private ICommunitySupplyPicBaseService communitySupplyPicBaseService;

	private ICommunitySupplyCompanyPicBaseService communitySupplyCompanyPicBaseService;

	@Resource
	IOmsPermiRoleService omsPermiRoleService;
	@Resource
	IUserBaseService userBaseService;
	@Resource
	IOmsUserBaseService omsUserBaseService;
	@Resource
	IGroupBuildingBaseService groupBuildingBaseService;
	@Resource
	CommunitySupplyBelongBaseService communitySupplyBelongBaseService;
	@Resource
	PropertySuggestCommsupplyBaseService propertySuggestCommsupplyBaseService;
	@Resource
	IGroupBuildingService msGroupBuildingService;
	@Resource
	ICommunitySupplyContectBaseService communitySupplyContectBaseService;

	public void setCommunitySupplyTmpBaseService(ICommunitySupplyTmpBaseService communitySupplyTmpBaseService) {
		this.communitySupplyTmpBaseService = communitySupplyTmpBaseService;
	}

	public void setCommunitySupplyPicBaseService(ICommunitySupplyPicBaseService communitySupplyPicBaseService) {
		this.communitySupplyPicBaseService = communitySupplyPicBaseService;
	}

	public void setCommunitySupplyCompanyPicBaseService(ICommunitySupplyCompanyPicBaseService communitySupplyCompanyPicBaseService) {
		this.communitySupplyCompanyPicBaseService = communitySupplyCompanyPicBaseService;
	}

	/**
	 * 商家类别
	 */
	private List<CommunitySupplyType> communitySupplyTypeList;

	public ICommunitySupplyService getCommunitySupplyService() {
		return communitySupplyService;
	}

	public void setCommunitySupplyService(ICommunitySupplyService communitySupplyService) {
		this.communitySupplyService = communitySupplyService;
	}

	/**
	 * 新增店铺列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/newList.html")
	public ModelAndView csTempList(HttpServletRequest request) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		handleCSTempListOrSearch(request, paramMap);

		if (communitySupplyTypeList == null) {//延迟加载
			paramMap.clear();
			paramMap.put("sys0DelState", 0);
			communitySupplyTypeList = communitySupplyService.getCommunitySupplyTypeByCondition(paramMap);
		}

		request.setAttribute("cstList", communitySupplyTypeList);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupply/communitySupplyTempList");
		return modelAndView;
	}

	/**
	 * 新增店铺列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/belongList.html")
	public ModelAndView csBelongList(HttpServletRequest request) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		handleCSBelongListOrSearch(request, paramMap);

		if (communitySupplyTypeList == null) {//延迟加载
			paramMap.clear();
			paramMap.put("sys0DelState", 0);
			communitySupplyTypeList = communitySupplyService.getCommunitySupplyTypeByCondition(paramMap);
		}

		request.setAttribute("cstList", communitySupplyTypeList);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupply/communitySupplyBelongList");
		return modelAndView;
	}

	/**
	 * 统一处理 （临时申请的店铺） List和Search请求
	 * 
	 * @param request
	 * @param paramMap
	 *            请求参数
	 */
	private void handleCSBelongListOrSearch(HttpServletRequest request, Map<String, Object> paramMap) {
		int resultSize = communitySupplyService.getCommunitySupplyBelongList_forCount(paramMap);
		request.setAttribute("resultSize", resultSize);

		// 页数的参数名 
		String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);

		List<CommunitySupplyBelongEntity> searchRestList = communitySupplyService.getCommunitySupplyBelongList_forPage(curPageIndex, pageSize, paramMap);
		request.setAttribute("resList", searchRestList);
	}

	/**
	 * 列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/list.html")
	public ModelAndView list(HttpServletRequest request) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		handleListOrSearch(request, paramMap);

		if (communitySupplyTypeList == null) {//延迟加载
			paramMap.clear();
			paramMap.put("sys0DelState", 0);
			communitySupplyTypeList = communitySupplyService.getCommunitySupplyTypeByCondition(paramMap);
		}
		request.setAttribute("cstList", communitySupplyTypeList);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupply/communitySupplyList");
		return modelAndView;
	}

	/**
	 * 商户查看
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/companyView.html")
	public ModelAndView companyView(HttpServletRequest request) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("omsUserId", UserContext.getCurrUser().getId());

		CommunitySupplyCompanyEntity communitySupplyCompanyEntity = communitySupplyService.getCommunitySupplyCompanyDetail(paramMap);
		request.setAttribute("communitySupplyCompanyEntity", communitySupplyCompanyEntity);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupply/communitySupplyCompanyView");
		return modelAndView;
	}

	/**
	 * 编辑
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/edit.html")
	public ModelAndView edit(HttpServletRequest request) {
		String id = request.getParameter("id");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupply/communitySupplyEdit");
		return modelAndView;
	}

	/**
	 * 保存
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/save.html")
	public ModelAndView save(HttpServletRequest request) {
		request.setAttribute("result", "账号保存成功");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupply/communitySupplyOprtSuccess");
		return modelAndView;
	}

	/**
	 * 查看明细
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/viewDetail.html")
	public ModelAndView viewDetail(HttpServletRequest request) {
		String id = request.getParameter("id");

		CommunitySupplyDetailEntity communitySupplyDetailEntity = communitySupplyService.getCommunitySupplyDetailById(id);
		request.setAttribute("entity", communitySupplyDetailEntity);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tCommunitySupplyFId", id);
		List<CommunitySupplyPic> supplyPicURL = communitySupplyPicBaseService.getCommunitySupplyPicByCondition(paramMap);
		request.setAttribute("supplyPicURL", supplyPicURL);

		paramMap.clear();
		paramMap.put("communitySupplyId", id);
		List<CommunitySupplyBelong> csbList = communitySupplyBelongBaseService.getCommunitySupplyBelongByCondition(paramMap);
		if (!csbList.isEmpty()) {//通过认领表找到商户信息
			if (csbList.get(0).gettCommunitySupplyCompanyFId() != null) {
				paramMap.clear();
				paramMap.put("tCommunitySupplyCompanyFId", csbList.get(0).gettCommunitySupplyCompanyFId());
				List<CommunitySupplyCompanyPic> comPicURL = communitySupplyCompanyPicBaseService.getCommunitySupplyCompanyPicByCondition(paramMap);
				request.setAttribute("comPicURL", comPicURL);
			} else {
				request.setAttribute("comPicURL", null);
			}
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupply/communitySupplyDetail");
		return modelAndView;
	}

	/**
	 * 临时店铺查看明细
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/viewTmpDetail.html")
	public ModelAndView viewTempDetail(HttpServletRequest request) {
		setCSTmpDetailForRequest(request);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupply/communitySupplyTempView");
		return modelAndView;
	}

	/**
	 * 认领 查看明细
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/viewBelongDetail.html")
	public ModelAndView viewBelongDetail(HttpServletRequest request) {
		setCSBelongDetailForRequest(request);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupply/communitySupplyBelongView");
		return modelAndView;
	}

	/**
	 * 往request中写入认领店铺信息
	 * 
	 * @param request
	 */
	private void setCSBelongDetailForRequest(HttpServletRequest request) {
		String id = request.getParameter("id");
		CommunitySupplyBelongViewEntity entity = communitySupplyService.getBelongDetailById(id);
		request.setAttribute("entity", entity);

		request.setAttribute("groupBuilding", groupBuildingBaseService.getGroupBuildingBySeqId(entity.getGroupBuildingId()));

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tCommunitySupplyFId", entity.getCommunitySupplyId());
		List<CommunitySupplyPic> supplyPicURL = communitySupplyPicBaseService.getCommunitySupplyPicByCondition(paramMap);
		request.setAttribute("supplyPicURL", supplyPicURL);

		paramMap.clear();
		paramMap.put("tCommunitySupplyBelongFId", id);
		List<CommunitySupplyCompanyPic> comPicURL = communitySupplyCompanyPicBaseService.getCommunitySupplyCompanyPicByCondition(paramMap);
		request.setAttribute("comPicURL", comPicURL);

		paramMap.clear();
		paramMap.put("sys0DelState", 0);
		paramMap.put("code", "CS_Management");//只显示“商家维护”角色
		request.setAttribute("roleList", omsPermiRoleService.getOmsPermiRoleByCondition(paramMap));

		User user = userBaseService.getUserBySeqId(entity.getClaimUserId());
		request.setAttribute("omsUserId", user.gettOmsUserFId());
	}

	/**
	 * 往request中写入临时店铺信息
	 * 
	 * @param request
	 */
	private void setCSTmpDetailForRequest(HttpServletRequest request) {
		String id = request.getParameter("id");
		CommunitySupplyTmpViewEntity entity = communitySupplyService.getTmpDetailById(id);
		request.setAttribute("entity", entity);
		if (entity.getAddType() == null || entity.getAddType() == CommunitySupplyTmpConstant.CommunitySupplyTmp_Add_Type.Add_Type_CS) {
			request.setAttribute("groupBuilding", groupBuildingBaseService.getGroupBuildingBySeqId(entity.getGroupBuildingId()));
		} else if (CommunitySupplyTmpConstant.CommunitySupplyTmp_Add_Type.Add_Type_PC == entity.getAddType()
				|| CommunitySupplyTmpConstant.CommunitySupplyTmp_Add_Type.Add_Type_CP == entity.getAddType()) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tmpId", id);
			List<GroupBuildingSimpleViewEntity> serviceGbList = this.msGroupBuildingService.selectGroupBuildingBySupply(paramMap);
			request.setAttribute("serviceGBList", serviceGbList);
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tCommunitySupplyTmpFId", id);
		List<CommunitySupplyPic> tmpPicURL = communitySupplyPicBaseService.getCommunitySupplyPicByCondition(paramMap);
		request.setAttribute("tmpPicURL", tmpPicURL);
		if(null!=entity.getAddType() && (entity.getAddType()==CommunitySupplyTmpConstant.CommunitySupplyTmp_Add_Type.Add_Type_PC
				||CommunitySupplyTmpConstant.CommunitySupplyTmp_Add_Type.Add_Type_CP==entity.getAddType())){
			List<CommunitySupplyContect> contects = communitySupplyContectBaseService.getCommunitySupplyContectByCondition(paramMap);
		    request.setAttribute("contects", contects);
		}
		List<CommunitySupplyCompanyPic> comPicURL = communitySupplyCompanyPicBaseService.getCommunitySupplyCompanyPicByCondition(paramMap);
		request.setAttribute("comPicURL", comPicURL);

		paramMap.clear();
		paramMap.put("sys0DelState", 0);
		paramMap.put("code", "CS_Management");//只显示“商家维护”角色
		request.setAttribute("roleList", omsPermiRoleService.getOmsPermiRoleByCondition(paramMap));
		if(entity.getAddType()==null || CommunitySupplyTmpConstant.CommunitySupplyTmp_Add_Type.Add_Type_CS==entity.getAddType()){
			User user = userBaseService.getUserBySeqId(entity.getCreateUserId());
			request.setAttribute("omsUserId", user.gettOmsUserFId());
		}else{
			OmsUser user = omsUserBaseService.getOmsUserBySeqId(entity.getCreateUserId());
			request.setAttribute("omsUserId", user.getId());
		}
	}

	/**
	 * 临时店铺 审核查看
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/auditCSTmpView.html")
	public ModelAndView auditCSTmpView(HttpServletRequest request) {
		setCSTmpDetailForRequest(request);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupply/communitySupplyTmpAudit");
		return modelAndView;
	}

	/**
	 * 认领店铺 审核查看
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/auditCSBelongView.html")
	public ModelAndView auditCSBelongView(HttpServletRequest request) {
		setCSBelongDetailForRequest(request);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupply/communitySupplyBelongAudit");
		return modelAndView;
	}

	/**
	 * 物业推荐 审核 查看
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/auditPSCView.html")
	public ModelAndView auditPSCView(HttpServletRequest request) {
		setPSCDetailForRequest(request);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupply/propertySuggestCommsupplyAudit");
		return modelAndView;
	}

	/**
	 * 往request中写入 物业推荐 信息
	 * 
	 * @param request
	 */
	private void setPSCDetailForRequest(HttpServletRequest request) {
		String pscId = request.getParameter("id");
		PropertySuggestCommsupplyEntity entity = communitySupplyService.getPropertySuggestCommsupplyDetail(pscId);
		request.setAttribute("entity", entity);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tCommunitySupplyFId", entity.gettCommunitySupplyFId());
		List<CommunitySupplyPic> supplyPicURL = communitySupplyPicBaseService.getCommunitySupplyPicByCondition(paramMap);
		request.setAttribute("supplyPicURL", supplyPicURL);

		paramMap.clear();
		paramMap.put("communitySupplyId", entity.gettCommunitySupplyFId());
		List<CommunitySupplyBelong> csbList = communitySupplyBelongBaseService.getCommunitySupplyBelongByCondition(paramMap);
		if (!csbList.isEmpty()) {//通过认领表找到商户信息
			paramMap.clear();
			paramMap.put("tCommunitySupplyCompanyFId", csbList.get(0).gettCommunitySupplyCompanyFId());
			List<CommunitySupplyCompanyPic> comPicURL = communitySupplyCompanyPicBaseService.getCommunitySupplyCompanyPicByCondition(paramMap);
			request.setAttribute("comPicURL", comPicURL);
		}
	}


	/**
	 * 物业推荐 审核保存
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/auditPSCSave.html")
	public ModelAndView auditPSCSave(HttpServletRequest request) {
		String pscId = request.getParameter("id");
		String auditResult = request.getParameter("auditResult").trim();//pass notpass

		PropertySuggestCommsupply psc = new PropertySuggestCommsupply();
		psc.setId(new BigInteger(pscId));
		psc.setAuditTime(DateUtil.formatSecond.format(new Date()));
		if ("notpass".equals(auditResult)) {//审核不通过
			String notPassReason = request.getParameter("notPassReason").trim();// 未通过原因
			psc.setAuditDesc(notPassReason);
			psc.setAuditStatus(CommunitySupplyConstant.CommunitySupplyTmp_Audit_Status.AUDIT_NOT_PASS);
			//			String mobile = request.getParameter("mobile");
			//			sendSMS(mobile, MessageFormat.format(CommunitySupplyConstant.AddNew_AuditNotPassSMSInfo, notPassReason));
			propertySuggestCommsupplyBaseService.updatePropertySuggestCommsupply(psc);
		} else if ("pass".equals(auditResult)) {//审核通过，业务复杂，放在Serveice中处理
			psc.setAuditDesc("审核通过");
			psc.setAuditStatus(CommunitySupplyConstant.PropertySuggestCommsupply_Audit_Status.AUDIT_PASS);
			propertySuggestCommsupplyBaseService.updatePropertySuggestCommsupply(psc);
		}

		request.setAttribute(JSPConstants.OprtResult, "审批结果保存成功");
		request.setAttribute(JSPConstants.ToURL, "../communitySupply/suggestList.html");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		return modelAndView;
	}

	/**
	 * 临时店铺 审核保存
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/auditCSTmpSave.html")
	public ModelAndView auditCSTmpSave(HttpServletRequest request) {
		String csTmpId = request.getParameter("id");
		String auditResult = request.getParameter("auditResult").trim();//pass notpass

		CommunitySupplyTmp communitySupplyTmp = new CommunitySupplyTmp();
		communitySupplyTmp.setId(new BigInteger(csTmpId));
		communitySupplyTmp.setAuditTime(DateUtil.formatSecond.format(new Date()));
		communitySupplyTmp.setAuditType(CommunitySupplyConstant.CommunitySupplyTmp_Audit_Type.NEW_AUDIT);
		if ("notpass".equals(auditResult)) {//审核不通过
			String notPassReason = request.getParameter("notPassReason").trim();// 未通过原因
			communitySupplyTmp.setAuditDesc(notPassReason);
			communitySupplyTmp.setAuditStatus(CommunitySupplyConstant.CommunitySupplyTmp_Audit_Status.AUDIT_NOT_PASS);
			String mobile = request.getParameter("mobile");
//			sendSMS(mobile, MessageFormat.format(CommunitySupplyConstant.AddNew_AuditNotPassSMSInfo, notPassReason));
			ShortMsgUtil.sendMessage(mobile, "supply_audit_no_pass", notPassReason);
			communitySupplyTmpBaseService.updateCommunitySupplyTmp(communitySupplyTmp);
		} else if ("pass".equals(auditResult)) {//审核通过，业务复杂，放在Serveice中处理
			String omsUserName = request.getParameter("omsUserName"); //账号name
			String omsUserId = request.getParameter("omsUserId"); //账号Id
			String roleId = request.getParameter("roleId"); //角色ID
			
			communitySupplyService.auditCSTmpPass(csTmpId, omsUserId, omsUserName, roleId);
		}
		
		request.setAttribute(JSPConstants.OprtResult, "审批结果保存成功");
		request.setAttribute(JSPConstants.ToURL, "../communitySupply/newList.html");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		return modelAndView;
	}

	/**
	 * 认领店铺 审核保存
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/auditCSBelongSave.html")
	public ModelAndView auditCSBelongSave(HttpServletRequest request) {

		String csBelongId = request.getParameter("id");
		String auditResult = request.getParameter("auditResult").trim();//pass notpass

		CommunitySupplyBelong communitySupplyBelong = new CommunitySupplyBelong();
		communitySupplyBelong.setId(new BigInteger(csBelongId));
		communitySupplyBelong.setAuditTime(DateUtil.formatSecond.format(new Date()));
		if ("notpass".equals(auditResult)) {//审核不通过
			String notPassReason = request.getParameter("notPassReason").trim();// 未通过原因
			communitySupplyBelong.setAuditDesc(notPassReason);
			communitySupplyBelong.setAuditStatus(CommunitySupplyConstant.CommunitySupplyBelong_Audit_Status.AUDIT_NOT_PASS);
			String mobile = request.getParameter("mobile");
//			sendSMS(mobile, MessageFormat.format(CommunitySupplyConstant.Belong_AuditNotPassSMSInfo, notPassReason));
			ShortMsgUtil.sendMessage(mobile, "supply_audit_no_pass", notPassReason);
			communitySupplyBelongBaseService.updateCommunitySupplyBelong(communitySupplyBelong);
		} else if ("pass".equals(auditResult)) {//审核通过，业务复杂，放在Serveice中处理
			String omsUserName = request.getParameter("omsUserName"); //账号name
			String omsUserId = request.getParameter("omsUserId"); //账号Id
			String roleId = request.getParameter("roleId"); //角色ID

			communitySupplyService.auditCSBelongPass(csBelongId, omsUserId, omsUserName, roleId);
		}

		request.setAttribute(JSPConstants.OprtResult, "审批结果保存成功");
		request.setAttribute(JSPConstants.ToURL, "../communitySupply/belongList.html");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		return modelAndView;
	}

	/**
	 * 标为推荐/取消推荐
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/suggestMark.html")
	@ResponseBody
	public String suggestMark(HttpServletRequest request) {
		String suggestMark = request.getParameter("suggestMark"); //标记
		String csId = request.getParameter("csId"); //商家ID
		String pcId = request.getParameter("pcId");//物业公司ID
		String gbId = request.getParameter("gbId");//小区ID

		String resultInfo = communitySupplyService.suggestMark(csId, suggestMark, pcId, gbId);
		return resultInfo;
	}

	/**
	 * 查找
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/search.html")
	public ModelAndView search(HttpServletRequest request) {
		String supplyType = request.getParameter("supplyType");
		String csName = request.getParameter("csName");
		String gbName = request.getParameter("gbName");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("omsUserId", UserContext.getCurrUser().getId());
		if (!"all".equals(supplyType)) {//查找全部时，不需要这个条件
			paramMap.put("supplyType", supplyType);
		}
		paramMap.put("csName", csName);
		paramMap.put("gbName", gbName);

		handleListOrSearch(request, paramMap);

		if (communitySupplyTypeList == null) {//延迟加载
			paramMap.clear();
			paramMap.put("sys0DelState", 0);
			communitySupplyTypeList = communitySupplyService.getCommunitySupplyTypeByCondition(paramMap);
		}
		request.setAttribute("cstList", communitySupplyTypeList);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupply/communitySupplyList");
		return modelAndView;
	}

	/**
	 * 临时店铺 查找
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/searchTmp.html")
	public ModelAndView searchTmp(HttpServletRequest request) {
		String auditStatus = request.getParameter("auditStatus");
		String cstName = ParamUtils.getString(request, "cstName", null);
		String cstId = request.getParameter("cstId");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("auditStatus", auditStatus);
		paramMap.put("cstName", cstName);
		paramMap.put("cstId", cstId);

		handleCSTempListOrSearch(request, paramMap);

		if (communitySupplyTypeList == null) {//延迟加载
			paramMap.clear();
			paramMap.put("sys0DelState", 0);
			communitySupplyTypeList = communitySupplyService.getCommunitySupplyTypeByCondition(paramMap);
		}
		request.setAttribute("cstList", communitySupplyTypeList);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupply/communitySupplyTempList");
		return modelAndView;
	}

	/**
	 * 认领店铺 查找
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/searchBelong.html")
	public ModelAndView searchBelong(HttpServletRequest request) {
		String auditStatus = request.getParameter("auditStatus");
		String csName = ParamUtils.getString(request, "csName", null);
		String cstId = request.getParameter("cstId");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("auditStatus", auditStatus);
		paramMap.put("csName", csName);
		paramMap.put("cstId", cstId);

		handleCSBelongListOrSearch(request, paramMap);

		if (communitySupplyTypeList == null) {//延迟加载
			paramMap.clear();
			paramMap.put("sys0DelState", 0);
			communitySupplyTypeList = communitySupplyService.getCommunitySupplyTypeByCondition(paramMap);
		}
		request.setAttribute("cstList", communitySupplyTypeList);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupply/communitySupplyBelongList");
		return modelAndView;
	}

	/**
	 * 统一处理List和Search请求
	 * 
	 * @param request
	 * @param paramMap
	 *            请求参数
	 */
	private void handleListOrSearch(HttpServletRequest request, Map<String, Object> paramMap) {
		paramMap.put("omsUserId", UserContext.getOperId());
		paramMap.put("isadmin", UserContext.getCurrUser().getIsadmin());

		int resultSize = communitySupplyService.getCommunitySupply_byUserId_forCount(paramMap);
		request.setAttribute("resultSize", resultSize);

		// 页数的参数名 
		String pageIndexName = new org.displaytag.util.ParamEncoder("row").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);

		List<CommunitySupplyEntity> searchRestList = communitySupplyService.getCommunitySupplyList_byUserId_forPage(curPageIndex, pageSize, paramMap);
		request.setAttribute("resList", searchRestList);
	}

	/**
	 * 统一处理 （临时申请的店铺） List和Search请求
	 * 
	 * @param request
	 * @param paramMap
	 *            请求参数
	 */
	private void handleCSTempListOrSearch(HttpServletRequest request, Map<String, Object> paramMap) {
		int resultSize = communitySupplyService.getCommunitySupplyTmpList_forCount(paramMap);
		request.setAttribute("resultSize", resultSize);

		// 页数的参数名 
		String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);

		List<CommunitySupplyTmpEntity> searchRestList = communitySupplyService.getCommunitySupplyTmpList_forPage(curPageIndex, pageSize, paramMap);
		request.setAttribute("resList", searchRestList);
	}

	/**
	 * 发短信
	 * 
	 * @param mobile
	 * @param msg
	 * @return
	 */
	private boolean sendSMS(String mobile, String msg) {
		try {
			List<String> mobiles = new ArrayList<String>();
			mobiles.add(mobile);
			String isMessageSend = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.IS_MESSAGE_SEND);
			FutureTask<String> task = new FutureTask<String>(new SendSmsRunnable(mobiles, msg, isMessageSend));
			new Thread(task).start();
			logger.info("发送短信的响应 = " + task.get());
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
		return true;
	}

	/**
	 * 物业推荐列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/suggestList.html")
	public ModelAndView suggestList(HttpServletRequest request) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("omsUserId", UserContext.getCurrUser().getId());

		handlePSCListOrSearch(request, paramMap);

		if (communitySupplyTypeList == null) {//延迟加载
			paramMap.clear();
			paramMap.put("sys0DelState", 0);
			communitySupplyTypeList = communitySupplyService.getCommunitySupplyTypeByCondition(paramMap);
		}
		request.setAttribute("cstList", communitySupplyTypeList);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupply/propertySuggestCommsupplyList");
		return modelAndView;
	}

	/**
	 * 物业推荐 查找
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/searchPSC.html")
	public ModelAndView searchPsc(HttpServletRequest request) {
		String auditStatus = request.getParameter("auditStatus");
		String csName = ParamUtils.getString(request, "csName", null);
		String cstId = request.getParameter("cstId");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("auditStatus", auditStatus);
		paramMap.put("csName", csName);
		paramMap.put("cstId", cstId);

		handlePSCListOrSearch(request, paramMap);

		if (communitySupplyTypeList == null) {//延迟加载
			paramMap.clear();
			paramMap.put("sys0DelState", 0);
			communitySupplyTypeList = communitySupplyService.getCommunitySupplyTypeByCondition(paramMap);
		}
		request.setAttribute("cstList", communitySupplyTypeList);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupply/propertySuggestCommsupplyList");
		return modelAndView;
	}

	private void handlePSCListOrSearch(HttpServletRequest request, Map<String, Object> paramMap) {
		int resultSize = communitySupplyService.getPropertySuggestCommsupply_forCount(paramMap);
		request.setAttribute("resultSize", resultSize);

		// 页数的参数名 
		String pageIndexName = new org.displaytag.util.ParamEncoder("row").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);

		List<PropertySuggestCommsupplyEntity> searchRestList = communitySupplyService.getPropertySuggestCommsupply_forPage(curPageIndex, pageSize, paramMap);
		request.setAttribute("resList", searchRestList);
	}
	
	/**
	 * 验证当前单据是否已经被认领
	 * @param request
	 * @return
	 */
	@RequestMapping("/auditCSBelongCheck.html")
	@ResponseBody
	public String auditCSBelongCheck(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		if(communitySupplyService.validateCSBelong_forCount(paramMap)>0){
			return "No";
		}else{
			return "Ok";
		}
	}
}
