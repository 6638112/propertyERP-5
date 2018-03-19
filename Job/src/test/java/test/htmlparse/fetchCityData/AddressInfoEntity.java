/**   
* Filename:    AddressInfoEntity.java   
* @version:    1.0  
* Create at:   2014年11月6日 上午9:25:54   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年11月6日    shiyl      1.0         1.0 Version   
*/
package test.htmlparse.fetchCityData;

import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename:    AddressInfoEntity.java
 * @version:    1.0.0
 * Create at:   2014年11月6日 上午9:25:54
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年11月6日       shiyl             1.0             1.0 Version
 */
public class AddressInfoEntity {
	private AddressInfoEntity parentAddressInfoEntity;
	private String placeCode ;
	private String totalCode ;
	private String placeName ;
	private String subUrl ;
	
	public AddressInfoEntity(){}
	public AddressInfoEntity(AddressInfoEntity parentAddressInfoEntity,String placeCode,String totalCode,String placeName,String subUrl){
		this.parentAddressInfoEntity = parentAddressInfoEntity;
		this.placeCode = placeCode;
		this.totalCode = totalCode;
		this.placeName = placeName;
		this.subUrl = subUrl;
	}
	
	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("placeCode=").append(placeCode).append(";");
		sbf.append("totalCode=").append(totalCode).append(";");
		sbf.append("placeName=").append(placeName).append(";");
		sbf.append("subUrl=").append(subUrl).append(";");
		if(parentAddressInfoEntity==null){
			sbf.append("parentAddressInfoEntity=").append("null").append("};");
		}else{
			sbf.append("parentAddressInfoEntity=").append("{").append(parentAddressInfoEntity.toString()).append("};");
		}
		return sbf.toString();
	}
	
	public boolean checkIsEmptyData(){
		if(StringUtils.isEmpty(placeCode)){return true;}
		return false;
	}
	
	public AddressInfoEntity getParentAddressInfoEntity() {
		return parentAddressInfoEntity;
	}
	public void setParentAddressInfoEntity(AddressInfoEntity parentAddressInfoEntity) {
		this.parentAddressInfoEntity = parentAddressInfoEntity;
	}
	public String getPlaceCode() {
		return placeCode;
	}
	public void setPlaceCode(String placeCode) {
		this.placeCode = placeCode;
	}
	public String getTotalCode() {
		return totalCode;
	}
	public void setTotalCode(String totalCode) {
		this.totalCode = totalCode;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getSubUrl() {
		return subUrl;
	}
	public void setSubUrl(String subUrl) {
		this.subUrl = subUrl;
	}
	
}
