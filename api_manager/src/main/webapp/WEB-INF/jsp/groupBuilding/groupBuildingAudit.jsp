
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业-小区审核-审核</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />

</head>

<body>
<div class="info">
	<form class="inputform" method="post" action="../groupBuilding/saveAudit.html">
		<input type="hidden" name="gbId" value="${entity.id }" />
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
	      <tr>
	        <td align="right">管理处：</td>
	        <td>
	            ${entity.propertyManagementName }
	        </td>
	      </tr>
	      <tr>
	        <td align="right">小区所属街道办：</td>
	        <td>${entity.streetName }</td>
	      </tr>
	      <tr>
	        <td align="right">街道办电话：</td>
	        <td>${entity.streetTel }</td>
	      </tr>
	      <tr>
	        <td align="right">小区所在居委会：</td>
	        <td>${entity.neighborName }</td>
	      </tr>
	      <tr>
	        <td align="right">居委会电话：</td>
	        <td>${entity.neighborTel }</td>
	      </tr>
	    </table>
	    <h2 class="mtop20">审核</h2>
        <div class="bs-example">
            <table class="info-list" border="0">
              <tr>
                <td width="90" align="right">审核结果：</td>
                <td colspan="5">
                	<select id="selectCheck" name="auditStatus" onchange="showTable();" class="select_normal w131">
                        <option value="0">通过</option>
                        <option value="1">不通过</option>
                    </select>
                </td>
              </tr>
              <tr id="auditStatusNo" class="dsn">
                <td align="right">原因：</td>
                <td colspan="5"><textarea class="textareas txt02" name="auditDesc" cols="" rows="5"></textarea></td>
              </tr>
            </table>
        </div>
<!--         <table id="auditStatusYes" class="info-list-02 mtop20" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="120" align="right">小区是否全面合作：</td>
            <td><input class="mtop3" name="cptType" type="radio" value="1" /> 是<input class="mtop3 mleft20" name="cptType" type="radio" value="0" /> 否</td>
          </tr>
          <tr>
            <td align="right">缴费周期：</td>
            <td>每月 <input type="text" id="pp_startDate" name="payPeriodStart" class="input_text pp w30" value="" /> 日 至 
            		<input type="text" id="ppe_endDate" name="payPeriodEnd" class="input_text pp w30" value="" /> 日</td>
          </tr>
          <tr>
            <td align="right">周期是否跨月：</td>
            <td><input class="mtop3" name="propertyMonthChange" type="radio" value="0" /> 跨0月
            	<input class="mtop3 mleft20" name="propertyMonthChange" type="radio" value="1" /> 跨1月</td>
          </tr>
        </table> -->
        <div class="padb"><input class="info-btn" type="button" onclick="submitValidate();" value="提 交" />
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" class="info-btn" onclick="history.back();" value="返回" /></div>
    </form>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
	function submitValidate(){
		if($("#pp_startDate").val() && $("#pp_endDate").val()){
			if(Date.parse($("#pp_endDate").val()) - Date.parse($("#pp_startDate").val()) < 0){
				alert("缴费结束时间不能大于开始时间");
				return false;
			}
		}
		$(".inputform").submit();
	}
	function showTable(){
		var ck = $('#selectCheck').val();
		if(ck==0){
			$('#auditStatusYes').show();
			$('#auditStatusNo').hide();
		}else{
			$('#auditStatusNo').show();
			$('#auditStatusYes').hide();
		}
		
		window.parent.TuneHeight();
	}
</script>
</html>
