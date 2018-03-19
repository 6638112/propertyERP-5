package com.cnfantasia.server.ms.door.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cnfantasia.server.ms.door.entity.DoorVerifyAndPaymentDto;

/**
 * 门牌验证缴费
 * 
 * @author liyulin
 * @version 1.0 2016年7月14日 上午10:58:39
 */
public interface IDoorService {

	/**
	 * 查询门牌验证缴费情况记录条数
	 * 
	 * @param paramMap
	 * @return int
	 */
	public int selectDoorVerifyAndPaymentForCount(Map<String, Object> paramMap);

	/**
	 * 查询门牌验证缴费情况
	 * 
	 * @param paramMap
	 * @return List<DoorVerifyAndPaymentDto>
	 */
	public List<DoorVerifyAndPaymentDto> selectDoorVerifyAndPaymentForList(Map<String, Object> paramMap);
	
	/**
	 * 导出门牌验证缴费情况
	 * 
	 * @param paramMap
	 * @return HSSFWorkbook
	 */
	public HSSFWorkbook exportDoorVerifyAndPayment(Map<String, Object> paramMap);
}
