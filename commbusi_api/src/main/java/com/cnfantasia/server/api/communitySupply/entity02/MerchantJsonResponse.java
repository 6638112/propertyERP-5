/**   
* Filename:    MerchantJsonResponse.java   
* @version:    1.0  
* Create at:   2014年11月26日 上午2:44:47   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年11月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.entity02;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * Filename:    MerchantJsonResponse.java
 * @version:    1.0.0
 * Create at:   2014年11月26日 上午2:44:47
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年11月26日       shiyl             1.0             1.0 Version
 */
public class MerchantJsonResponse{
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	private Integer status;
	private String message;
	private Integer total;
	private List<MerchantResult> results;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<MerchantResult> getResults() {
		return results;
	}
	public void setResults(List<MerchantResult> results) {
		this.results = results;
	}
	
}
