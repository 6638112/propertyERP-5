<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>幸运GO！</title>
<link rel="stylesheet" href="${resourcePathHttps}/commoncss/swiper.css" type="text/css">
<link rel="stylesheet" href="${apiPathHttps}/css/yiyuangou.css" type="text/css">
<style type="text/css">
[v-cloak] {
  display: none;
}
</style>
</head>

<body class="heightp100" style="opacity:0">
	<section id="wrapBox" class="sectionBox pos-relative minheight100 bggrey" v-cloak>
		<section id="tabBox" class="tabBox paddingbottom pos-relative minheight100 zindex10 nobg">
			<div class="sectionBox list-onsell bgwhite">
				<div class="lineheight0">
					<img src="${apiPathHttps}/images/top_banner.jpg"/>
				</div>
				<div class="swiper-container">
					<ul class="sell-date swiper-wrapper" style="margin: 0;">
						<li v-for="(activitiesTab, index) in actDetail.activityList" class="swiper-slide pos-relative" :class="index === 0 ? 'red' : ''">
							<span class="bold date-title">{{activitiesTab.startDay}}</span>
							<div v-if="index === 0 && activitiesTab.todayStatus === 1" class="swiper-text text01">今日必抢</div>
							<div v-else class="swiper-text text02">即将开场</div>
						</li>
					</ul>
				</div>
				<div class="divide-box"></div>

				<div v-for="(activities, index) in actDetail.activityList" class="swiper-con-box" :class="index !== 0 ? 'dsn' : ''">
					<div v-for="(list, index01) in activities.flashDealActivityDetailEntities" class="displaybox pos-relative sell-list borderbottom" :data-id="list.activityId">
						<img v-if="list.buyRecord != null" class="has-bought-icon" src="${apiPathHttps}/images/bought_icon.png"/>
						<div class="item-img-thumb mright15 bggrey">
							<a :href="'activityDetail.html?userId=' + actDetail.userId + '&activityId=' + list.activityId">
								<img class="disblock" :src="list.activityPicList[0] + '?x-oss-process=image/resize,m_fill,w_160,h_160,limit_0/format,jpg/quality,q_95/interlace,1'" />
							</a>
						</div>
						<div class="boxflex01">
							<a :href="'activityDetail.html?userId=' + actDetail.userId + '&activityId=' + list.activityId">
								<div class="title lineheight24 text-clamp2 f18 black">{{list.activityTitle }}</div>
							</a>
							<div class="f12 grey mtop5">限<span>{{list.prizeCount }}</span>份，已有<span class="red">{{list.buyCount }}</span>人参与</div>
							<ul class="mtop10">
								<li :data-time="list.dataTime" :class="(list.activityStatus === 1 || list.activityStatus === 2) ? 'countdown on' : ''" class="displaybox grey">
									<span class="boxflex01">时间：{{list.startTime}}-{{list.endTime}}</span>
									
									<span v-if="list.activityStatus === 1 && list.buyRecord == null" class="boxflex01 t-right mtop3 buyNowBtn">
										<a :href="'activityDetail.html?userId=' + actDetail.userId + '&activityId=' + list.activityId">
											<img src="${apiPathHttps}/images/sell_btn.png"/>
										</a>
									</span>
									<span v-if="list.activityStatus === 1 && list.buyRecord != null" class="boxflex01 t-right mtop3">
										<img src="${apiPathHttps}/images/has_hought_btn.png"/>
									</span>
									
									<span v-if="list.activityStatus === 2 && list.remindStatus === 0" class="boxflex01 t-right mtop3 remind-btn">
										<img style="height: 28px; width: auto;" src="${apiPathHttps}/images/remind_btn.png"/>
									</span>
									<span v-if="list.activityStatus === 2 && list.remindStatus === 1" class="boxflex01 t-right mtop3">
										<img style="height: 28px; width: auto;" src="${apiPathHttps}/images/has_remind.png"/>
									</span>
									
									<span v-if="list.activityStatus === 3 && list.isSettle === 1" class="boxflex01 t-right mtop3">
										<img src="${apiPathHttps}/images/act_done.png"/>
									</span>
									<span v-if="list.activityStatus === 3 && list.isSettle === 0" class="boxflex01 t-right mtop3">
										<img src="${apiPathHttps}/images/act_done_01.png"/>
									</span>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			
		    <section class="divide-box"></section>
		    <ul class="bottom-menu-box displaybox t-center black f18 bordertopgrey">
		    	<li id="viewDescDetail" class="boxflex01"><img class="icon-bottom icon-desc" src="${apiPathHttps}/images/icon_desc.png"/> 活动规则</li>
		    	<li class="boxflex01 borderleft"><a class="disblock" v-bind:href="'myRecord.html?userId=' + userId "><img class="icon-bottom" src="${apiPathHttps}/images/icon_history.png"/> 参与记录</a></li>
		    </ul>
	
			<section class="sectionBox wrap-bg pop-box02 dsn">
				<div class="tips-box desc-box pos-relative">
					<div id="closeTipsBtn"></div>
					<div><img src="${apiPathHttps}/images/desc_text.png?v1"/></div>	
				</div>
			</section>
		</section>
	</section>

<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePathHttps}/commonjs/vue.2.3.0.min.js"></script>
<script src="${resourcePathHttps}/commonjs/axios.0.16.1.min.js"></script>
<script src="${apiPathHttps}/js/jquery.downCount.forActivity.js"></script>
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script src="${resourcePathHttps}/commonjs/swiper.min.js"></script>
<script src="${apiPathHttps}/js/layer.min.js"></script>
<script src="${apiPathHttps}/js/imgResize.js"></script>
<script src="../js/method.common.js"></script>
<script src="../js/vue.lukygo.list.js"></script>
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
