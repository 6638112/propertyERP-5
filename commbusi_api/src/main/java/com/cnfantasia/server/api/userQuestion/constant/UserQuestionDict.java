package com.cnfantasia.server.api.userQuestion.constant;

/**
 * @ClassName: UserQuestionDict
 * @Date: 2017-02-23 14:27
 * @Auther: kangduo
 * @Description: ()
 */
public class UserQuestionDict {

    public static class QuestionType {
        public static final int PROPERTY_QUESTION = 1;
    }

    public static class QuestionStatus {
        //待处理
        public static final int ORIGIN = 1;
        //通知片区经理
        public static final int NOTICE_MANAGER = 2;
        //转公共维修
        public static final int TURN_REPAIR = 3;
        //转普通维修
        public static final int TURN_DREDGE = 4;
        //解放区回复
        public static final int SYS_REPLY = 5;
        //物业回复
        public static final int PROPERTY_REPLY = 6;
        //报修类型的维修,BILLTYPE == 4
        public static final int TURN_DREDGE_REPAIR = 7;
    }
}
