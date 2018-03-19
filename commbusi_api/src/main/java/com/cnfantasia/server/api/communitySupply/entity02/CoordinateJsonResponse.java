/**   
 * Filename:    CoordinateJsonResponse.java   
 * @version:    1.0  
 * Create at:   2014年11月26日 上午3:05:26   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年11月26日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.communitySupply.entity02;

import com.alibaba.fastjson.JSON;

/**
 * Filename: CoordinateJsonResponse.java
 * 
 * @version: 1.0.0 Create at: 2014年11月26日 上午3:05:26 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年11月26日 shiyl 1.0 1.0 Version
 */
public class CoordinateJsonResponse {
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	private Integer status;
	private CoordinateResult result;

	public CoordinateResult getResult() {
		return result;
	}

	public void setResult(CoordinateResult result) {
		this.result = result;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public class CoordinateResult {
		@Override
		public String toString() {
			return JSON.toJSONString(this);
		}

		private Integer precise;
		private Integer confidence;
		private String level;

		public Integer getPrecise() {
			return precise;
		}

		public void setPrecise(Integer precise) {
			this.precise = precise;
		}

		public Integer getConfidence() {
			return confidence;
		}

		public void setConfidence(Integer confidence) {
			this.confidence = confidence;
		}

		public String getLevel() {
			return level;
		}

		public void setLevel(String level) {
			this.level = level;
		}

		private Location location;

		public Location getLocation() {
			return location;
		}

		public void setLocation(Location location) {
			this.location = location;
		}

	}

}
