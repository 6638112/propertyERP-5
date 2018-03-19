<%-- 
    Document   : 新增车辆信息
    Created on : 2016-3-16, 13:20:24
    Author     : Liyl lyl010991@126.com
--%>
<%@page import="com.cnfantasia.server.common.CommConstants"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
		<link rel="dns-prefetch" href="//jiefangqu.com">
		<title>新增车辆</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shopping.common.css">
	</head>
	<body class="bggrey">
		<section id="wrapBox" class="sectionBox pos-relative minheight100 bggrey">
			<form class="inputform" action="${pageContext.request.contextPath}/payCarFee/bindCar.do" method="post" onsubmit="return false;">
				<section class="sectionBox wp100 ptb10 border-radius-bottom bggrey">
		       		<div class="f14 grey m010">请输入您的车辆信息</div>
				</section>
			    <ul class="register-list bordertbgrey binding-phone-num-box">
			        <li class="borderbottomgrey">
			            <div class="displaybox checkPhoneNumBox">
			                <div class="item-standard-name height36 f16 w80">车主姓名</div>
			                <div class="boxflex01 f16 grey"><input class="input-text wp100" type="text" id="name" name="name" placeholder="您在管理处登记的车主姓名" value="" maxlength="6" datatype="*"/></div>
			            </div>
			        </li>
			        <li class="borderbottomgrey">
			            <div class="displaybox">
			                <div class="item-standard-name height36 f16 w80">车牌号</div>
			                <div class="boxflex01 f16 grey"><input class="input-text wp100" type="text" id="carNo" name="carNo" placeholder="如：粤B12345" value="" maxlength="10" datatype="*"/></div>
			            </div>
			        </li>
			        <li>
			            <div class="displaybox">
			                <div class="item-standard-name height36 f16 w80">停车场</div>
			                <div class="boxflex01 f16 grey">
			                	<select id="plotId" name="plotId" class="select_normal wp100 noborder f16" style="outline: none; background: none;" onchange="countValNum()">
			                		<option value="">请选择停车场</option>
			                		<c:forEach items="${plots}" var="plot">
			                			<option value="${plot.plotId}">${plot.plotName}</option>
			                		</c:forEach>
				            	</select>
			                </div>
			            </div>
			        </li>
		        </ul>
				<section class="sectionBox wp100 ptb10 border-radius-bottom bggrey">
		       		<div class="f14 grey m010">信息加密处理，仅用于查询车辆信息。</div>
				    <div class="m010 mtop10"><input class="btn-submit btnSubmit btn-next" type="button" value="确认" /></div>
				</section>
			</form>
			<div class="tips-box tips-done bounceInDown animated1s dsn">
				<img class="rotateZoomIn animated1s delay" src="${pageContext.request.contextPath}/images/icon-tips-done.png"/><br>绑定成功
			</div>
		</section>
		<section class="pop-tips1 upload-text hide">
			<div class="pop-tips-text">信息提交中，请稍后…</div>
		</section>
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/fastclick.js"></script>
		<script src="${pageContext.request.contextPath}/js/Validform_v5.3.2.js"></script>
		<script src="${pageContext.request.contextPath}/dredge/js/jquery.form.js"></script>
		<script>
			$(function(){
				//表单验证
				$(".inputform").Validform({
					tiptype:1,
					btnSubmit:".btnSubmit",
					postonce:true,
					ajaxPost:true,
					beforeCheck:function(curform){
						var name = $.trim($("#name").val());
						var carNo = $.trim($("#carNo").val());
						var plotId = $.trim($("#plotId").val());
						if(name==null || name==""){
							$.Showmsg("请输入车主姓名");
							$(".pop-tips1").addClass('hide');
							return false;
						} else if(carNo==null || carNo==""){
							$.Showmsg("请输入车牌号");
							$(".pop-tips1").addClass('hide');
							return false;
						} else if(plotId==null || plotId == ""){
							$.Showmsg("请选择停车场");
							$(".pop-tips1").addClass('hide');
							return false;
						}
					},
					beforeSubmit:function(){
						$(".pop-tips1").removeClass('hide');
					},
					callback:function(data){
						if (data.status == '<%=CommConstants.ResponseStatus.SUCCESS%>' ) {
	                		if(data.dataValue=="true" || data.dataValue==true){
	                			$.Showmsg("添加成功！页面跳转中...");
								$(".pop-tips1").addClass('hide');
	                			window.location.href = '${pageContext.request.contextPath}/payCarFee/monthCar.do';
	                		} else {
	                			$.Showmsg("该车牌号码不存在！添加失败！");
								$(".pop-tips1").addClass('hide');
								return;
	                		}
						} else {
							$.Showmsg(data.message);
							$(".pop-tips1").addClass('hide');
							return;
						}
					}
				});
						
				new FastClick(document.body);//检查触发按钮状态
				$('input').keyup(function(){
					countValNum();
				});
				
			});
			
			//触发按钮状态
			function countValNum(){
				var num = 0;
				$('.register-list li').each(function(){
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