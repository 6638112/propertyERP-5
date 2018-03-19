package com.cnfantasia.server.ms.groupBuildingBillCycle.web;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.paybill.entity.PayBillCycle;
import com.cnfantasia.server.commbusi.plotproperty.entity.BusinessMonthYearFactory;
import com.cnfantasia.server.commbusi.plotproperty.entity.IBusinessMonthYear;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.groupBuilding.dao.IGroupBuildingBaseDao;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuildingBillCycle.entity.GroupBuildingBillCycle;
import com.cnfantasia.server.domainbase.groupBuildingBillCycle.service.IGroupBuildingBillCycleBaseService;
import com.cnfantasia.server.ms.groupBuildingBillCycle.dao.IGroupBuildingBillCycleDao;
import com.cnfantasia.server.ms.pub.comm.CnfantasiaCommUtil;

@Controller
@RequestMapping("/groupBuildingBillCycle02")
public class BillCycleSynTask{
	
	@Resource
	private IGroupBuildingBillCycleDao groupBuildingBillCycleDao;
	@Resource
	private IGroupBuildingBillCycleBaseService groupBuildingBillCycleBaseService;
	@Resource
	private IGroupBuildingBaseDao groupBuildingBaseDao;

	@RequestMapping("/createData.html")
	@ResponseBody
	public String createData(HttpServletRequest request) {
		String id = request.getParameter("yanghua");
		if(id.equals("18565714990")) {
			List<PayBillCycle> payBillList = groupBuildingBillCycleDao.selectPayBillForBillCycle();
			saveBillCycleList(payBillList);
			List<PayBillCycle> payBillList02 = groupBuildingBillCycleDao.selectPayBillForBillCycle02();
			saveBillCycleList02(payBillList02);
			return "success";
		} else {
			return "ok";
		}
	}
	
	private int saveBillCycleList(List<PayBillCycle> payBillList) {
		
		for (int i = 0; i < payBillList.size(); i++) {
			PayBillCycle billCycle = payBillList.get(i);
			BigInteger gbId = billCycle.getGbId();
			GroupBuilding groupBuilding = groupBuildingBaseDao.selectGroupBuildingBySeqId(gbId);
			
			if(groupBuilding ==null) {
				break;
			}
			IBusinessMonthYear businessMonthYear = BusinessMonthYearFactory.newInstanceByBillMonth(billCycle.getBillMonth(), groupBuilding);
			String startStr = businessMonthYear.getPayTimeStartDesc();
			String startStr2 = startStr.replaceAll("/", "-");
			String endStr = businessMonthYear.getPayTimeEndDesc();
			String endStr2 = endStr.replaceAll("/", "-");
			
			GroupBuildingBillCycle cycle = new GroupBuildingBillCycle();
			cycle.setBillMonth(DateUtils.convertDateToStr(DateUtils.convertStrToDate(billCycle.getBillMonth()), "yyyy-MM-dd HH:mm:ss"));
			cycle.settGroupBuildingId(gbId);
			cycle.settPayBillTypeId(billCycle.getBillTypeId());
			cycle.settPayBillTimeCfgId(billCycle.getBillTimeCfgId());
			cycle.setBillPayStart(DateUtils.convertDateToStr(DateUtils.convertStrToDate(startStr2), "yyyy-MM-dd HH:mm:ss"));
			cycle.setBillPayEnd(DateUtils.convertDateToStr(DateUtils.convertStrToDate(endStr2), "yyyy-MM-dd HH:mm:ss"));
			cycle.setPaytimeType(billCycle.getPaytimeType());
			
			CnfantasiaCommUtil.newStandard(cycle, SEQConstants.t_group_building_bill_cycle);
			cycle.setBankCollectionStatus(2);
			groupBuildingBillCycleBaseService.insertGroupBuildingBillCycle(cycle);
		}
		
		return 0;
	}

	private int saveBillCycleList02(List<PayBillCycle> payBillList02) {
		for (int i = 0; i < payBillList02.size(); i++) {
			PayBillCycle billCycle = payBillList02.get(i);
			
			GroupBuildingBillCycle cycle = new GroupBuildingBillCycle();
			cycle.setBillMonth(billCycle.getBillMonth());
			cycle.settGroupBuildingId(billCycle.getGbId());
			cycle.settPayBillTypeId(billCycle.getBillTypeId());
			cycle.settPayBillTimeCfgId(billCycle.getBillTimeCfgId());
			cycle.setBillPayStart(DateUtils.convertDateToStr(DateUtils.convertStrToDate(billCycle.getPayStart()), "yyyy-MM-dd HH:mm:ss"));
			cycle.setBillPayEnd(DateUtils.convertDateToStr(DateUtils.convertStrToDate(billCycle.getPayEnd()), "yyyy-MM-dd HH:mm:ss"));
			cycle.setPaytimeType(billCycle.getPaytimeType());
			cycle.setBillMonthStart(DateUtils.convertDateToStr(DateUtils.convertStrToDate(billCycle.getBillMonthStart()), "yyyy-MM-dd HH:mm:ss"));
			cycle.setBillMonthEnd(DateUtils.convertDateToStr(DateUtils.convertStrToDate(billCycle.getBillMonthEnd()), "yyyy-MM-dd HH:mm:ss"));
			
			CnfantasiaCommUtil.newStandard(cycle, SEQConstants.t_group_building_bill_cycle);
			cycle.setBankCollectionStatus(2);
			groupBuildingBillCycleBaseService.insertGroupBuildingBillCycle(cycle);
		}
		return 0;
	}
	
	public IGroupBuildingBillCycleBaseService getGroupBuildingBillCycleBaseService() {
		return groupBuildingBillCycleBaseService;
	}

	public void setGroupBuildingBillCycleBaseService(IGroupBuildingBillCycleBaseService groupBuildingBillCycleBaseService) {
		this.groupBuildingBillCycleBaseService = groupBuildingBillCycleBaseService;
	}

	public IGroupBuildingBaseDao getGroupBuildingBaseDao() {
		return groupBuildingBaseDao;
	}

	public void setGroupBuildingBaseDao(IGroupBuildingBaseDao groupBuildingBaseDao) {
		this.groupBuildingBaseDao = groupBuildingBaseDao;
	}

	public IGroupBuildingBillCycleDao getGroupBuildingBillCycleDao() {
		return groupBuildingBillCycleDao;
	}

	public void setGroupBuildingBillCycleDao(IGroupBuildingBillCycleDao groupBuildingBillCycleDao) {
		this.groupBuildingBillCycleDao = groupBuildingBillCycleDao;
	}

}
