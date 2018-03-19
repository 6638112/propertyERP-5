package com.cnfantasia.server.ms.copyOrder.web;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.UUIDGenerater;
import com.cnfantasia.server.ms.copyOrder.entity.DeliveryOrderEntity;
import com.cnfantasia.server.ms.copyOrder.service.CopyOrderService;
import com.cnfantasia.server.ms.pub.BaseController;

@Controller
public class CopyOrderController extends BaseController{
	
	@Autowired
	private ISysParamManager sysParamManager;
	@Autowired
	private CopyOrderService copyOrderService;
	
	/**
	 * 复制订单
	 * @param ids 运单表id（数组，序列化后传过来）
	 * @return
	 */
	@RequestMapping("/copyOrder.json")
	@ResponseBody
	public JsonResponse copyOrder(String ids){
		//1、生成uuid、4位code。
		String uuid = UUIDGenerater.getId();
		String code = UUIDGenerater.generateShortUuid();
		
		//2、将uuid、code、productIds存于redis，并设置缓存时间2天。
		/**默认有效期：2天*/
		int epSeconds = CnfantasiaCommbusiUtil.getSysParaValueInt(SysParamKey.COPY_ORDER_CACHE_TIME, 2*24*60*60);
		String key = uuid+code.toLowerCase();
		RedisCacheHandler.set(key, ids, epSeconds);
		
		//3、将code、唯一url返回给前端。
		String oosPath = sysParamManager.getSysParaValue(SysParamKey.OOS_PATH);
		String url = oosPath + "/api_manager/goCopyOrderPage/"+uuid+".json";
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.putData("url", url);
		jsonResponse.putData("code", code);
		return jsonResponse;
	}
	
	/**
	 * 进入已复制的订单信息界面
	 * @param uuid
	 * @return
	 */
	@RequestMapping("/goCopyOrderPage/{uuid}.json")
	public ModelAndView goCopyOrderPage(@PathVariable("uuid") String uuid){
		ModelAndView modelAndView = new ModelAndView("/copyOrder/copyOrderInfo");
		modelAndView.addObject("uuid", uuid);
		return modelAndView;
	}
	
	/**
	 * 查询复制的订单信息
	 * @param code
	 * @param uuid
	 * @return
	 */
	@RequestMapping("/queryCopyOrder.json")
	@ResponseBody
	public JsonResponse queryCopyOrder(String code, String uuid){
		String key = uuid + code.toLowerCase();
		String ids = RedisCacheHandler.get(key);
		
		JsonResponse jsonResponse = new JsonResponse();
		if(StringUtils.isNotBlank(ids)){
			List<BigInteger> idList = JSON.parseArray(ids, BigInteger.class);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("idList", idList);
			List<DeliveryOrderEntity> deliveryOrderList = copyOrderService.getDeliveryOrderForCopy(paramMap);
			
			jsonResponse.setDataValue(deliveryOrderList);
		} else {
			jsonResponse.setMessage("code不正确！获取订单信息失败！");
			jsonResponse.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
		}
		
		return jsonResponse;
	}
}
