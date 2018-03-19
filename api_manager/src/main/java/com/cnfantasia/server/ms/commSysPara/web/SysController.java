package com.cnfantasia.server.ms.commSysPara.web;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.utils.CommRequestFileParser;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.ms.pub.BaseController;

/**  
* 类说明   
*  
* @author yewj  
* @time   2016年7月15日 上午11:53:43
*/
@Controller
@RequestMapping("/sys")
public class SysController extends BaseController {
	
	private Log logger = LogFactory.getLog(this.getClass());
	
	@RequestMapping("/sysUpload.html")
	public ModelAndView sysUpload(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		//3.结果处理
		request.getSession().setAttribute("fortestupload", "fortestupload");
		modelAndView.setViewName("/sys/sysUpload");
		return modelAndView;
	}
	
	
	@RequestMapping("/upload.json")
	@ResponseBody
	public JsonResponse upload(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		String url = request.getRequestURL().toString();
		if(request.getSession().getAttribute("fortestupload") == null) {
			return jsonResponse;
		}
		
//		if(!url.contains("oos.jiefangqu.com") && (url.startsWith("http://127.0.0.1") || url.startsWith("http://192.168.1.45") || url.startsWith("http://oos.linlile"))) {
			//1.搜集参数
			String path = ParamUtils.getString(request, "path", null);
			if(path == null) {
				jsonResponse.setStatus("0001");
				jsonResponse.setMessage("文件根路径为空");
				return jsonResponse;
			}
			RequestFileEntity iconImage = CommRequestFileParser.parseRequsetFileInfo(request, "icon");
			validateOptionIcon(iconImage);
			
			String serverPath = path + "/" + iconImage.getFileName();
			
			//2.交互
			try {
				ApplicationContextBothUtil.getFileServerService().uploadFile(serverPath, iconImage.getFileContent());
			} catch (IOException e) {
				logger.info("upload MsPrizeActivity file cause exception:"+e.getMessage(), e);
				throw new BusinessRuntimeException("PrizeActivityService.addMsPrizeActivity.uploadIcon.error",new Object[]{iconImage.getFileName()});
			}
//		}
		//3.结果处理
		
		return jsonResponse;
	}
	
	private void validateOptionIcon(RequestFileEntity shareIconImage){
		if(shareIconImage!=null&&shareIconImage.getFileSize()!=null&&shareIconImage.getFileSize()>0){
			long nowK = shareIconImage.getFileSize()/1024;
			long limitK = 1024;
			if(nowK>limitK){
				throw new BusinessRuntimeException("PrizeActivityController.validateOptionIcon.tooBig",new Object[]{nowK,limitK});
			}
		}
	}

}
