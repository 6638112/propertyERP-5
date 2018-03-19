/**   
* Filename:    GiftRepeatMap.java   
* @version:    1.0  
* Create at:   2015年9月16日 上午9:41:25   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年9月16日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.prizeActivity.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Filename:    GiftRepeatMap.java
 * @version:    1.0.0
 * Create at:   2015年9月16日 上午9:41:25
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年9月16日       shiyl             1.0             1.0 Version
 */
public class GiftRepeatMap implements Serializable{
	private static final long serialVersionUID = 1L;
	private Map<GiftUqMarkCode,List<ExcelLocation>> dataMap;
	private Set<GiftUqMarkCode> repeatKeys;
	public GiftRepeatMap(){
		dataMap = new HashMap<GiftUqMarkCode, List<ExcelLocation>>();
		repeatKeys = new LinkedHashSet<GiftUqMarkCode>();
	}
	public void store(int rowNum,int colNum,String uqMark,String valueCode){
		GiftUqMarkCode key = new GiftUqMarkCode(uqMark, valueCode);
		if(dataMap.containsKey(key)){
			List<ExcelLocation> locList = dataMap.get(key);
			ExcelLocation loc = new ExcelLocation(rowNum, colNum);
			locList.add(loc);
			repeatKeys.add(key);
		}else{
			List<ExcelLocation> locList = new ArrayList<ExcelLocation>();
			ExcelLocation loc = new ExcelLocation(rowNum, colNum);
			locList.add(loc);
			dataMap.put(key, locList);
		}
	}
	
	public Set<GiftUqMarkCode> getDataMapKey(){
		return dataMap.keySet();
	}
	
	public boolean hasRepeatData(){
		return repeatKeys.size()>0?true:false;
	}
	
	public String show(Set<GiftUqMarkCode> repeatKeys){
		StringBuffer sbf = new StringBuffer();
		if(repeatKeys.size()>0){sbf.append("<br/>\r");}
		for(GiftUqMarkCode tmpKey:repeatKeys){
			sbf.append(tmpKey.toString());
			sbf.append(dataMap.get(tmpKey).toString());
			sbf.append("<br/>\r");
		}
		return sbf.toString();
	}
	public Set<GiftUqMarkCode> getRepeatKeys(){
		return repeatKeys;
	}
	
//	public static void main(String[] args) {
//		GiftRepeatMap repeatMap= new GiftRepeatMap();
//		repeatMap.store(1, 2, "uqMark01", "valueCode1");
//		repeatMap.store(1, 3, "uqMark01", "valueCode1");
//		repeatMap.store(1, 4, "uqMark01", "valueCode2");
//		repeatMap.store(2, 2, "uqMark01", "valueCode3");
//		repeatMap.store(3, 2, "uqMark02", "valueCode1");
//		repeatMap.store(3, 3, "uqMark02", "valueCode2");
//		repeatMap.store(4, 1, "uqMark03", "valueCode1");
//		repeatMap.store(4, 2, "uqMark03", "valueCode1");
//		System.out.println(repeatMap.show());
//	}
	
}
