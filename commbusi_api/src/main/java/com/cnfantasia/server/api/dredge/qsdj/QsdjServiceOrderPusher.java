package com.cnfantasia.server.api.dredge.qsdj;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.commSysPara.entity.QSDJPushParam;
import com.cnfantasia.server.api.commSysPara.parser.QSDJPushParamParser;
import com.cnfantasia.server.api.communitySupply.entity02.Location;
import com.cnfantasia.server.api.communitySupply.util.LocationConverter;
import com.cnfantasia.server.api.dredge.dao.DredgeDao;
import com.cnfantasia.server.api.dredge.entity.DredgeBill4Qsdj;
import com.cnfantasia.server.api.dredge.service.AbstractServiceOrderPusher;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.HttpUtil;
import com.cnfantasia.server.domainbase.dredgeBill.entity.DredgeBill;
import com.cnfantasia.server.domainbase.serviceTypeMapper.entity.ServiceTypeMapper;
import com.cnfantasia.server.domainbase.serviceTypeMapper.service.ServiceTypeMapperBaseService;
import com.cnfantasia.server.domainbase.user.dao.UserBaseDao;
import com.cnfantasia.server.domainbase.user.entity.User;


/**
 * 轻松到家推送器
 * @author wenfq  2016年11月4日
 *
 */
public class QsdjServiceOrderPusher extends AbstractServiceOrderPusher {
	private Log logger = LogFactory.getLog(getClass());
	
	public String pushNewOrder(DredgeBill db) {
		String pushNewOrderURL = this.getBaseURL() + "/createOrder.php";
		
		HttpUtil httpUtil = new HttpUtil();

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accessKey", getAppKey());
		paramMap.put("pt_order_id", db.getBillNo());
		
//		paramMap.put("productId", 17); // 产品列表中的产品id 否
//		paramMap.put("latitude", 39.999258); // 纬度 否
//		paramMap.put("longitude", 116.488885); // 经度 否
//		paramMap.put("serviceTime", "2016-11-9 08:30:00"); // 服务时间,格式:yyyy-MM-dd HH:mm:ss 否
//		paramMap.put("price", 23.55); // 价格,单位元 否
//		paramMap.put("quantity", 1); // 数量 否
//		paramMap.put("cellPhone", "13145826351"); // 客户手机号 否
//		paramMap.put("name", "owen"); // 客户姓名 否
//		paramMap.put("detailAddress", "深圳市南山松坪村2期"); // 楼栋,门牌号 否
//		paramMap.put("comment", "这是测试单"); // 客户备注
		
		DredgeDao dredgeDao = (DredgeDao) CnfantasiaCommbusiUtil.getBeanManager("dredgeDao");
		DredgeBill4Qsdj dredgeBill4Qsdj = dredgeDao.qryDredgeBill4Qsdj(db.getId());
		ServiceTypeMapperBaseService  serviceTypeMapperBaseService= (ServiceTypeMapperBaseService) CnfantasiaCommbusiUtil.getBeanManager("serviceTypeMapperBaseService");
		
		ServiceTypeMapper serviceTypeMapper = new ServiceTypeMapper();
		serviceTypeMapper.settDredge3rdSplFId(new BigInteger("20"));
		serviceTypeMapper.settDredgeTypeFId(db.gettDredgeTypeFId());
		serviceTypeMapper.settDredgeType2ndFId(db.gettDredgeType2ndFId());
		List<ServiceTypeMapper> serviceTypeMapperList = serviceTypeMapperBaseService.getServiceTypeMapperByCondition(MapConverter.toMap(serviceTypeMapper));
		if(serviceTypeMapperList.size()==0 ){
			ValidateRuntimeException e = new ValidateRuntimeException("has not set mapper relation");
			e.setErrorMsg("该类型没有配置轻松到家类型映射关系");
			throw e;
		}else if(serviceTypeMapperList.size() >1){
			ValidateRuntimeException e = new ValidateRuntimeException("has set more than one mapper relation");
			e.setErrorMsg("我方类型映射了轻松到家的多个类型");
			throw e;
		}else{
			paramMap.put("product_id", serviceTypeMapperList.get(0).getServiceTypeId());
		}
		
		IHttpClient simpleHttpClient = (IHttpClient) CnfantasiaCommbusiUtil.getBeanManager("simpleHttpClient");
		Map<String, Object> paramMap2 = new HashMap<String, Object>();
		paramMap2.put("roomId", db.getRoomid());
		
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/dredge/getLocationByGbIdOrRoomId.json", paramMap2);
		
		Location location = new Location();
		location.setLat(((Map<String, Object>)jsonResponse.getDataValue()).get("lat")+"");
		location.setLng(((Map<String, Object>)jsonResponse.getDataValue()).get("lng")+"");
		location = LocationConverter.bd09ToGcj02(location);
		paramMap.put("latitude", location.getLat()); // 纬度 否
		paramMap.put("longtitude", location.getLng()); // 经度 否
		
		Date d =DateUtils.convertStrToDate(db.getExpectdate(), "yyyy-MM-dd HH:mm:ss"); 
		int i =  d.getMinutes() / 30;
		if( d.getMinutes()<30){
			d.setMinutes(0);
		}else if( d.getMinutes()==30){
			
		}else{ // d.getMinutes()>30
			d.setHours(d.getHours() +1);
			d.setMinutes(0);
		}
		d.setSeconds(0);
		
		paramMap.put("service_time", DateUtils.formatTime(d)); // 服务时间,格式:yyyy-MM-dd HH:mm:ss 必须是半小时为单位
		paramMap.put("price", db.getPayAmount()/100.0); // 价格,单位元 否
		paramMap.put("quantity", db.getDredgeTypeNum()==null?1: db.getDredgeTypeNum()); // 数量,默认给1
		paramMap.put("cellphone", db.getTel()); // 客户手机号 否
		
		UserBaseDao userBaseDao = (UserBaseDao) CnfantasiaCommbusiUtil.getBeanManager("userBaseDao");
		User u = userBaseDao.selectUserBySeqId(db.gettUserFId());
		String userName = StringUtils.isEmpty(u.getRealName()) ? u.getNickName() : u.getRealName();
		userName = StringUtils.isEmpty(userName) ? "解放区用户" : userName;
		paramMap.put("name", userName); // 客户姓名 否
		paramMap.put("service_address", dredgeBill4Qsdj.getFullAddress()); // 楼栋,门牌号 否
		paramMap.put("houseNumber", db.getAddress()); // 楼栋,门牌号 否
		paramMap.put("city", dredgeBill4Qsdj.getCityName()); // 城市
		paramMap.put("comment", db.getContent()); // 客户备注
		paramMap.put("orderName", "解放区用户"); // 客户名称
		
		
		httpUtil.addParameter(paramMap);
		
		logger.info("push paramMap is: " + paramMap);
		String sign = generateSignature(paramMap, getAppSecret());
		logger.info("push sign is: " + sign);
		httpUtil.addParameter("sign", sign);
		
		String postResult = null;
		try {
			postResult = httpUtil.post(pushNewOrderURL);
			logger.info("push new order result is: " + postResult);
		} catch (HttpException e) {
			logger.info(e.getMessage(), e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.info(e.getMessage(), e);
			e.printStackTrace();
		}
		
		return postResult;
	}

	public String payOrder(String orderNumber, double price) throws HttpException, IOException {
		return null;
	}
	
	public String queryOrderStatus(String orderNumber) throws HttpException, IOException {
		return null;
	}

	public HttpUtil getHttpUtil() {
		return new HttpUtil();
	}
	
	public void qryProductList() throws HttpException, IOException{
		AbstractServiceOrderPusher serviceOrderPusher = new QsdjServiceOrderPusher();
		HttpUtil httpUtil = new HttpUtil();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accessKey", serviceOrderPusher.getAppKey());
		paramMap.put("version", "v1.0");
		paramMap.put("pageNumber", "1");
		paramMap.put("pageSize", "20");
		httpUtil.addParameter(paramMap);
		
		String sign = serviceOrderPusher.generateSignature(paramMap, serviceOrderPusher.getAppSecret());
		httpUtil.addParameter("sign", sign);
		paramMap.put("sign", sign);

		serviceOrderPusher.verifySignature(paramMap, serviceOrderPusher.getAppSecret());
		
		String postResult = httpUtil.post(serviceOrderPusher.getBaseURL() + "/v1/open/order/queryProductList");
		System.out.println(postResult);
	}
	
	public static void main(String[] args) throws HttpException, IOException {
		//QsdjServiceOrderPusher serviceOrderPusher = new QsdjServiceOrderPusher();
		//serviceOrderPusher.qryProductList();
		
		 //serviceOrderPusher.pushNewOrder(null);
		// serviceOrderPusher.payOrder(); 
		
		//serviceOrderPusher.queryOrderStatus("SC147850323661800001"); 
		 
		 System.out.println("\u53c2\u6570pt_order_id\u4e0d\u80fd\u4e3a\u7a7a");
		 System.out.println("\u53c2\u6570product_id\u4e0d\u80fd\u4e3a\u7a7a");
		 System.out.println("\u4ea7\u54c1\u5df2\u7ecf\u4e0b\u67b6\u6216\u4e0d\u5b58\u5728\u6b64\u4ea7\u54c1");
	}

	@Override
	public String getAppKey() {
		QSDJPushParamParser qsdjPushParamParser = (QSDJPushParamParser) CnfantasiaCommbusiUtil.getBeanManager("qsdjPushParamParser");
		QSDJPushParam sfdjPushParam = qsdjPushParamParser.parseParamValue();
		return sfdjPushParam.getKey();
//		return "224705d54179470094f74619d73aa580"; //测试
//		return "9e80965345e6485386d6fa0cde9014ec"; //生产
	}

	@Override
	public String getAppSecret() {
		QSDJPushParamParser qsdjPushParamParser = (QSDJPushParamParser) CnfantasiaCommbusiUtil.getBeanManager("qsdjPushParamParser");
		QSDJPushParam sfdjPushParam = qsdjPushParamParser.parseParamValue();
		return sfdjPushParam.getSecret();
		//return "a6042c52-f263-4661-b083-acb5997b3256";//测试
		//return "b9e48bfff1884acab25658b0938b66f6";//生产
	}

	@Override
	public String getBaseURL() {
		QSDJPushParamParser qsdjPushParamParser = (QSDJPushParamParser) CnfantasiaCommbusiUtil.getBeanManager("qsdjPushParamParser");
		QSDJPushParam sfdjPushParam = qsdjPushParamParser.parseParamValue();
		return sfdjPushParam.getBaseURL();
		//return "http://sandbox.uyess.com/jiefangqu";//测试
		//return "http://120.76.75.136:88/ha-thirdpart/";//生产的
	}
}
