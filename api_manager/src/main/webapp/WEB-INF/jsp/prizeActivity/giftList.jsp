<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="<%=basePath%>//" />
<title>轻应用活动运营-奖项管理-奖品列表</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css?v=1" />
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />
</head>

<body>
<div class="info">
    <div class="layer-prize-details">
        <h2>奖品列表</h2>
        <div class="bs-example bgebeb mtop10">
        <form id="searchForm"  action="<%=basePath%>/prizeActivity/giftList.html" method="post">
        	<input type="hidden" name="optId" value="${optId}" />
            <table class="info-list" border="0">
              <tr>
                <td><div class="list-name">奖品名称：</div></td>
                <td><input type="text" name="codeName" value="${param.codeName}" class="input_text w120 pp"/></td>
                <td><div class="list-name">兑换码：</div></td>
                <td><input type="text" name="valueCode" value="${param.valueCode}" class="input_text w120 pp"/></td>
                <td><div class="list-name">奖品状态：</div></td>
                <td>
	                 <select class="select_normal" name="qryStatus"> 
	                     <option value="0" <c:if test="${param.qryStatus == 0 }">selected</c:if> >全部</option>
	                     <option value="1" <c:if test="${param.qryStatus == 1 }">selected</c:if> >未使用</option>
	                     <option value="2" <c:if test="${param.qryStatus == 2 }">selected</c:if>>已锁定</option>
	                     <option value="3" <c:if test="${param.qryStatus == 3 }">selected</c:if>>已分配</option>
	                 </select>
                </td>
                <td class="t_center"><input class="input-btn w200" type="submit" value="搜索"/></td>
              </tr>
            </table>
        </form>
        </div>
        <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="5" requestURI="/prizeActivity/giftList.html" partialList="true" size="resultSize" >
			<display:column title="编号" property="id" />
			<display:column title="金额/数量" property="number" />
			<display:column title="单位" property="unit" />
			<display:column title="奖品编码-奖品名称-奖品兑换码">
				<c:forEach var="it" items="${row.msPrizeGiftCodeList}">
					${it.uqMark}&nbsp;${it.codeName}&nbsp;${it.valueCode} <br/>
				</c:forEach>
			</display:column>
			<display:column title="奖品状态">
				<c:if test="${row.convertStatus==0}">未使用</c:if>
				<c:if test="${row.convertStatus==1}">已锁定</c:if>
				<c:if test="${row.convertStatus==2}">已分配</c:if>
			</display:column>
			<display:column title="操作">
			 	<c:if test="${row.convertStatus==0}"><a class="blue" onclick="doGiftDel(${row.id},this)" href="javascript:void(0)">删除</a></c:if>
			</display:column>
		</display:table>
    </div>
</div>

<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/clearbox.js"></script>
<script type="text/javascript" src="../js/layer.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
(function($){
    //表单验证
	$(".inputform").Validform({
		tiptype:3,
		dragonfly:true,
		ignoreHidden:true
	});
})(jQuery);
</script>
<script type="text/javascript">
	function doGiftDel(giftId,tdTmp){
		if(confirm("确认删除?")){
			$.ajax({
				type:"post",
				url:"<%=basePath%>/prizeActivityJson/doGiftDel.json",
				data:{'giftId': giftId},
				dataType:"TEXT",
				beforeSend:function(data){
				},
				success:function(data){
					try {
						data = eval(data);
					} catch (e) {
						data = eval("("+data+")");
					}
					try {
						if (data.status == '0000') {
							alert("删除奖品成功!");
							$(tdTmp).parents('tr').remove();//TODO 刷新本页面？
						} else {
							alert(data.message);

						}
					} catch (e) {
						alert(data.message);
					}
				}
			});
		}
	}
</script>
</body>

</html>
