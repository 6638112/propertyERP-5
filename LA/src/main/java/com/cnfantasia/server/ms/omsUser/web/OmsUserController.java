package com.cnfantasia.server.ms.omsUser.web;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.ms.omsPermiRole.service.IOmsPermiRoleService;
import com.cnfantasia.server.ms.omsUser.service.IOmsUserService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.domainbase.omsPermiRole.entity.OmsPermiRole;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.entity.OmsUserHasTOmsPermiRole;
import com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.service.OmsUserHasTOmsPermiRoleBaseService;

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

	/**
	 * 账号列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/list.html")
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/omsUser/omsUserList");

		Map paramMap = new HashMap();
		List<OmsUser> resList = omsUserService.getOmsUserByCondition(paramMap);
		request.setAttribute("resList", resList);
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
	 * 保存
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/save.html")
	public ModelAndView save(HttpServletRequest request) {
		String id = request.getParameter("id");
		String userAccount = request.getParameter("userAccount");
		String realName = request.getParameter("realName");
		String password = request.getParameter("password");

		OmsUser omsUser = new OmsUser();
		omsUser.setUserAccount(userAccount);
		omsUser.setRealName(realName);
		omsUser.setPassword(password);
		if (StringUtils.isNotEmpty(id)) {
			omsUser.setId(new BigInteger(id));
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
		if (UserContext.getCurrUser().getPassword().equals(oldPassword)) {
			OmsUser omsUser = new OmsUser();
			omsUser.setPassword(password);//更改为新密码
			omsUser.setId(UserContext.getCurrUser().getId());
			omsUserService.updateOmsUser(omsUser);
			resultInfo = "密码修改成功。";
			UserContext.getCurrUser().setPassword(password);//不用再登录一次了
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
		String account = request.getParameter("account");//账号
		String realName = request.getParameter("realName");//用户名
		String userState = request.getParameter("userState");//启用状态
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

		//2.交互
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userAccount", omsUserName);
		int count = omsUserService.getOmsUserCount(paramMap);

		//3.结果处理
		if (count >= 1) {
			return "已经存在该账号，请重新输入";
		} else {
			return "该账号未使用，可以使用";
		}
	}
}
