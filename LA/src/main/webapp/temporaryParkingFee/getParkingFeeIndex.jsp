<%@page import="com.cnfantasia.server.ms.temporaryParkingFee.web.TemporaryParkingFeeController"%>
<%@page import="com.cnfantasia.server.common.CommConstants"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
		<link rel="dns-prefetch" href="//jiefangqu.com">
		<title>获取临时车费账单</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shopping.common.css?v20161110">
	</head>
	<body class="bggrey">
		<section id="wrapBox" class="sectionBox pos-relative minheight100 bggrey <c:if test="${isAlter }"> heightp100</c:if>">
			<form class="inputform" action="${pageContext.request.contextPath}/temporaryParkingFee/carNumIsExisted.html" method="post" onsubmit="return false;">
				<div>
					<a class="get-ad-click" data-title="临停车缴费顶部广告" href="https://www.xhqb.com/bt/btsfpp.html?appChannel=zhw_jiefangqu07"><img src="../images/zhihuangwang.png"/></a>
				</div>

				<section class="divide-box"></section>
		        <ul id="carTab" class="displaybox bgwhite t-center ptb10 f14 borderbottomgrey">
		            <li class="boxflex01 borderright"><a class="black" href="${pageContext.request.contextPath}/payCarFee/monthCar.do">月卡缴费</a></li>
		            <li class="boxflex01"><a class="black on" href="javascript:void(0)">临停缴费</a></li>
		        </ul>
				<section id="fixTop" class="mtop10 pb10">
					<section class="sectionBox wp100 ptb10 border-radius-bottom bggrey">
			       		<div class="f14 grey m010">请输入您的车牌号，获取临时停车账单信息</div>
					</section>
				    <ul class="register-list bordertbgrey binding-phone-num-box box-shadow">
				        <li style="padding: 0 15px 0 0 !important;">
				            <div class="displaybox">
				                <div class="item-standard-name f16" style="opacity: 0.5;">车牌号：</div>
				                <div class="boxflex01 f16 grey">
					                <input class="input-text height50" 
						                type="text" 
						                id="carNum" 
						                style="letter-spacing: 0.5em;font-size:20px;width: 100%;" 
						                name="carNum" 
						                placeholder="如：${carNumPrefix}12345" 
						                value="${carNumPrefix}" 
						                maxlength="8" 
						                datatype="*" 
						                nullmsg="请输入车牌号"/>
					            </div>
				            </div>
				        </li>
			       </ul>
				</section>
				<section class="sectionBox wp100 ptb10 border-radius-bottom bggrey">
				    <div class="wp72 mtop5"><input class="btn-submit btnSubmit btn-next height40 box-shadow-red" type="button" value="确认" /></div>
				</section>
				<section id="historyCars" class="mtop5" style="overflow:auto;">
					<ul class="displaybox historyNum-text m020 mtop10 marb15 dsn">
						<li class="boxflex01"><img src="../images/title-line-left.png" /></li>
						<li class="pos-relative text"> 历史车牌 </li>
						<li class="boxflex01"><img src="../images/title-line-right.png" /></li>
					</ul>
					<section class="sectionBox wp100 f16 bggrey car-num-list t-center pt10 dsn">
			       		<div class="single-car-num m010 height40 grey dsn"></div>
					</section>
				</section>
				<div class="sectionBox bggrey grey mtop10 f14">
					<div class="mleft10">找不到停车场？<a class="blue" href="parkingLots.html">寻找停车场</a></div>
				</div>
			</form>
			
			<!-- 扫码总停车券弹框 -->
			<c:if test="${isAlter }">
				<section class="sectionBox wrap-bg">
					<div class="tips-box" style="top: 50%; margin: 0 0 0 5%; transform: translate(0, -50%);">
						<div class="ptb20 m010 borderbottomgrey">
							<div class="f16">${title }</div>
							<div class="mtop10"><img src="${img}" /></div>
							<div class="mtop10 grey">${desc }</div>
						</div>
						<ul id="closeBtn" class="displaybox">
							<li class="boxflex01 ptb10 t-center back-btn">确定</li>
						</ul>	
					</div>
				</section>
			</c:if>
			
		</section>
		<section class="pop-tips1 upload-text hide">
			<div class="pop-tips-text">信息提交中，请稍后…</div>
		</section>
	</body>
	<script src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/fastclick.js"></script>
	<script src="${pageContext.request.contextPath}/js/Validform_v5.3.2.js"></script>
	<script src="${pageContext.request.contextPath}/js/ad.clicks.js"></script>
	<script>
		$(function(){
			new FastClick(document.body);
			
			//关闭弹框
			$('#closeBtn').click(function(){
				$('#wrapBox').removeClass('heightp100');
				$('.wrap-bg').addClass('dsn');
			});
			
			//检查触发按钮状态
			countValNum();
			
			$("#carNum").on('input propertychange', function() {
				var carNum = $("#carNum").val();
				if(carNum.length>8){
					carNum = carNum.substring(0,8);
				}
				countValNum();
			});
			
			$(".inputform").Validform({
				tiptype:1,
				btnSubmit:".btnSubmit",
				postonce:true,
				ajaxPost:true,
				beforeSubmit:function(){
					$.Showmsg('正在查询，请稍候…');
				},
				callback:function(data){
					if (data.status == '<%=CommConstants.ResponseStatus.SUCCESS%>' ) {
                		if(data.message=='<%=TemporaryParkingFeeController.JumpType.PAY_PAGE%>'){
            				//保存该车牌
                			HistoryCarNum.keepCarNum();
                			window.location.href = '${pageContext.request.contextPath}/cart/getParkingFee.html?'+data.dataValue;
                		}else {
                			$.Hidemsg();
                			alert('暂无该车辆信息！');
                		}
					} else {
						$.Hidemsg();
						alert("暂未查询到该车辆信息！");
						/* if(data.message){
							alert(data.message);
						} else {
							alert("暂未查询到该车辆信息！");
						} */
						return;
					}
				}
			});
			//获取历史车牌
			HistoryCarNum.getCarNum();
			//点击历史车牌
			$(document).on('click', '.single-car-num', function(){
				var thisText = $(this).text();
				$('#carNum').val(thisText);
				
				$(".inputform").submit();
			});
		});
		//触发按钮状态
		function countValNum(){
			var carNum=$.trim($('#carNum').val()); 
			if(carNum != ""){
				$('.btnSubmit').addClass('bgred noborder white');
			}else{
				$('.btnSubmit').removeClass('bgred noborder white');
			}
		}

		var cacheKey = "historyCarNums";
		var showMaxNum = 3;
		//保留历史车牌
		var HistoryCarNum = {
			keepCarNum: function(){
				var newCarNum = $.trim($('#carNum').val());
				var carNumList = localStorage[cacheKey];
				if(carNumList){
					var carArray = JSON.parse(carNumList);
					if(carNumList.indexOf(newCarNum) != -1){
						carArray.splice($.inArray(newCarNum, carArray), 1);
					}
					carArray.unshift(newCarNum);
					if(carArray.length>showMaxNum){
						carArray = carArray.slice(0, showMaxNum);
					}
					localStorage[cacheKey] = JSON.stringify(carArray);
				}else{
					var carArray = new Array();
					carArray.push(newCarNum);
					localStorage[cacheKey] = JSON.stringify(carArray);
				}
			},
			getCarNum: function(){
				var carNumList = localStorage[cacheKey];
				if(carNumList){
					var carArray = JSON.parse(carNumList);
					var $carNumLi = $('.single-car-num.dsn');
					for (var i = 0; i < carArray.length; i++) {
						var $carNumLiClone = $carNumLi.clone();
						$carNumLiClone.text(carArray[i]).removeClass('dsn').prependTo('.car-num-list');
					}

					$('.car-num-list, .historyNum-text').removeClass('dsn');
				}
			}
		};
	</script>
</html>