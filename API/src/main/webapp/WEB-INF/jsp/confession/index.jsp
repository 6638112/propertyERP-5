<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<title>今天，我鼓起勇气向你表白</title>
<link rel="dns-prefetch" href="//jiefangqu.com">
<script src="${pageContext.request.contextPath}/commonjs/pace.min.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/commoncss/pace-theme-center-circle.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/commoncss/animate.min.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/commoncss/swiper.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/confession/css/confession.css">
</head>
<body>
<div id="mainWrap" data-path="${laPath}" class="swiper-container posrelative sectionBox">
	<!--<div id="audioPlay" class="circleTiming infinite rotateInCircle">
		<audio id="audio" style="display: none;" src="jiefangqudetian-hechang.mp3" autoplay="autoplay" loop hidden="hidden"></audio>
	</div>-->
	<div class="arrow-icon">
		<div class="arrow-up animated infinite bounceInUp01"><img src="${pageContext.request.contextPath}/confession/images/arrow-icon.png" /></div>
	</div>
    <div class="swiper-wrapper">
        <div class="swiper-slide posrelative">
		    <div id="fallingContainer">
		    </div>
        	<div class="page-elements posrelative">
	        	<div class="elements page01_e01" data-class="animated1s fadeInDown delay"><img src="${pageContext.request.contextPath}/confession/images/page01_txt01.png" /></div>
	        	<div class="elements page01_e02" data-class="animated1s fadeInUp delay"><img src="${pageContext.request.contextPath}/confession/images/page01_txt02.png" /></div>
	        	<div class="elements page01_e03" data-class="animated1s heartbeat infinite delay"><img src="${pageContext.request.contextPath}/confession/images/heart.png" /></div>
        	</div>
        	<div class="pagebg"><img src="${pageContext.request.contextPath}/confession/images/main_bg.jpg" /></div>
        </div>
        <c:if test="${(not empty msAnnualConfession) and (not empty msAnnualConfession.dateRegister)}">
	        <div id="dateRegister" class="swiper-slide posrelative">
	        	<div class="page-elements posrelative">
		        	<div class="elements page02_e01" data-class="animated1s fadeInDown delay"><span id="metTime">${msAnnualConfession.dateRegister}</span><br>我终于遇见了你</div>
		        	<div class="elements page02_e02a" data-class="animated10s shake infinite"><img src="${pageContext.request.contextPath}/confession/images/pattern_01.png" /></div>
		        	<div class="elements page02_e02b" data-class="animated10s shake infinite delay"><img src="${pageContext.request.contextPath}/confession/images/pattern_02.png" /></div>
		        	<div class="elements page02_e02c" data-class="animated1s fadeInUp"><img src="${pageContext.request.contextPath}/confession/images/pattern_03.png" /></div>
		        	<div class="elements page02_e03" data-class="animated1s fadeInUp delay">上辈子多少次回眸才换来的 <br>你我这次的相遇</div>
	        	</div>
	        	<div class="pagebg"><img src="${pageContext.request.contextPath}/confession/images/page02_bg.jpg" /></div>
	        </div>
        </c:if>
        <c:if test="${(not empty msAnnualConfession) and (not empty msAnnualConfession.dateFirstFee)}">
	        <div id="dateFirstFee" class="swiper-slide posrelative">
	        	<div class="page-elements posrelative">
		        	<div class="elements page03_e01 t-left" data-class="animated1s fadeInDown delay"><span id="payTime">${msAnnualConfession.dateFirstFee}</span><br>你第一次缴费<br>交给我去办</div>
		        	<div class="elements page03_e02" data-class="animated1s fadeInUp"><img src="${pageContext.request.contextPath}/confession/images/pattern_17.png" /></div>
		        	<div class="elements page03_e03 t-left" data-class="animated1s fadeInUp delay">让我感受到了<br>存在的意义</div>
	        	</div>
	        	<div class="pagebg"><img src="${pageContext.request.contextPath}/confession/images/page_bg.jpg" /></div>
	        </div>
        </c:if>
        <c:if test="${(not empty msAnnualConfession) and (not empty msAnnualConfession.dateFirstMarket)}">
	        <div id="dateFirstMarket" class="swiper-slide posrelative">
	        	<div class="page-elements posrelative">
		        	<div class="elements page03_e01 t-left" data-class="animated1s fadeInDown delay"><span id="shoppingTime">${msAnnualConfession.dateFirstMarket}</span><br>你第一次买东西<br>叫我送过去。</div>
		        	<div class="elements page04_e02a" data-class="animated1s fadeInDown"><img src="${pageContext.request.contextPath}/confession/images/pattern_5.png" /></div>
		        	<div class="elements page04_e02b" data-class="animated1s fadeInUp"><img src="${pageContext.request.contextPath}/confession/images/pattern_14.png" /></div>
		        	<div class="elements page03_e03 t-left" data-class="animated1s fadeInUp delay">找了好久<br>只是想让你满意。</div>
	        	</div>
	        	<div class="pagebg"><img src="${pageContext.request.contextPath}/confession/images/page_bg.jpg" /></div>
	        </div>
        </c:if>
        <c:if test="${(not empty msAnnualConfession) and (not empty msAnnualConfession.dateFirstService)}">
	        <div id="dateFirstService" class="swiper-slide posrelative">
	        	<div class="page-elements posrelative">
		        	<div class="elements page02_e01" data-class="animated1s fadeInDown delay"><span id="housekeepingTime">${msAnnualConfession.dateFirstService}</span><br>你第一次叫我过去<br>清洗维修</div>
		        	<div class="elements page05_e02" data-class="animated1s fadeIn"><img src="${pageContext.request.contextPath}/confession/images/pattern_27.png" /></div>
		        	<div class="elements page02_e03" data-class="animated1s fadeInUp delay">看到你的微笑<br>我特别开心</div>
	        	</div>
	        	<div class="pagebg"><img src="${pageContext.request.contextPath}/confession/images/page02_bg.jpg" /></div>
	        </div>
        </c:if>
        <c:if test="${(not empty msAnnualConfession) and (not empty msAnnualConfession.amountPayment)}">
	        <div id="amountPayment" class="swiper-slide posrelative">
	        	<div class="page-elements posrelative">
		        	<div class="elements page03_e01 t-right" data-class="animated1s fadeInDown delay">相识的这段日子<br>你为我花了<br><span id="spendAmount">${msAnnualConfession.amountPayment}</span>元</div>
		        	<div class="elements page06_e02" data-class="animated1s zoomIn"><img src="${pageContext.request.contextPath}/confession/images/pattern_33.png" /></div>
		        	<div class="elements page03_e03 t-right" data-class="animated1s fadeInUp delay">我一直<br>记在心里</div>
	        	</div>
	        	<div class="pagebg"><img src="${pageContext.request.contextPath}/confession/images/page_bg.jpg" /></div>
	        </div>
        </c:if>
        <div class="swiper-slide posrelative">
        	<div class="page-elements posrelative">
	        	<div class="elements page07_e01" data-class="animated1s fadeInDown delay"><img src="${pageContext.request.contextPath}/confession/images/page07_txt01.png" /></div>
	        	<div class="elements page07_e02" data-class="animated1s zoomIn"><img src="${pageContext.request.contextPath}/confession/images/pattern_30.png" /></div>
	        	<div class="elements page07_e03" data-class="animated1s fadeInUp delay"><img src="${pageContext.request.contextPath}/confession/images/page07_txt02.png" /></div>
        	</div>
        	<div class="pagebg"><img src="${pageContext.request.contextPath}/confession/images/page_bg.jpg" /></div>
        </div>
        <div class="swiper-slide posrelative">
        	<div class="page-elements posrelative">
	        	<div class="elements page08_e01" data-class="animated1s fadeInDown delay"><img src="${pageContext.request.contextPath}/confession/images/page08_txt01.png" /></div>
	        	<div class="elements page08_e02a" data-class="animated1s zoomIn"><img src="${pageContext.request.contextPath}/confession/images/pattern_39.png" /></div>
	        	<div id="shareToFriend" class="elements page08_e02" data-class="animated1s fadeInUp delay"><img src="${pageContext.request.contextPath}/confession/images/sharetofriend.png" /></div>
	        	<div id="shareToQuan" class="elements page08_e03" data-class="animated1s fadeInUp delay"><img src="${pageContext.request.contextPath}/confession/images/sharetoquan.png" /></div>
        	</div>
        	<div class="pagebg"><img src="${pageContext.request.contextPath}/confession/images/page_bg.jpg" /></div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath}/commonjs/swiper.jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/confession/js/falling.js"></script>
<script src="${pageContext.request.contextPath}/confession/js/confession.js?v20160804a"></script>
</body>
</html>