/**   
* Filename:    FileTest.java   
* @version:    1.0  
* Create at:   2015年12月9日 下午8:02:03   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年12月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.msgpush;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Filename:    FileTest.java
 * @version:    1.0.0
 * Create at:   2015年12月9日 下午8:02:03
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年12月9日       shiyl             1.0             1.0 Version
 */
public class FileTest {
	//
	public static Set<PushConfig> parseFile(String path) throws Exception {
//		path = "D:\\ProgramData\\workspace\\example\\API\\src\\test\\java\\com\\cnfantasia\\server\\api\\msgpush\\"+path;
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader (fr);
		String line="";
		Set<PushConfig> confList = new HashSet<PushConfig>();
    while ((line=br.readLine())!=null) {
    	System.out.println(line);
    	line = line.replaceAll("\"", "").trim();
    	String[] arr = line.split(",");
    	
    	if(1 == Integer.valueOf(arr[0].trim())) {
    		PushConfig tmp = new PushConfig(Integer.valueOf(arr[0].trim()), arr[1].trim(), arr[2].trim());
        	confList.add(tmp);
    	}
    	
    }
    br.close();
    fr.close();
    return confList;
	}
	
}
