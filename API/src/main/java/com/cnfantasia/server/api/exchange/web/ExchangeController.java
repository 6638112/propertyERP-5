/**   
* Filename:    ExchangeController.java   
* @version:    1.0  
* Create at:   2014年10月15日 上午8:49:29   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年10月15日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.exchange.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.exchange.entity.ExchangeBaseEntity;
import com.cnfantasia.server.api.exchange.entity.ExchangeEntity;
import com.cnfantasia.server.api.exchange.entity.ExchangeSuccEntity;
import com.cnfantasia.server.api.exchange.service.IExchangeService;
import com.cnfantasia.server.api.pinyipin.entity.PinyipinContentEntity;
import com.cnfantasia.server.api.pinyipin.service.IPinyipinService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.utils.CommRequestFileParser;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename:    ExchangeController.java
 * @version:    1.0.0
 * Create at:   2014年10月15日 上午8:49:29
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年10月15日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/exchange")
public class ExchangeController extends BaseController{
	
	private IExchangeService exchangeService;
	public void setExchangeService(IExchangeService exchangeService) {
		this.exchangeService = exchangeService;
	}
	
	private IPinyipinService pinyipinService;
	public void setPinyipinService(IPinyipinService pinyipinService) {
		this.pinyipinService = pinyipinService;
	}
	
	
	/**
	 * 查询最热的一个换物
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryMostHotContent.json")
	@ResponseBody
	public JsonResponse qryMostHotContent(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdBigInteger();
		//2.交互
		ExchangeEntity exchangeEntity = exchangeService.getMostHotContent(userId);
		//3.结果处理
//		Map<String,Object> resMap = exchangeService.exhcangeContent2Map(userId,exchangeEntity.getOwner(),exchangeEntity,exchangeEntity.getExchangePicList(),exchangeEntity.getExchangeRelationGoalEntityList());
		Map<String,Object> resMap = exchangeService.exhcangeContent2Map(null,userId, exchangeEntity);
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
	/**
	 * 查询最热的一个拼单和换物
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryMostHotContentBoth.json")
	@ResponseBody
	public JsonResponse qryMostHotContentBoth(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdBigInteger();
		//2.交互
		PinyipinContentEntity pinyipinContentEntity = pinyipinService.getMostHotContent(userId);
		ExchangeEntity exchangeEntity = exchangeService.getMostHotContent(userId);
		//3.结果处理
		Map<String,Object> resMap = new HashMap<String, Object>();
		int totalCount = 0;
		Map<String,Object> pinyipinMap = new HashMap<String, Object>();
		Map<String,Object> exchangeMap = new HashMap<String, Object>();
		if(pinyipinContentEntity!=null){
			totalCount++;
			pinyipinMap = pinyipinService.pinyipinContent2Map(null,userId, pinyipinContentEntity);
		}
		if(exchangeEntity!=null){
			totalCount++;
//			exchangeMap = exchangeService.exhcangeContent2Map(userId,exchangeEntity.getOwner(), exchangeEntity, exchangeEntity.getExchangePicList(), exchangeEntity.getExchangeRelationGoalEntityList());
			exchangeMap = exchangeService.exhcangeContent2Map(null,userId, exchangeEntity);
		}
		resMap.put("pinyipin", pinyipinMap);
		resMap.put("exchange", exchangeMap);
		resMap.put("totalCount", totalCount);
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
	/**
	 * 查询热门换物列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryHotContentList.json")
	@ResponseBody
	public JsonResponse qryHotContentList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		PageModel pageModel = ControllerUtils.getPageModel(request);
		BigInteger userId = UserContext.getOperIdBigInteger();
		//2.交互
//		String nowUpdTime = exchangeService.fetchAllHotContentListLastUpdTime(userId);
//		if(!ControllerUtils.checkHasNewData(request, jsonResponse, nowUpdTime)){
//			return jsonResponse;
//		}
		List<ExchangeBaseEntity> exchangeEntityList = exchangeService.getHotContentList(userId, pageModel);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		resList = exchangeService.exhcangeContent2MapList(userId, exchangeEntityList);
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}
	
	/**
	 * 发起换物
	 * @param request
	 * @return
	 */
	@RequestMapping("/confirmLaunchContent.json")
	@ResponseBody
	public JsonResponse confirmLaunchContent(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String title = request.getParameter("title");
		String desc = request.getParameter("desc");
		//图片信息
//		List<String> picInfoParams = null;
//		{
//			if (request instanceof MultipartHttpServletRequest) {// 获取图片
//				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//				Iterator<String> paramNames=multipartRequest.getFileNames();
//				while(paramNames.hasNext()){
//					String tmpName=paramNames.next();
//					if(tmpName.startsWith("picInfo")){
//						if(picInfoParams==null){
//							picInfoParams=new ArrayList<String>();
//						}
//						picInfoParams.add(tmpName);
//					}
//				}
//			}
//		}
//		List<RequestFileEntity> picList = CommRequestFileParser.parseRequsetFileInfo(request, picInfoParams);
		List<RequestFileEntity> picList = CommRequestFileParser.parseRequsetFileInfoDimLike(request, "picInfo");
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		exchangeService.confirmLaunchContent(userId, title, desc, picList);
		//3.结果处理
		return jsonResponse;
	}
	
	/**
	 * 更新换物
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateLaunchContent.json")
	@ResponseBody
	public JsonResponse updateLaunchContent(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger contentId = new BigInteger("contentId");//拼单Id
		String delPicIdsStr = request.getParameter("delPicIds");
		List<BigInteger> delPicIds = null;
		if(!StringUtils.isEmpty(delPicIdsStr)){
			delPicIds = JSON.parseArray(delPicIdsStr, BigInteger.class);
		}
		//
		String title = request.getParameter("title");
		String desc = request.getParameter("desc");
		//图片信息
//		List<String> picInfoParams = null;
//		{
//			if (request instanceof MultipartHttpServletRequest) {// 获取图片
//				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//				Iterator<String> paramNames=multipartRequest.getFileNames();
//				while(paramNames.hasNext()){
//					String tmpName=paramNames.next();
//					if(tmpName.startsWith("picInfo")){
//						if(picInfoParams==null){
//							picInfoParams=new ArrayList<String>();
//						}
//						picInfoParams.add(tmpName);
//					}
//				}
//			}
//		}
//		List<RequestFileEntity> picList = CommRequestFileParser.parseRequsetFileInfo(request, picInfoParams);
		List<RequestFileEntity> picList = CommRequestFileParser.parseRequsetFileInfoDimLike(request, "picInfo");
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		exchangeService.updateLaunchContent(userId, contentId, title, desc, picList,delPicIds);
		//3.结果处理
		return jsonResponse;
	}
	
	/**
	 * 删除换物
	 * @param request
	 * @return
	 */
	@RequestMapping("/delLaunchContent.json")
	@ResponseBody
	public JsonResponse delLaunchContent(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String contentIdStr = request.getParameter("contentId");
		BigInteger contentId = new BigInteger(contentIdStr);
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		exchangeService.delLaunchContent(userId, contentId);
		//3.结果处理
		return jsonResponse;
	}
	
	/**
	 * 查询我发起的换物列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryLaunchContentList.json")
	@ResponseBody
	public JsonResponse qryLaunchContentList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		PageModel pageModel = ControllerUtils.getPageModel(request);
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		List<ExchangeBaseEntity> exchangeEntityList = exchangeService.getLaunchContentList(userId, pageModel);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		resList = exchangeService.exhcangeContent2MapList(userId, exchangeEntityList);
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}
	
	/**
	 * 参与换物
	 * @param request
	 * @return
	 */
	@RequestMapping("/confirmJoinContent.json")
	@ResponseBody
	public JsonResponse confirmJoinContent(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger goalContentId = new BigInteger(request.getParameter("goalContentId"));
		//
		String title = request.getParameter("title");
		String desc = request.getParameter("desc");
		//图片信息
//		List<String> picInfoParams = null;
//		{
//			if (request instanceof MultipartHttpServletRequest) {// 获取图片
//				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//				Iterator<String> paramNames=multipartRequest.getFileNames();
//				while(paramNames.hasNext()){
//					String tmpName=paramNames.next();
//					if(tmpName.startsWith("picInfo")){
//						if(picInfoParams==null){
//							picInfoParams=new ArrayList<String>();
//						}
//						picInfoParams.add(tmpName);
//					}
//				}
//			}
//		}
//		List<RequestFileEntity> picList = CommRequestFileParser.parseRequsetFileInfo(request, picInfoParams);
		List<RequestFileEntity> picList = CommRequestFileParser.parseRequsetFileInfoDimLike(request, "picInfo");
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		exchangeService.confirmJoinContent(userId, goalContentId, title, desc, picList);
		//3.结果处理
		return jsonResponse;
	}
	
	/**
	 * 查询我参与的换物列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryJoinContentList.json")
	@ResponseBody
	public JsonResponse qryJoinContentList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		PageModel pageModel = ControllerUtils.getPageModel(request);
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		List<ExchangeBaseEntity> exchangeEntityList = exchangeService.getJoinContentList(userId, pageModel);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		resList = exchangeService.exhcangeContent2MapList(userId, exchangeEntityList);
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}
	
	/**
	 * 查询我发起的和我参与的换物列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryLaunchAndJoinContentList.json")
	@ResponseBody
	public JsonResponse qryLaunchAndJoinContentList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		PageModel pageModel = ControllerUtils.getPageModel(request);
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
//		String nowUpdTime = exchangeService.fetchAllLaunchAndJoinContentListLastUpdTime(userId);
//		if(!ControllerUtils.checkHasNewData(request, jsonResponse, nowUpdTime)){
//			return jsonResponse;
//		}
		List<ExchangeBaseEntity> exchangeEntityList = exchangeService.getLaunchAndJoinContentList(userId, pageModel);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		resList = exchangeService.exhcangeContent2MapList(userId, exchangeEntityList);
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}
	
	/**
	 * 查询换物详情
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryExchangeContentDetail.json")
	@ResponseBody
	public JsonResponse qryExchangeContentDetail(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String contentIdStr = request.getParameter("contentId");
		BigInteger contentId = new BigInteger(contentIdStr);
		BigInteger userId = UserContext.getOperIdBigInteger();
		//2.交互
		ExchangeEntity exchangeEntity = exchangeService.getExchangeContentDetail(userId, contentId);
		//3.结果处理
//		Map<String,Object> resMap = exchangeService.exhcangeContent2Map(userId,exchangeEntity.getOwner(), exchangeEntity, exchangeEntity.getExchangePicList(), exchangeEntity.getExchangeRelationGoalEntityList());
		Map<String,Object> resMap = exchangeService.exhcangeContent2Map(null,userId, exchangeEntity);
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
	/**
	 * 查询参与换物的用户物品列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/qrySubJoinContentList.json")
	@ResponseBody
	public JsonResponse qrySubJoinContentList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String contentIdStr = request.getParameter("contentId");
		BigInteger contentId = new BigInteger(contentIdStr);
		BigInteger userId = UserContext.getOperIdBigInteger();
		//2.交互
		List<ExchangeBaseEntity> exchangeBaseEntityList= exchangeService.getSubJoinContentList(userId, contentId);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		resList = exchangeService.exhcangeContent2MapList(userId, exchangeBaseEntityList);
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}
	
	/**
	 * 发起者确认交换换物
	 * @param request
	 * @return
	 */
	@RequestMapping("/confirmExchangeRelation.json")
	@ResponseBody
	public JsonResponse confirmExchangeRelation(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数 
		BigInteger exchangeRelationId = new BigInteger(request.getParameter("exchangeRelationId"));
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		ExchangeSuccEntity exchangeSuccEntity = exchangeService.confirmExchangeRelation(userId, exchangeRelationId);
		//3.结果处理
		Map<String,Object> launchContentMap = exchangeService.exhcangeContent2Map(null,userId, exchangeSuccEntity.getLaunchExchgContent());
		Map<String,Object> joinContent = exchangeService.exhcangeContent2Map(null,userId, exchangeSuccEntity.getJoinExchgContent());
		jsonResponse.putData("launchContent", launchContentMap);
		jsonResponse.putData("joinContent", joinContent);
		return jsonResponse;
	}
	
	/**
	 * 跪求换
	 * @param request
	 * @return
	 */
	@RequestMapping("/doRelationWanted.json")
	@ResponseBody
	public JsonResponse doRelationWanted(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数 
		BigInteger exchangeRelationId = new BigInteger(request.getParameter("exchangeRelationId"));
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		Integer currCount = exchangeService.doRelationWanted(userId, exchangeRelationId);
		//3.结果处理
		jsonResponse.putData("currCount", currCount);
		return jsonResponse;
	}
}
