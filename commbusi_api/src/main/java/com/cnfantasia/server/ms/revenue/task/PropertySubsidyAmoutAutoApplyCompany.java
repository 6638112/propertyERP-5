package com.cnfantasia.server.ms.revenue.task;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.server.api.common.constant.Lock;
import com.cnfantasia.server.api.common.dao.ICommonLockDao;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.ms.revenue.constant.RevenueConstant;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.dao.IRevenueDao;
import com.cnfantasia.server.ms.revenue.entity.RevenueApplyParam;
import com.cnfantasia.server.ms.revenue.service.IRevenueService;

/**
 * 物业补贴自动发起提款申请,缴费时间当天或者前一天发起
* Filename:    PropertySubsidyAmoutAutoApplyCompany.java
* @version:    1.0.0
* Create at:   2015年11月24日 下午3:42:35
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月24日       shiyl             1.0             1.0 Version
 */
public class PropertySubsidyAmoutAutoApplyCompany implements ISynTask{
	private Log logger = LogFactory.getLog(getClass());
	
	private IRevenueService revenueService;
	public void setRevenueService(IRevenueService revenueService) {
		this.revenueService = revenueService;
	}
	
	private IRevenueDao revenueDao;
	public void setRevenueDao(IRevenueDao revenueDao) {
		this.revenueDao = revenueDao;
	}
	
	private ICommonLockDao commonLockDao;
	public void setCommonLockDao(ICommonLockDao commonLockDao) {
		this.commonLockDao = commonLockDao;
	}
	
	/**
	 * update by Liyl 2016-07-13
	 */
	@Override
	public Integer execTask(){
		// 角色类型
		// 注：PropertyCompany、PCManagement要特殊处理
		Integer[] roleTypes = new Integer[]{RevenueDict.MiniRoleType.PCManagement, RevenueDict.MiniRoleType.PropertyCompany, RevenueDict.MiniRoleType.PropertyAgent};
		// 提款对象类型
		Integer[] goalTypes = new Integer[]{RevenueDict.RevenueProject.RoomValidate,
											RevenueDict.RevenueProject.ServiceOrder,
											RevenueDict.RevenueProject.WuyebaoAmount,
											RevenueDict.RevenueProject.CarFinanceBaoAmout,
											RevenueDict.RevenueProject.MarketAmout,
											RevenueDict.RevenueProject.PropertyRealPayAmout,
											RevenueDict.RevenueProject.CarAmount,
											RevenueDict.RevenueProject.PropertyOtherFee,
											RevenueDict.RevenueProject.FinanceDeduAmount,
											RevenueDict.RevenueProject.CarDeduAmount,
											RevenueDict.RevenueProject.PropertySubsidyAmout,
											RevenueDict.RevenueProject.CarAmountBt};
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		for(Integer roleType:roleTypes){
			paramMap.clear();
			
			if(RevenueDict.MiniRoleType.PropertyCompany.compareTo(roleType)==0 || RevenueDict.MiniRoleType.PCManagement.compareTo(roleType)==0){
				paramMap.put("isPc", true);
				// 物业公司：pcType=ture; 管理处：pcType=false
				paramMap.put("pcType", (RevenueDict.MiniRoleType.PropertyCompany.compareTo(roleType)==0));
				// RevenueDict.MiniRoleType.PCManagement在表t_revenue_signal_amount中没有，所以
				// 当roleType为PropertyCompany和PCManagement时，roleType都为PropertyCompany
				paramMap.put("roleType", RevenueDict.MiniRoleType.PropertyCompany);
			} else if(RevenueDict.MiniRoleType.PropertyAgent.compareTo(roleType)==0){
				paramMap.put("isAgent", true);
				paramMap.put("roleType", roleType);
			}
			
			for(Integer goalType:goalTypes){
				paramMap.put("goalType", goalType);
				List<BigInteger> rsaIds = revenueDao.selectSysApplyToDoList(paramMap);
				if(rsaIds!=null&&rsaIds.size()>0){
					for(BigInteger rsaId:rsaIds){
						execTaskOnePc(rsaId, roleType, goalType);
					}
				}
			}
		}
		
		return null;
	}

	/**
	 * remove by Liyl 2016-07-13
	 * */
	/*@Override
	public Integer execTask() {
		//物业补贴每个月自动提取
//		List<BigInteger> tmpList = revenueDao.selectSysApplyToDoCompanyList();
//		if(tmpList!=null&&tmpList.size()>0){
//			for(BigInteger tmpId:tmpList){
//				execTaskOne(tmpId);
//			}
//		}
		
		//物业补贴的每个月自动提取，按照物业公司设置的提款日
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("isPc", true);
		paramMap.put("roleType", RevenueDict.MiniRoleType.PropertyCompany);
		paramMap.put("goalType", RevenueDict.RevenueProject.PropertySubsidyAmout);
		List<BigInteger> tmpList = revenueDao.selectSysApplyToDoList(paramMap);
		if(tmpList!=null&&tmpList.size()>0){
			for(BigInteger tmpId:tmpList){
				execTaskOnePc(tmpId, RevenueDict.MiniRoleType.PropertyCompany,  RevenueDict.RevenueProject.PropertySubsidyAmout);
			}
		}
		
//		Calendar cal = Calendar.getInstance();
//		String revenueJobDate = CnfantasiaCommbusiUtil.getSysParaValue("revenueJobDate");
//		if(revenueJobDate == null || revenueJobDate.equals("")) {
//			revenueJobDate = "25"; //默认每个月25日生成，而Job是每天凌晨跑
//		}
		//师傅服务收益自动生成提款单
//		if(revenueJobDate.equals(String.valueOf(cal.get(Calendar.DAY_OF_MONTH) - 1))) {
			//师傅服务收益　物业公司自动生成提款单
//			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.clear();
			paramMap.put("isPc", true);
			paramMap.put("roleType", RevenueDict.MiniRoleType.PropertyCompany);
			paramMap.put("goalType", RevenueDict.RevenueProject.ServiceOrder);
			tmpList = revenueDao.selectSysApplyToDoList(paramMap);
			if(tmpList!=null&&tmpList.size()>0){
				for(BigInteger tmpId:tmpList){
					execTaskOnePc(tmpId, RevenueDict.MiniRoleType.PropertyCompany,  RevenueDict.RevenueProject.ServiceOrder);
				}
			}
			
			//师傅服务收益　代理商自动生成提款单
			paramMap.clear();
			paramMap.put("isAgent", true);
			paramMap.put("roleType", RevenueDict.MiniRoleType.PropertyAgent);
			paramMap.put("goalType", RevenueDict.RevenueProject.ServiceOrder);
			tmpList = revenueDao.selectSysApplyToDoList(paramMap);
			if(tmpList!=null&&tmpList.size()>0){
				for(BigInteger tmpId:tmpList){
					execTaskOnePc(tmpId, RevenueDict.MiniRoleType.PropertyAgent,  RevenueDict.RevenueProject.ServiceOrder);
				}
			}
//		}
		
		//物业宝收益自动生成提款单
//		if(revenueJobDate.equals(String.valueOf(cal.get(Calendar.DAY_OF_MONTH) - 1))) {
			//物业公司自动生成提款单
//			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.clear();
			paramMap.put("isPc", true);
			paramMap.put("roleType", RevenueDict.MiniRoleType.PropertyCompany);
			paramMap.put("goalType", RevenueDict.RevenueProject.WuyebaoAmount);
			tmpList = revenueDao.selectSysApplyToDoList(paramMap);
			if(tmpList!=null&&tmpList.size()>0){
				for(BigInteger tmpId:tmpList){
					execTaskOnePc(tmpId, RevenueDict.MiniRoleType.PropertyCompany,  RevenueDict.RevenueProject.WuyebaoAmount);
				}
			}
			
			//代理商自动生成提款单
			paramMap.clear();
			paramMap.put("isAgent", true);
			paramMap.put("roleType", RevenueDict.MiniRoleType.PropertyAgent);
			paramMap.put("goalType", RevenueDict.RevenueProject.WuyebaoAmount);
			tmpList = revenueDao.selectSysApplyToDoList(paramMap);
			if(tmpList!=null&&tmpList.size()>0){
				for(BigInteger tmpId:tmpList){
					execTaskOnePc(tmpId, RevenueDict.MiniRoleType.PropertyAgent,  RevenueDict.RevenueProject.WuyebaoAmount);
				}
			}
//		}
			
			{//停车宝收益自动生成提款单 added by wenfq 2016-06-17
				//物业公司自动生成提款单
				paramMap.clear();
				paramMap.put("isPc", true);
				paramMap.put("roleType", RevenueDict.MiniRoleType.PropertyCompany);
				paramMap.put("goalType", RevenueDict.RevenueProject.CarFinanceBaoAmout);
				tmpList = revenueDao.selectSysApplyToDoList(paramMap);
				if(tmpList!=null&&tmpList.size()>0){
					for(BigInteger tmpId:tmpList){
						execTaskOnePc(tmpId, RevenueDict.MiniRoleType.PropertyCompany,  RevenueDict.RevenueProject.CarFinanceBaoAmout);
					}
				}
				
				//代理商自动生成提款单
				paramMap.clear();
				paramMap.put("isAgent", true);
				paramMap.put("roleType", RevenueDict.MiniRoleType.PropertyAgent);
				paramMap.put("goalType", RevenueDict.RevenueProject.CarFinanceBaoAmout);
				tmpList = revenueDao.selectSysApplyToDoList(paramMap);
				if(tmpList!=null&&tmpList.size()>0){
					for(BigInteger tmpId:tmpList){
						execTaskOnePc(tmpId, RevenueDict.MiniRoleType.PropertyAgent,  RevenueDict.RevenueProject.CarFinanceBaoAmout);
					}
				}
			}
			
			//物业宝抵扣自动生成提款单 -物业公司
			paramMap.clear();
			paramMap.put("isPc", true);
			paramMap.put("roleType", RevenueDict.MiniRoleType.PropertyCompany);
			paramMap.put("goalType", RevenueDict.RevenueProject.FinanceDeduAmount);
			tmpList = revenueDao.selectSysApplyToDoList(paramMap);
			if(tmpList!=null&&tmpList.size()>0){
				for(BigInteger tmpId:tmpList){
					execTaskOnePc(tmpId, RevenueDict.MiniRoleType.PropertyCompany,  RevenueDict.RevenueProject.FinanceDeduAmount);
				}
			}
			
			//停车宝抵扣自动生成提款单 -物业公司
			paramMap.clear();
			paramMap.put("isPc", true);
			paramMap.put("roleType", RevenueDict.MiniRoleType.PropertyCompany);
			paramMap.put("goalType", RevenueDict.RevenueProject.CarDeduAmount);
			tmpList = revenueDao.selectSysApplyToDoList(paramMap);
			if(tmpList!=null&&tmpList.size()>0){
				for(BigInteger tmpId:tmpList){
					execTaskOnePc(tmpId, RevenueDict.MiniRoleType.PropertyCompany,  RevenueDict.RevenueProject.CarDeduAmount);
				}
			}
			
			//电商物业公司收益自动生成提款单
			paramMap.clear();
			paramMap.put("isPc", true);
			paramMap.put("roleType", RevenueDict.MiniRoleType.PropertyCompany);
			paramMap.put("goalType", RevenueDict.RevenueProject.MarketAmout);
			tmpList = revenueDao.selectSysApplyToDoList(paramMap);
			if(tmpList!=null&&tmpList.size()>0){
				for(BigInteger tmpId:tmpList){
					execTaskOnePc(tmpId, RevenueDict.MiniRoleType.PropertyCompany,  RevenueDict.RevenueProject.MarketAmout);
				}
			}
			
			//电商代理商收益自动生成提款单
			paramMap.clear();
			paramMap.put("isAgent", true);
			paramMap.put("roleType", RevenueDict.MiniRoleType.PropertyAgent);
			paramMap.put("goalType", RevenueDict.RevenueProject.MarketAmout);
			tmpList = revenueDao.selectSysApplyToDoList(paramMap);
			if(tmpList!=null&&tmpList.size()>0){
				for(BigInteger tmpId:tmpList){
					execTaskOnePc(tmpId, RevenueDict.MiniRoleType.PropertyAgent,  RevenueDict.RevenueProject.MarketAmout);
				}
			}
		
		return null;
	}*/
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public void execTaskOne(BigInteger companyId){
		try {
			commonLockDao.getLock(Lock.RevenueAutoApplyCompanyTask);
			BigInteger miniRoleId = companyId;
			Integer optType = RevenueDict.TkOptType.SystemAuto;
			Integer miniRoleType = RevenueDict.MiniRoleType.PropertyCompany;
			Integer projectType = RevenueDict.RevenueProject.PropertySubsidyAmout;
			
			RevenueApplyParam applyParam =  null;
			{
				Integer goalType = projectType;
//				String goalStartTime = revenueDao.selectLastApplyGoalEndTime(miniRoleId,miniRoleType,goalType);//获取提款开始时间：为空或者最新的time
				String goalStartTime = null;//获取提款开始时间：为空或者最新的time
				String goalEndTime = ApplicationContextBothUtil.getDualDao().getNowTime();//获取当前时间为提款结束时间
				applyParam =  new RevenueApplyParam(miniRoleId, miniRoleType, goalType, goalStartTime, goalEndTime);
			}
			
			revenueService.applyRevenueSystem(applyParam, optType, RevenueConstant.System);
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	private void execTaskOnePc(BigInteger miniRoleId, Integer miniRoleType, Integer projectType){
		try {
			commonLockDao.getLock(Lock.RevenueAutoApplyCompanyTask);
	//		BigInteger miniRoleId = companyId;
			Integer optType = RevenueDict.TkOptType.SystemAuto;
	//		Integer miniRoleType = RevenueDict.MiniRoleType.PropertyCompany;
	//		Integer projectType = RevenueDict.RevenueProject.PropertySubsidyAmout;
			
			RevenueApplyParam applyParam =  null;
			{
				Integer goalType = projectType;
				String goalStartTime = revenueDao.selectLastApplyGoalEndTime(miniRoleId,miniRoleType,goalType);//获取提款开始时间：为空或者最新的time
				String goalEndTime = ApplicationContextBothUtil.getDualDao().getNowTime();//获取当前时间为提款结束时间
				applyParam =  new RevenueApplyParam(miniRoleId, miniRoleType, goalType, goalStartTime, goalEndTime);
			}
			
			revenueService.applyRevenueSystem(applyParam, optType, RevenueConstant.System);
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
}
