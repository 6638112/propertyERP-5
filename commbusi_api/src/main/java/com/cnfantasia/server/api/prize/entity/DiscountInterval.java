/**   
* Filename:    DiscountInterval.java   
* @version:    1.0  
* Create at:   2014年7月8日 上午3:00:18   
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
 * Filename:    DiscountInterval.java
 * @version:    1.0.0
 * Create at:   2014年7月8日 上午3:00:18
 * Description:折扣区间
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月8日       shiyl             1.0             1.0 Version
 */
public class DiscountInterval implements Serializable{
	private static final long serialVersionUID = 1L;
	/**开始折扣*/
	private DiscountValueEntity start;
	/**截止折扣*/
	private DiscountValueEntity end;
	/**
	 * 折扣区间 [start,end);
	 * @param start
	 * @param end
	 */
	public DiscountInterval(DiscountValueEntity start,DiscountValueEntity end){
		this.start = start;
		this.end = end;
	}
	public boolean isContain(DiscountValueEntity data){
		if(data.doubleValue()>=start.doubleValue()&&data.doubleValue()<end.doubleValue()){
			return true;
		}else{
			return false;
		}
	}
	public boolean isContain(double data){
		if(data>=start.doubleValue()&&data<end.doubleValue()){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "["+start+","+end+")";
	}
	
	public DiscountValueEntity getStart() {
		return start;
	}
	public void setStart(DiscountValueEntity start) {
		this.start = start;
	}
	public DiscountValueEntity getEnd() {
		return end;
	}
	public void setEnd(DiscountValueEntity end) {
		this.end = end;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DiscountInterval other = (DiscountInterval) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}
	
	
}
