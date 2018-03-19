
<%@page import="com.cnfantasia.server.ms.pub.session.UserContext"%>
<%@page import="java.util.List"%>
<%@page import="com.cnfantasia.server.msbase.omsPermiFunction.entity.OmsPermiFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>解放区运营管理系统</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/jquery.simple-dtpicker.css">
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.2.min.js"></script>
</head>

<body style=" min-width:1024px; overflow-x:scroll; overflow-y:hidden; position:relative;">
<div class="header">
	<c:if test="${not empty welcomeMsg }">
		<div class="left">${welcomeMsg }</div>
	</c:if>
	<c:if test="${empty welcomeMsg }">
		<div class="left">花样年<span> 有力，有戏，有价值! </span></div>
	</c:if>
    <div class="right">欢迎来到解放区运营管理系统！ <a href="#" onclick="exitsystem();">退出</a></div>
</div>

<div class="main">
	<div id="menu3" class="menuTree">
        <ul class="menu-all">
        	<%
        	List<OmsPermiFunction> permiFunctionList = (List<OmsPermiFunction> )request.getAttribute("permiFunctionList");
        	int preNodeLevel= 0 ; //上一个节点级次
        	for(int i = 0; i < permiFunctionList.size(); i++){
        		
        		OmsPermiFunction omsPermiFunction = permiFunctionList.get(i);
        		
        		if(omsPermiFunction.getLevel()<preNodeLevel){//根据level来输出上个非明细标签收尾
    				out.write("\t                </ul>\r\n");
    	            out.write("\t            </li>\r\n");
    			}
        		
        		if(omsPermiFunction.getIsleaf()!=1){//非明细结点
        			out.write("\t            <li class=\"parent collapsed\"><a class=\"menu-01\" href=\"#\">");
        			out.write(omsPermiFunction.getName());
					out.write("</a>\r\n");
        			out.write("\t                <ul class=\"collapsed\" id=\"menuShow\">\r\n");
        		}else{
        			out.write("\t                    <li class=\"child\"><a href='"+ basePath+omsPermiFunction.getAction()+"\'>"+omsPermiFunction.getName()+"</a></li>\r\n");
        		}
        		preNodeLevel = omsPermiFunction.getLevel();
        	}
   	      	out.write("\t                </ul>\r\n");
       	    out.write("\t            </li>\r\n");
        	%>
        	
        	<li class="parent collapsed"><a class="menu-01" href="#">账号设置</a>
                <ul class="collapsed">
                    <li class="child"><a href="../security/passwordModify.jsp">修改密码</a></li>
                </ul>
            </li>        	
        </ul>
	</div>
	<div class="main-right">
        <div class="menu-arrow"></div>
        <div class="main-con"><iframe src="<%=basePath%>/security/welcome.jsp" name="mainFrame" id="mainFrame" frameborder="no" scrolling="no" ></iframe>
			<div class="footer">
	            <p>粤ICP备14059299号<br />Copyright <span>&copy;</span> 2014 深圳前海邻里乐科技服务有限公司 All rights reserved.</p>
	        </div>
        </div>
		<script type="text/javascript">
				/*var isOnLoad = true; 
				$("#mainFrame").load(function(){
						isOnLoad = false;
						var mainheight = $(this).contents().find('.info').height() + 60;
						$(this).height(mainheight < 500 ? 500 : mainheight);
				});*/ 
				
				var oFrm = document.getElementById("mainFrame");
				oFrm.onload = oFrm.onreadystatechange = function() {
					 if (this.readyState && this.readyState != 'complete') return;
					 else {
						var isOnLoad = true; 
						$("#mainFrame").load(function(){
								isOnLoad = false;
								var mainheight = $(this).contents().find('.info').height() + 60;
								$(this).height(mainheight < 500 ? 500 : mainheight);
						});
					 }
				}
            </script>
    </div>
</div>

</body>
<script type="text/javascript" src="<%=basePath%>/js/JQuery.MenuTree.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.common.js"></script>
<script type="text/javascript">

$('.menuTree').find('a').each(function(){
	var sHref = $(this).attr('href');
	$(this).click(function(){
		if( sHref != '#' ){
			$('#mainFrame').attr('src',sHref);
		}
		return false;
	});
}); 

function exitsystem(){
	if (confirm('您确定要退出系统吗？')){
  		window.parent.location="<%=basePath%>/login/doLogout.jsp";
	}
}

</script>
<!--[if IE 6]>
<script type="text/javascript">
   document.getElementById("menu3").style.height=document.documentElement.clientHeight-72+"px";
</script>
<script type="text/javascript" src="js/DD_belatedPNG.js"></script>
<script type="text/javascript">
DD_belatedPNG.fix('.parent,.expanded,.points img');
</script>
<![endif]-->
</html>
