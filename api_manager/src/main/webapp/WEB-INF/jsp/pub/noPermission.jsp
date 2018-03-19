
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>404页面</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body style="height:auto; background:#f5f5f5;"  onload="toLoginPage();" >
<div class="info" style="width:960px; margin:50px auto;">
     <div class="t_center f16">
        	<div id="fofimg">
            	<img class="fof" src="../images/nopower.jpg" />
            </div>
     </div>
</div>

</body>

<script type="text/javascript">
    function toLoginPage(){
    	setTimeout(function(){
    		if (window != top) top.location.href = '../security/loginPage2.html';
    	},2000);
    }
</script>

</html>
