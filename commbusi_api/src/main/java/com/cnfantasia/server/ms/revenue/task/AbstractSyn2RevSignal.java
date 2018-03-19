package com.cnfantasia.server.ms.revenue.task;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;
import com.cnfantasia.server.domainbase.revenueSignalAmount.dao.IRevenueSignalAmountBaseDao;
import com.cnfantasia.server.domainbase.revenueSignalAmount.entity.RevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.dao.IRevenueDao;
import com.cnfantasia.server.ms.revenue.entity.AbstractRevSrcEntity;
import com.cnfantasia.server.ms.revenue.entity.BeginEndDate;
import com.cnfantasia.server.ms.revenue.entity.RevenueAmountNoTime;
import com.cnfantasia.server.ms.revenue.entity.RevenueConfigByTime;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;


/**
 * 收益明细数据同步实现抽象类
* Filename:    AbstractSyn2RevSignal.java
* @version:    1.0.0
* Create at:   2015年11月19日 上午11:31:11
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月19日       shiyl             1.0             1.0 Version
 */
public abstract class AbstractSyn2RevSignal<T extends AbstractRevSrcEntity<G>,G> implements ISyn2RevSignal<AbstractRevSrcEntity<G>,G>{
	private final Logger logger = Logger.getLogger(getClass());
	
	private IRevenueSignalAmountBaseDao revenueSignalAmountBaseDao;
	public void setRevenueSignalAmountBaseDao(IRevenueSignalAmountBaseDao revenueSignalAmountBaseDao) {
		this.revenueSignalAmountBaseDao = revenueSignalAmountBaseDao;
	}
	
	protected IRevenueDao revenueDao;
	public void setRevenueDao(IRevenueDao revenueDao) {
		this.revenueDao = revenueDao;
	}
	
	private BeginEndDate getBeginEndDate(Integer projectType, RevenueRole revenueRole,RevenueConfigByTime config,BeginEndDate givenRange){
		if(givenRange!=null){
			return givenRange;
		}
		String configStartTime = config.getStartTime();
		String configEndTime = config.getEndTime();
		return new BeginEndDate(configStartTime, configEndTime, DateUtil.formatSecond.get());
	}
	
	@Override
	public Integer syn2RegSignal(Integer projectType, RevenueRole revenueRole,BeginEndDate givenRange) {
		Integer resCount = 0;
		//查询已生效配置列表
		List<RevenueConfigByTime> activeRcList = getActiveRevenueConfigList(projectType, revenueRole);
		if(activeRcList!=null&&activeRcList.size()>0){
			List<RevenueSignalAmount> toAddRSAList = new ArrayList<RevenueSignalAmount>();
			String nowTime = ApplicationContextBothUtil.getDualDao().getNowTime();
			for(RevenueConfigByTime config:activeRcList){
				//查询各个时间段内属于该物业公司的且缴费成功的账单,且未生成过sig对应类别的记录
				//TODO limit处理，避免数据量过大
				BeginEndDate beginEndDate = getBeginEndDate(projectType, revenueRole, config,givenRange);
				String startTime = beginEndDate.getBegin(DateUtil.formatSecond.get());
				String endTime = beginEndDate.getEnd(DateUtil.formatSecond.get());
				List<T> toSigList = getToSigList(projectType, revenueRole,startTime,endTime);
				if(toSigList!=null&&toSigList.size()>0){
					for(int i=0;i<toSigList.size();i++){
						T tmpBill = toSigList.get(i);
						BigInteger addId = null;//设置为空
						RevenueSignalAmount tmpAdd = new RevenueSignalAmount();
						G param = tmpBill.getPayAmount();//参与收益计算的金额,一般为用户实缴金额
						Double srcMoney = tmpBill.getSrcMoney().doubleValue();
						RevenueAmountNoTime amountNoTime = getRevenueAmount(param, revenueRole, config.getRevenueConfig());
						
						Double amount = amountNoTime.getMoney().doubleValue();//用户收益额
						Double amountPtbt = 0.0;
						Double amountUsrReal = 0.0;
						// 物业费由于“代扣卡续费”原因特殊处理    tmpBill.getPayWay().toString().equals("3")
						if(projectType.compareTo(RevenueDict.RevenueProject.PropertyRealPayAmout)==0 
							&& tmpBill.getPayWay()!=null 
							&& tmpBill.getPayWay().toString().equals("3")){// 实收
							amountPtbt = 0.0;
							amountUsrReal = tmpBill.getWyAmountUsrReal().doubleValue();
							amount = amountUsrReal;
						} else if(projectType.compareTo(RevenueDict.RevenueProject.PropertySubsidyAmout)==0
							&& tmpBill.getPayWay()!=null 
							&& tmpBill.getPayWay().toString().equals("3")){    // 补贴
							amountPtbt = tmpBill.getWyAmountPtbt().doubleValue();
							amountUsrReal = 0.0;
							amount = amountPtbt;
						} else if(projectType.compareTo(RevenueDict.RevenueProject.PropertyOtherFee)==0
							&& tmpBill.getPayWay()!=null 
							&& tmpBill.getPayWay().toString().equals("3")){    // 其它代收费
							amountPtbt = tmpBill.getWyAmountPtbt().doubleValue();
							amountUsrReal = tmpBill.getWyAmountUsrReal().doubleValue();
						} else {
							amountPtbt = tmpBill.getAmountPtbt().doubleValue();
							amountUsrReal = tmpBill.getAmountUsrReal().doubleValue();
						}
						String payFlowNo = tmpBill.getPayFlowNo();
						Integer payMethod = tmpBill.getPayMethod();
						
						BigInteger goalId = tmpBill.getUniqueId();
						String goalRevTime = tmpBill.getRevActiveTime();//对象产生可产生收益的时间(冗余)
						Integer goalType = projectType;
						BigInteger miniRoleId = revenueRole.getRoleId();
						Integer miniRoleType = revenueRole.getRole().getCode();
						String moneyTime = nowTime;
						BigInteger revApplyId = null;//默认为空
						BigInteger revConfigId = config.getRevenueConfig().getId();
						String revConfigJson = JSON.toJSONString(config);
						Integer tkStatus = RevenueDict.TkStatus.Undo;
						String tkSuccTime = null;//默认为空
						
						tmpAdd.setGoalId(goalId);
						tmpAdd.setGoalRevTime(goalRevTime);
						tmpAdd.setGoalRevTimeStr("");//syl-add-2015-12-24 10:36:00增加默认值
						tmpAdd.setGoalType(goalType);
						tmpAdd.setId(addId);
						tmpAdd.setMiniRoleId(miniRoleId);
						tmpAdd.setMiniRoleType(miniRoleType);
						tmpAdd.setMoneyTime(moneyTime);
						tmpAdd.setRevApplyId(revApplyId);
						tmpAdd.setRevConfigId(revConfigId);
						tmpAdd.setRevConfigJson(revConfigJson);
						tmpAdd.setTkStatus(tkStatus);
						tmpAdd.setTkSuccTime(tkSuccTime);
						//syl-add=2015-11-22 15:39:15附加显示金额
						tmpAdd.setSrcMoney(srcMoney);
						tmpAdd.setAmount(amount);
						tmpAdd.setAmountPtbt(amountPtbt);
						tmpAdd.setAmountUsrReal(amountUsrReal);
						//其它信息备份
						tmpAdd.setRoleName("");
						tmpAdd.setPayFlowNo(payFlowNo);//syl-add-2015-11-30 15:00:28增加流水号的冗余
						tmpAdd.setPayMethod(payMethod);//syl-add-2015-11-30 15:00:28增加支付方式
						
						tmpAdd.settGroupBuildingId(tmpBill.getGbId());
						tmpAdd.settPropertyManagementFId(tmpBill.getPropManagerId());
						
						toAddRSAList.add(tmpAdd);
					}
				}
			}
			if(toAddRSAList!=null&&toAddRSAList.size()>0){
				List<BigInteger> addIdList = ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount, toAddRSAList.size());
				for(int i=0;i<toAddRSAList.size();i++){//设定Id
					toAddRSAList.get(i).setId(addIdList.get(i));
				}
				if(toAddRSAList.size()>0){
					List<RevenueSignalAmount> tmpList = Collections.synchronizedList(new ArrayList<RevenueSignalAmount>());
					for(int i=0;i<toAddRSAList.size();i++){
						tmpList.add(toAddRSAList.get(i));
						if(i!=0&&i%1000==0){//1000条分次提交
							if(tmpList.size()>0){
								resCount+=revenueSignalAmountBaseDao.insertRevenueSignalAmountBatch(tmpList);
							}
							tmpList.clear();
						}
					}
					if(tmpList.size()>0){
						resCount+=revenueSignalAmountBaseDao.insertRevenueSignalAmountBatch(tmpList);
					}
				}
//				resCount = revenueSignalAmountBaseDao.insertRevenueSignalAmountBatch(toAddRSAList);
				//fresh RoleName
				revenueDao.updateRevenueSignalAmountRoleName();
			}
		}
		return resCount;
	}

	@Override
	public Integer syn2RegSignal(Integer projectType, RevenueRole revenueRole){
		return syn2RegSignal(projectType, revenueRole, null);
	}

	public abstract List<RevenueConfigByTime> getActiveRevenueConfigList(Integer projectType, RevenueRole revenueRole);
	public abstract List<T> getToSigList(Integer projectType, RevenueRole revenueRole, String configStartTime, String configEndTime);
	public abstract RevenueAmountNoTime getRevenueAmount(G param, RevenueRole revenueRole, RevenueConfig revenueConfig);
}
