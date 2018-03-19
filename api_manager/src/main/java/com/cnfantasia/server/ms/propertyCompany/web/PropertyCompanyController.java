package com.cnfantasia.server.ms.propertyCompany.web;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.msg.common.util.ShortMsgUtil;
import com.cnfantasia.server.api.commonBusiness.service.ICommMobileService;
import com.cnfantasia.server.api.omsPermiRole.constant.OmsPermiRoleConstant;
import com.cnfantasia.server.api.propertyCompany.constant.PropertyCompanyDict;
import com.cnfantasia.server.business.commonBusiness.util.MailUtils;
import com.cnfantasia.server.business.pub.utils.Md5Util;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.PinyinUtil;
import com.cnfantasia.server.domainbase.addressBlock.entity.AddressBlock;
import com.cnfantasia.server.domainbase.channelPartner.entity.ChannelPartner;
import com.cnfantasia.server.domainbase.channelPartnerRecommend.entity.ChannelPartnerRecommend;
import com.cnfantasia.server.domainbase.channelPartnerRecommend.service.ChannelPartnerRecommendBaseService;
import com.cnfantasia.server.domainbase.cookieInfo.entity.CookieInfo;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuildingRegister.entity.GroupBuildingRegister;
import com.cnfantasia.server.domainbase.omsPermiRole.entity.OmsPermiRole;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.entity.OmsUserHasTOmsPermiRole;
import com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.service.IOmsUserHasTOmsPermiRoleBaseService;
import com.cnfantasia.server.domainbase.propertyCompany.entity.PropertyCompany;
import com.cnfantasia.server.domainbase.propertyCompanyThirdPayCfg.entity.PropertyCompanyThirdPayCfg;
import com.cnfantasia.server.domainbase.propertyCompanyThirdPayCfg.service.IPropertyCompanyThirdPayCfgBaseService;
import com.cnfantasia.server.domainbase.propertyManagement.entity.PropertyManagement;
import com.cnfantasia.server.ms.cookieInfo.service.ICookieInfoService;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.groupBuilding.service.IGroupBuildingService;
import com.cnfantasia.server.ms.groupBuildingRegister.service.IGroupBuildingRegisterService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.omsPermiRole.service.IOmsPermiRoleService;
import com.cnfantasia.server.ms.omsUser.service.IOmsUserService;
import com.cnfantasia.server.ms.permi.dao.IPermiDao;
import com.cnfantasia.server.ms.propertyCompany.constant.PropertyCompanyConstant;
import com.cnfantasia.server.ms.propertyCompany.entity.PropertyCompanyEntity;
import com.cnfantasia.server.ms.propertyCompany.entity.PropertyCompanyWorkbenchEntity;
import com.cnfantasia.server.ms.propertyCompany.service.IPropertyCompanyService;
import com.cnfantasia.server.ms.propertyManagement.entity.PropertyManagementEntity;
import com.cnfantasia.server.ms.propertyManagement.service.IPropertyManagementService;
import com.cnfantasia.server.ms.provinceCityBlock.entity.CityWithBlock;
import com.cnfantasia.server.ms.provinceCityBlock.service.IProvinceCityBlockService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.comm.CnfantasiaCommUtil;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;
import com.cnfantasia.server.ms.pub.constant.PathConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.DateUtil;
import com.cnfantasia.server.ms.pub.utils.MapConverter;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/propertyCompany")
public class PropertyCompanyController extends BaseController{
	/**
	 * 物业-商务跟进角色
	 */
	private static final String WY_SALES = "wy-sales";

	private static final String auditPassEmailInfo = "亲爱的用户您好！您在解放区申请资料已通过审核，已为您开通了使用账号，可使用账号登录系统使用发布公告功能。<br>账号：{0}<br>初始密码：{1}<br>系统地址：http://oos.jiefangqu.com   <br>欢迎您对解放区的支持，如有问题可拨打我们的热线：0755-22690915";

//	private static final String auditPassSMSInfo = "您在解放区申请资料已通过审核，可使用账号登录系统使用发布公告功能。账号：{0}初始密码：{1}系统地址：http://oos.jiefangqu.com【解放区】";

	private static final String auditNotPassEmailInfo = "亲爱的用户您好！您在解放区申请开通发布公告申请资料没有通过审核，不通过原因：{0}，您可登录http://oos.jiefangqu.com重新提交申请。<br>感谢您对解放区的支持，如有问题也可拨打我们的热线：0755-22690915";

//	private static final String auditNotPassSMSInfo = "您在解放区申请开通发布公告申请资料没有通过审核，不通过原因：{0}，您可登录http://oos.jiefangqu.com【解放区】";

	private Log logger = LogFactory.getLog(getClass());
	
	private IPropertyCompanyService propertyCompanyService;

	private IProvinceCityBlockService provinceCityBlockService;

	private IGroupBuildingRegisterService groupBuildingRegisterService;

	private IOmsUserService omsUserService;

	private IOmsPermiRoleService omsPermiRoleService;

	private IGroupBuildingService groupBuildingService;

	private ICookieInfoService cookieInfoService;
	
	@Resource
	ICommMobileService commMobileService;

	@Resource
	IOmsUserHasTOmsPermiRoleBaseService omsUserHasTOmsPermiRoleBaseService;

	public void setCookieInfoService(ICookieInfoService cookieInfoService) {
		this.cookieInfoService = cookieInfoService;
	}

	public void setOmsPermiRoleService(IOmsPermiRoleService omsPermiRoleService) {
		this.omsPermiRoleService = omsPermiRoleService;
	}

	public void setGroupBuildingService(IGroupBuildingService groupBuildingService) {
		this.groupBuildingService = groupBuildingService;
	}

	public void setOmsUserService(IOmsUserService omsUserService) {
		this.omsUserService = omsUserService;
	}

	public void setGroupBuildingRegisterService(IGroupBuildingRegisterService groupBuildingRegisterService) {
		this.groupBuildingRegisterService = groupBuildingRegisterService;
	}

	public void setProvinceCityBlockService(IProvinceCityBlockService provinceCityBlockService) {
		this.provinceCityBlockService = provinceCityBlockService;
	}

	public void setPropertyCompanyService(IPropertyCompanyService propertyCompanyService) {
		this.propertyCompanyService = propertyCompanyService;
	}
	
	@Resource
	IPropertyManagementService propertyManagementService; 
	@Resource
	IPermiDao permiDao;
	@Resource
	private ChannelPartnerRecommendBaseService channelPartnerRecommendBaseService;
	@Resource
	private IPropertyCompanyThirdPayCfgBaseService propertyCompanyThirdPayCfgBaseService;

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
		
		List<Map<String, Object>> userList = permiDao.selectUserByRoleCode(WY_SALES);
		request.setAttribute("userList", userList);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/propertyCompany/register/pcRegisterList");
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
		paramMap.put("userRealName", UserContext.getCurrUser().getRealName());
		int resultSize = propertyCompanyService.getPCList4admin_forCount(paramMap);
		request.setAttribute("resultSize", resultSize);

		// 页数的参数名 
		String pageIndexName = new org.displaytag.util.ParamEncoder("row").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);

		List<PropertyCompanyEntity> searchRestList = propertyCompanyService.getPCList4admin_forPage(curPageIndex, pageSize, paramMap);

		request.setAttribute("resList", searchRestList);
	}
	
	private void handleEditListOrSearch(HttpServletRequest request, Map<String, Object> paramMap) {
		paramMap.put("userRealName", UserContext.getCurrUser().getRealName());
		int resultSize = propertyCompanyService.getEditPCList4admin_forCount(paramMap);
		request.setAttribute("resultSize", resultSize);

		// 页数的参数名 
		String pageIndexName = new org.displaytag.util.ParamEncoder("row").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);

		List<PropertyCompanyEntity> searchRestList = propertyCompanyService.queryPcMsgList(curPageIndex, pageSize, paramMap);

		request.setAttribute("resList", searchRestList);
	}

	/**
	 * 编辑
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/edit.html")
	public ModelAndView edit(HttpServletRequest request) {
		BigInteger id = UserContext.getCurrUser().getId();

		PropertyCompanyEntity pcEntity = propertyCompanyService.selectPropertyCompanyByOmsUserId(id);
		request.setAttribute("pcEntity", pcEntity);

		List<GroupBuildingSimpleEntity> gbList = propertyCompanyService.select_gbList_ByOmsUserId(id);
		request.setAttribute("gbList", gbList);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/propertyCompany/propertyCompanyEdit");
		return modelAndView;
	}

	/**
	 * 审核
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/audit.html")
	public ModelAndView audit(HttpServletRequest request) {
		BigInteger pcId = new BigInteger(request.getParameter("pcId"));

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", pcId);
		PropertyCompany pc = propertyCompanyService.getPropertyCompanyByCondition(paramMap).get(0);
		request.setAttribute("pc", pc);

		List<Map<String, Object>> gbrList = propertyCompanyService.select_gbrList_ByPCId(pcId);
		request.setAttribute("gbrList", gbrList);

		paramMap.clear();
		paramMap.put("sys0DelState", 0);
		paramMap.put("code", OmsPermiRoleConstant.Sys_Role_PC_Base);
		List<OmsPermiRole> oprList1 = omsPermiRoleService.getOmsPermiRoleByCondition(paramMap);
		paramMap.clear();
		paramMap.put("sys0DelState", 0);
		paramMap.put("code", OmsPermiRoleConstant.Sys_Role_PC_Supper);
		List<OmsPermiRole> oprList2 = omsPermiRoleService.getOmsPermiRoleByCondition(paramMap);
		paramMap.clear();
		paramMap.put("sys0DelState", 0);
		paramMap.put("code", OmsPermiRoleConstant.Sys_Role_PC_All);
		List<OmsPermiRole> oprList3 = omsPermiRoleService.getOmsPermiRoleByCondition(paramMap);
		oprList1.addAll(oprList2);
		oprList1.addAll(oprList3);
		request.setAttribute("roleList", oprList1); //前台只能显示：基础/高级/全面 物业合作角色

		List<Map<String, Object>> userList = permiDao.selectUserByRoleCode(WY_SALES);
		request.setAttribute("userList", userList);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/propertyCompany/register/pcRegisterAudit");
		return modelAndView;
	}

	/**
	 * 审核后的查看
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/auditView.html")
	public ModelAndView auditView(HttpServletRequest request) {
		BigInteger pcId = new BigInteger(request.getParameter("pcId"));

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", pcId);
		PropertyCompany pc = propertyCompanyService.getPropertyCompanyByCondition(paramMap).get(0);
		request.setAttribute("pc", pc);

		List<Map<String, Object>> gbrList = propertyCompanyService.select_gbrList_ByPCId(pcId);
		request.setAttribute("gbrList", gbrList);

		request.setAttribute("omsUser", omsUserService.getOmsUserBySeqId(pc.getAdminId()));

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/propertyCompany/register/pcRegisterAuditView");
		return modelAndView;
	}
	
	/**
	 * 管理处审核后的查看
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/auditPMView.html")
	public ModelAndView auditPMView(HttpServletRequest request) {
		BigInteger pcId = ParamUtils.getBigInteger(request, "pcId", null);
		BigInteger pmId = ParamUtils.getBigInteger(request, "pmId", null);
		
		PropertyManagement propertyManagement = propertyManagementService.getPropertyManagementBySeqId(CnfantasiaCommUtil.convert2BigInteger(pmId));
		request.setAttribute("pm", propertyManagement);
		
		PropertyCompany propertyCompany = propertyCompanyService.getPropertyCompanyBySeqId(propertyManagement.gettPropertyCompanyFId());
		request.setAttribute("pc", propertyCompany);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/propertyManagement/pmView");
		return modelAndView;
	}

	/**
	 * 审核后的编辑
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/initEditAfterAuditView.html")
	public ModelAndView initEditAfterAuditView(HttpServletRequest request) {
		BigInteger pcId = new BigInteger(request.getParameter("pcId"));

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", pcId);
		PropertyCompany pc = propertyCompanyService.getPropertyCompanyByCondition(paramMap).get(0);
		request.setAttribute("pc", pc);
		
		//查询物业公司和合作代理的信息
		List<ChannelPartner> channelPartnerList = propertyCompanyService.selectChannelPartnerByPc(pc);
		request.setAttribute("cpList", channelPartnerList);
		boolean isHasPc = channelPartnerList!=null && channelPartnerList.size()>0 ? true : false;
		request.setAttribute("isHasPc", isHasPc);//是否有代理信息，true有
		
		List<Map<String, Object>> gbrList = propertyCompanyService.select_gbrList_ByPCId(pcId);
		request.setAttribute("gbrList", gbrList);

		paramMap.clear();
		paramMap.put("sys0DelState", 0);
		paramMap.put("code", OmsPermiRoleConstant.Sys_Role_PC_Base);
		List<OmsPermiRole> oprList1 = omsPermiRoleService.getOmsPermiRoleByCondition(paramMap);
		paramMap.clear();
		paramMap.put("sys0DelState", 0);
		paramMap.put("code", OmsPermiRoleConstant.Sys_Role_PC_Supper);
		List<OmsPermiRole> oprList2 = omsPermiRoleService.getOmsPermiRoleByCondition(paramMap);
		paramMap.clear();
		paramMap.put("sys0DelState", 0);
		paramMap.put("code", OmsPermiRoleConstant.Sys_Role_PC_All);
		List<OmsPermiRole> oprList3 = omsPermiRoleService.getOmsPermiRoleByCondition(paramMap);
		oprList1.addAll(oprList2);
		oprList1.addAll(oprList3);
		request.setAttribute("roleList", oprList1); //前台只能显示：基础/高级/全面 物业合作角色

		paramMap.clear();
		paramMap.put("tOmsUserFId", pc.getAdminId());
		paramMap.put("sys0DelState", 0);
		List<OmsUserHasTOmsPermiRole> roleList = omsUserHasTOmsPermiRoleBaseService.getOmsUserHasTOmsPermiRoleByCondition(paramMap);
		for (int i = 0; i < roleList.size(); i++) {
			for (int j = 0; j < oprList1.size(); j++) {
				if (roleList.get(i).gettOmsPermiRoleFId().equals(oprList1.get(j).getId())) {
					oprList1.get(j).setStatus(3);//借用3标识被选中的角色
				}
			}
		}
		
		List<Map<String, Object>> userList = permiDao.selectUserByRoleCode(WY_SALES);
		request.setAttribute("userList", userList);
		

		return new ModelAndView("/propertyCompany/pcEditAfterAudited");
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete.html")
	public ModelAndView delete(HttpServletRequest request) {
		BigInteger pcId = new BigInteger(request.getParameter("pcId"));

		PropertyCompany pc = new PropertyCompany();
		pc.setId(pcId);
		pc.setSys0DelState(1);
		pc.setSys0DelTime(DateUtil.formatSecond.format(new Date()));
		int updateCount = propertyCompanyService.updatePropertyCompany(pc);

		request.setAttribute("result", updateCount == 1 ? "删除成功" : "删除失败");
		return new ModelAndView("/propertyCompany/pcOprtSuccess");
	}

	/**
	 * 保存
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/save.html")
	public ModelAndView save(HttpServletRequest request) {
		String id = request.getParameter("pcId");
		String pcName = request.getParameter("pcName").trim();
		String tel = request.getParameter("tel").trim();
		String desc = request.getParameter("desc").trim();

		PropertyCompanyEntity pcEntity = new PropertyCompanyEntity();
		if (StringUtils.isNotEmpty(id))
			pcEntity.setId(new BigInteger(id));
		pcEntity.setName(pcName);
		pcEntity.setTel(tel);
		pcEntity.setDesc(desc);

		propertyCompanyService.savePropertyCompany(pcEntity);

		request.setAttribute("result", "物业公司信息保存成功");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/propertyCompany/pcOprtSuccess");
		return modelAndView;
	}

	/**
	 * 保存审核后的编辑
	 * 
	 * @param request
	 * @return
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	@RequestMapping("/saveEditAfterAudited.html")
	@Transactional
	public ModelAndView saveEditAfterAudited(HttpServletRequest request) {
		BigInteger pcId = new BigInteger(request.getParameter("pcId"));
		BigInteger omsUserId = new BigInteger(request.getParameter("omsUserId"));
		BigInteger channelPartnerFId = ParamUtils.getBigInteger(request,"channelPartnerFId", null);

		String roldId = request.getParameter("roleId"); //角色ID
		String follower = request.getParameter("follower");//商务跟进人
		int cooperationType = ParamUtils.getInt(request, "cooperationType", 0);//合作模式
		int oldCooperationType = ParamUtils.getInt(request, "oldCooperationType", 0);//合作模式

		PropertyCompanyEntity pcEntity = new PropertyCompanyEntity();
		pcEntity.setId(pcId);
		pcEntity.setFollower(follower);
		updatePropertyCompanyCooperationType(cooperationType, oldCooperationType, pcEntity);
		propertyCompanyService.savePropertyCompany(pcEntity);
		
		//更新代理合作信息
		if(channelPartnerFId != null) {
			PropertyCompany propertyComany = propertyCompanyService.getPropertyCompanyBySeqId(pcEntity.getId());
			//判断更新还是添加
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("pcName", propertyComany.getName());
			List<ChannelPartnerRecommend> channelList = channelPartnerRecommendBaseService.getChannelPartnerRecommendByCondition(paramMap);
			if(channelList !=null && channelList.size() > 0) {
				ChannelPartnerRecommend channelPartnerRecommend = channelList.get(0);
				channelPartnerRecommend.setIsRelevance(1);
				channelPartnerRecommendBaseService.updateChannelPartnerRecommend(channelPartnerRecommend);
			} else {
				ChannelPartnerRecommend channelPartnerRecommend = new ChannelPartnerRecommend();
				channelPartnerRecommend.setPcName(propertyComany.getName());
				channelPartnerRecommend.setLinkman(propertyComany.getLinkman());
				channelPartnerRecommend.settChannelPartnerFId(channelPartnerFId);
				channelPartnerRecommend.setIsRelevance(1);//已经关联
				channelPartnerRecommend.setSys0UpdTime(DateUtils.getCurrentDate());
				propertyCompanyService.saveChannelPartnerRecommend(channelPartnerRecommend);
			}
		}
		
		String[] roldIds = { roldId, };
		omsUserService.saveOmsUser(omsUserService.getOmsUserBySeqId(omsUserId), roldIds);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tPropertyCompanyFId", pcId);
		List<GroupBuilding> gbList = groupBuildingService.getGroupBuildingByCondition(paramMap);
		for (int i = 0; i < gbList.size(); i++) {
			updateGroupBuildingCooperationType(cooperationType, gbList.get(i));
		}
		if (!gbList.isEmpty()) {
			groupBuildingService.updateGroupBuildingBatch(gbList);
		}

		return new ModelAndView("redirect:/propertyCompany/list.html");
	}

	/**
	 * 保存审批结果
	 * 
	 * @param request
	 * @return
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	@RequestMapping("/saveAuditResult.html")
	@Transactional
	public ModelAndView saveAuditResult(HttpServletRequest request) throws BadHanyuPinyinOutputFormatCombination {
		BigInteger pcId = new BigInteger(request.getParameter("pcId"));
		String auditResult = request.getParameter("auditResult").trim();//pass notpass
		String notPassReason = request.getParameter("notPassReason").trim();// 未通过原因
		String omsUserName = request.getParameter("omsUserName").trim(); //账号name
		String roldId = request.getParameter("roleId"); //角色ID
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String follower = request.getParameter("follower");//商务跟进人
		int cooperationType = ParamUtils.getInt(request, "cooperationType", 0);//合作模式

		//0先判断该用户是否已经存在
		{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("sys0DelState", 0);
			paramMap.put("userAccount", omsUserName);
			if (omsUserService.getOmsUserCount(paramMap) > 0) {
				request.setAttribute(JSPConstants.OprtResult, "已经存在此后台账号");
				request.setAttribute(JSPConstants.ToURL, "../propertyCompany/list.html");

				ModelAndView modelAndView = new ModelAndView();
				modelAndView.setViewName(JSPConstants.OprtSuccessPage);
				return modelAndView;
			}
		}

		//1 更新物业公司审批信息字段
		PropertyCompanyEntity pcEntity = new PropertyCompanyEntity();
		pcEntity.setId(pcId);
		if ("pass".equals(auditResult)) {
			pcEntity.setFollower(follower);
			pcEntity.setAuditResult("审批通过");
			updatePropertyCompanyCooperationType(cooperationType, 0, pcEntity);
		} else {
			pcEntity.setAuditResult(notPassReason);
		}
		pcEntity.setIsAudited(1);
		propertyCompanyService.savePropertyCompany(pcEntity);
		if ("notpass".equals(auditResult)) {
//			String msg = SmsPropertyUtil.getProperty("property_company_audit_not_pass", notPassReason);
//			commMobileService.sendMsg(mobile, MessageFormat.format(auditNotPassSMSInfo, notPassReason));
//			commMobileService.sendMsg(mobile, msg);
			ShortMsgUtil.sendMessage(mobile, "pc_audit_not_pass", notPassReason);
			MailUtils.sendMail("解放区账号审核未通过", MessageFormat.format(auditNotPassEmailInfo, notPassReason), email, "");
			request.setAttribute(JSPConstants.OprtResult, "审批结果保存成功");
			request.setAttribute(JSPConstants.ToURL, "../propertyCompany/list.html");
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName(JSPConstants.OprtSuccessPage);
			return modelAndView;
		}

		//2 新增后台账号，并分配给其角色
		OmsUser omsUser = new OmsUser();
		omsUser.setIsadmin(0);
		omsUser.setUserState(0L);
		omsUser.setIsSubUser(0);
		omsUser.setIsPmUser(0);
		omsUser.setSys0AddUser(UserContext.getCurrUser().getId());
		omsUser.setUserAccount(omsUserName);
		omsUser.setPassword(Md5Util.MD5Twice(omsUserName));
		String[] roldIds = { roldId, };
		omsUserService.saveOmsUser(omsUser, roldIds);
		pcEntity.setAdminId(omsUser.getId());
		propertyCompanyService.savePropertyCompany(pcEntity);

		//2.1 更新CookieInfo信息，更新其t_oms_user_f_id 字段
		Map<String, Object> paramMapC = new HashMap<String, Object>();
		paramMapC.put("tPropertyCompanyFId", pcEntity.getId());
		List<CookieInfo> cokkieInfoList = cookieInfoService.getCookieInfoByCondition(paramMapC);
		if (cokkieInfoList.size() == 1) {
			CookieInfo cookieInfo = cokkieInfoList.get(0);
			cookieInfo.settOmsUserFId(omsUser.getId());
			cookieInfoService.updateCookieInfo(cookieInfo);
		}

		//3 更新小区信息：若找到匹配的小区则更新之，没找到则插入新小区

		List<Map<String, Object>> gbrList = propertyCompanyService.select_gbrList_ByPCId(pcId);

		//3.1 更新小区信息
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<GroupBuilding> gbWillBeUpdatedList = new ArrayList<GroupBuilding>(gbrList.size());
		for (int i = gbrList.size() - 1; i >= 0; i--) {
			paramMap.put("name", gbrList.get(i).get("gbrName"));
			paramMap.put("tBlockFId", gbrList.get(i).get("abId"));
			List<GroupBuilding> gbList = groupBuildingService.getGroupBuildingByCondition(paramMap);
			for (int j = 0; j < gbList.size(); j++) {
				GroupBuilding groupBuilding = gbList.get(j);
				groupBuilding.setAddressDesc(gbrList.get(i).get("gbrAddressdesc").toString());
				groupBuilding.settPropertyCompanyFId(pcId);
				groupBuilding.setCheckStatus(1);//审核通过

				updateGroupBuildingCooperationType(cooperationType, groupBuilding);

				groupBuildingService.updateGroupBuilding(groupBuilding);
			}
			gbWillBeUpdatedList.addAll(gbList);
			if (!gbList.isEmpty())
				gbrList.remove(i);
		}

		//3.2 插入小区信息
		List<GroupBuilding> gbWillBeInsertedList = new ArrayList<GroupBuilding>(gbrList.size());
		for (int i = gbrList.size() - 1; i >= 0; i--) {
			GroupBuilding groupBuilding = new GroupBuilding();
			String name = gbrList.get(i).get("gbrName").toString();
			groupBuilding.setName(name);
			groupBuilding.setPinyinName(PinyinUtil.hanyuToPinyinSimple(name));
			groupBuilding.setAddressDesc(gbrList.get(i).get("gbrAddressdesc").toString());
			groupBuilding.settBlockFId(new BigInteger(gbrList.get(i).get("abId").toString()));
			groupBuilding.settPropertyCompanyFId(pcId);
			groupBuilding.setCheckStatus(1);
			groupBuilding.setSys0AddUser(omsUser.getId());

			updateGroupBuildingCooperationType(cooperationType, groupBuilding);

			gbWillBeInsertedList.add(groupBuilding);
		}
		if (!gbWillBeInsertedList.isEmpty()) {
			groupBuildingService.insertGroupBuildingBatch(gbWillBeInsertedList);
		}

//		commMobileService.sendMsg(mobile, MessageFormat.format(auditPassSMSInfo, omsUserName, omsUserName));
//		String msg = SmsPropertyUtil.getProperty("property_company_audit_pass", omsUserName, omsUserName);
//		commMobileService.sendMsg(mobile, msg);
		ShortMsgUtil.sendMessage(mobile, "pc_audit_pass", omsUserName, omsUserName);
		MailUtils.sendMail("解放区账号审核通过", MessageFormat.format(auditPassEmailInfo, omsUserName, omsUserName), email, "");
		request.setAttribute(JSPConstants.OprtResult, "审批结果保存成功");
		request.setAttribute(JSPConstants.ToURL, "../propertyCompany/list.html");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		return modelAndView;
	}

	/**
	 * 更新小区的合作类型相关信息
	 * 
	 * @param cooperationType
	 * @param groupBuilding
	 */
	private void updateGroupBuildingCooperationType(int cooperationType, GroupBuilding groupBuilding) {
		groupBuilding.setSignStatus(cooperationType > 0 ? 1 : 0);
		groupBuilding.setCooperationType(cooperationType);

		switch (cooperationType) {
		case PropertyCompanyDict.CooperationType.baseCooperation:
			groupBuilding.setLightCooperationTime(DateUtil.formatSecond.format(new Date()));
			break;
		case PropertyCompanyDict.CooperationType.supperCooperation:
			groupBuilding.setHighCooperationTime(DateUtil.formatSecond.format(new Date()));
			break;
		case PropertyCompanyDict.CooperationType.allCooperation:
			groupBuilding.setAllCooperationTime(DateUtil.formatSecond.format(new Date()));
			break;
		default:
			break;
		}
	}

	/**
	 * 更新物业公司的合作类型相关信息
	 * 
	 * @param cooperationType
	 * @param groupBuilding
	 */
	private void updatePropertyCompanyCooperationType(int cooperationType, int oldCooperationType, PropertyCompany propertyCompany) {
		propertyCompany.setCooperationType(cooperationType);

		switch (cooperationType) {
		case PropertyCompanyDict.CooperationType.baseCooperation:
			propertyCompany.setIsLightCooperation(1);
			if(cooperationType != oldCooperationType) {
				propertyCompany.setLightCooperationTime(DateUtil.formatSecond.format(new Date()));
			}
			break;
		case PropertyCompanyDict.CooperationType.supperCooperation:
			propertyCompany.setIsHighCooperation(1);
			if(cooperationType != oldCooperationType) {
				propertyCompany.setHighCooperationTime(DateUtil.formatSecond.format(new Date()));
			}
			break;
		case PropertyCompanyDict.CooperationType.allCooperation:
			propertyCompany.setIsAllCooperation(1);
			if(cooperationType != oldCooperationType) {
				propertyCompany.setAllCooperationTime(DateUtil.formatSecond.format(new Date()));
			}
			break;
		default:
			break;
		}
	}

	/**
	 * 查找
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/search.html")
	public ModelAndView search(HttpServletRequest request) {
		String pcName = request.getParameter("pcName");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String isAudited = request.getParameter("isAudited");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pcName", pcName);
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		if (!"-1".equals(isAudited)) {
			paramMap.put("isAudited", isAudited);
		}

		handleListOrSearch(request, paramMap);
		
		List<Map<String, Object>> userList = permiDao.selectUserByRoleCode(WY_SALES);
		request.setAttribute("userList", userList);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/propertyCompany/register/pcRegisterList");
		return modelAndView;
	}

	/**
	 * 注册
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/register.html")
	public ModelAndView register(HttpServletRequest request) {
		request.setAttribute("pcbList", provinceCityBlockService.getProvinceWithCityBlockList());

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/propertyCompany/register/propertyCompanyRegister");
		return modelAndView;
	}
	
	/**
	 * 注册-物业公司名称校验
	 * 
	 */
	@RequestMapping("/checkPcName.html")
	@ResponseBody
	public String checkPcName(HttpServletRequest request) {
		String result = "验证通过";
		String pcName = request.getParameter("pcName");
		int pcCount =  propertyCompanyService.selectValidPropertyCompanyByPcName(pcName);
		if(pcCount>0)
			result = "\""+ pcName +"\"" + "物业公司已签约，请检查输入的物业公司的名称。";
		return result;
	}

	/**
	 * 修改商务跟进人
	 * 
	 * @author wenfq 2015-05-06
	 * @param request
	 * @return
	 */
	@RequestMapping("/modifyFollower.html")
	@ResponseBody
	public String modifyFollower(HttpServletRequest request) {
		String pcId = request.getParameter("id");
		String follower = request.getParameter("follower");

		PropertyCompany propertyCompany = new PropertyCompany();
		propertyCompany.setId(new BigInteger(pcId));
		propertyCompany.setFollower(follower);
		int udpCount = propertyCompanyService.updatePropertyCompany(propertyCompany);

		return udpCount >= 0 ? "success" : "fail";
	}

	/**
	 * 保存Cookie信息
	 * 
	 * @param request
	 * @param tPropertyCompanyFId
	 */
	private void saveCookie(HttpServletRequest request, BigInteger tPropertyCompanyFId) {
		Cookie[] cookies = request.getCookies();

		for (Cookie c : cookies) {
			if (c.getName().equals(JSPConstants.CookieName) && StringUtils.isNotEmpty(c.getValue())) {
				// %3Fhmsr%3Dsina%26hmmd%3Dimg%26hmpl%3Dlm%26hmkw%3DpropetyFee%26hmci%3DbyJFQ
				String[] namePairs = c.getValue().substring(3).split("%26");
				String jsonString = "{\"" + c.getValue().substring(3).replace("%26", "\",\"").replace("%3D", "\":\"") + "\"}";

				CookieInfo cookieInfo = JSONObject.parseObject(jsonString, CookieInfo.class);
				cookieInfo.settPropertyCompanyFId(tPropertyCompanyFId);
				cookieInfo.setSys0AddTime(DateUtil.formatSecond.format(new Date()));
				cookieInfoService.insertCookieInfo(cookieInfo);
			}
		}
	}

	/**
	 * 注册信息保存
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/registerInfoSave.html")
	@Transactional
	public ModelAndView registerInfoSave(HttpServletRequest request) throws IOException {
		
		String pcName = request.getParameter("pcName").trim();//公司名称
		if (StringUtils.isEmpty(pcName)) {//公司名称不能为空，偶发前台校验会被绕过的情况 
			return new ModelAndView("/propertyCompany/register/propertyCompanyRegister");
		}

		String phoneNum = request.getParameter("phoneNum").trim(); //手机号
		String officeNum = request.getParameter("officeNum").trim();//座机号
		String linkMan = request.getParameter("linkMan").trim();//联系人
		String email = request.getParameter("email").trim();//联系人
		String photoBusinessLicense = "";//营业执照
		String photoCredentials = "";//资质证明

		String[] gbNames = request.getParameterValues("gbName");//小区名称
		String[] gbAddrDesc = request.getParameterValues("gbAddrDesc");//小区详细地址
		String[] abIds = request.getParameterValues("abId");//小区所属哪个区

		if (request instanceof MultipartHttpServletRequest) {
			// 转型为Spring的MultipartHttpRequest(重点的所在)
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 根据前台的name名称得到上传的文件
			MultipartFile uploadImageFile = multipartRequest.getFile("imageFileBL");
			//String filePath = request.getSession().getServletContext().getRealPath(pcImageDir);
			String filePath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + PathConstants.PC_Image_Dir;
			if (uploadImageFile != null && StringUtils.isNotEmpty(uploadImageFile.getOriginalFilename())) {//有上传图片 
				//营业执照
				int indexOfDot = uploadImageFile.getOriginalFilename().indexOf(".");
				String fileNameBL = System.currentTimeMillis() + uploadImageFile.getOriginalFilename().substring(indexOfDot); //物业公司名作为前缀，防止文件重名
				File fileBL = new File(filePath + "/" + fileNameBL);
				photoBusinessLicense = PathConstants.PC_Image_Dir + fileNameBL;
				uploadImageFile.transferTo(fileBL);
			}

			//资质证明
			uploadImageFile = multipartRequest.getFile("imageFileC");
			if (uploadImageFile != null && StringUtils.isNotEmpty(uploadImageFile.getOriginalFilename())) {//有上传图片 
				int indexOfDot = uploadImageFile.getOriginalFilename().indexOf(".");
				String fileNameC = (System.currentTimeMillis() + 1/*服务器太快了，可能1ms之内就处理完了，所以加个1*/) + uploadImageFile.getOriginalFilename().substring(indexOfDot); //物业公司名作为前缀，防止文件重名
				File fileC = new File(filePath + "/" + fileNameC);
				photoCredentials = PathConstants.PC_Image_Dir + fileNameC;
				uploadImageFile.transferTo(fileC);
			}
		}

		PropertyCompany pcEntity = new PropertyCompany();
		pcEntity.setName(pcName);
		pcEntity.setTel(officeNum);
		pcEntity.setLinkman(linkMan);
		pcEntity.setEmail(email);
		pcEntity.setMobilePhone(phoneNum);
		pcEntity.setPhotoBusinessLicense(photoBusinessLicense);
		pcEntity.setPhotoCredentials(photoCredentials);
		pcEntity.setIsAudited(0);
		propertyCompanyService.savePropertyCompany(pcEntity);
		saveCookie(request, pcEntity.getId());
		
		List<GroupBuildingRegister> gbrs = new ArrayList<GroupBuildingRegister>();
		for (int i = 1/*忽略第一个无效数据*/; i < gbNames.length; i++) {
			GroupBuildingRegister gbr = new GroupBuildingRegister();
			gbr.setName(gbNames[i].trim());
			gbr.setAddressDesc(gbAddrDesc[i].trim());
			gbr.settAddressBlockFId(new BigInteger(abIds[i]));
			gbr.settPropertyCompanyFId(pcEntity.getId());
			gbrs.add(gbr);
		}
		if (gbrs.size() > 0) {
			groupBuildingRegisterService.insertGroupBuildingRegisterBatch(gbrs);
		}

		if ("1".equals(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.EmailSwitch))) {
			sendEmailToAdmin(pcEntity);
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/propertyCompany/register/registerInfoSaveSuccess");
		return modelAndView;
	}

	/**
	 * 给解放区后管理人员（吴阳）发送邮件提醒
	 * 
	 * @param pcEntity
	 */
	private void sendEmailToAdmin(PropertyCompany pcEntity) {
		StringBuilder sb = new StringBuilder("物业公司信息如下：\n");
		sb.append("物业公司名称：").append(pcEntity.getName());
		sb.append("联系人：").append(pcEntity.getLinkman());
		sb.append("联系电话：").append(pcEntity.getMobilePhone());
		logger.info("sending email: " + sb);
		MailUtils.sendMail("有刚注册的物业公司待您审核", sb.toString(), "wuyang@cnfantasia.com");
	}

	/**
	 * 根据省获得市
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getCityList.html")
	@ResponseBody
	public String getCityList(HttpServletRequest request) {
		String apId = request.getParameter("apId");//省ID
		List<CityWithBlock> cityList = provinceCityBlockService.getCityWiBlockList(apId);

		return JSON.toJSONString(cityList);
	}

	/**
	 * 根据市获得区
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getBlockList.html")
	@ResponseBody
	public String getBlockList(HttpServletRequest request) {
		String acId = request.getParameter("acId");
		List<AddressBlock> abList = provinceCityBlockService.getAddressBlockList(acId);
		return JSON.toJSONString(abList);
	}
	
	/**
	 * 物业公司工作台
	 * @param request
	 * @return
	 */
	@RequestMapping("/workbench.html")
	public ModelAndView workbenchView(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		//获取当前物业公司的信息
		PropertyCompanyWorkbenchEntity workbenchEntity = this.propertyCompanyService.getPropertyCompanyWorkbench(CnfantasiaCommUtil.getCurrentUserId());
		if(null!=workbenchEntity){
			request.setAttribute("entity", workbenchEntity);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("companyId", workbenchEntity.getId());
			List<PropertyManagementEntity> managementList = propertyManagementService.selectPropertyManagementForList(paramMap);
			request.setAttribute("managementList", managementList);
			request.setAttribute("managementSize", managementList.size());
			BigInteger pcId = UserContext.getUser().getPropertyCompanyId();

			PropertyCompanyThirdPayCfg propertyCompanyThirdPayCfgQry = new PropertyCompanyThirdPayCfg();
			propertyCompanyThirdPayCfgQry.settPcId(pcId);
			List<PropertyCompanyThirdPayCfg> propertyCompanyThirdPayCfgByConditionList = propertyCompanyThirdPayCfgBaseService.getPropertyCompanyThirdPayCfgByConditionDim(MapConverter.toMap(propertyCompanyThirdPayCfgQry));
			for(PropertyCompanyThirdPayCfg propertyCompanyThirdPayCfg: propertyCompanyThirdPayCfgByConditionList){
				if(propertyCompanyThirdPayCfg.gettPcId().equals(UserContext.getUser().getPropertyCompanyId())
						&& propertyCompanyThirdPayCfg.gettPmId() == null) {
					request.setAttribute("payConfig", propertyCompanyThirdPayCfg);
					break;
				}
			}


			modelAndView.setViewName("/propertyCompany/propertyCompanyWorkbench");
		}else{
			request.setAttribute(JSPConstants.OprtResult, "跳转失败，您当前没有物业公司信息!");
			request.setAttribute(JSPConstants.ToURL, "../security/welcome.html");
			modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		}
		return modelAndView;
	}
	
	/**
	 * 新增或编辑管理处
	 * @param request
	 * @return
	 */
	@RequestMapping("/initEditMgt.html")
	public ModelAndView initEditMgt(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		//获取当前管理处的信息
		String mgtId = request.getParameter("id");
		if(StringUtils.isNotEmpty(mgtId)){
			request.setAttribute("mgt", propertyManagementService.selectPropertyManagementById(CnfantasiaCommUtil.convert2BigInteger(mgtId)));
		}

		PropertyCompanyThirdPayCfg propertyCompanyThirdPayCfgQry = new PropertyCompanyThirdPayCfg();
		propertyCompanyThirdPayCfgQry.settPmId(new BigInteger(mgtId));
		List<PropertyCompanyThirdPayCfg> propertyCompanyThirdPayCfgByConditionList = propertyCompanyThirdPayCfgBaseService.getPropertyCompanyThirdPayCfgByConditionDim(MapConverter.toMap(propertyCompanyThirdPayCfgQry));
		if(!propertyCompanyThirdPayCfgByConditionList.isEmpty())
			request.setAttribute("payConfig", propertyCompanyThirdPayCfgByConditionList.get(0));

		request.setAttribute("companyId",request.getParameter("companyId"));
		modelAndView.setViewName("/propertyCompany/propertyCompanyAddManagement");
		return modelAndView;
	}
	
	/**
	 * 新增或编辑管理处
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveEditMgt.html")
	public ModelAndView saveEditMgt(HttpServletRequest request) throws Exception {
		//获取当前管理处的信息
		String omsUserId = request.getParameter("omsUserId");
		String userAccount = request.getParameter("userAccount");
		String password = request.getParameter("password");
		
		PropertyManagement management = new PropertyManagement();
		management.setId(ParamUtils.getBigInteger(request, "managementId", null));
		management.setName(request.getParameter("managementName"));
		management.setTel(request.getParameter("managementTel"));
		management.settPropertyCompanyFId(ParamUtils.getBigInteger(request, "tPropertyCompanyFId", null));
		
		management.setPersonChargeName(request.getParameter("personChargeName"));
		management.setPersonChargeTel(request.getParameter("personChargeTel"));
		management.setAccountNo(ParamUtils.getString(request, "accountNo"));
		management.setAccountName(ParamUtils.getString(request, "accountName"));
		management.setBankName(ParamUtils.getString(request, "bankName"));
		management.setBankBranch(ParamUtils.getString(request, "bankBranch"));
		management.setBankProvince(ParamUtils.getString(request, "bankProvince"));
		management.setBankCity(ParamUtils.getString(request, "bankCity"));
		management.setIsOpenReveivablesEdit(ParamUtils.getInteger(request, "isOpenReceivables", 0));
		management.setSettlementDay(ParamUtils.getInteger(request, "settlementDay", null));
		management.setIsOpenReceivables(0);

		ModelAndView modelAndView = new ModelAndView(JSPConstants.OprtSuccessPage);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userAccount", userAccount);
		paramMap.put("sys0DelState", 0);
		List<OmsUser> omsUserList = omsUserService.getOmsUserByCondition(paramMap);

		for (OmsUser omsUser : omsUserList) {
			if (!omsUser.getId().toString().equals(omsUserId)) {
				request.setAttribute(JSPConstants.OprtResult, "已存在此账号，请重新分配账号");
				request.setAttribute(JSPConstants.ToURL, "../propertyCompany/workbench.html");
				return modelAndView;
			}
		}

		if(propertyManagementService.saveManagement(management, omsUserId, userAccount, password)>0){
			request.setAttribute(JSPConstants.OprtResult, "保存成功");
			request.setAttribute(JSPConstants.ToURL, "../propertyCompany/workbench.html");
		}else{
			request.setAttribute(JSPConstants.OprtResult, "保存失败");
			request.setAttribute(JSPConstants.ToURL, "../propertyCompany/initEditMgt.html");
		}

		return modelAndView;
	}
	
	/**
	 * 删除
	 * @param request
	 * @return
	 */
	@RequestMapping("/delManagement.html")
	@ResponseBody
	public String delManagement(String id) {
		propertyManagementService.deleteManagement(CnfantasiaCommUtil.convert2BigInteger(id));
		return "删除成功";
	}
	
	/**
	 * 申请高级合作模式
	 * @param request
	 * @return
	 */
	@RequestMapping("/initApplySupper.html")
	public ModelAndView initApplySupper(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		request.setAttribute("companyId",request.getParameter("companyId"));
		modelAndView.setViewName("/propertyCompany/propertyCompanyApply");
		return modelAndView;
	}
	
	/**
	 * 保存申请高级合作模式
	 * */
	@RequestMapping("/applySupper.html")
	public ModelAndView applySupper(HttpServletRequest request) throws Exception {
		//获取当前管理处的信息
		String id = request.getParameter("companyId");
		String photoBusinessLicense = "";//营业执照
		String photoCredentials = "";//资质证明

		if (request instanceof MultipartHttpServletRequest) {
			// 转型为Spring的MultipartHttpRequest(重点的所在)
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 根据前台的name名称得到上传的文件
			MultipartFile uploadImageFile = multipartRequest.getFile("photoBusinessLicense");
			//String filePath = "D:/Program Files/development/workspace_comm/apache-tomcat-6.0.39-8080/webapps/uploadImages/propertyCompanyImg";//本地测试路径
			String filePath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + PathConstants.PC_Image_Dir;
			//如果图片目录不存在则创建新的文件目录
			File fileDir = new File(filePath);
			if (!fileDir.exists()) {
				fileDir.mkdir();
			}
			if (uploadImageFile != null && StringUtils.isNotEmpty(uploadImageFile.getOriginalFilename())) {//有上传图片 
				//营业执照
				int indexOfDot = uploadImageFile.getOriginalFilename().indexOf(".");
				String fileNameBL = System.currentTimeMillis() + uploadImageFile.getOriginalFilename().substring(indexOfDot); //物业公司名作为前缀，防止文件重名
				File fileBL = new File(filePath + "/" + fileNameBL);
				photoBusinessLicense = PathConstants.PC_Image_Dir + fileNameBL;
				uploadImageFile.transferTo(fileBL);
			}

			//资质证明
			uploadImageFile = multipartRequest.getFile("photoCredentials");
			if (uploadImageFile != null && StringUtils.isNotEmpty(uploadImageFile.getOriginalFilename())) {//有上传图片 
				int indexOfDot = uploadImageFile.getOriginalFilename().indexOf(".");
				String fileNameC = (System.currentTimeMillis() + 1/*服务器太快了，可能1ms之内就处理完了，所以加个1*/) + uploadImageFile.getOriginalFilename().substring(indexOfDot); //物业公司名作为前缀，防止文件重名
				File fileC = new File(filePath + "/" + fileNameC);
				photoCredentials = PathConstants.PC_Image_Dir + fileNameC;
				uploadImageFile.transferTo(fileC);
			}
		}
		PropertyCompany pcEntity = new PropertyCompany();
		pcEntity.setId(CnfantasiaCommUtil.convert2BigInteger(id));
		pcEntity.setPhotoBusinessLicense(photoBusinessLicense);
		pcEntity.setPhotoCredentials(photoCredentials);
		pcEntity.setHighCooperationTime(CnfantasiaCommUtil.getCurrentTime());
		pcEntity.setIsHighCooperation(PropertyCompanyConstant.HC_Status.Apply);
		propertyCompanyService.savePropertyCompany(pcEntity);
		request.setAttribute(JSPConstants.OprtResult, "上传成功");
		request.setAttribute(JSPConstants.ToURL, "../propertyCompany/workbench.html");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		return modelAndView;
	}
	
	/**
	 * 申请合作查询
	 * */
	@RequestMapping("/applyAuditList.html")
	public ModelAndView applyAuditList(HttpServletRequest request) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pcName", request.getParameter("propertyCompanyName"));
		paramMap.put("auditStatus", request.getParameter("auditStatus"));
		int resultSize = propertyCompanyService.queryPropertyCompanyForApplyCount(paramMap);
		request.setAttribute("resultSize", resultSize);
		// 页数的参数名 
		String pageIndexName = new org.displaytag.util.ParamEncoder("row").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		List<PropertyCompanyEntity> searchRestList = propertyCompanyService.queryPropertyCompanyForApplyList(curPageIndex, pageSize, paramMap);
		request.setAttribute("resList", searchRestList);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/propertyCompany/propertyCompanyAuditList");
		return modelAndView;
	}
	
	/**
	 * 初始化物业合作审核
	 * */
	@RequestMapping("/initApplyAudit.html")
	public ModelAndView initApplyAudit(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		String id = request.getParameter("id");
		request.setAttribute("opType",request.getParameter("opType"));
		request.setAttribute("entity", propertyCompanyService.getPropertyCompanyBySeqId(CnfantasiaCommUtil.convert2BigInteger(id)));
		modelAndView.setViewName("/propertyCompany/propertyCompanyAudit");
		return modelAndView;
	}
	
	/**
	 * 审核申请高级合作模式
	 * */
	@RequestMapping("/auditApply.html")
	public ModelAndView auditApply(HttpServletRequest request) throws Exception {
		//获取当前管理处的信息
		String id = request.getParameter("companyId");
		String adminId = request.getParameter("adminId");
		String auditResult = request.getParameter("auditResult");//审核结果
		String desc = request.getParameter("desc");//失败原因
		propertyCompanyService.auditPropertyCompanyApply(id, adminId, auditResult, desc);
		request.setAttribute(JSPConstants.OprtResult, "保存成功");
		request.setAttribute(JSPConstants.ToURL, "../propertyCompany/applyAuditList.html");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		return modelAndView;
	}
	/**
	 * 修改物业公司信息
	 * @param modelMap
	 * @param companyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/editpcMsg.html")
	public String editPcMsg(ModelMap modelMap,String companyId) throws Exception {
		modelMap.addAttribute("companyId", companyId);
		PropertyCompany propertyCompany = propertyCompanyService.getPropertyCompanyBySeqId(CnfantasiaCommUtil.convert2BigInteger(companyId));
		String pcName = propertyCompany.getName();
		String pcMobilePhone = propertyCompany.getMobilePhone();
		String pcLinkman = propertyCompany.getLinkman();
		String pcTel = propertyCompany.getTel();
		modelMap.addAttribute("pcName", pcName);
		modelMap.addAttribute("pcMobilePhone", pcMobilePhone);
		modelMap.addAttribute("pcLinkman", pcLinkman);
		modelMap.addAttribute("pcTel", pcTel);
		modelMap.addAttribute("accountName", propertyCompany.getAccountName());
		modelMap.addAttribute("accountNo", propertyCompany.getAccountNo());
		modelMap.addAttribute("bankName", propertyCompany.getBankName());
		modelMap.addAttribute("bankBranch", propertyCompany.getBankBranch());
		modelMap.addAttribute("bankProvince", propertyCompany.getBankProvince());
		modelMap.addAttribute("bankCity", propertyCompany.getBankCity());
		modelMap.addAttribute("revenueDate", propertyCompany.getRevenueDate());
		return "/propertyCompany/register/propertyCompanyeditMsg";
	}
	/**
	 * 保存物业修改信息
	 * @param companyId
	 * @param pcName
	 * @param pcTel
	 * @param linkMan
	 * @param pcMobilephone
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveEditpcmsg.html")
	public String saveEditPcMsg(HttpServletRequest request) throws Exception {
		String companyId = ParamUtils.getString(request, "companyId", null);
		String pcName = ParamUtils.getString(request, "pcName", null);
		String pcTel = ParamUtils.getString(request, "pcTel", null);
		String linkMan = ParamUtils.getString(request, "linkMan", null);
		String pcMobilephone = ParamUtils.getString(request, "pcMobilephone", null);
		String accountName = ParamUtils.getString(request, "accountName", null);
		String accountNo = ParamUtils.getString(request, "accountNo", null);
		String bankName = ParamUtils.getString(request, "bankName", null);
		String bankBranch = ParamUtils.getString(request, "bankBranch", null);
		String bankProvince = ParamUtils.getString(request, "bankProvince", null);
		String bankCity = ParamUtils.getString(request, "bankCity", null);
		Integer revenueDate = ParamUtils.getInteger(request, "revenueDate", null);
		
		PropertyCompany propertyCompany = propertyCompanyService.getPropertyCompanyBySeqId(CnfantasiaCommUtil.convert2BigInteger(companyId));
		propertyCompany.setEditName(pcName);
		propertyCompany.setEditLinkman(linkMan);
		propertyCompany.setEditMobilePhone(pcMobilephone);
		propertyCompany.setEditTel(pcTel);
		
		propertyCompany.setEditAccountName(accountName);
		propertyCompany.setEditAccountNo(accountNo);
		propertyCompany.setEditBankName(bankName);
		
		propertyCompany.setEditBankBranch(bankBranch);
		propertyCompany.setEditBankProvince(bankProvince);
		propertyCompany.setEditBankCity(bankCity);
		propertyCompany.setEditRevenueDate(revenueDate);
		
		propertyCompany.setIsEdit(0);
		SimpleDateFormat dateForma = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = dateForma.format(new Date());
		propertyCompany.setSys0EditTime(today);
		propertyCompanyService.savePropertyCompany(propertyCompany);
		return "redirect:/propertyCompany/workbench.html";
	}
	
	/**
	 * 物业信息修改列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/editmsglist.html")
	public String editMsgList(HttpServletRequest request) throws Exception {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("editType", "-1");//默认全部类型
		handleEditListOrSearch(request, paramMap);
		return "/propertyCompany/register/pceditMsgList";
	}
	
	/**
	 * 物业修改审核
	 * @param pcId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/editAudit.html")
	public String editMsgaudit(String pcId,ModelMap model) throws Exception{
		PropertyCompany propertyCompany = propertyCompanyService.getPropertyCompanyBySeqId(CnfantasiaCommUtil.convert2BigInteger(pcId));
		model.addAttribute("pc", propertyCompany);
		return "/propertyCompany/register/pceditAudit";
	}
	
	/**
	 * 管理处修改审核
	 * @param pcId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/editPMAudit.html")
	public String editPMMsgaudit(String pmId,ModelMap model) throws Exception{
		PropertyManagement propertyManagement = propertyManagementService.getPropertyManagementBySeqId(CnfantasiaCommUtil.convert2BigInteger(pmId));
		model.addAttribute("pm", propertyManagement);
		
		PropertyCompany propertyCompany = propertyCompanyService.getPropertyCompanyBySeqId(propertyManagement.gettPropertyCompanyFId());
		model.addAttribute("pc", propertyCompany);
		
		return "/propertyManagement/pmEditAudit";
	}
	
	/**
	 * 保存修改信息
	 * @param pcId
	 * @param auditResult
	 * @param notPassReason
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveEditAuditResult.html")
	public String saveEditAudit(String pcId,String auditResult,String notPassReason) throws Exception{
		if(pcId!="" && pcId!=null){
			PropertyCompany propertyCompany = propertyCompanyService.getPropertyCompanyBySeqId(CnfantasiaCommUtil.convert2BigInteger(pcId));
			if(auditResult.equals("pass")){
				if(propertyCompany.getEditTel() !=null && !propertyCompany.getEditTel().equals("pass")){
					propertyCompany.setTel(propertyCompany.getEditTel());
				}
				if(propertyCompany.getEditLinkman() !=null && !propertyCompany.getEditLinkman().equals("pass")){
					propertyCompany.setLinkman(propertyCompany.getEditLinkman());
				}
				if(propertyCompany.getEditName() != null && !propertyCompany.getEditName().equals("pass")){
					propertyCompany.setName(propertyCompany.getEditName());
				}
				if(propertyCompany.getEditMobilePhone() != null && !propertyCompany.getEditMobilePhone().equals("pass")){
					propertyCompany.setMobilePhone(propertyCompany.getEditMobilePhone());
				}
				
				if(propertyCompany.getEditAccountName() != null && !propertyCompany.getEditAccountName().equals("pass")){
					propertyCompany.setAccountName(propertyCompany.getEditAccountName());
				}				
				if(propertyCompany.getEditAccountNo() != null && !propertyCompany.getEditAccountNo().equals("pass")){
					propertyCompany.setAccountNo(propertyCompany.getEditAccountNo());
				}
				if(propertyCompany.getEditBankName() != null && !propertyCompany.getEditBankName().equals("pass")){
					propertyCompany.setBankName(propertyCompany.getEditBankName());
				}
				
				if(propertyCompany.getEditBankBranch() != null && !propertyCompany.getEditBankBranch().equals("pass")){
					propertyCompany.setBankBranch(propertyCompany.getEditBankBranch());
				}
				if(propertyCompany.getEditBankProvince() != null && !propertyCompany.getEditBankProvince().equals("pass")){
					propertyCompany.setBankProvince(propertyCompany.getEditBankProvince());
				}
				if(propertyCompany.getEditBankCity() != null && !propertyCompany.getEditBankCity().equals("pass")){
					propertyCompany.setBankCity(propertyCompany.getEditBankCity());
				}
				if(propertyCompany.getEditRevenueDate() != null && propertyCompany.getEditRevenueDate() != 0){
					propertyCompany.setRevenueDate(propertyCompany.getEditRevenueDate());
				}
				
				propertyCompany.setEditLinkman("pass");
				propertyCompany.setEditMobilePhone("pass");
				propertyCompany.setEditName("pass");
				propertyCompany.setEditTel("pass");
				
				propertyCompany.setEditAccountName("pass");
				propertyCompany.setEditAccountNo("pass");
				propertyCompany.setEditBankName("pass");
				
				propertyCompany.setEditBankBranch("pass");
				propertyCompany.setEditBankProvince("pass");
				propertyCompany.setEditBankCity("pass");
				propertyCompany.setEditRevenueDate(0);
				
				propertyCompany.setIsEdit(1);
				propertyCompany.setEditResult("审批通过");
				propertyCompanyService.savePropertyCompany(propertyCompany);
			}else{
				propertyCompany.setIsEdit(2);
				propertyCompany.setEditResult(notPassReason);
				propertyCompanyService.savePropertyCompany(propertyCompany);
			}
		}
		return "redirect:/propertyCompany/editmsglist.html";
	}
	
	/**
	 * 保存管理处修改信息
	 * @param pmId
	 * @param auditResult
	 * @param notPassReason
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/savePMEditAuditResult.html")
	public String savePMEditAudit(String pmId,String auditResult,String notPassReason) throws Exception{
		if(pmId!="" && pmId!=null){
			PropertyManagement pm = propertyManagementService.selectPropertyManagementById(new BigInteger(pmId));
			if (auditResult.equals("pass")) {
				pm.setIsEdit(1);
				pm.setEditResult("审核通过");
				pm.setIsOpenReceivables(pm.getIsOpenReveivablesEdit());
			} else {
				pm.setIsEdit(2);
				pm.setEditResult(notPassReason);
			}
			
			propertyManagementService.updatePropertyManagement(pm);
		}
		
		return "redirect:/propertyCompany/editmsglist.html";
	}
	
	@RequestMapping("/editSearch.html")
	public String editSearch(HttpServletRequest request){
		String pcName = request.getParameter("pcName");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String isEdit = request.getParameter("isEdit");
		String editType = request.getParameter("editType");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pcName", pcName);
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		paramMap.put("editType", editType == null ? "-1" : editType);
		if (!"-1".equals(isEdit)) {
			paramMap.put("isEdit",isEdit) ;
		}
		handleEditListOrSearch(request, paramMap);
		return "/propertyCompany/register/pceditMsgList";
	}

	public void setChannelPartnerRecommendBaseService(ChannelPartnerRecommendBaseService channelPartnerRecommendBaseService) {
		this.channelPartnerRecommendBaseService = channelPartnerRecommendBaseService;
	}
}