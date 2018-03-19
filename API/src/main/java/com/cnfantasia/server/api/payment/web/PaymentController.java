/**   
 * Filename:    PaymentController.java   
 * @version:    1.0  
 * Create at:   2015年10月16日 上午11:44:10   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2015年10月16日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.payment.web;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.payment.serviceUntran.AliDifferenceMerchantPayService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.constant.DictConstants;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.commbusi.plotproperty.service.IPlotpropertyCfgService;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domainbase.propertyCompanyThirdPayCfg.entity.PropertyCompanyThirdPayCfg;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Filename: PaymentController.java
 * 
 * @version: 1.0.0 Create at: 2015年10月16日 上午11:44:10 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2015年10月16日 shiyl 1.0 1.0 Version
 */
@Controller
@RequestMapping("/payment")
public class PaymentController extends BaseController {
	@Resource
	private ISysParamManager sysParamManager;
	@Resource
	private IPlotpropertyCfgService plotpropertyCfgService;
	@Resource
	private AliDifferenceMerchantPayService aliDifferenceMerchantPayService;

	/**
	 * 查询支持的支付方式列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/payList.json")
	@ResponseBody
	public JsonResponse payList(HttpServletRequest request) {
		//（1、物业费；2、停车费；3、超市；4、维修；5、代扣卡；6、门禁；7、生活缴费）
		String type = ParamUtils.getString(request, "type", "3");
		JsonResponse jsonResponse = new JsonResponse();
		// 1.搜集参数
		//-- 切换门牌有问题 弃用 BigInteger gbId = UserContext.getCurrLoginNo().getUserEntity().getDefaultRoomEntity().getRealRoomEntity().getBuildingEntity().gettGroupBuildingFId();
		BigInteger gbId = plotpropertyCfgService.getGroupBuildingIdByUserId(UserContext.getOperIdMustExistBigInteger());
		PropertyCompanyThirdPayCfg propertyCompanyThirdPayCfg = aliDifferenceMerchantPayService.getPropertyCompanyThirdPayCfg(gbId);

		// 2.交互
		List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
		//付款到物业公司：只显示支付宝支付
		//propertyCompanyThirdPayCfg第一条f_id=1的为默认数据（自己平台）:
		//1、物业费；2、停车费；4、维修；5、代扣卡；6、门禁 支持到账到物业公司
		if (propertyCompanyThirdPayCfg != null && (BigInteger.ONE.compareTo(propertyCompanyThirdPayCfg.getId()) != 0)
				&& (StringUtils.isNotBlank(type) && ("1".equals(type) || "2".equals(type)))) {
			Map<String, Object> payType = new HashMap<String, Object>();
			payType.put("type", "2");
			payType.put("name", "支付宝支付");
			resList.add(payType);
		} else {
			{
				Map<String, Object> payType = new HashMap<String, Object>();
				payType.put("type", "1");
				payType.put("name", "微信支付");
				resList.add(payType);
			}
			{
				Map<String, Object> payType = new HashMap<String, Object>();
				payType.put("type", "2");
				payType.put("name", "支付宝支付");
				resList.add(payType);
			}
			{
				// 除了超市，其它的控制开启银行卡支付。
				if(StringUtils.isNotBlank(type) && !"3".equals(type)){
					Map<String, Object> payType = getSQPayWay();
					if (payType != null) {
						resList.add(payType);
					}
				}
			}
		}

		// 3.结果处理
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}

	/**
	 * 双乾支付方式信息
	 * 
	 * @return
	 */
	private Map<String, Object> getSQPayWay() {
		Map<String, Object> payType = null;
		String sqPayName = sysParamManager.getSysParaValue(SysParamKey.SQ_PAY_NAME_350);// 9.5折
		if (StringUtils.isEmpty(sqPayName)) {// 如果物业缴费不优惠，则用普通的
			sqPayName = sysParamManager.getSysParaValue(SysParamKey.SQ_PAY_NAME_345);
		}
		if (!StringUtils.isEmpty(sqPayName)) {
			payType = new HashMap<String, Object>();
			payType.put("type", "3");
			payType.put("name", sqPayName);
		}
		return payType;
	}

	private boolean paywayByIOS(String subChannelId, String type) {
		return DictConstants.Channel_Sub.IOS.toString().equals(subChannelId)
				&& (PayType.Wu_Ye.equals(type) || PayType.Ting_Che.equals(type) || PayType.Wei_Xiu.equals(type));
	}

	private boolean paywayByAndroid(String subChannelId, String type) {// android未传type，特殊处理，只在物业缴费时显示
		return DictConstants.Channel_Sub.ANDROID.toString().equals(subChannelId)
				&& PayType.Wu_Ye.equals(type);
	}

	/**
	 * 缴费类型
	 * 
	 * @author liyulin
	 * @version 1.0 2017年2月22日 下午7:06:42
	 */
	public static final class PayType {
		/** 物业费 */
		public static final String Wu_Ye = "1";
		/** 停车费 */
		public static final String Ting_Che = "2";
		/** 超市 */
		public static final String Chao_Shi = "3";
		/** 维修 */
		public static final String Wei_Xiu = "4";
	}
}
