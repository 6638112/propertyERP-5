/**   
* Filename:    SuggestController.java   
* @version:    1.0  
* Create at:   2014年11月14日 上午9:08:35   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年11月14日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.suggest.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.suggest.service.ISuggestService;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.commFeedbackLabel.entity.CommFeedbackLabel;

/**
 * Filename:    SuggestController.java
 * @version:    1.0.0
 * Create at:   2014年11月14日 上午9:08:35
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年11月14日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/suggest")
public class SuggestController extends BaseController{
	private ISuggestService suggestService;
	public void setSuggestService(ISuggestService suggestService) {
		this.suggestService = suggestService;
	}

	@RequestMapping("/qrySuggestLabelList.json")
	@ResponseBody
	public JsonResponse qrySuggestLabelList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		//2.交互
		List<CommFeedbackLabel> commFeedbackLabelList = suggestService.getCommFeedbackLabelList();
		//3.结果处理
		List<Map<String,Object>> resList = commFeedbackLabel2MapList(commFeedbackLabelList);
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}
	
	@RequestMapping("/submitSuggest.json")
	@ResponseBody
	public JsonResponse submitSuggest(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String detail = request.getParameter("detail");
		String contect = request.getParameter("contect");
		String commFeedbackLabelIdStr = request.getParameter("commFeedbackLabelId");
		BigInteger commFeedbackLabelId = null;
		if(!StringUtils.isEmpty(commFeedbackLabelIdStr)){
			commFeedbackLabelId = new BigInteger(commFeedbackLabelIdStr);
		}
		BigInteger userId = UserContext.getOperIdBigInteger();
		//2.交互
		suggestService.submitSuggest(userId, detail, contect, commFeedbackLabelId);
		//3.结果处理
		return jsonResponse;
	}
	
	private Map<String,Object> commFeedbackLabel2Map(CommFeedbackLabel tmpLabel){
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("id", tmpLabel.getId());
		tmpMap.put("name", tmpLabel.getName());
		tmpMap.put("desc", tmpLabel.getDesc());
		return tmpMap;
	}
	
	private List<Map<String,Object>> commFeedbackLabel2MapList(List<CommFeedbackLabel> tmpLabelList){
		List<Map<String,Object>> resList = null;
		if(tmpLabelList!=null){
			resList = new ArrayList<Map<String,Object>>();
			for(CommFeedbackLabel tmpLabel:tmpLabelList){
				Map<String,Object> tmpMap = commFeedbackLabel2Map(tmpLabel);
				resList.add(tmpMap);
			}
		}
		return resList;
	}
	
}
