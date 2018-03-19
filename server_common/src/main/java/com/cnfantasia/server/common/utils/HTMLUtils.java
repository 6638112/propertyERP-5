package com.cnfantasia.server.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLUtils {
	public static String delHTMLTag(String htmlStr){ 
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
        String regEx_escape="&[^;]+;"; //定义HTML转义符，如&gt;&nbsp; 
        
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
        Matcher m_script=p_script.matcher(htmlStr); 
        htmlStr=m_script.replaceAll(""); //过滤script标签 
        
        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
        Matcher m_style=p_style.matcher(htmlStr); 
        htmlStr=m_style.replaceAll(""); //过滤style标签 
        
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_html=p_html.matcher(htmlStr); 
        htmlStr=m_html.replaceAll(""); //过滤html标签 
        
		Pattern p_escape = Pattern.compile(regEx_escape, Pattern.CASE_INSENSITIVE);
		Matcher m_escape = p_escape.matcher(htmlStr);
		htmlStr = m_escape.replaceAll(""); // 过滤html标签

       return htmlStr.trim(); //返回文本字符串 
    } 
	
	public static void main(String[] args) {
		System.out.println("    前面来点空格 http://t.cn/asdfa    ".trim());
		
		System.out.println(delHTMLTag(" <span> &gt;&lt;	</span>前后有空格、这是内容    <span style=\"white-space:pre\">	</span>"));
		System.out.println(delHTMLTag(" <span><u></u><br><hr>	</span>前后有空格、这是内容    <span style=\"white-space:pre\">	</span>"));
		System.out.println(delHTMLTag(" <span> &nbssafafap;	</span>前后有空格、这是内容    <span style=\"white-space:pre\">	</span>"));
		System.out.println(delHTMLTag(" <span> &nbsp;	</span>前后有空格、这是内容    <span style=\"white-space:pre\">	</span>"));
		System.out.println(delHTMLTag(" <span> &nbsp;	</span>前后有空格、这是内容    <span style=\"white-space:pre\">	</span>"));
		System.out.println(delHTMLTag(" <span> &nbsp;	</span>前后有空格、这是内容    <span style=\"white-space:pre\">	</span>"));
	}
}
