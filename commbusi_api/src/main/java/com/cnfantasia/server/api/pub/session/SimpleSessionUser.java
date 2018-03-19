/**   
* Filename:    SimpleSessionUser.java   
* @version:    1.0  
* Create at:   2014年6月10日 下午12:38:27   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月10日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.pub.session;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

/**
 * Filename:    SimpleSessionUser.java
 * @version:    1.0.0
 * Create at:   2014年6月10日 下午12:38:27
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月10日       shiyl             1.0             1.0 Version
 */
public class SimpleSessionUser implements Serializable{
	private static final long serialVersionUID = 1L;
//	private static Log logger = LogFactory.getLog(SimpleSessionUser.class);
//	/**用户id*/
//	private BigInteger userId;
	/**账号类别*/
	private Integer accType;
	/**账号*/
	private String account;
	/**时间*/
	private String createTime;
	
	public SimpleSessionUser(){
	}
	public SimpleSessionUser(/*BigInteger userId,*/String account,Integer accType,String createTime){
		/*this.userId = userId;*/
		this.account = account;
		this.accType = accType;
		this.createTime = createTime;
	}
	
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getAccType() {
		return accType;
	}
	public void setAccType(Integer accType) {
		this.accType = accType;
	}
	/*public BigInteger getUserId() {
		return userId;
	}
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}*/
	
}
