/**   
* Filename:    AddressDataInfo.java   
* @version:    1.0  
* Create at:   2015年7月31日 下午12:56:57   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年7月31日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.cache.address;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import com.cnfantasia.server.api.cache.IRedisMapAble;

/**
 * Filename:    AddressDataInfo.java
 * @version:    1.0.0
 * Create at:   2015年7月31日 下午12:56:57
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年7月31日       shiyl             1.0             1.0 Version
 */
public class AddressDataInfo implements IRedisMapAble{

	private BigInteger id;
	private String name;
	private BigInteger parentId;
	
	public AddressDataInfo(){}
	public AddressDataInfo(BigInteger id,String name,BigInteger parentId){
		this.id = id;
		this.name = name;
		this.parentId = parentId;
	}
	
	@Override
	public Map<String,String> toMap(){
		Map<String,String> tmpMap = new HashMap<String, String>();
		tmpMap.put(AddressDataConstant.KEY_Id, id==null?AddressDataConstant.NULL_VALUE:id.toString());
		tmpMap.put(AddressDataConstant.KEY_Name, name==null?AddressDataConstant.NULL_VALUE:name);
		tmpMap.put(AddressDataConstant.KEY_ParentId, parentId==null?AddressDataConstant.NULL_VALUE:parentId.toString());
		return tmpMap;
	}
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigInteger getParentId() {
		return parentId;
	}
	public void setParentId(BigInteger parentId) {
		this.parentId = parentId;
	}
	

}
