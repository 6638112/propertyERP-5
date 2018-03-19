/**   
* Filename:    UserSimpleEntity.java   
* @version:    1.0  
* Create at:   2015年1月22日 上午2:18:19   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月22日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.user.entity;

import com.cnfantasia.server.domainbase.user.entity.User;

/**
 * Filename:    UserSimpleEntity.java
 * @version:    1.0.0
 * Create at:   2015年1月22日 上午2:18:19
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月22日       shiyl             1.0             1.0 Version
 */
public class UserSimpleEntity extends User implements Comparable<UserSimpleEntity>{
	private static final long serialVersionUID = 1L;
	
	/**是否为对应真实门牌的管理员 {"true":"是","false":"否"} */
	private Boolean  ext_room_isAdmin;
	public Boolean getExt_room_isAdmin() {
		return ext_room_isAdmin;
	}
	public void setExt_room_isAdmin(Boolean ext_room_isAdmin) {
		this.ext_room_isAdmin = ext_room_isAdmin;
	}
	
	public boolean fetchIsAdmin(){
		if(ext_room_isAdmin!=null&&ext_room_isAdmin){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public int compareTo(UserSimpleEntity o) {
		if(o==null){
			return 0;
		}
		return this.getId().compareTo(o.getId());
	}
	
}
