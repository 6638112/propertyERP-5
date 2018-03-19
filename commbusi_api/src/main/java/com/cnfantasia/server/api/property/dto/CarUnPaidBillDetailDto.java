package com.cnfantasia.server.api.property.dto;

/**
 * 车禁未缴账单信息
 * 
 * @author liyulin
 * @version 1.0 2017年3月10日 下午2:05:20
 */
public class CarUnPaidBillDetailDto extends BillBaseDto {

	private CarBillTailDto carBillTail;

	public CarBillTailDto getCarBillTail() {
		return carBillTail;
	}

	public void setCarBillTail(CarBillTailDto carBillTail) {
		this.carBillTail = carBillTail;
	}

}
