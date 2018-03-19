package com.cnfantasia.server.api.ebuyInvoice.service;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyInvoice.dao.IEbuyInvoiceBaseDao;
import com.cnfantasia.server.domainbase.ebuyInvoice.entity.EbuyInvoice;
import com.cnfantasia.server.domainbase.ebuyInvoice.service.EbuyInvoiceBaseService;

public class EbuyInvoiceService extends EbuyInvoiceBaseService implements IEbuyInvoiceService {

	private IUuidManager uuidManager;

	public IUuidManager getUuidManager() {
		return uuidManager;
	}

	private IEbuyInvoiceBaseDao ebuyInvoiceBaseDao;

	public void setEbuyInvoiceBaseDao(IEbuyInvoiceBaseDao ebuyInvoiceBaseDao) {
		this.ebuyInvoiceBaseDao = ebuyInvoiceBaseDao;
	}

	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}


	@Override
	public int insertEbuyInvoice(EbuyInvoice ebuyInvoice) {
		ebuyInvoice.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_invoice));
		return ebuyInvoiceBaseDao.insertEbuyInvoice(ebuyInvoice);
	}

	public List<EbuyInvoice> getEbuyInvoiceByCondition(Map<String, Object> paramMap) {
		return ebuyInvoiceBaseDao.selectEbuyInvoiceByCondition(paramMap, false);
	}

	public int updateEbuyInvoice(EbuyInvoice ebuyInvoice) {
		return ebuyInvoiceBaseDao.updateEbuyInvoice(ebuyInvoice);
	}

	public EbuyInvoice getEbuyInvoiceBySeqId(java.math.BigInteger id) {
		return ebuyInvoiceBaseDao.selectEbuyInvoiceBySeqId(id);
	}
}
