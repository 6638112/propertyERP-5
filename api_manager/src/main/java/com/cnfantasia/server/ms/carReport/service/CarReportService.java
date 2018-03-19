package com.cnfantasia.server.ms.carReport.service;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.ms.carReport.dao.CarReportDao;
import com.cnfantasia.server.ms.carReport.dto.CarReportDO;
import com.cnfantasia.server.ms.carReport.dto.OptionDto;

public class CarReportService {

	private CarReportDao carReportDao;
	
	public void setCarReportDao(CarReportDao carReportDao) {
		this.carReportDao = carReportDao;
	}

	/**
	 * 停车费汇总报表查询
	 * @param paramMap
	 * @return
	 */
	public List<CarReportDO> getCarReportDOList(Map<String, Object> paramMap) {
		return carReportDao.selectCarReportDOList(paramMap);
	}

	/**
	 * 查询可选择的小区
	 * @return
	 */
	public List<OptionDto> getGbList(Map<String, Object> paramMap){
		return carReportDao.selectGbList(paramMap);
	}
	
	/**
	 * 查询可选择的管理处
	 * @return
	 */
	public List<OptionDto> getPmList(Map<String, Object> paramMap){
		return carReportDao.selectPmList(paramMap);
	}
}
