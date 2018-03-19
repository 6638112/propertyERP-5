<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业-业主信息管理-编辑</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body>
<div class="info">
    <h2>业主信息管理-编辑</h2>
    <form class="inputform" action="../propertyProprietor/save.html">
    <div class="bs-example bgebeb">
    		<input type="hidden" name="ppId" value="${ppEntity.id }"/> 
    		<input type="hidden" name="buildingId" value="${ppEntity.buildingId }"/> 
    		<input type="hidden" name="rrId" value="${ppEntity.realRoomId }"/> 
	        <table class="info-list" border="0">
	          <tr>
	            <td width="120" align="right">小区名称：</td>
	            <td>${ppEntity.groupBuildingName }</td>
	          </tr>
	          <tr>
	            <td align="right">楼栋号：</td>
	            <td> ${ppEntity.buildingName }</td>
	          </tr>
	          <tr>
	            <td align="right">单元号：</td>
	            <td><input type="text" class="input_text pp" name="unitName" value="${ppEntity.realRoomUnitName }"  ignore="ignore" datatype="*1-20" errormsg="单元号范围在1到20个字符！"></td>
	          </tr>
	          <tr>
	            <td align="right"><Span class="red">*</Span> 房间号：</td>
	            <td><input type="text" class="input_text pp" name="realRoomNum" value="${ppEntity.realRoomNum}" datatype="*1-20" nullmsg="请填房间号！" errormsg="房间号范围在1到20个字符！"></td>
	          </tr>
	          <tr>
	            <td align="right">房屋面积：</td>
	            <td><input type="text" class="input_text pp" name="roomSize" <c:if test="${ ppEntity.roomSize> 0}"> value="${ppEntity.roomSize }"</c:if> ignore="ignore" dataType="/^[0-9]+(\.[0-9]+){0,1}$/" errormsg="请输入正确的房间面积" ></td> 
	          </tr>
	          <tr>
	            <td align="right">管理费单价：</td>
	            <td><input type="text" class="input_text pp" name="roomManagerPrice" <c:if test="${ppEntity.roomManagerPrice>0 }"> value="${ppEntity.roomManagerPrice }"</c:if> ignore="ignore" dataType="/^[0-9]+(\.[0-9]+){0,1}$/"  errormsg="请输入正确的管理费单价" ></td> 
	          </tr>
	          <tr>
	            <td align="right"><Span class="red">*</Span> 物业管理费：</td>
	            <td> <c:if test="${ppEntity.manangeFee>0 }">${ppEntity.manangeFee }</c:if></td>
	          </tr>
				<tr>
					<td align="right">出售情况：</td>
					<td>
						<select class="select_normal" name="saleStatus">
							<option value="0" <c:if test="${ppEntity.saleStatus == 0}"> selected </c:if> ></option>
							<option value="1" <c:if test="${ppEntity.saleStatus == 1}"> selected </c:if> >未出售</option>
							<option value="2" <c:if test="${ppEntity.saleStatus == 2}"> selected </c:if> >已出售</option>
							<option value="3" <c:if test="${ppEntity.saleStatus == 3}"> selected </c:if> >已交房</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">居住情况：</td>
					<td>
						<select class="select_normal" name="livingStatus">
							<option value="0" <c:if test="${ppEntity.livingStatus == 0}"> selected </c:if>></option>
							<option value="1" <c:if test="${ppEntity.livingStatus == 1}"> selected </c:if>>未入住</option>
							<option value="2" <c:if test="${ppEntity.livingStatus == 2}"> selected </c:if>>已入住</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">出租情况：</td>
					<td>
						<select class="select_normal" name="leaseStatus">
							<option value="0" <c:if test="${ppEntity.leaseStatus == 0}"> selected </c:if>></option>
							<option value="1" <c:if test="${ppEntity.leaseStatus == 1}"> selected </c:if>>未出租</option>
							<option value="2" <c:if test="${ppEntity.leaseStatus == 2}"> selected </c:if>>已出租</option>
						</select>
					</td>
				</tr>
	        </table>
    </div>
   	<input class="info-btn" type="submit" value="保 存" />
   	</form>

	<form class="dsn templatePPForm" action="savePP.json" onsubmit="return false;">
		<table class="info-list " border="0">
			<tr>
				<td width="120" align="right">业主姓名：</td>
				<td><input type="text" class="input_text pp" name="name" ignore="ignore"  datatype="*2-20" errormsg="姓名范围在2到20个字符！"></td>
			</tr>
			<tr>
				<td align="right">身份证号：</td>
				<td><input type="text" maxlength="45" class="input_text pp" name="identifyNo"  ></td>
			</tr>
			<tr>
				<td align="right">联系方式：</td>
				<td><input type="text"  maxlength="45"  class="input_text pp" name="phone" ></td>
			</tr>
			<tr>
				<td align="right">操作：</td>
				<td>
					<input type="hidden" value="${ppEntity.realRoomId}" name="rrId" />
					<input type="hidden" value="" name="id" />
					<input class="weak-btn small-btn w50 deletePPBtn" type="button" data-rrId="${ppEntity.realRoomId}" value="删除">
					<input class="info-btn small-btn w50 savePPBtn mleft20" type="submit" value="保 存" />
				</td>
			</tr>
		</table>
	</form>

	<c:if test="${not empty ppList}">
	业主信息<br>
		<c:forEach var="row" items="${ppList}" >
			<form action="savePP.json" onsubmit="return false;">
				<table class="info-list " border="0">
					<tr>
						<td width="120" align="right">业主姓名：</td>
						<td><input type="text" class="input_text pp" name="name" value="${row.name }" ignore="ignore"  datatype="*2-20" errormsg="姓名范围在2到20个字符！"></td>
					</tr>
					<tr>
						<td align="right">身份证号：</td>
						<td><input type="text" maxlength="45" class="input_text pp" name="identifyNo" value="${row.identifyNo }" ></td>
					</tr>
					<tr>
						<td align="right">联系方式：</td>
						<td><input type="text"  maxlength="45"  class="input_text pp" name="phone" value="${row.phone }" ></td>
					</tr>
					<tr>
						<td align="right">操作：</td>
						<td>
							<input class="weak-btn small-btn w50 deletePPBtn" type="button" data-rrId="${ppEntity.realRoomId}" data-ppId="${row.id}"  value="删除">
							<input type="hidden" value="${ppEntity.realRoomId}" name="rrId" />
							<input type="hidden" value="${row.id}" name="id" />
							<input class="info-btn small-btn w50 savePPBtn mleft20" type="submit" value="保 存" />
						</td>
					</tr>

				</table>
			</form>
		</c:forEach>
	</c:if>
	<input class="info-btn addPPBtn" type="button" value="添加业主信息" />

    <form class="dsn templatePLForm" action="savePL.json" onsubmit="return false;">
        <table class="info-list " border="0">
            <tr>
                <td width="120" align="right">租户姓名：</td>
                <td><input type="text" class="input_text pp" name="name" ignore="ignore"  datatype="*2-20" errormsg="姓名范围在2到20个字符！"></td>
            </tr>
            <tr>
                <td align="right">身份证号：</td>
                <td><input type="text" maxlength="45" class="input_text pp" name="identifyNo"  ></td>
            </tr>
            <tr>
                <td align="right">联系方式：</td>
                <td><input type="text"  maxlength="45"  class="input_text pp" name="phone" ></td>
            </tr>
            <tr>
                <td align="right">操作：</td>
                <td>
                    <input type="hidden" value="${ppEntity.realRoomId}" name="rrId" />
                    <input type="hidden" value="" name="id" />
                    <input class="weak-btn small-btn w50 deletePLBtn" type="button" data-rrId="${ppEntity.realRoomId}" data-plId="" value="删除">
                    <input class="info-btn small-btn w50 savePLBtn mleft20" type="submit" value="保 存" />
                </td>
            </tr>
        </table>
    </form>

	<c:if test="${not empty plList}">
		<br>租户信息<br>
		<c:forEach items="${plList}" var="row">
			<form action="savePL.json" onsubmit="return false;">
			<table class="info-list " border="0">
					<tr>
						<td width="120" align="right">租户姓名：</td>
						<td>
							<input type="text" class="input_text pp" name="name" value="${row.name}" ignore="ignore"  datatype="*2-20" errormsg="姓名范围在2到20个字符！">
						</td>
					</tr>
					<tr>
						<td align="right">身份证号：</td>
						<td><input type="text" maxlength="45" class="input_text pp" name="identifyNo" value="${row.identifyNo}" ></td>
					</tr>
					<tr>
						<td align="right">联系方式：</td>
						<td><input type="text"  maxlength="45"  class="input_text pp" name="phone" value="${row.phone}" ></td>
					</tr>
					<tr>
						<td align="right">操作：</td>
						<td>
							<input type="hidden" value="${ppEntity.realRoomId}" name="rrId" />
							<input type="hidden" value="${row.id}" name="id" />
							<input class="weak-btn small-btn w50 deletePLBtn" type="button" data-rrId="${ppEntity.realRoomId}" data-plId="${row.id}" value="删除">
							<input class="info-btn small-btn w50 savePLBtn mleft20" type="submit" value="保 存" />
						</td>
					</tr>
			</table>
			</form>
		</c:forEach>

	</c:if>
    <input class="info-btn addPLBtn" type="button" value="添加租户信息" />

</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
$(function(){
    //表单验证
	$(".inputform").Validform({
		tiptype:3,
		dragonfly:true,
	});
});
	$(document).on('click', '.addPPBtn', function () {
		$("form.templatePPForm.dsn").clone().removeClass("dsn").insertBefore($(this));
		window.parent.TuneHeight();
    });

	$(document).on('click', '.addPLBtn', function () {
		$("form.templatePLForm.dsn").clone().removeClass("dsn").insertBefore($(this));
		window.parent.TuneHeight();
    });

	$(document).on('click', '.deletePPBtn', function () {
        if(!confirm("确认要删除？")){
            return;
        }

		var ppId = $(this).attr("data-ppId");
		var rrId = $(this).attr("data-rrId");
		var $thisBtn = $(this);

        if(ppId == null || ppId == undefined){
            $thisBtn.parents("form").remove();
            alert("删除成功");
            return;
		}

		$.ajax({
			url: "deletePP.json",
			type: "post",
			data: {"rrId": rrId, "ppId": ppId},
			success:function (data) {
				if(data.status == "0000"){
                    $thisBtn.parents("form").remove();
				    alert("删除成功");
				}else {
				    alert(data.message);
				}
            }
		});
    })

	$(document).on('click', '.deletePLBtn', function () {
        if(!confirm("确认要删除？")){
            return;
        }

		var plId = $(this).attr("data-plId");
		var rrId = $(this).attr("data-rrId");
		var $thisBtn = $(this);

        if(plId == null || plId == undefined){
            $thisBtn.parents("form").remove();
            alert("删除成功");
            return;
		}

		$.ajax({
			url: "deletePL.json",
			type: "post",
			data: {"rrId": rrId, "plId": plId},
			success:function (data) {
				if(data.status == "0000"){
                    $thisBtn.parents("form").remove();
				    alert("删除成功");
				}else {
				    alert(data.message);
				}
            }
		});
    })

	$(document).on('click', '.savePPBtn', function () {
		$form = $(this).parents("form");
        $.ajax({
            url: $form.attr("action"),
            type: "post",
            data: $form.serialize(),
            success: function (data) {
                if (data.status == "0000") {
                    $form.find("input[name='id']").val(data.dataValue.ppId);
                    $form.find("input.deletePPBtn").attr("data-ppId", data.dataValue.ppId);
                    alert("保存成功");
                } else {
                    alert(data.message);
                }
            }
        });
    });

	$(document).on('click', '.savePLBtn', function () {
		$form = $(this).parents("form");
        $.ajax({
            url: $form.attr("action"),
            type: "post",
            data: $form.serialize(),
            success: function (data) {
                if (data.status == "0000") {
                    $form.find("input[name='id']").val(data.dataValue.plId);
                    $form.find("input.deletePLBtn").attr("data-plId", data.dataValue.plId);
                    alert("保存成功");
                } else {
                    alert(data.message);
                }
            }
        });
    });
</script>
</html>
