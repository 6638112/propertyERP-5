<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>电商-商家管理-商家审核</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/picbox.css" />
</head>

<body>
<div class="info">
	<form id="inputform" class="inputform" method="post" action="saveEsmOwnerAudit.json" onsubmit="return false;">
		<input name="id" type="hidden" value="${bean.shopId }"/>
        <h2>店主审核</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td align="right" width="120"><Span class="red">*</Span>店主姓名：</td>
            <td><span id="userName">${bean.ownerInfo.ownerName }</span></td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> 联系方式：</td>
            <td><span id="userPhone">${bean.ownerInfo.ownerPhone }</span></td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> 身份证件：</td>
            <td>
                <ul id="userImg" class="menu-img">
                	<c:forEach items="${bean.ownerInfo.owenerIDPhotoList}" var="item">
                    	<li><a href="<%=OmsSysParamManager.getImageServerUrl(PathConstants.Ebuy_Store_Pic)+PathConstants.Ebuy_Store_Pic%>${item}"
                    	rel="lightbox-group"><img src="<%=OmsSysParamManager.getImageServerUrl(PathConstants.Ebuy_Store_Pic)+PathConstants.Ebuy_Store_Pic%>${item}" border="0" /></a></li>
                	</c:forEach>
                </ul>
            </td>
          </tr>
        </table>
		<h2 class="mtop20">服务小区</h2>
		<table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
			<thead>
			<tr class="title">
				<th width="120"><div align="left">小区名字</div></th>
				<th><div align="left">所在地区</div></th>
				<th><div align="left">详细地址</div></th>
			</tr>
			</thead>
			<c:forEach items="${bean.serviceGbList }" var="gb" varStatus="status" >
				<tr>
					<td id="testid"><span>${gb.gbName }</span></td>
					<td>${gb.gbArea }</td>
					<td>${gb.gbAddress }</td>
				</tr>
			</c:forEach>
		</table>
        <h2 class="mtop10 red">商品审核</h2>
        <div class="bs-example">
            <table class="info-list" border="0">
              <tr>
                <td width="90" align="right"><span class="grey">审核结果：</span></td>
                <td colspan="5"><select name="auditResult" class="select_normal w131 select-check">
                            <option value="pass">通过</option>
                            <option value="notpass">不通过</option>
                            </select></td>
              </tr>
              <tr class="dsn">
                <td align="right"><span class="grey">原因：</span></td>
                <td colspan="5"><textarea name="auditDesc" class="textareas txt02" name="" cols="" rows="5" datatype="*" nullmsg="审核结果为“不通过”时需填写原因！" errormsg="原因范围为4到100个字！"></textarea><br />还可以输入<span class="leftNum">100</span>字</td>
              </tr>
            </table>
        </div>
        <div class="padb mtop10"><input id="checkShopList" class="info-btn subOwnerAudit" type="submit" value="提 交" /></div>
    </form>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/picbox.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript" src="../js/layer.min.js"></script>
<script type="text/javascript">
$(function(){
	
	//表单验证
	$(".inputform").Validform({
		tiptype:3,
		btnSubmit:".subOwnerAudit",
		ignoreHidden:true,
		beforeSubmit:function(){
			if($('#userName').text() === '' || $('#userPhone').text() === '' || $('#userImg').text() === ''){
				alert('店主信息不完整！');
				return false;
			}
		},
		callback:function(data){
			$("#inputform").ajaxSubmit(function(data) {
				try {
					if (data.status == '0000' ) {
						alert(data.message);
						window.location.href = "supplyMerchantList.html";
					} else {
						alert(data.message);

					}
				} catch (e) {
					alert(e);
				}
			});
		}
	});
});

</script>

</html>

