<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>活动详情</title>
<link rel="stylesheet" href="${apiPathHttps}/css/swiper.css" type="text/css">
<link rel="stylesheet" href="${apiPathHttps}/css/yiyuangou.css" type="text/css">
<style>
.my-gallery { width: 100%; height: calc(100vw*473/560); max-height: 473px; float: left;}
.my-gallery img, .swiper-container { width: 100%; height: calc(100vw*473/560); max-height: 473px;}
.my-gallery figure { display: block; float: left; margin:0; overflow: hidden;}
.my-gallery figcaption { display: none; }

[v-cloak] {
  display: none;
}
.awards-list{width: 90%;height: 150px;margin: 0 5% 10px;overflow-y: scroll;}
</style>
</head>

<body class="bggrey heightp100">
	<section id="wrapBox" class="sectionBox bggrey pos-relative minheight100" v-cloak>
		<section id="tabBox" class="tabBox pos-relative minheight100 zindex10">
			<section class="sectionBox">
			    <ul class="f14 bgdarkblue details-time-info wp100">
			        <li v-if="actDetail.activityStatus === 1" :data-time="actDetail.activityEndTime " class="countdown left">
							时间：{{actDetail.startTime}}-{{actDetail.endTime}}
			        </li>
			        <li v-if="actDetail.activityStatus === 2" :data-time="actDetail.activityStartTime " class="countdown left">
							时间：{{actDetail.startTime}}-{{actDetail.endTime}}
			        </li>
			        <li v-if="actDetail.activityStatus !== 1 && actDetail.activityStatus !== 2" class="left">
							时间：{{actDetail.startTime}}-{{actDetail.endTime}}
			        </li>
			        <li class="right">{{actDetail.buyCount }} 人已参与</li>
			    </ul>
			    <!-- Swiper -->
			    <div class="swiper-container pos-relative bggrey">
			        <div class="swiper-wrapper my-gallery imgLoading">
			            <figure v-for="picList in actDetail.activityPicList" class="swiper-slide" itemprop="associatedMedia" itemscope style="background:url(../images/loading01.gif) no-repeat center; background-size:26px;">
			                <img :src="picList + '?x-oss-process=image/resize,m_fill,w_560,h_473,limit_0/format,jpg/interlace,1/quality,q_95'" itemprop="thumbnail" />
			            </figure>
			        </div>
			        <!-- Add Pagination -->
			        <div class="swiper-pagination"></div>
			    </div>
			    
			</section>
			<section class="sectionBox">
			   <div class="info pb0">
			        <div id="itemOnsell" class="f16 bold mtop15 p010" :data-id="actDetail.activityId" :data-itemid="actDetail.productShelfId">{{actDetail.activityTitle }}</div></a>
			        <div class="message p010">
			            <span class="f14">{{actDetail.introduceContent}}</span>
			        </div>
			        <div class="item-standard mleft10 mtop15 bordertopgrey">
			            <div class="number displaybox">
			            	<div class="boxflex01 name f14">每人限购 1 份</div>
		            		<div v-if="actDetail.isSettle === 1 && actDetail.activityStatus === 3" class="boxflex01 f14 t-right red mright10 ptb10 view-pop-box-btn" data-class="pop-box02">成功购买名单 <img class="w12 mleft5" src="${apiPathHttps}/images/arrow-down.png"/></div>
			            </div>
			        </div> 
			        <div class="mleft10 ptb15 bordertopgrey">
			            <div class="number displaybox">
			            	<div class="boxflex01 name f14 green">自提点：<span id="zitidian">{{actDetail.merchantName}}</span></div>
			            </div>
			        </div>   
					<section class="divide-box bordertbgrey"></section>
					<a :href="'../product/productDetail.do?ptId=' + actDetail.productShelfId">
						<div class="displaybox sell-list">
							<img class="item-img-thumb mright15 bggrey" :src="actDetail.activityPicList[0] + '?x-oss-process=image/resize,m_fill,w_160,h_160/format,jpg/quality,q_95/interlace,1'" />
							<div class="boxflex01">
								<div class="lineheight140 f16 text-clamp2">{{actDetail.productName }}</div>
								<div class="f18 red mtop15 bold">￥<span>{{actDetail.productPrice/100 }}</span></div>
							</div>
		            		<div class="w20 box-arrow t-right grey"></div>
						</div>
            		</a>
					<!-- <section class="divide-box bordertbgrey"></section>
				    <div class="list-box bgwhite tap-nobg displaybox pointer view-pop-box-btn" data-class="pop-box03">
			            <div class="item-standard-name height36 f16 boxflex01">活动规则</div>
			            <div class="boxflex01 box-arrow t-right grey"></div>
				    </div> -->
					<section class="divide-box bordertbgrey"></section>
			        <ul class="item-price f16 borderbottomgrey ptb1210 tap-nobg">
			            <li class="left">联系客服</li>
			            <li class="right"><a class="blue" href="tel:0755-86713324">0755-86713324</a></li>
			        </ul>
			    </div>
			</section>           
			<section class="divide-box"></section>
			<section class="pbfooter"></section>
			<div class="sectionBox">
			    <div class="exchang-fixed displaybox">
					<div v-if="actDetail.activityStatus === 1 && actDetail.buyRecord == null" id="buyNowBtn" class="boxflex01 t-center bgred pointer">
			    		<a class="white ptb20 f16 disblock" :href="'pay.html?activityId=' + actDetail.activityId">立即参与</a>
			        </div>
             		<div v-if="actDetail.activityStatus === 1 && actDetail.buyRecord != null" class="boxflex01 t-center bgdarkgrey ptb20 white pointer">
			    		<div class="f16">已参与</div>
			        </div>
       				<div v-if="actDetail.activityStatus === 3" class="boxflex01 ptb20 f16 white t-center bgdarkgrey pointer">已结束</div>
			    </div>
			</div>
	
			<section class="sectionBox wrap-bg pop-box02 dsn">
				<div class="tips-box pos-relative">
					<div id="adwardListCloseBtn" class="common-close-btn"><img src="${apiPathHttps}/images/close_icon.png"/></div>
					<div class="t-center ptb10 pos-relative">
						<div class="adward-title"><img class="wp80" src="${apiPathHttps}/images/adward_title.png"/></div>
					</div>
					<div class="grey t-center m010">恭喜以下用户，请留意短信内容或咨询物业管理处领取货品哦。</div>
					<div class="awards-list mtop15">
						<div v-for="(phoneList, index) in actDetail.winRecords" class="wp50 left mtop10" :class="[{'red' : (phoneList.recordStatus === 4 && phoneList.tUserFId === actDetail.buyRecord.tUserFId), 't-right' : (index % 2 !== 0)}]">
							{{phoneList.userMobile.substring(0,3)}}****{{phoneList.userMobile.substring(6,10)}}
						</div>
					</div>	
				</div>
			</section>
	
			<section class="sectionBox wrap-bg pop-box03 dsn">
				<div class="tips-box desc-box pos-relative">
					<div id="closeTipsBtn" class="common-close-btn"></div>
					<div><img src="${apiPathHttps}/images/desc_text.png"/></div>	
				</div>
			</section>
		</section>
		
		<div class="tips-box tips-done bounceInDown animated1s dsn">
			<img class="rotateZoomIn animated1s delay" src="${apiPathHttps}/images/icon-tips-done.png"/><br>成功参与
		</div>
	</section>
<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePathHttps}/commonjs/vue.2.3.0.min.js"></script>
<script src="${resourcePathHttps}/commonjs/axios.0.16.1.min.js"></script>
<script src="${apiPathHttps}/js/jquery.downCount.forActivity.js"></script>
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script src="${resourcePathHttps}/commonjs/swiper.min.js"></script>
<script src="../js/method.common.js"></script>
<script src="../js/vue.lukygo.detail.js"></script>
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