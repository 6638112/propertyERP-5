/**   
* Filename:    ComparatorNameValuePair.java   
* @version:    1.0  
* Create at:   2014年9月19日 上午9:56:36   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月19日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.common.utils;

import java.util.Comparator;

import org.apache.http.NameValuePair;

/**
 * Filename:    ComparatorNameValuePair.java
 * @version:    1.0.0
 * Create at:   2014年9月19日 上午9:56:36
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月19日       shiyl             1.0             1.0 Version
 */
public class ComparatorNameValuePair implements Comparator<NameValuePair>{

	@Override
	public int compare(NameValuePair o1, NameValuePair o2) {
		return o1.getName().compareTo(o2.getName());
	}

	
	
}
