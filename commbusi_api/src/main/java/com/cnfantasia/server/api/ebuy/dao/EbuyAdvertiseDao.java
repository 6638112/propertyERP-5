package com.cnfantasia.server.api.ebuy.dao;

import com.cnfantasia.server.api.ebuy.entity.EbuyAdvertise;
import com.cnfantasia.server.api.ebuy.entity.EbuyAdvertiseWithRela;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.ebuyFightSupplyMerchant.entity.EbuyFightSupplyMerchant;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EbuyAdvertiseDao extends AbstractBaseDao implements IEbuyAdvertiseDao{
	
	@Override
	public List<EbuyAdvertise> getEbuyAdvertiseList(String code, Integer version) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("code", code);
		tmpMap.put("version", version);
		return sqlSession.selectList("ebuyAdvertise.select_Advertise_List_old", tmpMap);
	}
	
	@Override
	public List<EbuyAdvertiseWithRela> getEbuyAdvertiseList(Map<String, Object> paramMap) {
		return sqlSession.selectList("ebuyAdvertise.select_Advertise_List", paramMap);
	}

	@Override
	public EbuyAdvertise getMainPromoteAdvertise(Map paramMap) {
		return sqlSession.selectOne("ebuyAdvertise.select_Main_Promote_Advertise", paramMap);
	}

	/**
	 * 查询有拼购商品的自提点
	 * @return
	 */
	@Override
	public List<EbuyFightSupplyMerchant> queryFightGroupProductZiti(){
		return sqlSession.selectList("ebuyAdvertise.queryFightGroupProductZiti");
	}
	
	/**
	 * 查询有推广商品的banner
	 * 
	 * @return
	 */
	@Override
	public List<BigInteger> queryPromoteProductAdIds(){
		return sqlSession.selectList("ebuyAdvertise.queryPromoteProductAdIds");
	}
}
