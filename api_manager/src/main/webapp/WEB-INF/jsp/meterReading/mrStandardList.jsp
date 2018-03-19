<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <base href="<%=basePath%>//" />
    <title>抄表收费标准</title>
    <link rel="stylesheet" type="text/css" href="css/common.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
<div class="info" style=" min-width: 960px;" gbId="${gbId}">
	<div class="side-bar left mright15 pright15 borderright">
    	<ul class="menu_01">
            <span class="side-bar-memu01 menu_ar">香栗园小区</span>
            <li class="menu_02">
            	<!--<ul class="menu_01">
                    <span class="side-bar-memu02">2栋</span>
                    <li class="side-bar-memu03">1单元1101</li>
                    <li class="side-bar-memu03">1单元1101</li>
                    <li class="side-bar-memu03">1单元1101</li>
                    <li class="side-bar-memu03">1单元1101</li>
                </ul>-->
            </li>
        </ul>
        <ul class="menu_01 dsn">
            <span class="side-bar-memu02 menu_ar">1栋</span>
        </ul>
        <li class="side-bar-memu03 dsn">1单元1101</li>
	</div>
	<div class="left">
	    <form class="inputform" action="">
	    	<div class="wrap-box01">
		        <h2>小区配置</h2>
	            <table class="info-list-02 mtop20" style=" min-width: 600px;" border="0" cellpadding="0" cellspacing="1" data-type="1">
			      <thead>
			      <tr class="title">
			        <th>小区</th>
			        <th>收费项</th>
			        <th>收费标准</th>
			        <th>操作</th>
			      </tr>
			      </thead>
			      <tr class="gb-tr dsn">
			        <td class="gb-name">米兰公寓</td>
			        <td class="fee-item-name">水费</td>
			        <td>
		                <select class="select_normal widthauto fee-standard">
							<option value="-1">请选择收费标准</option>
			            </select>
			        </td>
			        <td><a class="blue gb-save-btn" href="javascript:void(0)">保存</a></td>
			      </tr>
			    </table>
	    	</div>
	    	<div class="wrap-box02 dsn">
		        <h2>楼栋配置</h2>
	            <table class="info-list-02 mtop20" style=" min-width: 600px;" border="0" cellpadding="0" cellspacing="1" data-type="2">
			      <thead>
			      <tr class="title">
			        <th>楼栋</th>
			        <th>收费项</th>
			        <th>收费标准</th>
			        <th>操作</th>
			      </tr>
			      </thead>
			      <tr class="building-tr dsn">
			        <td class="building-name">1栋</td>
			        <td class="fee-item-name">水费</td>
			        <td>
		                <select class="select_normal widthauto fee-standard">
							<option value="-1">请选择收费标准</option>
			            </select>
			        </td>
			        <td><a class="blue building-save-btn" href="javascript:void(0)">保存</a></td>
			      </tr>
			    </table>
	    	</div>
	    	<div class="wrap-box03 dsn">
		        <h2>单元配置</h2>
	            <table class="info-list-02 mtop20" style=" min-width: 600px;" border="0" cellpadding="0" cellspacing="1" data-type="3">
			      <thead>
			      <tr class="title">
			        <th>楼栋</th>
			        <th>单元</th>
			        <th>房号</th>
			        <th>业主姓名</th>
			        <th>收费项</th>
			        <th>收费表名称</th>
			        <th>计量倍率</th>
			        <th>收费标准</th>
			        <th colspan="2">操作</th>
			      </tr>
			      </thead>
			       <tr class="realRoom-tr dsn">
			        <td class="building-name">1栋</td>
			        <td class="unit-name">1单元</td>
			        <td class="room-name">1101</td>
			        <td class="user-name">张</td>
			        <td>
		                <select class="select_normal widthauto fee-item-name">
			            </select>
			        </td>
			        <td><input class="input_text w100" type="text" name="mrName" maxlength="20" /></td>
			        <td><input class="input_text w50" type="text" name="multiplierTimes" maxlength="10" /></td>
			        <td>
		                <select class="select_normal widthauto fee-standard">
							<option value="-1">请选择收费标准</option>
			            </select>
			        </td>
			        <td><a class="blue realRoom-save-btn" href="javascript:void(0)">保存</a></td>
			        <td><a class="blue realRoom-delete-btn" href="javascript:void(0)">删除</a></td>
			      </tr>
			    </table>
			     
		        <input class="info-btn w100 realRoom-add-btn" type="button" value="添加" />
	    	</div>
	        <div class="h30"></div>
	    </form>
	</div>
</div>

</body>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/jquery.common.js"></script>
<script type="text/javascript" src="js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="js/property.meterReading.js"></script>
<script type="text/javascript" src="js/layer.min.js"></script>

</html>
