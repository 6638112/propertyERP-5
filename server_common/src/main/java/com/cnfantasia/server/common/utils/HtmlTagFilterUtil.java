/**   
* Filename:    HtmlTagFilterUtil.java   
* @version:    1.0  
* Create at:   2014年11月20日 上午2:22:50   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年11月20日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.common.utils;

/**
 * Filename:    HtmlTagFilterUtil.java
 * @version:    1.0.0
 * Create at:   2014年11月20日 上午2:22:50
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年11月20日       shiyl             1.0             1.0 Version
 */
public final class HtmlTagFilterUtil {
	
	public static final String removeHtmlTagInfo(String content){
		if(StringUtils.isEmpty(content)){ return content; }
		content = content.trim();
		// 过滤文章内容中的html
		content = content.replaceAll("</?[^<]+>", "");
		// 去除字符串中的空格 回车 换行符 制表符 等
		content = content.replaceAll("\\s*|\t|\r|\n", "");
		// 去除空格
		content = content.replaceAll("&nbsp;", "");
		// 去掉其他一些字符
		content = content.replaceAll("\\\\","");
		return content;
	}
	
	public static final String removeHtmlTag(String content){
		if(StringUtils.isEmpty(content)){ return content; }
		content = content.trim();
		// 过滤文章内容中的html
		content = content.replaceAll("</?[^<]+>", "");
		// 去除字符串中的空格 回车 换行符 制表符 等
		content = content.replaceAll("\\s*|\t", "");
		// 去除空格
		content = content.replaceAll("&nbsp;", "");
		// 去掉其他一些字符
		content = content.replaceAll("\\\\","");
		return content;
	}
	
	
}
