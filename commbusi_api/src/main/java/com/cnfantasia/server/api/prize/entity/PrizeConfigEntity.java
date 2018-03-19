/**   
* Filename:    PrizeConfigEntity.java   
* @version:    1.0  
* Create at:   2014年7月8日 上午2:35:02   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.entity;

import java.io.Serializable;

/**
 * Filename:    PrizeConfigEntity.java
 * @version:    1.0.0
 * Create at:   2014年7月8日 上午2:35:02
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月8日       shiyl             1.0             1.0 Version
 */
public class PrizeConfigEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**A类中奖概率*/
	private double propertyA;
	/**B类中奖概率*/
	private double propertyB;
	/**C类中奖概率*/
	private double propertyC;
	/**D类中奖概率*/
	private double propertyD;
	
	/**总房间数目*/
	private int totalRooms;

	public double getPropertyA() {
		return propertyA;
	}

	public void setPropertyA(double propertyA) {
		this.propertyA = propertyA;
	}

	public double getPropertyB() {
		return propertyB;
	}

	public void setPropertyB(double propertyB) {
		this.propertyB = propertyB;
	}

	public double getPropertyC() {
		return propertyC;
	}

	public void setPropertyC(double propertyC) {
		this.propertyC = propertyC;
	}

	public double getPropertyD() {
		return propertyD;
	}

	public void setPropertyD(double propertyD) {
		this.propertyD = propertyD;
	}

	public int getTotalRooms() {
		return totalRooms;
	}

	public void setTotalRooms(int totalRooms) {
		this.totalRooms = totalRooms;
	}
	
	
}
