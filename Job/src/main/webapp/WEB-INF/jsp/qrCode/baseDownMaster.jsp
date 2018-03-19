<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path2 = request.getContextPath();
	String basePath2 = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path2;
%>
<!DOCTYPE html>
<html lang = "zh-cn">
<head>
<meta charset = "gb2312">

<meta name="viewport" content="width=device-width; initial-scale=1.0">

<title>下载页面</title>
	<script type="text/javascript">
		function androidDown(){
			if (false) {
				window.location="http://a.app.qq.com/o/simple.jsp?pkgname=com.jiefangqu.living";
			}else{
				<%
				if(request.getAttribute("androidPath")!=null){
					%>window.location="<%=request.getAttribute("androidPath")%>";<%
				}
				%>
			}
		}
		function iosDown(){
			if (false) {
				window.location="http://a.app.qq.com/o/simple.jsp?pkgname=com.jiefangqu.living";
			}else{
				<%
				if(request.getAttribute("iosPath")!=null){
					%>window.location="<%=request.getAttribute("iosPath")%>";<%
				}
				%>
			}
		}
		function autoJump(){
			if (false) {
				window.location="http://a.app.qq.com/o/simple.jsp?pkgname=com.jiefangqu.living";
			}else{
				window.location="<%=request.getAttribute("androidPath")%>";
			}
		}
	</script>
<link href="<%=basePath2%>/css/main.css" rel="stylesheet" type="text/css">
</head>
<body onload="autoJump_donotUse();">
<section class="main-box">
    
    <section class="layer dsn"><img src="<%=basePath2%>/images/layer-ad.png"/></section>
    <section class="layer01 dsn"><img src="<%=basePath2%>/images/layer.png"/></section>
    
    <section class="main">
       <div class="txt-01">下载解放区师傅端，接单无压力</div>
       <figure>
       <img src="<%=basePath2%>/images/logo-small-master.png"/>
       </figure>
       
    </section>
    <section class="dl-btn">
        <div class="zocial android b01" onclick="androidDown();">Android 下载 <%=request.getAttribute("androiddVer")==null?"":request.getAttribute("androiddVer")%></div>
        <div class="zocial appstore b02" onclick="iosDown();">App Store 下载 <%=request.getAttribute("iosVer")==null?"敬请期待":request.getAttribute("iosVer")%></div>
    </section>
    <footer>
        <p>Copyright <span>&copy;</span> 2014 深圳前海邻里乐科技服务有限公司 <br />All rights reserved.</p>
    </footer>
</section>
</body>
<script type="text/javascript" src="<%=basePath2%>/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=basePath2%>/js/jquery.common.js"></script>
<script>
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F71a32d0a717889a15a9935bd0ee1c1bb' type='text/javascript'%3E%3C/script%3E"));
</script>
</html> 