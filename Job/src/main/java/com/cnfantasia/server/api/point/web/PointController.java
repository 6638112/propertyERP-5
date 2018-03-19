/**   
* Filename:    PointController.java   
* @version:    1.0  
* Create at:   2014年12月29日 上午8:34:27   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.point.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.commonBusiness.service.ICommEntityConvertService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonPointService;
import com.cnfantasia.server.api.commonBusiness.util.RelativeDateFormat;
import com.cnfantasia.server.api.point.constant.PointConstant;
import com.cnfantasia.server.api.point.service.IPointService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.pointData.entity.PointData;
import com.cnfantasia.server.domainbase.pointSourceRecord.entity.PointSourceRecord;

/**
 * Filename:    PointController.java
 * @version:    1.0.0
 * Create at:   2014年12月29日 上午8:34:27
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月29日       shiyl             1.0             1.0 Version
 */
@RequestMapping("/point")
@Controller
public class PointController extends BaseController{
	private ICommonPointService commonPointService;
	public void setCommonPointService(ICommonPointService commonPointService) {
		this.commonPointService = commonPointService;
	}

	private IPointService pointService;
	public void setPointService(IPointService pointService) {
		this.pointService = pointService;
	}
	
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	private ICommEntityConvertService commEntityConvertService;
	public void setCommEntityConvertService(ICommEntityConvertService commEntityConvertService) {
		this.commEntityConvertService = commEntityConvertService;
	}
	
	/**
	 * 查询用户当前积分
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryCurrPoint.json")
	@ResponseBody
	public JsonResponse qryCurrPoint(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		PointData pointData = commonPointService.getPointDataByUserId(userId);
		//3.结果处理
		Map<String,Object> resMap = commEntityConvertService.pointData2Map(pointData);
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
	/**
	 * 查询积分来源列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryPointSourceList.json")
	@ResponseBody
	public JsonResponse qryPointSourceList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		PageModel pageModel = ControllerUtils.getPageModel(request);
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		
		List<PointSourceRecord> pointSourceRecordList = pointService.qryPointSourceList(userId, pageModel);
//		List<PointSourceRecord> pointSourceRecordList = new ArrayList<PointSourceRecord>();
//		{
//			pointSourceRecordList.add(new PointSourceRecord(new BigInteger("123"), new BigInteger("555"), new BigInteger("666"), 51L, "2014-12-28 11:03:02", 1,"厨房交作业",1L, null, null, null, null, null, null, null));
//			pointSourceRecordList.add(new PointSourceRecord(new BigInteger("124"), new BigInteger("555"), new BigInteger("666"), 61L, "2014-12-29 20:08:02", 1,"分享消息到朋友圈",1L, null, null, null, null, null, null, null));
//			pointSourceRecordList.add(new PointSourceRecord(new BigInteger("125"), new BigInteger("555"), new BigInteger("666"), 100L, "2014-12-30 21:03:02",1, "发起优质换物",1L, null, null, null, null, null, null, null));
//		}
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		String nowTime = dualDao.getNowTime();
		Long nowTimeLong = DateUtil.timeToLong(nowTime);
		for(PointSourceRecord tmpPointSourceRecord:pointSourceRecordList){
			Map<String,Object> tmpMap = pointSourceRecord2Map(tmpPointSourceRecord,nowTimeLong);
			resList.add(tmpMap);
		}
//		return ControllerUtils.addPageInfo(jsonResponse, resList);
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}
	
//	/**
//	 * 查询积分兑换列表
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/qryPointCostList.json")
//	@ResponseBody
//	public JsonResponse qryPointCostList(HttpServletRequest request){
//		JsonResponse jsonResponse = new JsonResponse();
//		//1.搜集参数
//		PageModel pageModel = ControllerUtils.getPageModel(request);
//		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
//		//2.交互
//		List<PointCostRecord> pointCostRecordList = pointService.qryPointCostList(userId, pageModel);
////		List<PointCostRecord> pointCostRecordList = new ArrayList<PointCostRecord>();
////		{
////			pointCostRecordList.add(new PointCostRecord(new BigInteger("456"), new BigInteger("555"), new BigInteger("666"), 999L, "2014-12-30 21:31:47", "实物礼品A",1, null, null, null, null, null, null, null));
////			pointCostRecordList.add(new PointCostRecord(new BigInteger("457"), new BigInteger("555"), new BigInteger("666"), 999L, "2014-12-31 21:31:47", "20 元手机话费",2, null, null, null, null, null, null, null));
////			pointCostRecordList.add(new PointCostRecord(new BigInteger("458"), new BigInteger("555"), new BigInteger("666"), 999L, "2014-12-31 21:31:47", "500 元云家政现金券",3, null, null, null, null, null, null, null));
////		}
//		//3.结果处理
//		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
//		String nowTime = dualDao.getNowTime();
//		Long nowTimeLong = DateUtil.timeToLong(nowTime);
//		for(PointCostRecord tmpPointCostRecord:pointCostRecordList){
//			Map<String,Object> tmpMap = pointCostRecord2Map(tmpPointCostRecord,nowTimeLong);
//			resList.add(tmpMap);
//		}
////		return ControllerUtils.addPageInfo(jsonResponse, resList);
//		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
//	}
	
//	/**
//	 * 查询积分兑换详情
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/qryPointCostDetail.json")
//	@ResponseBody
//	public JsonResponse qryPointCostDetail(HttpServletRequest request){//TODO 
//		JsonResponse jsonResponse = new JsonResponse();
//		//1.搜集参数
//		//2.交互
//		//3.结果处理
//		Map<String,Object> tmpMap = pointCostRecord2Map(null,null);//TODO 
//		jsonResponse.setDataValue(tmpMap);
//		return jsonResponse;
//	}
	
	private Map<String,Object> pointSourceRecord2Map(PointSourceRecord pointSourceRecord,Long nowTimeLong){
//		if(pointSourceRecord==null){
//			pointSourceRecord = new PointSourceRecord(new BigInteger("123"), new BigInteger("555"), new BigInteger("666"), 51L, "2014-12-30 21:03:02", 1,"厨房交作业",1L, null, null, null, null, null, null, null);
//		}
		if(nowTimeLong==null){
			String nowTime = dualDao.getNowTime();
			nowTimeLong = DateUtil.timeToLong(nowTime);
		}
		Map<String,Object> resMap = new HashMap<String, Object>();
		{//描述
			String desc = pointSourceRecord.getDesc();
			if(StringUtils.isEmpty(desc)){
				desc = PointConstant.getDescByPointSourceType(pointSourceRecord.getType());
			}
			resMap.put("desc", desc);
		}
		resMap.put("time", pointSourceRecord.getFromTime());
		resMap.put("timeLong", DateUtil.timeToLong(pointSourceRecord.getFromTime()));
		resMap.put("timeInfo",RelativeDateFormat.format(nowTimeLong-DateUtil.timeToLong(pointSourceRecord.getFromTime()),nowTimeLong));
		resMap.put("id", pointSourceRecord.getId());
		resMap.put("roomId", pointSourceRecord.getRoomId());
		resMap.put("userId", pointSourceRecord.getUserId());
		resMap.put("pointValue", pointSourceRecord.getValue());
		resMap.put("type", pointSourceRecord.getType());
		return resMap;
	}
	
//	private Map<String,Object> pointCostRecord2Map(PointCostRecord pointCostRecord,Long nowTimeLong){
////		if(pointCostRecord==null){
////			pointCostRecord = new PointCostRecord(new BigInteger("456"), new BigInteger("555"), new BigInteger("666"), 999L, "2014-12-30 21:31:47", "10 元手机话费",1, null, null, null, null, null, null, null);
////		}
//		if(nowTimeLong==null){
//			String nowTime = dualDao.getNowTime();
//			nowTimeLong = DateUtil.timeToLong(nowTime);
//		}
//		Map<String,Object> resMap = new HashMap<String, Object>();
//		resMap.put("desc", pointCostRecord.getCostDesc());//TODO 
//		resMap.put("time", pointCostRecord.getCostTime());
//		resMap.put("timeLong", DateUtil.timeToLong(pointCostRecord.getCostTime()));
//		resMap.put("timeInfo",RelativeDateFormat.format(nowTimeLong-DateUtil.timeToLong(pointCostRecord.getCostTime())));
//		resMap.put("id", pointCostRecord.getId());
//		resMap.put("roomId", pointCostRecord.getRoomId());
//		resMap.put("userId", pointCostRecord.getUserId());
//		resMap.put("pointValue", pointCostRecord.getValue());
//		resMap.put("type", pointCostRecord.getCostType());
//		resMap.put("picUrl", "http://www.test/aa.jpg");//TODO 
//		resMap.put("orderStatus", 4);//TODO  订单状态
//		resMap.put("useStatus", 1);//TODO 使用状态
//		resMap.put("picUrlDetail", "http://www.test/bb.jpg");//TODO 详情图片地址
//		resMap.put("codeValue", "4001234555");//TODO 兑换码
//		resMap.put("endTimeLong", DateUtil.timeToLong("2015-1-6 12:01:03"));//TODO 兑换码有效期
//		resMap.put("endTime", "2015-1-6 12:01:03");//TODO 兑换码有效期
//		return resMap;
//	}
	
}
