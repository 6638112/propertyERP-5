/**   
* Filename:    MljiaBaseJsonResponse.java   
* @version:    1.0  
* Create at:   2015年1月6日 上午7:52:13   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月6日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.entity2mljia;

import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Decoder;

import com.alibaba.fastjson.JSON;

/**
 * Filename:    MljiaBaseJsonResponse.java
 * @version:    1.0.0
 * Create at:   2015年1月6日 上午7:52:13
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月6日       shiyl             1.0             1.0 Version
 */
public class MljiaBaseJsonResponse {
	private Log logger = LogFactory.getLog(getClass());
	
	/**
	 *后台执行操作状态，通常返回200:成功400：失败，201：登录超时，其它状态需说明 
	 */
	private Integer status;
	/**
	 * 后台操作错误信息
	 */
	private String errorMessage;
	/**
	 * 返回数据总条数（一般用于分页）
	 */
	private Integer totalCount;
	/**
	 * 返回数据内容（一般为返回格式为JSON字符串，需解密）
	 */
	private String content;
	public MljiaBaseJsonResponse(){}
	public MljiaBaseJsonResponse(Integer status,String errorMessage,Integer totalCount,String content){
		this.status = status;
		this.errorMessage = errorMessage;
		this.totalCount = totalCount;
		this.content = content;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
		
	}
	
	private String getContentDecode() {
		String resData = null;
		if(this.content!=null){
			try {
				byte[] res;
				res = new BASE64Decoder().decodeBuffer(this.content);
				resData = new String(res,"UTF-8");
			} catch (IOException e) {
				logger.debug("MljiaBaseJsonResponse decodeBuffer cause exception,content is "+content, e);
			}
		}
		return resData;
	}
	
	
	public <T> T parseContentObject(Class<T> classType){
		T res = JSON.parseObject(getContentDecode(),classType);
		return res;
	}
	public <T> List<T> parseContentList(Class<T> classType){
		List<T> res = JSON.parseArray(getContentDecode(),classType);
		return res;
	}
	
}
