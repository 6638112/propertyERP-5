<%@page import="com.cnfantasia.jfq.share.constant.ShareConstant"%>
<%@page import="com.cnfantasia.jfq.share.entity.ShareActiveEntity"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.cnfantasia.server.business.pub.utils.CookieUtil"%>
<%@page import="java.util.Date"%>
<%@page import="com.cnfantasia.pub.constant.CookieConstant"%>
<%@page import="com.cnfantasia.pub.util.WeChatConfig" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<htmlang = "zh-cn">
<head>
<meta charset = "utf-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0; user-scalable=no">
<title>解放区</title>
<link href="css/main.css?v05073" rel="stylesheet" type="text/css">
<link href="css/falling.css?V1" rel="stylesheet" type="text/css">
</head>
<body>
<section class="main-box">
    <!-- <div id="fallingContainer"></div> -->
    <img class="main-box-bg" src="images/greyBar.png" />
    <section class="part01">
        <ul class="disbox section01">
            <li class="disbox-a01">
            <div class="head-img">
            	<c:choose>
            		<c:when test="${fn:contains(user.imgprofile, 'null') }">
		            	<img src="images/defaultHeadImg-new.png" /> 
            		</c:when>
            		<c:otherwise>
            			<img src="${user.imgprofile}" />
            		</c:otherwise>
            	</c:choose>            	
            </div></li>
            <li class="disbox-a02"><div class="text01">我是<span class='blue'>${user.nickName}</span>：解放区，全球首款物业费打折神器送粮票啦，我得了 <span class="red">${cash }元</span> 粮票哦~小伙伴快来沾沾喜气吧</div></li>
        </ul>
        <ul class="section02">
            <!--<li><div class="get-prize-btn get-btn"><img src="images/zhanzhanxiqi-new.png" /></div></li>
            <li><div class="get-prize-btn done-btn">活动已结束，下次尽早哦</div></li>
            <li><a class="tap-nobg" href='#erweima'><div class="get-prize-btn add-btn">关注公众号，再抽一次</div></a></li>
            <li><a class="tap-nobg" href='#erweima'><div class="get-prize-btn got-btn">已领，关注公众号查看我的消费券</div></a></li>-->
            
		     <c:if test="${fn:length(sae.shareActiveDetail)<10 }">
				<% 
				String huaidTime = (String)request.getAttribute("huaidTime");
				String shareTime = ((ShareActiveEntity)request.getAttribute("sae")).getShareTime();
				String shareDate = shareTime.substring(0, "yyyy-MM-dd".length());
				String nowDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				if(nowDate.equals(shareDate)){//链接当天有效
					if(CookieUtil.getCookieByName(request, huaidTime)!=null
							&& "1".equals(CookieUtil.getCookieByName(request, huaidTime).getValue())){ //已参与活动
						 	if(CookieUtil.getCookieByName(request, huaidTime +"_FetchStatus")!=null && "1".equals(CookieUtil.getCookieByName(request, huaidTime +"_FetchStatus").getValue())){//抽中奖了
								out.write("<li><a class='tap-nobg' href='#erweima'><div class='get-prize-btn got-btn'>已领，关注公众号查看我的消费券</div></a></li>");
							}else{%>
								<li><div class="get-prize-btn get-btn" onclick="window.open('../shareDiscount/toGet.do','_blank');"><img src="images/zhanzhanxiqi-new.png" /></div></li>
							<% }
					}else{//未参与活动 %> 
						<li><div class="get-prize-btn get-btn" onclick="window.open('../shareDiscount/toGet.do','_blank');"><img src="images/zhanzhanxiqi-new.png" /></div></li>
					<%
					}
				}else{//过期的链接
					out.write("<li><div class='get-prize-btn got-btn done-btn'>活动已结束，下次尽早哦</div></li>");
				}
				%>
			</c:if>

			<c:if test="${fn:length(sae.shareActiveDetail)>=10 }">
				<% String huaidTime = (String)request.getAttribute("huaidTime");
				if(CookieUtil.getCookieByName(request, huaidTime)!=null
							&& "1".equals(CookieUtil.getCookieByName(request, huaidTime).getValue())){ //已参与活动
						 if(CookieUtil.getCookieByName(request, huaidTime +"_FetchStatus")!=null && "1".equals(CookieUtil.getCookieByName(request, huaidTime +"_FetchStatus").getValue())){//抽中奖了
							 out.write("<li><a class='tap-nobg' href='#erweima'><div class='get-prize-btn got-btn'>已领，关注公众号查看我的消费券</div></a></li>");
						}else{
							out.write("<li><div class='get-prize-btn done-btn'>活动已结束，下次尽早哦</div></li>");
							out.write("<li><a class='tap-nobg' href='#erweima'><div class='get-prize-btn add-btn'>关注公众号，再抽一次</div></a></li>");					
						}
					}else{
						out.write("<li><div class='get-prize-btn done-btn'>活动已结束，下次尽早哦</div></li>");
						out.write("<li><a class='tap-nobg' href='#erweima'><div class='get-prize-btn add-btn'>关注公众号，再抽一次</div></a></li>");	
					}
				%>
			</c:if>
        </ul>
        <div class="bottom-bg"></div>
    </section>
</section>
<section class="foot">
	 <div class="get-record">
    	<div class="record-title"><span class="txt">看朋友们手气如何</span></div>
    	
    	<c:forEach items="${sae.shareActiveDetail }" var="sad">
	        <ul class="disbox record-list">
	        	<li class="head-img01"><img src="${sad.headpicUrl }" /></li>
	            <li class="record-txt"><span class="f14 bold">${sad.participatorName }</span> <span class="f12 grey"> ${fn:substring(sad.participateTime, 5, 16) }</span><br><span class="f12 grey">${sad.message }</span></li>
	            <c:if test="${sad.cashAmt!= null and sad.cashAmt!=0 and !(sad.cashAmt eq '')}">
	            <li class="money orange"><fmt:formatNumber pattern="#,###,###,###" value="${sad.cashAmt/100.0}"/>元</li>
	            </c:if>
	        </ul>
    	</c:forEach>
    	    	
    </div>
    <ul class="text02">
    	<li class="grey">活动说明</li>
    	<li class="t-list"><span class="red mright10">微信授权</span> 该活动需经微信授权方可参与</li>
    	<li class="t-list"><span class="red mright10">每天一砸</span> 活动每天每人可参与一次，其余机会就让给其他小伙伴们吧</li>
    	<li class="t-list"><span class="red mright10">消费券购物爽歪歪</span> 消费券可以在“解放区公众号”的超市中当现金使用，还不赶紧来！</li>
    	<li class="t-list"><span class="red mright10">关注微信公众号</span> 一天一次根本不过瘾！赶紧关注以下公众号，每天有更多惊喜哦！</li>
    </ul>
    <div id="erweima" class="text02"><img src="images/erweima3.jpg?v0615" /></div>
    <div class="foot-txt grey">深圳市前海邻里乐科技服务有限公司版权所有</div>
</section>
</body>
<!-- <script type="text/javascript" src="js/falling.js"></script> -->
<script type="text/javascript" >
	var oathor2URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
</script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?10613c5cec2e7e169835579b1c82fd77";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();

//统一微信分享风格 
var shareTitle = '<%=ShareConstant.shareTitle%>';
var shareUrl = '<%=request.getSession().getAttribute(CookieConstant.ShareUrl)%>';

wx.config({
    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: '<%=com.cnfantasia.pub.util.WeChatConfig.APPID%>', // 必填，公众号的唯一标识
    timestamp: '${signInfo.timestamp}', // 必填，生成签名的时间戳
    nonceStr: '${signInfo.nonceStr}', // 必填，生成签名的随机串
    signature: '${signInfo.signature}',// 必填，签名，见附录1
    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
});

wx.ready(function(){
    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
    
	//分享朋友圈
	wx.onMenuShareTimeline({
	    title: shareTitle, // 分享标题
	    link: shareUrl, // 分享链接
	    imgUrl: '<%=ShareConstant.shareIcon%>', // 分享图标
	    success: function () { 
	        // 用户确认分享后执行的回调函数
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    }
	});
	
	//发送给朋友
	wx.onMenuShareAppMessage({
	    title: shareTitle, // 分享标题
	    desc: '<%=ShareConstant.getShareDesc()%>', // 分享描述
	    link: shareUrl, // 分享链接
	    imgUrl: '<%=ShareConstant.shareIcon%>', // 分享图标
	    type: 'link', // 分享类型,music、video或link，不填默认为link
	    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
	    success: function () { 
	        // 用户确认分享后执行的回调函数
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    }
	});
});

</script>
</html> 