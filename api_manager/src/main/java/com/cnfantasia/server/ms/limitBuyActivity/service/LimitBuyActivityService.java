package com.cnfantasia.server.ms.limitBuyActivity.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.domainbase.limitBuyActivity.service.LimitBuyActivityBaseService;
import com.cnfantasia.server.ms.limitBuyActivity.dao.ILimitBuyActivityDao;
import com.cnfantasia.server.ms.limitBuyActivity.entity.LimitBuyActivityDetailDto;
import com.cnfantasia.server.ms.limitBuyActivity.entity.LimitBuyActivityListDto;

/**
 * 限时购
 * 
 * @author liyulin
 * @version 1.0 2016年12月29日 下午2:08:28
 */
public class LimitBuyActivityService extends LimitBuyActivityBaseService implements ILimitBuyActivityService {
	
	private ILimitBuyActivityDao limitBuyActivityDao;
	
	public void setLimitBuyActivityDao(ILimitBuyActivityDao limitBuyActivityDao) {
		this.limitBuyActivityDao = limitBuyActivityDao;
	}
	
	/**
	 * 限时购列表数据查询
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<LimitBuyActivityListDto> selectLimitBuyActivityForPage(Map<String, Object> paramMap){
		return limitBuyActivityDao.selectLimitBuyActivityForPage(paramMap);
	}
	
	/**
	 * 限时购列表数据条数查询
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public int selectLimitBuyActivityForCount(Map<String, Object> paramMap){
		return limitBuyActivityDao.selectLimitBuyActivityForCount(paramMap);
	}
	
	/***
	 * 限时购详情
	 * 
	 * @param ibaId
	 * @return
	 */
	@Override
	public LimitBuyActivityDetailDto selectLimitBuyActivityForDetail(BigInteger ibaId){
		return limitBuyActivityDao.selectLimitBuyActivityForDetail(ibaId);
	}

	/**
	 * 限时购详情基本信息
	 * 
	 * @param productId
	 * @return
	 */
	@Override
	public LimitBuyActivityDetailDto selectLimitBuyActivityForBaseDetail(BigInteger productId) {
		return limitBuyActivityDao.selectLimitBuyActivityForBaseDetail(productId);
	}
	
	
	/**
	 * 商品下架后，限时购也要同时下架
	 * @param ebuyProducts 刚下架的商品
	 * @return
	 */
	public int downShelf(List<EbuyProduct> ebuyProducts) {
		return limitBuyActivityDao.downShelf(ebuyProducts);
	}
}
