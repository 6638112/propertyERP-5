<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>我的订单</title>
<link rel="stylesheet" href="css/shopping.common.css?v20170517b">
<script>

</script>
</head>

<body class="bggrey pos-relative">
<header class="sectionBox fantasia-header displaybox newheader-bgcolor">
	<a class="disblock p010 left" href="javascript:history.back();"><img class="back-arrow" src="../images/back_black.png" /></a>
	<div ><span class="header-title">我的订单</span></div>
	<a class="disblock p010 boxflex01 t-right histroylink" href="myAppointment.do?histroy=1">
		<span>历史订单</span>
	</a>
</header>

<section id="wrapBox" class="sectionBox bggrey pos-relative minheight100">
	<section id="tabBox" class="tabBox pos-relative zindex10">
	    <div class="hd tab-head bgwhite dsn">
	        <ul class="order-tab">
	            <li id="orderTab01" class="wp50 tap-nobg order-tab-title" data-page="1" data-next="true" status="1"><a href="javascript:void(0)">已预约</a></li>
	            <%--<li id="orderTab02" class="wp50 tap-nobg order-tab-title" data-page="1" data-next="true" status="2"><a href="javascript:void(0)">已结束</a></li>--%>
	        </ul>
	    </div>
	    <%--<section class="divide-box bordertbgrey"></section>--%>
	    
	   <div class="my-order-item bgwhite box-shadow mbottom10 mright10 mleft10 ptb15 hide">
	       <a class="singleInfoBox" href="#">
				<div class="displaybox order-pay-info">
					<div class="div-item-icon-new">
						<img class="icon-yyorder-type" src="images/icon_noimg.png" >
					</div>
					 <div class="boxflex01">
						 <div>
						 	<span class="f15 bold dataValueParentTypeName"></span>
						 	<span class="f14 grey dataValueType"></span>
						 </div>
						 <div class="f14 blue mtop5 word-break1 dredgeProductName lineheight120"></div>
					 </div>
					 <div class="mright15">
						 <%--<img class="w10 order-title-buff-icon" src="images/icons-shop-clock-grey.png" />--%>
						 <div class="t-right">
							 <span class="f15 red order-title-buff-text"></span>
						 </div>
						 <div class="t-right mtop5 hide">
							 <span class="bgred white p010 radius2px">待支付</span>
						 </div>
					 </div>
				</div>
		   </a>

		   <a class="singleInfoBox" href="#">
				<div class="displaybox order-pay-detail order-pay-amount mtop10 mleft46 heightauto p00">
					<div class="boxflex01">
						<div class="displaybox material-item">
							<br><span class="f12 orderStatusStr"></span><img class="icon-yugu hide" src="images/predictAlerticon.png" />
						</div>
					</div>

					<div class="mright15 lineheightnormal">
						<span class="f24 orderPayMoney"></span>
						<!-- <input class="order-status-btn btn-borderred btnSubmit" type="button" name="button" value="确认完成"> -->
					</div>
				</div>
		   </a>

		   <a class="singleInfoBox" href="#">
				<div class="displaybox mleft46">
					 <ul class="order-info-address mtop5 boxflex01">
						 <li class="grey">
							 <div class="displaybox">
								 <div class="w80">订单号</div>
								 <div class="t-right wp100 mright15 word-break1 lineheightnormal">
									 <span class="order-number-text"></span>
								 </div>
							 </div>
						 </li>
						 <li class="grey">
							 <div class="displaybox">
								 <div class="w80">预约地址</div>
								 <div class="t-right wp100 mright15 word-break1 lineheightnormal">
									 <span class="icon-address-text"></span>
								 </div>
							 </div>
						 </li>
						 <li class="mtop10 hide"><span class="icon-maintain-text"></span></li>
						 <li class="mtop5 grey">
							 <div class="displaybox icon-expectdate">
								 <div class="w80">预约时间</div>
								 <div class="t-right wp100 mright15">
									 <span class="icon-expectdate-text"></span>
								 </div>
							 </div>
							 <div class="displaybox icon-datedoortime hide">
								 <div class="w80 red">上门时间</div>
								 <div class="t-right wp100 mright15">
									 <span class="icon-datedoortime-text red"></span>
								 </div>
							 </div>
						 </li>
					 </ul>
				</div>
		   </a>
		   
			<div class="displaybox flex-end mleft46 mright15 mtop10 bordertopgrey item-list-arrow-box order-pay-detail heightauto p00">
				<div class="mtop10 boxflex01 t-right cancel-btn hide">
					<input class="order-status-btn bgdarkgrey white noborder radius2px" type="button" name="button" value="取消订单">
				</div>
				<div class="mtop10 mleft10 check-btn">
					<a class="singleInfoBox" href="#">
						<input class="order-status-btn btnSubmit btn-bgred" type="button" name="button" value="确认完成">
					</a>
				</div>
			</div>
		   <%--<section class="divide-box bordertbgrey"></section>--%>
	   </div>

		<div class="displaybox order-info-box mtop10 hide" style="padding: 0">
			<div class="displaybox bordertbgrey mleft46 mright15 wp100">
				<a class="singleInfoBox boxflex01" href="#">
					<div class="displaybox">
						<div class="master-info-img">
							<img src="images/master_info_img.png" />
						</div>
						<ul class="order-info-address boxflex01">
							<li>
								<div class="icon-person master-info-name f16 lineheightnormal mtop3">李师傅</div>
								<div class="star-box">
									<div class="star-shape"><img src="images/stars_small.png" /></div>
									<div class="star-bgred" data-width="90%"></div>
									<div class="star-bggrey"></div>
								</div>
							</li>
						</ul>
					</div>
				</a>

				<div class="order-info-phone-call noborder"><a href="tel:400-960-2228"><img src="images/icon-phone-call.png" /></a></div>
			</div>
		</div>

	    <div class="bd my-order" id="tabBox-bd">
	        <div class="con">
	            <div id="tabOne">
	                <div class="myOrderList ptop3">
	                	<div class="sectionBox loading grey mtopminus10"><img src="images/loading01.gif" /> 加载中…</div>
	                </div>
	            </div>
	        </div>
	    </div>
	</section>
	
	<section class="sectionBox wrap-bg hide">
		<div class="tips-box">
			<div class="t-center ptb20 borderbottomgrey">预估费用是平台针对该服务的<br>建议价格，最终价格根据师傅上<br>门视实际情况和您协商最终确定</div>
			<ul class="displaybox">
				<li class="boxflex01 ptb10 t-center back-btn">我知道了</li>
			</ul>	
		</div>
	</section>
	
</section>
<section class="pop-tips dsn">
	<div class="pop-tips-text">请选择</div>
</section>
<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script src="js/method.common.js"></script>
<script src="js/orderList.js?v20171027"></script>
</body>
</html>