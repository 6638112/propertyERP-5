package com.cnfantasia.server.ms.inviteReward.web;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.ms.inviteReward.entity.InviteRewardConfigEntity;
import com.cnfantasia.server.ms.inviteReward.entity.InviteUserEntity;
import com.cnfantasia.server.ms.inviteReward.service.IInviteRewardService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;

@Controller
@RequestMapping("/inviteReward")
public class InviteRewardController extends BaseController {
	//private Log logger = LogFactory.getLog(getClass());
	
	private IInviteRewardService inviteRewardService;
	
	public IInviteRewardService getInviteRewardService() {
		return inviteRewardService;
	}

	public void setInviteRewardService(IInviteRewardService inviteRewardService) {
		this.inviteRewardService = inviteRewardService;
	}

	/**
	 * 列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/list.html")
	public ModelAndView list(HttpServletRequest request) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		handleListOrSearch(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/inviteReward/inviteRewardConfigList");
		return modelAndView;
	}
	
	/**
	 * 查找
	 * @param request
	 * @return
	 */
	@RequestMapping("/search.html")
	public ModelAndView search(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("inviteUserId", request.getParameter("inviteUserId"));//邀请人解放号
		paramMap.put("inviteUserName", request.getParameter("inviteUserName"));//邀请人解放号
		paramMap.put("inviteCode", request.getParameter("inviteCode"));//邀请码
		paramMap.put("inviteType", request.getParameter("inviteType"));//邀请人类型
		handleListOrSearch(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/inviteReward/inviteRewardConfigList");
		return modelAndView;
	}
	
	/**
	 * 统一处理List和Search请求
	 * @param request
	 * @param paramMap
	 */
	private void handleListOrSearch(HttpServletRequest request, Map<String, Object> paramMap) {
		//总数量
		int resultSize = this.inviteRewardService.query_InviteRewardConfig_forCount(paramMap);
		request.setAttribute("resultSize", resultSize);
		// 页数的参数名 
		String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		//分页信息
		List<InviteRewardConfigEntity> searchRestList = this.inviteRewardService.query_InviteRewardConfig_forPage(curPageIndex, pageSize, paramMap);
		request.setAttribute("resList", searchRestList);
	}
	/**
	 * 邀请奖励-初始化编辑或添加配置信息
	 * @author huangzj 2015-05-12
	 * @param request
	 * @return
	 */
	@RequestMapping("/initEdit.html")
	public ModelAndView initEdit(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){
			request.setAttribute("entity", this.inviteRewardService.query_InviteRewardConfig_byId(new BigInteger(id)));
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/inviteReward/inviteRewardConfig");
		return modelAndView;
	}
	/**
	 * 物业管理 新增商家
	 * @author huangzj 2015-04-23
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveEdit.html")
	public ModelAndView saveEdit(HttpServletRequest request) throws IOException{
		String id = request.getParameter("configId");//配置ID
		String inviteUserId = request.getParameter("inviteUserId");//邀请人ID
		//String inviteUserName = request.getParameter("inviteUserName");//邀请人Name
		String inviteCode = request.getParameter("inviteCode");//邀请码
		String inviteType = request.getParameter("inviteType");//邀请人类型
		
		if(this.inviteRewardService.saveOrUpdate_InviteRewardConfig(id, inviteUserId, inviteCode, inviteType)>0){
			request.setAttribute(JSPConstants.OprtResult, "保存成功");
			request.setAttribute(JSPConstants.ToURL, "../inviteReward/list.html");
		}else{
			request.setAttribute(JSPConstants.OprtResult, "保存失败");
			request.setAttribute(JSPConstants.ToURL, "../inviteReward/initEdit.html");
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		return modelAndView;
	}
	
	/**
	 * 查看明细
	 * @param request
	 * @return
	 */
	@RequestMapping("/view.html")
	public ModelAndView view(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){
			InviteRewardConfigEntity entity = this.inviteRewardService.query_InviteRewardConfig_byId(new BigInteger(id));
			request.setAttribute("entity", entity);
			request.setAttribute("registerList", entity.getRelationList());
			request.setAttribute("registerSize", entity.getRelationList().size());
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/inviteReward/inviteRewardConfigView");
		return modelAndView;
	}
	
	
	/**
	 * 查询邀请用户
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryInviteUser.html")
	@ResponseBody
	public String queryInviteUser(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", request.getParameter("id"));
		paramMap.put("name", request.getParameter("name"));
		List<InviteUserEntity> userList = this.inviteRewardService.query_InviteUserList(paramMap);
		return JSON.toJSONString(userList);
	}
	
	/**
	 * 启用
	 * @param request
	 * @return
	 */
	@RequestMapping("/enableConfig.html")
	@ResponseBody
	public String enableConfig(String id) {
		if(inviteRewardService.enableOrDisableConfig(id, true)>0){
			return "启用成功";
		}else{
			return "启用失败";
		}
	}
	
	/**
	 * 禁用
	 * @param request
	 * @return
	 */
	@RequestMapping("/disableConfig.html")
	@ResponseBody
	public String disableConfig(String id) {
		if(inviteRewardService.enableOrDisableConfig(id, false)>0){
			return "禁用成功";
		}else{
			return "禁用失败";
		}
	}
	
	/**
	 * 验证邀请手机号是否重复
	 * @param request
	 * @return
	 */
	@RequestMapping("/checkMobileRepeat.html")
	@ResponseBody
	public String checkMobileRepeat(String id,String mobile) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("inviteId", id);//编辑的配置ID
		paramMap.put("inviteCode", mobile);//邀请手机号
		int count = this.inviteRewardService.query_InviteRewardConfig_isRepeat(paramMap);
		if(count>0){
			return "0";
		}else{
			return "1";
		}
	}
}
