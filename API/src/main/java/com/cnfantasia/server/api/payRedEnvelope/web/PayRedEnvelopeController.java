package com.cnfantasia.server.api.payRedEnvelope.web;

import java.math.BigInteger;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.payRedEnvelope.service.IPayRedEnvelopeService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.common.json.JsonResponse;

/**
 * 代扣卡转粮票
 * 
 * @author liyulin
 * @version 1.0 2016年11月4日 下午1:30:46
 */
@Controller
@RequestMapping("/payRedEnvelope")
public class PayRedEnvelopeController extends BaseController{
	
	@Resource
	private IPayRedEnvelopeService payRedEnvelopeService;

	/**
	 * 代扣卡转粮票
	 * 
	 * @return
	 */
	@RequestMapping("/propertyCard2Envelope.json")
	@ResponseBody
	public JsonResponse propertyCard2Envelope(){
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		JsonResponse jsonResponse = payRedEnvelopeService.propertyCard2Envelope(userId);
		return jsonResponse;
	}
	
}
