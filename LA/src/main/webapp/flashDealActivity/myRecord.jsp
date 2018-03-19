<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>参与记录</title>
<link rel="stylesheet" href="${apiPathHttps}/css/yiyuangou.css" type="text/css">
<style type="text/css">
[v-cloak] {
  display: none;
}
.awards-list{width: 90%;height: 150px;margin: 0 5% 10px;overflow-y: scroll;}
</style>
</head>

<body class="bggrey heightp100">
	<section id="wrapBox" class="sectionBox bggrey pos-relative minheight100" v-cloak>
		<section id="tabBox" class="tabBox pos-relative minheight100 zindex10">
			<div class="sectionBox list-onsell bggrey">
				<div v-if="recordDetail.records.length === 0" class="mtop20 t-center f18 lineheight36">暂无记录</div>
				<div v-for="list in recordDetail.records" class="displaybox m10 p5 borderradius4 bgwhite">
					<a class="item-img-thumb mright15 bggrey" :href="'activityDetail.html?activityId=' + list.activityId">
						<img :src="list.activityPicList[0]" />
					</a>
					<div class="boxflex01">
						<div class="lineheight140 text-clamp f18">{{list.activityTitle }}</div>
						<div class="f12 grey mtop5">{{list.buyCount }} 人已参与</div>
						<template v-if="list.isSettle === 1">
							<div v-if="list.buyRecord.recordStatus !== 4" class="f12 red mtop5 falseBuyBtn">1元钱已转入粮票账户，查看></div>
							<ul class="displaybox mtop20">
								<li class="f14 awards-list-btn">成功购买名单</li>
								<li class="boxflex01 f14">
									<div v-if="list.buyRecord.recordStatus === 4" class="right mright5 isBinggo on">成功购买</div>
									<div v-else class="right mright5 isBinggo">继续努力</div>
								</li>
							</ul>
							
							<div class="awards-list dsn">
								<div v-for="(phoneList, index) in list.winRecords" class="wp50 left mtop10" :class="[{'red' : (phoneList.recordStatus === 4 && phoneList.tUserFId === list.buyRecord.tUserFId), 't-right' : (index % 2 !== 0)}]">
									{{phoneList.userMobile.substring(0,3)}}****{{phoneList.userMobile.substring(6,10)}}
								</div>
							</div>
                		</template>
						
						<ul v-if="list.isSettle === 0 && list.activityStatus === 1" class="displaybox mtop20">
							<li class="boxflex01 f14">
								<div class="right mright5 isBinggo bgorange">进行中</div>
							</li>
						</ul>
						<ul v-if="list.isSettle === 0 && list.activityStatus === 3" class="displaybox mtop20">
							<li class="boxflex01 f14">
								<div class="right mright5 isBinggo bgorange">结果公布中</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
	
			<section class="sectionBox wrap-bg pop-box02 dsn">
				<div class="tips-box pos-relative">
					<div id="adwardListCloseBtn"><img src="${apiPathHttps}/images/close_icon.png"/></div>
					<div class="t-center ptb10 pos-relative">
						<div class="adward-title"><img class="wp80" src="${apiPathHttps}/images/adward_title.png"/></div>
					</div>
					<div class="grey t-center m010">恭喜以下用户，请留意短信内容或咨询物业管理处领取货品哦。</div>
					<div class="awards-list mtop15">
					</div>	
				</div>
			</section>
		</section>
	</section>

<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePathHttps}/commonjs/vue.2.3.0.min.js"></script>
<script src="${resourcePathHttps}/commonjs/axios.0.16.1.min.js"></script>
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script src="../js/vue.lukygo.record.js"></script>
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