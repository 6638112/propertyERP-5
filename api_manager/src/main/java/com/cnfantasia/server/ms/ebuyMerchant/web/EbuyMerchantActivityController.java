package com.cnfantasia.server.ms.ebuyMerchant.web;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.cnfantasia.server.api.ebuyorder.entity.EbuyMerchantBean;
import com.cnfantasia.server.api.ebuyorder.entity.MerchantProdDetail;
import com.cnfantasia.server.api.ebuyorder.service.EbuyMerchantService;
import com.cnfantasia.server.api.limitBuy.contant.LimitBuyDict;
import com.cnfantasia.server.api.limitBuy.entity.LimitBuyInfo;
import com.cnfantasia.server.api.limitBuy.service.ILimitBuyService;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.domainbase.ebuyProduct.service.IEbuyProductBaseService;
import com.cnfantasia.server.domainbase.limitBuyActivity.entity.LimitBuyActivity;
import com.cnfantasia.server.domainbase.limitBuyActivity.service.ILimitBuyActivityBaseService;
import com.cnfantasia.server.ms.ebuyMerchant.entity.McLogonUser;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.session.SessionManager;

/**
 * @ClassName: EbuyMerchantActivityController
 * @Date: 2017-01-17 11:20
 * @Auther: kangduo
 * @Description: (商户端活动管理控制层)
 */
@RequestMapping(value = "/ebuyMerchant/activity")
@Controller
public class EbuyMerchantActivityController extends BaseController {

    @Resource
    private EbuyMerchantService ebuyMerchantService;

    @Resource
    private ILimitBuyService limitBuyService;

    @Resource
    private IUuidManager uuidManager;

    @Resource
    private ILimitBuyActivityBaseService limitBuyActivityBaseService;

    @Resource
    private IEbuyProductBaseService ebuyProductBaseService;

    @RequestMapping(value = "/addLimitBuy.html", method = RequestMethod.GET)
    public ModelAndView addLimitBuyPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/ebuyMerchant/itemDetails-promotion");
        BigInteger productId = ParamUtils.getBigInteger(request, "productId", null);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("prodId", productId);
        MerchantProdDetail prodDetail = ebuyMerchantService.getMerchantProdDetail(paramMap);
        mav.addObject("prodDetail", prodDetail);
        return mav;
    }
    /**
     * 提交限时购
     * @param request
     * @param limitBuy
     * @return
     */
    @RequestMapping(value = "/addLimitBuy.html", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse addLimitBuy(HttpServletRequest request, LimitBuyActivity limitBuy) {
        JsonResponse json = new JsonResponse();
        BigInteger productId = limitBuy.gettEbuyProductFId();
        EbuyProduct product = ebuyProductBaseService.getEbuyProductBySeqId(productId);
        if (product != null && product.getLeftCount().intValue() <= 0) {
            json.setStatus("0001");
            json.setMessage("商品库存不足");
            return json;
        }
        //价格元转分
        BigInteger merchantId = BigInteger.valueOf(getSupplyId(request));
        Double price = ParamUtils.getDouble(request, "price", 0);
        long limitNumber = ParamUtils.getInt(request, "limitNumber", -1);//促销每人限够数量
        long leftCount = ParamUtils.getInt(request, "leftCount", 0);//促销库存
        
        if(leftCount <= 0){
        	 json.setStatus("0001");
             json.setMessage("促销库存必须大于0");
             return json;
        }
        
        limitBuy.setActivityPrice(PriceUtil.multiply100(price));
        BigInteger id = uuidManager.getNextUuidBigInteger(SEQConstants.t_limit_buy_activity);
        limitBuy.setId(id);
        limitBuy.setMaxCanBuy(limitNumber);
        limitBuy.setLeftCount(leftCount);
        limitBuy.setPositionType(LimitBuyDict.PositionType.IN_SELF_STORE);
        limitBuyActivityBaseService.insertLimitBuyActivity(limitBuy);
        //发推送
        new Thread(new PushMessgeThread(id, merchantId)).start();
        return json;
    }

    /**
     * 查楼下店的限时购列表
     * @param request
     * @param pageModel
     * @return
     */
    @RequestMapping(value = "/limitBuyList.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getLimitBuyList(HttpServletRequest request) {
        BigInteger merchantId = BigInteger.valueOf(getSupplyId(request));
        PageModel pageModel = ControllerUtils.getPageModel(request);
        List<LimitBuyInfo> limitBuyInfoList = limitBuyService.getLimitBuyListByMerchant(merchantId, 1, null, pageModel);
        JsonResponse json = new JsonResponse();
        json.putData("list", limitBuyInfoList);
        return json;
    }

    /**
     * 跳转到限时购详情修改
     * @param limitBuyId
     * @return
     */
    @RequestMapping(value = "editLimitBuy.html", method = RequestMethod.GET)
    public ModelAndView editLimitBuy(BigInteger limitBuyId) {
        ModelAndView mav = new ModelAndView();
        LimitBuyInfo limitBuyInfo = limitBuyService.getLimitBuyInfo(limitBuyId, 1);
        if (limitBuyInfo.getStatus() == 0) {
            mav.setViewName("/ebuyMerchant/itemDetails-promotionEdit");
        } else {
            mav.setViewName("/ebuyMerchant/itemDetails-onpromotion");
        }
        mav.addObject("limitBuyInfo", limitBuyInfo);
        return mav;
    }

    /**
     * 限时购修改
     * @param request
     * @param limitBuyActivity
     * @return
     */
    @RequestMapping(value = "editLimitBuy.html", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse limitBuyDetail(HttpServletRequest request, LimitBuyActivity limitBuyActivity) {
        JsonResponse json = new JsonResponse();
        LimitBuyInfo info = limitBuyService.getLimitBuyInfo(limitBuyActivity.getId(), 1);
        if (DataUtil.isEmpty(limitBuyActivity.getId())) {
            json.setStatus("0001");
            json.setMessage("数据不正确！");
            return json;
        } else if (info.getStatus() != 0) {
            json.setStatus("0000");
            json.setMessage("当前状态不允许修改！");
        }
        Double price = ParamUtils.getDouble(request, "price", 0);
        limitBuyActivity.setActivityPrice((long) (price*100));
        limitBuyActivityBaseService.updateLimitBuyActivity(limitBuyActivity);
        return json;
    }

    /**
     * 结束限时促销
     * @param limitBuyId 限时促销ID
     * @return
     */
    @RequestMapping(value = "closeLimitBuy.html", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse closeLimitBuy(BigInteger limitBuyId) {
        JsonResponse json = new JsonResponse();
        LimitBuyInfo limitBuyInfo = limitBuyService.getLimitBuyInfo(limitBuyId, 1);
        LimitBuyActivity activity = new LimitBuyActivity();
        activity.setId(limitBuyId);
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        activity.setActivityEndTime(now);
        if (limitBuyInfo.getStatus() == 2) {
            return json;
        } else if (limitBuyInfo.getStatus() == 0) {
            activity.setActivityStartTime(now);
        }
        limitBuyActivityBaseService.updateLimitBuyActivity(activity);
        return json;
    }


    /**
     * 获取供应商ID，顺便把供应商信息存至session
     * @param request
     * @return
     */
    private Long getSupplyId(HttpServletRequest request) {
        Long supplyId = SessionManager.getCurrentSupplyId(request);
        if(supplyId == null) {
            McLogonUser logonUser = SessionManager.getMcLogonUser(request);
            if(logonUser != null) {
                EbuyMerchantBean merchantBean = ebuyMerchantService.getEbuyMerchantByUserId(logonUser.getUserId());
                logonUser.setEbuyMerchantBean(merchantBean);
                SessionManager.setMcLogonUser(request, logonUser);

                supplyId = SessionManager.getCurrentSupplyId(request);
            }
        }
        return supplyId;
    }

    private class PushMessgeThread implements Runnable{
        private BigInteger merchantId;
        private BigInteger limitBuyId;

        PushMessgeThread(BigInteger limitBuyId, BigInteger merchantId) {
            this.merchantId = merchantId;
            this.limitBuyId = limitBuyId;
        }
        @Override
        public void run() {
            ebuyMerchantService.synSendPushAfterAddProduct(limitBuyId, merchantId);
        }
    }
}
