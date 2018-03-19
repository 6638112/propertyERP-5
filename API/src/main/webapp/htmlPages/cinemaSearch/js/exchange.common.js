$(function(){
	
	var num = 20;//每页显示的个数
	var n = 0;
	var m = -num;
	var $loading = $('.loading');
	//分页加载城市信息
	function ajax(pageType){
		$.ajax({
			type:"get",
			url:"js/cinema.js",
			dataType:"json",
			beforeSend:function(data){
				if($('.newLoading').length == 0){
					$loading.clone(true).addClass('newLoading').removeClass('hide').appendTo('.cinemas');
				}
			},
			success:function(data){
				var $cinemaLists = $('.cinemalists:visible');
				if(n < data.length + num && pageType=="next"){ //下一页
					if($cinemaLists.length > 0){
						n = num;
						m = 0;
						$cinemaLists.remove();	
					}else{
						n += num;
						m += num;
					}
				}
				var $cinemaBox = $('.cinemaBox.hide');
				$.each(data,function(i,val){
					if(i>=m && i<n){
						var $cinemaCityClone = $cinemaBox.clone(true);
						var cinemaCity = val.city;
						$cinemaCityClone.find('.title').children('span.left').text(cinemaCity);
						$cinemaCityClone.removeClass('hide').appendTo('.cinemas');
					}
				});
				setTimeout(function(){
					$('.newLoading').remove();
				},400);
			}
		});
	};
	
	//滚动到底部加载下一页
	$(window).scroll(function(event){
		var scrollt = document.documentElement.scrollTop + document.body.scrollTop; //获取滚动后的高度
		var docHeight = $(document).height() - $(window).height()-1; //当前文档高度
		var $cinemaLists = $('.cinemalists:visible');
		if(scrollt > docHeight && $cinemaLists.length == 0){
		 	ajax("next");
		}
	});
	
	//初始化
	$(function(){ 
		ajax("next");
	});
	
	
	//点击城市事件
	var $itemStandard = $(".item-standard .title");
	$itemStandard.click(function(){
		//动态插入影院列表
		var $cinemaBox = $(this).parents('.cinemaBox');
		var boxi = $cinemaBox.index();		
		var $cinemaList = $cinemaBox.find('.cinema-list');
		if($cinemaList.html() == ""){ //此处使用单引号''空值比对无效
			$.getJSON('js/cinema.js', function(data){
				if(data[boxi].area){
					var areaList = '';
					var $areaBox = $('<div class="areas"></div>');
					var $areaBoxClone = $areaBox.clone(true);
					
					$.each(data[boxi].area, function(areaIndex, areaNameList){
						areaList += '<span>';
						areaList += areaNameList.areaName;
						areaList += '</span>';						
					});
					var $areaNameList = $areaBoxClone.append(areaList);
					$areaNameList.find('span').eq(0).addClass('on');
					$areaNameList.insertBefore($cinemaList);
					
					var cinemaList01 = '';
					$.each(data[boxi].area[0].cinema, function(dIndex, dList){
						//过滤不支持在线订座的影院
						if(dList.TwoDsupport){
							//3D
							if(dList.threeDsupport){
								cinemaList01 += '<li>' + dList.name + '<span class="threeDicon">3D</span><br/><span class="cinema-address f12">地址：' + dList.address + '</span>' + '</li>';
							//2D
							}else{
								cinemaList01 += '<li>' + dList.name + '<br/><span class="cinema-address f12">地址：' + dList.address + '</span>' + '</li>';
							}
						}
					});
					//该区域下没有可以在线订座的影院
					if(cinemaList01 == ''){
						cinemaList01 = '<li class="grey">该区域下没有可以在线订座的影院</li>';
					}
					
					$cinemaList.html(cinemaList01);
				}else{
					var cinemaList = '';
					$.each(data[boxi].cinema, function(dIndex, dList){
						//过滤不支持在线2D订座的影院
						if(dList.TwoDsupport){
							//3D
							if(dList.threeDsupport){
								cinemaList += '<li>' + dList.name + '<span class="threeDicon">3D</span><br/><span class="cinema-address f12">地址：' + dList.address + '</span>' + '</li>';
							//2D
							}else{
								cinemaList += '<li>' + dList.name + '<br/><span class="cinema-address f12">地址：' + dList.address + '</span>' + '</li>';
							}
						}
						
					});
					//该区域下没有可以在线订座的影院
					if(cinemaList == ''){
						cinemaList = '<li class="grey">该区域下没有可以在线订座的影院</li>';
					}
					
					$cinemaList.html(cinemaList);
				}
			});
		}
		//箭头反转，切换列表
		var $arrow = $(this).find('.item-arrow-show');
		$(this).toggleClass('red');
		$arrow.swapAddClass('rotateIn', 'rotateOut');
		$(this).siblings().stop(true, false).toggle();
		$(this).parents('.sectionBox').siblings('.sectionBox').each(function(){
			var $cinemaBox = $(this).find('.cinema-list-box');
			if($cinemaBox.is(':visible')){
				$cinemaBox.toggle();	
				$(this).find('.item-arrow-show').swapAddClass('rotateIn', 'rotateOut');
				$(this).find('.title').removeClass('red');
			}
        });
	});	
	
	//切换区域
	$(document).on('click', '.areas span', function(event){
		$this = $(this);
		var i = $this.index();
		$this.addClass('on').siblings().removeClass('on');
		
		var $cinemaBox = $(this).parents('.cinemaBox');
		var boxi = $cinemaBox.index();		
		var $cinemaList = $cinemaBox.find('.cinema-list');
		$.getJSON('js/cinema.js', function(data){
			var cinemaList01 = '';
			$.each(data[boxi].area[i].cinema, function(dIndex, dList){
				//过滤不支持在线2D订座的影院
				if(dList.TwoDsupport){
					//3D
					if(dList.threeDsupport){
						cinemaList01 += '<li>' + dList.name + '<span class="threeDicon">3D</span><br/><span class="cinema-address f12">地址：' + dList.address + '</span>' + '</li>';
					//2D
					}else{
						cinemaList01 += '<li>' + dList.name + '<br/><span class="cinema-address f12">地址：' + dList.address + '</span>' + '</li>';
					}
				}
			});
						
			//该区域下没有可以在线订座的影院
			if(cinemaList01 == ''){
				cinemaList01 = '<li class="grey">该区域下没有可以在线订座的影院</li>';
			}
			
			$cinemaList.html(cinemaList01);
		});
		
	});
	
	//样式切换函数
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
	
	//快速清除搜索框
		
	var $cinemaSearch = $('.cinema-search');
	var $btnSearch = $('.btn-search');
	
	$btnSearch.click(function(){
		currentVal = $.trim($cinemaSearch.val());
		if(!currentVal == ''){
			ajaxSearch();
		}
	});	
	$cinemaSearch.on('keyup', function(event){
		var myEvent = event || window.event;
		currentVal = $.trim($cinemaSearch.val());
		if(myEvent.keyCode == 13 && !currentVal == ''){
			ajaxSearch();
		}else if(myEvent.keyCode == 8 && currentVal.length == 0 && $('.cinemas').find('.cinemaBox').length == 0){
			ajax('next');
		}
	});	
	
	$cinemaSearch.keyup(function(){
		var newcurrentVal = $.trim($(this).val());
		if(newcurrentVal == '' && $('.cinemas').find('.cinemaBox').length == 0){
			ajax("next");
		}
	});	
	
	//查询列表内容
	function ajaxSearch(){
		$.getJSON('js/cinema.js', function(data){
			
			var $cinemaLists = $('section.cinemalists.hide');
			var cinemaList = '';
			
			var $cinemaListClone = $cinemaLists.clone(true);
			
			$.each(data, function(i, ccList){	
				if(ccList.area){
					$.each(ccList.area, function(i, ssList){
						$.each(ssList.cinema, function(i, cNameList){
							//过滤不支持在线2D订座的影院
							if(cNameList.TwoDsupport){
								//3D
								if(cNameList.threeDsupport){
									if(cNameList.name.indexOf(currentVal) > -1 || cNameList.address.indexOf(currentVal) > -1){
										cinemaList += '<li>' + cNameList.name + '<span class="threeDicon">3D</span><br/><span class="cinema-address f12">地址：' + cNameList.address + '</span>' + '</li>';
									}
								//2D
								}else{
									if(cNameList.name.indexOf(currentVal) > -1 || cNameList.address.indexOf(currentVal) > -1){
										cinemaList += '<li>' + cNameList.name + '<br/><span class="cinema-address f12">地址：' + cNameList.address + '</span>' + '</li>';
									}
								}
							}
						});
					});
				}else{
					$.each(ccList.cinema, function(i, ssList){
						//过滤不支持在线2D订座的影院
						if(ssList.TwoDsupport){
							//3D
							if(ssList.threeDsupport){
								if(ssList.name.indexOf(currentVal) > -1 || ssList.address.indexOf(currentVal) > -1){
									cinemaList += '<li>' + ssList.name + '<span class="threeDicon">3D</span><br/><span class="cinema-address f12">地址：' + ssList.address + '</span>' + '</li>';
								}
							//2D
							}else{
								if(ssList.name.indexOf(currentVal) > -1 || ssList.address.indexOf(currentVal) > -1){
									cinemaList += '<li>' + ssList.name + '<br/><span class="cinema-address f12">地址：' + ssList.address + '</span>' + '</li>';
								}
							}
						}
					});
				}
			});
			if(cinemaList == ''){
				cinemaList = '<li>' + '没有找到相关内容' + '</li>';	
			}
			$cinemaListClone.find('.cinema-list').html(cinemaList);
			$('.cinemas').html($cinemaListClone.removeClass('hide'));
			$('.cinemas').find('.cinema-list li').eq(0).addClass('noborder');
		});		
	}

	
}); 