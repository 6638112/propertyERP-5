/**   
* Filename:    Test.java   
* @version:    1.0  
* Create at:   2014年9月28日 上午8:52:20   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月28日    shiyl      1.0         1.0 Version   
*/
package test.baiduPush;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * Filename:    Test.java
 * @version:    1.0.0
 * Create at:   2014年9月28日 上午8:52:20
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月28日       shiyl             1.0             1.0 Version
 */
public class Test {
	public static void main(String[] args) {
		Map<String,Object> messageMap = new HashMap<String, Object>();
		messageMap.put("title", "解放区");
		messageMap.put("description", "您有门牌通过验证");
		messageMap.put("pkg_content", "intent:#Intent;launchFlags=0x10000000;component=com.jiefangqu.living/.act.ChangePlaceAct;end");
		messageMap.put("open_type", "2");
		System.out.println(JSON.toJSONString(messageMap));
		System.out.println(JSON.toJSONString("{\"title\":\"解放区\",\"description\":\"您有门牌通过验证\",\"pkg_content\":\"intent:#Intent;launchFlags=0x10000000;component=com.jiefangqu.living/.act.ChangePlaceAct;end\",\"open_type\":\"2\"}"));
		System.out.println(JSON.toJSONString(messageMap).equals("{\"title\":\"解放区\",\"description\":\"您有门牌通过验证\",\"pkg_content\":\"intent:#Intent;launchFlags=0x10000000;component=com.jiefangqu.living/.act.ChangePlaceAct;end\",\"open_type\":\"2\"}"));
		System.out.println("{\"description\":\"\\r\\n亲爱的业主们：\\r\\n  小长假归来是否已调整过来了呢？大家在回归正常生活作息的同时，也要记得注意水、电、煤的"
					+ "安全。\\r\\n  在此，祝业主们生活开心，工作顺心！\",\"open_type\":\"2\",\"pkg_content\":\"intent:#Intent;launc"
					+ "hFlags=0x10000000;component=com.jiefangqu.living/.act.property.AnnouncementDetailsAct;S.id=100018;end\",\"titl"
					+ "e\":\"长假归来温馨提示\"}");
	}
}
