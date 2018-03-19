package com.cnfantasia.server.ms.omsPermiRole.web;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.ms.omsPermiRole.entity.OmsPermiFunctionEntity;
import com.cnfantasia.server.ms.omsPermiRole.service.IOmsPermiRoleService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.domainbase.omsPermiRole.entity.OmsPermiRole;
import com.cnfantasia.server.domainbase.omsPermiRole.service.OmsPermiRoleBaseService;

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

	/**
	 * 角色列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/roleList.html")
	public ModelAndView list(HttpServletRequest requset) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/omsPermiRole/roleList");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<OmsPermiRole> rtList = omsPermiRoleService.getOmsPermiRoleByCondition(paramMap);
		requset.setAttribute("resList", rtList);
		return modelAndView;
	}

	/**
	 * 角色查找
	 * 
	 * @param requset
	 * @return
	 */
	@RequestMapping("/roleSearch.html")
	public ModelAndView search(HttpServletRequest requset) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/omsPermiRole/roleList");
		String roleName = requset.getParameter("roleName");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", roleName);
		List<OmsPermiRole> rtList = omsPermiRoleService.getOmsPermiRoleByConditionDim(paramMap);
		requset.setAttribute("resList", rtList);
		return modelAndView;
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
	public ModelAndView save(HttpServletRequest requset) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/omsPermiRole/roleOprtSuccess");

		String roleId = requset.getParameter("roleId");
		String roleName = requset.getParameter("roleName");
		String roleDesc = requset.getParameter("roleDesc");
		String[] opfIds = requset.getParameterValues("opfId");
		OmsPermiRole role = new OmsPermiRole();
		if (StringUtils.isNotBlank(roleId)) {
			role.setId(new BigInteger(roleId));
		}
		role.setName(roleName);
		role.setDesc(roleDesc);

		omsPermiRoleService.saveRole(role, opfIds);
		requset.setAttribute("result", "角色保存成功");
		return modelAndView;
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
			request.setAttribute("result", "角色禁用成功");
		} else {
			request.setAttribute("result", "角色启用成功");
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/omsPermiRole/roleOprtSuccess");
		return modelAndView;
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
			request.setAttribute("result", "角色删除成功");
		} else {
			request.setAttribute("result", "角色删除失败");
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/omsPermiRole/roleOprtSuccess");
		return modelAndView;
	}
}
