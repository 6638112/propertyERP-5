<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>参与记录</title>
<link rel="stylesheet" href="../css/yiyuangou.css" type="text/css">
</head>

<body class="bggrey heightp100">
	<section id="wrapBox" class="sectionBox bggrey pos-relative minheight100">
		<section id="tabBox" class="tabBox pos-relative minheight100 zindex10">
			<div class="sectionBox list-onsell bggrey">
				<c:if test="${fn:length(records) == 0 }">
					<div class="mtop20 t-center f18 lineheight36">暂无记录</div>
				</c:if>
				<c:forEach items="${records }" var="list">
					<div class="displaybox m10 p5 borderradius4 bgwhite">
						<a class="item-img-thumb mright15" href="activityDetail.html?activityId=${list.activityId }">
							<img src="${list.activityPicList[0] }" />
						</a>
						<div class="boxflex01">
							<div class="lineheight140 text-clamp f18">${list.activityTitle }</div>
							<div class="f12 grey mtop5">${list.buyCount } 人已参与</div>
							<c:if test="${list.isSettle == 1 }">
								<c:if test="${list.buyRecord.recordStatus != 4 }">
									<div class="f12 red mtop5 falseBuyBtn">1元钱已转入粮票账户，查看></div>
								</c:if>
								<ul class="displaybox mtop20">
									<li class="f14 awards-list-btn">成功购买名单</li>
									<li class="boxflex01 f14">
										<c:choose> 
											<c:when test="${list.buyRecord.recordStatus == 4 }">
												<div class="right mright5 isBinggo on">成功购买</div>
											</c:when>
											<c:otherwise>
												<div class="right mright5 isBinggo">继续努力</div>
											</c:otherwise>
										</c:choose>
									</li>
								</ul>
								
								<ul class="awards-list dsn">
									<c:forEach items="${list.winRecords }" var="phoneList" varStatus="stat">
										<c:if test="${stat.index%2==0}">
											<li class="displaybox">
												<div class="boxflex01<c:if test="${phoneList.recordStatus==4 && phoneList.tUserFId == list.buyRecord.tUserFId}"> red</c:if>">
												${fn:substring(phoneList.userMobile, 0, 3)}****${fn:substring(phoneList.userMobile, 7, fn:length(phoneList.userMobile))}
												</div>
										</c:if>
										<c:if test="${stat.index%2!=0}">
												<div class="boxflex01 t-right<c:if test="${phoneList.recordStatus==4 && phoneList.tUserFId == list.buyRecord.tUserFId}"> red</c:if>">
														${fn:substring(phoneList.userMobile, 0, 3)}****${fn:substring(phoneList.userMobile, 7, fn:length(phoneList.userMobile))}
												</div>
											</li>
										</c:if>
									</c:forEach>
									<c:if test="${fn:length(list.winRecords) % 2 == 1}">
										</li>
									</c:if>
								</ul>
							</c:if>
							
							<c:if test="${list.isSettle == 0 }">
								<c:if test="${list.activityStatus == 1}">
								<ul class="displaybox mtop20">
									<li class="boxflex01 f14">
										<div class="right mright5 isBinggo bgorange">进行中</div>
									</li>
								</ul>
								</c:if>
								<c:if test="${list.activityStatus == 3}">
									<ul class="displaybox mtop20">
										<li class="boxflex01 f14">
											<div class="right mright5 isBinggo bgorange">结果公布中</div>
										</li>
									</ul>
								</c:if>
							</c:if>
						</div>
					</div>
				</c:forEach>
				
			</div>
	
			<section class="sectionBox wrap-bg pop-box02 dsn">
				<div class="tips-box pos-relative">
					<div id="adwardListCloseBtn"><img src="../images/close_icon.png"/></div>
					<div class="t-center ptb10 pos-relative">
						<div class="adward-title"><img class="wp80" src="../images/adward_title.png"/></div>
					</div>
					<div class="grey t-center m010">恭喜以下用户，请留意短信内容或咨询物业管理处领取货品哦。</div>
					<ul class="awards-list mtop15">
					</ul>	
				</div>
			</section>
		</section>
	</section>

<script src="../js/jquery-1.11.2.min.js"></script>
<script src="../js/fastclick.js"></script>
<script>
$(function(){
	new FastClick(document.body);

	//查看成功购买名单
	$('.awards-list-btn').click(function(){
		var thisListHtml = $(this).parent('ul').siblings('.awards-list').html();
		$('.pop-box02').find('.awards-list').html(thisListHtml);
		
		$('#wrapBox, #tabBox').addClass('heightp100');
		$('.pop-box02').removeClass('dsn');
	});
	//关闭成功购买名单
	$('#adwardListCloseBtn').click(function(){
		$('#wrapBox, #tabBox').removeClass('heightp100');
		$('.pop-box02').addClass('dsn');
	});
	
	//判断安卓ios系统
	var u = navigator.userAgent, app = navigator.appVersion;
	var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	
	//安卓
	if(isAndroid){
		//跳转到代扣卡
		$(".falseBuyBtn").click(function(){
			window.itemSelect.jumpToPropertyCard();
		});
	}
	
	//ios
	if(isiOS){
		setupWebViewJavascriptBridge(function(bridge) {
			//跳转到代扣卡
			$(".falseBuyBtn").click(function(){
				bridge.callHandler('jumpToPropertyCard', null, function(response) {});
			});
		});
	}
	
});
	
function setupWebViewJavascriptBridge(callback) {
    if (window.WebViewJavascriptBridge) { return callback(WebViewJavascriptBridge); }
    if (window.WVJBCallbacks) { return window.WVJBCallbacks.push(callback); }
    window.WVJBCallbacks = [callback];
    var WVJBIframe = document.createElement('iframe');
    WVJBIframe.style.display = 'none';
    WVJBIframe.src = 'wvjbscheme://__BRIDGE_LOADED__';
    document.documentElement.appendChild(WVJBIframe);
    setTimeout(function() { document.documentElement.removeChild(WVJBIframe) }, 0);
}
</script>
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1261123817'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1261123817' type='text/javascript'%3E%3C/script%3E"));</script>
</body>
</html>