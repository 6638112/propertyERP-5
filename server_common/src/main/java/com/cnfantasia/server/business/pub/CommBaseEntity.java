/**   
* Filename:    CommBaseEntity.java   
* @version:    1.0  
* Create at:   2014年7月5日 上午10:07:55   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月5日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.business.pub;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Filename:    CommBaseEntity.java
 * @version:    1.0.0
 * Create at:   2014年7月5日 上午10:07:55
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月5日       shiyl             1.0             1.0 Version
 */
public abstract class CommBaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/** 新增时间 */
	protected String sys0AddTime;
	/** 更新时间 */
	protected String sys0UpdTime;
	/** 删除时间 */
	protected String sys0DelTime;
	/** 新增者 */
	protected BigInteger sys0AddUser;
	/** 修改者 */
	protected BigInteger sys0UpdUser;
	/** 删除者 */
	protected BigInteger sys0DelUser;
	/** 记录状态 */
	protected Integer sys0DelState;
	
	public String getSys0AddTime() {
		return sys0AddTime;
	}
	public void setSys0AddTime(String sys0AddTime) {
		this.sys0AddTime = sys0AddTime;
	}
	public String getSys0UpdTime() {
		return sys0UpdTime;
	}
	public void setSys0UpdTime(String sys0UpdTime) {
		this.sys0UpdTime = sys0UpdTime;
	}
	public String getSys0DelTime() {
		return sys0DelTime;
	}
	public void setSys0DelTime(String sys0DelTime) {
		this.sys0DelTime = sys0DelTime;
	}
	public BigInteger getSys0AddUser() {
		return sys0AddUser;
	}
	public void setSys0AddUser(BigInteger sys0AddUser) {
		this.sys0AddUser = sys0AddUser;
	}
	public BigInteger getSys0UpdUser() {
		return sys0UpdUser;
	}
	public void setSys0UpdUser(BigInteger sys0UpdUser) {
		this.sys0UpdUser = sys0UpdUser;
	}
	public BigInteger getSys0DelUser() {
		return sys0DelUser;
	}
	public void setSys0DelUser(BigInteger sys0DelUser) {
		this.sys0DelUser = sys0DelUser;
	}
	public Integer getSys0DelState() {
		return sys0DelState;
	}
	public void setSys0DelState(Integer sys0DelState) {
		this.sys0DelState = sys0DelState;
	}
	
}
