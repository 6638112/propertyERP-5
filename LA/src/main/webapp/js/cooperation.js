
$(function(){
	//校验
	function validInput(){
		var nameVal = $('.input-name').val();
		var phoneVal = $.trim($('.input-phone').val());
		var regMphone = /^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|17[0-9]{9}$|18[0-9]{9}$/;
		var regPhone = /^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$/;
		
		if(nameVal == ''){
			alert('请填写物业公司名称');
			return false;
		}else if(phoneVal == ''){
			alert('请填写电话号码');
			return false;
		}else if(!regMphone.test(phoneVal) && !regPhone.test(phoneVal)){
			alert('请填写正确的手机或电话格式');
			return false;
		}else{
			$.ajax({
	  			url: "../commercialOpportunity/saveCOinfo.json",
				cache: false,
				dataType:"json",
				data:{"companyName" : nameVal, "linkTel":$.trim($('.input-phone').val()) },
				beforeSend: function(){
					$('.submit-btn').attr('disabled', 'disabled');
				},
				success: function(data){
					alert('提交成功！');
					$('.submit-btn').removeAttr('disabled');
				},
				error: function(){
					alert('网络不给力，请稍后重试'); 
					$('.submit-btn').removeAttr('disabled');
	            }
			});
		}
	}
	
	$('.submit-btn').click(function(){
		validInput();
	});
	
});