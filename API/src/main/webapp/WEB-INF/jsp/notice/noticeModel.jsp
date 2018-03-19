<%@page import="com.cnfantasia.server.common.utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset = "UTF-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0">
<title>${tagTitle}</title>
<link href="../htmlPages/notice/css/main.css?v=1.0.220170706" rel="stylesheet" type="text/css">

</head>
<body>

<header>
	<h1>${title}</h1>
	<p>${time} 解放区</p>
</header>
<%
if(!StringUtils.isEmpty(request.getAttribute("picUrl"))){
	%>
	<section>
		<figure><img src="<%=request.getAttribute("picUrl") %>" /></figure>
	</section>
	<%
}
%>
<section style="margin-top: 15px;">
    <c:if test="${userQuestionDetail == null}">
        <p>${content}</p>
    </c:if>

    <%--寻求帮助部分--%>
    <c:if test="${userQuestionDetail != null}">
        <p>${userQuestionDetail.content}</p>
        <ul class="menu-img displaybox">
        	<c:set var="count" value="3"/>
	        <c:forEach items="${userQuestionDetail.picList }" var="picurl">
	        	<c:set var="count" value="${count - 1 }"/>
	            <li class="boxflex01"><img src="${picurl }?x-oss-process=image/resize,m_fill,w_240,h_240,limit_0/format,jpg/quality,q_90/interlace,1" border="0" /></li>
	        </c:forEach>
	        <c:if test="${count > 0 }">
	        	<c:forEach begin="1" end="${count }" step="1">
		            <li class="boxflex01"></li>
	        	</c:forEach>
	        </c:if>
        </ul>
        <p class="bordertopgrey guanjia"><img src="../htmlPages/notice/images/guanjia.png"/> <span class="red">管家回复</span>：${userQuestionDetail.answerContent}</p>

    </c:if>
</section>


<footer>
	<p class="bordertopgrey">以上内容由解放区APP提供</p>
</footer>
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1261123817'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1261123817' type='text/javascript'%3E%3C/script%3E"));</script>
</body>
</html> 