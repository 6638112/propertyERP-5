<%@page import="com.cnfantasia.server.common.utils.StringUtils"%>
<%@page import="com.cnfantasia.server.common.json.JsonResponse"%>
<%@page import="com.cnfantasia.server.common.exception.FocRuntimeException"%>
<%@page import="com.cnfantasia.server.common.exception.FocException"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset = "utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<title>母亲节专场</title>
<link rel="stylesheet" type="text/css" href="../css/mothersday.css">
</head>
<body class="posrelative">
<section class="pop-tips hide">
	<div class="pop-tips-text">请选择</div>
</section>
<section class="bg-wrap hide"></section>
<section class="main-box">
	<div class="hometab hide">
		<div style="height: calc(335 * 100vw / 750);"><img class="bgloading" src="../images/mothersday/pixel.gif" data-original="../images/mothersday/main_01.jpg" /></div>
		<div style="height: calc(380 * 100vw / 750);"><img class="bgloading" src="../images/mothersday/pixel.gif" data-original="../images/mothersday/main_02.jpg" /></div>
		<div style="height: calc(211 * 100vw / 750);"><img class="bgloading" src="../images/mothersday/pixel.gif" data-original="../images/mothersday/main_03.jpg" /></div>
	</div>
	<div class="hometab shopstab hide">
		<div style="height: calc(168 * 100vw / 750);"><img class="bgloading" src="../images/mothersday/pixel.gif" data-original="../images/mothersday/main_04.jpg" /></div>
		<div style="height: calc(513 * 100vw / 750);"><img class="bgloading" data-original="../images/mothersday/main_05.jpg" src="../images/mothersday/pixel.gif" /></div>
		<div style="height: calc(417 * 100vw / 750);"><img class="bgloading" data-original="../images/mothersday/main_06.jpg" src="../images/mothersday/pixel.gif" /></div>
		<div style="height: calc(230 * 100vw / 750);"><img class="bgloading" data-original="../images/mothersday/main_07.jpg" src="../images/mothersday/pixel.gif" /></div>
		<div style="height: calc(127 * 100vw / 750);" class="buynow-btn" data-id="120282" data-name="山东樱桃"><img class="bgloading" src="../images/mothersday/pixel.gif" data-original="../images/mothersday/main_08.jpg" /></div>
		<div style="height: calc(64 * 100vw / 750);"><img class="bgloading" src="../images/mothersday/pixel.gif" data-original="../images/mothersday/main_09.jpg" /></div>
		<div style="height: calc(512 * 100vw / 750);"><img class="bgloading" src="../images/mothersday/pixel.gif" data-original="../images/mothersday/main_10.jpg" /></div>
		<div><img class="bgloading" src="../images/mothersday/pixel.gif" data-original="../images/mothersday/main_11.jpg" /></div>
		<div class="buynow-btn" data-id="120284" data-name="即食燕窝"><img class="bgloading" src="../images/mothersday/pixel.gif" data-original="../images/mothersday/main_12.jpg" /></div>
		<div><img class="bgloading" src="../images/mothersday/pixel.gif" data-original="../images/mothersday/main_13.jpg" /></div>
		<div><img class="bgloading" src="../images/mothersday/pixel.gif" data-original="../images/mothersday/main_14.jpg" /></div>
		<div><img class="bgloading" src="../images/mothersday/pixel.gif" data-original="../images/mothersday/main_15.jpg" /></div>
		<div class="buynow-btn" data-id="120248" data-name="野生土蜂蜜"><img class="bgloading" src="../images/mothersday/pixel.gif" data-original="../images/mothersday/main_16.jpg" /></div>
		<div><img class="bgloading" src="../images/mothersday/pixel.gif" data-original="../images/mothersday/main_17.jpg" /></div>
	</div>
	<div class="homeservicetab hide">
		<div style="height: 48px; overflow: hidden;"><img src="../images/mothersday/line-y.jpg"/></div>
	</div>
	<div class="hometab homeservicetab hide">
		<div class="homeservicetab hide" onclick="history.back(-1)" style="position: absolute; width: 60px; height: 24px; left: 36px;"><img style="width: 45px; height: auto;" src="../images/mothersday/goback.png"/></div>
		<div style="height: calc(166 * 100vw / 750);"><img class="bgloading" src="../images/mothersday/pixel.gif" data-original="../images/mothersday/main_18.jpg" /></div>
		<div style="height: calc(516 * 100vw / 750);"><img class="bgloading" src="../images/mothersday/pixel.gif" data-original="../images/mothersday/main_19.jpg" /></div>
		<div style="height: calc(651 * 100vw / 750);"><img class="bgloading" src="../images/mothersday/pixel.gif" data-original="../images/mothersday/main_20.jpg" /></div>
		<div style="height: calc(115 * 100vw / 750);" class="booknow-btn" data-id="115" data-name="家政服务" data-source="母亲节活动"><img class="bgloading" src="../images/mothersday/pixel.gif" data-original="../images/mothersday/main_21.jpg" /></div>
		<div style="height: calc(68 * 100vw / 750);"><img class="bgloading" src="../images/mothersday/pixel.gif" data-original="../images/mothersday/main_22.jpg" /></div>
		<div style="height: calc(514 * 100vw / 750);"><img class="bgloading" src="../images/mothersday/pixel.gif" data-original="../images/mothersday/main_23.jpg" /></div>
		<div style="height: calc(565 * 100vw / 750);"><img class="bgloading" src="../images/mothersday/pixel.gif" data-original="../images/mothersday/main_24.jpg" /></div>
		<div class="booknow-btn" data-id="113" data-name="家电清洗" data-source="母亲节活动"><img class="bgloading" src="../images/mothersday/pixel.gif" data-original="../images/mothersday/main_25.jpg" /></div>
		<div><img class="bgloading" src="../images/mothersday/pixel.gif" data-original="../images/mothersday/main_26.jpg" /></div>
	</div>
	<div class="hometab hide">
		<div><img class="bgloading" src="../images/mothersday/pixel.gif" data-original="../images/mothersday/main_27.jpg" /></div>
	</div>
</section>

<script src="../js/jquery-1.11.2.min.js"></script>
<script src="../js/jquery.lazyload.js"></script>
<script src="../js/act.mothersday.js"></script>
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1261123817'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1261123817' type='text/javascript'%3E%3C/script%3E"));</script>
</body>
</html> 