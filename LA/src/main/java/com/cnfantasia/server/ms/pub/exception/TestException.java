/**   
* Filename:    TestException.java   
* @version:    1.0  
* Create at:   2014年5月9日 上午5:13:37   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.ms.pub.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * Filename:    TestException.java
 * @version:    1.0.0
 * Create at:   2014年5月9日 上午5:13:37
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月9日       shiyl             1.0             1.0 Version
 */
public class TestException implements HandlerExceptionResolver{

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// 该方法暂未使用
		return null;
	}

}
