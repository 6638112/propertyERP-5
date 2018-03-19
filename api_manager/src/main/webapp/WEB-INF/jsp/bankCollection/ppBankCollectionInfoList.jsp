<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%--<base href="<%=basePath%>//"/>--%>
    <title>业主托收信息</title>
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <link rel="stylesheet" type="text/css" href="../css/select2.css">
</head>

<body>
<div class="info">
    <h2>业主托收信息</h2>
    <form action="<%=basePath%>/bankCollection/ppbankCollectionInfo.html" method="post">
        <div class="bs-example bgebeb">
            <table class="info-list" border="0">
                <tr>
                    <td width=""><div class="list-name">小区名称：</div></td>
                    <td>
                        <select id="groupBuiliding1" name="gbId" class="select_normal select2_class" datatype="*" nullmsg="请选择小区！">
                            <option value="">选择小区</option>
                        </select>
                    </td>
                    <td><div class="list-name">楼栋：</div></td>
                    <td><input type="text" name="buildingName" class="input_text w120 pp" value="${param.buildingName}"></td>
                    <td><div class="list-name">单元：</div></td>
                    <td><input type="text" name="unitName" class="input_text w120 pp" value="${param.unitName}"></td>
                    <td><div class="list-name">房号：</div></td>
                    <td><input type="text" name="rrNum" class="input_text w120 pp" value="${param.rrNum}"></td>
                    <td><div class="list-name">房间编号：</div></td>
                    <td><input type="text" name="roomNo" class="input_text w120 pp" value="${param.roomNo}"></td>
                <tr>
                <tr>
                    <td><div class="list-name">开卡人姓名：</div></td>
                    <td><input type="text" name="bankOwnerName" class="input_text w120 pp" value="${param.bankOwnerName}"></td>
                    <td><div class="list-name">手机号：</div></td>
                    <td><input type="text" name="ppPhone" class="input_text w120 pp" value="${param.ppPhone}"></td>
                    <td><div class="list-name">银行卡号：</div></td>
                    <td><input type="text" name="bankAccount" class="input_text w120 pp" value="${param.bankAccount}"></td>
                    <td><div class="list-name">开卡银行：</div></td>
                    <td><input type="text" name="bankName" class="input_text w120 pp" value="${param.bankName}"></td>
                    <td class="t_center"><input class="input-btn w200" type="submit" value="查询"></td>
                    <td class="t_center"><input class="input-btn w200" type="button" onclick="showImportTmpBillDialog();" value="导入"></td>
                </tr>
            </table>
        </div>
    </form>
    <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize" >
        <display:column title="小区名称" property="gbName" />
        <display:column title="楼栋" property="buildingName" />
        <display:column title="单元" property="unitName" />
        <display:column title="房号" property="rrNum" />
        <display:column title="手机号" property="ppPhone" />
        <display:column title="房间编号" property="roomNo" />
        <display:column title="业主托收银行卡号" property="bankAccount" />
        <display:column title="开卡银行" property="bankName" />
        <display:column title="开卡人姓名" property="bankOwnerName" />
        <display:column title="操作">
            <a class="blue viewTicket" href="<%=basePath%>/bankCollection/goPPBankCollectionInfoEdit.html?id=${row.id}&showType=show">查看</a>&nbsp;&nbsp;
            <a class="blue viewTicket" href="<%=basePath%>/bankCollection/goPPBankCollectionInfoEdit.html?id=${row.id}&showType=edit">编辑</a>
        </display:column>
    </display:table>

</div>
<div class="layer-bill ppInfoimportDiv dsn" style="height: auto;padding-top:15px;padding-bottom:50px;">
    <form enctype="multipart/form-data" id="ppInfoimportForm" class="inputform1" action="../bankCollection/importPpInfoData.json" method="post">
        <h2>第一步:下载模板</h2>
        <div class="bs-example bgebeb">
            <table class="info-list" border="0">
                <tr>
                    <td style="width: 60px;"><div class="list-name">小区：</div></td>
                    <td>
                        <select id="groupBuiliding" name="gbId" class="select_normal select2_class" datatype="*" nullmsg="请选择小区！">
                            <option value="">选择小区</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><div class="list-name">下载：</div></td>
                    <td><a id="downPpInfoBtn" class="blue" href="javascript:void(0)"><img src="../images/download-icon.jpg" />下载业主托收信息模版</a></td>
                </tr>
            </table>
        </div>
        <h2>第二步:上传数据</h2>
        <div class="bs-example bgebeb">
            <table class="info-list" border="0">
                <tr>
                    <td>
                        <input id="ppInfoexcelFileInput" name="excelFile" type="file" style="width: 300px;" title="上传" />
                        <input class="img-upload-val dsn" type="text" value="" datatype="*" nullmsg="请选择excel！">
                    </td>
                    <td><input id="uploadPpInfoBtn" class="input-btn w160" type="button" value="上传" /></td>
                </tr>
            </table>
            <span class="red dsn" id="uploadTips">数据正在上传中，请稍候…</span>
        </div>
    </form>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/layer.min.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="../js/select2.js"></script>
<script type="text/javascript">
    function showImportTmpBillDialog(){
        //加载小区信息
        initGroupBuilding();

        $.layer({
            type: 1,
            shade: [0.4,'#000000'],
            area: ['auto', 'auto'],
            title: false,
            border : [5, 0.3, '#000'],
            page: {dom : '.ppInfoimportDiv'}
        });
    }

    function initGroupBuilding() {
        $.ajax({
            url: '../bankCollection/initEdit.json',
            dataType: 'json',
            success: function(data){
                var list = JSON.parse(data).gbList;
                var strHtml = "<option value='''>选择小区</option>";
                $.each(list, function(i, item) {
                    strHtml += "<option value='" + item.id + "'>" + item.name + "</option>";
                });
                $("#groupBuiliding").html(strHtml);
                //清除原来的内容
                $(".ept").val("");
            }
        });
    }
    $(function() {
    	$('.select2_class').select2();
        //上传excel
        $("#uploadPpInfoBtn").click(function () {
            if ($("#ppInfoexcelFileInput").val() == "") {
                alert("请选择excel！");
                return false;
            } else {
                //增加菊花
                var layermsg = null;
                layermsg = layer.msg('正在导入，请稍候', {
                    icon: 16,
                    time: 0,	//指定time为0，默认该消息不关闭；若2秒后，再弹出layer.alert('加载完成', 1);该消息同时关闭。
                    shade: 0.5
                });
                $("#uploadPpInfoBtn").attr("disabled", true);
                $("#uploadPpInfoBtn").css({
                    "background": "#DCDCDC",
                    "color": "#FFFFFF",
                    "border": "0px",
                    "cursor": "wait"
                });
                $("#ppInfoimportForm").submit();
            }
        });

        //下载导入模板
        $("#downPpInfoBtn").click(function() {
            var gbId = $("#groupBuiliding").val();
            if(gbId == '') {
                alert("请先选择小区在进行下载！");
                return false;
            }
            location.href=  "${pageContext.request.contextPath}/bankCollection/exportPpInfoTemplate.json?gbId="+gbId;
        });

        getGbList()
    });

    function getGbList() {
        var gbId = '${param.gbId}';
        $.ajax({
            url: '<%=basePath%>/groupBuilding/getGroupBuildings.json',
            dataType: 'json',
            success: function (data) {
                var list = JSON.parse(data);
                var strHtml = "<option value=''>选择小区&nbsp;&nbsp;&nbsp;</option>";
                $.each(list, function (i, item) {
                    var str = "";
                    if(gbId == item.id) {
                        str = "<option value='" + item.id + "' selected='selected'>" + item.name + "</option>";
                    } else {
                        str = "<option value='" + item.id + "'>" + item.name + "</option>";
                    }
                    strHtml += str;
                });
                $("#groupBuiliding1").html(strHtml);
            }
        });
    }
</script>
</html>
