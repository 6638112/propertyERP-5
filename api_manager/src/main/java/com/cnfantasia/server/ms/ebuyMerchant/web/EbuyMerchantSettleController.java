package com.cnfantasia.server.ms.ebuyMerchant.web;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.ebuyorder.entity.DeliveryOrderDetailEntity;
import com.cnfantasia.server.api.ebuyorder.entity.EbuyMerchantBean;
import com.cnfantasia.server.api.ebuyorder.entity.SettleDelivOrder;
import com.cnfantasia.server.api.ebuyorder.service.EbuyMerchantService;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.constant.DictConstants;
import com.cnfantasia.server.api.pub.header.HeaderConstant;
import com.cnfantasia.server.api.pub.springSecurity.EncodeImpl;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.redpoint.constant.RedpointConstant;
import com.cnfantasia.server.api.redpoint.dao.IRedpointDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.HttpUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domainbase.ebuyProductSettleApplyLog.entity.EbuyProductSettleApplyLog;
import com.cnfantasia.server.domainbase.ebuyProductSettleApplyLog.service.IEbuyProductSettleApplyLogBaseService;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.service.IEbuySupplyMerchantBaseService;
import com.cnfantasia.server.domainbase.ebuySupplyMerchantBankAccount.entity.EbuySupplyMerchantBankAccount;
import com.cnfantasia.server.domainbase.ebuySupplyMerchantBankAccount.service.IEbuySupplyMerchantBankAccountBaseService;
import com.cnfantasia.server.domainbase.revenueApply.entity.RevenueApply;
import com.cnfantasia.server.domainbase.revenueApply.service.IRevenueApplyBaseService;
import com.cnfantasia.server.domainbase.supportBank.service.ISupportBankBaseService;
import com.cnfantasia.server.ms.ebuyMerchant.entity.McLogonUser;
import com.cnfantasia.server.ms.ebuyProductSettle.constants.PageType;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.RevenueApplyDto;
import com.cnfantasia.server.ms.ebuyProductSettle.service.IEbuyProductSettleService;
import com.cnfantasia.server.ms.provinceCityBlock.service.IProvinceCityBlockService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.session.SessionManager;
import com.cnfantasia.server.ms.pub.utils.MapConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: EbuyMerchantSettleController
 * @Date: 2016-09-20 15:49
 * @Auther: kangduo
 * @Description:(商户端结算中心)
 */
@Controller
@RequestMapping("/ebuyMerchant")
public class EbuyMerchantSettleController extends BaseController{
    private Log logger = LogFactory.getLog(getClass());

    @Resource
    private EbuyMerchantService ebuyMerchantService;

    @Resource
    private IEbuySupplyMerchantBaseService ebuySupplyMerchantBaseService;

    @Resource
    private IEbuyProductSettleService ebuyProductSettleService;

    @Resource
    private IEbuySupplyMerchantBankAccountBaseService ebuySupplyMerchantBankAccountBaseService;

    @Resource
    private IRevenueApplyBaseService revenueApplyBaseService;

    @Resource
    private IProvinceCityBlockService provinceCityBlockService;

    @Resource
    private ISupportBankBaseService supportBankBaseService;

    @Resource
    private IEbuyProductSettleApplyLogBaseService ebuyProductSettleApplyLogBaseService;

    @Resource
    private IRedpointDao redpointDao;

    @RequestMapping(value = "/settleCenter.html")
    public ModelAndView settleCenter(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/ebuyMerchant/settleCenter");
        Long supplyId = getSupplyId(request);
        if(supplyId == null) {
            mav.setViewName("/ebuyMerchant/login");
            return mav;
        }
        EbuySupplyMerchant ebuySupplyMerchant = ebuySupplyMerchantBaseService.getEbuySupplyMerchantBySeqId(BigInteger.valueOf(supplyId));
        request.getSession().setAttribute("merchantSupply", ebuySupplyMerchant);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("supplyMerchantId", supplyId);
        paramMap.put("pageType", PageType.APPLY_LIST);
        //待结算金额
        BigDecimal totalAmount = ebuyProductSettleService
                .selectRevenueApplyForTotalAmount(paramMap, ebuySupplyMerchant.getRevenueType())
                .setScale(2, RoundingMode.HALF_UP);
        //待结算运单数
        int applyTotal = ebuyProductSettleService.selectRevenueApplyForCount(paramMap);
        //银行卡信息
        paramMap.clear();
        paramMap.put("tSupplyMerchantId", supplyId);
        EbuySupplyMerchantBankAccount bankAccount = getSessionBankAccount(request, BigInteger.valueOf(supplyId));
        if (bankAccount != null) {
            mav.addObject("bankName", bankAccount.getBankName());
            mav.addObject("bankAccount", encodeBankAccount(bankAccount.getAccountBank(), 4));
        }
        //红点信息
        boolean hasRedPoint = ebuyMerchantService.
                hasNotClickRedPoint(RedpointConstant.RedpointContent_ModelCode.EBUY_MERCHANT_SETTLE_STAUTS_CHANGE, BigInteger.valueOf(supplyId));
        mav.addObject("hasRedPoint", hasRedPoint);

        mav.addObject("hasBankAccount", bankAccount != null);
        mav.addObject("totalAmount", totalAmount);
        mav.addObject("applyTotal", applyTotal);
        mav.addObject("ownerAuditStatus", ebuySupplyMerchant.getOwnerAuditStatus());
        mav.addObject("ownerPhone", ebuySupplyMerchant.getOwnerPhone());
        return mav;
    }

    /**
     * 银行卡号中间数字用*代替
     * @param account
     * @return 代替后的字符
     */
    private static String encodeBankAccount(String account, int notReplaceLength) {
        if (account == null || account.length() <= notReplaceLength * 2) {
            return account;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < account.length(); i++) {
            if (i < notReplaceLength || i >= account.length() - notReplaceLength) {
                sb.append(account.charAt(i));
            } else {
                sb.append("*");
            }
        }
        return sb.toString();
    }

    /**
     * 跳转到结算申请页面
     * @return
     */
    @RequestMapping(value = "/settleCenter/orderNotSettle.html")
    public ModelAndView jumpToOrderNotSettle(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/ebuyMerchant/orderNotSettle");
        EbuySupplyMerchant supplyMerchant = (EbuySupplyMerchant) request.getSession().getAttribute("merchantSupply");
        if (supplyMerchant == null || supplyMerchant.getOwnerPhone() == null) {
            Long supplyId = getSupplyId(request);
            supplyMerchant = ebuySupplyMerchantBaseService.getEbuySupplyMerchantBySeqId(BigInteger.valueOf(supplyId));
            request.getSession().setAttribute("merchantSupply", supplyMerchant);
        }
        EbuySupplyMerchantBankAccount bankAccount = getSessionBankAccount(request, supplyMerchant.getId());

        mav.addObject("hasBankAccount", bankAccount != null);
        mav.addObject("ownerPhone", supplyMerchant.getOwnerPhone());
        mav.addObject("bankName", bankAccount == null ? null : bankAccount.getBankName());
        mav.addObject("bankAccount", bankAccount == null ? null : encodeBankAccount(bankAccount.getAccountBank(), 4));

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("supplyMerchantId", supplyMerchant.getId());
        paramMap.put("pageType", PageType.APPLY_LIST);
        //待结算金额
        BigDecimal totalAmount = ebuyProductSettleService.selectRevenueApplyForTotalAmount(paramMap, supplyMerchant.getRevenueType());
        mav.addObject("totalAmount", totalAmount);
        return mav;
    }

    @RequestMapping(value = "/settleCenter/orderNotSettle.json")
    @ResponseBody
    public JsonResponse getOrderNotSettleList(HttpServletRequest request) {
        JsonResponse json = new JsonResponse();
        EbuySupplyMerchant ebuySupplyMerchant = (EbuySupplyMerchant) request.getSession().getAttribute("merchantSupply");
        if (ebuySupplyMerchant.getOwnerAuditStatus() != null && ebuySupplyMerchant.getOwnerAuditStatus() == 1) {
            PageModel pageModel = ControllerUtils.getPageModel(request);
            List<SettleDelivOrder> list = ebuyMerchantService.getOrderNotApplySettle(ebuySupplyMerchant.getId(), pageModel);
            json.putData("list", list);
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("supplyMerchantId", ebuySupplyMerchant.getId());
            paramMap.put("pageType", PageType.APPLY_LIST);
            int applyTotal = ebuyProductSettleService.selectRevenueApplyForCount(paramMap);
            json.putData("hasNext", applyTotal > pageModel.getBegin() + list.size());
        }
        return json;
    }

    @RequestMapping(value = "/settleCenter/orderDetail.html")
    public ModelAndView jumpToSettleOrderDetail(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/ebuyMerchant/deliveryOrderDetails");
        String page = request.getParameter("page");
        if ("refund".equals(page)) {
            mav.setViewName("/ebuyMerchant/deliveryRefundDetails");
        }
        BigInteger deliverOrderId = ParamUtils.getBigInteger(request, "deliveryOrderId", null);
        Long merchantId = getSupplyId(request);
        DeliveryOrderDetailEntity detail = ebuyMerchantService.getDeliveryOrderDetail(deliverOrderId, BigInteger.valueOf(merchantId));
        mav.addObject("detail", detail);
        return mav;
    }

    @RequestMapping(value = "/settleCenter/settle.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse settle(HttpServletRequest request) {
        JsonResponse json = new JsonResponse();
        String password = request.getParameter("password");
        Long supplyMerchantId = getSupplyId(request);
        McLogonUser logonUser = SessionManager.getMcLogonUser(request);

        //校验密码
        String encodePassword = EncodeImpl.doEncodePassword(password);
        EbuySupplyMerchant supplyMerchant = ebuySupplyMerchantBaseService.getEbuySupplyMerchantBySeqId(BigInteger.valueOf(supplyMerchantId));
        if (!encodePassword.equals(supplyMerchant.getWithdrawPassword())) {
            json.setMessage("密码不正确！");
            json.setStatus("0001");
            return json;
        }

        //校验可结算订单
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("supplyMerchantId", supplyMerchantId);
        paramMap.put("pageType", PageType.APPLY_LIST);
        int applyTotal = ebuyProductSettleService.selectRevenueApplyForCount(paramMap);
        if (applyTotal <= 0) {
            json.setMessage("暂无可结算订单！");
            json.setStatus("0001");
            return json;
        }
        //结算
        BigInteger applyId = ebuyProductSettleService.applyRevenue(
                BigInteger.valueOf(logonUser.getUserId()),
                BigInteger.valueOf(supplyMerchantId),
                logonUser.getEbuyMerchantBean().getOwnerPhone(), null,
                DictConstants.Channel_Sub.EBUY_MERCHANT);
        json.putData("applyId", applyId);
        return json;
    }

    @RequestMapping(value = "/settleCenter/settleRecordInfo.html", method = RequestMethod.GET)
    public ModelAndView getSettleRecordInfo(BigInteger applyId) {
        ModelAndView mav = new ModelAndView("/ebuyMerchant/settleRecordInfo");
        // 结算信息、审核结果
        RevenueApply revenueApply = revenueApplyBaseService.getRevenueApplyBySeqId(applyId);
        mav.addObject("revenueApply", revenueApply);
        mav.addObject("cardNo", encodeBankAccount(revenueApply.getbBankNo(), 4));

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("supplyMerchantId", revenueApply.gettEbuySupplyMerchantFk());
        paramMap.put("revenueApplyId", applyId);
        paramMap.put("pageType", PageType.DETAIL);
        int applyTotal = ebuyProductSettleService.selectRevenueApplyForCount(paramMap);
        mav.addObject("applyTotal", applyTotal);
        return mav;
    }

    @RequestMapping(value = "/settleCenter/bankAccount.html", method = RequestMethod.GET)
    public ModelAndView jumpToBankAccount(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/ebuyMerchant/bankAccount");
        EbuySupplyMerchantBankAccount bankAccount = getSessionBankAccount(request, BigInteger.valueOf(getSupplyId(request)));
        EbuySupplyMerchant supplyMerchant = (EbuySupplyMerchant) request.getSession().getAttribute("merchantSupply");
        if (supplyMerchant == null || supplyMerchant.getOwnerPhone() == null) {
            Long supplyId = getSupplyId(request);
            supplyMerchant = ebuySupplyMerchantBaseService.getEbuySupplyMerchantBySeqId(BigInteger.valueOf(supplyId));
            request.getSession().setAttribute("merchantSupply", supplyMerchant);
        }
        mav.addObject("ownerPhone", supplyMerchant.getOwnerPhone());
        mav.addObject("bankName", bankAccount == null ? null : bankAccount.getBankName());
        mav.addObject("cardNo", bankAccount == null ? null : encodeBankAccount(bankAccount.getAccountBank(), 4));
        return mav;
    }

    @RequestMapping(value = "/settleCenter/bindBankAccount.html", method = RequestMethod.GET)
    public ModelAndView jumpToBindBankAccount(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/ebuyMerchant/bindBankAccount");
        EbuySupplyMerchantBankAccount bankAccount = getSessionBankAccount(request, BigInteger.valueOf(getSupplyId(request)));
        mav.addObject("ownerName", bankAccount == null ? null : bankAccount.getAccountName());
        mav.addObject("pcbList", provinceCityBlockService.getProvinceWithCityBlockList());
        mav.addObject("bankList", supportBankBaseService.getSupportBankByCondition(null));
        return mav;
    }

    @RequestMapping(value = "/settleCenter/bindBankAccount.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse bindBankAccount(HttpServletRequest request, EbuySupplyMerchantBankAccount bankAccount) {
        JsonResponse json = new JsonResponse();
        Long supplyId = getSupplyId(request);
        if (!checkValicodeAndRemove(request)) {
            json.setStatus("0001");
            json.setMessage("验证码不正确或已过期");
            return json;
        }
        String withdrawPassword = ParamUtils.getString(request, "withdrawPassword", "");
        ebuyMerchantService.updateOrInsertBankAccount(BigInteger.valueOf(supplyId), withdrawPassword, bankAccount);
        request.getSession().removeAttribute("isValicodeCorrect");
        request.getSession().setAttribute("bankAccount", bankAccount);
        return json;
    }

    @RequestMapping(value = "/settleCenter/settleRecords.html", method = RequestMethod.GET)
    public String jumpToSetleRecords(HttpServletRequest request) {
        BigInteger supplyMerchantId = BigInteger.valueOf(getSupplyId(request));
        redpointDao.updateRedpointContentClicked(supplyMerchantId, 1, null, RedpointConstant.RedpointContent_ModelCode.EBUY_MERCHANT_SETTLE_STAUTS_CHANGE);
        return "/ebuyMerchant/settleRecords";
    }

    @RequestMapping(value = "/settleCenter/settleRecords.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse setleRecords(HttpServletRequest request) {
        Long supplyMerchantId = getSupplyId(request);
        PageModel pageModel = ControllerUtils.getPageModel(request);
        Integer settleStatus = ParamUtils.getInteger(request, "settleStatus", 1);
        int total = ebuyMerchantService.getRevenueApplyListCount(BigInteger.valueOf(supplyMerchantId), settleStatus);
        List<RevenueApplyDto> dtos = ebuyMerchantService.getRevenueApplyList(BigInteger.valueOf(supplyMerchantId), settleStatus, pageModel);
        JsonResponse json = new JsonResponse();
        json.putData("list", dtos);
        json.putData("total", total);
        json.putData("hasNext", pageModel.getBegin() + dtos.size() < total);
        return json;
    }

    @RequestMapping(value = "/settleCenter/settleRecordDetail.html", method = RequestMethod.GET)
    public String jumpToSettleRecordDetail(HttpServletRequest request) {
        return "/ebuyMerchant/settleRecordDetail";
    }

    @RequestMapping(value = "/settleCenter/settleRecordDetail.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse settleRecordDetail(HttpServletRequest request) {
        Long supplyId = getSupplyId(request);
        BigInteger applyId = ParamUtils.getBigInteger(request, "applyId", null);
        PageModel pageModel = ControllerUtils.getPageModel(request);
        EbuyProductSettleApplyLog log = new EbuyProductSettleApplyLog();
        log.settRevenueApplyFId(applyId);
        int total = ebuyProductSettleApplyLogBaseService.getEbuyProductSettleApplyLogCount(MapConverter.toMap(log));
        List<DeliveryOrderDetailEntity> dtos = ebuyMerchantService.getSettleDeliveryOrderList(BigInteger.valueOf(supplyId), applyId, pageModel);
        JsonResponse json = new JsonResponse();
        json.putData("total", total);
        json.putData("list", dtos);
        json.putData("hasNext", pageModel.getBegin() + dtos.size() < total);
        return json;
    }

    @RequestMapping(value = "/settleCenter/modifyWithdrawPassword.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse changeWithdrawPassword(HttpServletRequest request) {
        JsonResponse json = new JsonResponse();
        String withdrawPassword = ParamUtils.getString(request, "withdrawPassword", "");
        if (withdrawPassword.length() < 6) {
            json.setStatus("0001");
            json.setMessage("密码格式不正确");
            return json;
        }
        Long supplyMerchantId = getSupplyId(request);
        if (!checkValicodeAndRemove(request)) {
            json.setStatus("0001");
            json.setMessage("验证码不正确或已过期");
            return json;
        }
        EbuySupplyMerchant supplyMerchant = new EbuySupplyMerchant();
        supplyMerchant.setId(BigInteger.valueOf(supplyMerchantId));
        supplyMerchant.setWithdrawPassword(EncodeImpl.doEncodePassword(withdrawPassword));
        ebuySupplyMerchantBaseService.updateEbuySupplyMerchant(supplyMerchant);
        request.getSession().removeAttribute("isValicodeCorrect");
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

    private EbuySupplyMerchantBankAccount getSessionBankAccount(HttpServletRequest request, BigInteger supplyMerchantId) {
        EbuySupplyMerchantBankAccount bankAccount = (EbuySupplyMerchantBankAccount) request.getSession().getAttribute("bankAccount");
        if (bankAccount == null) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.clear();
            paramMap.put("tSupplyMerchantId", supplyMerchantId);
            List<EbuySupplyMerchantBankAccount> bankAccounts = ebuySupplyMerchantBankAccountBaseService.getEbuySupplyMerchantBankAccountByCondition(paramMap);
            if (!DataUtil.isEmpty(bankAccounts)) {
                bankAccount = bankAccounts.get(0);
            }
            request.getSession().setAttribute("bankAccount", bankAccount);
        }
        return bankAccount;
    }

    /**
     * 再次验证验证码用，之前单独验证的时候会记录验证结果，避免用户跳过校验
     * @param request
     * @return 验证码是否正确
     */
    private boolean checkValicodeAndRemove(HttpServletRequest request) {
        boolean flag = false;
        Boolean result = (Boolean) request.getSession().getAttribute("isValicodeCorrect");
        if (result != null && result) {
            flag = true;
            //清除验证码缓存
            HttpUtil httpUtil = new HttpUtil();
            httpUtil.addHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_DEVICE_ID, "ebuy_merchant_login");
            httpUtil.addHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL, HeaderConstant.SubChannelId.EBUY_MERCHANT_APP.toString());
            httpUtil.addHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_VERSION, "1.0.0");
            if(SessionManager.getMcLogonUser(request) != null) {
                httpUtil.addHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY, SessionManager.getMcLogonUser(request).getSessionKey());
            }
            httpUtil.addParameter("type", LoginDict.ValiCodeGetType.EBUY_MERCHANT + "");
            try {
                httpUtil.post(CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.Last_Api_BaseUrl) + "login/clearValicode.json", 10000, "UTF-8", request);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }
}
