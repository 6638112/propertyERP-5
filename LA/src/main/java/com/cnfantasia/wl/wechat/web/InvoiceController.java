package com.cnfantasia.wl.wechat.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.ms.pub.BaseController;

/**
 * 发票
 * 
 * @author wenfq 2015-02-22
 * 
 */
@Controller
@RequestMapping("/invoice")
public class InvoiceController extends BaseController {

	@Resource
	private IHttpClient simpleHttpClient;

	/**
	 * 发票查看
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/inoiceEdit.do")
	public ModelAndView invoiceEdit(HttpServletRequest request) {
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/ebuy/qryInvoice.json", null, LoginHelper.prepareReqHeader(request));
		request.setAttribute("jsonResponse", jsonResponse);

		ModelAndView view = new ModelAndView();
		view.setViewName("/ebuy/invoiceBillInfo");
		return view;
	}

	/**
	 * 发票更新
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateInvoic.do")
	@ResponseBody
	public String updateInvoic(HttpServletRequest request) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("companyName", request.getParameter("companyName"));
		paramMap.put("productTypeName", request.getParameter("productTypeName"));
		paramMap.put("invoiceId", request.getParameter("invoiceId"));

		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/ebuy/updInvoice.json", paramMap, LoginHelper.prepareReqHeader(request));

		//request.setAttribute("jsonResponse", jsonResponse);
		return jsonResponse.getStatus().equals("0000") ? "更新发票信息成功" : "更新发票信息失败";
	}
}
