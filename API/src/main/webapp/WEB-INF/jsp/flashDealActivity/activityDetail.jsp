<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>活动详情</title>
<link rel="stylesheet" href="../css/swiper.css" type="text/css">
<link rel="stylesheet" href="../css/yiyuangou.css" type="text/css">
<link rel="stylesheet" href="../dist/photoswipe.css">
<link rel="stylesheet" href="../dist/default-skin/default-skin.css">
<style>
.my-gallery { width: 100%; float: left;}
.my-gallery img { width: 100%;}
.my-gallery figure { display: block; float: left; margin:0; overflow: hidden;}
.my-gallery figcaption { display: none; }
</style>
</head>

<body class="bggrey heightp100">
	<section id="wrapBox" class="sectionBox bggrey pos-relative minheight100">
		<section id="tabBox" class="tabBox pos-relative minheight100 zindex10">
			<section class="sectionBox">
			    <ul class="f14 bgdarkblue details-time-info wp100">
			        <li class="
							        <c:if test="${detail.activityStatus == 1 || detail.activityStatus == 2 }">countdown</c:if> left" 
							        <c:if test="${detail.activityStatus == 1 }">data-time="${detail.activityEndTime }"</c:if> 
							        <c:if test="${detail.activityStatus == 2 }">data-time="${detail.activityStartTime }"</c:if> >
							时间：${detail.startTime}-${detail.endTime}
			        </li>
			        <li class="right">${detail.buyCount } 人已参与</li>
			    </ul>
			    <!-- Swiper -->
			    <div class="swiper-container pos-relative">
			        <div class="swiper-wrapper my-gallery imgLoading">
			            <c:forEach items="${detail.activityPicList }" var="picList">
				            <figure class="swiper-slide" itemprop="associatedMedia" itemscope style="background:url(../images/loading01.gif) no-repeat center; background-size:26px;">
				              <a href="${picList }" itemprop="contentUrl" data-size="640x540">
				                  <img src="${picList }<c:choose><c:when test="${fn:contains(picList, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,w_560,h_473,limit_0/format,jpg/interlace,1/quality,q_95" itemprop="thumbnail" />
				              </a>
				            </figure>
			            </c:forEach>
			        </div>
			        <!-- Add Pagination -->
			        <div class="swiper-pagination"></div>
			    </div>
			    
				<!--swiper zoom start-->
			    <div class="pswp" tabindex="-1" role="dialog" aria-hidden="true">
			        <div class="pswp__bg"></div>
			        <div class="pswp__scroll-wrap">
			            <div class="pswp__container">
			                <div class="pswp__item"></div>
			                <div class="pswp__item"></div>
			                <div class="pswp__item"></div>
			            </div>
			            <div class="pswp__ui pswp__ui--hidden">
			                    <div class="pswp__top-bar">
			                        <div class="pswp__counter"></div>
			                        <div class="pswp__button pswp__button--close" title="Close (Esc)"></div>
			                        <div class="pswp__button pswp__button--zoom" title="Zoom in/out"></div>
			                        <div class="pswp__preloader">
			                            <div class="pswp__preloader__icn">
			                              <div class="pswp__preloader__cut">
			                                <div class="pswp__preloader__donut"></div>
			                              </div>
			                            </div>
			                        </div>
			                    </div>
			                    <div class="pswp__share-modal pswp__share-modal--hidden pswp__single-tap">
			                        <div class="pswp__share-tooltip"></div> 
			                    </div>
			                    <div class="pswp__button pswp__button--arrow--left" title="Previous (arrow left)">
			                    </div>
			                    <div class="pswp__button pswp__button--arrow--right" title="Next (arrow right)">
			                    </div>
			                    <div class="pswp__caption">
			                        <div class="pswp__caption__center"></div>
			                    </div>
			              </div>
			        </div>
			    </div>
			    <!--swiper zoom end-->
			</section>
			<section class="sectionBox">
			   <div class="info pb0">
			        <div id="itemOnsell" class="f16 bold mtop15 p010" data-id="${detail.activityId }" data-itemid="${detail.productShelfId }">${detail.activityTitle }</div></a>
			        <div class="message p010">
			            <span class="f14">${detail.introduceContent }</span>
			        </div>
			        <div class="item-standard mleft10 mtop15 bordertopgrey">
			            <div class="number displaybox">
			            	<div class="boxflex01 name f14">每人限购 1 份</div>
			            	<c:if test="${detail.isSettle == 1 && detail.activityStatus == 3}">
			            		<div class="boxflex01 f14 t-right red mright10 view-pop-box-btn" data-class="pop-box02">成功购买名单 <img class="w12 mleft5" src="../images/arrow-down.png"/></div>
			            	</c:if>
			            </div>
			        </div> 
			        <div class="mleft10 ptb15 bordertopgrey">
			            <div class="number displaybox">
			            	<div class="boxflex01 name f14 green">自提点：<span id="zitidian">${detail.merchantName}</span></div>
			            </div>
			        </div>   
					<section class="divide-box bordertbgrey"></section>
					<div id="jumpToSinglePage" class="displaybox sell-list">
						<img class="item-img-thumb mright15" src="${detail.activityPicList[0] }<c:choose><c:when test="${fn:contains(detail.activityPicList[0], '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,w_160,h_160/format,jpg/quality,q_95/interlace,1" />
						<div class="boxflex01">
							<div class="lineheight140 f16 text-clamp2">${detail.productName }</div>
							<div class="f18 red mtop15 bold">￥<span>${detail.productPrice/100 }</span></div>
						</div>
	            		<div class="w20 box-arrow t-right grey"></div>
					</div>
					<!-- <section class="divide-box bordertbgrey"></section>
				    <div class="list-box bgwhite tap-nobg displaybox pointer view-pop-box-btn" data-class="pop-box03">
			            <div class="item-standard-name height36 f16 boxflex01">活动规则</div>
			            <div class="boxflex01 box-arrow t-right grey"></div>
				    </div> -->
					<section class="divide-box bordertbgrey"></section>
			        <ul class="item-price f16 borderbottomgrey ptb1210 tap-nobg">
			            <li class="left">联系客服</li>
			            <li class="right"><a class="blue" href="tel:${servePhone }">${servePhone }</a></li>
			        </ul>
			    </div>
			</section>           
			<section class="divide-box"></section>
			<c:if test="${detail.activityStatus == 1}">
				<section class="pbfooter"></section>
			</c:if>
			<div class="sectionBox">
			    <div class="exchang-fixed displaybox">
			        <c:choose>
               			<c:when test="${detail.activityStatus == 1}">
               				<c:if test="${detail.buyRecord == null }">
	               				<div id="buyNowBtn" class="boxflex01 t-center bgred ptb20 white pointer">
						    		<div class="f16">立即参与</div>
						        </div>
							</c:if>
               				<c:if test="${detail.buyRecord != null }">
	               				<div class="boxflex01 t-center bgdarkgrey ptb20 white pointer">
						    		<div class="f16">已参与</div>
						        </div>
							</c:if>
               			</c:when>
               		</c:choose>
			    </div>
			</div>
	
			<section class="sectionBox wrap-bg pop-box02 dsn">
				<div class="tips-box pos-relative">
					<div id="adwardListCloseBtn" class="common-close-btn"><img src="../images/close_icon.png"/></div>
					<div class="t-center ptb10 pos-relative">
						<div class="adward-title"><img class="wp80" src="../images/adward_title.png"/></div>
					</div>
					<div class="grey t-center m010">恭喜以下用户，请留意短信内容或咨询物业管理处领取货品哦。</div>
					<ul class="awards-list mtop15">
						<c:forEach items="${detail.winRecords }" var="phoneList" varStatus="stat">
							<c:if test="${stat.index%2==0}">
								<li class="displaybox">
								<div class="boxflex01<c:if test="${phoneList.recordStatus==4 && phoneList.tUserFId == detail.buyRecord.tUserFId}"> red</c:if>">
										${fn:substring(phoneList.userMobile, 0, 3)}****${fn:substring(phoneList.userMobile, 7, fn:length(phoneList.userMobile))}
								</div>
							</c:if>
							<c:if test="${stat.index%2!=0}">
								<div class="boxflex01 t-right<c:if test="${phoneList.recordStatus==4 && phoneList.tUserFId == detail.buyRecord.tUserFId}"> red</c:if>">
										${fn:substring(phoneList.userMobile, 0, 3)}****${fn:substring(phoneList.userMobile, 7, fn:length(phoneList.userMobile))}
								</div>
								</li>
							</c:if>
						</c:forEach>
						<c:if test="${fn:length(detail.winRecords) % 2 == 1}">
							</li>
						</c:if>
					</ul>	
				</div>
			</section>
	
			<section class="sectionBox wrap-bg pop-box03 dsn">
				<div class="tips-box desc-box pos-relative">
					<div id="closeTipsBtn" class="common-close-btn"></div>
					<div><img src="../images/desc_text.png"/></div>	
				</div>
			</section>
		</section>
		
		<div class="tips-box tips-done bounceInDown animated1s dsn">
			<img class="rotateZoomIn animated1s delay" src="../images/icon-tips-done.png"/><br>成功参与
		</div>
	</section>
<script src="../js/jquery-1.11.2.min.js"></script>
<script src="../js/jquery.downCount.forActivity.js"></script>
<script src="../js/fastclick.js"></script>
<script>
$(function(){
	new FastClick(document.body);

	//查看弹框
	$('.view-pop-box-btn').click(function(){
		var thisClass = $(this).attr('data-class');
		$('#wrapBox, #tabBox').addClass('heightp100');
		$('.' + thisClass).removeClass('dsn');
	});
	//关闭弹框
	$('.common-close-btn').click(function(){
		$('#wrapBox, #tabBox').removeClass('heightp100');
		$('.wrap-bg').addClass('dsn');
	});

	//设置轮播图片高度
    var $swiperContainer = $('.swiper-container');
    var swiperContainerWidth = $swiperContainer.width();
    $swiperContainer.height(swiperContainerWidth*540/640);
	

	//倒计时
	if($('.countdown').length){
		$('.countdown').each(function(){
		    $(this).downCount({
		        offset: +8,
		        curDate: '${sysdate }'
		    }, function () {
		        locaReload();
		    });	
		})
	}
		
	if(location.hash && location.hash.indexOf('fromPayDone') > -1){
		$('#wrapBox, #tabBox').addClass('heightp100');	
		$('.tips-box.tips-done').removeClass('dsn');
		
		setTimeout(function(){
			$('.tips-box.tips-done').hide();
			$('#wrapBox, #tabBox').removeClass('heightp100');
		},3000);
	}
	
	//重设弹出框尺寸
	resetPopBoxSize();
});

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

function PayDoneReload(){
	history.replaceState(null, '', '#fromPayDone=true');
	location.reload();
}
</script>

<script src="../js/swiper.min.js"></script>
<script>	
$(function(){
    var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        paginationClickable: false,
        spaceBetween: 0,
		loop:false
    });
    
	var swiperImgLength = $('.my-gallery figure').length;
	if(swiperImgLength <= 1){
		$('.swiper-pagination').hide();
	}
	
});
</script>
<script src="../dist/photoswipe.min.js"></script>
<script src="../dist/photoswipe-ui-default.min.js"></script>
<script src="../dist/index.js"></script>

<script src="../js/yiyuangou.js"></script>

<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1261123817'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1261123817' type='text/javascript'%3E%3C/script%3E"));</script>
</body>
</html>