/**   
* Filename:    PinyipinController.java   
* @version:    1.0  
* Create at:   2014年10月15日 上午8:05:11   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年10月15日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.pinyipin.web;

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
import com.cnfantasia.server.api.pinyipin.constant.PinyipinDict;
import com.cnfantasia.server.api.pinyipin.entity.PinyipinContentEntity;
import com.cnfantasia.server.api.pinyipin.entity.PinyipinReserveEntity;
import com.cnfantasia.server.api.pinyipin.service.IPinyipinService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.utils.CommRequestFileParser;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.StringUtils;


/**
 * Filename:    PinyipinController.java
 * @version:    1.0.0
 * Create at:   2014年10月15日 上午8:05:11
 * Description:拼一拼
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年10月15日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/pinyipin")
public class PinyipinController extends BaseController{
	private IPinyipinService pinyipinService;
	public void setPinyipinService(IPinyipinService pinyipinService) {
		this.pinyipinService = pinyipinService;
	}
	
//	private IFileServerService fileServerService;
//	public void setFileServerService(IFileServerService fileServerService) {
//		this.fileServerService = fileServerService;
//	}
//	private ISysParamManager sysParamManager;
//	public void setSysParamManager(ISysParamManager sysParamManager) {
//		this.sysParamManager = sysParamManager;
//	}

	/**
	 * 查询最热的一个拼单
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
		PinyipinContentEntity pinyipinContentEntity = pinyipinService.getMostHotContent(userId);
		//3.结果处理
//		Map<String,Object> resMap = pinyipinService.pinyipinContent2Map(userId,pinyipinContentEntity,pinyipinContentEntity.getCreateUser(),pinyipinContentEntity.getPinyipinPicList(),pinyipinContentEntity.getPinyipinReserveList());
		Map<String,Object> resMap = pinyipinService.pinyipinContent2Map(null,userId, pinyipinContentEntity);
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
	/**
	 * 查询热门拼单列表
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
//		String nowUpdTime = pinyipinService.fetchAllHotContentListLastUpdTime(userId);
//		if(!ControllerUtils.checkHasNewData(request, jsonResponse, nowUpdTime)){
//			return jsonResponse;
//		}
		List<PinyipinContentEntity> pinyipinContentEntityList = pinyipinService.getHotContentList(userId,pageModel);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		resList = pinyipinService.pinyipinContent2MapList(userId, pinyipinContentEntityList);
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}
	
	/**
	 * 查询我发起的拼单列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryLaunchContentList.json")
	@ResponseBody
	public JsonResponse qryLaunchContentList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		PageModel pageModel = ControllerUtils.getPageModel(request);
		//2.交互
		List<PinyipinContentEntity> pinyipinContentEntityList = pinyipinService.getLaunchContentList(userId,pageModel);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		resList = pinyipinService.pinyipinContent2MapList(userId, pinyipinContentEntityList);
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}
	
	/**
	 * 查询拼单详情
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryPinyipinContentDetail.json")
	@ResponseBody
	public JsonResponse qryPinyipinContentDetail(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String contentIdStr = request.getParameter("contentId");
		BigInteger contentId = new BigInteger(contentIdStr);
		BigInteger userId = UserContext.getOperIdBigInteger();
		//2.交互
		PinyipinContentEntity tmpEntity = pinyipinService.getPinyipinContentDetail(userId, contentId);
		//3.结果处理
		Map<String,Object> tmpMap = pinyipinService.pinyipinContent2Map(null,userId, tmpEntity);
		jsonResponse.setDataValue(tmpMap);
		return jsonResponse;
	}
	
//	/**
//	 * 查询查询我发起的拼单详情
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/qryLaunchContentDetail.json")
//	@ResponseBody
//	public JsonResponse qryLaunchContentDetail(HttpServletRequest request){
//		JsonResponse jsonResponse = new JsonResponse();
//		//1.搜集参数
//		String contentIdStr = request.getParameter("contentId");
//		BigInteger contentId = new BigInteger(contentIdStr);
//		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
//		//2.交互
//		PinyipinContentEntity pinyipinContentEntity = pinyipinService.getLaunchContentDetail(userId, contentId);
//		//3.结果处理
//		Map<String,Object> tmpMap = pinyipinService.pinyipinContent2Map(userId, pinyipinContentEntity, pinyipinContentEntity.getPinyipinPicList(), pinyipinContentEntity.getPinyipinReserveList());
//		jsonResponse.setDataValue(tmpMap);
//		return jsonResponse;
//	}
	
	
	/**
	 * 查询我参与的拼单列表
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
		List<PinyipinContentEntity> pinyipinContentEntityList = pinyipinService.getJoinContentList(userId,pageModel);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		resList = pinyipinService.pinyipinContent2MapList(userId, pinyipinContentEntityList);
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}
	
	/**
	 * 查询我发起的和参与的拼单列表
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
//		String nowUpdTime = pinyipinService.fetchAllLaunchAndJoinContentListLastUpdTime(userId);
//		if(!ControllerUtils.checkHasNewData(request, jsonResponse, nowUpdTime)){
//			return jsonResponse;
//		}
		List<PinyipinContentEntity> pinyipinContentEntityList = pinyipinService.getLaunchAndJoinContentList(userId,pageModel);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		resList = pinyipinService.pinyipinContent2MapList(userId, pinyipinContentEntityList);
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}
	
	/**
	 * 用户发起拼单
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
		Long marketPrice = Long.parseLong(request.getParameter("marketPrice"));
		Long realPrice = Long.parseLong(request.getParameter("realPrice"));
		String priceUnit = request.getParameter("priceUnit");
		Long goalCount = Long.parseLong(request.getParameter("goalCount"));
		Integer sendType = Integer.parseInt(request.getParameter("sendType"));
		String selfAddress = request.getParameter("selfAddress");
		String endDate = request.getParameter("endDate");
		String sendDate = request.getParameter("sendDate");
		String contectInfo = request.getParameter("contectInfo");
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
		//用户Id
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		validateSendType(sendType);
		pinyipinService.confirmLaunchContent(userId, title, desc, marketPrice, realPrice, priceUnit, goalCount, sendType, selfAddress, endDate, sendDate, contectInfo, picList);
		//3.结果处理
		return jsonResponse;
	}
	
	
	/**
	 * 用户删除发起的拼单
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
		pinyipinService.delLaunchContent(userId, contentId);
		//3.结果处理
		return jsonResponse;
	}
	
	/**
	 * 用户修改发起的拼单
	 * @param request
	 * @return
	 */
	@RequestMapping("/updLaunchConent.json")
	@ResponseBody
	public JsonResponse updLaunchConent(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String contentIdStr = request.getParameter("contentId");
		BigInteger contentId = new BigInteger(contentIdStr);//拼单Id
		String delPicIdsStr = request.getParameter("delPicIds");
		List<BigInteger> delPicIds = null;
		if(!StringUtils.isEmpty(delPicIdsStr)){
			delPicIds = JSON.parseArray(delPicIdsStr, BigInteger.class);
		}
		//
		String title = request.getParameter("title");
		String desc = request.getParameter("desc");
		Long marketPrice = Long.parseLong(request.getParameter("marketPrice"));
		Long realPrice = Long.parseLong(request.getParameter("realPrice"));
		String priceUnit = request.getParameter("priceUnit");
		Long goalCount = Long.parseLong(request.getParameter("goalCount"));
		Integer sendType = Integer.parseInt(request.getParameter("sendType"));
		String selfAddress = request.getParameter("selfAddress");
		String endDate = request.getParameter("endDate");
		String sendDate = request.getParameter("sendDate");
		String contectInfo = request.getParameter("contectInfo");
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
		//用户Id
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		pinyipinService.updLaunchConent(userId, contentId, title, desc, marketPrice, realPrice, priceUnit
				,goalCount, sendType, selfAddress, endDate, sendDate, contectInfo, picList,delPicIds);
		//3.结果处理
		return jsonResponse;
	}
	
//	/**
//	 * 参与拼单用户查看预定拼单详情
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/qryJoinReserveContentDetail.json")
//	@ResponseBody
//	public JsonResponse qryJoinReserveContentDetail(HttpServletRequest request){
//		JsonResponse jsonResponse = new JsonResponse();
//		//1.搜集参数
//		String contentIdStr = request.getParameter("contentId");
//		BigInteger contentId = new BigInteger(contentIdStr);
//		BigInteger userId = UserContext.getOperIdBigInteger();
//		//2.交互
//		PinyipinContentEntity pinyipinContentEntity = pinyipinService.getJoinReserveContentDetail(userId, contentId);
//		//3.结果处理
//		Map<String,Object> resMap = pinyipinService.pinyipinContent2Map(userId, pinyipinContentEntity, pinyipinContentEntity.getPinyipinPicList(), pinyipinContentEntity.getPinyipinReserveList());
//		jsonResponse.setDataValue(resMap);
//		return jsonResponse;
//	}
	
	/**
	 * 用户确认参与拼单
	 * @param request
	 * @return
	 */
	@RequestMapping("/confirmJoinContent.json")
	@ResponseBody
	public JsonResponse confirmJoinContent(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String contentIdStr = request.getParameter("contentId");
		BigInteger contentId = new BigInteger(contentIdStr);//拼单Id
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		Long count = Long.parseLong(request.getParameter("count"));//数量
		BigInteger deliveryAddressId = new BigInteger(request.getParameter("deliveryAddressId"));//配送地址Id
		//2.交互
		pinyipinService.freshContentDeliveryStatus(contentId);
		pinyipinService.confirmJoinContent(userId, contentId, count, deliveryAddressId);
		//3.结果处理
		return jsonResponse;
	}
	
	/**
	 * 发起拼单用户查看预定列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryLaunchPinyipinReserveList.json")
	@ResponseBody
	public JsonResponse qryLaunchPinyipinReserveList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String contentIdStr = request.getParameter("contentId");
		BigInteger contentId = new BigInteger(contentIdStr);//拼单Id
//		PageModel pageModel = ControllerUtils.getPageModel(request);
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		List<PinyipinReserveEntity> pinyipinReserveEntityList = pinyipinService.getLaunchPinyipinReserveList(userId, contentId);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		Integer joinUserCount = 0;
		Long totalUnitCount = 0L;
		Integer clainUserCount = 0; 
		if(pinyipinReserveEntityList!=null){
			for(PinyipinReserveEntity tmpEntity:pinyipinReserveEntityList){
				joinUserCount++;
				totalUnitCount+=tmpEntity.getCount();
				if(PinyipinDict.PinyipinReserve_ClaimStatus.HasClaimed.compareTo(tmpEntity.getClaimStatus())==0){
					clainUserCount++;
				}
			}
			resList = pinyipinService.pinyipinReserve2MapList(userId, pinyipinReserveEntityList);
		}
		//附加业务数据
		Map<String,Object> extBusiData = new HashMap<String, Object>();
		{
			extBusiData.put("joinUserCount", joinUserCount);
			extBusiData.put("totalUnitCount", totalUnitCount);
			extBusiData.put("clainUserCount", clainUserCount);
			PinyipinContentEntity tmpPinyipinContentEntity = pinyipinService.getPinyipinContentDetail(userId, contentId);
			Map<String,Object> pinyipinContentMap = pinyipinService.pinyipinContent2Map(null, userId, tmpPinyipinContentEntity);
			extBusiData.put("pinyipinContent", pinyipinContentMap);
		}
		
		jsonResponse.putData("extBusiData", extBusiData);
//		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}
	
	/**
	 * 修改指定用户参与的拼单数量(总量)
	 * @param request
	 * @return
	 */
	@RequestMapping("/updReserveContentByTotalCount.json")
	@ResponseBody
	public JsonResponse updReserveContentByTotalCount(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger reverseId = new BigInteger(request.getParameter("reverseId"));
		Integer totalCount = Integer.parseInt(request.getParameter("totalCount"));
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		pinyipinService.updReserveContentByTotalCount(userId,reverseId, totalCount);
		//3.结果处理
		return jsonResponse;
	}
	
	/**
	 * 修改指定用户参与的拼单数量(增量)
	 * @param request
	 * @return
	 */
	@RequestMapping("/updReserveContentByChangeCount.json")
	@ResponseBody
	public JsonResponse updReserveContentByChangeCount(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger reverseId = new BigInteger(request.getParameter("reverseId"));
		Integer changeCount = Integer.parseInt(request.getParameter("changeCount"));
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		pinyipinService.updReserveContentByChangeCount(userId,reverseId,changeCount);
		//3.结果处理
		return jsonResponse;
	}
	
	/**
	 * 生成发货清单
	 * @param request
	 * @return
	 */
	@RequestMapping("/confirmDeliveryOrder.json")
	@ResponseBody
	public JsonResponse confirmDeliveryOrder(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger contentId = new BigInteger(request.getParameter("contentId"));
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		pinyipinService.confirmDeliveryOrder(userId,contentId);
		//3.结果处理
		return jsonResponse;
	}
	
	/**
	 * 发起拼单者标记已领和未领
	 * @param request
	 * @return
	 */
	@RequestMapping("/markClaimStatus.json")
	@ResponseBody
	public JsonResponse markClaimStatus(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger reverseId = new BigInteger(request.getParameter("reverseId"));
		Integer calimStatus = Integer.parseInt(request.getParameter("calimStatus"));
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		pinyipinService.markClaimStatus(userId,reverseId,calimStatus);
		//3.结果处理
		return jsonResponse;
	}
	
	private void validateSendType(Integer sendType){
		if(sendType!=null&&
				(PinyipinDict.PinyipinContent_SendType.HomeDelivery.compareTo(sendType)==0||
					PinyipinDict.PinyipinContent_SendType.SelfAddress.compareTo(sendType)==0)
				){
			//校验通过
		}else{
			throw new ValidateRuntimeException("pinyipin.confirmLaunchContent.sendType.error");
		}
	}
	
	
}
