
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业新增管理处</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<link rel="stylesheet" type="text/css" href="../css/jRating.jquery.css" />

</head>

<body>
<div class="info">
    <form class="inputform" method="post" action="../propertyCompany/saveEditMgt.html">
    	<input type="hidden" name="tPropertyCompanyFId" value="${companyId }" />
    	<input type="hidden" name="managementId" value="${mgt.id }" />
    	<input type="hidden" name="omsUserId" value="${mgt.omsUserId }" />
        <h2>管理处基本信息</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="120"><span class="red">*</span>管理处名称：</td>
            <td><input type="text" name="managementName" class="input_text pp" value="${mgt.name }" datatype="*3-50" errormsg="管理处名称至少由3位字符组成！" nullmsg="请输入管理处名称！" /></td>
          </tr>
          <tr>
            <td>管理处电话 ：</td>
            <td>
                <input type="text" name="managementTel" class="input_text pp" value="${mgt.tel }" />
                                此电话展示在解放区APP物业服务的联系电话上
            </td>
          </tr>
          <tr>
            <td>管理处负责人 ：</td>
            <td>
                <input type="text" name="personChargeName" class="input_text pp" value="${mgt.personChargeName }" />
            </td>
          </tr>
          <tr>
            <td>负责人联系方式 ：</td>
            <td>
                <input type="text" name="personChargeTel" class="input_text pp" value="${mgt.personChargeTel }" />
            </td>
          </tr>
          <tr>
            <td><span class="red">*</span>管理处账号：</td>
            <td>
                <input type="text" name="userAccount" class="input_text pp" value="${mgt.userAccount }" id="userAccount"
                	<c:if test="${mgt.id!=null }">style="background-color: #dad8d6;" readonly="readonly"</c:if>
                	datatype="*5-15" errormsg="管理处账号至少由5位字符组成！" nullmsg="请输入管理处账号！" onchange="validateUserName(this);"/><span id = "showResult"></span>
                                此账号用于登录解放区管理后台
            </td>
          </tr>
            	<c:choose>
            		<c:when test="${empty mgt.id }">
	            		<tr>
				            <td><span class="red">*</span>密码：</td>
				            <td>
				                <input type="password" id="pwd1" name="password" class="input_text pp" value="${mgt.password }" datatype="*6-15" errormsg="密码至少由6位字符组成！" nullmsg="请输入密码！" />
				            </td>
				          </tr>
				          <tr>
				            <td><span class="red">*</span>确认密码：</td>
				            <td>
				                <input type="password" id="pwd2" name="password2" class="input_text pp" value="${mgt.password }" datatype="*6-15" errormsg="确认密码至少由6位字符组成！" nullmsg="请输入确认密码！" />
				            </td>
				          </tr>
            		</c:when>
            		<c:otherwise>
			          <tr>
			            <td>新密码：</td>
			            <td>
			                <input type="password" id="pwd1" name="password" class="input_text pp" datatype="s6-15" errormsg="密码至少由6位字符组成！" ignore="ignore"/>（不填表示使用之前的密码）
			            </td>
			          </tr>
			          <tr>
			            <td>确认新密码：</td>
			            <td>
			                <input type="password" id="pwd2" name="password2" class="input_text pp" datatype="s6-15" errormsg="确认密码至少由6位字符组成！" ignore="ignore"/>
			            </td>
			          </tr>
            		</c:otherwise>
            	</c:choose>
        </table>
        <h2>管理处收款信息管理</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="120">收款权限开启：</td>
            <td>
            	<input class="mtop3" name="isOpenReceivables" type="radio" <c:if test="${mgt.isOpenReceivables==1 }" >checked="checked" </c:if> value="1">开启
            	<input class="mtop3 mleft20" name="isOpenReceivables" type="radio" <c:if test="${mgt.isOpenReceivables==0 }" >checked="checked" </c:if> <c:if test="${mgt.isOpenReceivables==null }" >checked="checked" </c:if> value="0">关闭
            </td>
          </tr>
          <tr>
            <td width="120">银行卡号：</td>
            <td><input type="text" name="accountNo" id="accountNo" class="input_text pp" value="${mgt.accountNo }"/></td>
          </tr>
          <tr>
            <td width="120">开户行：</td>
            <td><input type="text" name="bankName" id="bankName" class="input_text pp" value="${mgt.bankName }"/></td>
          </tr>
          <tr>
            <td width="120">账户名称：</td>
            <td><input type="text" name="accountName" id="accountName" class="input_text pp" value="${mgt.accountName }"/></td>
          </tr>
          <tr>
            <td width="120">开卡支行：</td>
            <td><input type="text" name="bankBranch" id="bankBranch" class="input_text pp" value="${mgt.bankBranch }"/></td>
          </tr>
          <tr>
            <td width="120">支行所在省：</td>
            <td><input type="text" name="bankProvince" id="bankProvince" class="input_text pp" value="${mgt.bankProvince }"/></td>
          </tr>
          <tr>
            <td width="120">支行所在市：</td>
            <td><input type="text" name="bankCity" id="bankCity" class="input_text pp" value="${mgt.bankCity }"/></td>
          </tr>
          <tr>
	        <td>自动结算日期：</td>
	        <td>每月&nbsp;&nbsp;<input name="settlementDay" id="settlementDay" class="input_text pp" style="width: 20px;" value="${mgt.settlementDay }"/>&nbsp;&nbsp;日</td>
	      </tr>

            <tr >
                <td><span class="grey">AppId：</span></td>
                <td>${payConfig.appid}</td>
            </tr>
            <tr >
                <td><span class="grey">支付宝公钥：</span></td>
                <td>${payConfig.publicKey}</td>
            </tr>
            <tr >
                <td><span class="grey">支付宝私钥：</span></td>
                <td>${payConfig.privateKey}</td>
            </tr>
        </table>
        <input class="info-btn" id='sumAccount' type="submit" onclick="return submitValidate();" value="提 交" />
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
		tiptype:3,
		dragonfly:true
	});
});
function submitValidate(){
	if($("#pwd1").val() && $("#pwd2").val()){
		var pwd1 = $("#pwd1").val();
		var pwd2 = $("#pwd2").val();
		if(pwd1!=pwd2){
			alert('两次输入密码不一致!');
			return false;
		}
	}
	var reg = /^\d{6,29}$/;
	if($("input[name='isOpenReceivables']:checked").val() == 1) {
		var accountNo = $("#accountNo").val();
		if(accountNo == "") {
			alert("银行卡号不能为空！");
			return false;
		}
		if(reg.test(accountNo) == false) {
			alert("银行卡号格式不正确！");
			return false;
		}
		if($("#bankName").val() == "") {
			alert('开户行不能为空!');
			return false;
		}
		if($("#accountName").val() == "") {
			alert('账户不能为空!');
			return false;
		}
		if($("#bankBranch").val() == "") {
			alert('开卡支行不能为空!');
			return false;
		}
		if($("#bankProvince").val() == "") {
			alert('支行所在省不能为空!');
			return false;
		}
		if($("#bankCity").val() == "") {
			alert('支行所在市不能为空!');
			return false;
		}
	}
	
	//验证自动结算日 不能大于31，不能为负数
	if (typeof($("#settlementDay").val()) != "undefined") {
		if($("#settlementDay").val() != "" && parseInt($("#settlementDay").val())<=0 == true) {
			alert('自动结算日不能为负数或零！');
	        return false;
		}	
		if($("#settlementDay").val() != "" && parseInt($("#settlementDay").val())>28 == true) {
			alert('自动结算日填写不正确，日期不能大于28！');
	        return false;
		}	
		var reg = /^\d+(?=\.{0,1}\d+$|$)/
		//alert($("#settlementDay").val());
		if($("#settlementDay").val() != "" && reg.test($("#settlementDay").val())==false){
			alert('自动结算日填写不正确！');
	        return false;
		}
	}
}


$(function(){
	$("#sumAccount").click(function(){
		if($("#showResult").hasClass('red')){
			alert("已经存在该账号，请重新输入");
			return false;
		}
	});
});

//验证用户是否存在
function validateUserName(ths) {

    // 1获取文本框的内容
    var userName = ths.value;
    
    $.ajax({
        type:"POST",  
        url:"../omsUser/validUserName.html", 
        data:"omsUserName="+userName, 
        success:function(data, textStatus){ 
            $("#showResult").html(data);
            if(data==="已经存在该账号，请重新输入"){
           	 	$("#showResult").addClass('red');
           	 	ths.focus();
            }else{//验证通过
            	$("#showResult").removeClass('red').css("color","green");
            }
            var regs = /(\d(?!\d*$)|[A-z])[A-z0-9]*$/;
    		if(regs.test(userName) == false) {
    			alert("账号必须是字母加数字组合，请重新输入");
    			ths.focus();
    		}
        },
    });
}
</script>
</html>
