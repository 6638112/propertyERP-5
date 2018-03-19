package com.cnfantasia.server.ms.propertyCompany.web;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;

import javax.servlet.http.HttpServletRequest;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.commonBusiness.util.MailUtils;
import com.cnfantasia.server.business.commonBusiness.util.SendSmsRunnable;
import com.cnfantasia.server.common.utils.PinyinUtil;
import com.cnfantasia.server.domainbase.addressBlock.entity.AddressBlock;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuildingRegister.entity.GroupBuildingRegister;
import com.cnfantasia.server.domainbase.propertyCompany.entity.PropertyCompany;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.groupBuilding.service.IGroupBuildingService;
import com.cnfantasia.server.ms.groupBuildingRegister.service.IGroupBuildingRegisterService;
import com.cnfantasia.server.ms.omsPermiRole.service.IOmsPermiRoleService;
import com.cnfantasia.server.ms.omsUser.service.IOmsUserService;
import com.cnfantasia.server.ms.propertyCompany.entity.PropertyCompanyEntity;
import com.cnfantasia.server.ms.propertyCompany.service.IPropertyCompanyService;
import com.cnfantasia.server.ms.provinceCityBlock.entity.CityWithBlock;
import com.cnfantasia.server.ms.provinceCityBlock.service.IProvinceCityBlockService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.DateUtil;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;

@Controller
@RequestMapping("/propertyCompany")
public class PropertyCompanyController extends BaseController{
	private static final String auditPassEmailInfo = "亲爱的用户您好！您在解放区申请资料已通过审核，已为您开通了使用账号，可使用账号登录系统使用发布公告功能。<br>账号：{0}<br>初始密码：{1}<br>系统地址：http://oos.jiefangqu.com   <br>欢迎您对解放区的支持，如有问题可拨打我们的热线：0755-22690915";

	private static final String auditPassSMSInfo = "您在解放区申请资料已通过审核，可使用账号登录系统使用发布公告功能。账号：{0}初始密码：{1}系统地址：http://oos.jiefangqu.com";

	private static final String auditNotPassEmailInfo = "亲爱的用户您好！您在解放区申请开通发布公告申请资料没有通过审核，不通过原因：{0}，您可登录http://oos.jiefangqu.com重新提交申请。<br>感谢您对解放区的支持，如有问题也可拨打我们的热线：0755-22690915";

	private static final String auditNotPassSMSInfo = "您在解放区申请开通发布公告申请资料没有通过审核，不通过原因：{0}，您可登录http://oos.jiefangqu.com";

	private Log logger = LogFactory.getLog(getClass());
	
	private IPropertyCompanyService propertyCompanyService;

	private IProvinceCityBlockService provinceCityBlockService;

	private IGroupBuildingRegisterService groupBuildingRegisterService;

	private IOmsUserService omsUserService;

	private IOmsPermiRoleService omsPermiRoleService;

	private IGroupBuildingService groupBuildingService;

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

	/**
	 * 列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/list.html")
	public ModelAndView list(HttpServletRequest request) {
		BigInteger omsUserId = UserContext.getCurrUser().getId();
		if (omsUserId.intValue() != 1) {//不是管理员，只能管理自己的物业公司，直接跳到Edit界面
			return edit(request);
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		handleListOrSearch(request, paramMap);

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
		int resultSize = propertyCompanyService.getPCList4admin_forCount(paramMap);
		request.setAttribute("resultSize", resultSize);

		// 页数的参数名 
		String pageIndexName = new org.displaytag.util.ParamEncoder("row").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = 10;
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);

		List<PropertyCompanyEntity> searchRestList = propertyCompanyService.getPCList4admin_forPage(curPageIndex, pageSize, paramMap);

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
		paramMap.put("f_sys0_del_state", 0);
		request.setAttribute("roleList", omsPermiRoleService.getOmsPermiRoleByCondition(paramMap));

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

		paramMap.clear();
		paramMap.put("f_sys0_del_state", 0);
		request.setAttribute("roleList", omsPermiRoleService.getOmsPermiRoleByCondition(paramMap));

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/propertyCompany/register/pcRegisterAuditView");
		return modelAndView;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete.html")
	@ResponseBody
	public String delete(HttpServletRequest request) {
		BigInteger pcId = new BigInteger(request.getParameter("pcId"));

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", pcId);
		PropertyCompany pc = new PropertyCompany();
		pc.setId(pcId);
		pc.setSys0DelState(1);
		pc.setSys0DelTime(DateUtil.formatSecond.format(new Date()));
		int updateCount = propertyCompanyService.updatePropertyCompany(pc);

		return updateCount == 1 ? "删除成功" : "删除失败";
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

		//1 更新物业公司审批信息字段
		PropertyCompanyEntity pcEntity = new PropertyCompanyEntity();
		pcEntity.setId(pcId);
		if ("pass".equals(auditResult)) {
			pcEntity.setAuditResult("审批通过");
			pcEntity.setIsAudited(1);
			pcEntity.setLightCooperationTime(DateUtil.formatSecond.format(new Date()));
		} else {
			pcEntity.setIsAudited(0);
			pcEntity.setAuditResult(notPassReason);
		}
		propertyCompanyService.savePropertyCompany(pcEntity);
		if ("notpass".equals(auditResult)) {
			sendSMS(mobile, MessageFormat.format(auditNotPassSMSInfo, notPassReason));
			MailUtils.sendMail("解放区账号审核未通过", MessageFormat.format(auditNotPassEmailInfo, notPassReason), email, "");
			request.setAttribute(JSPConstants.OprtResult, "审批结果保存成功");
			request.setAttribute(JSPConstants.ToURL, "../propertyCompany/list.html");
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName(JSPConstants.OprtSuccessPage);
			return modelAndView;
		}

		//2 新增后台账号，并分配给其角色
		OmsUser omsUser = new OmsUser();
		omsUser.setUserState(1L);
		omsUser.setSys0AddUser(UserContext.getCurrUser().getId());
		omsUser.setUserAccount(omsUserName);
		omsUser.setPassword(omsUserName);
		String[] roldIds = { roldId, };
		omsUserService.saveOmsUser(omsUser, roldIds);
		pcEntity.setAdminId(omsUser.getId());
		propertyCompanyService.savePropertyCompany(pcEntity);

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
				gbList.get(j).setAddressDesc(gbrList.get(i).get("gbrAddressdesc").toString());
				gbList.get(j).settPropertyCompanyFId(pcId);
				gbList.get(j).setCheckStatus(1);//审核通过
				groupBuildingService.updateGroupBuilding(gbList.get(j));
			}
			gbWillBeUpdatedList.addAll(gbList);
			if (!gbList.isEmpty())
				gbrList.remove(i);
		}
		//gbrList 现在剩下的就是待插入的数据
		//		if (!gbWillBeUpdatedList.isEmpty())
		//TODO updateGroupBuildingBatch 方法貌似有问题，当size大于1时，老是报语法错误
		//			groupBuildingService.updateGroupBuildingBatch(gbWillBeUpdatedList);

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
			groupBuilding.setSignStatus(0);
			groupBuilding.setSys0AddUser(omsUser.getId());
			gbWillBeInsertedList.add(groupBuilding);
		}
		if (!gbWillBeInsertedList.isEmpty()) {
			groupBuildingService.insertGroupBuildingBatch(gbWillBeInsertedList);
		}

		sendSMS(mobile, MessageFormat.format(auditPassSMSInfo, omsUserName, omsUserName));
		MailUtils.sendMail("解放区账号审核通过", MessageFormat.format(auditPassEmailInfo, omsUserName, omsUserName), email, "");
		request.setAttribute(JSPConstants.OprtResult, "审批结果保存成功");
		request.setAttribute(JSPConstants.ToURL, "../propertyCompany/list.html");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		return modelAndView;
	}

	/**
	 * 查找
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/search.html")
	public ModelAndView search(HttpServletRequest request) {
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String isAudited = request.getParameter("isAudited");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		if (!"-1".equals(isAudited)) {
			paramMap.put("isAudited", isAudited);
		}

		handleListOrSearch(request, paramMap);

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
			String pcImageDir = "/uploadImages/propertyCompanyImg/"; //定义物业公司图片存放路径
			String filePath = request.getSession().getServletContext().getRealPath(pcImageDir);

			//营业执照
			int indexOfDot = uploadImageFile.getOriginalFilename().indexOf(".");
			String fileNameBL = System.currentTimeMillis() + uploadImageFile.getOriginalFilename().substring(indexOfDot); //物业公司名作为前缀，防止文件重名
			File fileBL = new File(filePath + "/" + fileNameBL);
			photoBusinessLicense = pcImageDir + fileNameBL;
			uploadImageFile.transferTo(fileBL);

			//资质证明
			uploadImageFile = multipartRequest.getFile("imageFileC");
			indexOfDot = uploadImageFile.getOriginalFilename().indexOf(".");
			String fileNameC = (System.currentTimeMillis() + 1/*服务器太快了，可能1ms之内就处理完了，所以加个1*/) + uploadImageFile.getOriginalFilename().substring(indexOfDot); //物业公司名作为前缀，防止文件重名
			File fileC = new File(filePath + "/" + fileNameC);
			photoCredentials = pcImageDir + fileNameC;
			uploadImageFile.transferTo(fileC);
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
		
		List<GroupBuildingRegister> gbrs = new ArrayList<GroupBuildingRegister>();
		for (int i = 1; i < gbNames.length; i++) {
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

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/propertyCompany/register/registerInfoSaveSuccess");
		return modelAndView;
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
}