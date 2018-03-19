<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="Keywords" content="社区O2O平台,小区服务O2O,生活软件,手机生活软件,小区app,解放区小区app,物业费,物业费减免,不交物业费,社区邻里,街坊邻居,二手交换,社区拼单,上下班拼车,家常菜谱,居家美食,家庭厨房,私家超市,家庭购物,家庭网购" />
<meta name="Description" content="解放区社区O2O平台是中国社区服务领导者。通过B2B2F的模式和O2O线上线下的合作方式联合第三方物业公司，为小区居民提供全面社区生活服务。解放区app是一款物业免费神器，为小区居民提供便捷生活消费服务，同时为物业公司免费提供服务平台。">
<title>解放区-智慧城市运营商 诚邀加盟</title>
<link rel="stylesheet" type="text/css" href="../css/base.css" media="screen">
<link rel="stylesheet" type="text/css" href="../css/global.css" media="screen">
</head>
<body>
<form class="inputform" action="../commercialOpportunity/saveCOinfo.html">
<div class="main-box mt40">
	<div class="sub-title">解放区-智慧城市运营商<br>诚邀加盟</div>
  	<div id="step01" class="p01 t-center">
        <ul class="ptxt01">
        	<li>姓名： <input class="f16 input_text" name="linkName" type="text" maxlength="12" placeholder="您的姓名" datatype="*2-12" nullmsg="亲，大名是要的哟" errormsg="大名至少两个字哟~" /></li>
        	<li>电话： <input class="f16 input_text" name="linkTel" type="text" maxlength="11" placeholder="电话号码" datatype="m" nullmsg="在移动互联网的时代，手机少不了~" errormsg="手机号码格式不对哟~" /></li>
        	<li>公司： <input class="f16 input_text" name="companyName" type="text" maxlength="24" placeholder="公司名称" /></li>
        </ul>
        <div class="mt40">
        	<a class="register-btn bgred" href="javascript:void(0)">完 成</a>
        </div> 
    </div>
  	<div id="step02" class="p01 t-center dsn">
        <ul class="ptxt01 mt100">
        	<li><img src="../images/done.png"/></li>
        	<li class="f28 red mt10">提交成功</li>
        	<li class="f18 mt30">我们的工作人员将尽快与您电话沟通,期待与您合作！</li>
        </ul>
    </div>
</div>
</form>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
(function($){
    //表单验证
	$(".inputform").Validform({
		tiptype:3,
		ajaxPost:true, 
		btnSubmit:".register-btn", 
		callback:function(){
			$.Hidemsg();
			$('#step01').hide();
			$('#step02').show();
		}
	});
	
})(jQuery);
</script>
</body>
</html>