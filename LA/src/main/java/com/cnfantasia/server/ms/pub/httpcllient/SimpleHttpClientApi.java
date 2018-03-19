/**   
* Filename:    SimpleHttpClientApi.java   
* @version:    1.0  
* Create at:   2014年9月25日 上午9:19:25   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月25日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.ms.pub.httpcllient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.common.exception.CommunicateRuntimeException;
import com.cnfantasia.server.common.httpcllient.SimpleHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename:    SimpleHttpClientApi.java
 * @version:    1.0.0
 * Create at:   2014年9月25日 上午9:19:25
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月25日       shiyl             1.0             1.0 Version
 */
public class SimpleHttpClientApi extends SimpleHttpClient{
	
	private Log logger = LogFactory.getLog(getClass());
	
	@Override
	public JsonResponse checkAndConvert2JsonResponse(String res) {
		if(!StringUtils.isEmpty(res)){
			JsonResponse jsonResponse = JSON.parseObject(res, JsonResponse.class);

			return jsonResponse;
			/*if(statuCode.equals(CommConstants.ResponseStatus.SUCCESS)){
				return jsonResponse;
			}else if(statuCode.equals(CommConstants.ResponseStatus.LOGIN_TIME_OUT)){
				FocRuntimeException exception = new TimeOutRuntimeException();
			//				exception.setErrorMsg(jsonResponse.getMessage());
				throw exception;
			}else{
				FocRuntimeException exception = new FocRuntimeException(jsonResponse.getErrcode());
				exception.setErrorMsg(jsonResponse.getMessage());
				throw exception;
			}*/
		}else{
			throw new CommunicateRuntimeException();//通讯异常
		}
	}
	
	@Override
	public JsonResponse submitSimple(String uri, Map<String, Object> params, List<Header> appendHeaderList) {
		appendHeaderList = appendSubChannelToHeaders(appendHeaderList, params);
		
		return super.submitSimple(uri, params, appendHeaderList);
	}

	private List<Header> appendSubChannelToHeaders(List<Header> appendHeaderList, Map<String, Object> params) {
		if (appendHeaderList == null) {
			appendHeaderList = new ArrayList<Header>();
		}
		//用来区别移动应用
		boolean hasSubChannelId = false;
		if(appendHeaderList != null) {
			for(Header header : appendHeaderList) {
				if("subChannelId".equals(header.getName())) {
					hasSubChannelId = true;
					logger.debug("hasSubChannel");
					break;
				}
			}
		}
		if(!hasSubChannelId) {
			Header subChannelIdHeader = new BasicHeader("subChannelId", "7");
			appendHeaderList.add(subChannelIdHeader);
			logger.debug("does not hasSubChannel");
		}
		return appendHeaderList;
	}

	@Override
	public JsonResponse doGet(String uri, Map<String, Object> params, List<Header> appendHeaderList) {

		appendHeaderList = appendSubChannelToHeaders(appendHeaderList, params);

		return super.doGet(uri, params, appendHeaderList);
	}
}
