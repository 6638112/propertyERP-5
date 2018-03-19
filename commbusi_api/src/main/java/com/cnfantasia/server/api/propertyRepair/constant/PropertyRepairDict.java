package com.cnfantasia.server.api.propertyRepair.constant;

public class PropertyRepairDict {

    public static final class RepairStatus {
        //待处理
        public static final Integer WAITING = 0;
        public static final Integer CANCELED = 1;
        public static final Integer SET_REPAIRER = 2;
        public static final Integer DONE = 3;
    }
}
