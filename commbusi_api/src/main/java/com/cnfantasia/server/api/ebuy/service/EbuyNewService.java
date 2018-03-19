package com.cnfantasia.server.api.ebuy.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.server.api.ebuy.dao.IEbuyDao;
import com.cnfantasia.server.api.ebuy.dao.IEbuyNewDao;
import com.cnfantasia.server.api.ebuy.entity.EbuyFamilyFavor;
import com.cnfantasia.server.api.ebuy.entity.EbuyFightGroups;
import com.cnfantasia.server.api.ebuy.entity.EbuyHomeProd;
import com.cnfantasia.server.api.ebuy.entity.EbuyProdForLst;
import com.cnfantasia.server.api.ebuy.entity.EbuyProdForLstSales;
import com.cnfantasia.server.api.ebuy.entity.EbuyProdType;
import com.cnfantasia.server.api.ebuy.entity.EbuySalesPromotion;
import com.cnfantasia.server.api.ebuy.entity.EbuyStore;
import com.cnfantasia.server.api.ebuy.entity.ThemeAdvEntity;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.domainbase.ebuyAdvertise.entity.EbuyAdvertise;
import com.cnfantasia.server.domainbase.ebuyAdvertise.service.IEbuyAdvertiseBaseService;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.dao.EbuyDeliveryOrderBaseDao;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;
import com.cnfantasia.server.domainbase.ebuyOrder.dao.EbuyOrderBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyProduct.dao.IEbuyProductBaseDao;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;

public class EbuyNewService implements IEbuyNewService {
	
	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	
	private IEbuyNewDao ebuyNewDao;
	
	private IEbuyDao ebuyDao;

	private IEbuyProductBaseDao ebuyProductBaseDao;

	private IEbuyAdvertiseBaseService ebuyAdvertiseBaseService;
	public void setEbuyAdvertiseBaseService(IEbuyAdvertiseBaseService ebuyAdvertiseBaseService) {
		this.ebuyAdvertiseBaseService = ebuyAdvertiseBaseService;
	}

	//加上此事务注解，由于只读数据库，不需要事务
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<EbuyHomeProd> getEbuyHomeProdList(int homeType, BigInteger groupBuildId, int prodCount, int sex) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("homeType", homeType);
		if(groupBuildId != null && groupBuildId.longValue() != -1) {
			paramMap.put("groupBuildId", groupBuildId);//登录的显示所有此小区的商品
		} else {
			paramMap.put("supplyType", 1);//未登录只显示自营商品
		}
		paramMap.put("sex", sex);
		
		List<EbuyHomeProd> homeProdList = ebuyNewDao.getEbuyHomeProdList(paramMap);
		
		//由于取每个分类的prodCount在SQL上处理会让语句非常的复杂且影响效率。所以直接在这里通过处理。
		for(int i = homeProdList.size() - 1; i >=0; i--) {
			EbuyHomeProd homeProd = homeProdList.get(i);
			if(homeProd.getEbuyProdForLstList().size() > prodCount) {
				homeProd.setEbuyProdForLstList(homeProd.getEbuyProdForLstList().subList(0, prodCount));
			} else if(homeProd.getEbuyProdForLstList().size() < 7 && homeType != 1) {
				homeProdList.remove(i);
			} else if(homeProd.getEbuyProdForLstList().size() < 3 && homeType == 1) {
				homeProdList.remove(i);
			}
		}
		
		return homeProdList;
	}
	
	public List<EbuyProdForLst> getEbuyHomeProdMoreList(Map<String, Object> paramMap, PageModel pageModel) {
		return ebuyNewDao.getEbuyHomeProdMoreList(paramMap, pageModel);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public EbuyHomeProd getEbuyHomeProd(int homeType, BigInteger groupBuildId, int prodCount, int sex) {
		List<EbuyHomeProd> homeProdList = getEbuyHomeProdList(homeType, groupBuildId, prodCount, sex);
		if(homeProdList.size() > 0) {
			EbuyHomeProd homeProd = homeProdList.get(0);
			if(homeProd.getEbuyProdForLstList().size() > prodCount) {
				homeProd.setEbuyProdForLstList(homeProd.getEbuyProdForLstList().subList(0, prodCount));
			}
			return homeProd;
		}
		return null;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<EbuyStore> getEbuyStoreList(Map<String, Object> paramMap, PageModel pageModel) {
		return ebuyNewDao.getEbuyStoreList(paramMap, pageModel);
	}

	public EbuyStore getEbuyStoreInfo(BigInteger ebuySupplyMerchantId) {
		return ebuyNewDao.getEbuyStoreInfo(ebuySupplyMerchantId);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<EbuyProdForLstSales> getProdList(Map<String, Object> paramMap, PageModel pageModel) {
		return ebuyNewDao.getProdList(paramMap, pageModel);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<EbuyFamilyFavor> getEbuyFamilyFavorList(Map<String, Object> paramMap) {
		List<EbuyFamilyFavor> familyFavorList = ebuyNewDao.getEbuyFamilyFavorList(paramMap);
		List<EbuyProdForLst> prodList = ebuyNewDao.getFamilyFavorMoreList();
		
		
		int i = 0;
		for(EbuyFamilyFavor favor : familyFavorList) {
			//如果家人喜欢没有相关的商品，则从首页运营主题取4个商品放到家人喜欢中。
			if(favor.getEbuyProdForLstList() == null || favor.getEbuyProdForLstList().size() == 0) {
				favor.setEbuyProdForLstList(prodList.subList(i, i+4));
				i = i + 4;
			}
			
			//设置家人的头相等信息
			for(UserSimpleEntity user : (List<UserSimpleEntity>) paramMap.get("familyUserList")) {
				if(favor.getId().equals(user.getId().longValue())) {
					favor.setIsAdmin(user.getExt_room_isAdmin());
					favor.setImgprofile(user.getImgprofile());
				}
			}
		}
		
		return familyFavorList;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Long getProductIdByShelfId(Long shelfId) {
		return ebuyNewDao.getProductIdByShelfId(shelfId);
	}
	
	@Override
	public List<EbuyProdType> getStoreProdTypes(Map<String, Object> paramMap) {
		return ebuyNewDao.getStoreProdTypes(paramMap);
	}
	
	@Override
	public List<EbuyProdForLst> searchHomeProdListByPage(Map<String, Object> paramMap, PageModel pageModel) {
		return ebuyNewDao.searchHomeProdListByPage(paramMap, pageModel);
	}
	
//	public List<EbuyProdForLst> getFamilyFavorMoreList(Long userId) {
//		return ebuyNewDao.getFamilyFavorMoreList(userId);
//	}
	
	/**
	 * 用来手动调用，清除ebuyNew下的所有缓存数据
	 */
	public void cleanCache() {
		ebuyNewDao.cleanCache();
	}
	
	
	public void setEbuyNewDao(IEbuyNewDao ebuyNewDao) {
		this.ebuyNewDao = ebuyNewDao;
	}

	@Resource
	EbuyDeliveryOrderBaseDao ebuyDeliveryOrderBaseDao;
	@Resource
	EbuyOrderBaseDao ebuyOrderBaseDao;

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public void confirmReceived(BigInteger userId, BigInteger orderId, BigInteger deliveryOrderId) {
		EbuyDeliveryOrder ebuyDeliveryOrder = new EbuyDeliveryOrder();
		ebuyDeliveryOrder.setId(deliveryOrderId);
		ebuyDeliveryOrder.setStatus(EbuyDict.EbuyDeliveryOrder_Status.QueRenShowHuo);
		ebuyDeliveryOrder.setReceiveTime(DateUtil.formatSecond.get().format(new Date()));
		ebuyDeliveryOrder.setSys0UpdUser(userId);
		ebuyDeliveryOrderBaseDao.updateEbuyDeliveryOrder(ebuyDeliveryOrder);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tEbuyOrderFId", orderId);
		paramMap.put("sys0DelState", 0);
		int allDeliveryOrderCount = ebuyDeliveryOrderBaseDao.selectEbuyDeliveryOrderCount(paramMap, false);

		paramMap.put("status", EbuyDict.EbuyDeliveryOrder_Status.QueRenShowHuo);
		int allQueRenShowHuoCount = ebuyDeliveryOrderBaseDao.selectEbuyDeliveryOrderCount(paramMap, false);

		if (allDeliveryOrderCount == allQueRenShowHuoCount) {//所有配送单都已经完成确认收货，订单也要标记为“待评价”
			EbuyOrder ebuyOrder = new EbuyOrder();
			ebuyOrder.setId(orderId);
			ebuyOrder.setStatus(EbuyDict.EbuyOrder_Status.DaiPingJia);
			ebuyOrder.setSys0UpdUser(userId);
			ebuyOrderBaseDao.updateEbuyOrder(ebuyOrder);
		}
		
		ebuyDao.addProductSellCountByOrderId(orderId, userId,null,null);
	}
	
	public EbuySalesPromotion getNewUserType(Map<String, Object> paramMap) {
		return ebuyNewDao.getNewUserType(paramMap);
	}
	
	public BigInteger selectCityIdByName(Map<String, Object> paramMap) {
		return ebuyNewDao.selectCityIdByName(paramMap);
	}

	public List<EbuyProdForLstSales> selectAdvertiseThemeProduct(BigInteger advId) {
		return ebuyNewDao.selectAdvertiseThemeProduct(advId);
	}

	/**
	 * 查询拼购商品列表
	 * @param fightStatus 0 未开始 1 进行中
	 * @return
     */
	public List<EbuyFightGroups> getFightProducts(String fightStatus, BigInteger gbId, PageModel pageModel) {
		Map<String, Object> param = new HashMap<String, Object>();
		if (fightStatus != null) {
			param.put("fightStatus", fightStatus);
		}
		if (gbId != null) {
			param.put("groupBuildingId", gbId);
		}
		if (pageModel != null) {
			param.put("begin", pageModel.getBegin());
			param.put("length", pageModel.getLength());
		}
		return ebuyNewDao.getFightProducts(param);
	}

	public EbuyFightGroups getHotFightProductSelling(BigInteger gbId) {
		Map<String, Object> param = new HashMap<String, Object>();
		if (gbId != null) {
			param.put("groupBuildingId", gbId);
		}
		return ebuyNewDao.getHotFightProductSelling(param);
	}

	@Override
	public ThemeAdvEntity getThemeAdvDetail(BigInteger advId, Integer appType) {
		EbuyAdvertise advertise = ebuyAdvertiseBaseService.getEbuyAdvertiseBySeqId(advId);
		ThemeAdvEntity entity = null;
		if ("DREDGE_THEME".equals(advertise.getCode())) {
			entity = ebuyNewDao.getThemeDredgeAdvDetail(advId);
		} else {
			entity = ebuyNewDao.getThemeEbuyAdvDetail(advId, appType);
		}
		if (entity != null) {
			entity.setAdvTitle(advertise.getTittle());
		}
		return entity;
	}

	@Override
	public String getEbuyThemeDescByShelfId(BigInteger shelfId, Integer type) {
		return ebuyNewDao.getEbuyThemeDescByShelfId(shelfId, type);
	}

	@Override
	public List<EbuyProduct> getHotSaleProduct4Store(BigInteger storeId) {
		EbuyProduct epQry = new EbuyProduct();
		epQry.settSupplyMerchantFId(storeId);
		epQry.setIsHotSale(EbuyDict.Product_Hot_Sale_Status.Hot_Sale);
		return ebuyProductBaseDao.selectEbuyProductByCondition(MapConverter.toMap(epQry), false);
	}

	public void setEbuyDao(IEbuyDao ebuyDao) {
		this.ebuyDao = ebuyDao;
	}

	@Override
	public String checkOrderPrdtBeforePay(BigInteger orderId) {
		String checkResult = null;
		checkResult = ebuyNewDao.selectExpiredProductName(orderId);
		return checkResult == null ? "通过校验" : checkResult + "已过期，请重新下单购买";
	}

}
