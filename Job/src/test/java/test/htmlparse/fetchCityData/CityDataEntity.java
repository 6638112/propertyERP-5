/**   
* Filename:    CityDataEntity.java   
* @version:    1.0  
* Create at:   2014年11月5日 上午7:30:20   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年11月5日    shiyl      1.0         1.0 Version   
*/
package test.htmlparse.fetchCityData;

import java.io.Serializable;

/**
 * Filename:    CityDataEntity.java
 * @version:    1.0.0
 * Create at:   2014年11月5日 上午7:30:20
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年11月5日       shiyl             1.0             1.0 Version
 */
public class CityDataEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**编码*/
	private String code;
	/**名称*/
	private String name;
	/**类别*/
	private String type;
	
	public CityDataEntity(){}
	
	public CityDataEntity(String code,String name,String type){
		this.code = code;
		this.name = name;
		this.type = type;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
