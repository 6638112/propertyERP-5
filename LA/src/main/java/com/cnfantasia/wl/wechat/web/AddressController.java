package com.cnfantasia.wl.wechat.web;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.pub.entity.Regist3rdResponse;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domainbase.addressProvince.service.IAddressProvinceBaseService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.wl.wechat.constant.AddressConstant;
import com.cnfantasia.wl.wechat.model.SnsUserinfo;

/**
 * 收货地址
 * 
 * @author wenfq 2015-01-23
 * 
 */
@Controller
@RequestMapping("/address")
public class AddressController extends BaseController {
	private static final String FROM_WHERE_PARAM = "fromWhereParam";
	private static final String FROM_WHERE = "fromWhere";
	@Resource
	private IHttpClient simpleHttpClient;

	/**
	 * 查询收货地址
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryDeliveryAddressList.do")
	public ModelAndView qryDeliveryAddressList(HttpServletRequest request) {
		//		if (LoginHelper.getSessionKey(request) == null)//未登录时，要先登录
		//			LoginHelper.login(request, simpleHttpClient);

		request.getSession().setAttribute(FROM_WHERE, request.getParameter(FROM_WHERE));

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", 1);
		params.put("pageNum", 20);

		JsonResponse addresResponse = simpleHttpClient.submitSimple("/ebuy/qryDeliveryAddressList.json", params, LoginHelper.prepareReqHeader(request));
		request.setAttribute("addresResponse", addresResponse);

		ModelAndView view = new ModelAndView();
		//view.setViewName("/ebuy/addressInfoList");
		view.setViewName("/ebuy/addressList");
		return view;
	}

	/**
	 * 添加收货地址
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addAddressInfo.do")
	public ModelAndView addAddressInfo(HttpServletRequest request) {
		if (request.getParameter(FROM_WHERE) != null)
			request.getSession().setAttribute(FROM_WHERE, request.getParameter(FROM_WHERE));

		IAddressProvinceBaseService addressProvinceBaseService = (IAddressProvinceBaseService) CnfantasiaCommbusiUtil.getBeanManager("addressProvinceBaseService");
		request.setAttribute("pcbList", addressProvinceBaseService.getAddressProvinceByCondition(null));
		
		Regist3rdResponse regist3rdResponse = LoginHelper.getRegist3rdResponseFromSession(request);
		if (regist3rdResponse.getDefaultRoomInfo() != null) {//有门牌
			request.setAttribute("title", "新增收货地址");
			request.setAttribute("action", "../address/addDeliveryAddress.do");
//			return new ModelAndView("/ebuy/addAddress_hasRoom");
		}
		return new ModelAndView("/ebuy/addAddress_new");
	}

	/**
	 * 保存新增的收货地址
	 * 
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@RequestMapping("/addDeliveryAddress.do")
	public ModelAndView addDeliveryAddress(HttpServletRequest request, HttpServletResponse response) throws ClientProtocolException, IOException {
		// XXX：有空优化
		String groupBuilding = request.getParameter("groupBuilding");
		String city = request.getParameter("_city");
		String block = request.getParameter("block");
		String building = request.getParameter("_building");
		String unit = request.getParameter("_unit");
		String room = request.getParameter("_room");
		String userName = request.getParameter("userName").trim();
		String userPhone = request.getParameter("userPhone").trim();
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		String createType = request.getParameter("createType");
		String targetId = "";
		if(createType==null || createType.equals("")){
			// 手动输入：从addAddress_hasRoom.jsp页面提交
			params.clear();
			params.put("targetType", EbuyDict.DELIVERY_ADDRESS_TYPE.HANDLE_ADDRESS); 
			params.put("userName", userName);
			params.put("userPhone", userPhone);
			params.put("isDefault", 1);
			params.put("groupBuildingId", request.getParameter("groupBuildingId"));
			params.put("gbName", request.getParameter("gbName"));
			params.put("blockId", request.getParameter("blockId"));
			params.put("noWriteRoom", "yes");
			String addressDetail = request.getParameter("addressDetail");
			if(addressDetail!=null){
				params.put("addressDetail", addressDetail.trim());
			}
			
			String addressArea = request.getParameter("addressArea");
			if(addressArea!=null){
				params.put("addressArea", addressArea.trim());
			}
			simpleHttpClient.submitSimple("/ebuy/addDeliveryAddress.json", params, LoginHelper.prepareReqHeader(request));
		} else {
			// 普通输入：从addAddress_noRoom.jsp页面提交
			if(AddressConstant.CREATE_BY_ROOM.equals(createType)){
				params.clear();
				params.put("realRoomId", room);
				simpleHttpClient.submitSimple("/room/addVirtualRoomOnly.json", params, LoginHelper.prepareReqHeader(request));
			} else if(AddressConstant.CREATE_BY_HAND_ROOM.equals(createType)){
				params.clear();
				params.put("groupBuildingId", groupBuilding);
				params.put("buildingName", building);
				params.put("unitName", unit);
				params.put("roomNum", room);
				simpleHttpClient.submitSimple("/room/addBuildingAndRoom.json", CommonController.getParameterMap(request), LoginHelper.prepareReqHeader(request));
			} else if(AddressConstant.CREATE_BY_WRITE.equals(createType)){
				// 判断小区是否已存在
				params.clear();
				params.put("gbName", groupBuilding);
				params.put("city", city);
				params.put("block", block);
				JsonResponse jsonResponse = simpleHttpClient.submitSimple("/groupBuilding/qryGroupBuildingByAddress.json", params, LoginHelper.prepareReqHeader(request));
				
				BigInteger gbId = null;
				String signStatus = null;
				if(jsonResponse.getDataValue() instanceof List){
					List<Map<String, Object>> list = (List<Map<String, Object>>)jsonResponse.getDataValue();
					if(list.size()>0){
						gbId = new BigInteger(list.get(0).get("gbId").toString());
						signStatus = list.get(0).get("signStatus").toString();
					}
				}
				if(gbId==null){
					// 创建小区
					params.clear();
					params.put("addressBlockId", block);
					params.put("groupBuildingName", groupBuilding);
					params.put("buildingName", building);
					params.put("unitName", unit);
					params.put("roomNum", room);
					simpleHttpClient.submitSimple("/room/addGroupBuildingAndRoom.json", params, LoginHelper.prepareReqHeader(request));
				} else {
//					if("0".equals(signStatus)){
						 // 未签约小区 
						params.clear();
						params.put("groupBuildingId", gbId);
						params.put("buildingName", building);
						params.put("unitName", unit);
						params.put("roomNum", room);
						simpleHttpClient.submitSimple("/room/addBuildingAndRoom.json", params, LoginHelper.prepareReqHeader(request));
//					} else if("1".equals(signStatus)){
						//已经签约小区
						// TODO:
//						params.clear();
//						params.put("addressBlockId", block);
//						simpleHttpClient.submitSimple("/room/addVirtualRoomOnly.json", params, LoginHelper.prepareReqHeader(request));
						// 根据
						
						
						/*params.clear();
						params.put("realRoomId", );
						simpleHttpClient.submitSimple("/room/addVirtualRoomOnly.json", params, LoginHelper.prepareReqHeader(request));*/
//					}

				}
			}
			SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
			//因为增加了房间，所以需要重新登录以更新Session中的Regist3rdResponse信息
			request.getSession().setAttribute("regist3rdResponse",null);//清空后，才能强制登录API，否则取的是缓存里的数据，默认房间还是刚自动分配的房间
			LoginHelper.loginAPI(simpleHttpClient, request, user);
			
			Regist3rdResponse regist3rdResponse = LoginHelper.getRegist3rdResponseFromSession(request);
			targetId = regist3rdResponse.getDefaultRoomId();
			
			params.clear();
			params.put("targetType", EbuyDict.DELIVERY_ADDRESS_TYPE.ROOM); //普通输入
			params.put("userName", userName);
			params.put("userPhone", userPhone);
			params.put("isDefault", 1);
			params.put("targetId", targetId);
			JsonResponse jsonResponse = simpleHttpClient.submitSimple("/ebuy/addDeliveryAddress.json", params, LoginHelper.prepareReqHeader(request));
		}
		
		//如果是从确认订单那里修改收货地址，保存后跳回  确认订单 界面，否则跳回收货地址列表界面  wenfq 2015-04-09
		if ("checkPay".equals(request.getSession().getAttribute(FROM_WHERE))) {
			// 跳到“/cart/checkProdctInfo.do”不能用“forward”，否则只会会失败，因为“forward”之后url没变，支付授权目录不一致
			return new ModelAndView("redirect:/cart/checkProdctInfo.do");
		}else if ("buyRightNow".equals(request.getSession().getAttribute(FROM_WHERE))) {
			return new ModelAndView("redirect:/cart/buyRightNow.do?" + request.getSession().getAttribute(FROM_WHERE_PARAM));
		} else if ("limitbuy".equals(request.getSession().getAttribute(FROM_WHERE))) {
			return new ModelAndView("redirect:/cart/limitBuyDetail.do?" + request.getSession().getAttribute(FROM_WHERE_PARAM));
		} else {
			return new ModelAndView("redirect:/address/qryDeliveryAddressList.do");
		}
	}

	/**
	 * 编辑收货地址
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/updDeliveryAddress.do")
	public ModelAndView updDeliveryAddress(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", request.getParameter("id"));
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/ebuy/qryDeliveryAddressDetailById.json", params, LoginHelper.prepareReqHeader(request));
		request.setAttribute("addresInfo", JSON.parseObject(jsonResponse.getDataValue().toString()));
		
		IAddressProvinceBaseService addressProvinceBaseService = (IAddressProvinceBaseService) CnfantasiaCommbusiUtil.getBeanManager("addressProvinceBaseService");
		request.setAttribute("pcbList", addressProvinceBaseService.getAddressProvinceByCondition(null));
		request.setAttribute("title", "修改收货地址");
		request.setAttribute("action", "../address/updDeliveryAddressSave.do");
		
		return new ModelAndView("/ebuy/editAddress");
	}

	/**
	 * 保存编辑的收货地址
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/updDeliveryAddressSave.do")
	public ModelAndView updDeliveryAddressSave(HttpServletRequest request) {
		// 创建参数队列    
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", request.getParameter("id"));
		params.put("targetType", 2); //普通输入
		params.put("userName", request.getParameter("userName").trim());
		params.put("userPhone", request.getParameter("userPhone").trim());
		params.put("isDefault", 1);
		params.put("groupBuildingId", request.getParameter("groupBuildingId"));
		params.put("gbName", request.getParameter("gbName"));
		params.put("blockId", request.getParameter("blockId"));
		String addressDetail = request.getParameter("addressDetail");
		if(addressDetail!=null){
			params.put("addressDetail", addressDetail.trim());
		}
		
		String addressArea = request.getParameter("addressArea");
		if(addressArea!=null){
			params.put("addressArea", addressArea.trim());
		}
		params.put("noWriteRoom", "yes");

		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/ebuy/updDeliveryAddress.json", params, LoginHelper.prepareReqHeader(request));

		//如果是从确认订单那里修改收货地址，保存后跳回到 确认订单 界面，否则跳回收货地址列表界面  wenfq 2015-04-09
		if ("checkPay".equals(request.getSession().getAttribute(FROM_WHERE))) {
			// 跳到“/cart/checkProdctInfo.do”不能用“forward”，否则只会会失败，因为“forward”之后url没变，支付授权目录不一致
			return new ModelAndView("redirect:/cart/checkProdctInfo.do");
		} else if ("buyRightNow".equals(request.getSession().getAttribute(FROM_WHERE))) {
			return new ModelAndView("redirect:/cart/buyRightNow.do?" + request.getSession().getAttribute(FROM_WHERE_PARAM));
		} else if ("limitbuy".equals(request.getSession().getAttribute(FROM_WHERE))) {
			return new ModelAndView("redirect:/cart/limitBuyDetail.do?" + request.getSession().getAttribute(FROM_WHERE_PARAM));
		} else {
			return new ModelAndView("redirect:/address/qryDeliveryAddressList.do");
		}
	}

	/**
	 * 设置默认收货地址
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/setDefaultDeliveryAddress.do")
	@ResponseBody
	public JsonResponse setDefaultDeliveryAddress(HttpServletRequest request) {
		//		if (LoginHelper.getSessionKey(request) == null)//未登录时，要先登录
		//			LoginHelper.login(request, simpleHttpClient);

		// 创建参数队列    
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", request.getParameter("id"));

		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/ebuy/setDefaultDeliveryAddress.json", params, LoginHelper.prepareReqHeader(request));

		return jsonResponse;
	}
	
}
