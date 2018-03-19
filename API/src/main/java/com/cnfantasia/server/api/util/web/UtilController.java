package com.cnfantasia.server.api.util.web;

import java.nio.charset.Charset;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.common.json.JsonResponse;

@Controller
@RequestMapping("/util")
public class UtilController extends BaseController {

	private Log logger = LogFactory.getLog(getClass());

	/**
     * 查看System属性，包含JDK的编码
     * @author wenfq 2016-05-06
     * @param request
     * @return
     */
    @RequestMapping("/viewSystemProperties.json")
    @ResponseBody
    public JsonResponse submitAllHobby(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        
        logger.info("Charset.defaultCharset().name(): " + Charset.defaultCharset().name());
        jsonResponse.putData("Charset.defaultCharset().name()", Charset.defaultCharset().name());

		Enumeration enu2 = System.getProperties().propertyNames();
		while (enu2.hasMoreElements()) {
			String key = (String) enu2.nextElement();
			jsonResponse.putData(key, System.getProperties().get(key));
		}
        
        return jsonResponse;
    }

}
