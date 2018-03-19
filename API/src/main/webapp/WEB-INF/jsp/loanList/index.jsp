<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">
<title>借贷</title>
<link rel="stylesheet" href="../css/loanlist.css?v20170629a">
</head>
<body class="bggrey">
<div id="container" class="section-box bggrey pb10 pos-relative" v-cloak>
	<section class="notice-box m010 mtop30 prev-box" v-if="isPrevShow">
		<div class="mtop15 prev-title-txt01"></div>
		<ul class="f14 mtop10 list-prev-box box-shadow">
			<li class="displaybox" style="height: 38px;">
				<div class="vw12 mright12 prev-img-bg"></div>
				<div class="boxflex01 prev-title-txt02"></div>
			</li>
			<li class="displaybox mtop5 list-info" style="height: 32px;">
				<div class="boxflex01 mright15">
					<div class="grey prev-txt01"></div>
					<div class="mtop5 prev-txt02"></div>
				</div>
			</li>
		</ul>
		<ul class="f14 mtop10 list-prev-box box-shadow">
			<li class="displaybox" style="height: 38px;">
				<div class="vw12 mright12 prev-img-bg"></div>
				<div class="boxflex01 prev-title-txt02"></div>
			</li>
			<li class="displaybox mtop5 list-info" style="height: 32px;">
				<div class="boxflex01 mright15">
					<div class="grey prev-txt01"></div>
					<div class="mtop5 prev-txt02"></div>
				</div>
			</li>
		</ul>
	</section>
	<section class="notice-box m010 mtop30 prev-box" v-if="isPrevShow">
		<div class="mtop15 prev-title-txt01"></div>
		<ul class="f14 mtop10 list-prev-box box-shadow">
			<li class="displaybox" style="height: 38px;">
				<div class="vw12 mright12 prev-img-bg"></div>
				<div class="boxflex01 prev-title-txt02"></div>
			</li>
			<li class="displaybox mtop5 list-info" style="height: 32px;">
				<div class="boxflex01 mright15">
					<div class="grey prev-txt01"></div>
					<div class="mtop5 prev-txt02"></div>
				</div>
			</li>
		</ul>
		<ul class="f14 mtop10 list-prev-box box-shadow">
			<li class="displaybox" style="height: 38px;">
				<div class="vw12 mright12 prev-img-bg"></div>
				<div class="boxflex01 prev-title-txt02"></div>
			</li>
			<li class="displaybox mtop5 list-info" style="height: 32px;">
				<div class="boxflex01 mright15">
					<div class="grey prev-txt01"></div>
					<div class="mtop5 prev-txt02"></div>
				</div>
			</li>
		</ul>
	</section>
	
	<section v-for="(loanEntityList, index) of list" class="notice-box m010 mtop20">
		<div class="mtop15"><span class="f16 mright12">{{loanEntityList.typeName}}</span><span class="f13 grey">{{loanEntityList.typeDesc}}</span></div>
		<a v-for="item of loanEntityList.loanProductList" href="javascript:void(0)" @click.stop.prevent="jumpEvent(item)">
			<ul class="f12 mtop10 list-prev-box box-shadow">
				<li class="displaybox" style="height: 38px;">
					<div class="vw12 mright12" style="line-height: 0;"><img :src="item.picUrl"/></div>
					<div class="boxflex01"><span class="f16 bold mright12">{{item.lpName}}</span><span class="f13 grey">{{item.lpDesc}}</span></div>
				</li>
				<li class="displaybox mtop10 list-info f13" style="height: 32px;">
					<div class="boxflex01 line smallborderrightgrey mright15">
						<div class="grey">额度(元)</div>
						<div>{{item.maxLoanDesc}}</div>
					</div>
					<div class="boxflex01 line smallborderrightgrey mright15">
						<div class="grey" v-if="item.lpName === '花易借'">利息(日)</div>
						<div class="grey" v-else>利息(月)</div>
						<div>{{item.rateDesc}}</div>
					</div>
					<div class="boxflex01">
						<div class="grey">期限</div>
						<div>{{item.expire}}</div>
					</div>
				</li>
			</ul>
		</a>
	</section>
	<!-- 消息提示-->
	<transition name="fade" tag="div">
		<section class="pop-tips" v-if="tipsShow">
			<div class="pop-tips-text">该功能需要先绑定手机号码<br>请到[我]-[登录与绑定]中进行绑定</div>
		</section>
	</transition>
</div>
<script src="${resourcePathHttps}/commonjs/vue.2.3.0.min.js"></script>
<script src="${resourcePathHttps}/commonjs/axios.0.16.1.min.js"></script>
<script src="../js/loan.list.js"></script>
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