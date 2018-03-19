package com.cnfantasia.server.ms.ebuyProductTemp.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.NumberUtils;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyProduct.dao.IEbuyProductBaseDao;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.domainbase.ebuyProductParameters.dao.IEbuyProductParametersBaseDao;
import com.cnfantasia.server.domainbase.ebuyProductParameters.entity.EbuyProductParameters;
import com.cnfantasia.server.domainbase.ebuyProductPic.dao.IEbuyProductPicBaseDao;
import com.cnfantasia.server.domainbase.ebuyProductPic.entity.EbuyProductPic;
import com.cnfantasia.server.domainbase.ebuyProductShelf.dao.IEbuyProductShelfBaseDao;
import com.cnfantasia.server.domainbase.ebuyProductShelf.entity.EbuyProductShelf;
import com.cnfantasia.server.domainbase.ebuyProductTemp.entity.EbuyProductTemp;
import com.cnfantasia.server.domainbase.ebuyProductTemp.service.EbuyProductTempBaseService;
import com.cnfantasia.server.ms.ebuyProductTemp.dao.IEbuyProductTempDao;
import com.cnfantasia.server.ms.ebuyProductTemp.entity.EbuyProductTempEntity;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;

public class EbuyProductTempService extends EbuyProductTempBaseService implements IEbuyProductTempService {
	IEbuyProductTempDao ebuyProductTempDao;
	IEbuyProductBaseDao ebuyProductBaseDao;
	IEbuyProductPicBaseDao ebuyProductPicBaseDao;
	
	public void setEbuyProductPicBaseDao(
			IEbuyProductPicBaseDao ebuyProductPicBaseDao) {
		this.ebuyProductPicBaseDao = ebuyProductPicBaseDao;
	}

	@Resource
	IEbuyProductShelfBaseDao ebuyProductShelfBaseDao;

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
		p.setPurchasePrice(pte.getPriceDiscount());
		double priceDiscount = pte.getPriceDiscount()
				* org.apache.commons.lang.math.NumberUtils.toDouble(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.ProductPriceSellRate), 1.05);
		double price = pte.getPriceDiscount()
				* org.apache.commons.lang.math.NumberUtils.toDouble(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.ProductPriceMarketRate), 1.1);
		if("egu".equalsIgnoreCase(pte.getFromWhere())) { //依谷网销售价和采购价是一样的
			p.setPriceDiscount(pte.getPriceDiscount());
			p.setPrice(NumberUtils.doubleToLong(priceDiscount));
		} else {
			p.setPriceDiscount(NumberUtils.doubleToLong(priceDiscount));
			p.setPrice(NumberUtils.doubleToLong(price));
		}
		String now = DateUtil.formatSecond.get().format(new Date());
		p.setUpShelfTime(now);//上架时间
		p.setCreateTime(now);
		p.setPicBase(pte.getPicBase());
		p.setStatus(0);//默认同步后就上架
		p.setStatusAudit(5);//默认值, 审核通过
		p.settEbuyProductTypeFId(pte.gettEbuyProductTypeFId());
		p.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product));
		p.setPointType(1);
		p.setSpecialProductType(1);
		p.setSelNum(BigInteger.ZERO);
		p.setLeftCount(new BigInteger("99"));
		p.setWlappType(1L);//默认是解放区的商品
		updateCount += ebuyProductBaseDao.insertEbuyProduct(p);
		pte.setIsSync(1);
		updateCount += ebuyProductTempDao.updateEbuyProductTemp(pte);
		
		{//商品上架，需要上架到货架上了 added by wenfq 2015-11-10
			EbuyProductShelf ps = new EbuyProductShelf();
//			ps.setId(p.getId());//伟坚之前设置的就是跟商品的id一样的
			ps.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product));
			ps.settEbuyProductId(p.getId());
			ps.setUpShelfState(0);
			ps.settEbuyProductTypeId(p.gettEbuyProductTypeFId());
			ps.setPrice(p.getPrice());
			ps.setPriceDiscount(p.getPriceDiscount());
			ps.setApplyTime(now);
			ps.setHandTime(now);
			ebuyProductShelfBaseDao.insertEbuyProductShelf(ps);
		}
		{
			EbuyProductPic ebuyProductPic = new EbuyProductPic();
			ebuyProductPic.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product_pic));
			ebuyProductPic.settEbuyProductFId(p.getId());
			ebuyProductPic.setUrlBig(pte.getPicBase());
			ebuyProductPic.setUrlMini(pte.getPicBase());
			ebuyProductPic.setSys0DelState(0);
			ebuyProductPicBaseDao.insertEbuyProductPic(ebuyProductPic);
		}

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
