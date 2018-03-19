/**   
* Filename:    HeaderInterceptor.java   
* @version:    1.0  
* Create at:   2014年5月6日 上午12:54:08   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月6日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.pub.header;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cnfantasia.server.api.commonBusiness.service.ICommonLoginService;

/**
 * Filename:    HeaderInterceptor.java
 * @version:    1.0.0
 * Create at:   2014年5月6日 上午12:54:08
 * Description:请求头header拦截处理
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月6日       shiyl             1.0             1.0 Version
 */
public class HeaderInterceptor extends HandlerInterceptorAdapter{

	private ICommonLoginService commonLoginService;
	public void setCommonLoginService(ICommonLoginService commonLoginService) {
		this.commonLoginService = commonLoginService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//检查SessionKey，并自动登录
		commonLoginService.checkSessionKeyAndAutoLogin(request);
		return super.preHandle(request, response, handler);
	}
	
	 /**
   * see the HandlerInterceptorAdapter 执行于action之后
   */
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
  	super.postHandle(request, response, handler, modelAndView);
  }
  
	/**
   * see the HandlerInterceptorAdapter 最后执行
   */
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
  	super.afterCompletion(request, response, handler, ex);
  }
	
}
