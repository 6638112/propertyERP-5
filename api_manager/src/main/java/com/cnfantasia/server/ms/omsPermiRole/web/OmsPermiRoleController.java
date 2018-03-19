package com.cnfantasia.server.ms.omsPermiRole.web;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domainbase.omsPermiFunction.entity.OmsPermiFunction;
import com.cnfantasia.server.domainbase.omsPermiFunction.service.IOmsPermiFunctionBaseService;
import com.cnfantasia.server.domainbase.omsPermiRole.entity.OmsPermiRole;
import com.cnfantasia.server.domainbase.omsPermiRole.service.OmsPermiRoleBaseService;
import com.cnfantasia.server.ms.omsPermiRole.entity.OmsPermiFunctionEntity;
import com.cnfantasia.server.ms.omsPermiRole.service.IOmsPermiRoleService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;

/**
 * 
 * 类说明：权限角色
 * 
 * @author wen_fuqiang
 */
@Controller
@RequestMapping("/omsPermiRole")
public class OmsPermiRoleController extends BaseController {
	private OmsPermiRoleBaseService omsPermiRoleBaseService;

	public OmsPermiRoleBaseService getOmsPermiRoleBaseService() {
		return omsPermiRoleBaseService;
	}

	public void setOmsPermiRoleBaseService(OmsPermiRoleBaseService omsPermiRoleBaseService) {
		this.omsPermiRoleBaseService = omsPermiRoleBaseService;
	}

	private IOmsPermiRoleService omsPermiRoleService;

	public IOmsPermiRoleService getOmsPermiRoleService() {
		return omsPermiRoleService;
	}

	public void setOmsPermiRoleService(IOmsPermiRoleService omsPermiRoleService) {
		this.omsPermiRoleService = omsPermiRoleService;
	}
	
	@Resource
	private IOmsPermiFunctionBaseService omsPermiFunctionBaseService;

	/**
	 * 角色列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/roleList.html")
	public ModelAndView list(HttpServletRequest requset) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<OmsPermiRole> rtList = omsPermiRoleService.getOmsPermiRoleByCondition(paramMap);
		requset.setAttribute("resList", rtList);
		return new ModelAndView("/omsPermiRole/roleList");
	}

	/**
	 * 角色查找
	 * 
	 * @param requset
	 * @return
	 */
	@RequestMapping("/roleSearch.html")
	public ModelAndView search(HttpServletRequest requset) {
		String roleName = requset.getParameter("roleName");
		String roleCode = requset.getParameter("roleCode");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", roleName);
		paramMap.put("code", roleCode);
		List<OmsPermiRole> rtList = omsPermiRoleService.getOmsPermiRoleByConditionDim(paramMap);
		requset.setAttribute("resList", rtList);
		return new ModelAndView("/omsPermiRole/roleList");
	}

	/**
	 * 角色编辑
	 * 
	 * @param requset
	 * @return
	 */
	@RequestMapping("/roleEdit.html")
	public ModelAndView edit(HttpServletRequest requset) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/omsPermiRole/roleEdit");
		String roleId = requset.getParameter("id");

		List<OmsPermiFunctionEntity> omsPermiFunctionList = omsPermiRoleService.selectAllOmsPermiFunction();
		//判断omsPermiFunctionList中哪些已经分配给了角色roleId
		if (StringUtils.isNotEmpty(roleId)) {//修改角色，新增角色不用判断
			OmsPermiRole role = omsPermiRoleBaseService.getOmsPermiRoleBySeqId(new BigInteger(roleId));
			requset.setAttribute("role", role);

			List<BigInteger> functionIdList = omsPermiRoleService.selectOmsPermiFunctionByRoleId(new BigInteger(roleId));
			for (int i = 0; i < omsPermiFunctionList.size(); i++)
				for (int j = 0; j < functionIdList.size(); j++) {
					if (omsPermiFunctionList.get(i).getId().intValue() == functionIdList.get(j).intValue()) {
						omsPermiFunctionList.get(i).setIsAssignedToRole(1);//已分配
					}
				}
		}

		requset.setAttribute("opfList", omsPermiFunctionList);
		return modelAndView;
	}

	/**
	 * 角色保存
	 * 
	 * @param requset
	 * @return
	 */
	@RequestMapping("/roleSave.html")
	public ModelAndView save(HttpServletRequest request) {
		String roleId = request.getParameter("roleId");
		String roleCode = request.getParameter("roleCode");
		String roleName = request.getParameter("roleName");
		String roleDesc = request.getParameter("roleDesc");
		String[] opfIds = request.getParameterValues("opfId");
		OmsPermiRole role = new OmsPermiRole();
		if (StringUtils.isNotBlank(roleId)) {
			role.setId(new BigInteger(roleId));
		}
		role.setCode(roleCode);
		role.setName(roleName);
		role.setDesc(roleDesc);

		boolean duplicateCode = false;
		if (!StringUtils.isEmpty(roleCode)) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("code", roleCode);
			paramMap.put("sys0DelState", 0);
			List<OmsPermiRole> roleList = omsPermiRoleService.getOmsPermiRoleByCondition(paramMap);
			for (OmsPermiRole existedRole : roleList) {
				if (!existedRole.getId().equals(role.getId()) && role.getCode().equals(existedRole.getCode())) {
					duplicateCode = true;
					break;
				}
			}
		}

		if (duplicateCode) {
			request.setAttribute(JSPConstants.OprtResult, "角色编码重复，保存失败");
		} else {
			omsPermiRoleService.saveRole(role, opfIds);
			request.setAttribute(JSPConstants.OprtResult, "角色保存成功");
		}

		request.setAttribute(JSPConstants.ToURL, "../omsPermiRole/roleList.html");

		return new ModelAndView(JSPConstants.OprtSuccessPage);
	}

	/**
	 * 启用/禁用
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/roleStatusChange.html")
	public ModelAndView statusChange(HttpServletRequest request) {

		Integer oldStatus = Integer.parseInt(request.getParameter("status").toString());

		OmsPermiRole omsPermiRole = new OmsPermiRole();
		omsPermiRole.setId(new BigInteger(request.getParameter("id").toString()));
		int newStatus = oldStatus ^ 1; //禁用要更新为启用，启用要更新为禁用
		omsPermiRole.setStatus(new Integer(newStatus));
		omsPermiRoleBaseService.updateOmsPermiRole(omsPermiRole);
		if (oldStatus == 1) {
			request.setAttribute(JSPConstants.OprtResult, "角色禁用成功");
		} else {
			request.setAttribute(JSPConstants.OprtResult, "角色启用成功");
		}

		request.setAttribute(JSPConstants.ToURL, "../omsPermiRole/roleList.html");

		return new ModelAndView(JSPConstants.OprtSuccessPage);
	}
	
	/**
	 * 删除
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/roleDelete.html")
	public ModelAndView delete(HttpServletRequest request) {
		int updateCount = omsPermiRoleBaseService.deleteOmsPermiRoleLogic((new BigInteger(request.getParameter("id").toString())));
		if (updateCount == 1) {
			request.setAttribute(JSPConstants.OprtResult, "角色删除成功");
		} else {
			request.setAttribute(JSPConstants.OprtResult, "角色删除失败");
		}
		request.setAttribute(JSPConstants.ToURL, "../omsPermiRole/roleList.html");
		
		return new ModelAndView(JSPConstants.OprtSuccessPage);
	}
	
	
	/**
	 * 菜单编辑
	 * 
	 * @param requset
	 * @return
	 */
	@RequestMapping("/functionEdit.html")
	public ModelAndView functionEdit(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/omsPermiRole/functionEdit");
		BigInteger functionId = ParamUtils.getBigInteger(request, "functionId", null);
		OmsPermiFunction function = omsPermiFunctionBaseService.getOmsPermiFunctionBySeqId(functionId);
		request.setAttribute("f", function);

		List<OmsPermiFunctionEntity> omsPermiFunctionList = omsPermiRoleService.selectAllOmsPermiFunction();
		request.setAttribute("opfList", omsPermiFunctionList);
		
		
		return modelAndView;
	}
}
