package com.cnfantasia.server.ms.payBill.constant;

import com.cnfantasia.server.common.utils.DataUtil;

/**
 * @ClassName: PropIconUtil
 * @Date: 2017-01-18 17:02
 * @Auther: yanghua
 * @Description:(物业缴费图标选择)
 */
public final class PropIconUtil {

    /**物业费图标*/
	public final static String PROPERTY = "bill_1_1.png";
    /**停车费图标*/
    private final static String PARKING = "bill_2_1.png";
    /**水、电、水电费图标*/
    private final static String HYDROPOWER = "bill_3.jpg";
    /**暖气费图标*/
    private final static String WARM_AIR_FEE = "bill_4.jpg";

    public static final String getBillIcon(String billName) {
        if(DataUtil.isEmpty(billName)) {
            return PROPERTY;
        }

        String name = "";
        if(billName.length() > 2) {
            name = billName.substring(0,2);
        } else {
            name = billName;
        }

        if(name.contains("停车")) {
            return PARKING;
        }
        if(name.substring(0,1).contains("水") || name.contains("水电") || name.substring(0,1).contains("电")) {
            return HYDROPOWER;
        }
        if(name.contains("暖气")){
        	return WARM_AIR_FEE;
        }
        return PROPERTY;
    }
}
