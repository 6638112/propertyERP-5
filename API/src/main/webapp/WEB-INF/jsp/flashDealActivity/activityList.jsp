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
<link rel="stylesheet" href="../css/yiyuangou.css" type="text/css">
</head>

<body class="heightp100" style="opacity:0">
	<section id="wrapBox" class="sectionBox pos-relative minheight100 bggrey">
		<section id="tabBox" class="tabBox paddingbottom pos-relative minheight100 zindex10 nobg">
			<div class="sectionBox list-onsell bgwhite">
				<div class="lineheight0">
					<img src="../images/top_banner.jpg"/>
				</div>
				<div class="swiper-container">
					<ul class="sell-date swiper-wrapper" style="margin: 0;">
						<c:forEach items="${activityList }" var="activitiesTab" varStatus="stat02">
							<li class="swiper-slide pos-relative <c:if test="${stat02.index == 0}">red</c:if>">
								<span class="bold date-title">${activitiesTab.startDay}</span>
								<c:choose>
									<c:when test="${stat02.index == 0 && activitiesTab.todayStatus==1}">
										<div class="swiper-text text01">今日必抢</div>
									</c:when>
									<c:otherwise>
										<div class="swiper-text text02">即将开场</div>
									</c:otherwise>
								</c:choose>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div class="divide-box"></div>

				<c:forEach items="${activityList }" var="activities" varStatus="stat">
					<div class="swiper-con-box <c:if test="${stat.index != 0}">dsn</c:if>">
						<c:forEach items="${activities.flashDealActivityDetailEntities }" var="list" varStatus="stat01">
								<div class="displaybox pos-relative sell-list borderbottom" data-id="${list.activityId }">
									<c:if test="${list.buyRecord != null }">
										<img class="has-bought-icon" src="../images/bought_icon.png"/>
									</c:if>
									<div class="item-img-thumb mright15"><a href="activityDetail.html?userId=${userId}&activityId=${list.activityId }"><img class="disblock" src="${list.activityPicList[0] }<c:choose><c:when test="${fn:contains(list.activityPicList[0], '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,w_160,h_160,limit_0/format,jpg/quality,q_95/interlace,1" /></a></div>
									<div class="boxflex01">
										<a href="activityDetail.html?userId=${userId}&activityId=${list.activityId }">
											<div class="title lineheight24 text-clamp2 f18 black">${list.activityTitle }</div>
										</a>
										<div class="f12 grey mtop5">限<span>${list.prizeCount }</span>份，已有<span class="red">${list.buyCount }</span>人参与</div>
										<ul class="mtop10">
											<li class="<c:if test="${list.activityStatus == 1 || list.activityStatus == 2 }">countdown on </c:if>displaybox grey"
													<c:if test="${list.activityStatus == 1 }"> data-time="${list.activityEndTime }"</c:if>
												<c:if test="${list.activityStatus == 2 }"> data-time="${list.activityStartTime }"</c:if>>
												<span class="boxflex01">时间：${list.startTime}-${list.endTime}</span>
												<c:choose>
												<c:when test="${list.activityStatus == 1}">
													<c:if test="${list.buyRecord == null }">
													<span class="boxflex01 t-right mtop3 buyNowBtn"><a href="activityDetail.html?userId=${userId}&activityId=${list.activityId }"><img src="../images/sell_btn.png"/></a></span>
												</c:if>
												<c:if test="${list.buyRecord != null }">
													<span class="boxflex01 t-right mtop3"><img src="../images/has_hought_btn.png"/></span>
												</c:if>
												</c:when>
												<c:when test="${list.activityStatus == 2}">
													<c:if test="${list.remindStatus == 0 }">
														<span class="boxflex01 t-right mtop3 remind-btn"><img src="../images/remind_btn.png"/></span>
													</c:if>
													<c:if test="${list.remindStatus == 1 }">
														<span class="boxflex01 t-right mtop3"><img src="../images/has_remind.png"/></span>
													</c:if>
												</c:when>
												<c:when test="${list.activityStatus == 3}">
													<c:if test="${list.isSettle == 1}">
														<span class="boxflex01 t-right mtop3"><img src="../images/act_done.png"/></span>
													</c:if>
													<c:if test="${list.isSettle == 0}">
														<span class="boxflex01 t-right mtop3"><img src="../images/act_done_01.png"/></span>
													</c:if>
												</c:when>
												</c:choose>
											</li>
										</ul>
									</div>
								</div>
						</c:forEach>
					</div>
				</c:forEach>
			</div>
			
		    <section class="divide-box"></section>
		    <ul class="bottom-menu-box displaybox t-center black f18 bordertopgrey">
		    	<li id="viewDescDetail" class="boxflex01"><img class="icon-bottom icon-desc" src="../images/icon_desc.png"/> 活动规则</li>
		    	<li class="boxflex01 borderleft"><a class="disblock" href="myRecord.html?userId=${userId }"><img class="icon-bottom" src="../images/icon_history.png"/> 参与记录</a></li>
		    </ul>
	
			<section class="sectionBox wrap-bg pop-box02 dsn">
				<div class="tips-box desc-box pos-relative">
					<div id="closeTipsBtn"></div>
					<div><img src="../images/desc_text.png?v1"/></div>	
				</div>
			</section>
		</section>
	</section>

<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script src="../js/jquery.downCount.forActivity.js"></script>
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script src="${resourcePathHttps}/commonjs/swiper.min.js"></script>
<script src="../js/layer.min.js"></script>
<script src="../js/imgResize.js"></script>
<script src="../js/yiyuangou.js"></script>
<script>
$(function(){
	new FastClick(document.body);

    if( $('.sell-list').length > 0 || ( $('.sell-list').length === 0 && getUrlParam('pic') == null )){
		$('body').css('opacity','1');
	}
	//如果没有商品，则跳转到活动预告
	if( $('.sell-list').length === 0 && getUrlParam('pic') != null ){
		location.href = 'https://resource.jiefangqu.com/docs/actPreview/index.htm?pic=' + getUrlParam('pic');
	}
	function getUrlParam(name) {
		//构造一个含有目标参数的正则表达式对象  
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		//匹配目标参数  
		var r = window.location.search.substr(1).match(reg);
		//返回参数值  
		if (r != null)
			return unescape(r[2]);
		return null;
	}
	
	var xjmessage = '${message}';
	if(xjmessage !== '' && xjmessage !== null && xjmessage !== undefined) {
		setTimeout(function(){
			layer.alert(xjmessage);
		},500)
	}
	//提醒我按钮
	var hasReminded = false;
	$('.remind-btn').click(function(){
		if(hasReminded){
			return;
		}
		var $this = $(this);

		var thisDateTime = $this.parents('.countdown').attr('data-time');
		var thisDate = thisDateTime.substring(3,5)*1;
		var thisHour = thisDateTime.substring(11,13)*1;
		var thisMinutes = thisDateTime.substring(14,16)*1;
		var curDate = new Date().getDate();
		var curHour = new Date().getHours();
		var curMinutes = new Date().getMinutes();
		if((thisDate-curDate) === 0 && (thisHour-curHour) === 0 && (thisMinutes-curMinutes) <= 3){
			layer.alert('活动即将开始!');
			return;
		}
		
		var thisActivityId = $this.parents('.sell-list').attr('data-id');
		hasReminded = true;
		$.ajax({
			url: '../flashDealActivity/flashDealRemind.json',
			dataType: 'json',
			data:{'activityId':thisActivityId},
			async: true,
			timeout: 10000,
			success: function(data){
				if(data.status=="0000"){
					layer.alert('设置成功，开始前3分钟提醒您');
					$this.find('img').attr('src', '../images/has_remind.png');
					$this.off('click');
					hasReminded = false;
				} else {
					layer.alert(data.message);
					hasReminded = false;
				}
			},
			complete: function(XMLHttpRequest,status){
				if(status === 'timeout'){
					layer.alert('网络不给力，请重试');
					hasReminded = false;
				}
			},
			error: function(){
				hasReminded = false;
			}
		});
	});
	
	//倒计时
	if($('.countdown').length){
		$('.countdown').each(function(){
		    $(this).downCount({
		        offset: +8
		    }, function () {
		        locaReload();
		    });	
		})
	}
    
	//查看活动说明详情
	$('#viewDescDetail').click(function(){
		$('#wrapBox').addClass('heightp100');
		$('.pop-box02').removeClass('dsn');
	});
	//关闭活动说明详情
	$('#closeTipsBtn').click(function(){
		$('#wrapBox').removeClass('heightp100');
		$('.pop-box02').addClass('dsn');
	});
	
	//重设弹出框尺寸
	resetPopBoxSize();
	setInterval(function(){
		resetPopBoxSize();
	},1000);
	
	//日期滑动切换
	var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        slidesPerView: 3.5,
        spaceBetween: 0,
        freeMode: true
    });
    setSlideWidth('.sell-date');
    tabSwap('.swiper-slide', '.swiper-con-box');

    //重设图片尺寸
    var itemImgWidth = $(".item-img-thumb").width();
    $(".item-img-thumb").height(itemImgWidth);
    $('.item-img-thumb').reSetSwiperImgSize(itemImgWidth,itemImgWidth);
});
//重设图片宽度
function resetPopBoxSize(){
	var windowWidth = $('#wrapBox').width();
	var windowHeight = $('body').height();
	if((windowWidth/windowHeight) > 508/840){
		$('.desc-box').width(508*windowHeight*0.8/840).css({'top':'10%', 'margin-left':(windowWidth-508*windowHeight*0.8/840)/2});
	}
}

function locaReload(){
	location.reload();
}
//标签切换，加载时显示默认标签
function tabSwap(tabObj, conObj){
	
	$(tabObj).click(function(){
		var $this = $(this);
		var thisIndex = $this.index();
		
		//点击切换标签
		$this.addClass('red').siblings().removeClass('red');
		$(conObj).eq(thisIndex).show().siblings(conObj).hide();
	});
}
//获取slide宽度
function setSlideWidth(obj){
	var totalWidth = $(obj).find('li').width()*$(obj).find('li').length;
	$(obj).width(totalWidth);
}
</script>
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1261123817'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1261123817' type='text/javascript'%3E%3C/script%3E"));</script>
</body>
</html>