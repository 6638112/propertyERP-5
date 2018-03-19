<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>代理人注册推荐</title>
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
        <form  id="inputForm" class="inputform" action="../channelPartner/savePerson.html" method="post">
        <input type="hidden" name="partnerType" value="${partnerType}" /> 
        <h2>注册推荐 
            <span class="f12 mar-left15">
                <span class="step orange"><span class="f16 bold">Step 1</span>：注册个人信息</span>
                
                <span class="step"><span class="grey">-></span> <span class="f16 bold">Step 2</span>：签订电子协议</span>
                <span class="step"><span class="grey">-></span> <span class="f16 bold">Step 3</span>：完善付款信息，解放区在您成功推荐签约后付款</span>
            </span>
        </h2>
        <div class="distr step01">
            <div class="bs-example">
                <div class="add-user">
                    <table border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td align="right" width="300"><Span class="red">*</Span> 登录账号：</td>
                        <td><input type="text" class="input_text pp" maxlength="15" name="omsUserName" value="" datatype="EnglishOrNumber" nullmsg="请输入登录账号！" errormsg="登录账号由字母或数字组成！" onchange="validateUserName(this);">用来登录解放区后台系统</td>
                      </tr>
                      <tr>
                        <td align="right"><Span class="red">*</Span> 密码：</td>
                        <td><input type="password" class="input_text pp" value="" name="password" datatype="s6-20" nullmsg="请输入新密码！" errormsg="密码至少由6位字符组成！"></td>
                      </tr>
                      <tr>
                        <td align="right"><Span class="red">*</Span> 确认密码：</td>
                        <td><input type="password" class="input_text pp" value="" name="repassword" datatype="s6-20" recheck="password" nullmsg="请再次输入密码！" errormsg="您两次输入的账号密码不一致！"></td>
                      </tr>
                      
                      <tr>
                        <td align="right"><Span class="red">*</Span> 手机号码：</td>
                        <td><input type="text" class="input_text pp" name="phone" value="" datatype="m" nullmsg="请输入手机号码！" errormsg="请输入正确的手机号码格式！"></td>
                      </tr>
                      <tr>
                        <td align="right"><Span class="red">*</Span> 联系邮箱：</td>
                        <td><input type="text" class="input_text pp" name="email" value="" datatype="e" nullmsg="请输入联系邮箱！" errormsg="请输入正确的邮箱格式！"></td>
                      </tr>
                      <tr>
                        <td align="right">邀请码：</td>
                        <td><input type="text" class="input_text pp" name="inviteCode"></td>
                      </tr>
                      <input type="hidden" name="partnerType" value="${partnerType}" /> 
                    </table>
                </div>
            </div>
            <input class="info-btn mtop40" style="margin-left:357px;" type="button" value="下一步" />
        </div>
        
        <div class="distr step03 dsn">
            <div class="bs-example">
                <div class="add-user jfq-paper">
                    <h1 class="t_center mtop20">解放区个人代理协议</h1>
                    <p class="mtop20 bold">一、代理协议条款的接受</p>
                    <p>《解放区平台》（以下简称《解放区》）的所有者和运营者深圳前海邻里乐科技服务有限公司（以下统称 “邻里乐”）在此提醒您在注册成为解放区业务代理人之前，请充分阅读并理解本条款中的各项内容。您接受本条款中所有条款，否则您无权注册、登录或使用本条款所涉相关服务。您的注册、登录、使用等行为将视为对本条款的接受，并同意接受本条款各项条款的约束。</p>
                    <p class="bold">二、定义
                    <p><strong>2.1 解放区：</strong>系指是邻里乐公司打造的面向整个中国物业行业的互联网服务平台。通过互联网线上联盟的方式整合物业资源，帮助物业公司实现扭亏为盈，其产品包括但不仅限于解放区APP，解放区物业管理后台等内容。
                    <p><strong>2.2 解放区APP装机比例：</strong>系指与邻里乐签署合作协议的物业公司所管辖小区里，小区用户安装解放区APP的比例：
                    <p>解放区APP装机比例=小区住户安装解放区APP注册的门牌数/小区住户总数
                    <p><strong>2.3 小区用户缴物业费比例：</strong>系指与邻里乐签署合作协议的物业公司所管辖小区里，小区用户通过解放区APP缴纳物业费的比例：
                    <p>小区用户缴物业费比例=小区住户通过解放区APP缴纳物业费的数量/小区住户总数
                    <p class="bold">三、合作方式
                    <p>3.1 邻里乐授权代理人进行解放区相关产品的代理推广工作，代理人需通过自身资源向物业公司推广解放区等相关产品,并促成邻里乐与物业公司签署《解放区社区合作协议书》和《物业费用代收协议》。
                    <p>3.2 代理人推广过程中,除提供物业公司决策者必要信息外,还需要全程负责与物业公司的谈判,并最终成功促成邻里乐和物业公司签订《解放区社区合作协议书》和《物业费用代收协议》并最终由邻里乐审核通过后,方视为代理人有效推广行为。
                    <p>3.3 邻里乐提供解放区渠道代理后台系统给代理推广人，代理人可以通过解放区渠道代理后台系统进行其所推广物业公司及其小区数量的查询和管理。
                    <p class="bold">四、结算方式
                    <p>4.1 代理人达到本协议3.2条款所约定的有效推广条件且该小区住户数缴费情况达到本协议4.3条相应条件后，邻里乐按照代理人促成签约的物业公司所管理的住户数给予代理人推广奖励。
                    <p>4.2 奖励标准：10元/户
                    <p>4.3 支付方式：当代理人促成签约的物业公司管辖小区里，解放区app装机比例>=10%，且至少有10户小区住户数通过解放区APP缴纳物业费用后，邻里乐支付奖励金额的50%给代理人；当代理人促成签约的物业公司管辖小区里，解放区app装机比例>=20%，且小区用户缴物业费比例>=20%后，邻里乐支付剩余的奖励金额给代理人。
                    <p>4.4 代理人需要提供准确的个人信息，如银行账户和身份证号码等给到邻里乐，并提供对应奖金金额的发票给到邻里乐。
                    <p class="bold">五、代理人须知：
                    <p>5.1 进行解放区产品的推广时必须保证：
                    <p class="indent2em">（1）遵守中国有关法律法规的规定。
                    <p class="indent2em">（2） 不得利用《解放区》作非法用途。
                    <p class="indent2em">（3）不得以任何方式干扰或破坏《解放区》APP平台任何部分或功能的正常运行
                    <p class="indent2em">（4）不得通过《解放区》从事任何危害国家安全、违反法律规定、侵犯其他用户合法权益的活动。
                    <p class="indent2em">（5）不得以任何形式损害《解放区》、邻里乐及关联公司的品牌和声誉。
                    <p class="indent2em">（6）不得以任何形式侵犯《解放区》、邻里乐及关联公司的知识产权。
                    <p>5.2 进行解放区产品的推广时必须保证：
                    <p class="indent2em">（1）如代理人违反国家法律法规或本代理协议条款，邻里乐公司将有权终止和代理人的合作而不需承担任何责任。如导致邻里乐公司遭受任何损害或遭受任何来自第三方的纠纷、诉讼、索赔要求等，代理人须向邻里乐公司赔偿相应的损失，并且对其违反代理协议条款所产生的一切后果承担全部法律责任。
                    <p class="indent2em">（2）依本《解放区个人代理协议》所取得的代理推广权利不可转让。
                    <p class="bold">六、权利与义务:
                    <p>6.1 邻里乐需保证解放区产品的正常运行。
                    <p>6.2 应代理人要求，邻里乐须向代理人提供营销、管理、技术等方面的支持，以及相应的培训。
                    <p>6.3 邻里乐负责对代理人推广的物业公司进行资格审查。（代理人严禁开发涉及黄、赌、毒等违法行为的用户，若因此而引起的一切法律及经济纠纷，代理人需承担全部法律责任）
                    <p>6.4 本代理协议经代理人点击同意并由邻里乐审核确认后生效，邻里乐有权随时对本条款内容进行修改、修改后的结果将公布于“解放区”网站（www.jiefangqu.com）上，代理人如继续合作，则视为其对修改后的内容无任何异议并同意遵守；如代理人对于修改内容有任何异议，可以向深圳前海邻里乐科技服务有限公司提出书面异议，并有权终止代理合作。如代理人继续合作，则视为仍受本条款及修改后条款的限制。
                    <p class="bold">七、其他说明：
                    <p>7.1 邻里乐公司郑重提醒代理人注意《解放区个人代理协议》中免除邻里乐公司责任和加重代理人义务的条款，请代理人仔细阅读，自主考虑风险。
                    <p>7.2 本《解放区个人代理协议》签订地为深圳。本《解放区个人代理协议》的解释、效力及纠纷的解决，适用于中华人民共和国法律。若代理人和邻里乐之间发生任何纠纷或争议，首先应友好协商解决，协商不成的，任何一方均可向深圳市仲裁委员会提起仲裁申请。
                    <p>7.3 以上条款的最终解释权归深圳前海邻里乐科技服务有限公司所有。
                </div>
            </div>
            <div class="mtop20" style="margin-left:357px;"><input class="mtop3 paper-check" name="" type="checkbox" checked="checked"> 阅读并同意《解放区个人代理协议》</div>
            <div class="padb"><input id="incomeCount" class="info-btn mtop10" style="margin-left:357px;" type="button" value="下一步" /> <a class="blue mar-left15 returnBack" style="display:inline-block; vertical-align:middle; margin-top:-12px;" href="#">返回上一步</a></div>
        </div>
        <div class="distr step04 dsn">
            <div class="bs-example">
                <div class="add-user">
                	
                    <table class="mtop20" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td align="right" width="300"><Span class="red">*</Span> 开户行：</td>
                        <td><input type="text" name="bankName" class="input_text pp" value="" datatype="*" nullmsg="请输入开户行名称！"></td>
                      </tr>
                      <tr>
                        <td align="right"><Span class="red">*</Span> 用户卡号：</td>
                        <td><input type="text" name="bankCardNo" class="input_text pp" value="" datatype="n" nullmsg="请输入用户卡号！"></td>
                      </tr>
                        
                      <tr>
                        <td align="right" width="300"><Span class="red">*</Span> 姓名：</td>
                        <td><input type="text" class="input_text pp" name="name" value="" datatype="*" nullmsg="请输入姓名！"></td>
                      </tr>
                      <tr>
                        <td align="right"><Span class="red">*</Span> 身份证号：</td>
                        <td><input type="text" name="idNumber" class="input_text pp" value="" datatype="idcard" nullmsg="请输入身份证号！" errormsg="请输入正确的身份证号！"></td>
                      </tr>
                    </table>
                </div>
            </div>
            <div class="padb">
            	<input id="submitBtn" class="info-btn mtop40" style="margin-left:357px;" type="submit" value="完 成" /> 
            	<a class="blue mar-left15 skipBtn" style="display:inline-block; vertical-align:middle; margin-top:18px;" href="javascript:void(0)">跳过</a>
           	</div>
        </div>
        <div class="distr step04 dsn">
            <div class="bs-example">
                <div class="add-user">
                	<div class="t_center f14">您的注册已成功提交！解放区工作人员将在3个工作日内把审核结果发送至您的手机，请注意查收，谢谢！</div>
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
	
	//下一步 不可跳过校验
	$('.info-btn').click(function(){
		$(this).parents('.distr').find('.input_text').each(function() {
            $(this).focus();
        });
		if($(this).parents('.distr').next('.distr').length && $(this).parents('.distr').find('.Validform_wrong').length == 0){
			if(!$('.paper-check').is(':checked')&&$('.step03').is(':visible')){
				alert('须同意《解放区个人代理协议》方可进行下一步操作！');
				return false;
			}else{
				$(this).parents('.distr').hide().next('.distr').show();
			}
		}
		if($('.step.orange').next('.step').length && $(this).parents('.distr').find('.Validform_wrong').length == 0){
			$('.step.orange').removeClass('orange').next('.step').addClass('orange');
		}
	}); 
	
	//最后一步"跳过"按钮，提交表单
	$('.skipBtn').click(function(){
		$("#inputForm").Validform().submitForm(true);
	}); 

	
	//上一步
	$('.returnBack').click(function(){
		$(this).parents('.distr').hide().prev('.distr').show();
		$('.step.orange').removeClass('orange').prev('.step').addClass('orange');
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

//省市切换
function onSelectChange(obj,toSelId){
	setSelect(obj.value,toSelId);
}

//省市切换详情逻辑处理
function setSelect(fromSelVal,toSelId){
	document.getElementById(toSelId).innerHTML="";//清空之前的选项
	if(toSelId === "city"){//选择省，更新市
		jQuery.ajax({
			  url: "../propertyCompany/getCityList.html",
			  cache: false,
			  dataType:"json",
			  async:false,
			  data:"apId="+fromSelVal,
			  success: function(data){
			    $.each(JSON.parse(data), function(i, item) {
			    	$("<option value='" + item.id + "'>" + item.name + "</option>").appendTo($("#"+toSelId));
			    });
			  }
			});
		$("#city").val(1);
		$("#block").empty();
	}else {
		jQuery.ajax({//选择市，更新区
			  url: "../propertyCompany/getBlockList.html",
			  cache: false,
			  dataType:"json",
			  data:"acId="+fromSelVal,
			  success: function(data){
			    $.each(JSON.parse(data), function(i, item) {
			    	$("<option value='" + item.id + "'>" + item.name + "</option>").appendTo($("#"+toSelId));
			    });
			  }
			});
	}	
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
        	  $(ths).siblings(".Validform_checktip").attr("class", "Validform_checktip Validform_wrong").text("已经存在该账号" + userName);
        	  $(ths).val('');
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
