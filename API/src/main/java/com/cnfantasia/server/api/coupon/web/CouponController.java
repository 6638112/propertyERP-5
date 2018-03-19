package com.cnfantasia.server.api.coupon.web;

import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.coupon.constant.CouponTypeConstant;
import com.cnfantasia.server.api.coupon.dao.ICouponDao;
import com.cnfantasia.server.api.coupon.entity.UserCouponEntity;
import com.cnfantasia.server.api.coupon.service.ICouponService;
import com.cnfantasia.server.api.couponArea.contant.UserCouponStatus;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.userCoupon.service.IUserCouponService;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.exception.TimeOutRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domainbase.coupon.entity.Coupon;
import com.cnfantasia.server.domainbase.userCoupon.entity.UserCoupon;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 消费券控制层.
 */
@Controller
@RequestMapping(value = "/coupon")
public class CouponController extends BaseController {

    @Resource
    private IUserCouponService userCouponService;
    @Resource
    private ICouponDao couponDao;
    @Resource
    private ICommonRoomService commonRoomService;
    @Resource
    private ICouponService couponService;

    @RequestMapping(value = "/exchange.json")
    @ResponseBody
    public JsonResponse couponExchange(HttpServletRequest request) {
    	String code = ParamUtils.getString(request, "code", null);
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        String couponName = couponService.couponExchange(code, userId);
        JsonResponse json = new JsonResponse();
        json.setMessage("兑换成功，券名称：" + couponName);
        return json;
    }
    @RequestMapping(value = "getCouponCanSend.json")
    @ResponseBody
    public JsonResponse getCouponCanSend() {
        JsonResponse json = new JsonResponse();
        Map<String, Object> param = new HashMap<String, Object>();
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        //由于描述是满xx送xx，所以只查现金券
        param.put("roomId", commonRoomService.getDefaultRoomByUserId(userId).gettRealRoomFId());
        param.put("couponType", CouponTypeConstant.CASHCOUPON);
        param.put("goalType", "1");
        List<Coupon> coupons = couponDao.getCouponListCanSendToUser(param);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Coupon coupon : coupons) {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("id", coupon.getId());
            m.put("desc", "满" + coupon.getLeastSpendSend() + "送" + coupon.getDiscountMoney());
            list.add(m);
        }
        json.putData("list", list);
        return json;
    }

    @RequestMapping(value = "/getCouponSendResult.json")
    @ResponseBody
    public JsonResponse getCouponSendResult(HttpServletRequest request) {

        String orderId = request.getParameter("orderId");
        List<UserCouponEntity> coupons = null;
        JsonResponse json = new JsonResponse();
        if (!orderId.isEmpty()) {
            //获取指定订单送的券
            BigInteger bigId = new BigInteger(orderId);
            UserCoupon userCoupon = new UserCoupon();
            userCoupon.settUserFId(UserContext.getOperIdMustExistBigInteger());
            userCoupon.settEbuyOrderFId(bigId);
            userCoupon.setGetType(1);
            coupons = userCouponService.getUserCouponList(MapConverter.toMap(userCoupon));
        }
        json.putData("list", coupons);
        return json;
    }

    @RequestMapping(value = "/couponList.html")
    public ModelAndView jumptoCouponList(HttpServletRequest request) {

        Integer couponStatus = Integer.valueOf(request.getParameter("status") == null ? "1" : request.getParameter("status"));
        ModelAndView mav = new ModelAndView();
        UserCoupon userCoupon = new UserCoupon();
        BigInteger userId = UserContext.getOperIdBigInteger();
        if(userId == null) {
        	userId = ParamUtils.getBigInteger(request, "userId", null);
        	if(userId == null) {
        		throw new TimeOutRuntimeException();
        	}
        }
        request.setAttribute("userId", userId);
        
        userCoupon.settUserFId(userId);
        if (couponStatus.equals(1)) {
            mav.setViewName("coupon/couponList");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("status", UserCouponStatus.VALID);
            paraMap.put("gbId", commonRoomService.getGroupBuildingIdByUserId(userId));
            paraMap.put("tUserFId", userId);
            List<UserCouponEntity> coupons = userCouponService.getUserCouponList(paraMap);
            mav.addObject("couponList", coupons);

            userCoupon.setStatus(UserCouponStatus.USED);
            Map<String, Object> param = MapConverter.toMap(userCoupon);
            param.put("begin", 0);
            param.put("length", 1);
            List<UserCouponEntity> usedCoupons = userCouponService.getUserCouponList(param);
            userCoupon.setStatus(UserCouponStatus.INVALID);
            param = MapConverter.toMap(userCoupon);
            param.put("begin", 0);
            param.put("length", 1);
            List<UserCouponEntity> invalidCoupons = userCouponService.getUserCouponList(param);
            boolean flag = !(DataUtil.isEmpty(usedCoupons) && DataUtil.isEmpty(invalidCoupons));
            mav.addObject("hasInvalidCoupon", flag);
        } else {
            userCoupon.setStatus(UserCouponStatus.USED);
            Map<String, Object> param = MapConverter.toMap(userCoupon);
            param.put("begin", 0);
            param.put("length", 10);
            List<UserCouponEntity> usedCoupons = userCouponService.getUserCouponList(param);

            userCoupon.setStatus(UserCouponStatus.INVALID);
            param = MapConverter.toMap(userCoupon);
            param.put("begin", 0);
            param.put("length", 10);
            List<UserCouponEntity> invalidCoupons = userCouponService.getUserCouponList(param);

            List<UserCouponEntity> coupons = new ArrayList<UserCouponEntity>();
            coupons.addAll(usedCoupons);
            coupons.addAll(invalidCoupons);
            Collections.sort(coupons, new couponComparator());
            List<UserCouponEntity> resCoupon = new ArrayList<UserCouponEntity>();
            int count = 0;
            for (UserCouponEntity coupon : coupons) {
                count++;
                resCoupon.add(coupon);
                if (count >= 10) {
                    break;
                }
            }
            mav.addObject("couponList", resCoupon);
            mav.setViewName("coupon/couponList-overTime");
        }
        return mav;
    }

    /**
     * 提供给LA查询我的消费券列表，只查有效的
     * @return
     */
    @RequestMapping(value = "/couponList.json")
    @ResponseBody
    public JsonResponse getCouponList(HttpServletRequest request) {
        UserCoupon userCoupon = new UserCoupon();
        JsonResponse json = new JsonResponse();
        BigInteger userId = ParamUtils.getBigInteger(request, "userId", null);
        userId = userId == null ? UserContext.getOperIdMustExistBigInteger() : userId;
        userCoupon.settUserFId(userId);
        userCoupon.setStatus(UserCouponStatus.VALID);
        List coupons = userCouponService.getUserCouponList(MapConverter.toMap(userCoupon));
        json.putData("couponList", coupons);
        return json;
    }

    /**
     * 查询可领券的receiveScene.
     * @return
     */
    @RequestMapping("/qryCanReceiveScene.json")
    @ResponseBody
    public JsonResponse qryCanCouponReceiveScene(BigInteger gbId) {
        List<Map<String, Long>> maps = couponService.qryCanCouponReceiveScene(UserContext.getOperIdBigInteger(), gbId);
        JsonResponse json = new JsonResponse();
        json.putData("scenes", maps);
        json.putData("userId", UserContext.getOperIdBigInteger());
        return json;
    }

    /**
     * 查询可领券的receiveScene.
     * @return
     */
    @RequestMapping("/receiveSceneCoupon.json")
    @ResponseBody
    public JsonResponse receiveSceneCoupon(@RequestParam(value = "sceneId", defaultValue = "1") int sceneId, BigInteger gbId) {
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
//        BigInteger userId = BigInteger.ONE;
        gbId = gbId == null ? HeaderManager.getGbId() : gbId;
        List<UserCouponEntity> userCouponEntities = couponService.receiveSceneCoupon(userId, sceneId, gbId);
        JsonResponse json = new JsonResponse();
        json.putData("couponList", userCouponEntities);
        json.putData("userId", userId);
        return json;
    }

    /**
     * 订单支付完成后，分享订单得消费券
     * @param orderId 订单ID
     * @return 送的券列表
     */
    @RequestMapping("/receiveShareOrderCoupon.json")
    @ResponseBody
    public JsonResponse shareOrderCoupon(BigInteger orderId) {
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        List<UserCouponEntity> resList = userCouponService.sendShareOrderCoupon(userId, orderId);
        JsonResponse json = new JsonResponse();
        json.putData("couponList", resList);
        return json;
    }

    /**
     * 比较器，用于用户消费券按使用时间排序
     */
    private static class couponComparator implements Comparator<UserCouponEntity>, Serializable {

        private static final long serialVersionUID = -8481359026312140000L;

        @Override
        public int compare(UserCouponEntity o1, UserCouponEntity o2) {
            String coupon1 = o1.getUpdTime();
            String coupon2 = o2.getUpdTime();
            return coupon2.compareTo(coupon1);
        }
    }

}
