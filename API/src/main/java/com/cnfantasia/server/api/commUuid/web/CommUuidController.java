package com.cnfantasia.server.api.commUuid.web;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.json.JsonResponse;

@RequestMapping("/commUuid")
@Controller
public class CommUuidController extends BaseController{
	/*private ICommUuidService commUuidService;
	
	public void setCommUuidService(ICommUuidService commUuidService) {
		this.commUuidService = commUuidService;
	} */
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	@RequestMapping("/getNextuuid.json")
	@ResponseBody
	public JsonResponse getNextuuid(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String tableName = request.getParameter("tableName");
		//2.交互
		BigInteger nextId = uuidManager.getNextUuidBigInteger(tableName);
		//3.结果处理
		jsonResponse.putData("nextId", nextId);
		jsonResponse.putData("tableName", tableName);
		return jsonResponse;
	}
	
}
