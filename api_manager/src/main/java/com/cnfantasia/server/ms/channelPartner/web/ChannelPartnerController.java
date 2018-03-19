package com.cnfantasia.server.ms.channelPartner.web;

import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.business.commonBusiness.util.MailUtils;
import com.cnfantasia.server.business.pub.utils.CommonMultiFileUtil;
import com.cnfantasia.server.business.pub.utils.Md5Util;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.addressBlock.entity.AddressBlock;
import com.cnfantasia.server.domainbase.addressBlock.service.IAddressBlockBaseService;
import com.cnfantasia.server.domainbase.channelPartner.entity.ChannelPartner;
import com.cnfantasia.server.domainbase.channelPartnerRecommend.entity.ChannelPartnerRecommend;
import com.cnfantasia.server.domainbase.channelPartnerRecommend.service.ChannelPartnerRecommendBaseService;
import com.cnfantasia.server.domainbase.groupBuildingRegister.entity.GroupBuildingRegister;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.domainbase.omsUser.service.OmsUserBaseService;
import com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.entity.OmsUserHasTOmsPermiRole;
import com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.service.OmsUserHasTOmsPermiRoleBaseService;
import com.cnfantasia.server.domainbase.propertyCompany.entity.PropertyCompany;
import com.cnfantasia.server.domainbase.propertyCompany.service.PropertyCompanyBaseService;
import com.cnfantasia.server.ms.channelPartner.entity.ChannelPartnerDetailEntity;
import com.cnfantasia.server.ms.channelPartner.entity.ChannelPartnerEntity;
import com.cnfantasia.server.ms.channelPartner.entity.GroupBuildingRegisterEntiy;
import com.cnfantasia.server.ms.channelPartner.service.ChannelPartnerService;
import com.cnfantasia.server.ms.groupBuildingRegister.service.GroupBuildingRegisterService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.propertyCompany.service.IPropertyCompanyService;
import com.cnfantasia.server.ms.provinceCityBlock.entity.CityWithBlock;
import com.cnfantasia.server.ms.provinceCityBlock.entity.ProvinceWithCityBlock;
import com.cnfantasia.server.ms.provinceCityBlock.service.IProvinceCityBlockService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;
import com.cnfantasia.server.ms.pub.constant.PathConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.page.PageUtils;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.DateUtil;


/**
 * 渠道代理 Controller
 * 
 * @author wenfq
 */
@Controller
@RequestMapping("/channelPartner")
public class ChannelPartnerController extends BaseController {
	private static final String PERSON = "person";

	private static final String COMPANY = "company";

	private Log logger = LogFactory.getLog(getClass());

	@Resource
	private IProvinceCityBlockService provinceCityBlockService;
	
	@Resource
	private ChannelPartnerService channelPartnerService;

	@Resource
	private OmsUserBaseService omsUserBaseService;

	@Resource
	private IUuidManager uuidManager;

	@Resource
	private ChannelPartnerRecommendBaseService channelPartnerRecommendBaseService;

	@Resource
	private GroupBuildingRegisterService groupBuildingRegisterService;

	@Resource
	private PropertyCompanyBaseService propertyCompanyBaseService;

	@Resource
	private OmsUserHasTOmsPermiRoleBaseService omsUserHasTOmsPermiRoleBaseService;
	
	@Resource
	private IPropertyCompanyService propertyCompanyService;

	@Resource
	private IAddressBlockBaseService addressBlockBaseService;
	
	/**
	 * 推荐注册
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/register.html")
	public ModelAndView register(HttpServletRequest request) {
		String partnerType =  request.getParameter("partnerType");
		request.setAttribute("partnerType",partnerType);
		
		ModelAndView modelAndView = new ModelAndView();
		if(COMPANY.equals(partnerType)){//代理商注册
			modelAndView.setViewName("/channelPartner/cpRegister4Company");
		}else if(PERSON.equals(partnerType)){//代理人注册
			modelAndView.setViewName("/channelPartner/cpRegister4Person");
		}else {
			modelAndView.setViewName("error/404");
		}
		
		return modelAndView;
	}

	/**
	 * 推荐注册保存
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateChannelPartner.html")
	public ModelAndView updateChannelPartner(HttpServletRequest request) {
		
		//1收集参数
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String idNumber = request.getParameter("idNumber");
		String bankName = request.getParameter("bankName");
		String bankCardNo = request.getParameter("bankCardNo");
		String companyName = request.getParameter("companyName");
		String positiondesc = request.getParameter("positionDesc");
		String bankAccountName = request.getParameter("bankAccountName");
		String bankBranch = ParamUtils.getString(request, "bankBranch", null);
		String bankProvince = ParamUtils.getString(request, "bankProvince", null);
		String bankCity = ParamUtils.getString(request, "bankCity", null);
		Integer revenueDate = ParamUtils.getInteger(request, "revenueDate", 25);
		
		

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile photoimage = multipartRequest.getFile("photoimage");
		
		//2参数校验
		if(id==null){
			throw new IllegalArgumentException("Id can not be null");
		}
		
		ChannelPartner channelPartner = new ChannelPartner();
		channelPartner.setId(new BigInteger(id));
		channelPartner.setEmail(email);
		channelPartner.setName(name);
		channelPartner.setPhone(phone);
		channelPartner.setIdNumber(idNumber);
		channelPartner.setBankName(bankName);
		channelPartner.setBankCardNo(bankCardNo);
		channelPartner.setCompanyName(companyName);
		channelPartner.setPositiondesc(positiondesc);
		channelPartner.setBankAccountName(bankAccountName);
		channelPartner.setBankBranch(bankBranch);
		channelPartner.setBankProvince(bankProvince);
		channelPartner.setBankCity(bankCity);
		channelPartner.setRevenueDate(revenueDate);

		try {
			String businessLicense = CommonMultiFileUtil.uploadFile(photoimage, OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH),
					PathConstants.ChannelPartnerPath, "channelPartner", channelPartner.getId());
			channelPartner.setBusinessLicense(businessLicense);
		} catch (IOException e) {
			logger.info("channelPartner.businessLicense.upload.failure", e);
			e.printStackTrace();
		}

		int udpCount = channelPartnerService.updateChannelPartner(channelPartner);

		String partnerType = request.getParameter("partnerType"); //代理类型
		if(COMPANY.equals(partnerType)){//代理商
			return new ModelAndView("redirect:/channelPartner/cpCompanyView.html");
		}else if(PERSON.equals(partnerType)) {//代理人
			return new ModelAndView("redirect:/channelPartner/cpPersonView.html");
		}else{
			request.setAttribute(JSPConstants.OprtResult, "保存成功");
			request.setAttribute(JSPConstants.ToURL, "../security/welcome.html");
			return new ModelAndView(JSPConstants.OprtSuccessPage);
		}
	}
	
	/**
	 * 代理人-注册保存
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/savePerson.html")
	@Transactional
	public ModelAndView savePerson(HttpServletRequest request) {
		// 物业推荐人信息
		String name = request.getParameter("name");
		String cname = request.getParameter("cname");//公司名称
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String bankName = request.getParameter("bankName");
		String bankCardNo = request.getParameter("bankCardNo");
		String idNumber = request.getParameter("idNumber");
		String partnerType = request.getParameter("partnerType");
		String inviteCode = request.getParameter("inviteCode");//邀请码

		//后台账号
		String omsUserName = request.getParameter("omsUserName");
		String password = request.getParameter("password");
		
		/*
		//物业公司信息
				String pcName = request.getParameter("pcName");
				String pcQualifications = request.getParameter("pcQualifications");
				String residentCount = request.getParameter("residentCount");
				String pcAddress = request.getParameter("pcAddress");
				String pcLinkMan = request.getParameter("pcLinkMan");

		//小区数据 
		String[] gbNames = request.getParameterValues("gbName");//小区名称
		String[] gbAddrDesc = request.getParameterValues("gbAddrDesc");//小区详细地址
		String[] abIds = request.getParameterValues("abId");//小区所属哪个区
		*/

		//保存数据
		OmsUser omsUser = new OmsUser();
		omsUser.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_oms_user));
		omsUser.setUserAccount(omsUserName);
		omsUser.setIsadmin(0);//非后台管理员
		omsUser.setUserState(0L);//启用
		omsUser.setIsSubUser(0);//非子账号
		omsUser.setIsPmUser(0);
		omsUser.setPassword(Md5Util.MD5Twice(password));
		omsUserBaseService.insertOmsUser(omsUser);

		//给用户分配默认的代理人的角色
		if (OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.Channel_Partner_Person_Role_ID) != null) {
			OmsUserHasTOmsPermiRole omsUserHasTOmsPermiRole = new OmsUserHasTOmsPermiRole(); 
			omsUserHasTOmsPermiRole.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_oms_user_has_t_oms_permi_role));
			omsUserHasTOmsPermiRole.settOmsUserFId(omsUser.getId());
			String tOmsPermiRoleFId = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.Channel_Partner_Person_Role_ID);
			omsUserHasTOmsPermiRole.settOmsPermiRoleFId(new BigInteger(tOmsPermiRoleFId));
			omsUserHasTOmsPermiRoleBaseService.insertOmsUserHasTOmsPermiRole(omsUserHasTOmsPermiRole);
		}

		ChannelPartner channelPartner = new ChannelPartner();
		channelPartner.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_channel_partner));
		channelPartner.setName(name);
		channelPartner.setCompanyName(cname);
		channelPartner.setPhone(phone);
		channelPartner.setEmail(email);
		channelPartner.setBankName(bankName);
		channelPartner.setBankCardNo(bankCardNo);
		channelPartner.setIdNumber(idNumber);
		channelPartner.setPartnertype(partnerType);
		channelPartner.setInviteCode(inviteCode);
		channelPartner.settOmsUserFId(omsUser.getId());
		channelPartner.setSys0AddUser(omsUser.getId());
		channelPartnerService.insertChannelPartner(channelPartner);
		
		/*
		ChannelPartnerRecommend channelPartnerRecommend = new ChannelPartnerRecommend();
		channelPartnerRecommend.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_channel_partner_recommend));
		channelPartnerRecommend.setPcName(pcName);
		channelPartnerRecommend.setPropertyQualifications(Integer.parseInt(pcQualifications));
		channelPartnerRecommend.setResidentCount(Long.parseLong(residentCount));
		channelPartnerRecommend.setAddress(pcAddress);
		channelPartnerRecommend.setLinkman(pcLinkMan);
		channelPartnerRecommend.settChannelPartnerFId(channelPartner.getId());
		channelPartnerRecommend.setSys0AddUser(omsUser.getId());
		channelPartnerRecommendBaseService.insertChannelPartnerRecommend(channelPartnerRecommend);

		List<GroupBuildingRegister> groupBuildingRegisterList = new ArrayList<GroupBuildingRegister>();
		//忽略第一个无效数据 
		for (int i = 1; gbNames != null && i < gbNames.length; i++) {
			GroupBuildingRegister groupBuildingRegister = new GroupBuildingRegister();
			groupBuildingRegister.settChannelPartnerRecommendFId(channelPartnerRecommend.getId());
			groupBuildingRegister.setName(gbNames[i]);
			groupBuildingRegister.setAddressDesc(gbAddrDesc[i]);
			groupBuildingRegister.settAddressBlockFId(new BigInteger(abIds[i]));
			groupBuildingRegister.setSys0AddUser(omsUser.getId());
			groupBuildingRegisterList.add(groupBuildingRegister);
		}
		
		if (groupBuildingRegisterList.size() > 0) {
			groupBuildingRegisterService.insertGroupBuildingRegisterBatch(groupBuildingRegisterList);
		}
		 */
	

		sendEmail(omsUser.getUserAccount(), password, email);

		request.setAttribute("omsUserName", omsUser.getUserAccount());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/channelPartner/cpRegisterSuccess");
		return modelAndView;
	}

	/**
	 * 代理公司-注册保存
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveCompany.html")
	@Transactional
	public ModelAndView saveCompany(HttpServletRequest request) {
		// 物业推荐人信息
		String name = request.getParameter("name");
		String cname = request.getParameter("cname");//公司名称
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String bankName = request.getParameter("bankName");
		String bankCardNo = request.getParameter("bankCardNo");
		String idNumber = request.getParameter("idNumber");
		String partnerType = request.getParameter("partnerType");

		//后台账号
		String omsUserName = request.getParameter("omsUserName");
		String password = request.getParameter("password");
		
		//物业公司信息
		/*		String pcName = request.getParameter("pcName");
				String pcQualifications = request.getParameter("pcQualifications");
				String residentCount = request.getParameter("residentCount");
				String pcAddress = request.getParameter("pcAddress");
				String pcLinkMan = request.getParameter("pcLinkMan");*/

		//小区数据 
		String[] gbNames = request.getParameterValues("gbName");//小区名称
		String[] gbAddrDesc = request.getParameterValues("gbAddrDesc");//小区详细地址
		String[] abIds = request.getParameterValues("abId");//小区所属哪个区

		//保存数据
		OmsUser omsUser = new OmsUser();
		omsUser.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_oms_user));
		omsUser.setUserAccount(omsUserName);
		omsUser.setIsadmin(0);//非后台管理员
		omsUser.setUserState(0L);//启用
		omsUser.setIsSubUser(0);//非子账号
		omsUser.setIsPmUser(0);
		omsUser.setPassword(Md5Util.MD5Twice(password));
		omsUserBaseService.insertOmsUser(omsUser);

		//给用户分配默认的代理公司的角色 
		if (OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.Channel_Partner_Company_Role_ID) != null) {
			OmsUserHasTOmsPermiRole omsUserHasTOmsPermiRole = new OmsUserHasTOmsPermiRole(); 
			omsUserHasTOmsPermiRole.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_oms_user_has_t_oms_permi_role));
			omsUserHasTOmsPermiRole.settOmsUserFId(omsUser.getId());
			String tOmsPermiRoleFId = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.Channel_Partner_Company_Role_ID);
			omsUserHasTOmsPermiRole.settOmsPermiRoleFId(new BigInteger(tOmsPermiRoleFId));
			omsUserHasTOmsPermiRoleBaseService.insertOmsUserHasTOmsPermiRole(omsUserHasTOmsPermiRole);
		}

		ChannelPartner channelPartner = new ChannelPartner();
		channelPartner.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_channel_partner));
		channelPartner.setName(name);
		channelPartner.setCompanyName(cname);
		channelPartner.setPhone(phone);
		channelPartner.setEmail(email);
		channelPartner.setBankName(bankName);
		channelPartner.setBankCardNo(bankCardNo);
		channelPartner.setIdNumber(idNumber);
		channelPartner.setPartnertype(partnerType);
		channelPartner.settOmsUserFId(omsUser.getId());
		channelPartner.setSys0AddUser(omsUser.getId());
		channelPartnerService.insertChannelPartner(channelPartner);
		
		/*
		ChannelPartnerRecommend channelPartnerRecommend = new ChannelPartnerRecommend();
		channelPartnerRecommend.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_channel_partner_recommend));
		channelPartnerRecommend.setPcName(pcName);
		channelPartnerRecommend.setPropertyQualifications(Integer.parseInt(pcQualifications));
		channelPartnerRecommend.setResidentCount(Long.parseLong(residentCount));
		channelPartnerRecommend.setAddress(pcAddress);
		channelPartnerRecommend.setLinkman(pcLinkMan);
		channelPartnerRecommend.settChannelPartnerFId(channelPartner.getId());
		channelPartnerRecommend.setSys0AddUser(omsUser.getId());
		channelPartnerRecommendBaseService.insertChannelPartnerRecommend(channelPartnerRecommend);

		List<GroupBuildingRegister> groupBuildingRegisterList = new ArrayList<GroupBuildingRegister>();
		//忽略第一个无效数据 
		for (int i = 1; gbNames != null && i < gbNames.length; i++) {
			GroupBuildingRegister groupBuildingRegister = new GroupBuildingRegister();
			groupBuildingRegister.settChannelPartnerRecommendFId(channelPartnerRecommend.getId());
			groupBuildingRegister.setName(gbNames[i]);
			groupBuildingRegister.setAddressDesc(gbAddrDesc[i]);
			groupBuildingRegister.settAddressBlockFId(new BigInteger(abIds[i]));
			groupBuildingRegister.setSys0AddUser(omsUser.getId());
			groupBuildingRegisterList.add(groupBuildingRegister);
		}
		if (groupBuildingRegisterList.size() > 0) {
			groupBuildingRegisterService.insertGroupBuildingRegisterBatch(groupBuildingRegisterList);
		}
		*/

		sendEmail(omsUser.getUserAccount(), password, email);

		request.setAttribute("omsUserName", omsUser.getUserAccount());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/channelPartner/cpRegisterSuccess");
		return modelAndView;
	}

	/**
	 * 代理商列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/listCompany.html")
	public ModelAndView listCPCompany(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("partnerType", COMPANY);
		handleListOrSearch(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/channelPartner/cpCompanyList");
		return modelAndView;
	}
	
	/**
	 * 代理人列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/listPerson.html")
	public ModelAndView listCPPerson(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("partnerType", PERSON);
		handleListOrSearch(request, paramMap);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/channelPartner/cpPersonList");
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
		int resultSize = channelPartnerService.getChannelPartnerCount(paramMap);
		request.setAttribute("resultSize", resultSize);

		PageUtils.addPageInfoToParam(request, paramMap);

		List<ChannelPartnerEntity> searchRestList = channelPartnerService.getChannelPartnerList_forPage(paramMap);
		request.setAttribute("resList", searchRestList);
	}

	/**
	 * 代理商查找
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/searchCPCompany.html")
	public ModelAndView searchCPCompany(HttpServletRequest request) {
		Map<String, Object> paramMap = prepareSearchParam(request);
		paramMap.put("partnerType", COMPANY);
		handleListOrSearch(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addAllObjects(paramMap);
		modelAndView.setViewName("/channelPartner/cpCompanyList");
		return modelAndView;
	}

	private Map<String, Object> prepareSearchParam(HttpServletRequest request) {
		String name = ParamUtils.getString(request, "name");
		String phone = ParamUtils.getString(request, "phone");
		String omsUserName = ParamUtils.getString(request, "omsUserName");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", name);
		paramMap.put("phone", phone);
		paramMap.put("omsUserName", omsUserName);
		return paramMap;
	}
	
	/**
	 * 代理人查找
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/searchCPPerson.html")
	public ModelAndView searchCPPerson(HttpServletRequest request) {
		Map<String, Object> paramMap = prepareSearchParam(request);
		paramMap.put("partnerType", PERSON);
		
		handleListOrSearch(request, paramMap);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addAllObjects(paramMap);
		modelAndView.setViewName("/channelPartner/cpPersonList");
		return modelAndView;
	}


	/**
	 * 查看
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/view.html")
	public ModelAndView view(HttpServletRequest request) {

		String cpId = request.getParameter("id");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", cpId);
		if (cpId == null && UserContext.getCurrUser().getIsadmin() == 1) {//管理员，从列表去看详情
			request.setAttribute(JSPConstants.OprtResult, "解放区用户，请去代理列表查看详情");
			request.setAttribute(JSPConstants.ToURL, "../security/welcome.html");
			return new ModelAndView(JSPConstants.OprtSuccessPage);
		}

		if (UserContext.getCurrUser().getIsadmin() == 0) {//非管理员，只能看到自己的
			paramMap.put("omsUserId", UserContext.getCurrUser().getId());
		}

		ChannelPartnerDetailEntity cpDetail = channelPartnerService.select_cp_detail(paramMap);
		request.setAttribute("cpDetail", cpDetail);
		request.setAttribute("channelPartnerRecommendList", cpDetail.getChannelPartnerRecommendList());
		request.setAttribute("channelPartnerRelevanceCompanyList", channelPartnerService.selectRelevanceCompanyList(paramMap));
		request.setAttribute("isAgent", false);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/channelPartner/cpCompanyViewDetail");
		return modelAndView;
	}
	
	/**
	 * 个人代理-查看与维护
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/cpPersonView.html")
	public ModelAndView cpPersonView(HttpServletRequest request) {
		
		String cpId = request.getParameter("id");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", cpId);

		if (UserContext.getCurrUser().getIsadmin() == 0) {//非管理员，只能看到自己的
			paramMap.put("omsUserId", UserContext.getCurrUser().getId());
		}else{
			request.setAttribute(JSPConstants.OprtResult, "解放区用户，请去代理列表查看详情");
			request.setAttribute(JSPConstants.ToURL, "../security/welcome.html");
			return new ModelAndView(JSPConstants.OprtSuccessPage);
		}
		
		ChannelPartnerDetailEntity cpDetail = channelPartnerService.select_cp_detail(paramMap);
		request.setAttribute("cpDetail", cpDetail);
		request.setAttribute("channelPartnerRecommendList", cpDetail.getChannelPartnerRecommendList());
		request.setAttribute("isAgent", true);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/channelPartner/cpPersonViewDetail");
		return modelAndView;
	}
	
	/**
	 * 公司代理-查看与维护
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/cpCompanyView.html")
	public ModelAndView cpCompanyView(HttpServletRequest request) {
		
		String cpId = request.getParameter("id");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", cpId);
		
		if (UserContext.getCurrUser().getIsadmin() == 0) {//非管理员，只能看到自己的
			paramMap.put("omsUserId", UserContext.getCurrUser().getId());
		}else{
			request.setAttribute(JSPConstants.OprtResult, "解放区用户，请去代理列表查看详情");
			request.setAttribute(JSPConstants.ToURL, "../security/welcome.html");
			return new ModelAndView(JSPConstants.OprtSuccessPage);
		}
		
		ChannelPartnerDetailEntity cpDetail = channelPartnerService.select_cp_detail(paramMap);
		request.setAttribute("cpDetail", cpDetail);
		request.setAttribute("channelPartnerRecommendList", cpDetail.getChannelPartnerRecommendList());
		request.setAttribute("channelPartnerRelevanceCompanyList", channelPartnerService.selectRelevanceCompanyList(paramMap));
		request.setAttribute("isAgent", true);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/channelPartner/cpCompanyViewDetail");
		return modelAndView;
	}
	
	/**
	 * 物业公司--管辖小区查看
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/cpGroupBuildingViewDetail.html")
	public ModelAndView cpGroupBuildingViewDetail(HttpServletRequest request) {
		
		String cpId = request.getParameter("companyId");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", cpId);
		
		//查询物业公司信息
		PropertyCompany company = propertyCompanyBaseService.getPropertyCompanyBySeqId(BigInteger.valueOf(Long.parseLong(cpId)));
		//查询物业公司下属小区信息
		List<Map<String, Object>> gbrList = propertyCompanyService.select_gbList_ByPCId(BigInteger.valueOf(Long.parseLong(cpId)));
		
		request.setAttribute("company", company);
		request.setAttribute("gbrList", gbrList);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/channelPartner/cpGroupBuildingViewDetail");
		return modelAndView;
	}

	/**
	 * 新增推荐
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addNewChannelPartnerRecommend.html")
	public ModelAndView addNewChannelPartnerRecommend(HttpServletRequest request) {
		String cpId = request.getParameter("cpId");
		request.setAttribute("cpId", cpId);

		request.setAttribute("pcbList", provinceCityBlockService.getProvinceWithCityBlockList());

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/channelPartner/cpAddRecommand");
		return modelAndView;
	}

	/**
	 * 保存新增推荐
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveNewChannelPartnerRecommend.html")
	@Transactional
	public ModelAndView saveNewChannelPartnerRecommend(HttpServletRequest request) {
		String cpId = request.getParameter("cpId");

		ChannelPartnerRecommend channelPartnerRecommend = saveChannelParterRecommend(request, cpId);

		saveGroupBuildingRegister(request, channelPartnerRecommend.getId());

		request.setAttribute("id", cpId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/channelPartner/cpCompanyView.html");
		return modelAndView;
	}
	
	/**
	 * 保存补充推荐的小区
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveSupplementGroupBuilding.html")
	@Transactional
	public ModelAndView saveSupplementGroupBuilding(HttpServletRequest request) {
		String cprId = request.getParameter("cprId");

		saveGroupBuildingRegister(request, new BigInteger(cprId));

		return new ModelAndView("redirect:/channelPartner/viewGroupBuildingRegister.html?cprId="+cprId);
	}

	private ChannelPartnerRecommend saveChannelParterRecommend(HttpServletRequest request, String cpId) {
		//物业公司信息
		String pcName = request.getParameter("pcName");
		String pcQualifications = request.getParameter("pcQualifications");
		String residentCount = request.getParameter("residentCount");
		String pcAddress = request.getParameter("pcAddress");
		String pcLinkMan = request.getParameter("pcLinkMan");


		ChannelPartnerRecommend channelPartnerRecommend = new ChannelPartnerRecommend();
		channelPartnerRecommend.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_channel_partner_recommend));
		channelPartnerRecommend.setPcName(pcName);
		channelPartnerRecommend.setPropertyQualifications(Integer.parseInt(pcQualifications));
		channelPartnerRecommend.setResidentCount(Long.parseLong(residentCount));
		channelPartnerRecommend.setAddress(pcAddress);
		channelPartnerRecommend.setLinkman(pcLinkMan);
		channelPartnerRecommend.settChannelPartnerFId(new BigInteger(cpId));
		channelPartnerRecommend.setSys0AddUser(UserContext.getCurrUser().getId());
		channelPartnerRecommend.setIsRelevance(0);//新增推荐的物业公司默认为没有进行管理员关联
		channelPartnerRecommendBaseService.insertChannelPartnerRecommend(channelPartnerRecommend);
		return channelPartnerRecommend;
	}

	private void saveGroupBuildingRegister(HttpServletRequest request, BigInteger cprId) {
		//小区数据 
		String[] gbNames = request.getParameterValues("gbName");//小区名称
		String[] gbAddrDesc = request.getParameterValues("gbAddrDesc");//小区详细地址
		String[] abIds = request.getParameterValues("abId");//小区所属哪个区
		List<GroupBuildingRegister> groupBuildingRegisterList = new ArrayList<GroupBuildingRegister>();
		for (int i = 1/* 忽略第一个无效数据 */; i < gbNames.length; i++) {
			GroupBuildingRegister groupBuildingRegister = new GroupBuildingRegister();
			groupBuildingRegister.settChannelPartnerRecommendFId(cprId);
			groupBuildingRegister.setName(gbNames[i]);
			groupBuildingRegister.setAddressDesc(gbAddrDesc[i]);
			groupBuildingRegister.settAddressBlockFId(new BigInteger(abIds[i]));
			groupBuildingRegister.setSys0AddUser(UserContext.getCurrUser().getId());
			groupBuildingRegister.setAuditstatus(0);//待审核
			groupBuildingRegisterList.add(groupBuildingRegister);
		}
		if (groupBuildingRegisterList.size() > 0) {
			groupBuildingRegisterService.insertGroupBuildingRegisterBatch(groupBuildingRegisterList);
		}
	}
	
	/**
	 * 编辑推荐小区
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/initGroupBuildingRegisterEdit.html")
	public ModelAndView initGroupBuildingRegisterEdit(HttpServletRequest request) {
		String gbrId = request.getParameter("gbrId");
		String cprId = request.getParameter("cprId");
		
		GroupBuildingRegister gbr = groupBuildingRegisterService.getGroupBuildingRegisterBySeqId(new BigInteger(gbrId));
		gbr.gettAddressBlockFId();
		
		request.setAttribute("gbr", gbr);
		request.setAttribute("cprId", cprId);
		List<ProvinceWithCityBlock> pcbList = provinceCityBlockService.getProvinceWithCityBlockList();
		request.setAttribute("pcbList", provinceCityBlockService.getProvinceWithCityBlockList());
		
		outLoop:
		for(ProvinceWithCityBlock pcb: pcbList){
			for(CityWithBlock city: pcb.getCityList()){
				for(AddressBlock ab: city.getBlockList()){
					if(ab.getId().equals(gbr.gettAddressBlockFId())){
						request.setAttribute("pId", pcb.getId());
						request.setAttribute("pName", pcb.getName());
						request.setAttribute("cId", city.getId());
						request.setAttribute("cName", city.getName());
						request.setAttribute("abId", ab.getId());
						request.setAttribute("abName", ab.getName());
						break outLoop;
					}
				}
			}
		}
		
		return new ModelAndView("/channelPartner/cpEditGroupBuildingRegister");
	}
	
	/**
	 * 更新推荐小区
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateGroupBuildingRegister.html")
	public ModelAndView updateGroupBuildingRegister(HttpServletRequest request) {
		String gbrId = request.getParameter("gbrId");
		String cprId = request.getParameter("cprId");
		String addressDesc = request.getParameter("gbAddrDesc");
		String name = request.getParameter("gbName");
		String abId = request.getParameter("abId");
		
		//校验小区是否被其它物业公司管辖
		AddressBlock addressBlock = addressBlockBaseService.getAddressBlockBySeqId(BigInteger.valueOf(Long.parseLong(abId)));
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("gbName", name);
		paramMap.put("blockName", addressBlock.getName());
		
		if (channelPartnerService.getGroupBuildingCount(paramMap) > 0) {
			request.setAttribute(JSPConstants.OprtResult, "该小区已与解放区合作，请推荐其它小区");
			request.setAttribute(JSPConstants.ToURL, "../channelPartner/initGroupBuildingRegisterEdit.html?gbrId="+gbrId+"&cprId="+cprId);
			return new ModelAndView(JSPConstants.OprtSuccessPage);
		}
		
		GroupBuildingRegister gbr = new GroupBuildingRegister();
		gbr.setId(new BigInteger(gbrId));
		gbr.setAddressDesc(addressDesc);
		gbr.setName(name);
		gbr.setAuditstatus(0);
		gbr.settAddressBlockFId(addressBlock.getId());
		groupBuildingRegisterService.updateGroupBuildingRegister(gbr);
		
		return new ModelAndView("redirect:/channelPartner/viewGroupBuildingRegister.html?cprId="+cprId);
	}

	/**
	 * 补充推荐小区
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/initGroupBuildingSupplement.html")
	public ModelAndView initGroupBuildingSupplement(HttpServletRequest request) {
		String id = request.getParameter("id");
		ChannelPartnerRecommend cpr = channelPartnerRecommendBaseService.getChannelPartnerRecommendBySeqId(new BigInteger(id));
		request.setAttribute("cpr", cpr);
		request.setAttribute("pcbList", provinceCityBlockService.getProvinceWithCityBlockList());
		
		return new ModelAndView("/channelPartner/cpSupplementGroupBuilding");
	}
	/**
	 * 查看物业公司所管辖的小区
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/viewGroupBuildingRegister.html")
	public ModelAndView viewGroupBuilding(HttpServletRequest request) {
		String cprId = request.getParameter("cprId");

		List<GroupBuildingRegisterEntiy> gbrList = channelPartnerService.select_gbr_by_cprId(cprId);
		request.setAttribute("resList", gbrList);
		request.setAttribute("cprId", cprId);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/channelPartner/cpViewGroupBuilding");
		return modelAndView;
	}

	/**
	 * 验证待注册的物业公司是否合法
	 * 
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/verfyPCName.html")
	@ResponseBody
	public String verfyPCName(HttpServletRequest request) throws ParseException {
		String pcName = request.getParameter("pcName");
		String resultInfo = "验证通过";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", pcName);
		paramMap.put("isAllCooperation", 1);
		if (propertyCompanyBaseService.getPropertyCompanyCount(paramMap) > 0) {
			resultInfo = "该物业公司已签约，不能再推荐";
			return resultInfo;
		}

		paramMap.clear();
		paramMap.put("pcName", pcName);
		List<ChannelPartnerRecommend> cprList = channelPartnerRecommendBaseService.getChannelPartnerRecommendByCondition(paramMap);
		for (int i = 0; i < cprList.size(); i++) {
			Date createTime = DateUtil.formatSecond.parse(cprList.get(i).getSys0AddTime());

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(createTime);
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 2);//让月份加2个月

			if (new Date().before(calendar.getTime())) {
				resultInfo = "该物业公司已被其它推荐人锁定，请推荐其它物业公司";
				return resultInfo;
			}
		}

		return resultInfo;
	}
	
	
	/**
	 * 验证待提交的小区是否被其它物业公司管辖
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/verfyGB.html")
	@ResponseBody
	public String verfyGB(HttpServletRequest request) throws ParseException {
		String gbName = request.getParameter("gbName"); //小区名
		String blockName = request.getParameter("blockVal");//区名
		String resultInfo = "验证通过";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("gbName", gbName);
		paramMap.put("blockName", blockName);
		
		if (channelPartnerService.getGroupBuildingCount(paramMap) > 0) {
			resultInfo = "该小区已与解放区合作，请推荐其它小区";
			return resultInfo;
		}
		
		return resultInfo;
	}
	
	/**
	 * 删除推荐的小区
	 */
	@RequestMapping("/deleteGbrById.html")
	@ResponseBody
	public String deleteGbrById(HttpServletRequest request) throws ParseException {
		String id = request.getParameter("id");
		int updCount = groupBuildingRegisterService.deleteGroupBuildingRegisterLogic(new BigInteger(id));

		return updCount > 0 ? "删除成功" : "删除失败";
	}
	
	/**
	 * 发送邮件告知用户密码
	 */
	private void sendEmail(String userName, String password, String email) {
		StringBuilder sb = new StringBuilder("刚注册的用户信息如下：\n");
		sb.append("用户名：").append(userName);
		sb.append("  密码：").append(password);
		sb.append(" , 请妥善保存");
		logger.info("sending email: " + sb);
		MailUtils.sendMail("解放区账号注册成功", sb.toString(), email);
	}
	
	/**
	 * 查询物业公司可配置的代理商/代理人
	 */
	@RequestMapping("/getCompanyChannelPartnerList.json")
	@ResponseBody
	public String getCompanyChannelPartnerList(HttpServletRequest request){
		//1.搜集参数
		String cpName = request.getParameter("cpName");
		String channelType = request.getParameter("channelType");//代理商/代理人
		//2.交互
		List<Map<Long, String>> list = channelPartnerService.getChannelPartnerList_ByCpnameAndType(cpName, channelType);
		//3.结果处理
		String htmlOption = "";
		for (Map<Long, String> map : list) {
			htmlOption += "<option value=\""+String.valueOf(map.get("id"))+"\">"+map.get("name1")+"</option>";
		}
		
		return htmlOption;
	}

	public void setAddressBlockBaseService(IAddressBlockBaseService addressBlockBaseService) {
		this.addressBlockBaseService = addressBlockBaseService;
	}
}
