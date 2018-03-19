/**   
 * Filename:    WidthHeight.java   
 * @version:    1.0  
 * Create at:   2014年9月4日 上午3:00:06   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年9月4日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.commonBusiness.entity;

/**
 * Filename: WidthHeight.java
 * 
 * @version: 1.0.0 Create at: 2014年9月4日 上午3:00:06 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年9月4日 shiyl 1.0 1.0 Version
 */
public class WidthHeight {

	private Integer width;
	private Integer height;

	public WidthHeight() {
	}

	public WidthHeight(Integer width) {
		this(width, width);
	}

	public WidthHeight(Integer width, Integer height) {
		this.width = width;
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getFileName() {
		String res = null;
		if (width != null) {
			if (width.compareTo(height) == 0) {
				res = width + "";
			} else {
				res = width + "x" + height;
			}
		}
		return res;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result + ((width == null) ? 0 : width.hashCode());
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
		WidthHeight other = (WidthHeight) obj;
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (width == null) {
			if (other.width != null)
				return false;
		} else if (!width.equals(other.width))
			return false;
		return true;
	}
	
	
}
