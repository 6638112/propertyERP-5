<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimal-ui">
<meta name="format-detection" content="telephone=no" />
<link rel="dns-prefetch" href="//jiefangqu.com">
<title>转盘大抽奖</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lotteryDraw.css?v1">
</head>
<body class="heightp100">
<!-- 代码 开始 -->
<img src="${pageContext.request.contextPath}/images/a01.png" id="vivo" style="display:none;" />
<img src="${pageContext.request.contextPath}/images/a02.png" id="watch" style="display:none;" />
<img src="${pageContext.request.contextPath}/images/a03.png" id="camera" style="display:none;" />
<img src="${pageContext.request.contextPath}/images/a04.png" id="wlan" style="display:none;" />
<img src="${pageContext.request.contextPath}/images/a05.png" id="bike" style="display:none;" />
<img src="${pageContext.request.contextPath}/images/a06.png" id="thank" style="display:none;" />
<section id="wrapBox" class="sectionBox bgpink relative minheight100 wp100">
	<section id="tabBox" class="tabBox relative minheight100 zindex2 nobg wp100" data-userId="${userId}">
		<div class="banner">
			<img class="top-bg" src="${pageContext.request.contextPath}/images/top_bg.jpg"/>
			<div class="turnplate" data-names="${lotteryDrawListEntity.names}" style="background-image:url(${pageContext.request.contextPath}/images/turnplate-bg.png);background-size:100% 100%;">
				<canvas class="item" id="wheelcanvas" width="422px" height="422px"></canvas>
				<c:if test="${lotteryDrawListEntity.drawStatus eq 2}">
					<img id="awardPointer" class="pointer" src="${pageContext.request.contextPath}/images/turnplate-pointer.png"/>
				</c:if>
				<c:if test="${lotteryDrawListEntity.prizeStatus eq 1}">
					<img class="pointer" src="${pageContext.request.contextPath}/images/turnplate-pointer-bingo.png"/>
				</c:if>
				<c:if test="${lotteryDrawListEntity.prizeStatus eq 3}">
					<img class="pointer" src="${pageContext.request.contextPath}/images/turnplate-pointer-done.png"/>
				</c:if>
			</div>
		</div>
		<div class="main-box relative">
			<div class="main-bg relative">
				<div class="main-con">
					<c:if test="${ not empty lotteryDrawListEntity.userRecord}">
						<div class="main-part01<c:if test="${lotteryDrawListEntity.prizeStatus eq 2}"> dsn </c:if>" data-id="${lotteryDrawListEntity.userRecord.name}">
							<img class="title-img" src="${pageContext.request.contextPath}/images/title01.png" />
							<table class="table-list01 f34 mtop_bottom45" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<%-- <td>
										<div class="w60 relative">
											<img id="awardImgRound" class="w60 relative zindex99" src="${pageContext.request.contextPath}/images/award_01.png"/>
											<img class="w60 absolute zindex2 award-bg" src="${pageContext.request.contextPath}/images/award_bg.png"/>
										</div>
									</td> --%>
									<td class="award-name" align="center">${lotteryDrawListEntity.userRecord.name}</td>
									<%-- <td align="right">${lotteryDrawListEntity.userRecord.mobile}</td> --%>
								</tr>
							</table>
						</div>
					</c:if>
						<div class="main-part01a dsn" align="center">
							<img class="title-img" src="${pageContext.request.contextPath}/images/title01.png" />
							<table class="table-list01 f34 mtop_bottom45" align="center"  border="0" cellspacing="0" cellpadding="0">
								<tr>
									<%-- <td>
										<div class="w60 relative">
											<img id="awardImgRound" class="w60 relative zindex99" src="${pageContext.request.contextPath}/images/award_01.png"/>
											<img class="w60 absolute zindex2 award-bg" src="${pageContext.request.contextPath}/images/award_bg.png"/>
										</div>
									</td> --%>
									<td class="award-name" align="center">奖品名称</td>
									<!-- <td class="award-phone" align="right">手机号码</td> -->
								</tr>
							</table>
						</div>
						<c:if test="${ not empty lotteryDrawListEntity.userRecordList}">
						<div class="main-part02">
							<img class="title-img" src="${pageContext.request.contextPath}/images/title02.png" />
							<div class="scroll-box scroll-bar">
								<table class="table-list03 f14 mtop10" border="0" cellspacing="0" cellpadding="0">
									<c:forEach items="${lotteryDrawListEntity.userRecordList}" var="userRecordList">
										<tr align="center">
											<td width="40%">
												${userRecordList.phone}
											</td>
											<td>${userRecordList.name}</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</c:if>
				</div>
				<img src="${pageContext.request.contextPath}/images/main_bg02.jpg"/>
			</div>
		</div>



		<!-- 代码 结束 -->
		<section class="sectionBox wrap-bg pop-box02 dsn">
			<div class="desc-box relative">
				<div id="closeTipsBtn"></div>
				<div class="bingo-box absolute">
					<%-- <img id="awardImg" class="wp50" src="${pageContext.request.contextPath}/images/a01.png"/> --%>
					<div class="mtop10 award-name mtop_bottom45 f16">vivo x7</div>
					<div id="awardText" class="mtop_bottom45 f16">价值2498元/台</div>
				</div>
				<div><img id="popBoxBg" src="${pageContext.request.contextPath}/images/pop_bg.png"/></div>	
			</div>
		</section>
	</section>
</section>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/layer.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/awardRotate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/award.fn.js?v6"></script>
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1261123817'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1261123817' type='text/javascript'%3E%3C/script%3E"));</script>
</body>
</html>