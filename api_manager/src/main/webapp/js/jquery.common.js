//导航链接
$(function(){
	var $menuShow = $('#menuShow');
	$menuShow.slideDown();
	$menuShow.parents('li.parent').removeClass('collapsed').addClass('expanded');
	$menuShow.siblings('a').addClass('on');
});

//表单边框变色插件
$(function(){
	/*表单得到失去焦点*/
	jQuery.objFocusBlur=function(obj){
		$(document).on("focus", obj, function(){$(this).addClass("focus").removeClass("inputError");});
		$(document).on("blur", obj, function(){$(this).removeClass("focus");});
	};
	$.objFocusBlur(".input_text,.textarea");

	$.fn.objFocusBlur02 = function(opts){  //对象级别
		$(this).focus(function(){
			$(this).addClass(opts);
		});
		$(this).blur(function(){
			$(this).removeClass(opts);
		});
	};
	$('.keywords').objFocusBlur02('textarea-focus');
	$('.textareas').objFocusBlur02('textarea-focus');

	//登录框边框着色
	$('.login-txt').focus(function(){
		$('.login-info').addClass('login-info-focus');
	});
	$('.login-txt').blur(function(){
		$('.login-info').removeClass('login-info-focus');
	});
	
	//密码加密
	$(".login-btn").click(function(){
		var $loginNumber = $('#loginNumber');
		var $loginPassword = $('#loginPassword');
		var username = $loginNumber.val();
		var password = $loginPassword.val();
		
		if($.trim(username) === ''){
			$(".erroTxt").removeClass('dsn').find('span').text('用户名不能为空');
			$loginNumber.focus();
			return false;
		}else if($.trim(password) === ''){
			$(".erroTxt").removeClass('dsn').find('span').text('密码不能为空');
			$loginPassword.focus();
			return false;
		}
		$loginPassword.val( md5($loginPassword.val()) );
	})
	
	//按钮状态切换
	$('.input-btn').hover(function(){
		$(this).toggleClass('input-btn-hover');
	});


//表单提示
	$(document).on('focus', '.input_text:not(".readonly"):not(".pp"):not(".info-list .input_text")', function(event){
		if (this.value == this.defaultValue){
			this.value = "";
		}
	});
	$(document).on('blur', '.input_text:not(".readonly"):not(".pp"):not(".info-list .input_text")', function(event){
		if (this.value == ""){
			this.value = this.defaultValue;	
		}
	});
	
	$(".edui-body-container").focus(function(){
		var txtValue=$(this).html();
		if(txtValue=="<p>说两句儿~</p>"){
			$(this).html("");	
		}
		$('.edui-container').addClass('edui-container-focus');
	});
	$(".edui-body-container").blur(function(){
		var txtValue=$(this).html();
		if(txtValue==""){
			$(this).html("<p>说两句儿~</p>");	
		}
		$('.edui-container').removeClass('edui-container-focus');
	});
});
//图片hover效果
jQuery.fn.imgOpacity=function(){  //对象级别
    var $self = $(this);
    $self.hover(function(){
		$self.stop(true,false).animate({opacity:"0.80"},100);	
	},function(){
		$self.stop(true,false).animate({opacity:"1"},100);
	});  
};  
$('.addimg02 li img').imgOpacity(); 
$('.menu-arrow').imgOpacity();

//限制时间
$('.rad-01').click(function(){
	$(this).attr("checked","checked");
	$('.rad-02').removeAttr("checked");
	$('.ser-time').hide();
});
$('.rad-02').click(function(){
	$(this).attr("checked","checked");
	$('.rad-01').removeAttr("checked");
	$('.ser-time').show();
});

//明细
$('#detailBtn').click(function(){
	$('#detailCon').toggle();
});

//编辑、删除、添加客服联系方式
$(function(){
	$('.edit').click(function(){
		var $this = $(this);
		if($this.val()=='编辑'){
			$this.parent('div').parent('td').siblings('td').find('input').attr('class','input_text').removeAttr('readonly');
			$this.val('确定').css('color','#dd4929');
			$this.parent('div').parent('td').siblings('td').find('div.select-box').removeClass();
			$this.parent('div').parent('td').siblings('td').find('select').removeAttr('disabled').removeClass().addClass('select_normal w200');
		}else{
			$this.parent('div').parent('td').siblings('td').find('input').removeClass().addClass('input_noborder').attr({readonly:'true'});
			$this.val('编辑').css({color:'#3298cb'});
			$this.parent('div').parent('td').siblings('td').find('div').addClass('select-box');
			$this.parent('div').parent('td').siblings('td').find('select').removeClass().addClass('select_noborder w200').attr('disabled','true');
		}
	});
});

//插件表格样式
$(function(){
	$('.mars').addClass('info-list-02 mtop20').attr({'border':'0','cellpadding':'0','cellspacing':'1'});
});

//鼠标经过表格变色
$('.info-list-02 tr:not("tr.title"):not("tr.nobg"), .ranking-info tr:not("tr.nobg"), .mars tr').hover(function(){
	$(this).children('td').addClass('trbg');
},function(){
	$(this).children('td').removeClass('trbg');
}).click(function(event){
	if($(event.target).is('td') && $(this).children('td').hasClass('trbgon')){
		$(this).children('td').removeClass();	
	}else if($(event.target).is('td')){
		$(this).children('td').addClass('trbgon').end();
		$(this).siblings('tr').children('td').removeClass('trbgon');
	}
});

//上传照片
$('#addimg').click(function(){
	$('.addimg01').hide();
	$('.addimg02').show();
	return false; 
});
$('#addvd').click(function(){
	$('.addvd01').hide();
	$('.addvd02').show();
	return false; 
});

//隐藏导航
$('.menu-arrow').click(function(){
	var $menuTree=$('.menuTree');
	var menuWidth=$menuTree.width();
	if($menuTree.is(':visible')){
		$menuTree.hide(300).animate({left:"-"+menuWidth},300);
		$(this).attr("class",'menu-arrow01');
	}else{
		$menuTree.show(100).animate({left:"0px"},100);
		$(this).attr("class",'menu-arrow');
	}
});

//厨房右侧栏高度
$(function(){
	var Iheight = $('.food-left').height();
	$('.food-right').height(Iheight);
});

//按钮状态
$('.input-btn[name="btnColor"]').click(function(){
	$(this).toggleClass('btn-on');
});

//登录背景
$('.login-main').css({"backgroundPositionY":"650px"}).animate({"backgroundPositionY":"276px"},{easing: "easeInBack",duration: 2000,complete:function(){}});

//checkbox
$('.ck-box').click(function(){
	$(this).toggleClass('ck-box-on');
});

//权限  用户状态切换
(function($){
	//切换样式
	$.fn.swapClass = function(class1, class2){
		return this.each(function(){
			var $element = $(this);
			if($element.hasClass(class1)){
				$element.removeClass(class1).addClass(class2);
			}
			else if($element.hasClass(class2)){
				$element.removeClass(class2).addClass(class1);	
			}
		});
	};
	//切换文本
	$.fn.swapText = function(txt1, txt2){
		return this.each(function(){
			var $element = $(this);
			if($element.text() == txt1){
				$element.text(txt2);	
			}
			else if($element.text() == txt2){
				$element.text(txt1);	
			}
		});
	};
	
})(jQuery);

$('.btn-status').click(function(){
	$(this).swapClass('blue alive', 'grey asleep').swapText('启用', '禁用');
});

$('.btn-status-pay').click(function(){
	$(this).swapClass('blue alive', 'grey asleep').swapText('已缴费', '未缴费');
});

$('.user-btn-status').click(function(){
	$(this).find('a').swapText('禁言', '解除禁言');
	$('.user-status').swapClass('blue alive', 'grey asleep').swapText('正常','禁言');
});

//翻页点击效果
$('.pages li:lt(9):gt(1)').has('a').click(function(){
	$(this).addClass('on').siblings().removeClass('on');
});

//欢迎界面
$(function(){
	$('.welcome-txt .txt01').css({'top':'200px','opacity':'0'}).animate({'top':'0','opacity':'1'}, 600);
	var $lis = $('.welcome-txt .point-list li');
	$lis.eq(0).css({'top':'200px','opacity':'0'}).animate({'top':'0','opacity':'1'}, 600);
	$lis.eq(1).css({'top':'200px','opacity':'0'}).animate({'top':'0','opacity':'1'}, 750);
	$lis.eq(2).css({'top':'200px','opacity':'0'}).animate({'top':'0','opacity':'1'}, 900);
	$lis.eq(3).css({'top':'200px','opacity':'0'}).animate({'top':'0','opacity':'1'}, 1050);
	$lis.find('img').each(function() {
        $(this).imgOpacity();
    });
	$('.welcome-txt .point-bg').css({'top':'200px','opacity':'0'}).animate({'top':'-100px','opacity':'1'}, 1400);
});

//物业管理权限
var $bbsManager = $('.bbs-manager');
var $bbsList = $('.bbs-list');
$bbsList.hide();
$bbsManager.click(function(){
	$(this).parent('td').parent('tr').next('tr.bbs-list').toggle();
});

//审核
$(function(){
	$('.select-check').change(function(){
		var $checkReason = $(this).parent('td').parent('tr').next('tr');
		if($(this).val() === "pass"){//审核通过
			$checkReason.hide();
			if($('#newAccount')){
				$('#createAccount').show();	
			}
			window.parent.TuneHeight();
		}else{//审核不通过
			$checkReason.show();
			if($('#newAccount')){
				$('#createAccount').hide();	
			}
			window.parent.TuneHeight();
		}
	});
});
$(function(){
	//导入账单弹出层
	$('#importBill').click(function(){
		$.layer({
			type: 1,
			shade: [0.4,'#000000'],
			area: ['auto', 'auto'],
			title: false,
	  	border : [5, 0.3, '#000'],
			page: {dom : '.layer-bill'}
		});
	});
});
//增加删除小区
$(function(){
	var $plotAdded = $('.plotAdded');
	var $plotDelete = $('.plot-delete');
	$('#createPlot').click(function(){
		var provinceVal = $('#province option:selected').text();
		var cityVal = $('#city option:selected').text();
		var blockVal = $('#block option:selected').text();
		var blockIdVal = $('#block option:selected').val();
		var roadVal = $('#road').val();
		var plotNameVal = $('#plotName').val();
		if(provinceVal == '选择省' || cityVal == '' || cityVal == '选择市' || blockVal == '选择区' || plotNameVal == ''){
			alert('请完善小区信息！');
		}else{
			var $plotTable = $(this).parents('table');
			var $newPlot = $plotAdded.clone(true).show().appendTo($plotTable);
			if(provinceVal == cityVal){
				cityVal = '';
			}
			$newPlot.find('input[name=province]').val(provinceVal);
			$newPlot.find('input[name=city]').val(cityVal);
			$newPlot.find('input[name=block]').val(blockVal);
			$newPlot.find('input[name=abId]').val(blockIdVal);
			$newPlot.find('input[name=gbAddrDesc]').val(roadVal);
			$newPlot.find('input[name=gbName]').val(plotNameVal);
			$newPlot.find('input').each(function(){  
			   if($(this).val() == ''){
				   $(this).hide(); 
			   }else{
				   var textWidth = function(text){ 
						var sensor = $('<div>'+ text +'</div>').css({display: 'none'}); 
						$('body').append(sensor); 
						var width = sensor.width();
						sensor.remove(); 
						return width;
					};
					$(this).width(textWidth($(this).val())); 
			   }
			}); 
		}
		if($('.plotAdded').length > 1){
			$(this).val('继续添加小区').addClass('btn-on');
			$('#road').val('');
			$('#plotName').val('');
		}
		window.parent.TuneHeight();
	});	
	$plotDelete.click(function(){
		$(this).parents('tr').remove();
		window.parent.TuneHeight();
		return false;
	});
});

//全选
$(function(){
	$('#checkAll').click(function(){
		$(this).parents('tr').parents("thead").siblings('tbody').find('input[type=checkbox]').prop('checked', $(this).is(':checked'));
	});
});


//增加删除商品规格
$(function(){
	var $standardAdded = $('#standardAdded');
	$standardAdded.hide();
	var $standardDelete = $('.standard-delete');
	$('#createStandard').click(function(){
		var standardNameVal = $('#standardName').val();
		var standardsVal = $('#standards').val();
		if(standardNameVal == '' || standardsVal == ''){
			alert('请完善商品规格信息！');
		}else{
			var $standardTable = $(this).parents('table');
			var $newStandard = $standardAdded.clone(true).show().appendTo($standardTable);
			$newStandard.find('input[name=newStandardName]').val(standardNameVal);
			$newStandard.find('input[name=newStandards]').val(standardsVal);
			$newStandard.find('input[name=newStandards]').each(function(){  
			   if($(this).val() == ''){
				   $(this).hide(); 
			   }else{
				   var textWidth = function(text){ 
						var sensor = $('<div>'+ text +'</div>').css({display: 'none'}); 
						$('body').append(sensor); 
						var width = sensor.width();
						sensor.remove(); 
						return width;
					};
					$(this).width(textWidth($(this).val())); 
			   }
			}); 
		}
		window.parent.TuneHeight();
	});	
	$standardDelete.click(function(){
		$(this).parents('tr').remove();
		window.parent.TuneHeight();
		return false;
	});
});

//增加删除商品参数
$(function(){
	var $paramAdded = $('#paramAdded');
	$paramAdded.hide();
	var $paramDelete = $('.param-delete');
	$('#createParam').click(function(){
		var paramNameVal = $('#paramName').val();
		var paramsVal = $('#params').val();
		if(paramNameVal == '' || paramsVal == ''){
			alert('请完善商品参数信息！');
		}else{
			var $paramTable = $(this).parents('table');
			var $newParam = $paramAdded.clone(true).show().appendTo($paramTable);
			$newParam.find('input[name=newParamName]').val(paramNameVal);
			$newParam.find('input[name=newParams]').val(paramsVal);
			$newParam.find('input[name=newParams]').each(function(){  
			   if($(this).val() == ''){
				   $(this).hide(); 
			   }else{
				   var textWidth = function(text){ 
						var sensor = $('<div>'+ text +'</div>').css({display: 'none'}); 
						$('body').append(sensor); 
						var width = sensor.width();
						sensor.remove(); 
						return width;
					};
					$(this).width(textWidth($(this).val())); 
			   }
			}); 
		}
		window.parent.TuneHeight();
	});	
	$paramDelete.click(function(){
		$(this).parents('tr').remove();
		window.parent.TuneHeight();
		return false;
	});
});

//商品推广
$(function(){
	var $itemPushCheck = $('.item-push-check');
	var $itemPush = $('.item-push');
	$itemPush.hide();
	$itemPushCheck.change(function(){
		if($(this).is(':checked')){
			$itemPush.show();
		}else{
			$itemPush.hide();
		}
	window.parent.TuneHeight();
	});
});

//上传图片前预览缩略图
$(".uploadImage").on("change", function(){
  var files = !!this.files ? this.files : [];
  if (!files.length || !window.FileReader) return;
  var $this = $(this);
  if (/^image/.test( files[0].type)){
      var reader = new FileReader();
      reader.readAsDataURL(files[0]);
      reader.onloadend = function(){
	    $this.prev(".uploadPreview").css("background-image", "url("+this.result+")");
      }
  }
});


//新增上传图片前预览缩略图
$(".uploadImage01").on("change", function(){
	  var files = !!this.files ? this.files : [];
	  if (!files.length || !window.FileReader) return;
	  var $this = $(this);
	  console.log(11,files);
	  if (/^image/.test( files[0].type)){
	      var reader = new FileReader();
	      reader.readAsDataURL(files[0]);
	      reader.onloadend = function(){
		    $this.parent(".uploadPreview01").css("background-image", "url("+this.result+")");
	      }
	  }
});



//上传图片预览
$(".uploadImage02").change(function() {
	var $pic = $(this).siblings(".imgPreview");
	var	$file = $(this);
	var maxSize = $file.attr('data-maxsize');
 
	var browserVersion = window.navigator.userAgent.toUpperCase();
	var ext=$file.val().substring($file.val().lastIndexOf(".")+1).toLowerCase();
 
	 // gif在IE浏览器暂时无法显示
	 if(ext!='png'&&ext!='jpg'&&ext!='jpeg'){
		 alert("图片的格式必须为png或者jpg或者jpeg格式！"); 
		 return;
	 }
	 var isIE = navigator.userAgent.match(/MSIE/)!= null;
 
	 if(isIE) {
		$file.select();
		document.getElementById("picDiv").focus();

		var reallocalpath = document.selection.createRange().text;
 
		// IE设置img的src为本地路径可以直接显示图片
		$pic.attr('src', reallocalpath);
	 }else {			
		var file = $file[0].files[0];
		var reader = new FileReader();
		 
		if (maxSize && file.size >= maxSize*1024) {
			$.Showmsg('图片过大，应小于' + maxSize + 'k');
			$file.parent().siblings('.img-upload-val').val('');
			$file.val('');
			return;
		}
		 
		 reader.readAsDataURL(file);
		 reader.onload = function(e){
			 $pic[0].src=this.result;
		 }
	 }
});
$(".item-upload-img .uploadImage02, .item-upload-img .uploadImage01").change(function() {	
	var $imgUploadVal = $('.img-upload-val');
	var uploadNum = 0;
	if($(this).val() !== ''){
		uploadNum += 1;
		$imgUploadVal.val(uploadNum);
	}
	$('.inputform').Validform().check(false,$imgUploadVal);
});	

$(".item-upload-img-new .uploadImage02, .item-upload-img-new .uploadImage01").change(function() {		 
	var $imgUploadVal = $(this).parents('.item-upload-img-new').siblings('.img-upload-val');
	var uploadNum = 0;
	if($(this).val() !== ''){
		uploadNum += 1;
		$imgUploadVal.val(uploadNum);
	}
	$('.inputform').Validform().check(false,$imgUploadVal);
});	

//动态改变页面高度
function TuneHeight() {
	var newHeight = $("#mainFrame").contents().find('.info').height() + 110;
	var windowHeight = $(window).height() - 76;
	$("#mainFrame").height(newHeight < windowHeight ? windowHeight : newHeight);
}

//jQuery plugin to prevent double submission of forms
jQuery.fn.preventDoubleSubmission = function() {
  $(this).on('submit',function(e){
    var $form = $(this);
   // if($form.Validform({}).getStatus() == 'posting'){
	    if ($form.data('submitted') === true) {
	      // Previously submitted - don't submit again
	      e.preventDefault();
	    } else {
	      // Mark it so that the next submit can be ignored
		    if(!$form.hasClass('inputform')){//如果不是inputform表单，意味着没有做重复提交校验，这里加上控制
		    	$form.data('submitted', true);
		    }
	    }
	//}
  });

  // Keep chainability
  return this;
};

//如果不是inputform表单，意味着没有做重复提交校验，这里加上控制
$('form').not(".inputform").preventDoubleSubmission();

//全选
$(function(){
	$('#checkAll').click(function(){
		if($(this).is(':checked')){
			$(this).parents('tr').siblings('tr').find('input[type=checkbox]').prop('checked', true);
		}else{
			$(this).parents('tr').siblings('tr').find('input[type=checkbox]').prop('checked', false);
		}
	});
	//输入框限制字数
	$('.textareas.txt02').keyup(function(){
		var valLength = $.trim($(this).val()).length;
		var currentVal = $.trim($(this).val());
		if(valLength > 99){
			$(this).val(currentVal.substring(0,100));	
		}
		var num = 100 - valLength;
		if(num < 0){
			num = 0;
		}
		$('.leftNum').text(num);
	});
	
	//轻应用活动运营
	$('.prize-single').click(function(){
		var $thisArrow = $(this).find('.prize-list-arrow');
		var $thisCheck = $(this).find('.optionCheckBox');
		if($thisCheck.is(":checked")){
			$(this).next('.prize-info').find('.input_text').attr('ignore', '');
		}else{
			$(this).next('.prize-info').find('.input_text').attr('ignore', 'ignore');
		}
		$(this).next('.prize-info').stop(true, false).animate({'height':'toggle'}, 200);
		$thisArrow.swapClass('rotateIn', 'rotateOut');
		setTimeout(function(){
			window.parent.TuneHeight();
		},201);
	});
	
	//选择派奖城市
	$('.prizeAreaSelect').change(function(){
		var curVal = $(this).val();
		if(curVal == 2){
			$('#citySelectBtn').show();
		}else{
			$('#citySelectBtn').hide();
		}
	});
	
	//日期选择器禁止手动输入
	//$('input.icon_dt').attr('readonly','readonly');
	
	//修复日期选择，延迟验证
	/*$('.input_text.icon_dt').keyup(function(){*/
	$('.input_text.icon_dt:not(".nocheck"):not(".info-list .input_text"), .input_text.date_picker:not(".nocheck")').blur(function(){
		var $this = $(this);
		setTimeout(function(){
			$(".inputform").Validform({}).check(false,$this);
		},600);
	});
	
});

function initJson(data){
	try {
		data = eval(data);
	} catch (e) {
		data = eval("("+data+")");
	}
}

//日期格式化
Date.prototype.format = function (format) {  
    var o = {  
        "M+": this.getMonth() + 1, //month  
        "d+": this.getDate(), //day  
        "h+": this.getHours(), //hour  
        "m+": this.getMinutes(), //minute  
        "s+": this.getSeconds(), //second  
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter  
        "S": this.getMilliseconds() //millisecond  
    };  
    if (/(y+)/.test(format))  
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
    for (var k in o)  
        if (new RegExp("(" + k + ")").test(format))  
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
    return format;  
};

//判断图片是否存在，不存在则替换默认图片
function imgNotFind(){
	var img = event.srcElement;
	img.src = '../images/head-img.jpg';
}

//获取当前的日期时间 格式“yyyy-MM-dd HH:MM:SS”
function getNowFormatDate() {
	var date = new Date();
	var seperator1 = "-";
	var seperator2 = ":";
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	var strHours = date.getHours();
	var strMin = date.getMinutes();
	var strSec = date.getSeconds();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	if (strHours >= 0 && strHours <= 9) {
		strHours = "0" + strHours;
	}
	if (strMin >= 0 && strMin <= 9) {
		strMin = "0" + strMin;
	}
	if (strSec >= 0 && strSec <= 9) {
		strSec = "0" + strSec;
	}
	var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
			+ " " + strHours + seperator2 + strMin
			+ seperator2 + strSec;
	return currentdate;
}

//选择下拉框，切换对应内容
function selectOptionChange(obj){
	$(obj).change(function(){
		var $checkTrCon = $(this).parent('td').parent('tr').nextAll('tr.swap-info');
		var thisVal = $(this).val();
		var $thisSwapVal = $(this).parent('td').parent('tr').nextAll('.swap-val-' + thisVal);
		
		$checkTrCon.hide();
		$checkTrCon.find('input[type=hidden]').attr('ignore', 'ignore');
		if($thisSwapVal.length){
			$thisSwapVal.show();
			$thisSwapVal.find('input[type=hidden]').removeAttr('ignore');
		}
		window.parent.TuneHeight();
	});
}

//select切换	//不在同一个table
function selectChangePlus(obj, swapClass){
	$(obj).change(function(){
		var thisVal = $(this).val();
		var $swapObj = $(swapClass + '-' + thisVal);
		
		$(swapClass).hide().find('input,select').attr('ignore', 'ignore');
		if($swapObj.length){
			$swapObj.show().find('input,select').attr('ignore', '');
		}
		window.parent.TuneHeight();
	});
}

//校验时间先后顺序
$.fn.compareTime = function(startTimeObj, endTimeObj){
	var $self = $(this);
	//不能使用blur，否则只能触发第一次校验
	$self.keyup(function(){
		var startTime = $(startTimeObj).val();
		var endTime = $(endTimeObj).val();
		if(startTime != '' && endTime != '' && startTime >= endTime){
			alert('结束时间须大于起始时间！');
			$(this).val('');
		}
	});
};

//radio单选切换
function radioChange(obj){
	$(obj).click(function(){
		var $checkTrCon = $(this).parents('tr').nextAll('tr.swap-con');
		var thisVal = $(this).attr('data-val');
		var $thisSwapVal = $(this).parents('tr').nextAll('.swap-val-' + thisVal);

		$checkTrCon.hide();
		$checkTrCon.find('input[type=text]').attr('ignore', 'ignore');
		$checkTrCon.find('input[type=radio]').prop('checked', false);
		$checkTrCon.find('input[type=checkbox]').prop('checked', false);
		$checkTrCon.find('.Validform_checktip').attr('class', 'Validform_checktip').text('');

		if($thisSwapVal.length){
			$thisSwapVal.show();
			$checkTrCon.find('input[type=text]').removeAttr('ignore');
		}
		window.parent.TuneHeight();
	});
}

/*=============================================display分页跳转到指定页 begin=============================================*/
function goPage() {
	var page = $.trim($("#tz").val())*1; //当前页
	var total = Math.ceil($.trim($('.pagebanner font').eq(0).text())*1/10); //总页数
	
	if (page === '' || isNaN(page)) {
		alert("输入数字非法，请重新输入！");
		return false;
	}

	if (page > total) {
		alert("超出了最大页数，请重新输入！");
	} else if (page < 1) {
		alert("页数不能小于1！");
	} else {
		var goUrl = $(".pagelinks a").eq(0).attr('href');//找到分页信息中链接页
		var goToUrl = goUrl.replace(/-p=\d+/, "-p="+page);//用正则方式替换为跳转页
		
		if(goToUrl.indexOf("?") == 0){
			goToUrl = 'http://' + location.host + location.pathname + goToUrl;
		}
		$(window.parent.document).find('#mainFrame').attr('src', goToUrl);
	}
}
/*=============================================display分页跳转到指定页 end=============================================*/
//类目管理
$(function(){
	var $sortTable = $('#sortTable');
	var $newSort1InfoTr = $('.new-sort1-info-tr.dsn');
	var $newSort2InfoTr = $('.new-sort2-info-tr.dsn');
	var $sortRanking = $('.sort-ranking');
	var $sortTitle = $('.sort-title');
	
	$('.add-sort1-btn').click(function(){
		if($(".new-sort1-info-tr:visible").length>=10){
			alert("收费项最多只能配置10个！！！");
		} else {
			$newSort1InfoTr.clone(true).removeClass('dsn').appendTo($sortTable);
			window.parent.TuneHeight();
		}
	});
	
	$('.add-sort2-btn').click(function(){
		var tdRowspan = $(this).parents('td').attr('rowspan');
		if(tdRowspan == 1){
			$newSort2InfoTr.clone(true).removeClass('dsn').insertAfter($(this).parents('tr'));
			$(this).parents('td').attr('rowspan', tdRowspan*1 + 1);
			$(this).parents('td').siblings('td.sort-ranking').attr('rowspan', tdRowspan*1 + 1);
		}else{
			$newSort2InfoTr.clone(true).removeClass('dsn').insertAfter($(this).parents('tr').nextAll('tr').eq(tdRowspan - 2));
			$(this).parents('td').attr('rowspan', tdRowspan*1 + 1);
			$(this).parents('td').siblings('td.sort-ranking').attr('rowspan', tdRowspan*1 + 1);	
		}
		window.parent.TuneHeight();
	});
	
	$(document).on('click', '.de-sort1-btn', function(){
		var tdRowspan = $(this).parents('td').attr('rowspan');
		if(confirm('确定删除该一级类目？')){
			if(tdRowspan == 1){
				$(this).parents('tr').remove();
			}else{
				$(this).parents('tr').nextAll('tr.new-sort2-info-tr:lt( '+tdRowspan+' )').remove();
				$(this).parents('tr').remove();
			}
		}
		window.parent.TuneHeight();
	});
	
	$(document).on('click', '.de-sort2-btn', function(){
		var $thisSortParents = $(this).parents('tr').prevAll('.new-sort1-info-tr').eq(0);
		var tdRowspan = $thisSortParents.children('td').eq(0).attr('rowspan');
		if(confirm('确定删除该二级类目？')){
			$(this).parents('tr').remove();
			$thisSortParents.children('td').eq(0).attr('rowspan', tdRowspan - 1);
			$thisSortParents.children('td').eq(1).attr('rowspan', tdRowspan - 1);
		}
		window.parent.TuneHeight();
	});
	
	//修改类目状态
	$('.status-btn').click(function(){
		if(confirm('确定修改该类目状态？')){
			$(this).siblings('span').swapClass('black alive', 'grey asleep').swapText('启用', '禁用');
		}
	});
	
	//设置表格适应宽度
	autoFixTableWidth([".info-list",".info-list-01",".info-list-02",".info-list-03"]);
	
	//判断安卓ios系统
	var u = navigator.userAgent, app = navigator.appVersion;
	var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	
	if(isiOS){
		$('.info, .main-right, .main-con').addClass('ios-scroll-fixed'); 
		
		$(document).on('click', '.menu-arrow01, .menu-arrow', function(){
			setTimeout(function(){
				$("#mainFrame").contents().find('.info:visible').width($('.main-con').width()-30);
			},301);
		});
	}
});

//设置表格适应宽度
function autoFixTableWidth(array){
	//ie9及以下版本，不支持table横向滚动条
	var browser = navigator.appName;
	var b_version = navigator.appVersion;
	var version = b_version.split(";");
	
	if(navigator.userAgent.indexOf("MSIE")>-1){
		var trim_Version = version[1].replace(/[ ]/g, "");
	}
	//ie9
	if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE9.0") {
		setInfoMinWidth([".info-list",".info-list-01",".info-list-02",".info-list-03"]);
		return;
	}
	//ie6-8
	if (!$.support.leadingWhitespace) { 
		setInfoMinWidth([".info-list",".info-list-01",".info-list-02",".info-list-03"]);
		return;
	}
	for(i=0; i<array.length; i++){
		$(array[i]).addClass('width-auto-fix');
		if($(array[i]).width() > $(array[i]).find('tr').width()){
			$(array[i]).removeClass('width-auto-fix').attr({'cellspacing':'0'});
		}
	}
}
//兼容小屏幕老旧浏览器- -
function setInfoMinWidth(array){
	for(i=0; i<array.length; i++){
		if($(array[i]).length && $(array[i]).width() < $(array[i]).find('tr').width()){
			$(array[i]).width($(array[i]).find('tr').width());
		}
		if($(array[i]).length && $('.info').width() < $(array[i]).width()){
			$('.info').css({'width':'94%', 'overflow-x':'scroll', 'padding-right':'10px'});
		}
	}
}

function getUrlParam(name) {
	//构造一个含有目标参数的正则表达式对象  
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	//匹配目标参数  
	var r = window.location.search.substr(1).match(reg);
	//返回参数值  
	if (r != null)
		return unescape(r[2]);
	return null;
}

//字数统计 obj：统计对象；leftBox：剩余字数容器；num：剩余字数
function countTextNum(obj, leftBox, maxNum){
	$(obj).keyup(function(){
		var valLength = $.trim($(this).val()).length;
		var currentVal = $.trim($(this).val());
		if(valLength > maxNum - 1 ){
			$(this).val(currentVal.substring(0,maxNum));	
		}
		var num = maxNum - valLength;
		if(num < 0){
			num = 0;
		}
		$(leftBox).text(num);
	});
}