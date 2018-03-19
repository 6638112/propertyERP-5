<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html lang = "zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="description" content="">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<meta name="format-detection" content="telephone=no, email=no">

<title>个人信息</title>
<link rel="stylesheet" href="../css/common.css?V1">
</head>

<body class="bggrey">
<!-- <section class="divide-box user-info-title borderbottom" style="padding-top:55px;"><span class="p010 f14 grey">会员信息</span></section>
<section class="sectionBox borderbottom">
	<ul class="user-menu-list">
    	<li><span class="right grey wp80"><input class="input-text t-right wp100" type="text" name="readonly" value="K566" readonly /></span>卡号</li>
    	<li><span class="right grey wp80"><input class="input-text t-right wp100" type="text" name="readonly" value="钻石花" readonly /></span>会员等级</li>
    	<li><span class="right grey wp80"><input class="input-text t-right wp100" type="text" name="readonly" value="2014/01/01-2015/12/31" readonly /></span>有效期</li>
    </ul>
</section> -->
<section class="divide-box user-info-title borderbottom">
	<span class="p010 f14 grey">个人信息  </span>
	<a id="userInfoEdit" class="disblock p010 right" href="#"><span  class="red">编辑</span></a>
</section>

<section class="sectionBox borderbottom">
	<form id="inputForm">
	<ul class="user-menu-list user-info-text">
    	<li><span class="right grey wp80"><input class="input-text t-right wp100" type="text" name="realName" value="${jsonResponse.dataValue.realName }" readonly="readonly" /></span>姓名</li>
    	<li><span class="right grey wp80">
    		<select name="sex" class="input-text right mtop10 select-no-border w50" disabled >
    			<option value="0" <c:if test="${jsonResponse.dataValue.sex==0}">  selected="selected" </c:if> >男</option>
    			<option value="1" <c:if test="${jsonResponse.dataValue.sex==1}">  selected="selected" </c:if> >女</option>
    		</select>
    		</span>性别</li>
    		
    	<!-- <li><span class="right grey wp80"><input class="input-text t-right wp100" type="text" name="readonly" value="4504811990010****6" readonly="readonly" /></span>证件号码</li>
    	<li><span class="right grey wp80"><input class="input-text t-right wp100" type="text" name="readonly" value="中国" readonly="readonly" /></span>国籍</li> -->
    	
    	<li><span class="right grey wp80"><input class="input-text t-right wp80" style="float: right;[;padding-right:20px;]" type="date" name="birthday" 
    		 value="${fn:substring(jsonResponse.dataValue.birthday, 0, 10) }" 
    		  readonly   /> 
    		</span>生日</li>
    	<li><span class="right grey wp80"><input class="input-text t-right wp100" type="text" name="mobile" value="${jsonResponse.dataValue.mobile }" readonly="readonly" /></span>手机</li>
    	
    	<%-- <li><span class="right grey wp80"><input class="input-text t-right wp100" type="text" name="readonly" value="${jsonResponse.dataValue.birthday }" readonly="readonly" /></span>电子邮箱</li> --%>
    </ul>
	</form>
</section>
<script src="${resourcePathHttps}/commonjs/zepto.min.js"></script>
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
	})
</script>
<script type="text/javascript">
//会员信息编辑
;(function($){
	$('#userInfoEdit').click(function(){
		if($(this).children('span').text() == '确定'){
			$('.user-info-text').find('input').removeClass('black').addClass('grey').prop('readonly',true);
			$(this).children('span').text('编辑');
			
			//ajax请求保存
			 $.ajax({
				type:"POST",
				url:"../user/updPersonInfo.do",
				data:$("#inputForm").serialize(),
				dataType:"text",
				success:function(data){
					alert("保存结果：" + data);
				},
			}); 
		}else{
			$('.user-info-text').find('input').removeClass('grey').addClass('black').prop('readonly',false);
			$('.user-info-text').find('select').removeClass('grey').addClass('black').prop('disabled',false);
			$('.user-info-text').find('input').eq(0).focus();
			$(this).children('span').html('确定');
		}
	});	
})(Zepto);
</script>
</body>
</html>