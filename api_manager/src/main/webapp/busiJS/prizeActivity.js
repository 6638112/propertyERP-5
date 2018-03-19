function qryLeftPriOptCount(optId,spanId,basePath){
	$.ajax({
		type:"post",
		url:basePath+"/prizeActivityJson/qryLeftPriOptCount.json",
		data:{'optId': optId},
		dataType:"json",
		beforeSend:function(data){
		},
		success:function(data){
			data = eval(data);
			try {
				if (data.status == '0000') {
					$('#'+spanId).text(data.dataValue.leftCount);
				} else {
					alert(data.message);
					return;
				}
			}catch (e) {
				alert(data.message);
			}
		}
	});
}

$(function (){

	//选择派奖城市
	$('#citySelectBtn').click(function(){
		$('#change-city').removeClass('visibleHidden');
		$.layer({
			type: 1,
			shade: [0.4,'#000000'],
			area: ['auto', 'auto'],
			title: false,
			offset: ['50px',''],
			border : [5, 0.3, '#000'],
			page: {dom : '#change-city'}
		});
	});
	
	$(".hasallcity li").hover(function(){
		$(this).addClass('hover');
	}, function(){
		$(this).removeClass('hover');
	});	
	
	//选择城市
	$(".hasallcity a").click(function(event){
		var curCity = $(this).text();
		var $cityBox = $('.city-selected-box .cityName');
		var cityArray = new Array();
		if($cityBox.length > 0){
			$cityBox.each(function(index, element) {
				var thisName = $(this).text();
				cityArray.push(thisName);
				return cityArray;
			});
			
			if($.inArray(curCity, cityArray) > -1){
				alert('该城市已选中！');	
				return false;
			}else{
				selectCity();	
			}
		}else{
			selectCity();		
		}
		
		function selectCity(){
			var $newCity = $('.cityName.hide');
			var $newCityClone = $newCity.clone(true);
			
			var $cityNameList = $('input.cityNameList.hide');
			var $cityNameListClone = $cityNameList.clone();
			$cityNameListClone.val(curCity).removeClass('hide').appendTo($newCityClone);
			$newCityClone.prepend(curCity).removeClass('hide').appendTo($('.city-selected-box'));

			setCityPage();	
		}
		event.preventDefault();
	});	
	
	$(".delete-icon").click(function(){
		$(this).parent('.cityName').remove();
		
		setCityPage();
	});	
	
	function setCityPage(){
		var cityNameLength = $('.city-selected-box .cityName').length;
		$('#citySelectBtn').val('已选择' + cityNameLength + '个城市');
		
		var citySelectedBoxHeight = $('.city-selected-box').height();
		if(citySelectedBoxHeight == 0){
			citySelectedBoxHeight = 42;
		}
		$('#bdw').css('margin-top',citySelectedBoxHeight + 88);	
	}
	
	setCityPage();
	

    //校验时间选择
	/*$('.input_text.icon_dt').keyup(function(){
		var date01 = $('#date01').val();
		var date02 = $('#date02').val();
		var curDate = new Date().format("yyyy-MM-dd hh:mm");
		var thisDate = $(this).val();

		if(thisDate < curDate){
			alert('活动时间须大于当前时间！');
			$(this).val('');
		}else if(date01 != '' && date02 != '' && date01 >= date02){
			alert('结束时间须大于起始时间！');
			$('#date02').val('');
		}
	});*/
	
});
