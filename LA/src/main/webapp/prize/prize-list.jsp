<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang = "zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="description" content="">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimal-ui">
<meta name="format-detection" content="telephone=no, email=no">

<title>我的消费券</title>
<link rel="stylesheet" href="css/prize.common.css?v0819">
</head>

<body class="bggrey">
<section class="sectionBox prize-list">
    <div class="get-record">
    	<c:forEach var="gift" items="${giftList.list}" varStatus="status"> 
	        <a target="_self"  class="record-list-bg <c:if test="${gift.useStatus!=1 }">out-of-date</c:if>" 
	        	<c:if test="${gift.useStatus!=1 }">href="#"</c:if>
	        	<c:if test="${gift.useStatus==1 }"><c:choose>
	        		<c:when test="${gift.type==1 || gift.type==2 }">href="../prize/amazing-prize_jfq.jsp?hbAmount=${gift.hbAmount }&expiryTimeLong=${gift.expiryTimeLong }"</c:when>
	        		<c:when test="${gift.type==3 }">href="../prize/amazing-prize_yibao.jsp"</c:when>
	        		<c:when test="${gift.type==4 }">href="../prize/amazing-prize_58.jsp"</c:when>
	        		<c:when test="${gift.type==5 }">href="../prize/amazing-prize_film.jsp?hbAmount=${gift.hbAmount }&exchCode=${gift.exchCode }&expiryTimeLong=${gift.expiryTimeLong }"</c:when>
	        		<c:when test="${gift.type==6 }">href="../prize/amazing-prize_zuche.jsp?hbAmount=${gift.hbAmount }&exchCode=${gift.exchCode }&expiryTimeLong=${gift.expiryTimeLong }"</c:when>
	        		<c:otherwise>href="../prize/prizeDetail.do?detailId=${gift.id}"</c:otherwise>
	        	</c:choose></c:if>
	        >
	            <ul class="disbox record-list" >
	                <li class="head-img01">
                	<c:choose>
	        		<%-- <c:when test="${gift.type==1 || gift.type==2 }"><img src="../images/logo-jfq.png" /></c:when> --%>
	        		<c:when test="${gift.type==3 }"><img src="../images/logo-yibao.png" /></c:when>
	        		<c:when test="${gift.type==4 }"><img src="../images/logo-58dj.png" /></c:when>
	        		<c:when test="${gift.type==5 }"><img src="../images/logo-film.png" /></c:when>
	        		<c:when test="${gift.type==6 }"><img src="../images/logo-zuche.png" /></c:when>
	        		<c:otherwise><img src="${gift.iconUrl}" /></c:otherwise>
	        		</c:choose>
	                </li>
	                <li class="record-txt"><span class="f16">${gift.name}</span><br>
	                <span class="f12">
	                	<c:if test="${gift.useStatus==1 }">
		                	有效期至：
		                	<c:set var="expiryTimeLong" value="${gift.expiryTimeLong }" />
		                    <%
		                    	String expiryTimeLong = pageContext.getAttribute("expiryTimeLong").toString();
								Date expiryTime = new java.util.Date(Long.parseLong(expiryTimeLong));
								out.write(new SimpleDateFormat("yyyy-MM-dd").format(expiryTime)); 
							%>
						</c:if>
	                	<c:if test="${gift.useStatus==2 }">已使用</c:if>
	                	<c:if test="${gift.useStatus==3 }">已过期</c:if>
	                </span>
	                </li>
	                <li id="opq" <c:choose><c:when test="${gift.type==5}">class="money bold color-red"</c:when>
		                <c:when test="${gift.type==6}">class="money bold color-green"</c:when>
		                <c:otherwise>class="money bold"</c:otherwise></c:choose>
	                 >
	                <c:choose><c:when test="${empty gift.showCount}">
	                	<c:if test="${gift.hbAmount!= null and gift.hbAmount!=0 and !(gift.hbAmount eq '')}">￥${fn:substring(gift.hbAmount,0,fn:indexOf(gift.hbAmount,"."))}</c:if>
	                </c:when>
	                <c:otherwise>${gift.showCount}</c:otherwise></c:choose>
	            </ul>
	        </a>
		</c:forEach>
    </div>
</section>

<div class="sectionBox loading grey hide"><img src="images/loading01.gif" /> 加载中…</div>

<script src="js/fastclick.js"></script>
<script>
$(function(){
	new FastClick(document.body);
});


var num = 15;//每页显示的个数
var n = 0;
var m = -num;
var page = 1;
var pageStatus = 1;
var pageHasNext = true;
var $loading = $('.loading');
//分页加载订单
function ajax(pageType){
	$.ajax({
		type:"get",
		url:"list.do",
		data:{page:page, pageNum:num, status:pageStatus},
		dataType:"json",
		beforeSend:function(data){
			if($('.newLoading').length == 0){
				$loading.clone(true).addClass('newLoading').removeClass('hide').appendTo($('.prize-list'));
			}
		},
		success:function(data){
			if( pageType=="next"){ //下一页
				n += num;
				m += num;
			}

			var $prizeListBox = $('.prize-list');
			//最后一页提示信息
			if(pageHasNext === false){
				$('.newLoading').html('已更新至最后一页！');
				return false;
			}

			//无消费券提示信息
			if(data.dataValue.list == ''){
				var noItemTips = '<div id="noItemTips" class="list-box t-center">暂无消费券</div>';
				$prizeListBox.html(noItemTips);
			}else{
		
				//加载消费券...
				
				
				//当前页加载完毕，下一页页数+1
				page += 1;
			}
			//400毫秒后移除loading提示
			setTimeout(function(){
				$('.newLoading').remove();
			},400);

			//最后一页，设置data-next为false
			if(data.dataValue.hasNext === false){
				pageHasNext = false;
			}
		}
	});
};


//滚动到底部，加载下一页
$(window).scroll(function(event){
	var scrollt = document.documentElement.scrollTop + document.body.scrollTop; //获取滚动后的高度
	var docHeight = $(document).height() - $(window).height()-1; //当前文档高度
	if(scrollt > docHeight){
	 	ajax("next");
	}
});

//初始化，加载第一页
$(function(){ 
	ajax("next");
});

</script>
<script src="js/prize.common.js"></script>
</body>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?10613c5cec2e7e169835579b1c82fd77";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
</html>