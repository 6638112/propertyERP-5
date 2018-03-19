package com.cnfantasia.server.ms.limitBuyActivity.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.ms.limitBuyActivity.entity.LimitBuyActivityDetailDto;
import com.cnfantasia.server.ms.limitBuyActivity.entity.LimitBuyActivityListDto;

/**
 * 限时购
 * 
 * @author liyulin
 * @version 1.0 2016年12月29日 下午2:11:03
 */
public class LimitBuyActivityDao extends AbstractBaseDao implements ILimitBuyActivityDao {
	
	/**
	 * 限时购列表数据查询
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<LimitBuyActivityListDto> selectLimitBuyActivityForPage(Map<String, Object> paramMap) {
		return sqlSession.selectList("limitBuyActivity.selectLimitBuyActivityForPage", paramMap);
	}

	/**
	 * 限时购列表数据条数查询
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public int selectLimitBuyActivityForCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("limitBuyActivity.selectLimitBuyActivityForCount", paramMap);
	}

	/***
	 * 限时购详情
	 * 
	 * @param ibaId
	 * @return
	 */
	@Override
	public LimitBuyActivityDetailDto selectLimitBuyActivityForDetail(BigInteger ibaId) {
		return sqlSession.selectOne("limitBuyActivity.selectLimitBuyActivityForDetail", ibaId);
	}

	/**
	 * 限时购详情基本信息
	 * 
	 * @param productId
	 * @return
	 */
	@Override
	public LimitBuyActivityDetailDto selectLimitBuyActivityForBaseDetail(BigInteger productId) {
		return sqlSession.selectOne("limitBuyActivity.selectLimitBuyActivityForBaseDetail", productId);
	}

	/**
	 * 商品下架后，限时购也要同时下架
	 * @param ebuyProducts 刚下架的商品
	 * @return
	 */
	@Override
	public int downShelf(List<EbuyProduct> ebuyProducts) {
		return sqlSession.update("limitBuyActivity.downShelf", ebuyProducts);
	}

}
