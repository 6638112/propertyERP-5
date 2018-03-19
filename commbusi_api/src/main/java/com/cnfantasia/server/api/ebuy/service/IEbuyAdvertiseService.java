package com.cnfantasia.server.api.ebuy.service;

import com.cnfantasia.server.api.ebuy.entity.EbuyAdvertise;
import com.cnfantasia.server.domainbase.ebuyFightSupplyMerchant.entity.EbuyFightSupplyMerchant;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;


public interface IEbuyAdvertiseService {
	
	public List<EbuyAdvertise> getEbuyAdvertiseList(String code, Integer version);
	
	public List<EbuyAdvertise> getEbuyAdvertiseList(Map<String, Object> paramMap);

	public EbuyAdvertise getMainPromoteAdvertise(Map paramMap);

	/**
	 * 查询有拼购商品的自提点
	 * 
	 * @return
	 */
	public List<EbuyFightSupplyMerchant> queryFightGroupProductZiti();
	
	/**
	 * 查询有推广商品的banner
	 * 
	 * @return
	 */
	public List<BigInteger> queryPromoteProductAdIds();
}
