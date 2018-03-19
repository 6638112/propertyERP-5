/**   
* Filename:    ISysUrlManager.java   
* @version:    1.0  
* Create at:   2014年8月5日 上午2:25:40   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月5日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.business.pub.sysUrl;

import javax.servlet.http.HttpServletRequest;

/**
 * Filename:    ISysUrlManager.java
 * @version:    1.0.0
 * Create at:   2014年8月5日 上午2:25:40
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月5日       shiyl             1.0             1.0 Version
 */
public interface ISysUrlManager<T> {//T CommSysUrl
	
	/**
	 * 初始化
	 */
	public void init();
	
	/**
   * 查询某个url对应的相关信息
   * @param url 请求的url
   * @return 参数值
   */
  public T getUrlDetail(String url);
  
  /**
   * 更新重新加载url信息到缓存
   */
  public void updUrlCatche();
  
  /**
   * 从请求中解析出对应功能的url
   */
  public String parseUrlFromRequest(HttpServletRequest request);
  
  /**
   * 新增url信息
   * @param name
   * @param url
   * @param desc
   * @return 返回新增的信息
   */
  public T addUrl(String name,String url,String desc);
}
