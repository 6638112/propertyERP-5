<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>供应商列表</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
</head>
<body>
<div class="info">
    <h2>供应商列表</h2>
    <div class="bs-example bgebeb">
    	<form action="supplyMerchantList.html" method="post">
        <table class="info-list" border="0">
          <tr>
            <td><div class="list-name">店铺地址：</div></td>
            <td><input name="address" type="text" class="input_text w120 pp" value="${param.address }"></td>
            <td><div class="list-name">联系人：</div></td>
            <td><input name="linkName" type="text" class="input_text w120 pp"  value="${param.linkName }"></td>
            <td><div  class="list-name">联系方式：</div></td>
            <td><input name="linkPhone" type="text" class="input_text w120 pp" value="${param.linkPhone }"></td>
          </tr>
          <tr>
            <td><div class="list-name">店铺状态：</div></td>
            <td>
                <select name="storeAuditStatus" class="select_normal">
                    <option value="-1" <c:if test="${param.storeAuditStatus == -1}">selected="selected"</c:if> >全部</option>
                    <option value="0" <c:if test="${ param.storeAuditStatus==0}">selected="selected"</c:if> >待审核</option>
                    <option value="2" <c:if test="${ param.storeAuditStatus==2}">selected="selected"</c:if> >审核失败</option>
                    <option value="1" <c:if test="${ param.storeAuditStatus==1}">selected="selected"</c:if> >审核成功</option>
                </select>
            </td>
            <td><div class="list-name">店铺审核时间：</div></td>
            <td><input name="storeAuditTimeStart" type="text" class="input_text icon_dt" id="date01" title="请选择起始时间" value="${ param.storeAuditTimeStart}"> 至 <input name="storeAuditTimeEnd" type="text" class="input_text icon_dt" id="date02" title="请选择结束时间" value="${ param.storeAuditTimeEnd}"></td>
            <td><div class="list-name">店主姓名：</div></td>
            <td><input name="ownerName" type="text" class="input_text w120 pp" value="${ param.ownerName}"></td>
          </tr>
          <tr>
            <td><div class="list-name">店主状态：</div></td>
            <td>
                    <select name="ownerAuditStatus" class="select_normal">
                        <option value="-1" <c:if test="${param.ownerAuditStatus == -1}">selected="selected"</c:if>>全部</option>
                        <option value="0" <c:if test="${param.ownerAuditStatus == 0}">selected="selected"</c:if>>待审核</option> 
                        <option value="2" <c:if test="${param.ownerAuditStatus == 2}">selected="selected"</c:if>>审核失败</option>
                        <option value="1" <c:if test="${param.ownerAuditStatus == 1}">selected="selected"</c:if>>审核成功</option>
                    </select>
            </td>
            <td><div class="list-name">店主审核时间：</div></td>
            <td><input name="ownerAuditTimeStart" type="text" class="input_text icon_dt" id="date01" title="请选择起始时间" value="${param.ownerAuditTimeStart}"> 至 <input name="ownerAuditTimeEnd" type="text" class="input_text icon_dt" id="date02" title="请选择结束时间" value="${param.ownerAuditTimeEnd}"></td>
            <td><div  class="list-name">店铺名称：</div></td>
            <td><input name="storeName" type="text" class="input_text w120 pp" value="${param.storeName}"></td>
          </tr>
          <tr>
            <td colspan="8" class="t_center"><input class="input-btn w200" type="submit" value="搜索"></td>
          </tr>
        </table>
        </form>
    </div>
    
    <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
		<display:column title="<input id='checkAll' name='checkbox' type='checkbox' value=''> 全选" >
			<input id="checkAll" name="checkbox" type="checkbox" value=""></div>
		</display:column>
		<display:column title="提交时间" property="sys0AddTime" />
		<display:column title="店铺审核时间" property="storeAuditTime" />
		<display:column title="店主审核时间" property="ownerAuditTime" />
		<display:column title="注册手机号" property="registMobile" />
		<display:column title="店铺地址" property="address" />
		<display:column title="联系人" property="linkName" />
		<display:column title="联系电话" property="tel" />
		<display:column title="店铺名称" property="name" />
		<display:column title="店主姓名" property="userName" />
		<display:column title="店铺状态"  >
            <c:if test="${ row.storeAuditStatus==0}">待审核</c:if> 
            <c:if test="${ row.storeAuditStatus==2}">审核失败</c:if> 
            <c:if test="${ row.storeAuditStatus==1}">审核成功</c:if>
		</display:column>
		<display:column title="店主状态" >
			<c:if test="${ row.ownerAuditStatus==0}">待审核</c:if> 
            <c:if test="${ row.ownerAuditStatus==2}">审核失败</c:if> 
            <c:if test="${ row.ownerAuditStatus==1}">审核成功</c:if>
		</display:column>
		<display:column title="操作人">
			<c:choose>
				<c:when test="${not empty row.updateMan}">${row.updateMan}</c:when>
				<c:otherwise>${row.addMan}</c:otherwise>
			</c:choose>
		</display:column>
		<display:column title="审核或查看"> 
			<div align="center"> 
				<a class="blue viewShopInfo" href="../supplyMerchant/esmView.html?id=${row.id }">查看</a> 
				<c:if test="${row.storeAuditStatus==0}"> 
					<span class="grey">|</span>
					<a class="blue checkShopInfo" href="../supplyMerchant/esmAudit.html?id=${row.id }">店铺审核</a> 
				</c:if>
				<c:if test="${row.storeAuditStatus==1 and row.ownerAuditStatus==0}"> 
					<span class="grey">|</span>
					<a class="blue checkShopManInfo" href="../supplyMerchant/esmOwnerAudit.html?id=${row.id }">店主审核</a>
				</c:if>
			</div>
		 </display:column>
		<display:column title="启用或禁用"> 
			<c:if test="${row.sys0DelState==0 }">
				<div align="center"><span class="black">启用</span> <span class="grey">|</span> <a class="blue status-btn merchant-status" href="#" esmId="${row.id }">禁用</a></div>
			</c:if>
			<c:if test="${row.sys0DelState==1 }">
				<div align="center"><a class="blue status-btn merchant-status" href="#" esmId="${row.id }">启用&nbsp;</a><span class="grey">|</span><span class="black">&nbsp;禁用</span></div>
			</c:if>
		</display:column>
		<display:column title="操作">
			<a href="editProPic.html?merchantId=${row.id}"><span class="blue">装修</span></a>
		</display:column>
	</display:table>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/DatePicker.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript">
	//修改商家状态
	$('.status-btn.merchant-status').click(function(){
		if(confirm('确定修改该商家状态？')){
			var $this = $(this);
			var isEnable = $this.siblings('span:first').text() == '启用'?0:1;
			$.get("../supplyMerchant/esmViewEnabled.json", { esmId: $this.attr("esmId"),isEnable: isEnable, },
			  function(data){
				try {
					if (data.status == '0000' ) {
						alert(data.message);
						self.location.reload();
						//$this.siblings('span:first').swapClass('black alive', 'grey asleep').swapText('启用', '禁用');
					} else {
						alert(data.message);

					}
				} catch (e) {
					alert(e);
				}
			});
		}
	});
</script>
</html>