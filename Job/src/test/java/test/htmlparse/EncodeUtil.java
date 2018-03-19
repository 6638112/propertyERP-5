/**   
* Filename:    EncodeUtil.java   
* @version:    1.0  
* Create at:   2014年11月5日 上午7:52:47   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年11月5日    shiyl      1.0         1.0 Version   
*/
package test.htmlparse;

/**
 * Filename:    EncodeUtil.java
 * @version:    1.0.0
 * Create at:   2014年11月5日 上午7:52:47
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年11月5日       shiyl             1.0             1.0 Version
 */
public class EncodeUtil {
	 /** 
   * 判断字符串的编码 
   * 
   * @param str 
   * @return 
   */  
  public static String getEncoding(String str) {  
      String encode = "GB2312";  
      try {  
          if (str.equals(new String(str.getBytes(encode), encode))) {  
              String s = encode;  
              return s;  
          }  
      } catch (Exception exception) {  
      }  
      encode = "ISO-8859-1";  
      try {  
          if (str.equals(new String(str.getBytes(encode), encode))) {  
              String s1 = encode;  
              return s1;  
          }  
      } catch (Exception exception1) {  
      }  
      encode = "UTF-8";  
      try {  
          if (str.equals(new String(str.getBytes(encode), encode))) {  
              String s2 = encode;  
              return s2;  
          }  
      } catch (Exception exception2) {  
      }  
      encode = "GBK";  
      try {  
          if (str.equals(new String(str.getBytes(encode), encode))) {  
              String s3 = encode;  
              return s3;  
          }  
      } catch (Exception exception3) {  
      }  
      return "";  
  }  
}
