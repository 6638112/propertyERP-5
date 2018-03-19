/**   
* Filename:    OperationHomeSupplyTypeEntity.java   
* @version:    1.0  
* Create at:   2015年8月19日 上午11:44:35   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年8月19日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.operation.entity;

import java.math.BigInteger;

import com.cnfantasia.server.api.communitySupply.util.CommunitySupplyTypeUtil;
import com.cnfantasia.server.api.operation.constant.OperationDict;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;
import com.cnfantasia.server.domainbase.operationHomeSupplyType.entity.OperationHomeSupplyType;

/**
 * Filename:    OperationHomeSupplyTypeEntity.java
 * @version:    1.0.0
 * Create at:   2015年8月19日 上午11:44:35
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年8月19日       shiyl             1.0             1.0 Version
 */
public class OperationHomeSupplyTypeEntity extends AbstractOperationHomeSupplyType{
	private static final long serialVersionUID = 1L;
	
	/**普通商家类别数据时,需要传入CommunitySupplyType*/
	public OperationHomeSupplyTypeEntity(OperationHomeSupplyType operationHomeSupplyType,CommunitySupplyType communitySupplyType){
		super(operationHomeSupplyType);
		this.communitySupplyType = communitySupplyType;
	}
	
	/**商家类别数据*/
	private CommunitySupplyType communitySupplyType;
	public void setCommunitySupplyType(CommunitySupplyType communitySupplyType) {
		this.communitySupplyType = communitySupplyType;
	}
	
	/**是否为普通商家类别*/
	private boolean checkIsCommSupplyType(){
		if(super.getDataType().compareTo(OperationDict.OperationHomeSupplyType_DataType.common_Type)==0){
			return true;
		}else{
			return false;
		}
	}
	/**返回数据是否可被重载*/
	private boolean checkIsDataReload(){
		if(checkIsCommSupplyType()&&communitySupplyType!=null){
			return true;
		}else{
			return false;
		}
	}
	
	public BigInteger fetchTopParentId(){
		if(checkIsDataReload()){return communitySupplyType.getParentId();}
		return null;
	}
	
	@Override
	public String getName() {
		if(checkIsDataReload()&&StringUtils.isEmpty(super.getName())){return communitySupplyType.getName();}
		return super.getName();
	}
	
	@Override
	public String getIconName() {
		if(checkIsDataReload()&&StringUtils.isEmpty(super.getIconName())){return CommunitySupplyTypeUtil.getIconName(communitySupplyType);}
		return super.getIconName();
	}
	
	
	
}
