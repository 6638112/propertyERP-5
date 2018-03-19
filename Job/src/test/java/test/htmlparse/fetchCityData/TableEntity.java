/**   
* Filename:    TableEntity.java   
* @version:    1.0  
* Create at:   2014年6月18日 上午2:46:38   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月18日    shiyl      1.0         1.0 Version   
*/
package test.htmlparse.fetchCityData;

import com.alibaba.fastjson.JSON;

/**
 * Filename:    TableEntity.java
 * @version:    1.0.0
 * Create at:   2014年6月18日 上午2:46:38
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月18日       shiyl             1.0             1.0 Version
 */
public class TableEntity {
	private String index;
	private String company;
	private String code;
	private String level;
	private String name;
	private String tel;
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
}
