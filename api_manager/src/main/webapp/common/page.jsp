<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- <style type="text/css">
	#pager ul.pages {
	display:block;
	border:none;
	text-transform:uppercase;
	font-size:10px;
	margin:10px 0 50px;
	padding:0;
	}
	#pager ul.pages li {
	list-style:none;
	float:left;
	border:1px solid #ccc;
	text-decoration:none;
	margin:0 5px 0 0;
	padding:5px;
	}
	#pager ul.pages li:hover {
	border:1px solid #003f7e;
	}
	#pager ul.pages li.pgEmpty {
	border:1px solid #eee;
	color:#eee;
	}
	#pager ul.pages li.pgCurrent {
	background: #DD4929 none repeat scroll 0% 0%;
	color: #FFF;
	border: 1px solid #CF4627;
	height: 26px;
	padding: 0px 8px;
	display: inline-block;
	} 
</style> -->
<style type="text/css">
	a.pgEmpty {
		border:0;
	}
	a.pgEmpty:hover {
	}
</style>
&nbsp;&nbsp;&nbsp;&nbsp;<span class="pagebanner" id="result"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="pager" class="pagelinks"></span>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.pager.js?v=151020_2"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#pager").pager({ pagenumber: "${page._currentPage+1}", pagecount: "${page._totalPage}", buttonClickCallback: PageClick });
	$("#searchForm").find("#_page_qry_div").remove();
	$("#searchForm").append("<div id=\"_page_qry_div\"></div>");
	$("#_page_qry_div").append("<input type=\"hidden\" name=\"page\" value=\""+"${page._currentPage+1}"+"\"/>");
	$("#_page_qry_div").append("<input type=\"hidden\" name=\"pageNum\" value=\""+"${page._length}"+"\"/>");
	//没有记录//只有一页
	var pageHtml = "总共<font color=\"red\"> ${page._count} </font> 条记录";
	if("${page._totalPage}">1){
		if("${page._isLast}"=="true"){//最后一页
			pageHtml+=", 显示第<font color=\"red\"> ${page._begin+1} </font>条至第<font color=\"red\"> ${page._count} </font>条.";
		}else{
			pageHtml+=", 显示第<font color=\"red\"> ${page._begin+1} </font>条至第<font color=\"red\"> ${page._begin+page._length} </font>条.";
		}
	}
	$("#result").html(pageHtml);
});
PageClick = function(pageclickednumber) {
	$("#pager").pager({ pagenumber: pageclickednumber, pagecount: "${page._totalPage}", buttonClickCallback: PageClick});
	$("#searchForm").find("#_page_qry_div").remove();
	$("#searchForm").append("<div id=\"_page_qry_div\"></div>");
	$("#_page_qry_div").append("<input type=\"hidden\" name=\"page\" value=\""+pageclickednumber+"\"/>");
	$("#_page_qry_div").append("<input type=\"hidden\" name=\"pageNum\" value=\""+"${page._length}"+"\"/>");
	$("#searchForm").submit();
};
</script> 