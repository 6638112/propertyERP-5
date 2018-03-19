/**   
 * Filename:    KitchenDict.java   
 * @version:    1.0  
 * Create at:   2014年8月11日 上午9:01:28   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年8月11日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.kitchen.constant;

/**
 * Filename: KitchenDict.java
 * 
 * @version: 1.0.0 Create at: 2014年8月11日 上午9:01:28 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年8月11日 shiyl 1.0 1.0 Version
 */
public class KitchenDict {
	/**
	 * 是否喜欢吃 0不喜欢吃，1喜欢吃
	 */
	public static class Kitchen_Cookbook_IsLikeEat {
		/** 不喜欢吃 */
		public static final Integer NO = 0;
		/** 喜欢吃 */
		public static final Integer YES = 1;
	}
	
	/**
	 * 厨房食品参数类别
	 */
	public static class KitchenDetail_Type {
		/** 主料 */
		public static final Integer MAIN = 1;
		/** 辅料 */
		public static final Integer ASSIST = 2;
		/** 配料 */
		public static final Integer BATCHING = 3;
	}
	
	/**
	 * 用户是否已收藏菜谱类别标识
	 */
	public static class Kitchen_Cookbook_Type_CollectFlag {
		/** 未收藏 */
		public static final Integer NO = 0;
		/** 已收藏 */
		public static final Integer YES = 1;
	}
	/**
	 * 用户是否已收藏菜谱标识
	 */
	public static class Kitchen_Cookbook_CollectFlag {
		/** 未收藏 */
		public static final Integer NO = 0;
		/** 已收藏 */
		public static final Integer YES = 1;
	}
	
	/**
	 * 厨房菜谱顶级类别是否可多选标识
	 */
	public static class Kitchen_Cookbook_Type_MultiFlag {
		/** 是 */
		public static final Integer YES = 1;
		/** 否 */
		public static final Integer NO = 2;
	}

    /**
     * @author wangzhe
     * @date 2015年11月6日
     * @description 膳食建议评论
     *
     */
    public static class Kitchen_Diet_Comment {
        /**
         * 小白
         */
        public static final Integer STUPID = 1;
        /**
         * 菜鸟
         */
        public static final Integer ROOKIE = 2;
        /**
         * 学徒
         */
        public static final Integer STU = 3;
        /**
         * 达人
         */
        public static final Integer EXPERT = 4;
    }
    
	
}
