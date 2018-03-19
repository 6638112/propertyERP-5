$(function(){
    
	//校验
	function validInput(){
		var amountVal = $.trim($('.input-amount').val());
		var phoneVal = $.trim($('.input-phone').val());
		var regMphone = /^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|17[0-9]{9}$|18[0-9]{9}$/;
		var regAmount = /^[0-9]+(.[0-9]{0,2})?$/;
		
		if(phoneVal == ''){
			showMsg('请填写电话号码');
			return false;
		}else if(!regMphone.test(phoneVal)){
			showMsg('请填写正确的手机格式');
			return false;
		}else if(amountVal == ''){
			showMsg('请填写金额');
			return false;
		}else if(!regAmount.test(amountVal)){
			showMsg('金额格式为数字，可带两位小数');
			return false;
		}else{
			$.ajax({
    			  url: "../commercialOpportunity/saveCOinfo.json",
    			  data:{"companyName" : amountVal, "linkTel":phoneVal}, 
				  dataType : "json",  
    			  success: function(data){
    			    showMsg('提交成功！')
    			  },  
                  error: function(){  
                  	showMsg('网络不给力，请稍后重试'); 
                  }
    			});
		}
	}
	
	$('.submit-btn').click(function(){
		validInput();
	});
	
	function showMsg(txt){
		$(".pop-tips-text").text(txt);
		if($(".pop-tips").is(':visible')){
			return;
		}
		$(".pop-tips").fadeIn();
		setTimeout(function(){
			$(".pop-tips").fadeOut();
		}, 2000)
	}
});