<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>服务商品管理详情</title>
		<link rel="stylesheet" type="text/css" href="../css/common.css">
		<link rel="stylesheet" type="text/css" href="../css/style.css">
		<link rel="stylesheet" type="text/css" href="../css/picbox.css">
		<style>
			.dpTd1{width: 120px; text-align: left;}
			.dpTd2{width: 240px; text-align: left;}
			.dpTd3{width: 800px; min-width: 800px;text-align: left;}
			.pcTd1{width: 60px; text-align: left;vertical-align:center;}
			.pcTd2{text-align: left;}
			.overflowDiv{max-height: 346px; overflow-y: scroll;}
			hr{height:2px;border:none;border-top:2px dotted #185598;margin-top: 10px;}
			.title{font-weight: bold;}
			.red{color:red;}
			#cityTable tbody, #gbTable tbody, #areaHasSelectedList tbody{  
				display:block;  
				max-height:346px;  
				overflow-y:scroll; 
				overflow-x:hidden; 
			}  
			
			#cityTable thead, #cityTable tr, #gbTable thead, #gbTable tr, #areaHasSelectedList thead, #areaHasSelectedList tr {  
				display:table;  
				width:100%;  
				table-layout:fixed;  
				overflow-x:hidden;
			}  
			  
			#cityTable thead, #gbTable thead, #areaHasSelectedList thead {  
				/*Standard */
				width: calc( 100% - 8px );
				/*chrome safari*/
				width: -webkit-calc( 100% - 8px );
				/*Firefox*/
				width: -moz-calc( 100% - 8px );				
			}
		</style>
	</head>
	<body>
		<div class="info">
		    <h2>服务商品管理详情</h2> 
		    <form id="saveForm" method="post" action="">
		    	<input type="hidden" name="id" value="${id}"/>
			    <table class="info-list-02" style="width: 440px;min-width: 440px;margin-top: 20px;">
					<tr>
						<td class="dpTd1 title"><span class="red">*</span>商品名称：</td>
						<td class="dpTd2">${dp.name}</td>
					</tr>
					<tr>
						<td class="dpTd1 title">商品描述：</td>
						<td class="dpTd2">${dp.desc}</td>
					</tr>
				</table>
				<hr>
				<h2 class="mtop20">产品所属类目</h2> 
				<table class="info-list-02" style="width: 440px;min-width: 440px;">
					<tr>
						<td class="dpTd1">选择商品所属大类：</td>
						<td class="dpTd2">${dp.cstName }</td>
					</tr>
					<tr>
						<td class="dpTd1">选择所属需求小类：</td>
						<td class="dpTd2">${dp.dtName }</td>
					</tr>
					<tr>
						<td class="dpTd1">选择产品大类：</td>
						<td class="dpTd2">${dp.dt2Name }</td>
					</tr>
				</table>
		        <hr>
		        <table class="info-list-02">
					<tr>
						<td class="dpTd1 title"><span class="red">*</span>选择供应商：</td>
						<td class="dpTd3">${dp.ssName }</td>
					</tr>
					<tr>
						<td class="dpTd1 title"><span class="red">*</span>付款方式：</td>
						<td class="dpTd3">
							<c:choose>
								<c:when test="${dp.payType==1 }">服务前付款</c:when>
								<c:when test="${dp.payType==2 }">服务后付款</c:when>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td class="dpTd1 title">销售范围：</td>
						<td class="dpTd3">
							<c:forEach items="${dpSellRangeList}" var="dpSellRange">
								<li>${dpSellRange.acName } ${dpSellRange.abName } ${dpSellRange.gbName } </li>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td class="dpTd1 title" style="vertical-align: top;">选择规格：</td>
						<td class="dpTd3">
							<table class="info-list-02">
								<thead>
									<tr>
										<td>规格</td>
										<td>销售价</td>
										<td>市场价</td>
										<td>计价单位</td>
									</tr>
								</thead>
								<tbody id="noBody">
									<c:forEach items="${dp.prdtSpecList }" var="item"> 
										<tr>
											<td>${item.specification }</td>
											<td>${item.sellPrice/100 }</td>
											<td>${item.marketPrice/100 }</td>
											<td>${item.priceUnit }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</td>
					</tr>
				</table>
		        <hr>
		        <h2 class="mtop20">商品图片</h2>
		        <table class="info-list-02">
					<tr>
						<td class="pcTd2">
			               <ul class="menu-img">
			            		<c:forEach items="${dp.prdtPics }" var="pic">
			           				<li><a href="${pic }" rel="lightbox-group"><img src="${pic }" border="0" /></a></li>
			            		</c:forEach>
		          			</ul>
						</td>
					</tr>
				</table>
		        <hr>
		        <h2 class="mtop20">商品介绍图片</h2>
		        <table class="info-list-02">
					<tr>
						<td id="dragIntroduceItems">
							 <ul class="menu-img">
			            		<c:forEach items="${dp.introducePics }" var="pic">
			           				<li><a href="${pic }" rel="lightbox-group"><img src="${pic }" border="0" /></a></li>
			            		</c:forEach>
		          			</ul>
						</td>
					</tr>
				</table>
		        
		        <hr>
		        <h2 class="mtop20">微信分享</h2>
		        <table class="info-list-02">
					<tr>
						<td class="dpTd1 title"><span class="red">*</span>分享图片：</td>
						<td class="dpTd3">
							<ul class="menu-img">
			           				<li><a href="${dp.sharePicUrl }" rel="lightbox-group"><img src="${dp.sharePicUrl }" border="0" /></a></li>
		          			</ul>
						</td>
					</tr>
					<tr>
						<td class="dpTd1 title"><span class="red">*</span>好友标题：</td>
						<td class="dpTd3">
							${dp.shareFriendTitle }
						</td>
					</tr>
					<tr>
						<td class="dpTd1 title"><span class="red">*</span>朋友圈标题：</td>
						<td class="dpTd3">
							${dp.shareCycleTitle }
						</td>
					</tr>
					<tr>
						<td class="dpTd1 title"><span class="red">*</span>推送图片：</td>
						<td class="dpTd3">
							<ul class="menu-img">
			           			<li><a href="${dp.sharePushPicUrl }" rel="lightbox-group"><img src="${dp.sharePushPicUrl }" border="0" /></a></li>
		          			</ul>
						</td>
					</tr>
					<tr>
						<td class="dpTd1 title"><span class="red">*</span>分享内容：</td>
						<td class="dpTd3">
							${dp.shareContent }
						</td>
					</tr>
				</table>
		    </form>
		</div>
	</body>
	<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="../js/picbox.js"></script>
	<script type="text/javascript" src="../js/jquery.common.js"></script>
</html>