/**   
* Filename:    ShareController.java   
* @version:    1.0  
* Create at:   2015年1月6日 上午3:07:26   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月6日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.point.web;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.point.service.IShareService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename:    ShareController.java
 * @version:    1.0.0
 * Create at:   2015年1月6日 上午3:07:26
 * Description:分享
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月6日       shiyl             1.0             1.0 Version
 */
@RequestMapping("/share")
@Controller
public class ShareController extends BaseController{
	
	private IShareService shareService;
	public void setShareService(IShareService shareService) {
		this.shareService = shareService;
	}
	
	/**
	 * 进行分享
	 * @param request
	 * @return
	 */
	@RequestMapping("/doShare.json")
	@ResponseBody
	public JsonResponse doShare(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String shareTypeStr = request.getParameter("shareType");
		Integer shareType = null;
		if(!StringUtils.isEmpty(shareTypeStr)){
			shareType = Integer.parseInt(shareTypeStr);
		}
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		shareService.doShare(userId, shareType, title, content);
		//3.结果处理
		return jsonResponse;
	}
	
}
