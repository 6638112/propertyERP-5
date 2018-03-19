package com.cnfantasia.server.api.comments.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.cnfantasia.server.api.comments.constant.CommentsConstant;
import com.cnfantasia.server.api.comments.entity.CommentsEntity;
import com.cnfantasia.server.api.comments.entity.CommentsPointsParamEntity;
import com.cnfantasia.server.api.comments.service.ICommentsService;
import com.cnfantasia.server.api.commonBusiness.service.ICommEntityConvertService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.StringUtils;


@Controller
@RequestMapping("/comments")
public class CommentsController extends BaseController{
	private ICommentsService commentsService;
	public void setCommentsService(ICommentsService commentsService) {
		this.commentsService = commentsService;
	} 
	
	private ICommEntityConvertService commEntityConvertService;
	public void setCommEntityConvertService(ICommEntityConvertService commEntityConvertService) {
		this.commEntityConvertService = commEntityConvertService;
	}
	
	private Double fetchParamDouble(HttpServletRequest request,String paramName){
		Double starLevel = null;
		{
			String starLevelStr = request.getParameter(paramName);
			if(!StringUtils.isEmpty(starLevelStr)){
				starLevel = Double.valueOf(starLevelStr);
			}
		}
		return starLevel;
	}
	
	@RequestMapping("/postComment.json")
	@ResponseBody
	public JsonResponse postComment(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = ParamUtils.getBigInteger(request, "userId", UserContext.getOperIdMustExistBigInteger());
		Integer goalType = Integer.parseInt(request.getParameter("goalType"));
		BigInteger goalId = new BigInteger(request.getParameter("goalId"));
		BigInteger goalId2nd = ParamUtils.getBigInteger(request,"goalId2nd", null);
		Double starLevel = fetchParamDouble(request, "starLevel");//星级
//		Double costPerformLevel = fetchParamDouble(request, "costPerformLevel");//性价比分数
//		Double techLevel = fetchParamDouble(request, "techLevel");//技师评分 
//		Double envLevel = fetchParamDouble(request, "envLevel");//环境评分
		String txtContent = request.getParameter("txtContent");
		List<BigInteger> labelIds = JSON.parseArray(request.getParameter("labelList"), BigInteger.class);
		
		List<CommentsPointsParamEntity> commentsPointsParamList = null;
		String pointsListStr = request.getParameter("pointsList");
		if(!StringUtils.isEmpty(pointsListStr)){
			Map<BigInteger,Double> pointsMap = null;
//			pointsList = JSON.parseObject(pointsListStr, new TypeReference<List<Map<BigInteger,Double>>>(){});
			pointsMap = JSON.parseObject(pointsListStr, new TypeReference<Map<BigInteger,Double>>(){});
			if(pointsMap!=null&&pointsMap.size()>0){
				commentsPointsParamList = new ArrayList<CommentsPointsParamEntity>();
				for(BigInteger key:pointsMap.keySet()){
					if(key!=null){
						BigInteger commentsPointsId = key;
						Double pointsValue = pointsMap.get(key);
						if(pointsValue!=null&&pointsValue>=0){
							CommentsPointsParamEntity tmpPoint = new CommentsPointsParamEntity(commentsPointsId, Double.valueOf(CommentsConstant.Default_Comments_Level_Format.format(pointsValue)));
							commentsPointsParamList.add(tmpPoint);
						}
					}
				}
			}
		}
		
		//BigInteger userId = UserContext.getOperIdMustExistBigInteger();
/*允许内容为空
 * 		if(StringUtils.isEmpty(txtContent)&&(labelIds==null||labelIds.size()==0)){
			throw new BusinessRuntimeException("comments.postComment.content.empty");
		}*/
		//2014-11-24 11:20:30 编码过滤
//		if(!StringUtils.isEmpty(txtContent)){//syl-del-2015-1-20 21:23:16
//			txtContent = MYSQLEncoder.encode(txtContent);
//		}
		//2.交互
		commentsService.postComments(goalType, goalId, goalId2nd, txtContent, userId,labelIds,starLevel,commentsPointsParamList);
		//3.结果处理
		return jsonResponse;
	}
	
	@RequestMapping("/qryCommentList.json")
	@ResponseBody
	public JsonResponse qryCommentList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer goalType = Integer.parseInt(request.getParameter("goalType"));
		BigInteger goalId = new BigInteger(request.getParameter("goalId"));
		PageModel pageModel = ControllerUtils.getPageModel(request);
		//2.交互
		List<CommentsEntity> commentsEntityList = commentsService.getCommentsList(goalType, goalId, pageModel);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(CommentsEntity commentsEntity:commentsEntityList){
			Map<String,Object> tmpMap = comments2Map(commentsEntity);
			resList.add(tmpMap);
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}
	@RequestMapping("/qryCommentListAll.json")
	@ResponseBody
	public JsonResponse qryCommentListAll(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer goalType = Integer.parseInt(request.getParameter("goalType"));
		BigInteger goalId = new BigInteger(request.getParameter("goalId"));
		//2.交互
		List<CommentsEntity> commentsEntityList = commentsService.getCommentsList(goalType, goalId);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(CommentsEntity commentsEntity:commentsEntityList){
			Map<String,Object> tmpMap = comments2Map(commentsEntity);
			resList.add(tmpMap);
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}
	
	@RequestMapping("/qryCommentDetail.json")
	@ResponseBody
	public JsonResponse qryCommentDetail(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger commentId =new BigInteger(request.getParameter("commentId"));
		//2.交互
		CommentsEntity commentsEntity = commentsService.getCommentsDetail(commentId);
		//3.结果处理
		{
			Map<String,Object> tmpMap = comments2Map(commentsEntity);
			jsonResponse.setDataValue(tmpMap);
		}
		return jsonResponse;
	}
	
	
	@RequestMapping("/delComment.json")
	@ResponseBody
	public JsonResponse delComment(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger commentId =new BigInteger(request.getParameter("commentId"));
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		commentsService.delComments(userId, commentId);
		//3.结果处理
		return jsonResponse;
	}

	/**
	 * 查询营销活动埋点
	 * @param request
	 * @return
     */
	@RequestMapping("/qryActivityUrl.json")
	@ResponseBody
	public JsonResponse qryActivityUrl(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String placeCode = ParamUtils.getString(request, "placeCode");
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		Map<String, Object> resMap = commentsService.qryActivityUrl(placeCode, userId);
		//3.结果处理
		jsonResponse.setDataValue(resMap);

		return jsonResponse;
	}

	private Map<String,Object> comments2Map(CommentsEntity commentsEntity){
		Double totalStarLevel = commentsEntity.getAvgTotalStarLevel();
		Map<String,Object> tmpMap = commEntityConvertService.comments2Map(commentsEntity, commentsEntity.getUserGroupBuilding(),commentsEntity.getUser(),commentsEntity.getNoticeUserList(),commentsEntity.getCommentsLabelList(),commentsEntity.getCommentsHasTCommentsPointsEntityList(),totalStarLevel);
		return tmpMap;
	}
	
}
