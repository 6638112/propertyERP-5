<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="../error/404.jsp" %>
<%@page import="java.util.Calendar"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">
<title>解放区周年庆</title>
<link type="text/css" rel="stylesheet" href="../css/anniversary.css">
</head>
<body class="bggrey pos-relative">
<div id="container" data-param="${servertpart }" class="section-box pos-relative" v-cloak>
	<div class="section-box">
		<div id="locationTips" class="hide"><img src="../images/tip-location.png"/></div>
		<div><img src="../images/anniversary2017_01.jpg"/></div>
		<div><img src="../images/anniversary2017_02.jpg"/></div>
		<div><img src="../images/anniversary2017_03.jpg"/></div>
		<div><img src="../images/anniversary2017_04.jpg"/></div>
	</div>
	<div class="section-box bggrey pos-relative displaybox">
		<section class="boxflex01">
			<div class="market-part" v-if="!serverShowOnly">
				<div class="pos-relative"><img src="../images/anniversary2017_05.jpg"/></div>
				<div class="pos-relative">
					<div class="house_icon animated floatInfinite"><img src="../images/anniversary_shengxianwu.png"/></div>
					<img src="../images/anniversary2017_07.jpg"/>
				</div>
				<div class="pos-relative">
					<div class="item-info-box radius4 bgbluegradient box-shadow-dark">
						<div class="item-info radius4 t-center white"><span class="f14">特色生鲜店</span></br><span class="f18 bold">满39减10</span></div>
						<div class="get-coupon-btn radius4 box-shadow-black bold f14" v-if="scenes && scenes[1].canReceive === 1" @click="getCoupon(scenes[1].couponSceneId)">立刻抢券</div>
						<div class="get-coupon-btn radius4 box-shadow-black grey" v-if="scenes && scenes[1].canReceive === 0 && scenes[1].hasReceive === 0">暂无可领</div>
						<div class="get-coupon-btn radius4 box-shadow-black red" v-if="scenes && scenes[1].canReceive === 0 && scenes[1].hasReceive === 1" @click="gotoCouponlist()">去使用</div>
					</div>
					<img src="../images/anniversary2017_09.jpg"/>
				</div>
				<div class="pos-relative">
					<div class="house_icon haixianwu animated floatInfinite"><img src="../images/anniversary_haixianwu.png"/></div>
					<img src="../images/anniversary2017_11.jpg"/>
				</div>
				<div class="pos-relative">
					<div class="item-info-box radius4 bgbluegradient box-shadow-dark">
						<div class="item-info radius4 t-center white"><span class="f14">严选海鲜店</span></br><span class="f18 bold">满39减10</span></div>
						<div class="get-coupon-btn radius4 box-shadow-black bold f14" v-if="scenes && scenes[3].canReceive === 1" @click="getCoupon(scenes[3].couponSceneId)">立刻抢券</div>
						<div class="get-coupon-btn radius4 box-shadow-black grey" v-if="scenes && scenes[3].canReceive === 0 && scenes[3].hasReceive === 0">暂无可领</div>
						<div class="get-coupon-btn radius4 box-shadow-black red" v-if="scenes && scenes[3].canReceive === 0 && scenes[3].hasReceive === 1" @click="gotoCouponlist()">去使用</div>
					</div>
					<img src="../images/anniversary2017_13.jpg"/>
				</div>
			</div>
			<div class="server-part" style="margin-top: -1px;" v-if="!marketShowOnly">
				<div class="pos-relative"><img src="../images/anniversary2017_15.jpg"/></div>
				<div class="pos-relative" style="margin-top: -1px;">
					<div class="house_icon jiazheng animated floatInfinite"><img src="../images/anniversary_jiazheng.png"/></div>
					<img src="../images/anniversary2017_17.jpg"/>
				</div>
				<div class="pos-relative">
					<div class="item-info-box radius4 bgpinkgradient box-shadow-dark">
						<div class="item-info radius4 t-center white"><span class="f14">家政服务</span></br><span class="f16 bold">满199减60</span></div>
						<div class="get-coupon-btn radius4 box-shadow-black bold f14" v-if="scenes && scenes[5].canReceive === 1" @click="getCoupon(scenes[5].couponSceneId)">立刻抢券</div>
						<div class="get-coupon-btn radius4 box-shadow-black grey" v-if="scenes && scenes[5].canReceive === 0 && scenes[5].hasReceive === 0">暂无可领</div>
						<div class="get-coupon-btn radius4 box-shadow-black red" v-if="scenes && scenes[5].canReceive === 0 && scenes[5].hasReceive === 1" @click="gotoCouponlist()">去使用</div>
					</div>
					<img src="../images/anniversary2017_19.jpg"/>
				</div>
				<div class="pos-relative">
					<div class="house_icon jiazhuang animated floatInfinite"><img src="../images/anniversary_jiazhuang.png"/></div>
					<img src="../images/anniversary2017_21.jpg"/>
				</div>
				<div class="pos-relative">
					<div class="item-info-box radius4 bggreengradient box-shadow-dark">
						<div class="item-info radius4 t-center white"><span class="f14">家装服务</span></br><span class="f18 bold">满29减15</span></div>
						<div class="get-coupon-btn radius4 box-shadow-black bold f14" v-if="scenes && scenes[7].canReceive === 1" @click="getCoupon(scenes[7].couponSceneId)">立刻抢券</div>
						<div class="get-coupon-btn radius4 box-shadow-black grey" v-if="scenes && scenes[7].canReceive === 0 && scenes[7].hasReceive === 0">暂无可领</div>
						<div class="get-coupon-btn radius4 box-shadow-black red" v-if="scenes && scenes[7].canReceive === 0 && scenes[7].hasReceive === 1" @click="gotoCouponlist()">去使用</div>
					</div>
					<img src="../images/anniversary2017_23.jpg"/>
				</div>
			</div>
		</section>
		<section class="boxflex01">
			<div class="market-part" v-if="!serverShowOnly">
				<div class="pos-relative">
					<div class="house_icon shuiguo animated floatInfinite"><img src="../images/anniversary_shuiguowu.png"/></div>
					<img src="../images/anniversary2017_06.jpg"/>
				</div>
				<div class="pos-relative">
					<div class="item-info-box radius4 bgorangegradient box-shadow-dark-right">
						<div class="item-info radius4 t-center white"><span class="f14">超级水果店</span></br><span class="f18 bold">满39减10</span></div>
						<div class="get-coupon-btn radius4 box-shadow-black bold f14" v-if="scenes && scenes[0].canReceive === 1" @click="getCoupon(scenes[0].couponSceneId)">立刻抢券</div>
						<div class="get-coupon-btn radius4 box-shadow-black grey" v-if="scenes && scenes[0].canReceive === 0 && scenes[0].hasReceive === 0">暂无可领</div>
						<div class="get-coupon-btn radius4 box-shadow-black red" v-if="scenes && scenes[0].canReceive === 0 && scenes[0].hasReceive === 1" @click="gotoCouponlist()">去使用</div>
					</div>
					<img src="../images/anniversary2017_08.jpg"/>
				</div>
				<div class="pos-relative">
					<div class="house_icon animated floatInfinite"><img src="../images/anniversary_niunaiwu.png"/></div>
					<img src="../images/anniversary2017_10.jpg"/>
				</div>
				<div class="pos-relative">
					<div class="item-info-box radius4 bggreygradient box-shadow-dark-right">
						<div class="item-info radius4 t-center white"><span class="f14">养生食品店</span></br><span class="f18 bold">满79减20</span></div>
						<div class="get-coupon-btn radius4 box-shadow-black bold f14" v-if="scenes && scenes[2].canReceive === 1" @click="getCoupon(scenes[2].couponSceneId)">立刻抢券</div>
						<div class="get-coupon-btn radius4 box-shadow-black grey" v-if="scenes && scenes[2].canReceive === 0 && scenes[2].hasReceive === 0">暂无可领</div>
						<div class="get-coupon-btn radius4 box-shadow-black" v-if="scenes && scenes[2].canReceive === 0 && scenes[2].hasReceive === 1" @click="gotoCouponlist()">去使用</div>
					</div>
					<img src="../images/anniversary2017_12.jpg"/>
				</div>
				<div class="pos-relative"><img src="../images/anniversary2017_14.jpg"/></div>
			</div>
			<div class="server-part" style="margin-top: -1px;" v-if="!marketShowOnly">
				<div class="pos-relative">
					<div class="house_icon jiadian animated floatInfinite"><img src="../images/anniversary_jiadian.png"/></div>
					<img src="../images/anniversary2017_16.jpg"/>
				</div>
				<div class="pos-relative" style="margin-top: -1px;">
					<div class="item-info-box radius4 bgbabygreengradient box-shadow-dark-right">
						<div class="item-info radius4 t-center white"><span class="f14">家电服务</span></br><span class="f18 bold">满49减20</span></div>
						<div class="get-coupon-btn radius4 box-shadow-black bold f14" v-if="scenes && scenes[4].canReceive === 1" @click="getCoupon(scenes[4].couponSceneId)">立刻抢券</div>
						<div class="get-coupon-btn radius4 box-shadow-black grey" v-if="scenes && scenes[4].canReceive === 0 && scenes[4].hasReceive === 0">暂无可领</div>
						<div class="get-coupon-btn radius4 box-shadow-black red" v-if="scenes && scenes[4].canReceive === 0 && scenes[4].hasReceive === 1" @click="gotoCouponlist()">去使用</div>
					</div>
					<img src="../images/anniversary2017_18.jpg"/>
				</div>
				<div class="pos-relative">
					<div class="house_icon jiaju animated floatInfinite"><img src="../images/anniversary_jiajufuwu.png"/></div>
					<img src="../images/anniversary2017_20.jpg"/>
				</div>
				<div class="pos-relative">
					<div class="item-info-box radius4 bgcyangradient box-shadow-dark-right">
						<div class="item-info radius4 t-center white"><span class="f14">家居服务</span></br><span class="f18 bold">满29减15</span></div>
						<div class="get-coupon-btn radius4 box-shadow-black bold f14" v-if="scenes && scenes[6].canReceive === 1" @click="getCoupon(scenes[6].couponSceneId)">立刻抢券</div>
						<div class="get-coupon-btn radius4 box-shadow-black grey" v-if="scenes && scenes[6].canReceive === 0 && scenes[6].hasReceive === 0">暂无可领</div>
						<div class="get-coupon-btn radius4 box-shadow-black red" v-if="scenes && scenes[6].canReceive === 0 && scenes[6].hasReceive === 1" @click="gotoCouponlist()">去使用</div>
					</div>
					<img src="../images/anniversary2017_22.jpg"/>
				</div>
				<div class="pos-relative"><img src="../images/anniversary2017_24.jpg"/></div>
			</div>
		</section>
	</div>
	<div class="section-box" style="margin-top: -1px;">
		<div class="pos-relative"><img src="../images/anniversary2017_25.jpg"/></div>
	</div>

	<section class="coupon-box-wrap" v-if="couponBoxShow" v-cloak>
		<div><img src="../images/anniversary_popbg01.png"/></div>
		<section class="coupon-box">
			<ul class="coupon-list radius4 box-shadow-black displaybox" v-for="(item, index) of couponList">
				<!-- <li class="boxflex01">
					<div class="f14 grey">￥{{item.discountMoney}} {{item.couponName}}</div>
					<div class="f16 puple">{{item.couponDesc}}<br><span class="f12 grey">有效期至{{item.useEndDate}}</span></div>
				</li>
				<li class="coupon-use-btn radius4">
					<a class="white" @click.stop.prevent="gotoItemlist(item)">
						<div class="goto-itemlist">立即使用</div>
					</a>
				</li>
				 -->
				<li class="wp100">
					<div class="f14 grey displaybox">
						<div class="boxflex01">￥{{item.discountMoney}} {{item.couponName}}</div>
						<div class="coupon-use-btn radius4 f12">
							<div class="goto-itemlist" @click.stop.prevent="gotoItemlist(item)">立即使用</div>
						</div>
					</div>
					<div class="f16 puple">{{item.couponDesc}} <span class="f12 grey">有效期至{{item.useEndDate}}</span></div>
				</li>
			</ul>
			<div class="goto-couponlist radius4 box-shadow-orange f16 puple" @click="gotoCouponlist()">查看我的消费券 ></div>
		</section>
	</section>
	<div class="bg-wrap" v-if="couponBoxShow" @click="closeCouponBox()"></div>
	<!-- 消息提示-->
	<section class="pop-tips hide">
		<div class="pop-tips-text"></div>
	</section>
</div>
<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePathHttps}/commonjs/vue.2.3.0.min.js"></script>
<script src="${resourcePathHttps}/commonjs/axios.0.16.1.min.js"></script>
<script src="${resourcePathHttps}/commonjs/jquery.cookie.js"></script>
<script src="../js/method.common.js"></script>
<script src="../js/anniversary.js?20170801"></script>
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
</body>
</html>