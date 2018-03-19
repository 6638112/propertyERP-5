<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<title>到家服务详情</title>
<script>
	//判断是否已关注公众号20170629
	if('${subscribe}' == '0' && location.search.indexOf('weixin') > -1){
		location.href = 'https://resource.jiefangqu.com/docs/followjfq/index.htm';
	}
</script>
<link type="text/css" rel="stylesheet" href="css/homeservice.css?20170809">
<link type="text/css" rel="stylesheet" href="${resourcePathHttps}/commoncss/progressive.img.css">
</head>
<body class="pos-relative">
<div id="container" v-cloak>
	<div class="main-part01">  
		<section id="slideBox" class="slideBox details-banner mtop0 bggrey">
		    <div id="slidePic" class="bd">
		        <ul id="slideLi">
		            <li class="progressive detail-swiper-img-box" v-for="(item, index) of productDetail&&productDetail.productPicList">
		            	<img v-if="index === 0" 
		            	 class="preview lazy" 
		            	 :data-src="item + '?x-oss-process=image/resize,m_fill,w_640,h_640,limit_0/format,jpg/quality,q_90/interlace,1'"  
		            	 :src="item + '?x-oss-process=image/resize,m_fill,w_39,h_39/format,jpg/quality,q_90/interlace,1'" />
		            	<img v-else 
		            	 :src="item + '?x-oss-process=image/resize,m_fill,w_640,h_640,limit_0/format,jpg/quality,q_90/interlace,1'" />
		            </li>
		        </ul>
		    </div>
		    <div id="slideDot" class="hd">
		        <ul></ul>
		    </div>
		</section>
		
		<section class="sectionBox">
	        <div id="openLocation" class="f16 bold mtop10 p010 minheight21">{{productDetail&&productDetail.name}}<div class="text-prev-bg" style="width: 90%; height: 16px;" v-if="!productDetail"></div></div>
	        <div class="message p010 minheight21" v-if="themeAdDesc !== null">
	            <span class="f12 lineheight120">{{themeAdDesc}}<div class="text-prev-bg" style="width: 60%; height: 10px;" v-if="!productDetail"></div></span>
	        </div>
	        <div class="message p010 minheight21" v-if="productDetail&&productDetail.desc !== ''">
	            <span class="grey f12 lineheight120">{{productDetail&&productDetail.desc}}<div class="text-prev-bg" style="width: 60%; height: 10px;" v-if="!productDetail"></div></span>
	        </div>
            <div class="hot-price f24 p010 mtop5 red bold">￥{{productDetail&&productDetail.productSpecList[0].sellPrice}}</div>
            <!-- 只有一个规格时显示 -->
		    <div v-cloak v-if="productSpecList && productSpecList.length === 1">
		        <ul class="f16 displaybox list-box" style="padding:5px 0 5px 10px" v-for="(item, index) of productSpecList" v-bind:key="item">
		        	<li class="boxflex01 word-break1 mright20">{{item.specification}}</li>
		        	<li class="server-price t-right">
		        		<div class="red">￥ {{item.sellPrice}}/{{item.priceUnit}}</div>
		        		<div class="grey f12 line-through mtop3">￥ {{item.marketPrice}}/{{item.priceUnit}}</div>
		        	</li>
			    	<li class="displaybox">
				    	<div class="btn-minus pleft10 pt10 pb10" @click="counterReduce(item)">
				    		<img v-if="item.counter > 0 && totalCounter > 1" class="w20 align-middle" src="images/icon_minus_red.png"/>
				    		<img v-else class="w20 align-middle" src="images/icon_minus.png"/>
				    	</div>
				    	<div class="server-num">{{item.counter}}</div>
				    	<div class="btn-add pright10 pt10 pb10" @click="counterAdd(item)"><img class="w20 align-middle" src="images/icon_add.png"/></div>
					</li>
		        </ul>
		    </div>
		    <!--  多个规格时显示 -->
		    <div v-else class="displaybox list-box mtop15 bgwhite p1210 lineheightnormal boxalign-start pos-relative overvisible">
		    	<div class="select-tips" v-if="tipsShow === true" @click="toggleStandard">点击选择更多服务<div class="select-tips-arrow"></div></div>
	        	<div class="mright10 f14" @click="toggleStandard">已选</div>
	            <ul class="f14 boxflex01" @click="toggleStandard">
	            	<li class="word-break1" v-for="(item, index) of standardSelectedList">{{item.specification}}</li>
	            	<!--<li class="word-break1">2台背投电视+3台</li>-->
	            </ul>
	            <ul class="f14 t-right mright10" @click="toggleStandard">
	            	<li class="word-break1" v-for="(item, index) of standardSelectedList"><span class="red mright5">￥ {{item.sellPrice}}/{{item.priceUnit}}</span> x{{item.counter}}</li>
	            	<!--<li class="word-break1"><span class="red">￥ 298.00/台</span> x1</li>-->
	            </ul>
	            <div class="btn-more" @click="toggleStandard"><img class="w20 align-middle" src="images/icon_more.png"/></div>
		    </div>
		    <!--  耗材 -->
		    <div class="displaybox list-box bgwhite p1210 lineheightnormal boxalign-start" v-if="hasServerItems">
	        	<div class="mright10 f14">耗材</div>
	            <ul class="f14 boxflex01">
	            	<li class="grey" v-show="itemSelectedNum === 0">价格透明，师傅带上门</li>
	            	<li class="word-break1" v-show="itemSelectedNum > 0" v-for="(item, index) of itemSelectedList">{{item.name}}</li>
	            </ul>
	            <ul class="f14 t-right mright10" v-show="itemSelectedNum > 0">
	            	<li class="word-break1" v-for="(item, index) of itemSelectedList"><span class="red mright5">￥ {{item.price}}</span> x{{item.counter}}</li>
	            	<!--<li class="word-break1"><span class="red">￥ 298.00/台</span> x1</li>-->
	            </ul>
	            <div class="btn-more" @click="toggleServerItems"><img class="w20 align-middle" src="images/icon_more.png"/></div>
		    </div>
		    <div class="m010 mtop10 grey">由 {{productDetail&&productDetail.supplierName}} 提供本项服务</div>
			<section class="divide-box mtop10"></section>
		</section>   
		
		<section class="sectionBox">
			<div class="m15 f16 t-center"><img class="w20 mtop3 align-middle" src="images/sale_title_left.png" /><span class="m015">服务详情</span><img class="w20 mtop3 align-middle" src="images/sale_title_right.png" /></div>
            <div class="info">
            	<div class="progressive" :class="index > 0 ? 'mtop5' : ''" v-for="(item, index) of productDetail&&productDetail.introducePicList">
                	<img 
                	class="preview lazy" 
                	:data-src="item + '?x-oss-process=image/resize,w_640/format,jpg/quality,q_90/interlace,1'"
                	:src="item + '?x-oss-process=image/resize,w_39/format,jpg/quality,q_90/interlace,1'"  />
                </div>
            </div>
			<section class="divide-box"></section>
			<section class="pbfooter"></section>
		</section>
	</div>
</div>
<!-- 预付按钮 -->
<div id="payBtnBox" class="sectionBox" v-cloak>
    <div class="exchang-fixed displaybox">
        <div class="btn-fixed boxflex01">
            <div @click="payThisBill" class="btn-cart">
            	<a v-if="payType == 1" class="bgred" href="javascript:void(0)">立即预约 ￥{{totalPrice}}</a>
            	<a v-if="payType == 2" class="bgred" href="javascript:void(0)">下一步</a>
            </div>
        </div>
    </div>
</div>
<!-- 消息提示-->
<section class="pop-tips hide">
	<div class="pop-tips-text">请选择</div>
</section>
<!-- 规格 -->
<div id="standardsBox" v-cloak>
	<transition name="fade" tag="div">
		<section class="pop-box pbfooter overscroll" style="max-height: calc(70% - 43px);" v-show="standardShow">
		    <div>
		        <ul class="f16 displaybox borderbottomgrey" style="padding: 15px 5px 15px 15px;" v-for="(item, index) of productSpecList" v-bind:key="item">
		        	<li class="boxflex01 word-break1 mright20">{{item.specification}}</li>
		        	<li class="server-price mright5 t-right">
		        		<div>￥ {{item.sellPrice}}</div>
		        		<div class="grey f12 line-through mtop3">￥ {{item.marketPrice}}</div>
		        	</li>
			    	<li class="displaybox">
				    	<div class="btn-minus pleft10 pt10 pb10" @click="counterReduce(item)">
				    		<img v-if="item.counter > 0 && totalCounter > 1" class="w20 align-middle" src="images/icon_minus_red.png"/>
				    		<img v-else class="w20 align-middle" src="images/icon_minus.png"/>
				    	</div>
				    	<div class="server-num">{{item.counter}}</div>
				    	<div class="btn-add pright10 pt10 pb10" @click="counterAdd(item)"><img class="w20 align-middle" src="images/icon_add.png"/></div>
					</li>
		        </ul>
		    </div>
		    <div class="exchang-fixed select-server-btn displaybox" @click="checkStandard">
		        <div class="btn-fixed boxflex01 wp100 t-center">
		            <a class="bgred" href="javascript:void(0)">选好了</a>
		        </div>
		    </div>
		</section>
	</transition>
	<transition name="fade" tag="div">
		<section class="bg-wrap" v-show="standardShow"></section>
	</transition>
</div>
<!-- 耗材 -->
<div id="serverItemsBox" v-cloak>
	<transition name="fade" tag="div">
		<section class="pop-box maxheightp70 overscroll" v-show="serverItemsShow">
	        <div class="my-order-item" v-for="(item, index) of serverItemsList&&serverItemsList.list">
	        	<div class="single-item-wrap">
	                <div class="displaybox order-info-box p00 borderbottomgrey">
	                    <div class="item-info-img mleft10"><img :src="item.pic + '?x-oss-process=image/resize,w_92,h_92/format,jpg/quality,q_90/interlace,1'"></div>
		                <ul class="order-info-address boxflex01">
	 	                    <!--<a class="disblock item-url" :href="itemProductHost + '/API/ebuyNew/newEbuyproductDetail.html?readonly=true&productId=' + item.id"> -->
		                    <a class="disblock item-url" href="javascript:void(0)">
		                        <li>
		                        	<div class="single-item-name">{{item.name}}</div>
		                        	<div class="mtop5">￥<span class="single-item-price">{{item.price}}</span></div>
		                        </li>
		                    </a>
	                    </ul>
	                	<div class="displaybox">
				    		<div class="btn-minus pleft10 pt10 pb10" @click="itemCounterReduce(item)">
				    			<img v-if="item.counter > 0" class="w20 disblock align-middle" src="images/icon_minus_red.png"/>
				    			<img v-else class="w20 disblock align-middle" src="images/icon_minus.png"/>
				    		</div>
		                	<input class="input-normal itemNum" type="text" name="itemSelectNum" v-model.number="item.counter" v-on:blur="noBlank(item, $event.target.value)" v-on:keyup="onlyNumber(item, $event.target.value)" maxlength="3" />
				    		<div class="btn-add pright10 pt10 pb10" @click="itemCounterAdd(item)"><img class="w20 disblock align-middle" src="images/icon_add.png"/></div>
	                	</div>
	                </div>
	               </div>
	        </div>
			<div class="section-box loading grey" v-show="isLoading"><img src="images/loading01.gif" /> 加载中…</div>
			<div class="section-box loading grey" v-if="!hasNext && !hasNoItems">已加载至最后一页</div>
			<div class="section-box loading grey" v-if="hasNoItems">该商品暂无耗材</div>
		    <div class="pbfooter"></div>
	        <ul class="bottom-menu-box displaybox t-center">
	            <li class="p00 boxflex01">
	            	<input @click="checkItemSelected" class="btn-submit btn-next noradius bordertbgrey bgred white" type="button" value="选好了">
	            </li>
	        </ul>
		</section>
	</transition>
	<transition name="fade" tag="div">
		<section class="bg-wrap" v-show="serverItemsShow"></section>
	</transition>
</div>
<div id="gotop" class="go-top dsn" style="display: block;"><img src="images/icon-gotop.png"><br>顶部</div>
</body>
<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePathHttps}/commonjs/vue.2.3.0.min.js"></script>
<script src="${resourcePathHttps}/commonjs/axios.0.16.1.min.js"></script>
<script src="${resourcePathHttps}/commonjs/TouchSlide.1.1.js"></script>
<script src="${resourcePathHttps}/commonjs/progressive.img.js"></script>
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="js/wx.share.info.js"></script>
<script src="js/method.common.js"></script>
<script src="js/vue.serverdetails.js"></script>
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