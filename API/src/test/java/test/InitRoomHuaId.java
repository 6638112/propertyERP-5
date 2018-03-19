/**   
* Filename:    InitRoomHuaId.java   
* @version:    1.0  
* Create at:   2014年7月25日 上午2:34:59   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月25日    shiyl      1.0         1.0 Version   
*/
package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.BaseTest;
import com.cnfantasia.server.api.commonBusiness.dao.ICommonRoomDao;
import com.cnfantasia.server.api.pub.generator.HuaGenerator;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.room.dao.IRoomBaseDao;
import com.cnfantasia.server.domainbase.room.entity.Room;

/**
 * Filename:    InitRoomHuaId.java
 * @version:    1.0.0
 * Create at:   2014年7月25日 上午2:34:59
 * Description: 更新room的huaId
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月25日       shiyl             1.0             1.0 Version
 */
public class InitRoomHuaId extends BaseTest{
	
//	@Test
	public void initRoomHuaId(){
		//查询所有的room信息
		IRoomBaseDao roomBaseDao = ctx.getBean("roomBaseDao", IRoomBaseDao.class);
		ICommonRoomDao commonRoomDao = ctx.getBean("commonRoomDao", ICommonRoomDao.class);
		List<Room> roomList = roomBaseDao.selectRoomByCondition(new HashMap<String, Object>(), false);
		//逐个查询门牌并更生成更新数据
		List<Room> toUpdRoomList = new ArrayList<Room>();
		for(Room tmpRoom:roomList){
			Room upd = new Room();
			upd.setId(tmpRoom.getId());
			GroupBuilding gb = commonRoomDao.selectGroupBuildingByRealRoomId(tmpRoom.gettRealRoomFId());
			String huaId = HuaGenerator.getRoomHuaId(gb.getName(), tmpRoom.getId());
			upd.setHuaId(huaId);
			toUpdRoomList.add(upd);
		}
		System.out.println(JSON.toJSONString(toUpdRoomList));
		//批量更新
		roomBaseDao.updateRoomBatch(toUpdRoomList);
	}
}
