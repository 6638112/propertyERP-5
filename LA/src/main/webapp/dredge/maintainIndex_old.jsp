<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>约师傅</title>
<link rel="stylesheet" href="css/shopping.common.css?v20170103">
</head>

<body>
<div class="main-part01">  
	<header class="sectionBox fantasia-header displaybox">
		<div id="cityOrientation" class="mleft10 boxflex01">
			<span class="mright5"><span id="curCity">正在定位…</span></span>
			<div class="get-city-arrow disinline"></div>
		</div>
		<a class="disblock p010 boxflex01 t-right" href="myAppointment.do">
			<img class="icon_appointment mright5" src="images/icon_appointment.png" />
			<span>我的预约</span>
		</a>
	</header>
	<section id="slideBox" class="slideBox mtop0">
	    <div class="bd noborder">
	        <ul>
	            <!-- <li><a href="javaScript:void(0)"><img src="images/main_banner.jpg" /></a></li> -->
	        </ul>
	    </div>
	    <div class="hd">
	        <ul></ul>
	    </div>
	</section>
	<section class="sectionBox">
		<ul class="maintain-menus t-center borderone">
		</ul>
	</section>
	<section id="szSaleInfo" class="sectionBox pb20 dsn">
		<section class="divide-box borderbottomgrey"></section>
		<div class="m20 f16 t-center"><img class="w24 mtop3 align-middle" src="images/sale_title_left.png" /><span class="m010">特惠精选</span><img class="w24 mtop3 align-middle" src="images/sale_title_right.png" /></div>
		<ul class="t-center displaybox f14 lineheightnormal">
			<li class="boxflex01">
				<a href="maintainTypelist.do?name=家政&parentTypeId=115&forSale=true&bigTypeName=日常保洁&smallTypeName=A1单次">
					<img src="images/sale_icon_04.jpg" /><br>单次深度保洁<br>1人4小时<br><span class="red">158元</span><!--  <span class="grey"><span class="f12 align-top">|</span> 1个</span> -->
				</a>
			</li>
			<li class="boxflex01" style="margin:0 2px;">
				<a href="maintainTypelist.do?name=清洗&parentTypeId=113&forSale=true&bigTypeName=空调清洗&smallTypeName=挂式空调">
					<img src="images/sale_icon_01.jpg" /><br>单台清洗<br>空调挂机*1<br><span class="red">68元</span><!--  <span class="grey"><span class="f12 align-top">|</span> 1个</span> -->
				</a>
			</li>
			<li class="boxflex01">
				<a href="maintainTypelist.do?name=清洗&parentTypeId=113&forSale=true&bigTypeName=空调清洗&smallTypeName=挂机*3">
					<img src="images/sale_icon_02.jpg" /><br>3台清洗套餐<br>挂机*3<br><span class="red">138元</span><!--  <span class="grey"><span class="f12 align-top">|</span> 1个</span> -->
				</a>
			</li>
		</ul>
	</section>

</div>

<div class="main-part02 dsn">    
    <section class="sectionBox item-details-info pos-relative">
        <section class="sectionBox password-mind-box bordertbgrey">
            <div class="mleft15 grey">请选择您所在省市</div>
        </section>
        <ul class="register-list">
        	  <li class="borderbottomgrey">
   					<select id="province" onchange="onSelectChange(this,'city');" class="province select_normal input-text wp100 list-arrow" name="province" data-first-title="选择省" title="选择省" >
	                   	<option value="">选择省</option>
	                   	<c:forEach items="${pcbList}" var="pcb" >
	                   		<option value="${pcb.id}">${pcb.name}</option>
                   		</c:forEach>
                   </select> 
        	  </li>   
            <li>
            	<select id="city" class="input-text wp100 list-arrow" name="city">
                      	<option value="">选择城市</option>
                </select> 
            </li>
        </ul>
    	<section class="divide-box bordertopgrey"></section>
        <div class="item-list-arrow-box p00 borderbottomgrey"><input class="btn-submit btn-next noradius bordertbgrey btnSubmit bgwhite red" type="button" name="button" value="确定"></div>
    </section>
    
</div>

<script src="${resourcePath}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePath}/commonjs/Validform_v5.3.2.js"></script>
<script src="${resourcePath}/commonjs/jquery.cookie.js"></script>
<script src="http://api.map.baidu.com/api?v=2.0&ak=BF512d9c46c962e21adf4bd17340b6cc"></script>
<script src="${resourcePath}/commonjs/TouchSlide.1.1.js"></script>
<script>
    TouchSlide({ 
        slideCell:"#slideBox",
        titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
        mainCell:".bd ul", 
        effect:"left", 
        autoPage:true,//自动分页
        autoPlay:false //自动播放
    });

	var $mainPart01 = $('.main-part01');
	var $mainPart02 = $('.main-part02');
	$(function(){
		var $li = $('#slideBox .hd ul li');
		if($li.length == 1){
			$li.hide();
		}
		
		//设置定位城市
		$('#cityOrientation').click(function(){
			$mainPart01.hide();
			$mainPart02.show();
		});
		
		$('.item-list-arrow-box').click(function(){
			var $city = $('#city');
			if($city.val() !== ''){
				var cityName = $('#city').find('option:selected').text();
				$('#curCity').text(cityName);
				//先设置10年有效
				$.cookie('cityName', cityName, { expires: 365*10, path: '/'}); 
				$mainPart02.hide();
				$mainPart01.show();
				updateCommunitySupplyTypeAfterInsertCity();
				updateADAfterInsertCity();
			}else if($city.val() == ''){
				$.Showmsg('请选择城市');
			}
		});
	});
	
	//省市切换
    function onSelectChange(obj,toSelId){
    	setSelect(obj.value,toSelId);
    }

    //省市切换详情逻辑处理
    function setSelect(fromSelVal,toSelId){
    	$('#' + toSelId).html('<option value="">选择城市</option>');//清空之前的选项
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
    		//$("#city").prepend('<option value="">选择城市</option>');
    	}	
    }
    
	//更新城市后要更新维修类别
    function updateCommunitySupplyTypeAfterInsertCity(){
    	var $ul = $("ul.maintain-menus.t-center.borderone");
    	
    	$.ajax({
			 url: "../common/toUrl.do",
			  async: true,
			  dataType:"json",
			  data:{"cityName":$("#curCity").text(),"detailUrl":"/communitySupply/qrySupplyTypeList3LevelByCityName.json",},
			  success: function(data){
				  if(data.status!="0000"){
					  $.Showmsg(data.message);
				  }else{
 					  $ul.html("");
					  
					  $.each(data.dataValue.level01List, function(i,item){
						 var li_item = '<li class="borderright"><a class="dgTypeItem" href="javaScript:void(0)" data-href="maintainTypelist.do?name='+ item.name +'&amp;parentTypeId='+ item.supplyTypeId +'" data-value="'+ item.supplyTypeId +'" onclick="return toDredageTypeList(this);"><img class="wp18" style=" min-width: 27px !important;" src="'+ item.iconUrl +'"><div>'+ item.name +'</div></a></li>';
						 if(item.code =="weixiu"){
							$ul.append(li_item);
						 }
					  });

					  //去除右侧线
					  $ul.find('li:eq(2),li:eq(5),li:eq(8),li:eq(11),li:eq(14)').removeClass('borderright');
					  //去除底部线
					  var liLength = $ul.find('li').length;
					  var liLengthLeft = liLength%3;
					  if(liLengthLeft == 0 && liLength > 3){
						  var leftGtNum = liLength - 4;
						  $ul.find('li:gt('+leftGtNum+')').addClass('noborderbottom');
					  }else if(liLengthLeft == 0 && liLength == 3){
						  $ul.find('li').addClass('noborderbottom');
					  }else if(liLengthLeft > 0 && liLength > 3){
						  var gtNum = liLength - liLengthLeft-1;
						  $ul.find('li:gt('+gtNum+')').addClass('noborderbottom');
					  }else if(liLengthLeft > 0 && liLength < 3){
						  $ul.find('li').addClass('noborderbottom');
					  }
					  
				  }
			  },
			  error: function(){  
               	$.Showmsg('网络不给力，请稍后重试'); 
             }
		});
    }
	
	function toDredageTypeList(dgTypeName){
		var cityName = $('#curCity').text();
		var cstId = $(dgTypeName).attr("data-value");
		var isOpenDredgeService = 0;
		$.ajax({
			 url: "qryOpenDredgeService.html",
			  async: false,
			  dataType:"json",
			  data:{"cstId" : cstId, "cityName":cityName,},
			  success: function(data){
				  isOpenDredgeService = data.dataValue.isOpenDredgeService;
			  }
		});
		
		if(isOpenDredgeService==0){
			window.location.href = "maintainRecruit.jsp";
		}else{
			window.location.href = $(dgTypeName).attr("data-href");
		}
			return false;
	};
	

	//更新城市后更新广告图
    function updateADAfterInsertCity(){
    	var $ul = $("#slideBox ul");
    	    	
    	$.ajax({
			  url: "../common/toUrl.do",
			  async: true,
			  dataType:"json",
			  data:{"code":"LA_WX", "city":$('#curCity').text(), "detailUrl":"/ebuyNew/qryAds.json"},
			  success: function(data){
				  if(data.status!="0000"){
					  $.Showmsg(data.message);
				  }else{
					  if(data.dataValue.list != null){
						  var li_item = '';
						  $.each(data.dataValue.list, function(i,item){
							  li_item += '<li><a href="' + item.linkUrl + '"><img src="' + item.picUrl + '" /></a></li>';
						  });  
						  $ul.html(li_item);
					  }else{
						  var li_item = '<li><a href="javaScript:void(0)"><img src="images/main_banner.jpg" /></a></li>';
						  $ul.html(li_item);
					  }
					  
					  TouchSlide({ 
						slideCell:"#slideBox",
						titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
						mainCell:".bd ul", 
						effect:"left", 
						autoPage:true,//自动分页
						autoPlay:false //自动播放
					  });
					  
						var $li = $('#slideBox .hd ul li');
						if($li.length == 1){
							$li.hide();
						}
				  }
				  var cityName = $('#curCity').text();
				  $.ajax({
						 url: "qryOpenDredgeService.html",
						  async: false,
						  dataType:"json",
						  data:{"cityName":cityName,},
						  success: function(data){
							  //isOpenDredgeService = data.dataValue.isOpenDredgeService;
						  }
					});
			  },
			  error: function(){  
               	$.Showmsg('网络不给力，请稍后重试'); 
             }
		});
    	
    	//判断当前城市，显示活动
    	if($('#curCity').text() === '深圳' || $('#curCity').text() === '深圳市'){
	    	$('#szSaleInfo').removeClass('dsn');
    	}else{
    		$('#szSaleInfo').addClass('dsn');
    	}
    }
	
	$(document).ready(function(){
		var curCityName = getUrlParam('curCity');
		if(curCityName === 'shenzhen'){
			$.cookie('cityName', '深圳市', { expires: 3650, path: '/' });
		}
		var cookie_cityName = $.cookie('cityName');
		if(cookie_cityName==null || cookie_cityName =="" || cookie_cityName== undefined){
		    autoLocation();
		}else{
			$('#curCity').html(cookie_cityName);
			updateCommunitySupplyTypeAfterInsertCity();
			updateADAfterInsertCity();
		}
	});

	//声明地址解析器
	var geoc = new BMap.Geocoder();

	//自动定位
	function autoLocation() {
		if (navigator.geolocation) { //判断浏览器是否能获取当前位置
			navigator.geolocation.getCurrentPosition(AddrSuc, AddrFail);
		}
		else {
			$.Showmsg("无法自动定位，请手动选择所在城市");
			$mainPart01.hide();
			$mainPart02.show();
		}
	}

	//获取当前坐标成功
	function AddrSuc(param) {
		var lng = param.coords.longitude;
		var lat = param.coords.latitude;
		var point = new BMap.Point(lng, lat);

		translateCallback(point);
	}

	//获取坐标失败
	function AddrFail(err) {
		$.Showmsg("无法自动定位，请手动选择所在城市");
		$mainPart01.hide();
		$mainPart02.show();
	}

	//坐标转换
	function translateCallback(point) {
		geoc.getLocation(point, function (rs) {
			var addComp = rs.addressComponents; //查询得到的地址对象组件
			$('#curCity').html(addComp.city);
			//expires单位为天，永久先设置为10年
	    	$.cookie('cityName', addComp.city, { expires: 3650, path: '/' }); 
			
			//定位到城市后，才能获取对应类别
			updateCommunitySupplyTypeAfterInsertCity();
			updateADAfterInsertCity();
		});
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

</script>

</body>
</html>