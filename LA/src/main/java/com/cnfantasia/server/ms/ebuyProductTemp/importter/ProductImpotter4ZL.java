package com.cnfantasia.server.ms.ebuyProductTemp.importter;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domainbase.ebuyProductParametersTemp.dao.EbuyProductParametersTempBaseDao;
import com.cnfantasia.server.ms.ebuyProductTemp.dao.EbuyProductTempDao;

/**
 * 中粮的商品数据导入实现
 * 
 * @author wenfq 2014-12-31
 * 
 */
public class ProductImpotter4ZL extends ProductImporterBase {
	public ProductImpotter4ZL(IUuidManager uuidManager, EbuyProductTempDao ebuyProductTempDao,
			EbuyProductParametersTempBaseDao ebuyProductParametersTempBaseDao, String serverRealPath) {
		super(uuidManager, ebuyProductTempDao, ebuyProductParametersTempBaseDao, serverRealPath);
	}

	private Log logger = LogFactory.getLog(getClass());

	@Override
	public void run() {
		try {
			executeImportTask();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	@Override
	public void executeImportTask() throws IOException {
		//TODO 
	}
}
