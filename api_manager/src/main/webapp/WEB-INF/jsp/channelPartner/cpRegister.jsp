<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>渠道合伙人注册推荐</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css?v20150505">
</head>

<body class="resetbody">
<div class="header resetheader">
	<div class="left">解放区<span> 解放后，一切都会有!</span></div>
    <div class="right">欢迎来到解放区运营管理系统！</div>
</div>
<div class="main posrelative resetmain">
    <div class="info w960">
        <form  id="inputForm" class="inputform" action="../channelPartner/save.html" method="post">
        <h2>渠道注册</h2>
        <div class="distr step01">
            <div class="bs-example">
                <div class="add-user">
                    <table border="0" cellspacing="0" cellpadding="0">
                    
                     <tr>
                        <td align="right"><Span class="red">*</Span> 用户名：</td>
                        <td><input type="text" class="input_text pp" name="omsUserName" value="" datatype="*4-20" nullmsg="请输入用户名" errormsg="用户名称至少由4位字符组成" onchange="validateUserName(this);"/>用来登录解放区后台系统</td>
                      </tr>
                      <tr>
                        <td align="right"><Span class="red">*</Span> 密码：</td>
                        <td><input type="password" class="input_text pp" value="" name="password" datatype="s6-20" nullmsg="请输入新密码" errormsg="密码至少由6位字符组成"/></td>
                      </tr>
                      <tr>
                        <td align="right"><Span class="red">*</Span> 确认密码：</td>
                        <td><input type="password" class="input_text pp" value="" name="repassword" datatype="s6-20" recheck="password" nullmsg="请再次输入密码" errormsg="您两次输入的账号密码不一致"/></td>
                      </tr>
                        
                      <tr>
                        <td align="right" width="300"><Span class="red">*</Span> 姓名：</td>
                        <td><input type="text" class="input_text pp" name="name" value="" datatype="*" nullmsg="请输入姓名"/></td>
                      </tr>
                      <tr>
                        <td align="right"><Span class="red">*</Span> 手机号码：</td>
                        <td><input type="text" class="input_text pp" name="phone" value="" datatype="m" nullmsg="请输入手机号码" errormsg="请输入正确的手机号码格式"/></td>
                      </tr>
                      <tr>
                        <td align="right"><Span class="red">*</Span> 联系邮箱：</td>
                        <td><input type="text" class="input_text pp" name="email" value="" datatype="e" nullmsg="请输入联系邮箱" errormsg="请输入正确的邮箱格式"/></td>
                      </tr>
                     
                      <tr>
                        <td align="right" width="300"><Span class="red"></Span> 公司名称：</td>
                        <td><input type="text" class="input_text pp" name="cname" value="" />个人用户可以不填</td>
                      </tr>
                      
                      <tr>
                        <td align="right" width="300"><Span class="red"></Span> 代理类型：</td>
                        <td><input type="radio" name="partnerType" value="company"  <c:if test="${partnerType=='company'}">checked="checked"</c:if> />公司代理 <br/>
                        	<input type="radio" name="partnerType" value="person" <c:if test="${partnerType=='person'}">checked="checked"</c:if>/>个人代理
                        </td>
                      </tr>
                    </table>
                </div>
            </div>
            <input id="submitBtn" class="info-btn mtop40" style="margin-left:357px;" type="submit" value="完成" />
        </div>
        
        <div class="distr step04 dsn">
            <div class="bs-example">
                <div class="add-user">
                	<div class="t_center f14">您的注册已成功提交！解放区工作人员将在3个工作日内把审核结果发送至您的手机，请注意查收，谢谢！<br/>您可以登录 <a class="blue" href="http://oos.jiefangqu.com" target="_blank">解放区物业管理后台</a></div>
                </div>
            </div>
        </div>
        </form>
    </div>
</div>
<div class="footer rela-foot">
    <p>粤ICP备14059299号<br />Copyright <span>&copy;</span> 2014 深圳前海邻里乐科技服务有限公司 All rights reserved.</p>
</div>

</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.cxselect.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
(function($){
    //表单验证
	$(".inputform").Validform({
		tiptype:3
	});
	
})(jQuery);

//检查是否选择了图片文件
function checkImgType(ths) {
          if (ths.value == "") {
              alert("请上传图片");
              return false;
          } else {
              if (!/\.(gif|jpg|jpeg|png|bmp|GIF|JPG|PNG|BMP)$/.test(ths.value)) {
                  alert("图片类型必须是.gif,jpeg,jpg,png,bmp中的一种");
                  ths.value = "";
                  return false;
              }
          }
          return true;
      }


//验证用户是否存在
function validateUserName(ths) {

  // 1获取文本框的内容
  var userName = ths.value;

  // 2将获取到的内容发送给服务器的servlet
  //使用jquery的XMLHTTPRequest对象get请求的封装
  
  $.ajax({
      type:"POST",   
	  dataType:"text",
      url:"../omsUser/validUserName.html", //发送给服务器的url
      data:"omsUserName="+userName, //发送给服务器的参数
      success:function(data, textStatus){ 
          if(data==="已经存在该账号，请重新输入"){
        	  $(ths).siblings(".Validform_checktip").attr("class", "Validform_checktip Validform_wrong").text("已经存在该账号，请重新输入");
        	  $(ths).focus();
          }
      },
  });
}

//验证推荐的物业公司是否合法：1不在别人推荐锁定期，2不是已签约的
function validatePCName(ths) {

  // 1获取文本框的内容
  var pcName = ths.value;

  // 2将获取到的内容发送给服务器的servlet
  //使用jquery的XMLHTTPRequest对象get请求的封装
  
  $.ajax({
      type:"POST",   
	  dataType:"text",
      url:"../channelPartner/verfyPCName.html", 
      data:"pcName=" + pcName, 
      success:function(data, textStatus){ 
          if(data!=="验证通过"){
        	  $(ths).siblings(".Validform_checktip").attr("class", "Validform_checktip Validform_wrong").text(data);
        	  $(ths).focus();
          }
      },
  });
}
</script>
</html>
