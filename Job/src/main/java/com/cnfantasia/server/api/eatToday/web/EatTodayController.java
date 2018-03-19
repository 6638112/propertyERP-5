/**   
* Filename:    EatTodayController.java   
* @version:    1.0  
* Create at:   2014年5月24日 上午4:57:53   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月24日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.eatToday.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.eatToday.entity.EatMenuRecommendEntity;
import com.cnfantasia.server.api.eatToday.service.IEatTodayService;
import com.cnfantasia.server.api.fileServer.constant.FileServerConstant;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename:    EatTodayController.java
 * @version:    1.0.0
 * Create at:   2014年5月24日 上午4:57:53
 * Description:今晚吃什么
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月24日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/eatToday")
public class EatTodayController extends BaseController{
	private IEatTodayService eatTodayService;
	public void setEatTodayService(IEatTodayService eatTodayService) {
		this.eatTodayService = eatTodayService;
	}
	private IFileServerService fileServerService;
	public void setFileServerService(IFileServerService fileServerService) {
		this.fileServerService = fileServerService;
	}
	private ISysParamManager sysParamManager;
	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}
	/**
	 * 获取推荐列表
	 */
	@RequestMapping("/getRecommend.json")
	@ResponseBody
	public JsonResponse getRecommend(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		//2.交互
		List<EatMenuRecommendEntity> recommendList = eatTodayService.getTodayRecommend();
		//3.结果处理
		String eatTodayPicBasePath = sysParamManager.getSysParaValue(SysParamKey.EatToday_PIC_BASE_PATH);//今晚吃什么图片信息根路径
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(EatMenuRecommendEntity tmpEntity:recommendList){
			Map<String,Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("id", tmpEntity.getEatMenu().getId());
			tmpMap.put("title", tmpEntity.getEatMenu().gettTitle());
			tmpMap.put("picBase", StringUtils.isEmpty(tmpEntity.getEatMenu().gettPicBase())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(eatTodayPicBasePath+tmpEntity.getEatMenu().gettPicBase(),tmpEntity.getEatMenu()));
			resList.add(tmpMap);
		}
		return ControllerUtils.addPageInfo(jsonResponse,resList);
	}
	
}
