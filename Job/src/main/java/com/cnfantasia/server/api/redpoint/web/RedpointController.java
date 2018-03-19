/**   
* Filename:    RedpointController.java   
* @version:    1.0  
* Create at:   2015年3月24日 上午8:11:02   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月24日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redpoint.web;

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
import com.cnfantasia.server.api.commonBusiness.entity.UserIdType;
import com.cnfantasia.server.api.commonBusiness.service.ICommonDeviceService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.redpoint.entity.RedpointContentEntity;
import com.cnfantasia.server.api.redpoint.service.IRedpointService;
import com.cnfantasia.server.common.json.JsonResponse;

/**
 * Filename:    RedpointController.java
 * @version:    1.0.0
 * Create at:   2015年3月24日 上午8:11:02
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月24日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/redpoint")
public class RedpointController extends BaseController{
	
	private IRedpointService redpointService;
	public void setRedpointService(IRedpointService redpointService) {
		this.redpointService = redpointService;
	}
	
	private ICommonDeviceService commonDeviceService;
	public void setCommonDeviceService(ICommonDeviceService commonDeviceService) {
		this.commonDeviceService = commonDeviceService;
	}

	/**
	 * 查询红点信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryRedpointInfo.json")
	@ResponseBody
	public JsonResponse qryRedpointInfo(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String modelCode = request.getParameter("modelCode");
		UserIdType userIdType = commonDeviceService.getUserIdType();
		BigInteger userId = userIdType.getUserId();
		Integer userType = userIdType.getUserType();
		//2.交互
		RedpointContentEntity tmpRedpointContentEntity = redpointService.qryRedpointInfo(userId,userType,modelCode);
		//3.结果处理
		Map<String,Object> resMap = redpointContentEntity2Map(tmpRedpointContentEntity);
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
	/**
	 * 查询红点列表信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryRedpointInfoMulti.json")
	@ResponseBody
	public JsonResponse qryRedpointInfoMulti(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		List<String> modelCodeList = JSON.parseArray(request.getParameter("modelCodeList"), String.class);;
		UserIdType userIdType = commonDeviceService.getUserIdType();
		BigInteger userId = userIdType.getUserId();
		Integer userType = userIdType.getUserType();
		//2.交互
		List<RedpointContentEntity> tmpRedpointContentEntityList = redpointService.qryRedpointInfoMulti(userId,userType,modelCodeList);
		//3.结果处理
		List<Map<String,Object>> resList = redpointContentEntity2Map(tmpRedpointContentEntityList);
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}
	
	/**
	 * 点击红点
	 * @param request
	 * @return
	 */
	@RequestMapping("/clickRedpointInfo.json")
	@ResponseBody
	public JsonResponse clickRedpointInfo(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String modelCode = request.getParameter("modelCode");
		UserIdType userIdType = commonDeviceService.getUserIdType();
		BigInteger userId = userIdType.getUserId();
		Integer userType = userIdType.getUserType();
		//2.交互
		RedpointContentEntity tmpRedpointContentEntity = redpointService.clickRedpointInfo(userId,userType,modelCode);
		//3.结果处理
		Map<String,Object> resMap = redpointContentEntity2Map(tmpRedpointContentEntity); 
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
	private List<Map<String,Object>> redpointContentEntity2Map(List<RedpointContentEntity> redpointContentEntityList){
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		if(redpointContentEntityList!=null&&redpointContentEntityList.size()>0){
			for(RedpointContentEntity tmpEntity:redpointContentEntityList){
				Map<String,Object> tmpMap = redpointContentEntity2Map(tmpEntity);
				resList.add(tmpMap);
			}
		}
		return resList;
	}
	
	private Map<String,Object> redpointContentEntity2Map(RedpointContentEntity redpointContentEntity){
		Map<String,Object> resMap = new HashMap<String, Object>();
		if(redpointContentEntity!=null){
			resMap.put("modelCode", redpointContentEntity.getModelCode());
			resMap.put("clickStatus", redpointContentEntity.fetchClickStatus());
//			resMap.put("msgCount", 10);//待阅的消息数量
			resMap.put("lastClickTime", redpointContentEntity.getLastClickTime());
			resMap.put("lastModifyTime", redpointContentEntity.getLastModifyTime());
		}
		return resMap;
	}
	
	
}
