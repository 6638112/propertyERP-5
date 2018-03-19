<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cnfantasia.server.api.pub.header.HeaderConstant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimal-ui">
    <link rel="dns-prefetch" href="//jiefangqu.com">

    <title>限时抢购</title>
    <link rel="stylesheet" href="../css/groupbuying.css?v20160815" type="text/css">
</head>

<body style="opacity:0">
<div class="sectionBox list-onsell">
    <c:if test="${fn:length(limitBuyInfoList) <= 0}">
        <div class="f16 mtop10 t-center lineheight140">暂无限时抢购</div>
    </c:if>
    <c:forEach var="limitBuy" items="${limitBuyInfoList}">
        <section class="divide-box10 bgwhite"></section>
        <div class="pos-relative overhidden m010 groupbuying-list" style="background: #EAF4FF;">
            <a class="disblock" href="${limitBuy.linkUrl}">
                <div class="item-list-info">
                    <div class="lineheight140 text-clamp f16">${limitBuy.limitBuyTitle}</div>
                    <div class="w150 lineheight140 pright10 red">￥<span class="f22 bold">${limitBuy.limitBuyPrice}</span></div>
                    <input class="input-btn-radius4 bgred" type="button" name="button" value="我要抢购" />
                </div>
                <img style="max-width:45%; float: right;" class="disblock" src="${limitBuy.limitBuyPic}<c:choose><c:when test="${fn:contains(limitBuy.limitBuyPic, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,w_250,h_203,limit_0/format,jpg/quality,q_80/interlace,1" />
            </a>
        </div>
    </c:forEach>
</div>

<script src="../js/jquery-1.11.2.min.js"></script>
<script src="../js/fastclick.js"></script>
<script>
    $(function(){
        new FastClick(document.body);
    	
        if( $('.groupbuying-list').length > 0 || ( $('.groupbuying-list').length === 0 && getUrlParam('pic') == null ) ){
    		$('body').css('opacity','1');
    	}
    	//如果没有商品，则跳转到活动预告
    	if( $('.groupbuying-list').length === 0 && getUrlParam('pic') != null ){
    		location.href = 'http://resource.jiefangqu.com/docs/actPreview/index.htm?pic=' + getUrlParam('pic') + '?x-oss-process=image/resize,w_560/format,jpg/quality,q_80/interlace,1';
    	}
    	function getUrlParam(name) {
    		//构造一个含有目标参数的正则表达式对象  
    		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    		//匹配目标参数  
    		var r = window.location.search.substr(1).match(reg);
    		//返回参数值  
    		if (r != null)
    			return unescape(r[2]);
    		return null;
    	}

    });
</script>
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1261123817'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1261123817' type='text/javascript'%3E%3C/script%3E"));</script>
</body>
</html>