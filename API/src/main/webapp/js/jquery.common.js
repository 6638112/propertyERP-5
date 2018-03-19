//调用页面
$(".b01").click(function(){
	if(isWeiXin()){ 
		$('.layer').show().click(function(){
			$(this).hide();
		});
	}
});
$(".b02").click(function(){
	if(isWeiXin()){ 
		$('.layer01').show().click(function(){
			$(this).hide();
		});
	}
});


function isWeiXin(){ 
var ua = window.navigator.userAgent.toLowerCase(); 
if(ua.match(/MicroMessenger/i) == 'micromessenger'){ 
return true; 
}else{ 
return false; 
} 
} 