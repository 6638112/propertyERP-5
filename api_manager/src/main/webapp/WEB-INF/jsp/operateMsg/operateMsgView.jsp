<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>运营通知查看</title>
		<link rel="stylesheet" type="text/css" href="../css/common.css">
		<link rel="stylesheet" type="text/css" href="../css/style.css">
	</head>
	<body>
		<div class="info">
		    <h2>新增运营通知</h2> 
			    <table class="info-list-02" style="min-width: 440px;margin-top: 20px;">
					<tr>
						<td class="dpTd1 title" width="120">发送对象：</td>
						<td class="dpTd2">
							<c:choose>
								<c:when test="${m.sendRange == 1 }">全国</c:when>
								<c:when test="${m.sendRange == 4 }">城市/区县</c:when>
								<c:when test="${m.sendRange == 3 }">小区</c:when>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td class="dpTd1 title">信息类型：</td>
						<td class="dpTd2">
							<c:choose>
								<c:when test="${m.msgType == 1 }">短信</c:when>
								<c:when test="${m.msgType == 2 }">push</c:when>
							</c:choose>
						</td>
					</tr>
					<c:if test="${m.msgType == 2 }">
						<tr>
							<td class="dpTd1 title">跳转目标：</td>
							<td class="dpTd2">
								<c:forEach items="${oprtConfigList}" var="item">
									<c:if test="${item.number == m.jumpTarget}">${item.jumpDescription }</c:if>
								</c:forEach>
							</td>
						</tr>
					</c:if>
					<tr>
						<td class="dpTd1 title">商品详情参数：</td>
						<td class="dpTd2">
							${m.ebuyProductDetail }
						</td>
					</tr>
					<c:if test="${m.msgType == 1 }">
						<tr>
							<td class="dpTd1 title">短信黑名单：</td>
							<td class="dpTd2">
								${m.blackList }
							</td>
						</tr>
						<tr>
							<td class="dpTd1 title">短信白名单：</td>
							<td class="dpTd2">
								${m.whiteList }
							</td>
						</tr>
					</c:if>
					
					<tr>
						<td class="dpTd1 title">发送时间：</td>
						<td class="dpTd2">
							${m.sendTime }
						</td>
					</tr>
					<tr>
						<td class="dpTd1 title">标题：</td>
						<td class="dpTd2">
							${m.title }
						</td>
					</tr>
					<c:if test="${m.msgType == 1 }">
						<tr>
							<td class="dpTd1 title">短链接：</td>
							<td class="dpTd2">
								${m.shortUrl }
							</td>
						</tr>
					</c:if>
					<tr>
						<td class="dpTd1 title">内容：</td>
						<td class="dpTd2">
							${m.content }
						</td>
					</tr>
					<tr>
						<td class="dpTd1 title">发送范围：</td>
						<td class="dpTd2">
							<c:forEach items="${sendRangeList}" var="sendRange">
								<li>${sendRange.acName } ${sendRange.abName } ${sendRange.gbName } </li>
							</c:forEach>
						</td>
					</tr>
				</table>
		</div>
	</body>
</html>