/**   
 * Filename:    HuaGenerator.java   
 * @version:    1.0  
 * Create at:   2014年5月10日 上午7:56:54   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年5月10日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.pub.generator;

import java.math.BigInteger;
import java.text.DecimalFormat;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.PinyinUtil;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename: HuaGenerator.java
 * 
 * @version: 1.0.0 Create at: 2014年5月10日 上午7:56:54 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年5月10日 shiyl 1.0 1.0 Version
 */
public class HuaGenerator {
	public static final DecimalFormat decimalFormat4 = new DecimalFormat("0000");
	public static final DecimalFormat decimalFormat5 = new DecimalFormat("00000");
	private static Log logger = LogFactory.getLog(HuaGenerator.class);
	
	public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
//		System.out.println(getUserHuaId(new BigInteger("66")));
//		String id = decimalFormat4.format(11906); // 此id即为四位不重复的流水号
//		System.out.println(id);
//		System.out.println(PinyinUtil.hanyuToPinyinSimple("您好"));
//		System.out.println(getRoomHuaId("O(∩_∩)O~按时服务", new BigInteger("111032")) );
	}

	/**
	 * 生成房间的花Id
	 * @param groupBuildingName 小区名称
	 * @param roomId 虚拟门牌Id
	 * @return
	 */
	public static String getRoomHuaId(String groupBuildingName,BigInteger roomId) {
		//根据小区名得到简称
		String simpleName = null;
		if(StringUtils.isEmpty(groupBuildingName)){
			simpleName="NULL";
		}else{
			try {
				simpleName=PinyinUtil.hanyuToPinyinFirst(groupBuildingName);
			} catch (BadHanyuPinyinOutputFormatCombination e) {
				logger.info("The pinyinFirst name for "+groupBuildingName+" is failed to transfer,exception is :"+e.getMessage(),e);
				throw new BusinessRuntimeException("HuaGenerator.getRoomHuaId.BadHanyuPinyin",new Object[]{groupBuildingName},e);
			}
		}
		return simpleName + decimalFormat4.format(roomId);
	}
	/*public static String getRoomHuaId(String unique) {
		return "hua" + unique;
	}*/
	/**
	 * 生成用户的花Id
	 * 
	 * @param unique
	 * @return
	 */
	public static String getUserHuaId(BigInteger unique) {
		String tmp = decimalFormat5.format(unique);
		return tmp;
	}
}
