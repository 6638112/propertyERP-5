<%-- 
    Document   : 新增小区-小区已合作
    Created on : 2016-3-15, 11:49:24
    Author     : Liyl lyl010991@126.com
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
		<link rel="dns-prefetch" href="//jiefangqu.com">
		<title>新增小区</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shopping.common.css">
	</head>
	<body class="bggrey">
		<div class="main-part01">  
			<section id="wrapBox" class="sectionBox bggrey pos-relative minheight100">
				<form class="inputform">
				<section class="sectionBox">
					    <div class="list-box">
					        <div id="cityOrientation" class="item-standard-name height36 f16">
					        	<span class="left">所在城市</span>
					        	<span class="right list-arrow pright20"><span id="curCity">正在定位…</span></span>
					        </div>
					    </div>
				    <section class="divide-box bordertbgrey"></section>
				    <ul class="register-list check-select borderbottomgrey">
				        <li class="borderbottomgrey">
				            <select id="bankCardNameSelect" class="input-text wp100 list-arrow-down" datatype="*" nullmsg="请选择区域！" >
				                <option value="">区域</option>
				                <option value="1">南山区</option>
				                <option value="2">福田区</option>
				            </select>
				        </li>
				        <li class="borderbottomgrey">
				            <select class="input-text wp100 list-arrow-down" datatype="*" nullmsg="请选择小区名！" >
				                <option value="">小区名</option>
				                <option value="1">花好园</option>
				                <option value="2">金珠园</option>
				            </select>
				        </li>
				        <li class="borderbottomgrey">
				            <select class="input-text wp100 list-arrow-down" datatype="*" nullmsg="请选择楼栋！" >
				                <option value="">楼栋</option>
				                <option value="1">1栋</option>
				                <option value="1">2栋</option>
				            </select>
				        </li>
				        <li class="borderbottomgrey">
				            <select class="input-text wp100 list-arrow-down" datatype="*" nullmsg="请选择单元！" >
				                <option value="">单元</option>
				                <option value="1">1单元</option>
				                <option value="1">3单元</option>
				            </select>
				        </li>
				        <li>
				            <select class="input-text wp100 list-arrow-down" datatype="*" nullmsg="请选择门牌号！" >
				                <option value="">门牌号</option>
				                <option value="1">306</option>
				            </select>
				        </li>
				    </ul>
					<section class="sectionBox bggrey">
					    <div class="m010 mtop15"><input class="btn-submit btnSubmit btn-next" type="button" value="完成" /></div>
					</section>
				    <section class="divide-box"></section>
				</section>
				</form>
				<div class="tips-box tips-done bounceInDown animated1s dsn">
					<img class="rotateZoomIn animated1s delay" src="${pageContext.request.contextPath}/images/icon-tips-undone.png"/><br>您的小区暂未<br>与解放区合作
				</div>
				<section class="sectionBox wrap-bg dsn">
					<div class="tips-box">
						<div class="t-center ptb20 borderbottomgrey">
							<div class="marb15 f18">请确认银行卡信息</div>
							<div class="wp90 t-left margin_auto">姓名：<span class="bankCardPerson"></span></div>
							<div class="wp90 t-left margin_auto">卡号：<span class="bankCardNum"></span></div>
							<div class="wp90 t-left margin_auto">银行：<span class="bankCardName"></span></div>
						</div>
						<ul class="displaybox">
							<li class="boxflex01 ptb10 t-center back-btn">取消</li>
							<li class="boxflex01 ptb10 t-center red borderleft pay-check-btn"><a class="disblock red" href="#">确认绑定</a></li>
						</ul>	
					</div>
				</section>
			</section>
		</div>
		<div class="main-part02 dsn">    
		    <section class="sectionBox item-details-info pos-relative">
		        <section class="sectionBox password-mind-box bordertbgrey">
		            <div class="mleft15 grey">请选择您所在省市</div>
		        </section>
		        <ul class="register-list">
		            <li class="borderbottomgrey">
		                <select id="province" class="input-text wp100 list-arrow" datatype="*" nullmsg="请完善上门地址！" >
		                    <option style="color: #8e8e93;"  value="">选择省份</option>
		                </select>
		            </li>
		            <li>
		                <select id="city" class="input-text wp100 list-arrow" datatype="*" nullmsg="请完善上门地址！" >
		                    <option value="">选择城市</option>
		                </select>
		            </li>
		        </ul>
		        <div class="item-list-arrow-box p00 borderbottomgrey"><input class="btn-submit btn-next noradius bordertbgrey btnSubmit bgwhite red" type="button" name="button" value="确定"></div>
		    </section>
		</div>
		<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/fastclick.js"></script>
		<script src="${pageContext.request.contextPath}/js/Validform_v5.3.2.js"></script>
		<script>
			$(function(){
				new FastClick(document.body);
				//表单验证
				$(".inputform").Validform({
					tiptype:1,
					btnSubmit:".btnSubmit",
					ajaxPost:true,
					callback:function(data){
						//$('.tips-box').show();
						window.location.href = '${pageContext.request.contextPath}/payProperty/newVillageDone.jsp';
					}
				});
				
				//检查触发按钮状态
				$('input').keyup(function(){
					countValNum();
				});
				$('select').change(function(){
					countValNum();
				});
				
				//设置定位城市
				var $mainPart01 = $('.main-part01');
				var $mainPart02 = $('.main-part02');
				$('#cityOrientation').click(function(){
					$mainPart01.hide();
					$mainPart02.show();
					
					// query all provinces
					$.post("${pageContext.request.contextPath}/payProperty/httpClient.do", {"url":"/addressProvince/getAddressProvinceList.json"}, function(data){
						$("#province").html('<option style="color: #8e8e93;"  value="">选择省份</option>');
						var provinces = data.dataValue.list;
						$.each(provinces, function(i, item){
							$("#province").append('<option style="color: #8e8e93;"  value="'+item.id+'">'+item.name+'</option>');
						});
					}); 
				});
				
				// query cities by province
				$("#province").change(function(){
					var province = $("#province").val();
					if(province!=""){
						var params = new Object();
						params["provId"] = province;
						$.post("${pageContext.request.contextPath}/payProperty/httpClient.do", {"url":"/addressCity/getAddressCityListById.json", "params":JSON.stringify(params)}, function(city){
							$("#city").html('<option value="">选择城市</option>');
							console.log(city.dataValue);
							var cities = city.dataValue.list;
							$.each(cities, function(i, item){
								$("#city").append('<option style="color: #8e8e93;"  value="'+item.id+'">'+item.name+'</option>');
							}); 
					 	});
					}
				});
				
				$('.item-list-arrow-box').click(function(){
					var $city = $('#city');
					if($city.val() !== ''){
						var cityName = $('#city').find('option:selected').text();
						$('#curCity').text(cityName);
						$mainPart02.hide();
						$mainPart01.show();
					}else{
						$.Showmsg('请选择城市');
					}
				});
			})
				
			$.getScript('http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js', function(){
			    $('#curCity').html(remote_ip_info.city);
			});
			//触发按钮状态
			function countValNum(){
				var num = 0;
				$('.register-list.check-select li').each(function(){
					var thisInputVal = $(this).find('input').val();
					var thisSelectVal = $(this).find('select').val();
					if(thisInputVal == '' || thisSelectVal == ''){
						num += 1;
					}
				});
				if(num == 0){
					$('.btnSubmit').addClass('bgred noborder white');
				}else{
					$('.btnSubmit').removeClass('bgred noborder white');
				}
			}
		</script>
	</body>
</html>