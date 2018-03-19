<%@page import="com.cnfantasia.server.api.pub.header.HeaderConstant"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

<title>解放区喜报</title>
<base href="<%=currPath%>" target="_blank">
<link rel="stylesheet" type="text/css" href="https:${apiPathNoHttp }/css/xibaomain.css?v20160817">
</head>
<body>

<section>
    <img src="https:${resourcePathNoHttp }/xibaoimages/xibao_main_03.jpg" />
    <img src="https:${resourcePathNoHttp }/xibaoimages/xibao_main_05.jpg" />
    <img src="https:${resourcePathNoHttp }/xibaoimages/xibao_main_06.jpg" />
    <img src="https:${resourcePathNoHttp }/xibaoimages/xibao_main_07.jpg" />
    <img id="activateButton" src="https:${resourcePathNoHttp }/xibaoimages/xibao_main_08.jpg" />
    <img src="https:${resourcePathNoHttp }/xibaoimages/xibao_main_09.jpg" />
</section>

<c:if test="${not empty dataList }">

	<section class="desc-text bgdarkblue">
	    <div class="f16 orange lineHeight160">本小区已有${xibaoGroupBuilding.userCount}人次享受缴费优惠<br>单笔最高减免<fmt:formatNumber type="number" value="${xibaoGroupBuilding.maxHbAmount/100}" pattern="0.00" />元，合计减免<fmt:formatNumber type="number" value="${xibaoGroupBuilding.totalHbAmount/100}" pattern="0.00" />元</div>
	    <div class="f18 white mtop10">本小区物业缴费优惠前十榜</div>
	</section>
	
	<section>
	    <div id="myOrderList" class="p010 mb20">
	        <table class="bordered grey">
	          <tr class="title">
	            <th width="18%">楼栋</th>
	            <th width="26%">解放号</th>
	            <th width="26%">缴费时间</th>
	            <th width="26%">减免额(元)</th>
	          </tr>
	          <tr class="my-order-item hide">
	            <td>1</td>
	            <td>299099</td>
	            <td>2016.7</td>
	            <td>280.00</td>
	          </tr>
	          <c:forEach var="data" items="${dataList}">
	          	<tr <c:if test="${data.isFree == true }">class="numb01"</c:if>>
		      		<td>${data.buildName}</td>
		      		<td>${data.buyerId}</td> 
		      		<td>${data.paybillDateStr}</td>
		      		<c:choose>
		      			<c:when test="${data.isFree == true }"><td<c:if test="${data.isFree == true }">  class="pos-relative"</c:if>><fmt:formatNumber type="number" value="${data.hbAmount/100}" pattern="0.00" /><img class="icon_free" src="https:${resourcePathNoHttp }/xibaoimages/free.png"/></td></c:when>
		      			<c:otherwise><td><fmt:formatNumber type="number" value="${data.hbAmount/100}" pattern="0.00" /></td></c:otherwise>
		      		</c:choose>
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

<div class="sectionBox loading grey bordertopgrey f12 hide"><img src="https:${resourcePathNoHttp }/commonimages/loading01.gif" /> 加载中…</div>
<script src="https:${resourcePathNoHttp }/commonjs/jquery-1.11.2.min.js"></script>
<script src="https:${resourcePathNoHttp }/commonjs/fastclick.js"></script>
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
		var freeIcon = '<img class="icon_free" src="https:${resourcePathNoHttp }/xibaoimages/free.png"/>';
		
		//当前页数
		curPage += 1;
		
		//分页加载订单
		(function ajaxPage(){
			$.ajax({
				type:"get",
				url:"https:${apiPathNoHttp }/microblog/getXiBaoDataList.json",
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
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1261123817'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1261123817' type='text/javascript'%3E%3C/script%3E"));</script>
</body>
</html>