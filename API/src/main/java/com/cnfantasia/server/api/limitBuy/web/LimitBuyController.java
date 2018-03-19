package com.cnfantasia.server.api.limitBuy.web;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.entity.UserIdType;
import com.cnfantasia.server.api.commonBusiness.service.ICommonDeviceService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.company.service.ICompanyService;
import com.cnfantasia.server.api.coupon.constant.CouponUseTypeConstant;
import com.cnfantasia.server.api.coupon.entity.UserCouponEntity;
import com.cnfantasia.server.api.couponArea.contant.UserCouponStatus;
import com.cnfantasia.server.api.ebuy.entity.EbuyExtandBuyParam;
import com.cnfantasia.server.api.ebuy.entity.ProductIdQtyEntity;
import com.cnfantasia.server.api.ebuy.service.IEbuyService;
import com.cnfantasia.server.api.limitBuy.contant.LimitBuyDict;
import com.cnfantasia.server.api.limitBuy.entity.LimitBuyInfo;
import com.cnfantasia.server.api.limitBuy.service.ILimitBuyService;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.payment.serviceUntran.IPayConfigService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.userCoupon.entity.CouponUseEndDateComparator;
import com.cnfantasia.server.api.userCoupon.service.IUserCouponService;
import com.cnfantasia.server.business.pub.RepeatReqValidation.annotation.RepeatReqValidate;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domainbase.coupon.entity.Coupon;
import com.cnfantasia.server.domainbase.ebuyDeliveryMethod.entity.EbuyDeliveryMethod;
import com.cnfantasia.server.domainbase.ebuyProduct.service.EbuyProductBaseService;
import com.cnfantasia.server.domainbase.ebuyProductIntroducePic.entity.EbuyProductIntroducePic;
import com.cnfantasia.server.domainbase.ebuyProductIntroducePic.service.IEbuyProductIntroducePicBaseService;
import com.cnfantasia.server.domainbase.ebuyProductParameters.entity.EbuyProductParameters;
import com.cnfantasia.server.domainbase.ebuyProductParameters.service.IEbuyProductParametersBaseService;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.service.IEbuySupplyMerchantBaseService;
import com.cnfantasia.server.domainbase.user.service.IUserBaseService;
import com.cnfantasia.server.domainbase.userCoupon.entity.UserCoupon;

/**
 * @ClassName: LimitBuyController
 * @Date: 2016-12-28 11:34
 * @Auther: kangduo
 * @Description: (限时抢购controller)
 */
@Controller
@RequestMapping(value = "limitBuy")
public class LimitBuyController extends BaseController{

    @Resource
    private ILimitBuyService limitBuyService;

    @Resource
    private ICommonRoomService commonRoomService;

    @Resource
    private ICompanyService companyService;

    @Resource
    private IPayConfigService payConfigService;

    @Resource
    private IUserCouponService userCouponService;

    @Resource
    private IEbuyProductIntroducePicBaseService ebuyProductIntroducePicBaseService;

    @Resource
    private IEbuyProductParametersBaseService ebuyProductParametersBaseService;

    @Resource
    private ISysParamManager sysParamManager;

    @Resource
    private IEbuySupplyMerchantBaseService ebuySupplyMerchantBaseService;

    @Resource
    private IEbuyService ebuyService;

    @Resource
    private IUserBaseService userBaseService;
    
    @RequestMapping(value = "/limitBuyList.html", method = RequestMethod.GET)
    public String jumpToLimitBuyList(HttpServletRequest request) {
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        BigInteger gbId = commonRoomService.getGroupBuildingIdByUserIdForEbuy(userId);
        List<LimitBuyInfo> limitBuyInfos = limitBuyService.getLimitBuyListByGbId(gbId, 1, LimitBuyDict.PositionType.IN_PUB_STORE, null);
        if (limitBuyInfos != null && limitBuyInfos.size() == 1) {
            BigInteger limitBuyId = limitBuyInfos.get(0).getLimitBuyId();
            return "forward:/limitBuy/limitBuyDetail.html?id=" + limitBuyId;

        }
        request.setAttribute("limitBuyInfoList", limitBuyInfos);
        return "limitBuy/limitBuyList";
    }

    @RequestMapping(value = "/limitBuyDetail.html", method = RequestMethod.GET)
    public ModelAndView jumpToLimitBuyDetail(HttpServletRequest request, BigInteger id) {
        ModelAndView mav = new ModelAndView("limitBuy/limitBuyDetail");

        LimitBuyInfo limitBuyInfo = limitBuyService.getLimitBuyInfo(id, HeaderManager.getWlAppType().intValue());
        List<EbuyProductIntroducePic> introducePicList = null;
        List<EbuyProductParameters> parametersList = null;
        if (limitBuyInfo != null) {
            EbuyProductIntroducePic productIntroducePic = new EbuyProductIntroducePic();
            productIntroducePic.settEbuyProductFId(limitBuyInfo.getProductId());
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("tEbuyProductFId", limitBuyInfo.getProductId());
            introducePicList = ebuyProductIntroducePicBaseService.getEbuyProductIntroducePicByCondition(param);
            parametersList = ebuyProductParametersBaseService.getEbuyProductParametersByCondition(param);
        }

        //客服电话
        String phone = companyService.getCompanyServiceInfo().getTel();
        mav.addObject("phone", phone);
        mav.addObject("limitBuyInfo", limitBuyInfo);
        mav.addObject("ebuyProductIntroducePicList", introducePicList);
        mav.addObject("productParametersList", parametersList);
        String basePath = sysParamManager.getSysParaValue(SysParamKey.PRODUCT_PIC_BASE_PATH);
        String imageServerUrl = sysParamManager.getImageServerUrl(SysParamKey.PRODUCT_PIC_BASE_PATH);
        mav.addObject("picserverUrl", imageServerUrl + basePath);
        
        //购物车数量
        Integer count = ebuyService.getBuyCarProductTotalCount(UserContext.getOperIdBigInteger(),LoginDict.UserRegistOrTmp.REGIST_USER,EbuyDict.PointType.EBUY_PRODUCT, EbuyDict.WlAppType.Jfq);
        mav.addObject("productCount", count);
        
        return mav;
    }

    @RequestMapping(value = "/limitBuyDetail.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getLimitBuyDetail(BigInteger id, Integer buyNum) {
        JsonResponse json = new JsonResponse();
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        LimitBuyInfo info = limitBuyService.getLimitBuyInfo(id, HeaderManager.getWlAppType().intValue());
        if (info != null) {
            Double payConfigHbPercent = payConfigService.getPayConfigHbPercent(EbuyDict.EbuyOrder_Type.Limit_Buy_Activity);

            //查询可用消耗券
            UserCoupon userCoupon = new UserCoupon();
            Coupon coupon = new Coupon();
            coupon.setLeastSpendUse(info.getLimitBuyPrice().multiply(new BigDecimal(buyNum == null ? 1 : buyNum)).intValue());
            coupon.setUseType(CouponUseTypeConstant.EBUY_PRODUCT);
            userCoupon.setCoupon(coupon);
            userCoupon.settUserFId(userId);
            userCoupon.setStatus(UserCouponStatus.VALID);
            Map<String, Object> param = MapConverter.toMap(userCoupon);
            List<Object> supplyMerchantIds = new ArrayList<Object>();
            supplyMerchantIds.add(info.getMerchantId());
            param.put("supplyMerchantIds", supplyMerchantIds);
            List<UserCouponEntity> coupons = userCouponService.getUserCouponList(param);

            {
                // 再加上 定向商品 的券 added by wenfq 2017-0-12
                List<ProductIdQtyEntity> productIdQtyList = new ArrayList();
                ProductIdQtyEntity productIdQtyEntity = new ProductIdQtyEntity();
                productIdQtyEntity.setProductId(info.getShelfId());
                productIdQtyEntity.setProductQty(Long.valueOf(buyNum));
                productIdQtyList.add(productIdQtyEntity);

                if (!DataUtil.isEmpty(productIdQtyList)) {
                    List<UserCouponEntity> couponsEbuyProduct = userCouponService.getUserCouponList4EbuyProduct(productIdQtyList, userId);
                    if (!couponsEbuyProduct.isEmpty()) {
                        coupons.addAll(couponsEbuyProduct);
                    }
                }
            }

            json.putData("ext_isContainCoupon", !(coupons == null || coupons.isEmpty()));
            json.putData("ext_couponCombiInfo", coupons);
            json.putData("payConfigHbPercent", payConfigHbPercent);

            EbuySupplyMerchant esm = ebuySupplyMerchantBaseService.getEbuySupplyMerchantBySeqId(info.getMerchantId());
            json.putData("merchantType", esm.getType());//供应商类型：2是楼下店支持自提
            json.putData("merchantAddress", esm.getAddress());//供应商地址

            EbuyDeliveryMethod ebuyDeliveryMethod = limitBuyService.getEbuyDeliveryMethod(info.getMerchantId(), PriceUtil.multiply100(buyNum * info.getLimitBuyPrice().doubleValue()));
            long totalFee = ebuyDeliveryMethod.getFee();
            json.putData("signalMer_Fee", PriceUtil.div100(totalFee));//运费
            json.putData("signalMer_Deliv_Info", ebuyDeliveryMethod.getDesc()); //运费描述:单位元
        } else {
            json.putData("ext_isContainCoupon", null);
            json.putData("ext_couponCombiInfo", null);
            json.putData("payConfigHbPercent", null);
        }

        json.putData("limitBuyInfo", info);
        return json;
    }
    
    @Resource
    EbuyProductBaseService ebuyProductBaseService;
    
    /**
     * 抢前的数量检查，看是否超出配置每个可购的数量
     * @author wenfq 2017-03-15
     * @param id
     * @param buyNum
     * @return
     */
    @RequestMapping(value = "/checkBuyCountBeforelimitBuy.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse checkBuyCountBeforelimitBuy(BigInteger id, Integer buyNum) {
        JsonResponse json = new JsonResponse();
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("limitBuyId", id);
        LimitBuyInfo limitBuyInfo = limitBuyService.getLimitBuyInfo(id, HeaderManager.getWlAppType().intValue());
        if(limitBuyInfo.getMaxCanBuy() != -1){// -1表示每人不限购数量
	        int buyCount = limitBuyService.qryBuyCountWithActivtyIdForUser(param);
			if ((buyNum + buyCount) > limitBuyInfo.getMaxCanBuy()) {
				json.setStatus("0001");
				json.setMessage("抢购失败，本次活动只允许每人最多购买" + limitBuyInfo.getMaxCanBuy() +"份");
				return json;
			}
        }
        
        if(buyNum > limitBuyInfo.getLeftCount()){
        	json.setStatus("0001");
			json.setMessage("抢购失败，购买数量不能大于促销库存" + limitBuyInfo.getLeftCount() +"份");
			return json;
        }
        
         
        int epLeftCount = ebuyProductBaseService.getEbuyProductBySeqId(limitBuyInfo.getProductId()).getLeftCount().intValue();
        if(buyNum > epLeftCount){
        	json.setStatus("0001");
			json.setMessage("抢购失败，商品库存不足，只剩" + epLeftCount +"份");
        }
        
        return json;
    }

    @RequestMapping(value = "/confirmPay.json", method = RequestMethod.POST)
    @ResponseBody
    @RepeatReqValidate
    public JsonResponse confirmPay(HttpServletRequest request) {
        BigInteger limitBuyId = ParamUtils.getBigInteger(request, "limitBuyId", null);
        Integer buyNum = ParamUtils.getInteger(request, "buyNum", 1);
        buyNum = buyNum <= 0 ? 1 : buyNum;
        Double hbDouble = ParamUtils.getDouble(request, "hbAmount", 0d);
        Long hbAmount = PriceUtil.multiply100(hbDouble);
        BigInteger couponId = ParamUtils.getBigInteger(request, "couponId", null);
        String address = ParamUtils.getString(request, "address");
        String linkName = ParamUtils.getString(request, "linkName");
        String linkPhone = ParamUtils.getString(request, "linkPhone");
        BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
        if (gbId == null || new BigInteger("-1").equals(gbId)) {
            BigInteger roomId = userBaseService.getUserBySeqId(UserContext.getOperIdMustExistBigInteger()).getDefaultRoomId();
            Map<String, Object> roomAddressIdByRoom = commonRoomService.getRoomAddressIdByRoom(roomId);
            gbId = new BigInteger(roomAddressIdByRoom.get("gbId").toString());
        }
        int isSelfGet = ParamUtils.getInt(request, "isSelfGet", 0);//是否自提：1是自提，0不是
        Map<String, Object> resMap = limitBuyService.confirmPay(limitBuyId, buyNum, hbAmount, couponId, address, linkName, linkPhone, isSelfGet, gbId);
        JsonResponse json = new JsonResponse();
        json.putDataAll(resMap);
        return json;
    }
}
