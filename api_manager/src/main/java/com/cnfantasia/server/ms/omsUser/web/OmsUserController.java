package com.cnfantasia.server.ms.omsUser.web;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.sysParam.SysParamManager;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.utils.Md5Util;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domainbase.omsPermiRole.entity.OmsPermiRole;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.entity.OmsUserHasTOmsPermiRole;
import com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.service.OmsUserHasTOmsPermiRoleBaseService;
import com.cnfantasia.server.ms.omsPermiRole.service.IOmsPermiRoleService;
import com.cnfantasia.server.ms.omsUser.entity.OmsUserWithRoles;
import com.cnfantasia.server.ms.omsUser.service.IOmsUserService;
import com.cnfantasia.server.ms.permi.service.PermissionService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;
import com.cnfantasia.server.ms.pub.session.UserContext;

@Controller
@RequestMapping("/omsUser")
public class OmsUserController extends BaseController{
	private Log logger = LogFactory.getLog(getClass());
	
	private IOmsUserService omsUserService;

	public void setOmsUserService(IOmsUserService omsUserService) {
		this.omsUserService = omsUserService;
	}

	private IOmsPermiRoleService omsPermiRoleService;

	public void setOmsPermiRoleService(IOmsPermiRoleService omsPermiRoleService) {
		this.omsPermiRoleService = omsPermiRoleService;
	}

	private OmsUserHasTOmsPermiRoleBaseService omsUserHasTOmsPermiRoleBaseService;

	public void setOmsUserHasTOmsPermiRoleBaseService(OmsUserHasTOmsPermiRoleBaseService omsUserHasTOmsPermiRoleBaseService) {
		this.omsUserHasTOmsPermiRoleBaseService = omsUserHasTOmsPermiRoleBaseService;
	}
	
	@Resource
	PermissionService permissionService;
	
	@Resource
	private SysParamManager sysParamManager;

	/**
	 * 账号列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/list.html")
	public ModelAndView list(HttpServletRequest request) {
		String account = ParamUtils.getString(request, "account", null);//账号
		String realName = ParamUtils.getString(request, "realName", null);//用户名
		String userState = ParamUtils.getString(request, "userState", null);//启用状态
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userAccount", account);
		paramMap.put("realName", realName);
		paramMap.put("userState", userState);
		paramMap.put("sys0DelState", 0);
		
		int total = omsUserService.selectAccountCount(paramMap);
		
		// 页数的参数名 
		String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = 10;
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		// 分页信息
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		List<OmsUser> resList = omsUserService.selectAccountList(paramMap);
		
		ModelAndView modelAndView = new ModelAndView("/omsUser/omsUserList");
		modelAndView.addObject("resList", resList);
		modelAndView.addObject("total", total);
		return modelAndView;
	}
	
	/**
	 * 子账号管理
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/listSubUser.html")
	public ModelAndView listSubUser(HttpServletRequest request) {
		OmsUser omsUserQry = new OmsUser();
		omsUserQry.setParentUserId(UserContext.getCurrUser().getId());
		omsUserQry.setRealName(request.getParameter("userName"));
		omsUserQry.setUserAccount(request.getParameter("userAccount"));
		Map<String, Object> paramMap = MapConverter.convertBean(omsUserQry);
		paramMap.put("roleName", ParamUtils.getString(request, "roleName", null));
		List<OmsUserWithRoles> resList = omsUserService.selectSubUserList(paramMap);
		request.setAttribute("resList", resList);
		
		return new ModelAndView("/omsUser/subUserList");
	}

	/**
	 * 新增
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addNew.html")
	public ModelAndView addNew(HttpServletRequest request) {
		//取得可分配的角色列表
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", 1);// 启用
		paramMap.put("sys0DelState", 0);//未删除
		List<OmsPermiRole> omsRoles = omsPermiRoleService.getOmsPermiRoleByCondition(paramMap);

		request.setAttribute("omsRoles", omsRoles);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/omsUser/omsUserAddNew");
		return modelAndView;
	}
	
	/**
	 * 物业新增子账号
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addNewSubUser.html")
	public ModelAndView addNewSubUser(HttpServletRequest request, BigInteger subUserId) {
		//取得可分配的角色列表
		List<OmsPermiRole> omsRoles = permissionService.getOmsPermiRoleByUserId(UserContext.getCurrUser().getId());
		for (int i = omsRoles.size() - 1; i >= 0; i--) {
			OmsPermiRole omsPermiRole = omsRoles.get(i);
			if(StringUtils.isNotBlank(omsPermiRole.getCode()) && 
					sysParamManager.getSysParaValue(SysParamKey.SubUser_Exclue_Role).contains(omsPermiRole.getCode()))
				omsRoles.remove(i);
		}
		request.setAttribute("omsRoles", omsRoles);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/omsUser/subUserAddNew");
		return modelAndView;
	}
	
	/**
	 * 保存子账号
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveSubUser.html")
	public ModelAndView saveSubUser(HttpServletRequest request, BigInteger subUserId, String userAccount, String userName, String telPhone, String password) {
		int updCount = 0;
		if(subUserId == null){//新增子账号
			OmsUser parentUser = omsUserService.getOmsUserBySeqId(UserContext.getCurrUser().getId());
			OmsUser ou = new OmsUser();
			ou.setParentUserId(UserContext.getCurrUser().getId());
			ou.setIsSubUser(1);
			ou.setIsPmUser(parentUser.getIsPmUser() == null ? 0 : parentUser.getIsPmUser());//如果父是管理处账号，那么子账号也应该是
			ou.setUserAccount(userAccount);
			ou.setRealName(userName);
			ou.setTelPhone(telPhone);
			ou.setPassword(Md5Util.MD5Twice(password));
			ou.setIsadmin(0);
			ou.setUserState(0L);
			
			updCount += omsUserService.saveOmsUser(ou, request.getParameterValues("roleId"));
		}else{//编辑子账号
			OmsUser ou = new OmsUser();
			ou.setId(subUserId);
			ou.setRealName(userName);
			ou.setTelPhone(telPhone);
			updCount += omsUserService.saveOmsUser(ou, request.getParameterValues("roleId"));
		}
		
		if(updCount > 0 ){
			request.setAttribute(JSPConstants.OprtResult, "操作成功");
		} else {
			request.setAttribute(JSPConstants.OprtResult, "操作失败");
		}
		request.setAttribute(JSPConstants.ToURL, "../omsUser/listSubUser.html");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(JSPConstants.OprtSuccessPage);
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

		//取得可分配的角色列表
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", 1);// 启用
		paramMap.put("sys0DelState", 0);//未删除
		List<OmsPermiRole> omsRoles = omsPermiRoleService.getOmsPermiRoleByCondition(paramMap);

		if (StringUtils.isNotEmpty(id)) {//修改
			OmsUser omsUser = omsUserService.getOmsUserBySeqId(new BigInteger(id));
			request.setAttribute("omsUserBean", omsUser);
			
			//处理哪些是选中角色
			paramMap.clear();
			paramMap.put("tOmsUserFId", id);
			//找到已选角色
			List<OmsUserHasTOmsPermiRole> userHasRoleList = omsUserHasTOmsPermiRoleBaseService.getOmsUserHasTOmsPermiRoleByCondition(paramMap);

			//在可选角色中标识出来
			for(int i= 0; i < omsRoles.size();i++){
				for(int j = 0; j < userHasRoleList.size();j++)
					if (userHasRoleList.get(j).gettOmsPermiRoleFId().intValue()
							== omsRoles.get(i).getId().intValue())
						omsRoles.get(i).setSys0DelState(1);//借用这个字段来标识为已选
			}
		}
		request.setAttribute("omsRoles", omsRoles);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/omsUser/omsUserEdit");
		return modelAndView;
	}
	
	/**
	 * 物业编辑子账号
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/editSubUser.html")
	public ModelAndView editSubUser(HttpServletRequest request, BigInteger subUserId, BigInteger parentUserId) {

		//取得可分配的角色列表
		List<OmsPermiRole> omsRoles = permissionService.getOmsPermiRoleByUserId(parentUserId);

		String id = request.getParameter("id");
		if (StringUtils.isNotEmpty(id)) {//修改
			OmsUser omsUser = omsUserService.getOmsUserBySeqId(new BigInteger(id));
			request.setAttribute("omsUserBean", omsUser);
			
			//处理哪些是选中角色
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tOmsUserFId", id);
			//找到已选角色
			List<OmsUserHasTOmsPermiRole> userHasRoleList = omsUserHasTOmsPermiRoleBaseService.getOmsUserHasTOmsPermiRoleByCondition(paramMap);

			//在可选角色中标识出来
			for (int i = omsRoles.size() - 1; i >= 0; i--) {
				OmsPermiRole omsPermiRole = omsRoles.get(i);
				for(int j = 0; j < userHasRoleList.size();j++)
					if (userHasRoleList.get(j).gettOmsPermiRoleFId().intValue()
							== omsPermiRole.getId().intValue())
						omsPermiRole.setSys0DelState(1);//借用这个字段来标识为已选
				if(StringUtils.isNotBlank(omsPermiRole.getCode()) && 
						sysParamManager.getSysParaValue(SysParamKey.SubUser_Exclue_Role).contains(omsPermiRole.getCode()))
					omsRoles.remove(i);
			}
		}
		request.setAttribute("omsRoles", omsRoles);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/omsUser/subUserEdit");
		return modelAndView;
	}
	
	/**
	 * 物业 查看子账号
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/viewSubUser.html")
	public ModelAndView viewSubUser(HttpServletRequest request, BigInteger subUserId) {

		//取得可分配的角色列表
		List<OmsPermiRole> omsRoles = permissionService.getOmsPermiRoleByUserId(UserContext.getCurrUser().getId());

		String id = request.getParameter("id");
		if (StringUtils.isNotEmpty(id)) {//修改
			OmsUser omsUser = omsUserService.getOmsUserBySeqId(new BigInteger(id));
			request.setAttribute("omsUserBean", omsUser);
			
			//处理哪些是选中角色
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tOmsUserFId", id);
			//找到已选角色
			List<OmsUserHasTOmsPermiRole> userHasRoleList = omsUserHasTOmsPermiRoleBaseService.getOmsUserHasTOmsPermiRoleByCondition(paramMap);

			//在可选角色中标识出来
			for (int i = omsRoles.size() - 1; i >= 0; i--) {
				OmsPermiRole omsPermiRole = omsRoles.get(i);
				for(int j = 0; j < userHasRoleList.size();j++)
					if (userHasRoleList.get(j).gettOmsPermiRoleFId().intValue()
							== omsPermiRole.getId().intValue())
						omsPermiRole.setSys0DelState(1);//借用这个字段来标识为已选
				if(StringUtils.isNotBlank(omsPermiRole.getCode()) && 
						sysParamManager.getSysParaValue(SysParamKey.SubUser_Exclue_Role).contains(omsPermiRole.getCode()))
					omsRoles.remove(i);
			}
		}
		request.setAttribute("omsRoles", omsRoles);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/omsUser/subUserView");
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
		String id = request.getParameter("id");
		String userAccount = ParamUtils.getString(request, "userAccount", null);
		String realName = ParamUtils.getString(request, "realName", null);
		String password = request.getParameter("password");
		String isadmin = request.getParameter("isadmin");
		String inviteCode = ParamUtils.getString(request, "inviteCode", null);

		OmsUser omsUser = new OmsUser();
		omsUser.setUserAccount(userAccount);
		omsUser.setRealName(realName);
		omsUser.setIsadmin("on".equals(isadmin) ? 1 : 0);
		omsUser.setUserState(0L);
		omsUser.setInviteCode(inviteCode);

		if (StringUtils.isNotEmpty(id)) {//edit
			omsUser.setId(new BigInteger(id));
		} else {//addnew
			omsUser.setIsSubUser(0);
			omsUser.setIsPmUser(0);
			omsUser.setPassword(Md5Util.MD5Twice(password));
		}

		String[] roldIds = request.getParameterValues("roleId");
		omsUserService.saveOmsUser(omsUser, roldIds);

		request.setAttribute("result", "账号保存成功");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/omsUser/omsUserOprtSuccess");
		return modelAndView;
	}

	/**
	 * 修改密码
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveNewPassword.html")
	@ResponseBody
	public String saveNewPassword(HttpServletRequest request) {
		String oldPassword = request.getParameter("oldPassword");
		String password = request.getParameter("password");

		String resultInfo = "";
		if (UserContext.getCurrUser().getPassword().equals(Md5Util.MD5Twice(oldPassword))) {
			OmsUser omsUser = new OmsUser();
			omsUser.setPassword(Md5Util.MD5Twice(password));//更改为新密码
			omsUser.setId(UserContext.getUser().getSourceUserId());
			omsUserService.updateOmsUser(omsUser);
			resultInfo = "密码修改成功。";
			UserContext.getCurrUser().setPassword(Md5Util.MD5Twice(password));//不用再登录一次了
		}else{
			resultInfo = "输入的旧密码不正确，请重新输入。";
		}
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
		String account = ParamUtils.getString(request, "account", null);//账号
		String realName = ParamUtils.getString(request, "realName", null);//用户名
		String userState = ParamUtils.getString(request, "userState", null);//启用状态
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userAccount", account);
		paramMap.put("realName", realName);
		paramMap.put("userState", userState);
		paramMap.put("sys0DelState", 0);
		List<OmsUser> resList = omsUserService.getOmsUserByConditionDim(paramMap);
		request.setAttribute("resList", resList);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/omsUser/omsUserList");
		return modelAndView;
	}

	/**
	 * 删除账号，逻辑删除
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete.html")
	public ModelAndView delete(HttpServletRequest request) {
		//1.搜集参数
		String id = request.getParameter("id");

		//2.交互
		int deleteCount = omsUserService.deleteOmsUserLogic(new BigInteger(id));

		//3.结果处理
		if (deleteCount == 1) {
			request.setAttribute("result", "账号删除成功");
		} else {
			request.setAttribute("result", "账号删除失败");
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/omsUser/omsUserOprtSuccess");
		return modelAndView;
	}

	/**
	 * 账号名称校验
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/validUserName.html")
	@ResponseBody
	public String validUserName(HttpServletRequest request) {
		//1.搜集参数
		String omsUserName = request.getParameter("omsUserName");
		String id = request.getParameter("id");

		//2.交互
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userAccount", omsUserName);
		paramMap.put("sys0DelState", 0);
		List<OmsUser> omsUserList = omsUserService.getOmsUserByCondition(paramMap);
		
		//3.结果处理
		for (OmsUser omsUser : omsUserList) {
			if (!omsUser.getId().toString().equals(id)) {
				//未删除的，且不是修改自己的账号
				return "已经存在该账号，请重新输入";
			}
		}
			
		return "该账号未使用，可以使用";
	}

	/**
	 * 启用/禁用
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/changeStatus.html")
	public ModelAndView changeStatus(HttpServletRequest request) {
		//1.搜集参数
		String omsUserId = request.getParameter("id");
		Long omsUserStatus = Long.parseLong(request.getParameter("status"));

		//2.交互
		OmsUser omsUser = new OmsUser();
		omsUser.setId(new BigInteger(omsUserId));
		omsUser.setUserState(omsUserStatus == 1 ? 0L : 1L);
		int count = omsUserService.updateOmsUser(omsUser);
		
		//3.结果处理
		if (count >= 1) {
			request.setAttribute("result", omsUserStatus == 1 ? "启用成功" : "禁用成功");
		} else {
			request.setAttribute("result", "操作失败");
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/omsUser/omsUserOprtSuccess");
		return modelAndView;
	}
}
