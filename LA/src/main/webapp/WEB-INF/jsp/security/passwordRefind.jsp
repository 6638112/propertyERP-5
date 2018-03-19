<%@page import="com.cnfantasia.server.ms.pub.session.UserContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>密码找回</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/themes/metro/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/themes/icon.css">
    
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/jquery.simple-dtpicker.css">

<script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.easyui.min.js"></script>
 <script type="text/javascript" src="<%=basePath%>/js/JQuery.MenuTree.js"></script>
 <script type="text/javascript" src="<%=basePath%>/js/jquery.common.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.simple-dtpicker.js"></script>

	<script>
	function addTab(title, url){
		if ($('#tt').tabs('exists', title)){
			$('#tt').tabs('select', title);
		} else {
			var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
			$('#tt').tabs('add',{
				title:title,
				content:content,
				
				//option:{height:100%},
				
				closable:true
			});
		}
	}
	
	function exitsystem(){
		if (confirm('您确定要退出系统吗？')){
	  		window.parent.location="<%=basePath%>/login/doLogout.jsp";
		}
	}

	</script>
	
	
</head>
            
 <body >
 <div class="header">
	<div class="left">花样年<span> 有力，有戏，有价值!</span></div>
    <div class="right">欢迎来到解放区运营管理系统！ <input type="button" value="退出" onclick="exitsystem();"/></div>
</div>
	

	<div id="menu3" class="menuTree">
        <ul class="menu-all">
        	<%
       		if(UserContext.getUser()!=null&&UserContext.getUser().getOmsUser()!=null&&UserContext.getUser().getOmsUser().getUserAccount()!=null){
       			if(UserContext.getUser().getOmsUser().getUserAccount().equals("admin")){%>
         		<li class="parent collapsed"><a class="menu-01" href="#" >物业模块</a>
                  <ul class="collapsed">
                      <li class="parent collapsed"><a href="#" >物业公告管理</a>
                          <ul>
                              <li class="child"><a  id="entryGoods" href="javascript:void(0)" onclick="addTab('物业公告管理','<%=basePath%>/notice/index.jsp');">物业公告管理</a></li>
                          </ul>
                      </li>
                  </ul>
              </li>
           <%}}%>
            
            <li class="parent collapsed"><a class="menu-01" href="#">电商模块</a>
                <%-- <ul class="collapsed">
                    <li><a href="javascript:void(0)" onclick="addTab('订单管理','<%=basePath%>/order/index.jsp')">订单管理</a></li>
                </ul> --%>
                <ul>
                    <li class="child"><a  id="entryGoods" href="javascript:void(0)" onclick="addTab('订单管理','<%=basePath%>/order/index.jsp');">订单管理</a></li>
                </ul>
            </li>
        </ul>
	</div>
	<div >
	
	</div>
	
	<!-- 
	<div region="east" split="false" border="false" title="日历" style="width:250px;">
   		<div class="easyui-calendar" style="width:250px;height:250px;"></div>
	</div>
	-->
	<!-- 
	<div region="south" border="false" style="height:30px;" align="center">版权所有 © 2014 by 解放区，保留所有权利</div>
	 -->
	<div region="center" border="false" title="" style="border-left:0px;border-right:0px;">
		<div id="tt" class="easyui-tabs" style="width:400px;*height:885px;min-height:885px;" fit="true">
				<div title="Home" style="padding:20px">
					<p style="font-size:30px;text-align:center; margin:40px 0 0 -60px;">欢迎使用解放区运营管理系统</p>
				</div>
		</div>
	</div>
</body>


</html>