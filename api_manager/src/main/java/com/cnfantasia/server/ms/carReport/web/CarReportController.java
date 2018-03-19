package com.cnfantasia.server.ms.carReport.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.ms.carReport.dto.CarReportDO;
import com.cnfantasia.server.ms.carReport.dto.CarReportDTO;
import com.cnfantasia.server.ms.carReport.dto.CarReportFeeDTO;
import com.cnfantasia.server.ms.carReport.dto.CarReportRequest;
import com.cnfantasia.server.ms.carReport.dto.OptionDto;
import com.cnfantasia.server.ms.carReport.dto.PayChannelDTO;
import com.cnfantasia.server.ms.carReport.service.CarReportService;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.MapConverter;

/**
 * 停车费汇总报表
 * 
 * @author liyulin
 * @version 1.0 2017年11月7日 上午10:35:21
 */
@Controller
@RequestMapping("/carReport")
public class CarReportController {

	@Resource
	private CarReportService carReportService;
	private static final BigDecimal totalPercent = BigDecimal.valueOf(100L);

	@RequestMapping("/index.html")
	public ModelAndView index(CarReportRequest carReportRequest, HttpServletRequest request) {
		String pvStr = request.getParameter("percentVisible");
		boolean percentVisible = (StringUtils.isBlank(pvStr) || "true".equals(pvStr));

		Map<String, Object> paramMap = MapConverter.toMap(carReportRequest);
		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}

		paramMap.put("gbIdList", UserContext.getGbIdList());
		List<CarReportDO> carReportDOList = carReportService.getCarReportDOList(paramMap);

		List<CarReportDTO> carReportDTOList = dealFeeItem(carReportDOList, percentVisible);
		PayChannelDTO totalAllPayChannel = getTotalAllPayChannel(carReportDOList, percentVisible);

		List<OptionDto> gbList = carReportService.getGbList(paramMap);
		List<OptionDto> pmList = carReportService.getPmList(paramMap);

		ModelAndView mav = new ModelAndView("/carReport/index");
		mav.addObject("carReportDTOList", carReportDTOList);
		mav.addObject("totalAllPayChannel", totalAllPayChannel);
		mav.addObject("gbList", gbList);
		mav.addObject("pmList", pmList);
		mav.addObject("percentVisible", percentVisible);
		return mav;
	}

	private List<CarReportDTO> dealFeeItem(List<CarReportDO> carReportDOList, boolean percentVisible) {
		List<CarReportDTO> carReportDTOList = null;
		if (carReportDOList != null && carReportDOList.size() > 0) {
			carReportDTOList = new ArrayList<CarReportDTO>();
			for (CarReportDO carReport : carReportDOList) {
				CarReportDTO carReportDTO = new CarReportDTO();
				carReportDTO.setPmName(carReport.getPmName());
				carReportDTO.setPlotName(carReport.getPlotName());

				BigDecimal monthCash = carReport.getMonthCash();
				BigDecimal tempCash = carReport.getTempCash();
				BigDecimal monthJfqFee = carReport.getMonthJfqFee();
				BigDecimal tempJfqFee = carReport.getTempJfqFee();

				PayChannelDTO monthPayChannel = new PayChannelDTO();
				setFee(monthPayChannel, monthJfqFee, monthCash);

				PayChannelDTO tempPayChannel = new PayChannelDTO();
				setFee(tempPayChannel, tempJfqFee, tempCash);

				PayChannelDTO totalPayChannel = new PayChannelDTO();
				setFee(totalPayChannel, monthJfqFee.add(tempJfqFee), monthCash.add(tempCash));
				
				if (percentVisible) {
					BigDecimal monthTotalFee = monthJfqFee.add(monthCash);
					BigDecimal tempTotalFee = tempJfqFee.add(tempCash);
					setPercent(monthPayChannel.getJfqFee(), monthPayChannel.getCash(), monthTotalFee);
					setPercent(tempPayChannel.getJfqFee(), tempPayChannel.getCash(), tempTotalFee);
					setPercent(totalPayChannel.getJfqFee(), totalPayChannel.getCash(), monthTotalFee.add(tempTotalFee));
				}

				carReportDTO.setMonthPayChannel(monthPayChannel);
				carReportDTO.setTempPayChannel(tempPayChannel);
				carReportDTO.setTotalPayChannel(totalPayChannel);

				carReportDTOList.add(carReportDTO);
			}
		}

		return carReportDTOList;
	}

	private static PayChannelDTO getTotalAllPayChannel(List<CarReportDO> carReportDOList, boolean percentVisible) {
		if (carReportDOList != null && carReportDOList.size() > 0) {
			BigDecimal totalAllJfqFee = BigDecimal.ZERO;
			BigDecimal totalAllCash = BigDecimal.ZERO;
			for (CarReportDO carReport : carReportDOList) {
				BigDecimal monthCash = carReport.getMonthCash();
				BigDecimal tempCash = carReport.getTempCash();
				BigDecimal monthJfqFee = carReport.getMonthJfqFee();
				BigDecimal tempJfqFee = carReport.getTempJfqFee();
				
				totalAllJfqFee = totalAllJfqFee.add(monthJfqFee.add(tempJfqFee));
				totalAllCash = totalAllCash.add(monthCash.add(tempCash));
			}
			
			PayChannelDTO totalAllPayChannel = new PayChannelDTO();
			setFee(totalAllPayChannel, totalAllJfqFee, totalAllCash);
			if (percentVisible) {
				setPercent(totalAllPayChannel.getJfqFee(), totalAllPayChannel.getCash(), totalAllJfqFee.add(totalAllCash));
			}
			
			return totalAllPayChannel;
		}
		
		return null;
	}
	
	private static void setFee(PayChannelDTO payChannel, BigDecimal jfqFee, BigDecimal cash) {
		CarReportFeeDTO jfqFeeMonth = new CarReportFeeDTO();
		jfqFeeMonth.setFee(jfqFee);
		payChannel.setJfqFee(jfqFeeMonth);
		
		CarReportFeeDTO cashMonth = new CarReportFeeDTO();
		cashMonth.setFee(cash);
		payChannel.setCash(cashMonth);
		
		BigDecimal monthTotalFee = jfqFee.add(cash);
		payChannel.setTotalFee(monthTotalFee);
	}

	private static void setPercent(CarReportFeeDTO jfqFee, CarReportFeeDTO cash, BigDecimal totalFee) {
		if (totalFee.compareTo(BigDecimal.ZERO) == 0) {
			jfqFee.setPercentVisible(false);
			cash.setPercentVisible(false);
		} else {
			BigDecimal totalJfqFeePercent = BigDecimalUtil.div(jfqFee.getFee(), totalFee, 4).multiply(totalPercent);
			jfqFee.setPercent(totalJfqFeePercent);
			BigDecimal totalCashPercent = totalPercent.subtract(totalJfqFeePercent);
			cash.setPercent(totalCashPercent);

			jfqFee.setPercentVisible(true);
			cash.setPercentVisible(true);
		}
	}

}
