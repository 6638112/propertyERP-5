package com.cnfantasia.server.api.buildingKeyConfig.web;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.buildingKeyConfig.service.IBuildingKeyConfigService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domainbase.buildingKeyConfig.entity.BuildingKeyConfig;

/**
 * 门禁认证选项配置管理Controller
 *
 * @author Liyl
 * @version 1.0 2016年3月22日 下午5:57:52
 */
@Controller
@RequestMapping("/buildingKeyConfig")
public class BuildingKeyConfigController  extends BaseController{
	
	private Log logger = LogFactory.getLog(getClass());

	@Resource
	private IBuildingKeyConfigService buildingKeyConfigService;
	
	/**
	 * 根据小区id查询门禁认证选项配置
	 * @return
	 */
	@RequestMapping("getGroupBuildingKeyConfigById.json")
	@ResponseBody
	public JsonResponse getGroupBuildingKeyConfigById(BigInteger tGroupBuildingFId){
		JsonResponse jsonResponse = new JsonResponse();
		if(tGroupBuildingFId==null){
			jsonResponse.setMessage("小区id不能为空！");
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
			return jsonResponse;
		} else {
			List<BuildingKeyConfig> configList = buildingKeyConfigService.selectBuildingKeyConfigByCondition(tGroupBuildingFId);
			// 将inputEnum的json数组转化为List<Map>
			for(BuildingKeyConfig buildingKeyConfig:configList){
				if(buildingKeyConfig.getInputType()==2){// if it is a radio type
					String inputEnum = buildingKeyConfig.getInputEnum();
					buildingKeyConfig.setEnumList(JSON.parseArray(inputEnum, Map.class));
				}
			}
			
			
			jsonResponse.setDataValue(configList);
			jsonResponse.setMessage("操作成功！");
			jsonResponse.setStatus(CommConstants.ResponseStatus.SUCCESS);
			
			logger.debug(JSON.toJSONString(jsonResponse));
			return jsonResponse;
		}
	}
	
	/*public static void main(String[] args) throws IOException {
		String text = FileUtils.readFileToString(new File("E:/test.txt"), "utf-8");
		Map<String, Object> map = JSON.parseObject(text);
	}*/
}
