<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<base href="<%=basePath%>//" target="_blank"/>
	<title>物业管理-错误页面</title>
	<link rel="stylesheet" type="text/css" href="css/common.css" />
	<link rel="stylesheet" type="text/css" href="css/style.css?v1" />
	<link rel="stylesheet" type="text/css" href="css/jquery.simple-dtpicker.css"/>
	<style type="text/css">
		html{
			height: auto;
		}
	</style>
</head>

<body>
	<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.simple-dtpicker.js"></script>
	<script type="text/javascript" src="js/jquery.common.js"></script>
	
	<script type="text/javascript" src="js/revenue/layer.js"></script>
	<script type="text/javascript" src="js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
	<script type="text/javascript">
	(function($){
		var index = parent.layer.getFrameIndex(window.name);//获取窗口索引
		//layer.style("display", "hidden");
		var msg = "${errouMsg}";
		//parent.layer.tips(msg, '#searchForm', {time: 5000});
		parent.layer.msg(msg, {shade: 0.3});
		//parent.layer.alert(msg, {closeBtn:0}, function(){});
		parent.layer.close(index);
	})(jQuery);
	</script>
</body>
</html>

