package com.cnfantasia.server.api.loan.web;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.loan.constants.LoanDict;
import com.cnfantasia.server.api.loan.entity.ChargeInfoEntity;
import com.cnfantasia.server.api.loan.entity.LoanEntity;
import com.cnfantasia.server.api.loan.entity.LoanProduct;
import com.cnfantasia.server.api.loan.entity.LoanUserInfoEntity;
import com.cnfantasia.server.api.loan.service.ILoanService;
import com.cnfantasia.server.api.payment.util.SqPayRSAUtils;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.HttpUtil;
import com.cnfantasia.server.domainbase.loanProduct.service.ILoanProductBaseService;
import com.cnfantasia.server.domainbase.user.dao.IUserBaseDao;
import com.cnfantasia.server.domainbase.user.entity.User;

/**
 * 借贷
 * 
 * @author liyulin
 * @version 1.0 2017年6月5日 下午3:22:03
 */
@Controller
@RequestMapping("/loan")
public class LoanController extends BaseController {
	private final Logger logger = Logger.getLogger(getClass());
	private static final ExecutorService loanNewCachedThreadPool = Executors.newCachedThreadPool();

	@Autowired
	private ILoanService loanService;
	@Autowired
	private ILoanProductBaseService loanProductBaseService;
	@Autowired
	private IUserBaseDao userBaseDao;

	/**
	 * 借贷首页借贷产品信息查询
	 * 
	 * @param page 页码
	 * @return
	 */
	@RequestMapping("/indexPage.json")
	public ModelAndView indexPage() {
		return new ModelAndView("/loanList/index");
	}
	
	/**
	 * 借贷首页借贷产品信息查询
	 * 
	 * @param page 页码
	 * @return
	 */
	@RequestMapping("/index.json")
	@ResponseBody
	public JsonResponse index(int page) {
		int pageNum = 10;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("_begin", (page-1)*pageNum);
		paramMap.put("_length", pageNum);
		
		List<LoanEntity> loanEntityList = loanService.getLoanProductWithList(paramMap);
		dealLoanIcon(loanEntityList);
		
		int count = loanService.getLoanProductWithCount();
		boolean isLast = (page*pageNum >= count);
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("loanEntityList", loanEntityList);
		dataMap.put("isLast", isLast);
		dataMap.put("isLogin", UserContext.isUserInContext());
		dataMap.put("mobile", getMobile());
		dataMap.put("userId", UserContext.getOperIdBigInteger());
		
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.putDataAll(dataMap);
		return jsonResponse;
	}
	
	/**
	 * 获取用户手机号
	 * @return
	 */
	private String getMobile(){
		BigInteger userId = UserContext.getOperIdBigInteger();
		if(null!=userId){
			User user = userBaseDao.selectUserBySeqId(userId);
			if(null!=user && StringUtils.isNotBlank(user.getMobile())){
				return user.getMobile();
			}
		}
		return null;
	}
	
	
	/**
	 * 处理借贷图标地址
	 * @param loanEntityList
	 */
	private static final void dealLoanIcon(List<LoanEntity> loanEntityList){
		if(loanEntityList!=null){
			for(LoanEntity loanEntity:loanEntityList){
				List<LoanProduct> loanProductList = loanEntity.getLoanProductList();
				if(loanProductList!=null){
					for(LoanProduct loanProduct:loanProductList){
						String picUrl = loanProduct.getPicUrl();
						if(StringUtils.isNotBlank(picUrl)){
							String icon = ApplicationContextBothUtil.getAbsolutePath(picUrl, SysParamKey.LOAN_ICO_BASEPATH, null);
							loanProduct.setPicUrl(icon);
						}
					}
				}
			}
		}
	}

	/**
	 * 跳到第三方页面
	 * 
	 * @param lpId
	 * @return
	 */
	@RequestMapping("/goThirdPage.json")
	public ModelAndView goThirdPage(BigInteger lpId) {
		// TODO:
		com.cnfantasia.server.domainbase.loanProduct.entity.LoanProduct loanProduct = loanProductBaseService.getLoanProductBySeqId(lpId);
		ModelAndView modelAndView = new ModelAndView("/loan/loanThirdPage");
		modelAndView.addObject("url", loanProduct.getThirdUrl());
		//modelAndView.addObject("userId", "100000");
		return modelAndView;
	}

	/**
	 * 购买后重写记录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/reWrite.json")
	@ResponseBody
	public JsonResponse reWrite(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		return jsonResponse;
	}
	
	/**
	 * 根据useId获取用户借贷信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getLoanUserInfo.json"/*, method=RequestMethod.POST*/)
	@ResponseBody
	public JsonResponse getLoanUserInfo(String userId, String sign, HttpServletRequest request){
		String allParams = HttpUtil.getAllParams("/loan/getLoanUserInfo.json", request);
		logger.info(allParams);
		
		JsonResponse jsonResponse = new JsonResponse();
		if(StringUtils.isBlank(userId)){
			jsonResponse.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
			jsonResponse.setMessage("userId不能为空！");
		} else if(!StringUtils.isNumeric(userId)){
			jsonResponse.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
			jsonResponse.setMessage("userId格式不正确！");
		} else if(StringUtils.isBlank(sign)){
			jsonResponse.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
			jsonResponse.setMessage("签名字符串不能为空！");
		} else {
			// 签名公钥：解放区用
			//String signPubicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3Ri1nOjte4KPatIyphjxKUa36fr0IxPuv6xH5MkGqsBe7HBb+uMi0voqk9BF1HwGHX9yvfmO7EdWzDYcWsLbgbjYqYmSf9UEVnDOqdjSYsdtSOK+rKvHOo0oP40JPvbuEtJIqYpODAW3Ec//Ts/0iDVRRZ2cHuoaLqDO4sZqWvpyO1WTFEDRrMF3gIre/C88/ym901UN2rPaJHzX7XHtqeZwwmbkL7Zbb1iSeFnVEQM5oSHdc7fcitYpNsUlMVWkpIZPkept2y48EORPIQb4QbWKpRxc6rhUaul59uj6OGfDByvudKz3zJ/7sEuMOFJShnHIqq3ZX0LUaFmC1AWbwQIDAQAB";
			// 签名私钥：团贷网用
			//String signPrivateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDdGLWc6O17go9q0jKmGPEpRrfp+vQjE+6/rEfkyQaqwF7scFv64yLS+iqT0EXUfAYdf3K9+Y7sR1bMNhxawtuBuNipiZJ/1QRWcM6p2NJix21I4r6sq8c6jSg/jQk+9u4S0kipik4MBbcRz/9Oz/SINVFFnZwe6houoM7ixmpa+nI7VZMUQNGswXeAit78Lzz/Kb3TVQ3as9okfNftce2p5nDCZuQvtltvWJJ4WdURAzmhId1zt9yK1ik2xSUxVaSkhk+R6m3bLjwQ5E8hBvhBtYqlHFzquFRq6Xn26Po4Z8MHK+50rPfMn/uwS4w4UlKGcciqrdlfQtRoWYLUBZvBAgMBAAECggEBANfLDy9X1LpQ4uPSxuaAv5iW0liHvJMkGElxwDXX0y8DVWKWA+Bqoomi672bcRyD9IAD7ZzoH4wT0O69/YzEVPRW0vkeMiOcaIBp1/vEiQsqNEsILCxh+LD4JwloAVc4MYOjRJJdT/HB/j1fcoOcysHKsEkvHLq7qNRL1cFXAWVK7Qr2H0xskPhZp15YLU7IPL59gEmkM0eaIEeWqt12ieNlJHOZ9rY6G26KRgqkaTZqZegVpgyfixUlnNOv/ash0o+025kDQ/73bUAlgezhbFq3E9DW0AYO0TNriZ8VNRzZ4XlHfk7jlR8WC6Sh3QBvg2GUiEMhi6xYtfKDehxDloUCgYEA+fOg/8/4kKOLMVOA0dBGTpAhQ5kHIll8OMsNYpHNBoZzAgyhRdq2VBd9ZE2kHma8keeSyMecxEuPDfy7YeiDPj6Gjtkzgh2vyEEB9rLCQ/ilVLcOlGoiiwtH8JgSgGwyXn+/zwtwArzY7ry921nP/U9TI5siAWnP494hPwiJHhsCgYEA4nJU/3nItvgEH7LrFXrwXYcOqWamkp71vyinJaZeDgMStUsuFA9FTMhWv+UV/6+OcvtooI9IoPif1LsgHh0XjRDCPOsAqUFCpI93RUVM8qOSMxrMv4mz5eZ0KumN6Utfx1B6/hNIlmxW6QyNma3mhTif99bfPfJuBxp2AhJsG1MCgYEA4UOofeQ46GH8OGBBYNaMBpHu6LUpsNNlGOonDgbI+HCDhf1L+2C5od/pqlQu7Rc6K2TA7SK5Lk/KhZiYaAzc+2PucR9fRIzst1WfJ27Dle6pUkgIY8mjiByKoVLsmcDpRDFt9HMiZ9U6zS5dDStMP8cV7qkJZKywJ3UyEVTn/rECgYEA1eMEIeMYEKqmyoImxj0++DhvQtawk2EN4ac2abuAr197tj3yogPigynftpVdeAqMVFLfenicM19jKH4vUgJMknO34+5cy4HWvrS8BXP1wGF74EV6C5i3kXlhVH8SCb+mEg8UfRQdxedDGBFBVl0Jld/sSR5t8ocgIhl+SWq5Lf0CgYBfYuhsoPbWRVRyd5U/Y09QBbwyG+3XHsmvV3C/U4jr7Z66+1cy1qdIpxXVjOIf/IvuEJDl9BXP+wXam0jO4RIKqUHHcuP0qJxS3c3wZJSCS3JaArtUQy4YB23IEhU+yqDyWhMsCdYJP42k7ptmyP7kDcej7kvCXx3aGVSv3Y0Wng==";
			String signPubicKey = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.TDW_SIGN_PUBLIC_KEY);
			Map<String, String> paramMap = new TreeMap<String, String>();
			paramMap.put("userId", userId);
			// 签名前数据
			String beforSignInfo = JSONObject.toJSONString(paramMap);
			byte[] dataBytes;
			boolean signflag = false;
			try {
				dataBytes = beforSignInfo.getBytes("UTF-8");
				// 对签名数据进行验签
				signflag = SqPayRSAUtils.verify(dataBytes, signPubicKey, sign);
				logger.info("验签结果：" + signflag+"[userId="+userId+";sign="+sign+"]");
			} catch (Exception e) {
				logger.error("参数签名校验异常！", e);
				
				jsonResponse.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
				jsonResponse.setMessage("参数签名校验异常！");
				return jsonResponse;
			}
			
			if(signflag){
				LoanUserInfoEntity loanUserInfo = getLoanUserInfoEntity(userId);
				jsonResponse.setDataValue(loanUserInfo);
			} else {
				jsonResponse.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
				jsonResponse.setMessage("参数签名校验未通过！");
			}
		}
		return jsonResponse;
	}
	
	/**
	 * 根据useId获取用户借贷信息（测试）
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getLoanUserInfoTest.json")
	@ResponseBody
	public JsonResponse getLoanUserInfoTest(String userId, HttpServletRequest request){
		String allParams = HttpUtil.getAllParams("/loan/getLoanUserInfoTest.json", request);
		logger.info(allParams);
		
		String url = request.getRequestURL().toString();
		JsonResponse jsonResponse = new JsonResponse();
		if(!url.contains("api.jiefangqu.com") 
				&& (url.startsWith("http://127.0.0.1") 
						|| url.startsWith("http://localhost") 
						|| url.startsWith("http://api.linlile")
						|| url.startsWith("https://api.linlile"))) {
			logger.info("[/loan/getLoanUserInfo.json]userId="+userId);
			if(StringUtils.isBlank(userId)){
				jsonResponse.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
				jsonResponse.setMessage("userId不能为空！");
			} else if(!StringUtils.isNumeric(userId)){
				jsonResponse.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
				jsonResponse.setMessage("userId格式不正确！");
			} else {
				LoanUserInfoEntity loanUserInfo = getLoanUserInfoEntity(userId);
				jsonResponse.setDataValue(loanUserInfo);
			}
		} else {
			jsonResponse.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
			jsonResponse.setMessage("非法访问！");
		}
		
		return jsonResponse;
	} 
	
	private LoanUserInfoEntity getLoanUserInfoEntity(String userId){
		String cacheKey = LoanDict.CACHE_KEY_PERFIX + userId;
		String cacheData = RedisCacheHandler.get(cacheKey);
		
		LoanUserInfoEntity loanUserInfo = null;
		if(null==cacheData){
			BigInteger id = new BigInteger(userId);
			loanUserInfo = loanService.getLoanUserInfo(id);
			
			loanNewCachedThreadPool.execute(new LoanCacheRunnable(cacheKey, loanUserInfo));
		} else {
			loanUserInfo = JSON.parseObject(cacheData, LoanUserInfoEntity.class);
		}
		
		return loanUserInfo;
	}
	
	class LoanCacheRunnable implements Runnable{
		private String cacheKey;
		private LoanUserInfoEntity loanUserInfo;
		
		public LoanCacheRunnable(String cacheKey, LoanUserInfoEntity loanUserInfo){
			this.cacheKey = cacheKey;
			this.loanUserInfo = loanUserInfo;
		}
		
		@Override
		public void run() {
			String cacheData = JSON.toJSONString(loanUserInfo);
			int cacheTime = CnfantasiaCommbusiUtil.getSysParaValueInt(SysParamKey.TDW_CACHE_TIME, 86400);//默认时间一天（86400秒）
			RedisCacheHandler.set(cacheKey, cacheData, cacheTime);
		}
	}
	
	// 测试数据
	private LoanUserInfoEntity test(){
		LoanUserInfoEntity loanUserInfo = new LoanUserInfoEntity();
		loanUserInfo.setName("张三");
		loanUserInfo.setMobile("13285869514");
		loanUserInfo.setCardId("611281198305093514");
		loanUserInfo.setCityName("深圳");
		loanUserInfo.setResidenceTime(22);
		loanUserInfo.setRoomSize(113.7);
		loanUserInfo.setEnergyFee(120.98);
		loanUserInfo.setWaterFee(22.45);
		
		ChargeInfoEntity propertyCharge = new ChargeInfoEntity();
		propertyCharge.setTotalAmount(BigDecimal.valueOf(100.3));
		propertyCharge.setRealAmount(BigDecimal.valueOf(100.3));
		propertyCharge.setChargeCount(3);
		propertyCharge.setLastPayTime("2017-07-04 12:20:23");
			
		loanUserInfo.setPropertyCharge(propertyCharge);
		
		loanUserInfo.setHasCar(LoanDict.Sure.YES);
		loanUserInfo.setHasParkingLot(LoanDict.Sure.YES);
		loanUserInfo.setIsPropertyProprietor(LoanDict.Sure.UNKNOWN);
		
		loanUserInfo.setUnPropertyChargeCount(13);
		loanUserInfo.setFinanceLogCount(0);
		return loanUserInfo;
	}
}