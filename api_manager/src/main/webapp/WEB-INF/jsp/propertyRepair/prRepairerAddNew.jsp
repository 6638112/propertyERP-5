<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>报修管理-新增维修工人</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
</head>

<body>
<div class="info">
    <h2>增加维修工人</h2>
	<form class="inputform" method="post" action="../propertyRepair/saveRepairer.html" enctype="multipart/form-data">
        <input type="hidden" name="pmId" value="${propertyRepairer.tPropertyManagementFId}" /> 
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="120" align="right"><Span class="red">*</Span> 员工姓名：</td>
            <td><input type="text" name="repairerName" class="input_text" maxlength="6" datatype="*" nullmsg="请填写员工姓名！"></td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> 联系手机：</td>
            <td><input type="text" name="repairerTel" class="input_text" maxlength="11"  datatype="m" nullmsg="请填写联系手机！" errormsg="请填写正确的手机号码格式！"></td>
          </tr>
          <tr>
            <td align="right">大头照：</td>
            <td><div class="uploadPreview"></div><input type="file" name="headimgurl" class="uploadImage fimg1 w150" /><span class="f12 mar-left15">注：宽度须大于200像素</span></td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> 身份证号：</td>
            <td><input type="text" name="idNumber" class="input_text"  datatype="idcard" nullmsg="请填写身份证号！" errormsg="请填写正确的身份证号码格式！" maxlength="18"></td>
          </tr>
          <tr>
            <td align="right">身份证正面：</td>
            <td><div class="uploadPreview"></div><input type="file" name="idimgurl1" class="uploadImage fimg1 w150" /><span class="f12 mar-left15">注：宽度须大于200像素</span></td>
          </tr>
          <tr>
            <td align="right">身份证反面：</td>
            <td><div class="uploadPreview"></div><input type="file" name="idimgurl2" class="uploadImage fimg1 w150" /><span class="f12 mar-left15">注：宽度须大于200像素</span></td>
          </tr>
          <tr>
            <td align="right"><div align="right"><Span class="red">*</Span> 负责报修类型：</div></td>
            <td><select name="repairType" class="select_normal" datatype="*" nullmsg="请选择负责报修类型！">
                    	<option value="">请选择该员工擅长类型</option>
	            		<c:forEach items="${prTypeList}" var="prType">
		                    <option value="${prType.id }"  pmId="${prType.pmId }"  <c:if test="${prType.id == propertyRepairer.tPropertyRepairTypeFId }"> selected="selected" </c:if> >${prType.name }——${prType.pmName } </option>
	            		</c:forEach>
                    </select></td>
          </tr>
        </table>
        <div class="padb mtop10"><input class="info-btn manageRepair" type="submit" value="保 存" /></div>
    </form>
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
			postonce:true,
			beforeSubmit:function(){
				var isMobileRepeated = 0;
				$.ajax({
					type:"post",
					url:"verifyMobile.html",
					data:{mobile: $("input[name=repairerTel]").val(),},
					dataType:"json",
					async:false,
					beforeSend:function(data){
						//$.Showmsg("正在获取…");
					},
					success:function(data){
						try {
							if (data.status == '0000') {
								isMobileRepeated = data.dataValue.isMobileRepeated;
							} else {
								alert(data.message);
								return;
							}
						} catch (e) {
							alert(e);
						}
					},  
		            error: function(){  
		            	alert('网络不给力，请稍后重试'); 
		            }  
				});
				
				if(isMobileRepeated==1){
					alert("该手机号已被别的维修工作使用，请检查手机号");
					return false;
				}
				else
					return true;
			},
			callback:function(data){
				//按钮置灰
				$('.info-btn').css('background','#999');
			}
		});
	});

	//选中报修类型，更新物业管理处ID
	$(document).ready(function(e){
		$('select.select_normal').change(function(e){
			$this = $(this);
			$pmid = ($this.find('option:selected').attr('pmId'));
			$('input[name=pmId]').val($pmid);
		});
	});
</script>

</html>
