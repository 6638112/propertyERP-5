
<%@page import="com.cnfantasia.server.ms.pub.session.UserContext"%>
<%@page import="java.util.List"%>
<%@page import="com.cnfantasia.server.domainbase.omsPermiFunction.entity.OmsPermiFunction"%>
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
<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no" />
<meta http-equiv="Content-Type" content="text/html,charset=utf-8" />
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>解放区运营管理系统</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/perfect-scrollbar.css">
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.2.min.js"></script>
</head>

<body style="/*  min-width:1024px; overflow-x:scroll; */ overflow-y:hidden; position:relative;">
<div class="header">
	<c:if test="${not empty welcomeMsg }">
		<div class="left">${welcomeMsg }</div>
	</c:if>
	<c:if test="${empty welcomeMsg }">
		<div class="left jfqName">解放区<span> <span class="phoneViewTextHide">解放后，一切都会有!</span> </span></div>
	</c:if>
    <div class="right"><span class="phoneViewTextHide">欢迎 <%=UserContext.getCurrUser().getUserAccount() %> 来到解放区运营管理系统！</span> <a target="mainFrame" href="../security/passwordModify.html">修改密码</a> &nbsp; <a href="#" onclick="exitsystem();">退出</a></div>
</div>

<div class="main">
	<div class="top-line01"></div>
	<div id="menu3" class="menuTree">
		
		

        <ul class="menu-all">
        
        <%-- <li class="parent on expanded"><a class="menu-01 on" href="#">物业费收益</a>
		   <ul style="display: block;" class="collapsed" id="menuShow">
		       <li class="child"><a href="<%=basePath%>/payBill/searchRevenueAdmin.html">物业费明细(管理员)</a></li>
		       <li class="child"><a href="<%=basePath%>/payBill/searchRevenueFinance.html?isPay=1&paymentWay=1">物业费明细(财务)</a></li>
		       <li class="child"><a href="<%=basePath%>/payBill/searchRevenueCompany.html?isPay=1&paymentWay=1">物业费明细(物业公司)</a></li>
		       <li class="child"><a href="<%=basePath%>/revenueAmount/toMarkPage.html?companyId=1">标记结算</a></li>
		   </ul>
		</li> --%>
        <%-- <li class="parent on expanded"><a class="menu-01 on" href="#">轻应用活动运营</a>
        	<ul style="display: block;" class="collapsed" id="menuShow">
				<li class="parent on expanded"><a class="menu-01 on" href="#">抽奖活动管理</a>
				   <ul style="display: block;" class="collapsed" id="menuShow">
				       <li class="child"><a href="<%=basePath%>/prizeActivity/activityList.html">活动列表</a></li>
				       <li class="child"><a href="<%=basePath%>/prizeActivity/activityAdd.html">新增活动</a></li>
				   </ul>
				</li>
				<li class="parent on expanded"><a class="menu-01 on" href="#">抽奖奖项管理</a>
				   <ul style="display: block;" class="collapsed" id="menuShow">
				       <li class="child"><a href="<%=basePath%>/prizeActivity/optionList.html">奖项列表</a></li>
				       <li class="child"><a href="<%=basePath%>/prizeActivity/optionAdd.html">新增奖项</a></li>
				   </ul>
				</li>
			</ul>
        </li> --%>
		<%-- <li class="parent on expanded"><a class="menu-01 on" href="#">收益中心配置</a>
        	<ul style="display: block;" class="collapsed" id="menuShow">
				<li class="child"><a href="<%=basePath%>/revenueConfig/previewList.html">概要列表</a></li>
				<li class="child"><a href="<%=basePath%>/revenueAmount/previewListAdmin.html">收益总额列表(管理员)</a></li>
				<li class="child"><a href="<%=basePath%>/revenueAmount/previewListPropertyAgent.html">收益总额列表(物业公司)</a></li>
				<li class="child"><a href="<%=basePath%>/revenueAmount/previewListPropertyCompany.html">收益总额列表(代理商)</a></li>
				<li class="child"><a href="<%=basePath%>/revenueAmount/previewListEbuy.html">收益总额列表(周边店铺)</a></li>
				<li class="child"><a href="<%=basePath%>/revenueAmount/previewListFinancer.html">收益总额列表(财务)</a></li>
				<li class="child"><a href="<%=basePath%>/revenueSettle/listPropCompany.html">结算情况查询(物业)</a></li>
				<li class="child"><a href="<%=basePath%>/revenueSettle/listPropertyAgent.html">结算情况查询(代理商)</a></li>
				<li class="child"><a href="<%=basePath%>/revenueSettle/listEbuy.html">结算情况查询(周边店铺)</a></li>
				<li class="child"><a href="<%=basePath%>/revenueSettle/listFinance.html">结算中心(财务)</a></li>
			</ul>
			
			<ul style="display: block;" class="collapsed" id="menuShow">
				<li class="parent on expanded"><a class="menu-01 on" href="#">周边店铺</a>
				   <ul style="display: block;" class="collapsed" id="menuShow">
				       <li class="child"><a href="<%=basePath%>/revenueEbuy/ebuySearch.html">收益查询</a></li>
				       <li class="child"><a href="<%=basePath%>/revenueEbuy/ebuyList.html">周边店铺交易明细</a></li>
				       <li class="child"><a href="<%=basePath%>/revenueEbuy/ebuyRevenue.html">结算情况查询</a></li>
				   </ul>
				</li>
			</ul>
			
        </li> --%>
        	<%
        	List<OmsPermiFunction> permiFunctionList = (List<OmsPermiFunction>)request.getAttribute("permiFunctionList");
        	int preNodeLevel= 0 ; //上一个节点级次
        	int unCloseElementCount = 0; //到最后节点还剩下未闭合的标签数
        	Integer ebuyRevenueCount = (Integer) request.getAttribute("ebuyRevenueCount");
        	Boolean isPcManagementRevenue = (Boolean) request.getAttribute("isPcManagementRevenue");
        	for(int i = 0; i < permiFunctionList.size(); i++){
        		
        		OmsPermiFunction omsPermiFunction = permiFunctionList.get(i);
        		if(isPcManagementRevenue != null && isPcManagementRevenue == false &&
        				(omsPermiFunction.getName().contains("收益中心-管理处") || omsPermiFunction.getName().contains("明细")
        						 || omsPermiFunction.getName().contains("收益查询") || omsPermiFunction.getName().contains("结算情况"))) {
        			continue;
        		}
        		
        		if(omsPermiFunction.getLevel()<preNodeLevel){//根据level来输出上几个非明细标签收尾
	        		for(int j = 0 ; j<preNodeLevel - omsPermiFunction.getLevel(); j++){//闭合标签
	        			unCloseElementCount--;//闭合时，消减 unCloseElementCount
	    				out.write("\t                </ul>\r\n");
	    	            out.write("\t            </li>\r\n");
	        		}
    			}
        		
        		if(omsPermiFunction.getIsleaf()!=1){//非明细结点
        			unCloseElementCount++;//待闭合标签
        			out.write("\t            <li class=\"parent collapsed\"><a class=\"menu-01\" href=\"#\">");
        			out.write(omsPermiFunction.getName());
					out.write("</a>\r\n");
        			out.write("\t                <ul class=\"collapsed\" id=\"menuShow\">\r\n");
        		}else{
        			if(ebuyRevenueCount == null || ebuyRevenueCount > 0 || !omsPermiFunction.getAction().contains("/revenueEbuy/ebuyList.html")) {
        				if(omsPermiFunction.getAction().startsWith("http")) {
        					out.write("\t                    <li class=\"child\"><a href='" + omsPermiFunction.getAction()+"\'>"+omsPermiFunction.getName()+"</a></li>\r\n");
        				} else {
        					out.write("\t                    <li class=\"child\"><a href='"+ basePath+omsPermiFunction.getAction()+"\'>"+omsPermiFunction.getName()+"</a></li>\r\n");
        				}
        			}
        		}
        		preNodeLevel = omsPermiFunction.getLevel();
        	}
			for(int i = 0; i < unCloseElementCount; i++){
	        	out.write("\t                </ul>\r\n");
	       	    out.write("\t            </li>\r\n");
			}
        	%>
        </ul>
	</div>
	<div class="main-right">
        <div class="menu-arrow"></div>
        <div class="main-con"><iframe src="<%=basePath%>/security/welcome.html" name="mainFrame" id="mainFrame" frameborder="no" scrolling="no" ></iframe>
			<div class="footer rela-foot">
	            <p>粤ICP备14059299号<br />Copyright <span>&copy;</span> 2014 深圳前海邻里乐科技服务有限公司 All rights reserved.</p>
	        </div>
        </div>
		<script type="text/javascript">
		$(function() {	//判断安卓ios系统
			var u = navigator.userAgent, app = navigator.appVersion;
			var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
			
			$("#mainFrame").load(function(){
				var $this = $(this);
				if (document.readyState == "complete") {
					var windowHeight = $(window).height() - 76;
					var getMainHeight = setInterval(function(){
						var mainheight = $this.contents().find('.info:visible').height() + 110;
						if(mainheight > 110){
							clearInterval(getMainHeight);
							$this.height(mainheight < windowHeight ? windowHeight : mainheight);
							
							if(isiOS){
								$this.contents().find('.info:visible').width($('.main-con').width()-30);
								$this.height((mainheight < windowHeight ? windowHeight : mainheight) + 140);
							}
						}
					},100)
				}
				$('.top-line01').css('width', '0').stop(true,false).animate({width:'100%'},800, 'linear');
				$this.stop(true,false).delay(150).animate({'margin-left':'-1px', 'opacity':'1'}, 800);
			});
		});
        </script>
    </div>
</div>

</body>
<script type="text/javascript" src="<%=basePath%>/js/jquery.mousewheel.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/perfect-scrollbar.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/JQuery.MenuTree.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.common.js"></script>
<script type="text/javascript">

$('.menuTree').find('a').each(function(){
	var sHref = $(this).attr('href');
	$(this).click(function(e){
		if( sHref != '#' ){
			$('#mainFrame').css({'margin-left':'30px', 'opacity':'0'}).attr('src',sHref).attr("url", sHref);
			e.preventDefault;
		}
		return false;
	});
}); 

function exitsystem(){
	if (confirm('您确定要退出系统吗？')){
  		window.parent.location="<%=basePath%>/login/doLogout.html";
	}
}

$(function() {
	$('#menu3').perfectScrollbar();
});
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
<!-- <script src="https://s11.cnzz.com/z_stat.php?id=1261123817&web_id=1261123817" language="JavaScript"></script> -->
</html>
