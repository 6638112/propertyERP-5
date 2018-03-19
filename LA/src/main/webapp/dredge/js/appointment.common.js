//预约页面
$(function(){
	
	//插入已选商品信息
	var productInfo = JSON.parse(localStorage.productInfo);
	$('[name=dredgeProductId]').val(JSON.stringify(productInfo.dredgeProductId));
	$('[name=specList]').val(JSON.stringify(productInfo.specList));
	$('[name=productList]').val(JSON.stringify(productInfo.productList));
	
	//提交预约表单
	function submitMaintainForm(){

		var ajaxURL= "addDredgeBill.do";       
		$(".inputform").ajaxSubmit({
            url: ajaxURL,//ajax调用微信统一接口获取prepayId
            cache: false,
		    dataType:"json",
			async:true,
			beforeSend: function(){
				//$.Showmsg('信息提交中，请稍后…'); 
				$('.pop-tips1').removeClass('hide');
				$(".btnSubmit").attr('disabled', 'disabled');
			},
			success: function(data){
				
				var dataValue = data.dataValue;
				
				if (data.status == '0000' ) {
					//已成功提交，设置issubmited为true
					issubmited = true;
					
					//修改历史记录，完成后，后退回到我的订单首页
					history.replaceState({}, '我的订单', 'myAppointment.do');
					
					//检查是否第一次预约上门维修
					checkIsFirstPay(dataValue.needPay, dataValue.dredgeBillId);
				} else {
					$('.pop-tips1').addClass('hide');
					$.Showmsg(data.message); 
					$(".btnSubmit").removeAttr('disabled');
					return;
				}
			},  
			error: function(){
				$('.pop-tips1').addClass('hide');
				$.Showmsg('网络不给力，请稍后重试'); 
				$(".btnSubmit").removeAttr('disabled');
			}
		});
	}
	
	//表单验证
	$(".inputform").Validform({
		tiptype:1,
		btnSubmit:".btnSubmit",
		dragonfly:true,
		ajaxPost: false,
		postonce:false,
		beforeSubmit:function(){
			
			//如果需要验证码，则先校验验证码是否正确，再提交
			if($('.valid-code-box').is(':visible')){
				$.ajax({
					url: "../common/toUrl.do",
					cache: false,
					dataType:"json",
					data:{"detailUrl":"/login/isVarifyCodeCorrect.json", "mobile":$('#validPhoneNum').val(), "verifyCode":$('#verifyCode').val(), "type":7},
					success:function(data){
						if(data.status =="0000"){
							//提交预约表单
							submitMaintainForm();
						}else{
							//失败 
							$.Showmsg(data.message);
							return false;
						}
					},  
					error: function(){  
						$.Showmsg('网络不给力，请稍后重试'); 
						return false;
					} 
				});
				
			//不需要验证码，直接提交
			}else{
				submitMaintainForm();
			}
			
		},
		callback:function(data){
			return;
		}
	});
	
	//同意上门服务条款
	$('.password-check-box.accept').click(function(){
		$(this).toggleClass('on');
	});
	
	
	//选择日期
	var currYear = (new Date()).getFullYear();	
	var opt={};
	opt.date = {preset : 'date'};
	opt.datetime = {preset : 'datetime'};
	opt.time = {preset : 'time'};
	opt.default = {
		theme: 'android-ics light', //皮肤样式
        display: 'modal', //显示方式 
        mode: 'scroller', //日期选择模式
		dateFormat: 'yyyy-mm-dd',
		lang: 'zh',
		showNow: true,
		nowText: "今天",
        startYear: currYear, //开始年份
        endYear: currYear + 1 //结束年份
	};

  	var optDateTime = $.extend(opt['datetime'], opt['default']);
    $("#appDateTime").mobiscroll(optDateTime).datetime(optDateTime);
    
    
    
    //判断预约时间，冒泡触发外层容器
    $(document).on('click', '.dwbw.dwb-s', function(){
    	var curDate = new Date(),
    		curTime = getNowFormatDate().substring(0,16),
    		selectTime = $('#appDateTime').val();

    	if(selectTime < curTime){
    		$('#appDateTime').val('');
    		$.Showmsg('预约时间须晚于当前时间');
    	}
    });
    //利用遮罩层触发确定按钮
    $(document).on('click', '.opcityBtn', function(){
		$('.dwbw.dwb-s span.dwb').click();
    });

    
    //获取当前的日期时间 格式“yyyy-MM-dd HH:MM:SS” 
    function getNowFormatDate() {
		var date = new Date();
		var seperator1 = "-";
		var seperator2 = ":";
		var month = date.getMonth() + 1;
		var strDate = date.getDate();
		var strHours = date.getHours();
		var strMin = date.getMinutes();
		if (month >= 1 && month <= 9) {
			month = "0" + month;
		}
		if (strDate >= 0 && strDate <= 9) {
			strDate = "0" + strDate;
		}
		if (strHours >= 0 && strHours <= 9) {
			//strHours = strHours + 4;
			strHours = "0" + strHours;
		}else{
			//strHours = strHours + 4;
			strHours = strHours;
		}
		if (strMin >= 0 && strMin <= 9) {
			strMin = "0" + strMin;
		}
		var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
				+ " " + strHours + seperator2 + strMin
				+ seperator2 + date.getSeconds();
		return currentdate;
	}
    
	//获取验证码，验证手机号码
	$(".checkPhoneNumBox").Validform({
		tiptype:1,
		btnSubmit:".get-code-btn",
		beforeSubmit:function(curform){
			//验证手机号码通过，不提交
			$('.get-code-btn').removeClass('on');
			
			getValidCode();
			
			settime('.get-code-btn');
			
			return false;
		}
	});
    
	//60秒倒计时，重新获取验证码
	var countdown = 60;
	function settime(obj) {
		if (countdown == 0) {
			$(obj).attr("disabled",false).val("获取验证码").addClass('on'); 
			countdown = 60;
		} else {
			$(obj).attr("disabled", true).val("重新发送(" + countdown + ")");
			countdown--;
			setTimeout(function() {
				settime(obj)
			}, 1000)
		}
	}
	
	//设置子类ID subTypeId
	$('#subTypeId').val(getUrlParam('subTypeId'));
	
	//校验手机号是否存在
	checkDefaultPhoneNum();
	
	$('#validPhoneNum').keyup(function(){
		var thisVal = $.trim($(this).val());
		var defaultNum = $(this).attr('data-default-num');

		if(thisVal.length === 11){
			if($('.inputform').Validform().check(false, $(this)) && thisVal === defaultNum){
				$('.valid-code-box').addClass('dsn');
				$('#verifyCode').attr('ignore', 'ignore');
			}else if($('.inputform').Validform().check(false, $(this)) && thisVal !== defaultNum){
			    $('.valid-code-box').removeClass('dsn');
			    $('#verifyCode').attr('ignore', '');
			}else{
				$.Showmsg('请输入正确的手机号码格式！');
			}
		}
	});
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//预定耗材选择 进入02
	$("#itemSelectBtn").click(function(){
		var $this = $(this);
		//插入当前step
		setCurStep($this);
		
		$('#bookStep01').addClass('dsn');
		$('#bookStep02').removeClass('dsn');
	});
	
	//确认耗材选择 进入01
	$("#itemHasSelectedBtn").click(function(){
		
		var $this = $(this);
		//插入当前step
		setCurStep($this);
		
		$('#bookStep02').addClass('dsn');
		$('#bookStep01').removeClass('dsn');
		
		//获取已选中耗材数量及价格，计算总额
		getItemSelectedNumAndPrice();
		
		//获取已选中商品id及数量
		getItemSelectedInfo();
		
		//记录已选商品
		setItemSelectedLocalStorage();
	});

	//取消耗材选择 进入01
	$('#itemCancelBtn').click(function(){
		var $this = $(this);
		//插入当前step
		setCurStep($this);
		
		$('#bookStep02').addClass('dsn');
		$('#bookStep01').removeClass('dsn');
	});

	//下一步 进入03
	$('#bookNextBtn').click(function(){
		var $this = $(this);
		//插入当前step
		setCurStep($this);
		
		$('#bookStep01').addClass('dsn');
		$('#bookStep03').removeClass('dsn');
	});
	
	if (history.pushState) {
	    window.addEventListener("popstate", function() {
	        readCurStep();                            
	    });
	    
	    // 默认载入
	    readCurStep();
	}
	
	//增加耗材明细20170322
	$('#maintainTypeName').text(localStorage.maintainTypeName);
	$('#smallTypeName').text(localStorage.smallTypeName);
	$('#selectedTypePrice').text(localStorage.selectedTypePrice);
	
});

//解析location.search值
function getUrlParam(name) {
	//构造一个含有目标参数的正则表达式对象  
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	//匹配目标参数  
	var r = window.location.search.substr(1).match(reg);
	//返回参数值  
	if (r != null)
		return r[2];
	return null;
}

//获取验证码
function getValidCode(){
	jQuery.ajax({
		  url: "../common/toUrl.do",
		  cache: false,
		  dataType:"json",
		  data:{"detailUrl":"/login/getValidateCode.json", "mobile":$('#validPhoneNum').val(), "type":7, "isNeedLogin":"0"},
		  success:function(data){
			if(data.status =="0000"){
				// 成功
				$.Showmsg('验证码已成功发送！');
			}else{
				//失败 
				$.Showmsg(data.message);
			}
		  },  
          error: function(){  
          	$.Showmsg('网络不给力，请稍后重试'); 
          } 
	});
}

//检查是否有填写过手机
function checkDefaultPhoneNum(){
	jQuery.ajax({
		  url: "../common/toUrl.do",
		  cache: false,
		  dataType:"json",
		  data:{"detailUrl":"/dredge/getLastDredgeCellphone.json"},
		  success:function(data){
			  if(data.status =="0000"){
				  $('#validPhoneNum').val(data.dataValue.lastCellphone).attr('data-default-num', data.dataValue.lastCellphone);
				  $('.valid-code-box').addClass('dsn');
				  $('#verifyCode').attr('ignore', 'ignore');
			  }else{
				  $('.valid-code-box').removeClass('dsn');
				  $('#verifyCode').attr('ignore', '');
			  }
		  },  
        error: function(){  
      	  $.Showmsg('网络不给力，请稍后重试'); 
        } 
	});	
}

function isImgLoaded(){
	//判断图片是否加载完成
	var t_img; // 定时器
	var isLoad = true; // 控制变量
	// 判断图片加载状况，加载完成后回调
	isImgLoad(function(){
		//弹出层图片居中
		var tipsBoxHeight = $('.pop-box02').height();
		var tipsBoxImgHeight = $('.pay-tips img').height();
		$('.pay-tips').css('margin-top', (tipsBoxHeight-tipsBoxImgHeight)/2);
	});
	
	// 判断图片加载的函数
	function isImgLoad(callbackFun){
		// 查找所有封面图，迭代处理
		$('.pop-box02').each(function(i){
			// 找到为0就将isLoad设为false，并退出each
			var itemListPicHeight = $(this).find('img').height();
			var itemListPicSrc = $(this).find('img').attr('src');
			if(itemListPicHeight === 0){
				isLoad = false;
				return false;
			}
		});
		// 为true，没有发现为0的。加载完毕
		if(isLoad){
			clearTimeout(t_img); // 清除定时器
			// 回调函数
			callbackFun();
		// 为false，因为找到了没有加载完成的图，将调用定时器递归
		}else{
			isLoad = true;
			t_img = setTimeout(function(){
				isImgLoad(callbackFun); // 递归扫描
			},500); // 我这里设置的是500毫秒就扫描一次，可以自己调整
		}
	}
}

//检查是否第一次预约上门维修
function checkIsFirstPay(needPay,id){
	jQuery.ajax({
		  url: "../common/toUrl.do",
		  cache: false,
		  dataType:"json",
		  data:{"detailUrl":"/dredge/isFirstPayDredge.json"},
		  success:function(data){
			  if(data.status =="0000" && data.dataValue.isFirstPayDredge){
				  $('#wrapBox').addClass('heightp100');
				  $('.pop-box02').removeClass('dsn');
				  isImgLoaded();

				  //首次预约提示，提示后跳转
				  $('.pay-tips').click(function(){
					  isNeedPay(needPay, id); 
				  });
				  
			  //非首次预约直接跳转
			  }else{
				  isNeedPay(needPay, id);
			  }
		  },  
          error: function(){  
        	  $.Showmsg('网络不给力，请稍后重试'); 
          } 
	});	
	
	function isNeedPay(needPay, id){
		if(needPay){
			window.location.href = "../cart/prepareForPay.do?id=" + id; 
		}else{
			window.location.href = "myAppointment.do";
		}
	}
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//设置当前步骤为显示状态
function readCurStep(){
	var stepNum;
	
	//加载时显示默认步骤。如果存在浏览记录，则按照浏览记录显示
	if(location.hash){
		stepNum = location.hash.substring(9);
	}else{
		//否则显示第一步	
		stepNum = '01';
	}
	//页面加载时触发默认显示标签
	$('#bookStep01, #bookStep02, #bookStep03').addClass('dsn');
	$('#bookStep' + stepNum).removeClass('dsn');
	
}


function setCurStep(obj){
	var thisStep = $(obj).attr('data-step');
	var	state = {
			'url':'#stepNum=' + thisStep
		};
	//插入当前step
	history.pushState(state, '', state.url); 
}	
//获取已选中耗材数量及价格，计算总额
function getItemSelectedNumAndPrice(){
	var selectedNum = 0;
	var selectedTotalPrice = 0;
	
	$('.order-info-box').each(function(){
		var $me = $(this);
		if($me.find('.item-check-box.single-check').hasClass('item-checked')){
			var thisPrice = $me.find('.single-item-price').text()*1;
			var thisNum = $me.find('.itemNum').val()*1;
			selectedNum += thisNum;
			selectedTotalPrice += thisPrice*thisNum;
		}
	});
	
	//插入已选中耗材信息
	$("#consumableName").text('耗材费');
	$("#consumableInfo").text(selectedNum + '件，' + '合计￥' + selectedTotalPrice.toFixed(2));
}

//获取已选中商品信息
function getItemSelectedInfo(){
	var totalItemSelectedInfo = [];
	
	//增加耗材明细20170322
	var $itemSelectedBox = $('.item-selected-box.hide');
	$('#itemSelectedWrap').removeClass('hide').html('');
	$('.type-selected-box').addClass('borderbottomgrey');
	
	//获取已选中耗材id及数量
	$('.order-info-box').each(function(){
		var singleItemInfo = {};
		var $this = $(this);
		var $thisCbox = $this.find('.item-check-box.single-check');
		
		if($thisCbox.hasClass('item-checked')){
			var thisId = $this.attr('data-itemid');
			var thisNum = $this.find('.itemNum').val()*1;
			singleItemInfo.productId = thisId;
			singleItemInfo.productQty = thisNum;
			totalItemSelectedInfo.push(singleItemInfo);
			
			//增加耗材明细20170322
			var $itemSelectedBoxClone = $itemSelectedBox.clone(true);
			var thisName = $this.find('.single-item-name').text();
			var thisPrice = $this.find('.single-item-price').text();
			$itemSelectedBoxClone.find('.item-selected-name').text(thisName);
			$itemSelectedBoxClone.find('.item-selected-price').text(thisPrice);
			$itemSelectedBoxClone.find('.item-selected-num').text(thisNum);
			$('#itemSelectedWrap').append($itemSelectedBoxClone.removeClass('hide'));
			
		}
	});
	
	//增加耗材明细20170322
	if($('#itemSelectedWrap').html() === ''){
		$('#itemSelectedWrap').addClass('hide');
		$('.type-selected-box').removeClass('borderbottomgrey');
	}
	
	$('#itemSelectedInfo').val(JSON.stringify(totalItemSelectedInfo));
}
