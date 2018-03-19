<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>物业公司-注册</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>


<body class="resetbody">
<div class="header resetheader">
	<div class="left">解放区<span> 解放后，一切都会有! </span></div>
    <div class="right">欢迎来到解放区运营管理系统！</div>
</div>
<div class="main posrelative resetmain">
    <div class="info w960">
        <form class="inputform" enctype="multipart/form-data" method="post" action="../propertyCompany/registerInfoSave.html">
        <h2>账号申请</h2>
        <div class="distr">
            <div class="bs-example">
                <div class="add-user">
                    <table border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="150" align="right"><Span class="red">*</Span> 物业公司名称：</td>
                        <td><input type="text" class="input_text pp" name="pcName" onchange="validatePCName(this);" value="" datatype="*4-20" nullmsg="请输入物业公司名称！" errormsg="物业公司名称至少由4位字符组成！"></td>
                      </tr>
                      <tr>
                        <td align="right"><Span class="red">*</Span> 手机号码：</td>
                        <td><input type="text" class="input_text pp" name="phoneNum" value="" datatype="m" nullmsg="请输入手机号码！" errormsg="请输入正确的手机号码格式！"> 请填写手机号，我们会在第一时间发送审核结果到此手机上！</td>
                      </tr>
                      <tr>
                        <td align="right">办公电话：</td>
                        <td><input type="text" class="input_text pp" name="officeNum" value=""></td>
                      </tr>
                      <tr>
                        <td align="right">联系人姓名：</td>
                        <td><input type="text" class="input_text pp" name="linkMan" value=""></td>
                      </tr>
                      <tr>
                        <td align="right"><Span class="red">*</Span> 联系邮箱：</td>
                        <td><input type="text" class="input_text pp" name="email" value="" datatype="e" nullmsg="请输入联系邮箱！" errormsg="请输入正确的邮箱格式！"> 用于给您发送解放区登录账号以及后期找回密码，请填写正确</td>
                      </tr>
                  <!--     <tr >
                        <td align="right">上传贵公司营业执照：</td>
                        <td><input id="imageInput" name="imageFileBL"  type="file" class="w150"  onchange="checkImgType(this)"></td>
                      </tr>
                      <tr>
                        <td align="right">上传小区管理资质证明：</td>
                        <td><input id="imageInput" name="imageFileC"  type="file" class="w150" onchange="checkImgType(this)"></td>
                      </tr> -->
                    </table>
                    <table class="add-plots" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="150" align="right" class="bold f16">添加管辖小区：</td>
                        <td></td>
                      </tr>
                      <tr>
                        <td align="right">小区所在地：</td>
                        <td>
	                        <select id="province" onchange="onSelectChange(this,'city');" class="province select_normal" data-first-title="选择省" title="选择省">
	                        	<option value="-1">选择省</option>
	                        	<c:forEach items="${pcbList}" var="pcb" >
	                        		<option value="${pcb.id}">${pcb.name}</option>
	                        	</c:forEach>
	                        </select> 
	                        <select id="city" onchange="onSelectChange(this,'block');"  class="city select_normal" data-first-title="选择市">
	                        	<option value="-1">选择市</option>
	                        </select> 
	                        <select  id="block" class="area select_normal" data-first-title="选择区">
	                        	<option value="-1">选择区</option>
	                        </select>
                        </td>
                      </tr>
                      <tr>
                        <td align="right">小区详细地址：</td>
                        <td><input id="road" type="text" class="input_text" value="" placeholder='如富民路23号'></td>
                      </tr>
                      <tr>
                        <td align="right">小区名称：</td>
                        <td><input id="plotName" type="text" class="input_text" value=""></td>
                      </tr>
                      <tr id="btnPlot">
                        <td></td>
                        <td><input id="createPlot" class="input-btn mtop3" type="button" value="添加到管辖小区"></td>
                      </tr>
                      <tr class="plotAdded dsn">
                        <td align="right">管辖小区：</td>
                        <td><span class="plot-name"> <input name="province" type="text" value="" readonly="readonly" width="auto"> 
                        <input name="city" type="text" value="" readonly="readonly" width="auto"> 
                        <input name="block" type="text" value="" readonly="readonly"> 
                        <input name="abId" type="hidden" value="" readonly="readonly"> 
                        <input name="gbAddrDesc" type="text" value="" readonly="readonly"> 
                        <input name="gbName" type="text" value="" readonly="readonly"></span> <a class="blue mar-left15 plot-delete" href="#">删除</a></td>
                      </tr>
                    </table>
                </div>
            </div>
            <div class="mtop20"><input class="mtop3" name="" type="checkbox" datatype="*" nullmsg="同意使用协议后才能提交。"> 阅读并同意<a class="blue" href="../docs/Operation_background_use_agreement_1_1.htm" target="_blank">《解放区物业管理后台使用协议》</a></div>
            <input class="info-btn mtop10 infoList" type="submit" value="提交申请" />
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
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript">	
	var urlId = window.location.search;
	$.cookie("urlCk", urlId,{expires:30},{path:"/"});	
</script>
<script type="text/javascript">

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

$(function(){
    //表单验证
	var infoList = $(".inputform").Validform({
		tiptype:3,
		postonce:true
	});
});

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

</script>
<script type="text/javascript" src="../js/provinceCityBlock.js"></script>
<script type="text/javascript">
	var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
	document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F71a32d0a717889a15a9935bd0ee1c1bb' type='text/javascript'%3E%3C/script%3E"));
</script>
</html>
