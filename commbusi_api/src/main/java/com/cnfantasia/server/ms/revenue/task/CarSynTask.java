package com.cnfantasia.server.ms.revenue.task;

import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.common.utils.CloneUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.entity.CarRevenue;
import com.cnfantasia.server.ms.revenue.service.RevenueCarService;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

/**
 * 车禁收益定时任务
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月19日       yewj             1.0             1.0 Version
 */
@Repository
public class CarSynTask implements ISynTask{
	
	private Log logger = LogFactory.getLog(getClass());
	
	private RevenueCarService revenueCarService;
	
	@Override
	public Integer execTask() {
		int size = 0;
		logger.debug("CarSynTask start:" + DateUtils.getCurrentDate());
		
		//避免极端情况下大量数据一次性处理，此处只会循环每次处理300条，在某次少于50(或者少于100)条即已经处理完成。
		while(true) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			Date currentDate = new Date();
			paramMap.put("currentDate", currentDate);
			List<CarRevenue> carRevenueList = revenueCarService.updateAndGetCarRevenueList(paramMap);
			// 深度拷贝作用：解决“saveCarRevenueList(carRevenueList, 1)”执行完后，会改变carRevenueList的值的问题
			List<CarRevenue> carRevenueBtList = null;
			try {
				carRevenueBtList = (List<CarRevenue>) CloneUtil.deepClone(carRevenueList);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			
			int saveRealPaySize = saveCarRevenueList(carRevenueList, 1);// 停车费实收
			int saveBtPaySize = saveCarRevenueList(carRevenueBtList, 2);// 停车费补贴
			size += (saveRealPaySize + saveBtPaySize);
			
			if(saveRealPaySize + saveBtPaySize < 50) {
				break;
			}
		}

		size += revenueCarService.updateCarRevnueSignalAmountToSettled();
		
		logger.debug("CarSynTask end:" + DateUtils.getCurrentDate() + ";size:" + size);
		return size;
	}
	
	/**
	 * 停车费实收、补贴提款项生成
	 * 
	 * @param carRevenueList
	 * @param type 类型=={1：实收；2：补贴}
	 * @return
	 */
	private int saveCarRevenueList(List<CarRevenue> carRevenueList, int type) {
		int i = 0;
		if(carRevenueList!=null && carRevenueList.size()>0){
			List<BigInteger> revenueAmountIds = ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_signal_amount, carRevenueList.size());
			for(CarRevenue carRevenue : carRevenueList) {
				carRevenue.setId(revenueAmountIds.get(i));
				try {
					construct(carRevenue, type);
					revenueCarService.saveRevenueFinance(carRevenue);
					i++;
				} catch(Exception e) {
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("id", carRevenue.getGoalId());
					paramMap.put("revenueStatusTo", -1);
					revenueCarService.updateCarRevenueStatus(paramMap);
					logger.error("car-task failed orderId:" + carRevenue.getGoalId());
					logger.error(e.getMessage(), e);
				}
			}
		}
		return i;
	}
	
	/**
	 * 构造对象
	 * 
	 * @param carRevenue
	 * @param type 类型=={1：实收；2：补贴}
	 */
	private void construct(CarRevenue carRevenue, int type) {
		carRevenue.setSrcMoney(carRevenue.getAmount());
		carRevenue.setMoneyTime(DateUtils.getCurrentDate());
		carRevenue.setRevApplyId(null); //t_revenue_apply.f_id
		carRevenue.setRevConfigId(BigInteger.ZERO);
		carRevenue.setRevConfigJson("");
		carRevenue.setTkStatus(RevenueDict.TkStatus.Undo);
		carRevenue.setGoalRevTime(DateUtils.getCurrentDate());
		carRevenue.setGoalRevTimeStr(carRevenue.getGoalRevTime().substring(0, 7));
		carRevenue.setSys0AddTime(DateUtils.getCurrentDate());
		carRevenue.setSys0DelState(0);
		if(type==1){// 实收
			carRevenue.setGoalType(RevenueDict.RevenueProject.CarAmount);
			carRevenue.setAmountUsrReal(carRevenue.getAmount());
			carRevenue.setAmountPtbt(0.0);
		} else {// 补贴
			carRevenue.setGoalType(RevenueDict.RevenueProject.CarAmountBt);
			carRevenue.setAmount(carRevenue.getAmountPtbt());
			carRevenue.setAmountUsrReal(0.0);
		}
		carRevenue.setMiniRoleType(RevenueDict.MiniRoleType.PropertyCompany);
		carRevenue.settGroupBuildingId(carRevenue.getGbId());
	}

	public void setRevenueCarService(RevenueCarService revenueCarService) {
		this.revenueCarService = revenueCarService;
	}

}
