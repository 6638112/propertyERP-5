package com.cnfantasia.server.api.flashDealActivity.web;

import java.math.BigInteger;
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

import com.cnfantasia.server.api.commSysPara.entity.CompanyInfoConfig;
import com.cnfantasia.server.api.commSysPara.parser.CompanyInfoParamParser;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.flashDealActivity.entity.FlashDealActivityDetailEntity;
import com.cnfantasia.server.api.flashDealActivity.entity.FlashDealActivityDetailNewEntity;
import com.cnfantasia.server.api.flashDealActivity.service.IFlashDealActivityService;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.payment.serviceUntran.IPayConfigService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.RepeatReqValidation.annotation.RepeatReqValidate;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.utils.NumberUtils;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.domainbase.ebuyProduct.service.IEbuyProductBaseService;
import com.cnfantasia.server.domainbase.user.entity.User;
import com.cnfantasia.server.domainbase.user.service.IUserBaseService;

/**
 * @ClassName: FlashDealActivityController
 * @Date: 2016-10-31 9:17
 * @Auther: kangduo
 * @Description:(秒杀活动控制层)
 */
@Controller
@RequestMapping(value = "/flashDealActivity")
public class FlashDealActivityController extends BaseController {

    @Resource
    private ICommonRoomService commonRoomService;

    @Resource
    private IFlashDealActivityService flashDealActivityService;

    @Resource
    private IPayConfigService payConfigService;

    @Resource
    private IUserBaseService userBaseService;

    @Resource
    private IEbuyProductBaseService ebuyProductBaseService;

    @RequestMapping(value = "/activityList.json")
    @ResponseBody
    public JsonResponse jumpToActivityList(HttpServletRequest request) {
        JsonResponse json = new JsonResponse();
        //根据用户ID查小区有的活动
        BigInteger supplyMerchantId = ParamUtils.getBigInteger(request, "supplyMerchantId", null);
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        List<FlashDealActivityDetailNewEntity> activityList = flashDealActivityService.getActivityList(userId, supplyMerchantId, null, null);
        json.putData("activityList", activityList);
        json.putData("supplyMerchantId", supplyMerchantId);
        json.putData("userId", userId);
        return json;
    }

    @RequestMapping(value = "/myRecord.json")
    @ResponseBody
    public JsonResponse myRecord() {
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        JsonResponse json = new JsonResponse();
        List<FlashDealActivityDetailEntity> myRecords = flashDealActivityService.getMyRecords(paramMap);
        json.putData("records", myRecords);
        json.putData("userId", paramMap.get("userId"));
        return json;
    }

    /**
     * 我的抢购记录页面 + 数据
     * @return
     */
    @RequestMapping(value = "/myRecord.html", method = RequestMethod.GET)
    public ModelAndView jumpToMyRecord(HttpServletRequest request) {
		BigInteger userId = UserContext.getOperIdBigInteger();
		if (userId == null) {
			userId = ParamUtils.getBigInteger(request, "userId", null);
		}
         
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);

        List<FlashDealActivityDetailEntity> myRecords = flashDealActivityService.getMyRecords(paramMap);
        ModelAndView mav = new ModelAndView("/flashDealActivity/myRecord");
        mav.addObject("records", myRecords);
        mav.addObject("userId", paramMap.get("userId"));
        return mav;
    }

    /**
     * 进入活动列表页面
     * @return
     */
    @RequestMapping(value = "/activityList.html", method = RequestMethod.GET)
    public ModelAndView jumpToActivityList() {
        ModelAndView mav = new ModelAndView("/flashDealActivity/activityList");
        //根据用户ID查小区有的活动
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
//        BigInteger userId = new BigInteger("5260130");
        BigInteger gbId = commonRoomService.getGroupBuildingIdByUserIdForEbuy(userId);
        List<FlashDealActivityDetailNewEntity> activityList = flashDealActivityService.getActivityList(userId, null,gbId, null);
        mav.addObject("activityList", activityList);
        mav.addObject("userId", userId);
        return mav;
    }

    /**
     * 活动详情页
     * @param activityId
     * @return
     */
    @RequestMapping(value = "/activityDetail.html", method = RequestMethod.GET)
    public ModelAndView jumpToActivityDetail(BigInteger userId ,BigInteger activityId) {
        ModelAndView mav = new ModelAndView("/flashDealActivity/activityDetail");
        if (DataUtil.isEmpty(activityId)) {
            mav.addObject("detail", null);
        } else {
            userId = (UserContext.getOperId() == null && userId != null) ? userId : UserContext.getOperIdMustExistBigInteger();
            FlashDealActivityDetailEntity activityEntityDetail = flashDealActivityService.
                    getActivityEntityDetail(activityId, userId);
            mav.addObject("detail", activityEntityDetail);

            CompanyInfoParamParser companyInfoParamParser = (CompanyInfoParamParser) CnfantasiaCommbusiUtil.getBeanManager("companyInfoParamParser");
            CompanyInfoConfig companyInfoConfig = companyInfoParamParser.parseParamValue();
            mav.addObject("servePhone", companyInfoConfig.getTel());

            if(activityEntityDetail == null) {//处理特殊情况下 商品下架，前端提示
                mav.setViewName("/flashDealActivity/activityList");
                mav.addObject("message","幸运购已结束请联系客服!");
                BigInteger gbId = commonRoomService.getGroupBuildingIdByUserIdForEbuy(userId);
                List<FlashDealActivityDetailNewEntity> activityList = flashDealActivityService.getActivityList(userId, null, gbId, null);
                mav.addObject("activityList", activityList);
                mav.addObject("userId", userId);
            }
        }
        return mav;
    }

    /**
     * 活动详情页
     * @param activityId
     * @return
     */
    @RequestMapping(value = "/activityDetail.json")
    @ResponseBody
    public JsonResponse qryActivityDetail(BigInteger activityId) {
        JsonResponse json = new JsonResponse();
        if (DataUtil.isEmpty(activityId)) {
            json.putData("detail", null);
        } else {
            BigInteger userId = UserContext.getOperIdMustExistBigInteger();
            User user = userBaseService.getUserBySeqId(userId);
            FlashDealActivityDetailEntity activityEntityDetail = flashDealActivityService.
                    getActivityEntityDetail(activityId, userId);
            json.putData("detail", activityEntityDetail);
            if(activityEntityDetail != null) {
                activityEntityDetail.setJFBPercent(payConfigService.getPayConfigHbPercent(EbuyDict.EbuyOrder_Type.Flash_Deal_Activity));//7一元夺宝
            } else {
                json.setMessage("幸运购已结束请联系客服!");
            }
//            json.setDataValue(activityEntityDetail);

            activityEntityDetail.setMerchantId(ebuyProductBaseService.getEbuyProductBySeqId(activityEntityDetail.getProductId()).gettSupplyMerchantFId());

            json.putDataAll(MapConverter.toMap(activityEntityDetail));
            json.putData("mobile", user.getMobile());
        }
        return json;
    }

    /**
     * 确认付款
     * @param request
     * @return
     */
    @RequestMapping(value = "/confirmPay.json")
    @ResponseBody
    @RepeatReqValidate
    public JsonResponse confirmPay(HttpServletRequest request) {
        BigInteger activityId = ParamUtils.getBigInteger(request, "activityId", null);
        String userMobile = ParamUtils.getString(request, "userMobile", null);
        String userName = ParamUtils.getString(request, "userName", null);
        Double JFBAmount = ParamUtils.getDouble(request, "JFBAmount", 0);
        JsonResponse json = new JsonResponse();

        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        String subChannelId = HeaderManager.getSubChannelId();
        Long JFBAmountL = DataUtil.isEmpty(NumberUtils.doubleM100ToLong(JFBAmount)) ? 0 : NumberUtils.doubleM100ToLong(JFBAmount);
        Map<String, Object> resMap = flashDealActivityService.confirmPay(activityId, userId, userMobile, userName, subChannelId, JFBAmountL);
        json.setDataValue(resMap);
        return json;
    }

    /**
     * 活动提醒
     */
    @RequestMapping(value = "/flashDealRemind.json")
    @ResponseBody
    public JsonResponse flashDealRemind(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        BigInteger activityId = ParamUtils.getBigInteger(request, "activityId", null);
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();

        boolean res = flashDealActivityService.saveFlashDealRemind(activityId, userId);

        if(res){
            jsonResponse.setStatus("0000");
        } else {
            jsonResponse.setStatus("0001");
        }
        return jsonResponse;
    }

}
