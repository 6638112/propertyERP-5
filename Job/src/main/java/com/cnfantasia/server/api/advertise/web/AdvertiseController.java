/**   
* Filename:    AdvertiseController.java   
* @version:    1.0  
* Create at:   2014年10月30日 上午2:14:01   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年10月30日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.advertise.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.advertise.entity.AdvertiseModel;
import com.cnfantasia.server.api.advertise.service.IAdvertiseService;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;

/**
 * Filename:    AdvertiseController.java
 * @version:    1.0.0
 * Create at:   2014年10月30日 上午2:14:01
 * Description: 广告模块
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年10月30日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/advertise")
public class AdvertiseController extends BaseController{
	private IAdvertiseService advertiseService;
	public void setAdvertiseService(IAdvertiseService advertiseService) {
		this.advertiseService = advertiseService;
	}
	
//	/**
//	 * 街坊广告动态查询
//	 * @return
//	 */
//	@RequestMapping("/qryAdvListDym.json")
//	@ResponseBody
//	public JsonResponse qryListDym(HttpServletRequest request){
//		JsonResponse jsonResponse = new JsonResponse();
//		//1.搜集参数
//		BigInteger userId = UserContext.getOperIdBigInteger();
//		String version = request.getHeader("version");
//		//2.交互
//		List<AdvertiseModel> resModelList = advertiseService.getCommunityAdsDym(userId, version);
//		//3.结果处理
//		List<Map<String,Object>> resList = advertiseModel2MapList(resModelList);
//		return ControllerUtils.addPageInfo(jsonResponse, resList);
//	}
	
	
	/**
	 * 街坊模块的推荐
	 * @return
	 */
	@RequestMapping("/qryCommunitySuggest.json")
	@ResponseBody
	public JsonResponse qryCommunitySuggest(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdBigInteger();
		String version = request.getHeader("version");
//		 Enumeration enumAA = request.getHeaderNames();
//		 while(enumAA.hasMoreElehments()){
//			 System.out.println(enumAA.nextElement());
//		 }
		//2.交互
//		List<AdvertiseModel> resModelList = advertiseService.getAdvertiseList(userId, version);
		List<AdvertiseModel> resModelList = advertiseService.getCommunityAdsDym(userId, version);
		//3.结果处理
		List<Map<String,Object>> resList = advertiseModel2MapList(resModelList);
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}
	
	/**
	 * 街坊模块的推荐
	 * @return
	 */
	@RequestMapping("/picRecord.html")
	public String picRecord(HttpServletRequest request){
		//1.搜集参数
		String go = ParamUtils.getString(request, "go", "#");
		//2.交互
		//3.结果处理
		return "redirect:"+go;
	}
	
	private Map<String,Object> advertiseModel2Map(AdvertiseModel tmpAdvertiseModel){
		String currServerBaseUrl = ApplicationContextBothUtil.getSysParamManager().getSysParaValue(SysParamKey.Curr_Server_BaseUrl)+"advertise/picRecord.html?go=";
		Map<String,Object> resMap = new HashMap<String, Object>();
		resMap.put("id", tmpAdvertiseModel.getId());
		resMap.put("dataType", tmpAdvertiseModel.getDataType());
		resMap.put("jsonEntity", tmpAdvertiseModel.getJsonEntity());
		resMap.put("picUrl", currServerBaseUrl+tmpAdvertiseModel.getPicUrl());
		return resMap;
	}
	
	public List<Map<String,Object>> advertiseModel2MapList(List<AdvertiseModel> tmpAdvertiseModelList){
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		if(tmpAdvertiseModelList!=null){
			for(AdvertiseModel tmpModel:tmpAdvertiseModelList){
				Map<String,Object> tmpMap = advertiseModel2Map(tmpModel);
				resList.add(tmpMap);
			}
		}
		return resList;
	}
	
}
