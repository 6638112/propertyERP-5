/**   
* Filename:    TestFetchFile.java   
* @version:    1.0  
* Create at:   2015年5月21日 上午8:49:24   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年5月21日    shiyl      1.0         1.0 Version   
*/
package test.httpclient;

import java.io.IOException;

import org.apache.http.HttpException;

import com.cnfantasia.server.common.utils.HttpClientUtil;

/**
 * Filename:    TestFetchFile.java
 * @version:    1.0.0
 * Create at:   2015年5月21日 上午8:49:24
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年5月21日       shiyl             1.0             1.0 Version
 */
public class TestFetchFile {
	public static void main(String[] args) throws HttpException, IOException {
		String imgProfile = "http://q.qlogo.cn/qqapp/101097644/DA3DD734E2D2867538D3EBCC93936E8E/100";
		byte[] res = HttpClientUtil.doGet(imgProfile);
		System.out.println(res);
	}
}
