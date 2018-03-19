package com.cnfantasia.server.ms.roomValidate.web;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.api.room.constant.RoomDict;
import com.cnfantasia.server.commbusi.microblogQueue.util.DefaultGbUtil;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.ms.inviteReward.service.IInviteRewardService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.propertyProprietor.entity.PropertyProprietorEntity;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.roomValidate.service.IRoomValidateService;

@Controller
@RequestMapping("/roomValidate")
public class RoomValidateController extends BaseController {
//	private Log logger = LogFactory.getLog(getClass());
	
	private IRoomValidateService roomValidateService;

	public void setRoomValidateService(IRoomValidateService roomValidateService) {
		this.roomValidateService = roomValidateService;
	}
	@Resource
	IInviteRewardService inviteRewardService; 
	/**
	 * 列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/list.html")
	public ModelAndView list(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();

		//默认显示待审核的
		paramMap.put("rvStatus", RoomDict.RoomValidte_VerifyStatus.DOING); 
		
		handleListOrSearch(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/roomValidate/roomValidateList");
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

		int resultSize = roomValidateService.getRVList_byUserId_forCount(paramMap);
		request.setAttribute("resultSize", resultSize);

		// 页数的参数名 
		String pageIndexName = new org.displaytag.util.ParamEncoder("row").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);

		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		List<PropertyProprietorEntity> searchRestList = roomValidateService.getRVList_byUserId_forPage(paramMap);

		request.setAttribute("resList", searchRestList);
	}

	/**
	 * 查找
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/search.html")
	public ModelAndView search(HttpServletRequest request) {
		String rvStatus = request.getParameter("rvStatus");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String uId = request.getParameter("uId");
		String groupBuildingName = request.getParameter("groupBuildingName");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("omsUserId", UserContext.getCurrUser().getId());
		if (!rvStatus.equals("0")) {
			paramMap.put("rvStatus", rvStatus);
		}
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		paramMap.put("uId", uId);
		paramMap.put("groupBuildingName", groupBuildingName);

		handleListOrSearch(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/roomValidate/roomValidateList");
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
		String rvId = request.getParameter("id");

		request.setAttribute("rvInfo", roomValidateService.select_rm_forAudit(rvId));

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/roomValidate/roomValidateEdit");
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
		String rvId = request.getParameter("id");

		request.setAttribute("rvInfo", roomValidateService.select_rm_forAudit(rvId));

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/roomValidate/roomValidateView");
		return modelAndView;
	}
	/**
	 * 保存审核结果
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveAuditResult.html")
	@Transactional
	public ModelAndView saveAuditResult(HttpServletRequest request) {
		String rvId = request.getParameter("rvId");
		String rrId = request.getParameter("rrId");
		String auditResult = request.getParameter("auditResult");//审核结果：pass notPass
		String auditNotPassResultDesc = request.getParameter("auditNotPassResultDesc"); //不通过原因说明
		String roomId = request.getParameter("rId");
		String userId = request.getParameter("userId");
		
		String planPropertyAmountStr = request.getParameter("planPropertyAmount");
		String planSwitchStatusStr = request.getParameter("planSwitchStatus");
		
		Double planPropertyAmount = StringUtils.isEmpty(planPropertyAmountStr)?null:Double.valueOf(planPropertyAmountStr);
		Integer planSwitchStatus = StringUtils.isEmpty(planSwitchStatusStr)?null:Integer.valueOf(planSwitchStatusStr);
		
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("omsUserId", UserContext.getCurrUser().getId());

		paramMap.put("rvId", rvId);
		paramMap.put("rrId", rrId);
		paramMap.put("auditNotPassResultDesc", auditNotPassResultDesc);
		paramMap.put("roomId", roomId);
		paramMap.put("userId", userId);
		
		if(planPropertyAmount!=null){paramMap.put("planPropertyAmount", PriceUtil.multiply100(planPropertyAmount));}
		if(planSwitchStatus!=null){paramMap.put("planSwitchStatus", planSwitchStatus);}
		
		int updateCount = 0;
		if ("pass".equals(auditResult) || auditResult==null) {
			updateCount = roomValidateService.auditPassUpdate(paramMap);
			if(updateCount>0){
				//神码行动新增邀请人和注册新人的验证门牌粮票奖励 huangzj 2015-05-11
				if(StringUtils.isNotEmpty(userId)&&StringUtils.isNotEmpty(roomId)){
					inviteRewardService.excuteInviteRewardForVerifyRoom(new BigInteger(userId), new BigInteger(roomId));
				}
				//查询小区的签约状态
				BigInteger groupBuildingId = new BigInteger(request.getParameter("gbId"));
				if(!DefaultGbUtil.checkIsDefaultGb(groupBuildingId)){
					boolean signStatus = roomValidateService.qryGbSignStatus(groupBuildingId);
					if(!signStatus){//非签约的
						ApplicationContextBothUtil.getMicroblogQueueService().userRoomValidate(userId, groupBuildingId,new BigInteger(roomId));
					}
				}
			}
		} else {
			updateCount = roomValidateService.auditNotPassUpdate(paramMap);
		}

		if(planSwitchStatus!=null){
			roomValidateService.changeUserHasTRoomWhenAudit(paramMap);//更新金额信息
		}
		
		if (updateCount > 0) {
			request.setAttribute(JSPConstants.OprtResult, "审批结果保存成功");
		} else {
			request.setAttribute(JSPConstants.OprtResult, "审批结果保存失败");
		}

		request.setAttribute(JSPConstants.ToURL, "../roomValidate/list.html");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		return modelAndView;
	}

	/**
	 * 根据门牌验证的ID，修改小区名称
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateGroupBuildingName.html")
	@ResponseBody
	public String updateGroupBuildingName(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rvId", request.getParameter("rvId"));
		paramMap.put("gbName", request.getParameter("gbName"));
		paramMap.put("omsUserId", UserContext.getCurrUser().getId());
		int updateCount = roomValidateService.updateGroupBuildingName(paramMap);
		if (updateCount > 0)
			return "更新小区名称成功";
		else
			return "更新小区名称失败";
	}
}
