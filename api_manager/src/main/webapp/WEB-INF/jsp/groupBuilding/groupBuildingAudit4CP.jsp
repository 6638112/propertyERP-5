
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>渠道合伙人-小区审核-审核</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />
</head>

<body>
<div class="info">
	<form class="inputform" method="post" action="../groupBuilding/saveAuditCP.html">
		<input type="hidden" name="gbrId" value="${entity.id }" />
        <h2>小区审核-审核</h2>
	    <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
	      <tr>
	        <td width="120" align="right">小区名称：</td>
	        <td>${entity.name }</td>
	      </tr>
	      <tr>
	        <td align="right">小区所属物业：</td>
	        <td>${entity.propertyCompanyName }</td>
	      </tr>
	      <tr>
	        <td align="right">小区所在地：</td>
	        <td>
	            ${entity.addressProvinceName}-${entity.addressCityName}-${entity.addressBlockName}
	         </td>
	      </tr>
	      <tr>
	        <td align="right">详细地址：</td>
	        <td>${entity.addressDesc }</td>
	      </tr>
	    </table>
	    <h2 class="mtop20">审核</h2>
        <div class="bs-example">
            <table class="info-list" border="0">
              <tr>
                <td width="90" align="right">审核结果：</td>
                <td colspan="5">
                	<select id="selectCheck" name="auditStatus" onchange="showTable();" class="select_normal w131">
                        <option value="1">通过</option>
                        <option value="2">不通过</option>
                    </select>
                </td>
              </tr>
              <tr id="auditStatusNo" class="dsn">
                <td align="right">原因：</td>
                <td colspan="5"><textarea class="textareas txt02" name="auditDesc" cols="" rows="5" onKeyUp="if(this.value.length > 100) this.value=this.value.substr(0,100)"></textarea></td>
              </tr>
            </table>
        </div>
        <div class="padb">
        	<input class="info-btn" type="submit" value="提 交" />
        	<input type="button" class="info-btn" onclick="history.back();" value="返回" />
        </div>
    </form>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
	function showTable(){
		var ck = $('#selectCheck').val();
		if(ck==1){
			$('#auditStatusNo').hide();
		}else{
			$('#auditStatusNo').show();
		}
	}
</script>
</html>
