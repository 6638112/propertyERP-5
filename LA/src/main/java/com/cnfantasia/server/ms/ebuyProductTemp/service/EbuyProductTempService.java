package com.cnfantasia.server.ms.ebuyProductTemp.service;

import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyProduct.dao.IEbuyProductBaseDao;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.domainbase.ebuyProductParameters.dao.IEbuyProductParametersBaseDao;
import com.cnfantasia.server.domainbase.ebuyProductParameters.entity.EbuyProductParameters;
import com.cnfantasia.server.domainbase.ebuyProductTemp.entity.EbuyProductTemp;
import com.cnfantasia.server.domainbase.ebuyProductTemp.service.EbuyProductTempBaseService;
import com.cnfantasia.server.ms.ebuyProductTemp.dao.IEbuyProductTempDao;
import com.cnfantasia.server.ms.ebuyProductTemp.entity.EbuyProductTempEntity;
import com.cnfantasia.server.ms.pub.utils.DateUtil;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class EbuyProductTempService extends EbuyProductTempBaseService implements IEbuyProductTempService {
	IEbuyProductTempDao ebuyProductTempDao;
	IEbuyProductBaseDao ebuyProductBaseDao;

	public void setEbuyProductBaseDao(IEbuyProductBaseDao ebuyProductBaseDao) {
		this.ebuyProductBaseDao = ebuyProductBaseDao;
	}

	public void setEbuyProductParametersBaseDao(IEbuyProductParametersBaseDao ebuyProductParametersBaseDao) {
		this.ebuyProductParametersBaseDao = ebuyProductParametersBaseDao;
	}

	IEbuyProductParametersBaseDao ebuyProductParametersBaseDao;

	private IUuidManager uuidManager;


	public void setEbuyProductTempDao(IEbuyProductTempDao ebuyProductTempDao) {
		this.ebuyProductTempDao = ebuyProductTempDao;
	}

	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	@Override
	public int getProductList_forCount(Map<String, Object> paramMap) {
		return ebuyProductTempDao.getProductList_forCount(paramMap);
	}

	@Override
	public List<EbuyProductTemp> getProductList_forPage(Map<String, Object> paramMap) {
		return ebuyProductTempDao.getProductList_forPage(paramMap);
	}

	@Override
	@Transactional
	public int sync(String ptId) {
		int updateCount = 0;
		EbuyProductTempEntity pte = ebuyProductTempDao.getProductTempEntityById(ptId);
		EbuyProduct p = new EbuyProduct();
		p.setSrcId(pte.getId());
		p.setName(pte.getName());
		p.settSupplyMerchantFId(pte.gettSupplyMerchantFId());
		p.setPrice(pte.getPrice());
		p.setPriceDiscount(pte.getPriceDiscount());
		p.setCreateTime(DateUtil.formatSecond.format(new Date()));
		p.setPicBase(pte.getPicBase());
		p.setStatus(0);//默认同步后就上架
		p.settEbuyProductTypeFId(pte.gettEbuyProductTypeFId());
		p.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product));
		//p.setPointType(1);//电商商品 TODO 等石头那边发版后，要去掉注释
		//p.setSpecialProductType(1);
		p.setSelNum(BigInteger.ZERO);
		p.setLeftCount(new BigInteger("99"));
		p.setIsPreSell(0);
		updateCount += ebuyProductBaseDao.insertEbuyProduct(p);
		pte.setIsSync(1);
		updateCount += ebuyProductTempDao.updateEbuyProductTemp(pte);

		int paramSize = pte.getPrdtParamter().size();
		if (paramSize == 0)
			return updateCount;
		
		List<EbuyProductParameters> ppList = new ArrayList<EbuyProductParameters>(paramSize);
		List<BigInteger> ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product_parameters, paramSize);
		for(int i = 0; i < paramSize; i++){
			EbuyProductParameters pp = new EbuyProductParameters();
			pp.setId(ids.get(i));
			pp.settEbuyProductFId(p.getId());
			pp.settPropName(pte.getPrdtParamter().get(i).gettPropName());
			pp.settPropValue(pte.getPrdtParamter().get(i).gettPropValue());
			ppList.add(pp);
		}
		updateCount += ebuyProductParametersBaseDao.insertEbuyProductParametersBatch(ppList);

		return updateCount;
	}

}
