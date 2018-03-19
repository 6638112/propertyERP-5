package com.cnfantasia.server.ms.accesslink.web;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.blackList.entity.BlackList;
import com.cnfantasia.server.domainbase.blackList.service.IBlackListBaseService;
import com.cnfantasia.server.ms.accesslink.dto.BlackListDto;
import com.cnfantasia.server.ms.accesslink.dto.ParkingRecordDto;
import com.cnfantasia.server.ms.accesslink.service.IAccessLinkService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.DateUtils;

/**
 * 车禁-易豪生客户端连接管理
 * @author liyulin
 * @version 1.0 2016年6月28日 下午1:36:26
 */
@Controller
@RequestMapping("/accessLink")
public class AccessLinkController extends BaseController{

	@Resource
	private IAccessLinkService accessLinkService;
	@Resource
	private IBlackListBaseService blackListBaseService;
	@Resource
	private IUuidManager uuidManager;
	@Resource
	private IHttpClient simpleHttpClient;
	
	/**
	 * 查询所有连接
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryTcpLinks.html")
    public ModelAndView queryTcpLinks(HttpServletRequest request){
    	JsonResponse jsonResponse = simpleHttpClient.submitSimple("/access/queryTcpLinks.json", null);
    	
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.addObject("links", jsonResponse.getDataValue());
    	modelAndView.setViewName("/accessLink/linkList");
    	return modelAndView;
    }
	
    /**
     * 加入黑名单
     * 
     * @param ip
     * @return
     */
	@RequestMapping("/addBlackList.html")
	@ResponseBody
    public JsonResponse addBlackList(String ip){
		BigInteger blackListId = uuidManager.getNextUuidBigInteger(SEQConstants.t_revenue_apply);
		BlackList blackList = new BlackList();
		blackList.setId(blackListId);
		blackList.setContent(ip);
		blackList.setType(1);
		blackList.setSys0AddTime(DateUtils.getCurrentDate());
		blackList.setSys0AddUser(UserContext.getOperIdBigInteger());
		
		JsonResponse jsonResponse = new JsonResponse();
		if(blackListBaseService.insertBlackList(blackList)>0){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("remoteIp", ip);
			simpleHttpClient.submitSimple("/access/closeTcpLinkByIp.json", paramMap);
			
			jsonResponse.setMessage("操作成功！");
			jsonResponse.setStatus(CommConstants.ResponseStatus.SUCCESS);
		} else {
			jsonResponse.setMessage("操作失败！");
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
		}
    	return jsonResponse;
    }
	
	/**
     * 断开连接
     * 
     * @param ip
     * @param ioSessionId
     * @return
     */
	@RequestMapping("/closeLink.html")
	@ResponseBody
    public JsonResponse closeLink(long ioSessionId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ioSessionId", ioSessionId);
		simpleHttpClient.submitSimple("/access/closeTcpLinkByIoSessionId.json", paramMap);
		
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setMessage("操作成功！");
		jsonResponse.setStatus(CommConstants.ResponseStatus.SUCCESS);
    	return jsonResponse;
    }
    
    /**
     * 查询临停车记录
     * 
     * @param request
     * @return
     */
	@RequestMapping("/queryParkingRecords.html")
    public ModelAndView queryParkingRecords(HttpServletRequest request){
    	ModelAndView modelAndView = new ModelAndView();
    	String startTime = ParamUtils.getString(request, "startTime", null);
		String endTime = ParamUtils.getString(request, "endTime", null);
		String carNum = request.getParameter("carNum");
		if(org.apache.commons.lang.StringUtils.isNotBlank(carNum)){
			carNum = carNum.trim();
		} 
		String gbName = request.getParameter("gbName");
		if(org.apache.commons.lang.StringUtils.isNotBlank(gbName)){
			gbName = gbName.trim();
		} 
    	
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		paramMap.put("carNum", carNum);
		paramMap.put("gbName", gbName);
		
    	int total = accessLinkService.selectParkingRecordForCount(paramMap);
		// 页数的参数名 
		String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = 10;
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		// 分页信息
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		List<ParkingRecordDto> ParkingRecords = accessLinkService.selectParkingRecordForList(paramMap);
		
		modelAndView.addObject("total", total);
		modelAndView.addObject("ParkingRecords", ParkingRecords);
		modelAndView.setViewName("/accessLink/parkingRecordList");
		
    	return modelAndView;
    }
	
	/**
	 * 查询易豪生客户端黑名单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryBlackLists.html")
	public ModelAndView queryBlackLists(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		String startTime = ParamUtils.getString(request, "startTime", null);
		String endTime = ParamUtils.getString(request, "endTime", null);
    	
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		
    	int total = accessLinkService.selectBlackListForCount(paramMap);
		// 页数的参数名 
		String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = 10;
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		// 分页信息
		
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		List<BlackListDto> balckLists = accessLinkService.selectBlackListForList(paramMap);
		
		modelAndView.addObject("total", total);
		modelAndView.addObject("balckLists", balckLists);
		modelAndView.setViewName("/accessLink/blackList");
		
    	return modelAndView;
	}
	
	/**
	 * 删除易豪生客户端黑名单
	 * 
	 * @param blackListId
	 * @param remoteIp
	 * @return
	 */
	@RequestMapping("/removeBlackList.html")
	@ResponseBody
	public JsonResponse removeBlackList(BigInteger blackListId, String remoteIp){
		BlackList blackList = new BlackList();
		blackList.setId(blackListId);
		blackList.setSys0DelState(1);
		blackList.setSys0UpdTime(DateUtils.getCurrentDate());
		blackList.setSys0UpdUser(UserContext.getOperIdBigInteger());
		
		JsonResponse jsonResponse = new JsonResponse();
		if(blackListBaseService.updateBlackList(blackList)>0){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("remoteIp", remoteIp);
			simpleHttpClient.submitSimple("/access/removeBlackList.json", paramMap);
			
			jsonResponse.setMessage("操作成功！");
			jsonResponse.setStatus(CommConstants.ResponseStatus.SUCCESS);
		} else {
			jsonResponse.setMessage("操作失败！");
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
		}
		return jsonResponse;
	}
}
