<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset = "utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<title>${sa.name }</title>
<link rel="stylesheet" type="text/css" href="../css/act.common.css">
</head>
<body class="posrelative">
<section class="main-box">
	<c:forEach items="${sa.sadList }" var="sad" varStatus="status">
		<c:if test="${sad.jumpType == 0}">
			<div>
				<img class="bgloading" src="../images/pixel.gif" data-original="${sad.picUrl }" />
			</div>
		</c:if>
		
		<c:if test="${sad.jumpType == 1}">
			<div class="buynow-btn" data-id="${paramList[status.index].id}" data-name="${paramList[status.index].name}">
			<img class="bgloading" src="../images/pixel.gif" data-original="${sad.picUrl }" />
			</div>
		</c:if>
		
		<c:if test="${sad.jumpType == 2}">
			<div class="booknow-btn" data-id="${paramList[status.index].id}" data-name="${paramList[status.index].name}">
			<img class="bgloading" src="../images/pixel.gif" data-original="${sad.picUrl }" />
			</div>
		</c:if>
		
		<c:if test="${sad.jumpType == 3}">
			<div class="toh5page-btn" data-href="${sad.jumpParam}">
			<img class="bgloading" src="../images/pixel.gif" data-original="${sad.picUrl }" />
			</div>
		</c:if>
		
	</c:forEach>
	<div class="toh5page-btn hide" data-href="https://resource.linlile.com.cn/docs/act_feediscount/index.htm"><img src="../images/mothersday/pixel.gif" data-original="../images/mothersday/main_22.jpg" /></div>
</section>

<script src="../js/jquery-1.11.2.min.js"></script>
<script src="../js/jquery.lazyload.js"></script>
<script src="../js/act.common.js?20170717a"></script>
<script type="text/javascript">
	var cnzz_s_tag = document.createElement('script');
	cnzz_s_tag.type = 'text/javascript';
	cnzz_s_tag.async = true;
	cnzz_s_tag.charset = 'utf-8';
	cnzz_s_tag.src = 'https://s11.cnzz.com/z_stat.php?id=1261123817&async=1';
	var root_s = document.getElementsByTagName('script')[0];
	setTimeout(function(){
		root_s.parentNode.insertBefore(cnzz_s_tag, root_s);
	},4000);
</script>
</body>
</html> 