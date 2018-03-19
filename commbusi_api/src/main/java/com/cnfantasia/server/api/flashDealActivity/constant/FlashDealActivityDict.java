package com.cnfantasia.server.api.flashDealActivity.constant;

/**
 * @ClassName: FlashDealActivityDict
 * @Date: 2016-10-29 17:42
 * @Auther: kangduo
 * @Description:()
 */
public class FlashDealActivityDict {

    public static class BuyRecord {
        public static final Integer New_Record = 1;
        public static final Integer Alread_Pay = 2;
        public static final Integer Client_Mark_pay = 3;
        public static final Integer Result_Win = 4;
        public static final Integer Result_Fail = 5;
    }

    public static class activityStatus {
        public static final Integer Already_Begin = 1;
        public static final Integer Not_Begin = 2;
        public static final Integer Is_Over = 3;
    }
}
