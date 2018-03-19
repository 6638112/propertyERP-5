<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业缴费管理-账单明细</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
<style type="text/css">
.tablePrint td {
	padding-top:50px;
	font-size: 18px;
}
.tablePrint h2 {
	color: #000000;
}
body {
	color:#000000;
}

.info-btn{ width:200px; height:40px; border:0; border-radius:2px; cursor:pointer; margin:20px 0; background:#cc3d44; color:#fff; overflow:hidden;}
.small-btn{ height:30px; margin:0}
.w100{ width:100px !important;}

/* 应用这个样式的在打印时隐藏 */
.Noprint {
     display: none;
    }
    
   
table.info-list-02{ width:590px; _width:90%; background:#fff; font-size:12px !important; margin-top:0 !important}
td.r,th.r {
	text-align: right;
	padding-right: 10px;
}
</style>

<script type="text/javascript">
var hkey_root,hkey_path,hkey_key;
hkey_root="HKEY_CURRENT_USER";
hkey_path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
function pagesetup_null() {
	try {
		var RegWsh = new ActiveXObject("WScript.Shell");hkey_key = "header";
		RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "");
		hkey_key = "footer";
		RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "");
	} catch (e) {}
}
function doPrint() {
	//pagesetup_null();
	$("#noPrintTable").hide();
	//$("#adTr").show();
	window.print();
	//$("#adTr").hide();
	$("#noPrintTable").show();
}
</script>
</head>

<body>
<div class="info">
	 
	<table class="" style="margin-top:20px;" border="0" cellpadding="0" cellspacing="0" width="100%" id="noPrintTable">
      <tr>
        <td width="65%">
        <OBJECT id=WebBrowser classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0 width=0 ></OBJECT>
        <div style="float: right;">
        <input class="info-btn small-btn w100" style="margin-right: 50px;" type="button" value=" 确认打印 " onclick="doPrint();"></div></td>
        <td width="35%"></td>
      </tr>
    </table>
	
	<div id="forPrintDiv" width="80%">
    
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
      	<td align="center" style="padding-top:10px;">
      		<table class="tablePrint" border="0" cellpadding="0" cellspacing="0" width="850px;">
      		  <tr>
      		  	<td colspan="3"><h2 style="text-align: center;font-size:22px;">电子账单</h2></td>
      		  </tr>
		      <tr>
		        <td width="20%" style="line-height:150%;padding-right:2px;" width="90"><div align="right" class="bold">客户名称：</div></td>
		        <td width="70%" style="border-bottom:1px solid #000;line-height:150%;text-align: left;padding-left:3px;">${addr }</td>
		        <td width="10%"></td>
		      </tr>
		      <tr>
		        <td width="20%" style="line-height:150%;padding-right:2px;" width="90"><div align="right" class="bold">账单名称：</div></td>
		        <td width="70%" style="border-bottom:1px solid #000;line-height:150%;text-align: left;padding-left:3px;">${feeName }</td>
		        <td width="10%"></td>
		      </tr>
		      <tr>
		        <td width="20%" style="line-height:150%;padding-right:2px;" width="90"><div align="right" class="bold">账单月份：</div></td>
		        <td width="70%" style="border-bottom:1px solid #000;line-height:150%;text-align: left;padding-left:3px;">${month }</td>
		        <td width="10%"></td>
		      </tr>
		      <tr>
		        <td width="20%" style="line-height:150%;padding-right:2px;" width="90"><div align="right" class="bold">缴费时间：</div></td>
		        <td width="70%" style="border-bottom:1px solid #000;line-height:150%;text-align: left;padding-left:3px;"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${payTime }" /> </td>
		        <td width="10%"></td>
		      </tr>
		      <tr style="vertical-align:top">
		        <td width="20%" style="line-height:150%;padding-right:2px;" width="90"><div align="right"  class="bold">缴费明细：</div></td>
		        <td width="80%" colspan="2" style="line-height:150%;text-align: left;padding-left:3px;">
		        	<display:table name="feeDetailList" id="row" class="info-list-02 bordered"   decorator="org.displaytag.decorator.TotalTableDecorator">
		        		<display:column title="收费项目名称" property="name" />
		        		<display:column title="金额" property="amount" format="{0,number,0.00}" class="r" style="text-align: right;" total="true"  />
		        	</display:table>
		        </td>
		      </tr>
		      <tr>
		        <td colspan="2" style="text-align: right;padding-right: 70px;">${pcName }&nbsp;</td>
		      </tr>
		      <tr>
		        <td colspan="2" style="text-align: right;padding-right: 70px; padding-top:10px;"><fmt:formatDate value="${currentDate}" pattern="yyyy-MM-dd" />&nbsp;</td>
		      </tr>
		      
		      <tr id="adTr">
		      	<td colspan="3" style="text-align: center; padding:50px;padding-top:10px;">
		      		<c:if test="${!empty adIconUrl }"><img width="100%" border="0" height="300" src="${adIconUrl }" alt=""  /></c:if>
		    	</td>
		      </tr>
		    </table>
      	</td>
      </tr>
    </table>
    
    </div>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript">
	$(function(){
		$("tr.total").find('td').eq(0).text("合计");
		$("tr.total").find('td').eq(1).css("text-align","right");
		
		$("table.info-list-02").attr({"border":"0", "cellpadding":"0", "cellspacing":"0"});
	});
</script>
</html>
