<%@page import="com.cnfantasia.server.common.CommConstants"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>缓存管理</title>
		<style type="text/css">
			body{background-color: #CCCCCC;}
			.container{width: 100%; margin-top: 200px;;text-align: center; vertical-align: center;}
			.op_div{width: 340px;height: 320px;margin: auto;padding: 15px 40px 5px 40px;background-color: #999999; border: 1px solid #666666; border-radius:5px;}
			.refresh_btn{margin-right: 10px;}
			.refresh_all_btn{color: red;}
			.note{color: #FFFF00;}
			.note span{float:left;}
			p{float:left;}
		</style>
	</head>
	<body>
		<div class="container">
			<div class="op_div">
				<h2>缓存刷新</h2>
				<div class="btn_div">
					<p><button onclick="refreshAll()" class="refresh_btn refresh_all_btn">刷新所有数据</button></p>
					<p><button onclick="refreshSysParam()" class="refresh_btn">刷新参数表</button></p>
					<p><button onclick="refreshMyBatisCache()" class="refresh_btn">刷新mybatis缓存</button></p>
					<p><button onclick="refreshAllHtCarGb()" class="refresh_btn">刷新车禁小区缓存</button></p>
					<p><button onclick="refreshCarNumPrefix()" class="refresh_btn">刷新车牌前缀缓存</button></p>
				</div>
				<div class="note">
					<span>注：生产环境下1个小时内只能刷新5次！</span>
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript">
		function refreshAll(){
			refreshCache("refreshAll");
		}
		
		function refreshSysParam(){
			refreshCache("refreshSysParam");
		}
		
		function refreshMyBatisCache(){
			refreshCache("refreshMyBatisCache");
		}
		
		function refreshAllHtCarGb(){
			refreshCache("refreshAllHtCarGb");
		}
		
		function refreshCarNumPrefix(){
			refreshCache("refreshCarNumPrefix");
		}
		
		function refreshCache(urlTail){
			$.post("${pageContext.request.contextPath}/cache2/"+urlTail+".json", function(data){
				alert(data.message);
				if(data.status=="0005"){
					if(confirm("您确定要返回登陆界面吗？")){
						location = "${pageContext.request.contextPath}/cache2/loginPage.json";
					}
				}
			});
		}
	</script>
</html>