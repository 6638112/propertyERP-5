
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业信息修改</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<link rel="stylesheet" type="text/css" href="../css/jRating.jquery.css" />
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />

</head>

<body>
<div class="info">
    <form class="inputform" method="post" action="../propertyCompany/saveEditpcmsg.html?companyId=${companyId}">
    	<input type="hidden" name="tPropertyCompanyFId" value="${companyId}" />
        <h2>物业公司基本信息</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="120"><span class="red">*</span>物业公司名称：</td>
            <td><input type="text" name="pcName" id="pcName" maxlength="30" class="input_text pp" placeholder="" value="${pcName}" onchange="validatePCName(this);"  datatype="*4-50" errormsg="公司名称至少由4位字符组成！"/></td>
          </tr>
          <tr>
            <td><span class="red">*</span>公司电话 ：</td>
            <td>
                <input type="text" id="pcTel" maxlength="15" name="pcTel" class="input_text pp" placeholder="" value="${pcTel}" datatype="/-/|n6-16" errormsg="请输入正确格式的公司电话！"/>
            </td>
          </tr>
          <tr>
            <td><span class="red">*</span>联系人：</td>
            <td>
                <input type="text" id="linkMan" maxlength="16" name="linkMan" class="input_text pp" datatype="*2-15" placeholder="" value="${pcLinkman}" errormsg="联系人至少由2位字符组成！"/>
            </td>
          </tr>
                    <tr>
            <td><span class="red">*</span>联系人手机：</td>
            <td>
                <input  id="pcMobilephone" maxlength="11" type="text" name="pcMobilephone" class="input_text pp" placeholder="" value="${pcMobilePhone}" datatype="m" errormsg="请输入正确的手机号码格式！"/>
           </td>
            </tr>
          <tr>
            <td><span class="red">*</span>账户名称：</td>
            <td>
                <input  id="accountName" maxlength="30" type="text" name="accountName" class="input_text pp" placeholder="" value="${accountName}" datatype="*" errormsg="请输入正确账户名称！"/>
           </td>           
          </tr>
          <tr>
            <td><span class="red">*</span>银行卡号：</td>
            <td>
                <input  id="accountNo" maxlength="24" type="text" name="accountNo" class="input_text pp" placeholder="" value="${accountNo}" datatype="n" errormsg="请输入正确的银行卡号！"/>
           </td>
           </tr>
           <tr>
            <td><span class="red">*</span>开户行：</td>
            <td>
                <input  id="bankName" maxlength="30" type="text" name="bankName" class="input_text pp" placeholder="" value="${bankName}" datatype="*" errormsg="请输入正确的开户行"/>
            </td>
           </tr>
           <tr>
            <td><span class="red">*</span>开卡支行：</td>
            <td>
                <input  id="bankBranch" maxlength="30" type="text" name="bankBranch" class="input_text pp" placeholder="" value="${bankBranch}" datatype="*" errormsg="请输入正确的开户支行"/>
            </td>
           </tr>
           <tr>
            <td><span class="red">*</span>支行所在省：</td>
            <td>
                <input  id="bankProvince" maxlength="10" type="text" name="bankProvince" class="input_text pp" placeholder="" value="${bankProvince}" datatype="*" errormsg="请输入正确的支行所在省"/>
            </td>
           </tr>
           <tr>
            <td><span class="red">*</span>支行所在市：</td>
            <td>
                <input  id="bankCity" maxlength="10" type="text" name="bankCity" class="input_text pp" placeholder="" value="${bankCity}" datatype="*" errormsg="请输入正确的支行所在市"/>
            </td>
           </tr>
           <tr>
            <td><span class="red">*</span>自动结算日：</td>
            <td>
                               每月 <input  id="revenueDate" maxlength="2" style="width:50px;" type="text" name="revenueDate" class="input_text pp" placeholder="" value="${revenueDate}" datatype="*" errormsg="请输入正确的自动结算日"/> 日
                               &nbsp;&nbsp;&nbsp;（缴费截止日后一天）
            </td>
           </tr>
           

        </table>
        <input class="info-btn" id='sumAccount' type="submit" value="修改"/>
    </form>
</div>

</body>

<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
$(function(){
    //表单验证
	$(".inputform").Validform({
		tiptype:3
	});
});


/* $(function(){
    $(".inputform").submit(function(){
        var pctel = $("#pcTel").val();
        var pcmp = $("#pcMobilephone").val();
        var pcname = $("#pcName").val();
        var linkman = $("#linkMan").val();
        var accountName = $("#accountName").val();
        var accountNo = $("#accountNo").val();
        var bankName = $("#bankName").val();
        var bankBranch = $("#bankBranch").val();
        var bankProvince = $("#bankProvince").val();
        var bankCity = $("#bankCity").val();
        var revenueDate = $("#revenueDate").val();
        if(pctel == "" && pcmp =="" && pcname=="" && linkman == ""
        		&& accountName == "" && accountNo == "" && bankName == ""
        		&& bankBranch == "" && bankProvince == "" && bankCity == "" && revenueDate == ""){
            alert('至少要输入一处要修改的内容！！！');
            return false;
       }
   });

}); */
// 校验物业公司的有效性
function validatePCName(ths) {
	  // 1获取文本框的内容
	  var pcName = ths.value;

	  $.ajax({
	      type:"POST",   
		  dataType:"text",
	      url:"../propertyCompany/checkPcName.html", 
	      data:"pcName=" + pcName, 
	      success:function(data, textStatus){ 
	          if(data!=="验证通过"){
	        	  $(ths).siblings(".Validform_checktip").attr("class", "Validform_checktip Validform_wrong").text(data);
	        	  $(ths).val('');
	        	  $(ths).focus();
	          }
	      },
	  });
}
</script>
</html>