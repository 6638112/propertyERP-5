package com.cnfantasia.server.api.ebuyProductTemp.importter;

import java.io.IOException;

import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domainbase.ebuyProductParametersTemp.dao.EbuyProductParametersTempBaseDao;
import com.cnfantasia.server.ms.ebuyProductTemp.dao.EbuyProductTempDao;

/**
 * 商品数据导入接口
 * 
 * @author wenfq 2004-12-31
 * 
 */
public abstract class ProductImporterBase implements Runnable {
	protected IUuidManager uuidManager;
	protected EbuyProductTempDao ebuyProductTempDao;
	protected EbuyProductParametersTempBaseDao ebuyProductParametersTempBaseDao;
	protected String serverRealPath;
	
	public ProductImporterBase() {
		// TODO Auto-generated constructor stub
	}

	public IUuidManager getUuidManager() {
		return uuidManager;
	}

	public EbuyProductTempDao getEbuyProductTempDao() {
		return ebuyProductTempDao;
	}

	public EbuyProductParametersTempBaseDao getEbuyProductParametersTempBaseDao() {
		return ebuyProductParametersTempBaseDao;
	}

	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	public void setEbuyProductTempDao(EbuyProductTempDao ebuyProductTempBaseDao) {
		this.ebuyProductTempDao = ebuyProductTempBaseDao;
	}

	public void setEbuyProductParametersTempBaseDao(EbuyProductParametersTempBaseDao ebuyProductParametersTempBaseDao) {
		this.ebuyProductParametersTempBaseDao = ebuyProductParametersTempBaseDao;
	}

	public ProductImporterBase(IUuidManager uuidManager, EbuyProductTempDao ebuyProductTempBaseDao,
			EbuyProductParametersTempBaseDao ebuyProductParametersTempBaseDao, String serverRealPath) {
		super();
		this.uuidManager = uuidManager;
		this.ebuyProductTempDao = ebuyProductTempBaseDao;
		this.ebuyProductParametersTempBaseDao = ebuyProductParametersTempBaseDao;
		this.serverRealPath = serverRealPath;
	}

	/**
	 * 执行导入任务
	 * 
	 * @throws IOException
	 */
	public abstract void executeImportTask() throws IOException;
}
