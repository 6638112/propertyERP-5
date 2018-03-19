package com.cnfantasia.server.ms.revenue.task;

import com.cnfantasia.server.api.propertycard.service.PropertyCardService;

/**
 * @ClassName: PropertyAccountDeduTask
 * @Auther: yewj
 * @Description:()
 */
public class PropertyAccountDeduTask implements ISynTask {

    private PropertyCardService propertyCardService;

    @Override
    public Integer execTask() {
    	propertyCardService.triggerDeducntion4GroupBuilding();
    	return 1;
    }

	public void setPropertyCardService(PropertyCardService propertyCardService) {
		this.propertyCardService = propertyCardService;
	}
}
