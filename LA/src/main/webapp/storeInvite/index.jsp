<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<title>解放区邀请函</title>
<link rel="dns-prefetch" href="//jiefangqu.com">
<script src="${resourcePathHttps}/commonjs/pace.min.js"></script>
<link type="text/css" rel="stylesheet" href="${resourcePathHttps}/commoncss/pace-theme-center-circle.css">
<link type="text/css" rel="stylesheet" href="${resourcePathHttps}/commoncss/swiper.css">
<link type="text/css" rel="stylesheet" href="../css/store.invite.css">
</head>
<body>
<div class="swiper-container posrelative sectionBox">
    <div class="swiper-wrapper">
        <div class="swiper-slide posrelative">
        	<div class="page-elements posrelative">
	        	<div class="elements page01_e01 posrelative" data-class="animated1s fadeInUp"><img src="../images/astore_01.png" /></div>
	        	<div class="elements page01_e02 posrelative" data-class="animated1s fadeInUp"><img src="../images/astore_bg01.jpg" /></div>
	        	<div id="nextSlide" class="elements page01_e04" data-class="animated1s fadeInUp"><img src="../images/astore_btn.png" /></div>
        	</div>
        	<div class="pagebg"><img src="../images/astore_bg.jpg" /></div>
        </div>
        <div class="swiper-slide posrelative swiper-no-swiping">
        	<div class="page-elements">
	        	<div class="elements page01_e01 posrelative" data-class="animated1s fadeInUp">
		        	<form class="inputform">
			        	<div class="elements page09_e02">
			        		<input class="input-normal input-name" maxlength="20" type="text" name="" value="" placeholder="请输入姓名" />
			        		<input class="input-normal input-phone" maxlength="13" type="text" name="" value="" placeholder="请输入手机号" />
			        		<input class="submit-btn" type="button" name="" value="" />
			        	</div>
		        	</form>
	        		
	        		<img src="../images/astore_02.png" usemap="#Map" />
	        	</div>
        	</div>
        	<div class="pagebg"><img src="../images/astore_bg.jpg" /></div>
        </div>
        <div class="swiper-slide posrelative swiper-no-swiping">
        	<div class="page-elements posrelative">
	        	<div class="elements page03_e01" data-class="animated1s fadeInUp"><img src="../images/astore_03.png" /></div>
        	</div>
        	<div class="pagebg"><img src="../images/astore_bg.jpg" /></div>
        </div>
    </div>
</div>
</body>
<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePathHttps}/commonjs/swiper.jquery.min.js"></script>
<script src="../js/store.invite.js?v01a"></script>

<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="../js/wx.share.info.js"></script>
<script type="text/javascript">
$(function(){
	
	//LA不支持接口跨域调用，须把项目迁到LA
	
    //轻应用分享
	setShareInfo({
		title: '开业邀请函',		// 分享标题
		desc: '解放区蛇口优选体验店8.1盛大开业，美食美酒已备好，就等您',		// 分享描述
		imgIcon: '${resourcePathHttps}/commonimages/jfqlogo_share.png'
	});
	
});

</script>
</html>
