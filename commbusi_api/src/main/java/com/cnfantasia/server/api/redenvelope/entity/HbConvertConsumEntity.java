/**   
 * Filename:    HbConvertConsumEntity.java   
 * @version:    1.0  
 * Create at:   2014年6月26日 上午8:55:37   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年6月26日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.redenvelope.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.redenvelope.util.OrderType2ConsumDesc;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.common.utils.PriceUtil;

/**
 * Filename: HbConvertConsumEntity.java
 * 
 * @version: 1.0.0 Create at: 2014年6月26日 上午8:55:37 Description:粮票兑换和消费信息
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年6月26日 shiyl 1.0 1.0 Version
 */
public class HbConvertConsumEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Integer TableType_Convert = 0;
	private static final Integer TableType_Consum = 1;
	private Log logger = LogFactory.getLog(getClass());
	/** 记录Id */
	private BigInteger id;
	/** 记录表标识：0兑换，1消费 */
	private Integer tableType;
	/** 操作时间 */
	private String time;
	/** 用户 */
	private BigInteger userId;
	/** 金额 */
	private Long amount;
	/** 折扣/消费类型 */
	private String desc;
	/** 用户详情 */
	private UserSimpleEntity user;

	public HbConvertConsumEntity() {
	}

	public HbConvertConsumEntity(BigInteger id, Integer tableType, String time, BigInteger userId, Long amount,
			String desc, UserSimpleEntity user) {
		this.id = id;
		this.tableType = tableType;
		this.time = time;
		this.userId = userId;
		this.amount = amount;
		this.desc = desc;
		this.user = user;
	}

	public BigDecimal getAmountDiv100() {
		if(amount==null){return BigDecimal.valueOf(0L);}
		return PriceUtil.div100(amount);
	}

	public String getDescDetail() {
		if (TableType_Convert.compareTo(tableType) == 0) {//兑换，直接返回折扣
			return desc;
		} else if (TableType_Consum.compareTo(tableType) == 0) {//消费，根据订单类型获取消费描述
			Integer orderType = null;
			try {
				orderType = Integer.parseInt(desc);
			} catch (Exception e) { 
				logger.debug("HbConvertConsumEntity.getDescDetail cause exception,tableType="+tableType+",desc="+desc,e);
			}
			return OrderType2ConsumDesc.getConsumInfoByOrderType(orderType);
		} else {
			return "";
		}
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("id=").append(id).append(";");
		sbf.append("tableType=").append(tableType).append(";");
		sbf.append("time=").append(time).append(";");
		sbf.append("userId=").append(userId).append(";");
		sbf.append("amount=").append(amount).append(";");
		sbf.append("desc=").append(desc).append(";");
		sbf.append("user=").append(user).append(";");
		return sbf.toString();
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Integer getTableType() {
		return tableType;
	}

	public void setTableType(Integer tableType) {
		this.tableType = tableType;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public BigInteger getUserId() {
		if (user == null) {
			return null;
		}
		return user.getId();
	}

	public void setUserId(BigInteger userId) {
		if (user == null) {
			user = new UserSimpleEntity();
		}
		user.setId(userId);
	}

	public UserSimpleEntity getUser() {
		return user;
	}

	public void setUser(UserSimpleEntity user) {
		this.user = user;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
