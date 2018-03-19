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

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.exception.CommunicateRuntimeException;
import com.cnfantasia.server.common.exception.FocRuntimeException;
import com.cnfantasia.server.common.exception.TimeOutRuntimeException;
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
	
	@Override
	public JsonResponse checkAndConvert2JsonResponse(String res) {
		if(!StringUtils.isEmpty(res)){
			JsonResponse jsonResponse = null;
			try{
				jsonResponse = JSON.parseObject(res, JsonResponse.class);
			}catch(Exception e){
				throw new CommunicateRuntimeException("response.parseObject.failed",e);//通讯异常
			}
			String statuCode=jsonResponse.getStatus();
			if(statuCode.equals(CommConstants.ResponseStatus.SUCCESS)){
				return jsonResponse;
			}else if(statuCode.equals(CommConstants.ResponseStatus.LOGIN_TIME_OUT)){
				FocRuntimeException exception = new TimeOutRuntimeException();
//				exception.setErrorMsg(jsonResponse.getMessage());
				throw exception;
			}else{
				FocRuntimeException exception = new FocRuntimeException(jsonResponse.getErrcode());
				exception.setErrorMsg(jsonResponse.getMessage());
				throw exception;
			}
		}else{
			throw new CommunicateRuntimeException();//通讯异常
		}
	}
	
}
