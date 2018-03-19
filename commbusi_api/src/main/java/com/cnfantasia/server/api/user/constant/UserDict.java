/**   
 * Filename:    UserDict.java   
 * @version:    1.0  
 * Create at:   2014年8月8日 上午7:59:54   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年8月8日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.user.constant;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Filename: UserDict.java
 * 
 * @version: 1.0.0 Create at: 2014年8月8日 上午7:59:54 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年8月8日 shiyl 1.0 1.0 Version
 */
public class UserDict {

    /**
     * 用户性别
     */
    public class UserSex {
        /** 男 */
        public static final String BOY = "0";
        /** 女 */
        public static final String GIRL = "1";
    }

    /**
     * 用户状态
     */
    public static class UserState {
        /** 启用 */
        public static final Long IN_USE = 0L;
        /** 禁用 */
        public static final Long DIS_ABLE = 1L;
    }

    // /**
    // * 用户的密码状态
    // */
    // public static class UserPassword_Status{
    // /**未曾登录*/
    // public static final Integer NEVER_LOGIN = 0;
    // /**未曾登录*/
    // public static final Integer FIRST_LOGIN = 1;
    // /**重置密码*/
    // public static final Integer IS_RESET = 2;
    // /**已改密*/
    // public static final Integer IS_CHANGED = 3;
    // }
    /**
     * 用户是否登陆过标识
     */
    public static class User_IsFirst_LoginStatus {
        /** 未曾登录 */
        public static final Integer NEVER_LOGIN = 0;
        /** 已登陆过 */
        public static final Integer HAVE_LOGIN = 1;
    }

    /**
     * 用户是否已经选中了某个兴趣爱好的标志 0未选中 1 已选中
     */
    public static class Hobby_HaveSelectFlag {
        /** 未选中 */
        public static final Integer NO = 0;
        /** 已选中 */
        public static final Integer YES = 1;
    }

    /**
     * @author wangzhe
     * @date 2015年8月31日
     * @description 用户角色
     *
     */
    public static class Role {
        /** 未知 */
        public static final Integer UNKNOWN = 0;
        /** 爷爷 */
        public static final Integer GRANDPA = UNKNOWN + 1;
        /** 奶奶 */
        public static final Integer GRANDMA = GRANDPA + 1;
        /** 姥爷 */
        public static final Integer GRANDPA_M = GRANDMA + 1;
        /** 姥姥 */
        public static final Integer GRANDMA_M = GRANDPA_M + 1;
        /** 父亲 */
        public static final Integer FATHER = GRANDMA_M + 1;
        /** 母亲 */
        public static final Integer MOTHER = FATHER + 1;
        /** 儿子 */
        public static final Integer SON = MOTHER + 1;
        /** 女儿 */
        public static final Integer DAUGTHER = SON + 1;
    }

    /**
     * 角色与菜谱类别的映射
     */
    public static Map<Integer, List<Integer>> S_ROLE2CBTYPE = new HashMap<Integer, List<Integer>>() {
        /**
        * 
        */
        private static final long serialVersionUID = 1468652993337127629L;

        {
            put(Role.GRANDPA, Arrays.asList(new Integer[] { 1, 24, 22, 20, 19, 17, }));
            put(Role.GRANDMA, Arrays.asList(new Integer[] { 1, 23, 22, 20, 19, 17, }));
            put(Role.GRANDPA_M, Arrays.asList(new Integer[] { 1, 24, 22, 20, 19, 17, }));
            put(Role.GRANDMA_M, Arrays.asList(new Integer[] { 1, 23, 22, 20, 19, 17, }));
            put(Role.FATHER, Arrays.asList(new Integer[] { 1, 24, 21, 20, 4, 17, }));
            put(Role.MOTHER, Arrays.asList(new Integer[] { 1, 23, 21, 20, 2, 4, 5, 6, 12, 17, }));
            put(Role.SON, Arrays.asList(new Integer[] { 1, 25, 11, 4, 18 }));
            put(Role.DAUGTHER, Arrays.asList(new Integer[] { 1, 25, 23, 11, 2, 4, 18 }));
        }
    };

}
