package com.cnfantasia.wl.wechat.web;

import com.cnfantasia.pub.util.WeChatConfig;
import com.alipay.util.httpClient.HttpRequest;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.company.service.ICompanyService;
import com.cnfantasia.server.api.limitBuy.entity.LimitBuyInfo;
import com.cnfantasia.server.api.limitBuy.service.ILimitBuyService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.domainbase.ebuyProductIntroducePic.entity.EbuyProductIntroducePic;
import com.cnfantasia.server.domainbase.ebuyProductIntroducePic.service.IEbuyProductIntroducePicBaseService;
import com.cnfantasia.server.domainbase.ebuyProductParameters.entity.EbuyProductParameters;
import com.cnfantasia.server.domainbase.ebuyProductParameters.service.IEbuyProductParametersBaseService;
import com.cnfantasia.wl.wechat.WeChatConstant;
import com.cnfantasia.wl.wechat.model.SnsUserinfo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description:
 * @Auther: wenfq
 * @Date: 2017-08-04 13:38
 */

@Controller
@RequestMapping(value = "limitBuy")
public class LimitBuyController extends BaseController {
    @Resource
    private ILimitBuyService limitBuyService;
    @Resource
    private IEbuyProductIntroducePicBaseService ebuyProductIntroducePicBaseService;
    @Resource
    private IEbuyProductParametersBaseService ebuyProductParametersBaseService;
    @Resource
    ICompanyService companyService;
    @Resource
    ISysParamManager sysParamManager;
	@Resource
	private IHttpClient simpleHttpClient;

    @RequestMapping(value = "/limitBuyDetail.html", method = RequestMethod.GET)
    public ModelAndView jumpToLimitBuyDetail(HttpServletRequest request, HttpServletResponse response, BigInteger id) throws IOException {
    	Boolean isLogin = (Boolean) request.getSession().getAttribute("isLogin");
    	if ((!LoginHelper.isLoginUser(request) && request.getParameter("code") == null) || isLogin == null) {//判断此用户是否是未关注或登录的用户
    		String currentUrl = sysParamManager.getSysParaValue(SysParamKey.LA_BASE_URL) + "limitBuy/limitBuyDetail.html" + "?" + request.getQueryString();
			request.getSession().setAttribute("isLogin", true);
			String wechatRedirectUrl = WeChatConstant.OAuth2_URL.replace("APPID", WeChatConfig.APPID).replace("REDIRECT_URI", URLEncoder.encode(currentUrl, "utf-8"));
			response.sendRedirect(wechatRedirectUrl);
			return null; //重定向到微信授权页
		}

    	SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
		LoginHelper.loginAPI(simpleHttpClient, request, user);
		
        ModelAndView mav = new ModelAndView("/limitBuy/limitBuyDetail");

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
        
		// 购物车商品数量
		request.setAttribute("productCount",
				simpleHttpClient.submitSimple("/ebuy/qryBuyCarProductCount.json", null, LoginHelper.prepareReqHeader(request)).getDataValue());

        //客服电话
        String phone = companyService.getCompanyServiceInfo().getTel();
        mav.addObject("phone", phone);
        mav.addObject("limitBuyInfo", limitBuyInfo);
        mav.addObject("ebuyProductIntroducePicList", introducePicList);
        mav.addObject("productParametersList", parametersList);
        String basePath = sysParamManager.getSysParaValue(SysParamKey.PRODUCT_PIC_BASE_PATH);
        String imageServerUrl = sysParamManager.getImageServerUrl(SysParamKey.PRODUCT_PIC_BASE_PATH);
        mav.addObject("picserverUrl", imageServerUrl + basePath);
        return mav;
    }
}