package com.cnfantasia.server.api.login.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import com.cnfantasia.server.api.dredge.constant.DredgeConstant;
import com.cnfantasia.server.api.login.callable.IAddOtherInfoAfterRegister;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.dredgeWorker.entity.DredgeWorker;
import com.cnfantasia.server.domainbase.dredgeWorker.service.DredgeWorkerBaseService;

/**
 * 师傅端注册时，添加额外的师傅信息
 * @author wenfq 2015-08-10
 * 
 */
public class AddMasterInfoAfterRegister implements IAddOtherInfoAfterRegister {

	@Override
	public boolean addOtherInfo(BigInteger userId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tUserFId", userId);
		paramMap.put("sys0DelState", 0);
		DredgeWorkerBaseService dredgeWorkerBaseService = (DredgeWorkerBaseService) CnfantasiaCommbusiUtil.getBeanManager("dredgeWorkerBaseService");
		if (dredgeWorkerBaseService.getDredgeWorkerByCondition(paramMap).size() > 0) {
			return true;
		}

		IUuidManager uuidManager = (IUuidManager) CnfantasiaCommbusiUtil.getBeanManager("uuidManager");
		DredgeWorker dredgeWorker = new DredgeWorker();
		dredgeWorker.settUserFId(userId);
		dredgeWorker.setAuditStatus(DredgeConstant.DredgeWorker.DredgeWorker_Status_NoCertificate);
		dredgeWorker.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_dredge_worker));
		dredgeWorker.setSys0AddUser(userId);
		dredgeWorker.setCreateType(DredgeConstant.DredgeWorker.DredgeWorker_CreateType_Regist);
		dredgeWorker.setWorkerLevel(DredgeConstant.DredgeWorkerLevel.Low_Level);
		int udpCount = dredgeWorkerBaseService.insertDredgeWorker(dredgeWorker);
		return udpCount > 0;
	}

}
