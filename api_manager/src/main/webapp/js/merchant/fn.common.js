
//触发按钮状态
function countValNum(){
	var num = 0;
	$('.register-list li:visible').each(function(){
		if($(this).find('input').length > 0){
			var thisInputVal = $(this).find('input').val();
		}else{
			var thisInputVal = '1';
		}
		if($(this).find('select').length > 0){
			var thisSelectVal = $(this).find('select').val();
		}else{
			var thisSelectVal = '1';
		}
		if($.trim(thisInputVal) === '' || thisSelectVal === '-1'){
			num += 1;
		}
	});
	if(num == 0){
		$('.btnSubmit').addClass('bgred noborder white');
	}else{
		$('.btnSubmit').removeClass('bgred noborder white');
	}
}

//省市切换
function onSelectChange(obj,toSelId){
	setSelect( obj.options[obj.selectedIndex].getAttribute('data-id'),toSelId);
}

//省市切换详情逻辑处理
function setSelect(fromSelVal,toSelId){
	$('#' + toSelId).find('option:gt(0)').remove();//清空之前的选项
	if(toSelId === "bankCity"){//选择省，更新市
		jQuery.ajax({
			  url: "propertyCompany/getCityList.html",
			  cache: false,
			  dataType:"json",
			  async:false,
			  data:"apId="+fromSelVal,
			  success: function(data){
			    $.each(JSON.parse(data), function(i, item) {
			    	$("<option value='" + item.name + "'>" + item.name + "</option>").appendTo($("#"+toSelId));
			    });
			  }
		});
		$("#city").val(-1);
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

//设置结算密码
function checkPassword(){
	$.ajax({
		url: "ebuyMerchant/settleCenter/modifyWithdrawPassword.json",
		type: "post",
		dataType:"json",
		data:{"withdrawPassword":$('#passwordInput').val()},
		success:function(data){
			if(data.status =="0000"){
				$("#wrapBox").addClass('heightp100');
				$("#setPasswordTips").removeClass('hide');
			}else{
				$.Showmsg(data.message);
				return false;
			}
		},  
		error: function(){  
			$.Showmsg('网络不给力，请稍后重试'); 
			return false;
		} 
	});
}