/**   
* Filename:    TelPhoneParser.java   
* @version:    1.0  
* Create at:   2014年11月27日 下午1:48:00   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年11月27日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.util;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename:    TelPhoneParser.java
 * @version:    1.0.0
 * Create at:   2014年11月27日 下午1:48:00
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年11月27日       shiyl             1.0             1.0 Version
 */
public class TelPhoneParser {
	public static void main(String[] args) {
//		String tel = "(0755)82550580,(0755)82772522,(0755)82550510";
		String tel = "132213";
		System.out.println(JSON.toJSONString(getPhoneList(tel)));
	}
	public static List<String> getPhoneList(String srcStr){
		List<String> resList = new ArrayList<String>();
		String[] telArr = srcStr.split(",");
		for(String signal:telArr){
			if(!StringUtils.isEmpty(signal)){
				signal = signal.trim();
				signal = signal.replaceAll("\\(", "");
				signal = signal.replaceAll("\\)", "-");
				resList.add(signal);
			}
		}
		return resList;
	}
	
}
