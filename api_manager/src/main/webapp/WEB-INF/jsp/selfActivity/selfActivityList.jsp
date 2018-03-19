<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>自定义活动列表</title>
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <link rel="stylesheet" type="text/css" href="../css/style.css?v20161128">
    <link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
    <link rel="stylesheet" type="text/css" href="../styles/displaytag-css/alternative.css">
    <link rel="stylesheet" type="text/css" href="../css/select2.css">
</head>

<body>
<div class="info posrelative">
    <div class="bs-example bgebeb">
        <form action="list.html" method="get">
            <table class="info-list" border="0">
                <tr>
                    <td><div class="list-name">活动名称：</div></td>
                    <td>
                        <input type="text" value='${param.name }' name="name" class="input_text  pp">
                    </td>

                    <td><div class="list-name">页面地址：</div></td>
                    <td>
                        <input type="text" value='${param.url }' name="url" class="input_text  pp">
                    </td>
                    <td><div class="list-name">添加人：</div></td>
                    <td>
                        <input type="text" value='${param.addUser }' name="addUser" class="input_text  pp">
                    </td>
                </tr>
                <tr>
                    <td><div class="list-name">新增时间：</div></td>
                    <td colspan="5"><input type="text" name="addTimeStart" class="input_text pp icon_dt" id="date01" title="请选择起始时间" value="${param.addTimeStart }" readonly="readonly"> 至 <input name="addTimeEnd" type="text" class="input_text pp icon_dt" id="date02" title="请选择结束时间" value="${param.addTimeEnd }" readonly="readonly"></td>
                </tr>
                <tr>
                    <td colspan="6" class="t_center">
                        <input class="input-btn w200" type="submit" value="查询">&nbsp;&nbsp;&nbsp;&nbsp;
                        <input class="weak-btn small-btn w150" type="button" value="新增" onclick="location.href='addNew.html'">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI=""  partialList="true" size="resultSize">
        <display:column title="活动名称" property="name" >
        </display:column>
        <display:column title="页面地址">
        	<c:if test="${row.status ==1 }"> <%-- 没有发送的，不显示链接 --%>
                ${row.url }
            </c:if>
        </display:column>
        <display:column title="添加时间" property="sys0AddTime"/>
        <display:column title="添加人" property="addUser"/>
        <display:column title="修改人" property="updUser"/>
        <display:column title="状态" >
			<span class="sendStatusSpan">
				<c:if test="${row.status == 0 }">未发布</c:if>
				<c:if test="${row.status == 1 }">已发布</c:if>
			</span>
        </display:column>
        <display:column title="操作">
            <a class="blue" href="view.html?id=${row.id }">查看</a>
            <a class="blue edit" href="edit.html?id=${row.id }">编辑</a>
            <c:if test="${row.status ==0 }"> <%-- 没有发送的，可以编辑和删除 --%>
                <a class="blue delete" href="delete.html?id=${row.id }">删除</a>
                <a class="blue publish" href="publish.html?id=${row.id }">发布</a>
            </c:if>
        </display:column>
    </display:table>
</div>

</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="../js/jquery.form.js?v=5"></script>
<script src="${resourcePath}/commonjs/layer/layer.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.common.js?v20161128"></script>
<script type="text/javascript" src="../js/select2.js"></script>
<script type="text/javascript">
    $("a.delete").click(function(){
        if(!window.confirm("确认要删除吗？")){
            return false;
        }
    });

    $("a.publish").click(function(){
        if(!window.confirm("确认要发布吗？")){
            return false;
        }
    });

    $("a.sendBtn").click(function(e){
        e.preventDefault();
        if(window.confirm("确认要发布吗？")){
            var layermsg = layer.msg('正在发布，请稍候', {
                icon: 16,
                time: 0,	//指定time为0，默认该消息不关闭；若2秒后，再弹出layer.alert('加载完成', 1);该消息同时关闭。
                shade: 0.5
            });

            $this = $(this);
            var id = $this.attr("data-id");
            //send.html?id=${row.id }
            var $sendStatusSpan = $this.parents("tr").find(".sendStatusSpan");
            $sendStatusSpan.text("发送中");
            $.ajax({//使用ajax请求
                url : "send.json?id=" + id,
                async : true,
                success : function(data) {
                    if(data.status == "0000"){
                        alert("操作提示：" + data.message);
                        location.reload();
                    }else{
                        alert("发送失败，失败原因："+ data.message);
                    }
                },
                error: function(){
                    layer.close(layermsg);
                    alert("网络错误");
                }
            });
            return false;
        }
    });
</script>

</html>
