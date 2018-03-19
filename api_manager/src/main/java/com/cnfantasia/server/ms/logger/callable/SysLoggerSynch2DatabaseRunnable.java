package com.cnfantasia.server.ms.logger.callable;

import java.util.List;
import java.util.concurrent.Callable;

import com.cnfantasia.server.ms.logger.entity.ManagerLogger;
import com.cnfantasia.server.ms.logger.service.ISysLoggerMsService;

public class SysLoggerSynch2DatabaseRunnable implements Callable<List<ManagerLogger>>{
	private ISysLoggerMsService sysLoggerService;
	private List<ManagerLogger> logCatcheList;
	public SysLoggerSynch2DatabaseRunnable(ISysLoggerMsService sysLoggerService,List<ManagerLogger> logCatcheList){
		this.sysLoggerService = sysLoggerService;
		this.logCatcheList = logCatcheList;
	}
	
	@Override
	public List<ManagerLogger> call() throws Exception {
		List<ManagerLogger> resList = sysLoggerService.synch2Database(logCatcheList);
		return resList;
	}
}
