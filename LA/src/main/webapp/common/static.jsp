<%
	String path2 = request.getContextPath();
	String basePath2 = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path2;
%>
<link rel="stylesheet" type="text/css" href="<%=basePath2%>/themes/metro/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath2%>/themes/icon.css">
<script type="text/javascript" src="<%=basePath2%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath2%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath2%>/locale/easyui-lang-zh_CN.js"></script>
<style type="text/css">
	#fm {
		margin: 0;
		padding: 10px 30px;
	}
	
	.ftitle {
		font-size: 14px;
		font-weight: bold;
		padding: 5px 0;
		margin-bottom: 10px;
		border-bottom: 1px solid #ccc;
	}
	
	.fitem {
		margin-bottom: 5px;
	}
	
	.fitem label {
		display: inline-block;
		width: 80px;
	}
	
</style>