<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="../error/404.jsp" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<title>停车场列表</title>
<link type="text/css" rel="stylesheet" href="../css/common.css?v20171220">
</head>
<body class="pos-relative heightp100">
<!-- 搜索框 -->
<div class="sectionBox">
	<ul class="displaybox ptb10 bgwhite">
		<!-- <li id="goBackBtn" class="p10 f14">取消</li> -->
		<li class="boxflex01 mleft10">
			<input id="storeSearch" 
			class="border-left-radius border-right-radius0 noborder bggrey02 wp100 ptb8 indent10 f14 bg_icon_search" type="text" placeholder="输入停车场" 
			style="height: 33px; background:#efeff4 url(../images/icon_search.png) no-repeat 10px 9px !important; background-size: 15px auto !important; text-indent: 34px;" />
		</li>
		<li class="btn-search f14">搜索</li>
	</ul>
</div>
<div id="searchListBox" class="sectionBox pos-relative search-list">
   	<ul class="displaybox ptb10 lot-item pointer borderbottomgrey boxalignstart dsn">
   		<li class="overhidden" style="margin: 3px 0 0 5px;">
  				<img class="w13" src="../images/icon_parkinglot.png">
   		</li>
   		<li class="boxflex01 f16 m010 mb5 lineheight110">
  				<div class="lot-item-name lineheight140 word-break1">越南红肉火龙果500g</div>
  				<div class="item-desc mtop2 f12 grey word-break1">爽口甘甜 清凉一夏</div>
   		</li>
   	</ul>
	<div class="p010 item-list-box">
		<div class="sectionBox loading grey mtop15 newLoading"><img src="../images/loading01.gif" /> 加载中…</div>
	</div>
</div>
<div class="sectionBox loading grey mtop15 hide"><img src="../images/loading01.gif" /> 加载中…</div>
<section class="pop-tips dsn">
	<div class="pop-tips-text">请选择</div>
</section>
<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="../js/method.common.js"></script>
<script src="../js/parkinglot.search.js?20180104"></script>
</body>
<script type="text/javascript">
	var cnzz_s_tag = document.createElement('script');
	cnzz_s_tag.type = 'text/javascript';
	cnzz_s_tag.async = true;
	cnzz_s_tag.charset = 'utf-8';
	cnzz_s_tag.src = 'https://s11.cnzz.com/z_stat.php?id=1261123817&async=1';
	var root_s = document.getElementsByTagName('script')[0];
	setTimeout(function(){
		root_s.parentNode.insertBefore(cnzz_s_tag, root_s);
	},4000);
</script>
</html>