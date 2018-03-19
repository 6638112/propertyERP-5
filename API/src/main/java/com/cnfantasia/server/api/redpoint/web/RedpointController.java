/**   
* Filename:    RedpointController.java   
* @version:    1.0  
* Create at:   2015年3月24日 上午8:11:02   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月24日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redpoint.web;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commonBusiness.entity.UserIdType;
import com.cnfantasia.server.api.commonBusiness.service.ICommonDeviceService;
import com.cnfantasia.server.api.couponArea.contant.UserCouponStatus;
import com.cnfantasia.server.api.plotproperty.service.IPlotpropertyService;
import com.cnfantasia.server.api.property.service.IPropertyService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.redpoint.constant.RedpointConstant;
import com.cnfantasia.server.api.redpoint.entity.RedpointContentEntity;
import com.cnfantasia.server.api.redpoint.service.IRedpointService;
import com.cnfantasia.server.api.userCoupon.service.IUserCouponService;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domainbase.userCoupon.entity.UserCoupon;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Filename:    RedpointController.java
 * @version:    1.0.0
 * Create at:   2015年3月24日 上午8:11:02
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月24日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/redpoint")
public class RedpointController extends BaseController{
	
	private IRedpointService redpointService;
	public void setRedpointService(IRedpointService redpointService) {
		this.redpointService = redpointService;
	}
	
	private ICommonDeviceService commonDeviceService;
	public void setCommonDeviceService(ICommonDeviceService commonDeviceService) {
		this.commonDeviceService = commonDeviceService;
	}

	@Resource
	private IPlotpropertyService plotpropertyService;
	@Resource
	private IPropertyService propertyService;

	@Resource
	private IUserCouponService userCouponService;

	/**
	 * 查询红点信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryRedpointInfo.json")
	@ResponseBody
	public JsonResponse qryRedpointInfo(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String modelCode = request.getParameter("modelCode");
		UserIdType userIdType = commonDeviceService.getUserIdType();
		BigInteger userId = userIdType.getUserId();
		Integer userType = userIdType.getUserType();
		//2.交互
		RedpointContentEntity tmpRedpointContentEntity = redpointService.qryRedpointInfo(userId,userType,modelCode);
		//3.结果处理
		Map<String,Object> resMap = redpointContentEntity2Map(tmpRedpointContentEntity);
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
	/**
	 * 查询红点列表信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryRedpointInfoMulti.json")
	@ResponseBody
	public JsonResponse qryRedpointInfoMulti(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		List<String> modelCodeList = JSON.parseArray(request.getParameter("modelCodeList"), String.class);
		UserIdType userIdType = commonDeviceService.getUserIdType();
		BigInteger userId = userIdType.getUserId();
		Integer userType = userIdType.getUserType();
		//2.交互
		List<RedpointContentEntity> tmpRedpointContentEntityList = redpointService.qryRedpointInfoMulti(userId,userType,modelCodeList);
		//3.结果处理
		List<Map<String,Object>> resList = redpointContentEntity2Map(tmpRedpointContentEntityList);
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}
	
	/**
	 * 查询首页红点列表信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryRedpointInfoMulti4Home.json")
	@ResponseBody
	public JsonResponse qryRedpointInfoMulti4Home(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		List<String> modelCodeList = JSON.parseArray(request.getParameter("modelCodeList"), String.class);
		UserIdType userIdType = commonDeviceService.getUserIdType();
		BigInteger userId = userIdType.getUserId();
		BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
//		BigInteger userId = new BigInteger("50003");
//		Integer userType = LoginDict.UserRegistOrTmp.REGIST_USER;
		Integer userType = userIdType.getUserType();
		//2.交互
		
		redpointService.addPropertyBillRedPoint(userId);
		
		List<RedpointContentEntity> tmpRedpointContentEntityList = redpointService.qryRedpointInfoMulti(userId,userType,modelCodeList);
		boolean hasCoupon = false;
		for (RedpointContentEntity redpointContentEntity : tmpRedpointContentEntityList) {//处理红点下的文字描述
			if (redpointContentEntity.getModelCode().equals(RedpointConstant.RedpointContent_ModelCode.USER_HAS_NEW_COUPON)) {
				redpointContentEntity.setDataStr(getCouponStr(userId));
				hasCoupon = true;
			} else if (redpointContentEntity.getModelCode().equals(RedpointConstant.RedpointContent_ModelCode.IS_HAS_PROPERTY_BILL)) {
                String sessionId = request.getSession().getId();
                if(!DataUtil.isEmpty(sessionId)) {
                    redpointContentEntity.setDataStr(getWuYeFeeStr(userId, sessionId, gbId));
                }
			}
		}

		//消费券的红点一直在
		if (!hasCoupon) {
			RedpointContentEntity redpoint = new RedpointContentEntity();
			String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			redpoint.setModelCode(RedpointConstant.RedpointContent_ModelCode.USER_HAS_NEW_COUPON);
			redpoint.setClickStatus(1);
			redpoint.setLastClickTime(now);
			redpoint.setLastModifyTime(now);
			redpoint.setDataStr(getCouponStr(userId));
			tmpRedpointContentEntityList.add(redpoint);
		}
		//3.结果处理
		List<Map<String,Object>> resList = redpointContentEntity2Map(tmpRedpointContentEntityList);
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}

	private String getWuYeFeeStr(BigInteger userId, String sessionId, BigInteger gbId) {
		//查询当期需缴费用总和
		Double totalAmt = propertyService.getTotalAmtNow(userId, sessionId, gbId);
		DecimalFormat df = new DecimalFormat("0.00");
		return totalAmt == 0 ? "" : "本期 ¥" + df.format(totalAmt);
	}

	private String getCouponStr(BigInteger userId) {
		UserCoupon userCoupon = new UserCoupon();
		userCoupon.settUserFId(userId);
		userCoupon.setStatus(UserCouponStatus.VALID);
		int count = userCouponService.getUserCouponCount(MapConverter.toMap(userCoupon));
		return count > 0 ? count + "张" : "";
	}
	
	/**
	 * 点击红点
	 * @param request
	 * @return
	 */
	@RequestMapping("/clickRedpointInfo.json")
	@ResponseBody
	public JsonResponse clickRedpointInfo(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String modelCode = request.getParameter("modelCode");
		UserIdType userIdType = commonDeviceService.getUserIdType();
		BigInteger userId = userIdType.getUserId();
		Integer userType = userIdType.getUserType();
		//2.交互
		RedpointContentEntity tmpRedpointContentEntity = redpointService.clickRedpointInfo(userId,userType,modelCode);
		//3.结果处理
		Map<String,Object> resMap = redpointContentEntity2Map(tmpRedpointContentEntity); 
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
	private List<Map<String,Object>> redpointContentEntity2Map(List<RedpointContentEntity> redpointContentEntityList){
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		if(redpointContentEntityList!=null&&redpointContentEntityList.size()>0){
			for(RedpointContentEntity tmpEntity:redpointContentEntityList){
				Map<String,Object> tmpMap = redpointContentEntity2Map(tmpEntity);
				resList.add(tmpMap);
			}
		}
		return resList;
	}
	
	private Map<String,Object> redpointContentEntity2Map(RedpointContentEntity redpointContentEntity){
		Map<String,Object> resMap = new HashMap<String, Object>();
		if(redpointContentEntity!=null){
			resMap.put("modelCode", redpointContentEntity.getModelCode());
			resMap.put("clickStatus", redpointContentEntity.fetchClickStatus());
//			resMap.put("msgCount", 10);//待阅的消息数量
			resMap.put("lastClickTime", redpointContentEntity.getLastClickTime());
			resMap.put("lastModifyTime", redpointContentEntity.getLastModifyTime());
			resMap.put("dataStr", redpointContentEntity.getDataStr());
		}
		return resMap;
	}
	
	
}
