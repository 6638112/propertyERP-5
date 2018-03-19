<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>绑定银行卡</title>
<link rel="stylesheet" href="css/recommendaward.css">
</head>

<body class="bggrey">
<section id="wrapBox" class="sectionBox bggrey pos-relative minheight100">
	<form class="inputform">
	<section class="sectionBox">
	    <section class="divide-box bordertbgrey"></section>
	    <ul class="register-list borderbottomgrey">
	        <li class="borderbottomgrey">
	            <div class="displaybox">
	                <div class="item-standard-name height36 f16 w50">持卡人</div>
	                <div class="boxflex01 f16 t-right grey"><input id="bankCardPersonInput" class="input-text wp100 t-right" name="bankCardPersonInput" type="text" placeholder="持卡人姓名" value="" maxlength="14" datatype="*" nullmsg="请输入持卡人姓名！" /></div>
	            </div>
	        </li>
	        <li class="borderbottomgrey">
	            <div class="displaybox">
	                <div class="item-standard-name height36 f16 w50">卡号</div>
	                <div class="boxflex01 f16 t-right grey"><input name="bankCardNumInput" id="bankCardNumInput" class="input-text wp100 t-right" type="text" placeholder="银行卡号" value="" maxlength="24" datatype="*19-24" nullmsg="请输入银行卡号！" errormsg="银行卡号由16-20位数字组成！" /></div>
	            </div>
	        </li>
	        <li class="borderbottomgrey">
	            <div class="displaybox">
	                <div class="item-standard-name height36 f16 boxflex01 pleft30">开户行</div>
	                <div class="boxflex01 f16 lineheight140">
			            <select id="bankCardNameSelect" name="bankCardNameSelect" class="input-text wp100 list-arrow directionRtl pright15 mtop3" datatype="*" nullmsg="请选择开户行！" >
			                <option value="">请选择</option>
		                   	<c:forEach items="${bankList}" var="bank" >
		                   		<option value="${bank.bankname}">${bank.bankname}</option>
		                  	</c:forEach>
			            </select>
	                </div>
	            </div>
	        </li>
	        <li class="borderbottomgrey">
	            <div class="displaybox">
	                <div class="item-standard-name height36 f16 boxflex01 pleft30">开户行所在省</div>
	                <div class="boxflex01 f16 lineheight140">
			            <select id="province" name="province" onchange="onSelectChange(this.value,'city');" class="input-text wp100 list-arrow directionRtl pright15 mtop3" datatype="*" nullmsg="请选择开户行所在省！" >
			                <option value="">请选择</option>
		                   	<c:forEach items="${pcbList}" var="pcb" >
		                   		<option value="${pcb.id}">${pcb.name}</option>
		                  	</c:forEach>
			            </select>
	                </div>
	            </div>
	        </li>
	        <li class="borderbottomgrey">
	            <div class="displaybox">
	                <div class="item-standard-name height36 f16 boxflex01 pleft30">开户行所在市</div>
	                <div class="boxflex01 f16 lineheight140">
			            <select id="city" name="city" class="input-text wp100 list-arrow directionRtl pright15 mtop3" datatype="*" nullmsg="请选择开户行所在市！" >
			                <option value="">请选择</option>
			            </select>
	                </div>
	            </div>
	        </li>
	        <li>
	            <div class="displaybox">
	                <div class="item-standard-name height36 f16 w50">开户行支行</div>
	                <div class="boxflex01 f16 t-right grey"><input id="bankCardSubName" class="input-text wp100 t-right" name="bankCardSubName" type="text" placeholder="开户行支行名称" value="" maxlength="20" datatype="*" nullmsg="请输入开户行支行名称！" /></div>
	            </div>
	        </li>
	    </ul>
	    
		<section class="sectionBox bggrey">
		    <div class="m010 mtop15"><input class="btn-submit btnSubmit btn-next" type="button" value="完成" /></div>
		</section>
	    <section class="divide-box"></section>
	
	</section>
	</form>
	<section class="sectionBox wrap-bg dsn">
		<div class="tips-box">
			<div class="t-center ptb20 borderbottomgrey">
				<div class="marb15 f18">请确认银行卡信息</div>
				<div class="wp90 t-left margin_auto">姓名：<span class="bankCardPerson"></span></div>
				<div class="wp90 t-left margin_auto">卡号：<span class="bankCardNum"></span></div>
				<div class="wp90 t-left margin_auto">银行：<span class="bankCardName"></span></div>
			</div>
			<ul class="displaybox">
				<li class="boxflex01 ptb10 t-center back-btn">取消</li>
				<li class="boxflex01 ptb10 t-center red borderleft pay-check-btn"><a class="disblock red" href="javascript:void(0)">确认绑定</a></li>
			</ul>	
		</div>
	</section>
</section>
<script src="js/jquery-1.11.2.min.js"></script>
<script src="js/Validform_v5.3.2.js"></script>
<script>
$(function(){
	//表单验证
	$(".inputform").Validform({
		tiptype:1,
		btnSubmit:".btnSubmit",
		ajaxPost:true,
		beforeSubmit:function(curform){
			$('#wrapBox').addClass('heightp100');
			
			//确认银行卡信息
			var bankCardPerson = $('#bankCardPersonInput').val();
			var bankCardNum = $('#bankCardNumInput').val();
			var bankProvince = $('#province option:selected').text();
			var bankCity = $('#city option:selected').text();
			var bankName = $('#bankCardNameSelect option:selected').text();
			var bankBranchName = $('#bankCardSubName').val();
			
			$('.bankCardPerson').text(bankCardPerson);
			$('.bankCardNum').text(bankCardNum);
			$('.bankCardName').text(bankName);
			$('.wrap-bg').removeClass('dsn');

			$('.back-btn').click(function(){
				$('#wrapBox').removeClass('heightp100');
				$('.wrap-bg').addClass('dsn');
			});
			
			$('.pay-check-btn').click(function(e){
				//提交地址			
				jQuery.ajax({//绑定手机，该接口有校验验证码功能
					  url: "../common/toUrl.do",
					  cache: false,
					  dataType:"json",
					  data:{"detailUrl":"/dredge/master/bindBankCard.json", "cardOwner":bankCardPerson, "cardNumber":bankCardNum, "bankName":bankName, "bankBranchName":bankBranchName, "bankProvince":bankProvince, "bankCity":bankCity, "isNeedLogin":"1"},
					  success:function(data){
						if(data.status =="0000"){
							// 成功 
							window.location.href = 'qryMyAccount.do';
							history.pushState({}, '个人中心', 'personalCenter.do');
						}else{
							//失败 
							$.Showmsg(data.message);
						}
					  },  
			          error: function(){  
			          	$.Showmsg('网络不给力，请稍后重试'); 
			          } 
				});
			});
			
			return false;
		}
	});

	//检查触发按钮状态
	$('input').keyup(function(){
		countValNum();
		
	});
	$('select').change(function(){
		countValNum();
	});
	
	$('#bankCardNumInput').bankInput();
	
	//保存表单数据  
	$("input[type=text],select").change(function(){
	    $this = $(this);
	    localStorage[$this.attr("name")] = $this.val();
	});

	//读取本地存储的数据
	if (localStorage) {

		if (localStorage.bankCardNameSelect) {
		  $("#bankCardNameSelect").val(localStorage.bankCardNameSelect);
		}
		if (localStorage.province) {
		  $("#province").val(localStorage.province);
		  //触发已选省份的城市列表
		  setSelect(localStorage.province,"city");
		}
		if (localStorage.city) {
		  $("#city").val(localStorage.city);
		}
		if (localStorage.bankCardPersonInput) {
		  $("#bankCardPersonInput").val(localStorage.bankCardPersonInput);
		}
		if (localStorage.bankCardNumInput) {
		  $("#bankCardNumInput").val(localStorage.bankCardNumInput);
		}
		if (localStorage.bankCardSubName) {
		  $("#bankCardSubName").val(localStorage.bankCardSubName);
		}
		//检查按钮是否可触发
		countValNum();

	}
});

//触发按钮状态
function countValNum(){
	var num = 0;
	$('.register-list li').each(function(){
	var thisInputVal = $(this).find('input').val();
	var thisSelectVal = $(this).find('select').val();
	if(thisInputVal == '' || thisSelectVal == ''){
			num += 1;
		}
	});
	if(num == 0){
		$('.btnSubmit').addClass('bgred noborder white');
	}else{
		$('.btnSubmit').removeClass('bgred noborder white');
}
}

(function($) {
	// 输入框格式化 
	$.fn.bankInput = function(options) {
			var defaults = {
				min: 16, // 最少输入字数 
				max: 24, // 最多输入字数 
				deimiter: ' ', // 账号分隔符 
				onlyNumber: true, // 只能输入数字 
				copy: true // 允许复制 
			};
			var opts = $.extend({}, defaults, options);
			var obj = $(this);
			obj.css({
				imeMode: 'Disabled',
				borderWidth: '1px',
				color: '#000',
				fontFamly: 'Times New Roman'
			}).attr('maxlength', opts.max);
			if (obj.val() != '') obj.val(obj.val().replace(/\s/g, '').replace(/(\d{4})(?=\d)/g, "$1" + opts.deimiter));
			obj.bind('keyup', function(event) {
				if (opts.onlyNumber) {
					if (!(event.keyCode >= 48 && event.keyCode <= 57)) {
						this.value = this.value.replace(/\D/g, '');
					}
				}
				this.value = this.value.replace(/\s/g, '').replace(/(\d{4})(?=\d)/g, "$1" + opts.deimiter);
				//保存数据
				localStorage[this.getAttribute("name")] = this.value;
			}).bind('dragenter', function() {
				return false;
			}).bind('onpaste', function() {
				return !clipboardData.getData('text').match(/\D/);
			}).bind('blur', function() {
				this.value = this.value.replace(/\s/g, '').replace(/(\d{4})(?=\d)/g, "$1" + opts.deimiter);
			});
		};
		// 列表显示格式化 
	$.fn.bankList = function(options) {
		var defaults = {
			deimiter: ' ' // 分隔符 
		};
		var opts = $.extend({}, defaults, options);
		return this.each(function() {
			$(this).text($(this).text().replace(/\s/g, '').replace(/(\d{4})(?=\d)/g, "$1" + opts.deimiter));
		});
	};
})(jQuery);

/* ------------------------------------------------------------------ */

//省市切换
function onSelectChange(fromSelVal,toSelId){
	//手动选择省份后，将localStorage的city置为空，避免没有选择城市时，下次重新进入该页面无法设置city的value
	localStorage['city'] = '';
	setSelect(fromSelVal,toSelId);
}

//省市切换详情逻辑处理
function setSelect(fromSelVal,toSelId){
	$('#' + toSelId).html('<option value="">请选择</option>');//清空之前的选项
	if(toSelId === "city"){//选择省，更新市
		jQuery.ajax({
			  url: "../common/toUrl.do",
			  cache: false,
			  dataType:"json",
			  async:false,
			  data:{"detailUrl" : "/addressCity/getAddressCityListById.json", "provId":fromSelVal, "isNeedLogin":"0"}, //"provId="+fromSelVal,
			  success: function(data){
			    $.each(data.dataValue.list, function(i, item) {
			    	$("<option value='" + item.id + "'>" + item.name + "</option>").appendTo($("#"+toSelId));
			    });
			  },  
              error: function(){  
              	$.Showmsg('网络不给力，请稍后重试'); 
              }
		});
	}	
}

</script>


</body>
</html>