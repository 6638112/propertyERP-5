//兑换说明
$(function(){
	
	var $itemStandard = $(".item-standard .title");
	$itemStandard.click(function(){
		var $arrow = $(this).find('.item-arrow-show');
		$arrow.swapAddClass('rotateIn', 'rotateOut');
		$(this).siblings().stop(true, false).animate({height: 'toggle'}, 300);
	});	
	
	$.fn.swapAddClass = function(class1, class2){
		return this.each(function(){
			var $elem = $(this);
			if($elem.hasClass(class1)){
				$elem.removeClass(class1).addClass(class2);
			}else if($elem.hasClass(class2)){
				$elem.removeClass(class2).addClass(class1);
			}else{
				$elem.addClass(class1);
			}
		});
	};
	
}); 

/*设置消费券高度*/
function xfquanResize(){
	var $xiaofeiquan = $('.step02 .step-box .xiaofeiquan');
	var xfqWigth = $xiaofeiquan.width();
	$xiaofeiquan.height(xfqWigth*395/300);	
}
$(function(){
	xfquanResize();
	$(window).resize(function(){
		xfquanResize();	
	});
});	

function xfquanResize01(){
	var $xiaofeiquan = $('.step03 .step-box .xiaofeiquan');
	var xfqWigth = $xiaofeiquan.width();
	$xiaofeiquan.height(xfqWigth*495/304);	
}
$(function(){
	xfquanResize01();
	$(window).resize(function(){
		xfquanResize01();	
	});
});	

function mainInfoResize(){
	var $mainInfo = $('.main-info');
	var mainInfoWidth = $mainInfo.width();
	$mainInfo.height(mainInfoWidth*1080/640);
}
$(function(){
	mainInfoResize();
	$(window).resize(function(){
		mainInfoResize();	
	});
});	

/*领取步骤*/
$(function(){
	var $getBtn = $('#getBtn');
	var $stepEnd = $('.step-end');
	
	$('#getPrize').click(function(){
		$('.pop-box, .step01').show();
		if($('.step02, .step03').is(":visible")){
			$('.step02, .step03').hide();
		}
		if(!$getBtn.is(":visible")){
			$getBtn.show();
			$stepEnd.hide();
		}
		//去领券
		$.ajax({
			type: "POST",
			url: "../prize/getHBAmount.do",
			dataType:"json",
			async:false,
			success: function(data, textStatus){
		        var hbAmount = data.dataValue.hbAmount;
		    	$('#hbAmount').val(hbAmount);
		    	$('#hbAmount1').text(hbAmount);
		    	$('#hbAmount2').text(hbAmount);		    	
		    	$("#getPrize").attr("id","hasPrize").find("img").attr("src", "images/lingquan-btn2.png");
			},
		});
	});
	
	$('.red-packet li').click(function(){
		var hbAmount = parseFloat($("#hbAmount").val());
		if (hbAmount > 0) {
			$('.step01').hide();
			$('.step02').show();
		} else {
			$('.step01').hide();
			$('.step03').show();
		}
		xfquanResize();
		xfquanResize01();
	});
	
	$getBtn.click(function(){
		$getBtn.hide();
		$stepEnd.show();
	});
	
	$('.close-btn').click(function(){
		$(this).parents('.pop-box').hide();
	});
});

//重置消费券高度
$(function(){
	function setQuanHeight(){
		var $jfqQuan = $('.jfq-quan');	
		var jfqQuanWidth = $jfqQuan.width();
		var jfqQuanHeight = jfqQuanWidth*347/640;
		$jfqQuan.css({'height':jfqQuanHeight + 'px'});
	}
	setQuanHeight();
	$(window).resize(function(){
		setQuanHeight();	
	});
});