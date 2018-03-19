<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path2 = request.getContextPath();
	String basePath2 = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path2;
%>
<!DOCTYPE html>
<htmlang = "zh-cn">
<head>
<meta charset = "gb2312">

<meta name="viewport" content="width=device-width; initial-scale=1.0">

<title>下载页面</title>
	<script type="text/javascript">
		function androidDown(){
			<%
			if(request.getAttribute("androidPath")!=null){
				%>window.location="<%=request.getAttribute("androidPath")%>";<%
			}
			%>
		}
		function iosDown(){
			<%
			if(request.getAttribute("iosPath")!=null){
				%>window.location="<%=request.getAttribute("iosPath")%>";<%
			}
			%>
		}
		function autoJump(){
			if (isWeiXin()) {
				
			}else{
				window.location="<%=request.getAttribute("androidPath")%>";
			}
		}
	</script>
<link href="../css/main.css" rel="stylesheet" type="text/css">
</head>
<body onload="autoJump();">
<section class="main-box">
    
    <section class="layer dsn"><img src="../images/layer-ad.png"/></section>
    <section class="layer01 dsn"><img src="../images/layer.png"/></section>
    
    <section class="main">
        <div class="txt-01">下载解放区，享免费物业</div>
        <figure><img src="../images/logo-small-03.png"/></figure>
        <div class="txt-02">解放区</div>
    </section>
    <section class="dl-btn">
        <div class="zocial android b01" onclick="androidDown();">Android 下载 <%=request.getAttribute("androiddVer")==null?"":request.getAttribute("androiddVer")%></div>
        <div class="zocial appstore b02" onclick="iosDown();">App Store 下载 <%=request.getAttribute("iosVer")==null?"":request.getAttribute("iosVer")%></div>
    </section>
    <footer>
        <p>Copyright <span>&copy;</span> 2014 深圳前海邻里乐科技服务有限公司 <br />All rights reserved.</p>
    </footer>
</section>
</body>
<script type="text/javascript" src="../js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
</html> 