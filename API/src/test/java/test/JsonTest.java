/**   
* Filename:    JsonTest.java   
* @version:    1.0  
* Create at:   2014年5月13日 上午1:08:02   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月13日    shiyl      1.0         1.0 Version   
*/
package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.alibaba.fastjson.JSON;


/**
 * Filename:    JsonTest.java
 * @version:    1.0.0
 * Create at:   2014年5月13日 上午1:08:02
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月13日       shiyl             1.0             1.0 Version
 */
public class JsonTest {
	/**
	 * 查询省列表：
	 * http://cyz.colourlife.com/site/regions?region_id=0
	 * 根据省查询市列表
	 * http://cyz.colourlife.com/site/regions?parent_id=14
	 * 根据市查询地区列表
	 * http://cyz.colourlife.com/site/regions?parent_id=167
	 * 根据地区查询小区列表
	 * http://cyz.colourlife.com/site/communities?region_id=1580
	 * 根据小区查询楼栋
	 * http://cyz.colourlife.com/site/builds?community_id=989
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		File file =new File("src/test/java/test/jsonData.txt");
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
		String line = null;
		while((line=br.readLine())!=null){
			System.out.println(line);
			Object obj = JSON.parse(line);
			System.out.println(obj);
		}
		br.close();
	}
}
