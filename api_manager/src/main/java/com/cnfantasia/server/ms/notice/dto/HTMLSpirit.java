package com.cnfantasia.server.ms.notice.dto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 类说明：过滤html标签，返回纯文本
 *
 * @author hunter
 * @since 2014年6月9日下午3:37:56
 */
public class HTMLSpirit {
    
    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
    
    public static String delHTMLTag(String htmlStr) {
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签

        return htmlStr.trim(); // 返回文本字符串
    }
    
    public static void main(String[] args){
    	String msg = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"+
    				 "<string xmlns=\"http://tempuri.org/\">91536301594060319</string>";
    	String result = delHTMLTag(msg);
    	System.out.println(result);
    }
    
}