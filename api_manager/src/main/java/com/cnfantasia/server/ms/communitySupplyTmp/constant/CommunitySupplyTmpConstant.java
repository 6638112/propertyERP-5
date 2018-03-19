package com.cnfantasia.server.ms.communitySupplyTmp.constant;

public class CommunitySupplyTmpConstant {
	
	// 商家图片类型=={"1":"大图","2":"略缩图"}
	public static class CommunitySupplyPic_Type {
		/** 新增图片 */
		public static final int Pic_Type_Add = 1;
		/** 删除图片 */
		public static final int Pic_Type_Del = 2;
	}
	
	// 商家新增类型=={1:商家录入,2:物业录入,3:商家编辑,4:代理渠道录入}
	public static class CommunitySupplyTmp_Add_Type {
		/** 商家录入 */
		public static final int Add_Type_CS = 1;
		/** 物业录入 */
		public static final int Add_Type_PC = 2;
		/** 商家编辑 */
		public static final int Add_Type_Edit_CS = 3;
		/** 渠道代理商录入 */
		public static final int Add_Type_CP = 4;
	}
}
