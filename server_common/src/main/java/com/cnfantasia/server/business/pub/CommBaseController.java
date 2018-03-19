/**   
 * Filename:    CommBaseController.java   
 * @version:    1.0  
 * Create at:   2014年5月9日 上午4:01:57   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年5月9日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.business.pub;

import javax.servlet.http.HttpServletRequest;
//import com.cnfantasia.server.common.exception.FocException;

/**
 * Filename: CommBaseController.java
 * 
 * @version: 1.0.0 Create at: 2014年5月9日 上午4:01:57 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年5月9日 shiyl 1.0 1.0 Version
 */
public abstract class CommBaseController<T> {
	public abstract T handleException(Exception ex, HttpServletRequest request);
	public abstract String getUniqueCode(Exception ex, HttpServletRequest request);

}
