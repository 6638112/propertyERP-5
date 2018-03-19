package com.cnfantasia.server.api.ebuy.web;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.ebuy.service.IEguService;
import com.cnfantasia.server.api.ebuy.task.JsonToMap;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.utils.SignatureUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.service.IEbuyDeliveryOrderBaseService;
import com.cnfantasia.server.domainbase.ebuyExpressCompany.entity.EbuyExpressCompany;
import com.cnfantasia.server.domainbase.ebuyExpressCompany.service.IEbuyExpressCompanyBaseService;
import com.cnfantasia.server.domainbase.ebuyProductParametersTemp.entity.EbuyProductParametersTemp;
import com.cnfantasia.server.ms.ebuyProductTemp.entity.EbuyProductTempEntity;

/**
 * @author yewj
 */
@Controller
@RequestMapping("/egu")
public class EguController extends BaseController{
	
	private Log logger = LogFactory.getLog(getClass());
	
	private final static String SupplyMerchantCode = "egu";
	
	@Resource
	private IEbuyExpressCompanyBaseService ebuyExpressCompanyService;
	
	@Resource
	private IEguService eguService;
	
	@Resource
	private IUuidManager uuidManager;
	
	@Resource
	private IEbuyDeliveryOrderBaseService ebuyDeliveryOrderBaseService;
	
	/**
	 * 2.	订单校验接口
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveOrderCallBack.json")
	@ResponseBody
	public Object saveOrderCallBack(HttpServletRequest request){
		Map<String, Object> respMap = new HashMap<String, Object>();
		try {
			String eguTradeCode = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.egu_order_code);
			String key = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.egu_order_key);
			String trade_code = ParamUtils.getString(request, "trade_code", null);
			String order_id = ParamUtils.getString(request, "order_id", null);
			String signature = ParamUtils.getString(request, "signature", null);
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("trade_code", trade_code);
			paramMap.put("order_id", new BigInteger(order_id));
//			logger.debug("签名："+signature+">>>>>>>>>egu");
//			logger.debug("签名："+SignatureUtil.getSign(paramMap,key)+">>>>>>>>>local");
			if(SignatureUtil.getSign(paramMap,key).equalsIgnoreCase(signature)) {
				EbuyDeliveryOrder ebuyDeliveryOrder = eguService.selectTenmimsg(new BigInteger(order_id));
				if(ebuyDeliveryOrder != null){
//					paramMap.clear();
//					paramMap.put("orderNo", new BigInteger(order_id));
					ebuyDeliveryOrder.setStatus(1);
					ebuyDeliveryOrderBaseService.updateEbuyDeliveryOrder(ebuyDeliveryOrder);
//					eguService.updateDeliveryOrder(paramMap);
				}else{
					respMap.put("status", -1);
					respMap.put("errorMsg", "没有该订单记录！");
				}
			} else {
				respMap.put("status", -1);
				respMap.put("errorMsg", "签名信息错误！");
			}
		} catch(Exception e) {
			respMap.put("status", -1);
			String msg = e.getMessage();
			if(msg != null && msg.length() > 50) {
				msg = msg.substring(0, 50);
			}
			respMap.put("msg", msg);
		}
		return JSONObject.toJSONString(respMap);
	}
	
	
	/**
	 * 3.	商品同步接口
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveProduct.json")
	@ResponseBody
	public Object saveProduct(HttpServletRequest request){
		Map<String, Object> respMap = new HashMap<String, Object>();
		String prodId= "";
		try {
			String eguTradeCode = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.egu_order_code);
			String key = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.egu_order_key);
			
			prodId = ParamUtils.getString(request, "prodId", null);
			String prodName = ParamUtils.getString(request, "prodName", null);
			String prodMini = ParamUtils.getString(request, "prodMini", null);
			String priceDiscount = ParamUtils.getString(request, "priceDiscount", null);
			String prodType = ParamUtils.getString(request, "prodType", null);
			String price = ParamUtils.getString(request, "price", null);
			String params = ParamUtils.getString(request, "params", null);
			String deliverScope = ParamUtils.getString(request, "deliverScope", null);
			String status = ParamUtils.getString(request, "status", null);
			String leftCount = ParamUtils.getString(request, "leftCount", null);
			String signature = ParamUtils.getString(request, "signature", null);
			String images = ParamUtils.getString(request, "images", null);
			String trade_code = ParamUtils.getString(request, "trade_code", null);
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("prodId", prodId);
			paramMap.put("prodName", prodName);
			paramMap.put("prodMini", prodMini);
			paramMap.put("priceDiscount", priceDiscount);
			paramMap.put("prodType", prodType);
			paramMap.put("price", price);
			paramMap.put("params", params);
			paramMap.put("deliverScope", deliverScope);
			paramMap.put("status", status);
			paramMap.put("images", images);
			paramMap.put("leftCount", leftCount);//库存
			paramMap.put("trade_code", trade_code);//业务编号
			
			if(SignatureUtil.getSign(paramMap,key).equalsIgnoreCase(signature)) {
				List<EbuyProductParametersTemp> prdtParamList = new ArrayList<EbuyProductParametersTemp>();
				EbuyProductTempEntity product = new EbuyProductTempEntity();
				product.setName(prodName);
				product.setNameMini(prodMini);
				long proPrice =PriceUtil.multiply100(Double.parseDouble(price));
				long propriceDiscount =PriceUtil.multiply100(Double.parseDouble(priceDiscount));
				product.setPrice(proPrice);
				product.setPriceDiscount(propriceDiscount);
				product.setSrcId(new BigInteger(prodId));
				product.setFromWhere(SupplyMerchantCode);
				product.setLeftCount(new BigInteger(leftCount));
				product.setStatus(Integer.parseInt(status));
				product.setCreateTime(CnfantasiaCommbusiUtil.getCurrentTime());
				product.settEbuyProductTypeFId(eguTOjfqType(prodType));
				//商品参数添加
				//解析返回的商品参数
				if(StringUtils.isNotEmpty(params)){
					Map paramsMap = JsonToMap.jsonToMap(params);
					//遍历key，value存入list
			        Iterator i=paramsMap.entrySet().iterator();
			        while(i.hasNext()){
			        	Map.Entry e=(Map.Entry)i.next();
			        	EbuyProductParametersTemp parameterTemp = new EbuyProductParametersTemp();
			        	parameterTemp.settPropName(e.getKey().toString());
			        	parameterTemp.settPropValue(e.getValue().toString());
			        	prdtParamList.add(parameterTemp);
			        	}
				}
				//配送方式
				EbuyProductParametersTemp productParameterTemp = new EbuyProductParametersTemp();
				productParameterTemp.settPropName("配送范围");
				productParameterTemp.settPropValue(deliverScope.equals("1")?("全国范围"):("深圳范围"));//依谷网（1:全国范围，2:深圳范围）
				if(deliverScope.equals("1")){
					product.settSupplyMerchantFId(new BigInteger("204"));
				}else if(deliverScope.equals("2")){
					product.settSupplyMerchantFId(new BigInteger("212"));
				}else{
					product.settSupplyMerchantFId(new BigInteger("212"));
				}
				prdtParamList.add(productParameterTemp);
				product.setPrdtParamter(prdtParamList);
				eguService.updateORinsertproduct(product,images);
				respMap.put("status", 0);
				respMap.put("prodId", prodId);
				respMap.put("msg", "商品同步成功！");
			} else {
				respMap.put("status", -1);
				respMap.put("prodId", prodId);
				respMap.put("msg", "签名信息错误！");
			}
		} catch(Exception e) {
			e.printStackTrace();
			respMap.put("status", -1);
			respMap.put("prodId", prodId);
			String msg = e.getMessage();
			if(msg != null && msg.length() > 50) {
				msg = msg.substring(0, 50);
			}
			respMap.put("msg", msg);
		}
		return JSONObject.toJSONString(respMap);
	}
	
	/**
	 * 4.	物流信息同步接口
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveExpress.json")
	@ResponseBody
	public Object saveExpress(HttpServletRequest request){
		Map<String, Object> respMap = new HashMap<String, Object>();
		String orderId= "";
		try {
			String eguTradeCode = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.egu_order_code);
			String key = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.egu_order_key);
			
			orderId = ParamUtils.getString(request, "orderId", null);
			String expressNo = ParamUtils.getString(request, "expressNo", null);
			String expressName = ParamUtils.getString(request, "expressName", null);
			String expressFee = ParamUtils.getString(request, "expressFee", null);
			String signature = ParamUtils.getString(request, "signature", null);
			String trade_code = ParamUtils.getString(request, "trade_code", null);
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("orderId", orderId);//配送单号
			paramMap.put("expressNo", expressNo);//快递单号
			paramMap.put("expressName", expressName);//快递公司
			paramMap.put("expressFee", expressFee);//快递费
			paramMap.put("trade_code", trade_code);//业务编号
			if(SignatureUtil.getSign(paramMap,key).equalsIgnoreCase(signature)) {
				Map<String, Object> exMap = new HashMap<String, Object>();
				BigInteger exId =null;
				List<EbuyExpressCompany> exList = ebuyExpressCompanyService.getEbuyExpressCompanyByCondition(null);
				for(EbuyExpressCompany ex:exList){
					if((ex.getName()).equals(expressName)){
						exId = ex.getId();
					}
				}
				if(expressNo!=null){
					exMap.put("expressFee", Double.parseDouble(expressFee));
					exMap.put("expressNo", expressNo);
					exMap.put("expressCompanyId", exId);
					exMap.put("orderNo", orderId);
					eguService.updateExpress(exMap);
					respMap.put("status", 0);
					respMap.put("msg", "物流信息同步成功！");	
				}else{
					respMap.put("status", -1);
					respMap.put("errorMsg", "快递单号为空！");
				}
			} else {
				respMap.put("status", -1);
				respMap.put("errorMsg", "签名信息错误！");
			}
		} catch(Exception e) {
			respMap.put("status", -1);
			String msg = e.getMessage();
			if(msg != null && msg.length() > 50) {
				msg = msg.substring(0, 50);
			}
			respMap.put("msg", msg);
		}
		return JSONObject.toJSONString(respMap);
	}
	
	private BigInteger eguTOjfqType(String typeName){
		BigInteger typeId = null;
		String[] eguType = new String[] { "依谷套餐",
				"年节商品",
				"新鲜水果",
				"优质蔬菜",
				"禽肉蛋品",
				"乳品熟食",
				"水产海鲜",
				"冷冻冷藏",
				"休闲食品",
				"米面粮油",
				"干货特产",
				"调味食品",
		};
		String[] jfqType = new String[] {"18","18","2","1","9","14","9","9","14","10","16","10",};//解放区对应类型
		List<String> tempList = Arrays.asList(eguType);
		if(StringUtils.isNotEmpty(typeName)){
			if(tempList.contains(typeName)){
				int i =tempList.indexOf(typeName);
				typeId = new BigInteger(jfqType[i]);
			}else{
				typeId = new BigInteger("100022");//默认为生活日用
			}
		}else{
			typeId = new BigInteger("100022");//默认为生活日用
		}
		return typeId;
	}
	
}
