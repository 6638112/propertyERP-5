/**   
* Filename:    SignController.java   
* @version:    1.0  
* Create at:   2015年1月6日 上午3:07:26   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月6日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.point.web;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.commonBusiness.service.ICommonPointService;
import com.cnfantasia.server.api.point.constant.PointConstant;
import com.cnfantasia.server.api.point.entity.SignResultEntity;
import com.cnfantasia.server.api.point.service.ISignService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domainbase.commSignRecord.entity.CommSignRecord;
import com.cnfantasia.server.domainbase.pointData.entity.PointData;

/**
 * Filename:    SignController.java
 * @version:    1.0.0
 * Create at:   2015年1月6日 上午3:07:26
 * Description:签到
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月6日       shiyl             1.0             1.0 Version
 */
@RequestMapping("/sign")
@Controller
public class SignController extends BaseController{
	private ICommonPointService commonPointService;
	public void setCommonPointService(ICommonPointService commonPointService) {
		this.commonPointService = commonPointService;
	}

	private ISignService signService;
	public void setSignService(ISignService signService) {
		this.signService = signService;
	}
	
	/**
	 * 查询最近一个签到信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryLastSignInfo.json")
	@ResponseBody
	public JsonResponse qryLastSignInfo(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		CommSignRecord commSignRecord = signService.getLastCommSignRecord(userId);
		//3.结果处理
		Map<String,Object> resMap = commSignRecord2Map(commSignRecord,null);
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
	/**
	 * 进行签到
	 * @param request
	 * @return
	 */
	@RequestMapping("/doSign.json")
	@ResponseBody
	public JsonResponse doSign(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
//		SignResultEntity signResult = signService.doSign(userId);
		//查询当前积分
		PointData currPointData = commonPointService.getPointDataByUserId(userId);
		//3.结果处理
//		Map<String,Object> resMap = commSignRecord2Map(signResult.getCommSignRecord(),signResult.getTodayFirst());
		Map<String,Object> resMap = new HashMap<String, Object>();
		resMap.put("ext_currPoint", currPointData==null?0:currPointData.getPointValue());
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
	private Map<String,Object> commSignRecord2Map(CommSignRecord record,Boolean todayFirst){
		Map<String,Object> resMap = new HashMap<String, Object>();
		resMap.put("id", record.getId());
		resMap.put("dayCount", record.getDayCount());
		resMap.put("roomId", record.getRoomId());
		resMap.put("time", record.getTime());
		resMap.put("timeLong", DateUtil.timeToLong(record.getTime()));
		resMap.put("userId", record.getUserId());
		{//签到获得的积分
			resMap.put("signPoint", PointConstant.getPointValueBySignDays(record.getDayCount()));
		}
		if(todayFirst!=null){//是否为当天首次签到
			resMap.put("isSignToday", todayFirst);
		}
		return resMap;
	}
	
	
}
