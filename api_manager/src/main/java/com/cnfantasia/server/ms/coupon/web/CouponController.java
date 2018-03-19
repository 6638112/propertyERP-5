package com.cnfantasia.server.ms.coupon.web;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.search.DateTerm;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.coupon.constant.CouponUseTypeConstant;
import com.cnfantasia.server.api.coupon.entity.CouponDto;
import com.cnfantasia.server.api.coupon.entity.CouponEntity;
import com.cnfantasia.server.api.coupon.service.ICouponService;
import com.cnfantasia.server.api.couponArea.service.ICouponAreaService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.business.pub.communitySupply.ICommunitySupplyManager;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.domainbase.coupon.entity.Coupon;
import com.cnfantasia.server.domainbase.coupon.service.ICouponBaseService;
import com.cnfantasia.server.domainbase.couponScene.entity.CouponScene;
import com.cnfantasia.server.domainbase.couponScene.service.ICouponSceneBaseService;
import com.cnfantasia.server.domainbase.dredgeCouponConfig.entity.DredgeCouponConfig;
import com.cnfantasia.server.domainbase.dredgeCouponConfig.service.IDredgeCouponConfigBaseService;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.service.IEbuySupplyMerchantBaseService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.DateUtil;

/**
 * 消费券控制层
 */
@Controller
@RequestMapping(value = "/coupon")
public class CouponController extends BaseController {

    @Resource
    private ICouponService couponService;

    @Resource
    private ICouponBaseService couponBaseService;

    @Resource
    private ICouponAreaService couponAreaService;

    @Resource
    private IEbuySupplyMerchantBaseService ebuySupplyMerchantBaseService;

    @Resource
    private ICommunitySupplyManager communitySupplyManager;

    @Resource
    private IDredgeCouponConfigBaseService dredgeCouponConfigBaseService;
    
    @Resource
    private ICouponSceneBaseService couponSceneBaseService;

    @RequestMapping(value = "/addCoupon.html", method = RequestMethod.GET)
    public ModelAndView jumpToAddCoupon() {
        ModelAndView mav = new ModelAndView("/coupon/addCoupon");
        List<Map<String, Object>> communitySupplyTypeList = communitySupplyManager.getCommunitySupplyTypeList(null);
        mav.addObject("communitySupplyTypes", communitySupplyTypeList);
        
        List<CouponScene> couponSceneList = couponSceneBaseService.getCouponSceneByCondition(null);
        mav.addObject("couponSceneList", couponSceneList);
        mav.addObject("now", DateTime.now().toString("yyyy-MM-dd"));
        return mav;
    }

    @RequestMapping(value = "/couponDetail.html", method = RequestMethod.GET)
    public ModelAndView jumpToCouponDetail(BigInteger couponId) {
        ModelAndView mav = new ModelAndView("/coupon/couponDetail");
        Coupon coupon = couponBaseService.getCouponBySeqId(couponId);
        List<Map> areas = null;
        if (coupon.getSendAreaType().equals(2)) {
            areas = couponAreaService.getCityListByCouponId(couponId);
        } else if(coupon.getSendAreaType().equals(3)) {
            areas = couponAreaService.getGroupBuildingListByCouponId(couponId);
        }
        EbuySupplyMerchant merchant = ebuySupplyMerchantBaseService.getEbuySupplyMerchantBySeqId(coupon.getSupplyMerchantId());
        mav.addObject("supplyMerchant", merchant);
        mav.addObject("areas", areas);
        mav.addObject("coupon", coupon);
        if (coupon.getUseType().equals(CouponUseTypeConstant.REPAIR)) {
            DredgeCouponConfig config = new DredgeCouponConfig();
            List<DredgeCouponConfig> configs = dredgeCouponConfigBaseService.getDredgeCouponConfigByCondition(MapConverter.toMap(config));
            if (!DataUtil.isEmpty(configs)) {
                config = configs.get(0);
            }
            List<Map<String, Object>> communitySupplyTypeList = communitySupplyManager.getCommunitySupplyTypeList(null);
            for (Map<String, Object> map : communitySupplyTypeList) {
                if (map.get("id").toString().equals(config.getCommunitySupplyTypeId().toString())) {
                    mav.addObject("communitySupplyTypeName", map.get("name").toString());
                }
            }
            mav.addObject("dredgecCouponConfig", config);
        }
        
        List<Map<String, Object>> products = couponService.getCouponProductList(couponId);
        mav.addObject("products", products);
        
        String couponSceneName = StringUtils.EMPTY;
        if(coupon!=null && coupon.getReceiveScene()!=null){
        	CouponScene couponScene = couponSceneBaseService.getCouponSceneBySeqId(coupon.getReceiveScene());
        	if(couponScene!=null){
        		couponSceneName = couponScene.getName();
        	}
        }
        mav.addObject("couponSceneName", couponSceneName);
        
        return mav;
    }

    @RequestMapping(value = "/updCoupon.html", method = RequestMethod.GET)
    public ModelAndView jumpToUpdCoupon(BigInteger couponId) {
        ModelAndView mav = new ModelAndView("/coupon/updCoupon");
        Coupon coupon = couponBaseService.getCouponBySeqId(couponId);
        List<Map> areas = null;
        if (coupon.getSendAreaType().equals(2)) {
            areas = couponAreaService.getCityListByCouponId(couponId);
        } else if(coupon.getSendAreaType().equals(3)) {
            areas = couponAreaService.getGroupBuildingListByCouponId(couponId);
        }
        EbuySupplyMerchant merchant = ebuySupplyMerchantBaseService.getEbuySupplyMerchantBySeqId(coupon.getSupplyMerchantId());
        mav.addObject("supplyMerchant", merchant);
        mav.addObject("areas", areas);
        mav.addObject("coupon", coupon);
        if (coupon.getUseType().equals(CouponUseTypeConstant.REPAIR)) {
            DredgeCouponConfig config = new DredgeCouponConfig();
            List<DredgeCouponConfig> configs = dredgeCouponConfigBaseService.getDredgeCouponConfigByCondition(MapConverter.toMap(config));
            if (!DataUtil.isEmpty(configs)) {
                config = configs.get(0);
            }
            List<Map<String, Object>> communitySupplyTypeList = communitySupplyManager.getCommunitySupplyTypeList(null);
            mav.addObject("communitySupplyTypes", communitySupplyTypeList);
            mav.addObject("dredgecCouponConfig", config);
        }
        
        List<Map<String, Object>> products = couponService.getCouponProductList(couponId);
        mav.addObject("products", products);
        
        List<CouponScene> couponSceneList = couponSceneBaseService.getCouponSceneByCondition(null);
        mav.addObject("couponSceneList", couponSceneList);
        mav.addObject("now", DateTime.now().toString("yyyy-MM-dd"));
        return mav;
    }

    @RequestMapping(value = "/couponList.html")
    public ModelAndView jumpToCouponList(Coupon coupon, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/coupon/couponList");
        if (coupon.getQueryOrderByType() == null) {
            coupon.setQueryOrderByType(5);
        }
        Map<String, Object> param = MapConverter.toMap(coupon);

        String pageIndexName = new org.displaytag.util.ParamEncoder("row")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);// 页数的参数名

        int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));

        int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ?
                0 : (Integer.parseInt(request.getParameter(pageIndexName))-1);

        PageModel pageModel = new PageModel((curPageIndex)*pageSize, pageSize);

        List<CouponDto> couponList = couponService.getCouponListByCondition(param, pageModel);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String today = df.format(new Date());
        //判断是否可编辑
        for (int i = 0; i < couponList.size(); i++) {
            String sendStartDate = couponList.get(i).getSendStartDate();
            String sendEndDate = couponList.get(i).getSendEndDate();
            couponList.get(i).setCanEdit(!DateUtil.isSysTimeLaterThan(sendStartDate));
            couponList.get(i).setCanOpen(today.compareTo(sendEndDate) <= 0);
        }
        int total = couponBaseService.getCouponCount(param);
        mav.addObject("param", coupon);
        mav.addObject("resList", couponList);
        mav.addObject("resultSize", total);
        return mav;
    }

    @RequestMapping(value = "/addCoupon.html", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse addCoupon(CouponEntity coupon, HttpServletRequest request) {
        BigInteger userId = UserContext.getOperIdBigIntegerMustExist();
        coupon.setSys0AddUser(userId);
        
        String[] productIds = request.getParameterValues("shelfIds");
    	coupon.setProductIds(productIds);
    	
        couponService.addCoupon(coupon);
        return new JsonResponse();
    }

    @RequestMapping(value = "/updCoupon.html", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse updCoupon(CouponEntity coupon, HttpServletRequest request) {
        UserContext.getOperIdBigIntegerMustExist();
        BigInteger userId = UserContext.getOperIdBigIntegerMustExist();
        Coupon nowCoupon = couponBaseService.getCouponBySeqId(coupon.getId());
        //开始发放不可编辑
        if (DateUtil.isSysTimeLaterThan(nowCoupon.getSendStartDate())) {
            throw new BusinessRuntimeException("couponContrller.updCoupon.overStartDate");
        }
        coupon.setSys0UpdUser(userId);
        
        String[] productIds = request.getParameterValues("shelfIds");
    	coupon.setProductIds(productIds);
    	
        couponService.updCoupon(coupon);
        return new JsonResponse();
    }

    @RequestMapping(value = "/switchCoupon.html", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse switchCouponStatus(Coupon coupon) {

        Coupon nowCoupon = couponBaseService.getCouponBySeqId(coupon.getId());
        coupon.setSys0UpdUser(UserContext.getOperIdBigIntegerMustExist());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String today = df.format(new Date());
        //超过发券时间不可开启
        if (today.compareTo(nowCoupon.getSendEndDate()) > 0 && coupon.getCouponStatus().equals(1)) {
            throw new BusinessRuntimeException("couponController.switchCouponStatus.overEndDate");
        }
        couponBaseService.updateCoupon(coupon);
        return new JsonResponse();
    }

}
