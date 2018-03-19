<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>商品详情</title>
<link rel="stylesheet" href="../css/common.css" type="text/css">
</head>

<body>
<div class="sectionBox t-center lineheight0">
    <img class="no-item-pic" src="../images/noitem.png" />
</div>
<script src="../js/jquery-1.11.2.min.js"></script>
<script src="../js/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
	})
	
	function resetConSize(obj){
		//重置内容宽高
		var windowHeight = $(window).height();
		var windowWidth = $(window).width();
		var imgWidth;
		imgWidth = 640*windowHeight/1136;
		$(obj).css({'width':imgWidth});
	}
	resetConSize('.no-item-pic');
</script>

</body>
</html>