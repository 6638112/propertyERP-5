package com.cnfantasia.server.ms.carReport.dao;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.ms.carReport.dto.CarReportDO;
import com.cnfantasia.server.ms.carReport.dto.OptionDto;
import com.cnfantasia.server.ms.pub.dao.AbstractBaseDao;

public class CarReportDao extends AbstractBaseDao{
	/**
	 * 停车费汇总报表查询
	 * @param paramMap
	 * @return
	 */
	public List<CarReportDO> selectCarReportDOList(Map<String, Object> paramMap) {
		return sqlSession.selectList("carReport.selectCarReportDOList", paramMap);
	}

	/**
	 * 查询可选择的小区
	 * @return
	 */
	public List<OptionDto> selectGbList(Map<String, Object> paramMap){
		return sqlSession.selectList("carReport.selectGbList", paramMap);
	}
	
	/**
	 * 查询可选择的管理处
	 * @return
	 */
	public List<OptionDto> selectPmList(Map<String, Object> paramMap){
		return sqlSession.selectList("carReport.selectPmList", paramMap);
	}
}
