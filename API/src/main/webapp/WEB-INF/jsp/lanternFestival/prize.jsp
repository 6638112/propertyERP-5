<%@page import="com.cnfantasia.server.api.pub.header.HeaderConstant"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	String path2 = request.getContextPath();
	String basePath2 = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path2;
	String currPath = basePath2+"/signalStyle/xibaoPage/";
%>
<!DOCTYPE html>
<html lang = "zh-cn">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>

<title>闹元宵猜灯谜</title>
<base href="<%=currPath%>" target="_blank">
<link rel="stylesheet" type="text/css" href="../../css/xibaomain.css?v20160817">
</head>
<body>

<%-- <section>
    <img src="${resourcePath }/xibaoimages/xibao_main_03.jpg" />
    <img src="${resourcePath }/xibaoimages/xibao_main_05.jpg" />
    <img src="${resourcePath }/xibaoimages/xibao_main_06.jpg" />
    <img src="${resourcePath }/xibaoimages/xibao_main_07.jpg" />
    <img id="activateButton" src="${resourcePath }/xibaoimages/xibao_main_08.jpg" />
    <img src="${resourcePath }/xibaoimages/xibao_main_09.jpg" />
</section> --%>

	<section class="desc-text bgdarkblue" style="text-align: left">
	    <div class="f16 orange lineHeight160" style="    margin-left: 30px; margin-right: 30px; ">活动规则：<br>
			1、活动参与人：园景园名苑/万丰园/湾厦福园/海润楼的全体业主（住户）<br>
			2、活动内容：（1）在解放区街坊模块发送谜底，发送格式（谜语编号+谜底），以第一位发送正确谜底的参与者为获奖人 （2）在活动过程中进行抽奖环节，奖项设为一等奖、二等奖、三等奖<br>
			3、礼品：精美礼品，解放区消费券<br>
			4、礼品领取方式：实物礼品现场发放，虚拟礼品工作日发送到该解放区账户<br>
			5、活动时间：2017年2月11日15:00~18:00</div>
		<c:if test="${not empty dataList }">
	    	<div class="f18 white mtop10" style="    margin-left: 30px; margin-right: 30px; ">中奖名单如下</div>
	    </c:if>
	</section>
	
 <c:if test="${not empty dataList }">
	<section>
	    <div id="myOrderList" class="p010 mb20">
	        <table class="bordered grey">
	          <tr class="title">
	            <th width="18%">谜语编号</th>
	            <th width="26%">解放号</th>
	            <th width="26%">手机号</th>
	            <th width="26%">领奖状态</th>
	          </tr>
	          <c:forEach var="data" items="${dataList}">
	          	<tr>
		      		<td>${data.riddleNo}</td>
		      		<td>${data.userId}</td> 
		      		<td>${fn:substring(data.userPhoneNum, 0, 3) }****${fn:substring(data.userPhoneNum, 7, 11) }</td>
		      		<td> 
			      		<c:if test="${data.status == 0 }">未领</c:if> 
			      		<c:if test="${data.status == 1 }">已领</c:if> 
		      		</td>
		      	</tr>
	          </c:forEach>
	        </table>
	    </div>
	    
	    <c:if test="${lastPage == false }">
		    <ul id="btnNextPage" class="xibao-foot displaybox bggrey">
		    	<li class="boxflex01 desc-text blue">查看更多</li>
		    </ul>
	    </c:if>
	    
	</section>
</c:if> 

<div style="display: none">${xibaoGroupBuilding.gbName}</div>

<div class="sectionBox loading grey bordertopgrey f12 hide"><img src="${resourcePath }/commonimages/loading01.gif" /> 加载中…</div>
<script src="${resourcePath }/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePath }/commonjs/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
		
		//加载下一页
		$('#btnNextPage').click(function(){
			getNextPage('#myOrderList');
		});
		
		//判断安卓ios系统
		var u = navigator.userAgent, app = navigator.appVersion;
		var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
		var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
		
		//安卓
		if(isAndroid){
			//激活免单机会
			document.getElementById("activateButton").onclick = function(){
				window.activate.jumpToActivate();
			};
		}
		
		//ios
		if(isiOS){
			//激活免单机会
			document.getElementById("activateButton").onclick = function(){
				document.location='jfq://'+'jumpToActivate';
			};
		}
		
	});
	
	var curPage = 1;
	
	function getNextPage(obj){
		
		var $loading = $('.loading');
		var freeIcon = '<img class="icon_free" src="${resourcePath }/xibaoimages/free.png"/>';
		
		//当前页数
		curPage += 1;
		
		//分页加载订单
		(function ajaxPage(){
			$.ajax({
				type:"get",
				url:"../../microblog/getXiBaoDataList.json",
				data:{pageNo: curPage},
				dataType:"json",
				beforeSend:function(data){
					if($('.newLoading').length == 0){
						$loading.clone(true).addClass('newLoading').removeClass('hide').insertAfter(obj);
					}
				},
				success:function(data){
					
					//加载下一页数据
					var $myOrderListBox = $(obj);
					var $myOrderItem = $('.my-order-item.hide');
						
					$.each(data.dataValue.dataList,function(i,dataPro){
							
						var $myOrderItemClone = $myOrderItem.clone(true);
						var dataList = dataPro;
						
						//将订单数据更新至$myOrderItemClone
						$myOrderItemClone.find('td').eq(0).text(dataList.buildName);
						$myOrderItemClone.find('td').eq(1).text(dataList.buyerId);
						$myOrderItemClone.find('td').eq(2).text(dataList.paybillDateStr);
						$myOrderItemClone.find('td').eq(3).text((dataList.hbAmount/100).toFixed(2));
						
						if(dataList.isFree === true){
							$myOrderItemClone.addClass('numb01').find('td').eq(3).addClass('pos-relative').append(freeIcon);
						}
						
						//插入页面
						$myOrderItemClone.removeClass('hide').appendTo($myOrderListBox.find('table'));						
					});	
					
					if(data.dataValue.lastPage === true){
						$('.newLoading').html('已加载至最后一页');
						$('#btnNextPage').hide();
					}else{
						setTimeout(function(){
							$('.newLoading').remove();
						},400);
					}
				}
			});
		}());
	}
	
</script>

</body>
</html>