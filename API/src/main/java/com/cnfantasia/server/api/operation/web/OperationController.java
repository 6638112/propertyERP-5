/**   
* Filename:    OperationController.java   
* @version:    1.0  
* Create at:   2015年4月23日 上午9:06:18   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.operation.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.operation.entity.OperationConstantDataEntity;
import com.cnfantasia.server.api.operation.service.IOperationService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename:    OperationController.java
 * @version:    1.0.0
 * Create at:   2015年4月23日 上午9:06:18
 * Description:运维模块Controller
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月23日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/operation")
public class OperationController extends BaseController{
	
	private IOperationService operationService;
	public void setOperationService(IOperationService operationService) {
		this.operationService = operationService;
	}
	
	//syl-del-2015-8-14 16:31:05注释
//	/**
//	 * 查询服务目标Id列表
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/qryServiceDataList.json")
//	@ResponseBody
//	public JsonResponse qryServiceDataList(HttpServletRequest request){
//		JsonResponse jsonResponse = new JsonResponse();
//		//1.搜集参数
//		PageModel pageModel = ControllerUtils.getPageModel(request);
//		Integer targetType = ParamUtils.getInteger(request, "targetType", null);
//		if(targetType==null){
//			throw new BusinessRuntimeException("operation.qryServiceDataList.targetType.empty");
//		}
//		List<BigInteger> resList = null;
//		BigInteger countryId = ParamUtils.getBigInteger(request, "countryId", null);
//		BigInteger provinceId = ParamUtils.getBigInteger(request, "provinceId", null);
//		BigInteger cityId = ParamUtils.getBigInteger(request, "cityId", null);
//		BigInteger blockId = ParamUtils.getBigInteger(request, "blockId", null);
//		BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
//		//2.交互
////		resList = addressOperationService.getDataList(targetType, countryId,provinceId,cityId,blockId,gbId,pageModel);
//		resList = addressOperationService.getCodeIdList(countryId,provinceId,cityId,blockId,gbId);
//		//3.结果处理
//		if(resList==null){
//			return ControllerUtils.addPageInfo(jsonResponse, resList);
//		}else{
//			return ControllerUtils.addPageInfo(jsonResponse, resList);
////			return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
//		}
//	}
	
	/**
	 * 查询单个运维配置数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryOperationSignal.json")
	@ResponseBody
	public JsonResponse qryOperationSignal(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String code = request.getParameter("code");
		//2.交互
		OperationConstantDataEntity operationConstantData = operationService.getOperationSignal(code);
		//3.结果处理
		Map<String,Object> resMap = operationConstantData2Map(operationConstantData);
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
	/**
	 * 查询多个运维配置数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryOperationMulti.json")
	@ResponseBody
	public JsonResponse qryOperationMulti(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String codeListStr = request.getParameter("codeList");
		List<String> codeList = null;
		if(!StringUtils.isEmpty(codeListStr)){
			codeList = JSON.parseArray(codeListStr, String.class);
		}
		//2.交互
		List<OperationConstantDataEntity> dataList = operationService.getOperationMulti(codeList);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		if(dataList!=null&&dataList.size()>0){
			for(OperationConstantDataEntity tmpData:dataList){
				Map<String,Object> tmpMap = operationConstantData2Map(tmpData);
				resList.add(tmpMap);
			}
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}
	
	private Map<String,Object> operationConstantData2Map(OperationConstantDataEntity tmpEntity){
		if(tmpEntity==null){return null;}
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("code", tmpEntity.getCode());
		tmpMap.put("content", operationService.getOperationConstantFinalContent(tmpEntity));
		tmpMap.put("dataType",tmpEntity.getDataType());
		tmpMap.put("id", tmpEntity.getId());
		return tmpMap;
	}
	
}
