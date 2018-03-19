<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>我的预约</title>
<link rel="stylesheet" href="css/shopping.common.css?v20160817">
<link rel="stylesheet" href="${resourcePathHttps}/dist/photoswipe.css">
<link rel="stylesheet" href="${resourcePathHttps}/dist/default-skin/default-skin.css">
<style>
.my-gallery {   width: 100%; float: left; }
.my-gallery img { max-width: 100%; width: 90px; height:90px; }
.my-gallery figure { display: block; float: left; margin: 0 5px 0 0; width: 90px; height:90px; }
.my-gallery figcaption { display: none; }
</style>
</head>

<body class="bggrey">
<section id="wrapBox" class="sectionBox bggrey pos-relative minheight100" data-url="${apiPath}">
	<section id="tabBox" class="tabBox pos-relative minheight100 zindex10">
	    <div class="bd my-order" id="tabBox-bd">
	        <div class="myOrderList bgwhite">
	           <div class="my-order-item">
					<c:if test="${not empty dataValue.payAmount }">
						<div class="payStatus statusInfo hide">
							<section class="sectionBox addressInfo black paid-info bordertopgrey">
								<ul class="p10 t-center">
							    	<li class="mtop5"><span class="f14 mright10 grey">待支付金额（元）</span></li>
							    	<li class="paid-num lineheightnormal h57"><span id="orderShouldPrice">${dataValue.payAmount }</span></li>
							    	<li>
							    		<input id='confirmPayBtn' class="btn-borderred" type="submit" name="submit" value="确认完成">
							    	</li>
							    </ul>
							</section>
						</div>
					</c:if>
					<!-- <section class="divide-box bordertbgrey"></section> -->
	                <div class="displaybox h70 close-desc-info-box hide">
	                    <div class="order-title-time boxflex01 close-desc-info"><span class="f16">${dataValue.statusDesc }</span><br><span class="grey">${dataValue.propertyCloseDesc }</span></div>
	                </div>
					<section class="divide-box bordertbgrey close-desc-info-box hide"></section>
		            
		            
		            
					<div class="order-title-info-new">
						<section class="sectionBox addressInfo black paid-info bordertopgrey">
							<div class="list-box bggrey t-center borderbottomdash"><div class="f16 height36 dataValueType">${dataValue.type }</div></div>
							
				            <!-- 派单中  -->
				            <c:if test="${dataValue.status==1 && dataValue.billType != 3}">
								<ul id="commonStatusBox" class="p10 t-center">
							    	<li class="mtop5"><span class="f14 grey order-title-buff-text">${dataValue.statusDesc }</span></li>
							    	<li class="paid-num lineheightnormal t-center mtop10"><img class="w60 marginauto" src="images/waitAcceptOrder@2x.png" /></li>
							    	<li><span class="f16">师傅正赶来接单</span></li>
							    	<li class="mtop5"><span class="f14 grey">下单后将尽快进行处理</span></li>
							    	
							    	<!-- 取消订单按钮，详情页不显示20171023 -->
							    	<%-- <c:if test="${dataValue.billType==5 }">
								    	<li class="mtop10">
							    			<input class="details-common-btn btn-grey radius2 black cancelOrderBtn" type="button" value="取消订单">
								    	</li>
							    	</c:if> --%>
							    </ul>
								<ul id="paySuccessBox" class="p10 t-center hide">
							    	<li class="paid-num lineheightnormal t-center mtop10"><img class="w60 marginauto" src="images/pay_success.png" /></li>
							    	<li><span class="f16 red">支付成功</span></li>
							    	<li class="paid-num lineheightnormal h57">￥<span id="orderShouldPrice">${dataValue.payAmount }</span></li>
							    	<li>
							    		<input onclick="location.reload()" class="details-common-btn btn-bgred radius2 black" type="button" value="确定">
							    	</li>
							    </ul>
				            </c:if>
				            
				            <!-- 已接单，尚未设置金额  -->
				            <c:if test="${dataValue.status==2 }">
								<ul class="p10 t-center">
							    	<li class="mtop5"><span class="f14 grey order-title-buff-text">${dataValue.statusDesc }</span></li>
							    	<li class="paid-num lineheightnormal t-center mtop10">
							    		<img class="master-info-img-details marginauto" src="${dataValue.repairUrl }<c:choose><c:when test="${fn:contains(dataValue.repairUrl, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,w_92,h_92/format,jpg/interlace,1" />
							    	</li>
							    	<li><span class="f16">${dataValue.repairName }</span></li>
							    	<li class="mtop10">
							    		<a href="tel:${dataValue.repairPhone }">
							    			<input class="details-common-btn btn-bgred radius2 black" type="button" value="联系师傅">
							    		</a>
							    	</li>
							    </ul>
	            			</c:if>
				            
				            <!-- 已完成  -->
				    		<c:if test="${dataValue.status==3 || dataValue.status==5 }">
								<ul class="p10 t-center">
							    	<li class="mtop5"><span class="f14 grey order-title-buff-text">${dataValue.statusDesc }</span></li>
							    	<li class="paid-num lineheightnormal h57"><span id="orderShouldPrice">${dataValue.payAmount }</span></li>
							    </ul>
	            			</c:if>
							
				            <!-- 已取消  -->
				            <c:if test="${dataValue.status==4}">
								<ul class="p10 t-center">
							    	<li class="paid-num lineheightnormal t-center mtop10"><img class="w60 marginauto" src="images/order_cancel@x2.png" /></li>
							    	<li><span class="f16">订单已取消</span></li>
							    	<li class="mtop5"><span class="f14 grey">如有意见或建议，欢迎致电解放区客服</span></li>
							    </ul>
				            </c:if>
				            
				            <!-- 前付款，已接单，师傅已服务完成  -->
				    		<c:if test="${dataValue.billType==5 && dataValue.status==8 }">
								<ul class="p10 t-center">
							    	<li class="mtop5"><span class="f14 mright10 grey">待支付金额（元）</span></li>
							    	<li class="paid-num lineheightnormal h57"><span id="orderShouldPrice">${dataValue.payAmount }</span></li>
							    	<li class="mtop10">
							    		<input id="billComplete" class="details-common-btn btn-bgred radius2 black" type="button" value="确认完成">
							    	</li>
							    </ul>
	            			</c:if>
				            
						</section>
					</div>
				    
		    		<c:if test="${dataValue.selfBuyCount > 0 }">
					    <div class="item-select-entrance bordertopgrey">
					    	<a href="../dredge/viewSelfBuyProductList.do?dredgeBillId=${dataValue.id }&dredgeTypeId=${dataValue.dredgeTypeId }&subTypeId=${dataValue.subTypeId }&billType=${dataValue.billType }">
							    <div class="list-box bgwhite displaybox">
						            <div class="item-standard-name f16">耗材费<span class="grey f12"></span></div>
						            <div class="boxflex01 box-arrow t-right red">${dataValue.selfBuyCount }件，合计￥<span id="costOfItems">${dataValue.selfBuyAmount }</span></div>
							    </div>
				            </a>
							<section class="divide-box bordertopgrey"></section>
						</div>
					</c:if>
					
					<!-- 是否有规格或耗材、人工费等 -->
					<c:set var="hasItemsList" value="${(not empty dataValue.dredgeProductSpecList) or (not empty dataValue.selfBuyProductList)}"/>
					<c:if test="${hasItemsList or (not empty dataValue.amountList)}">
						<section id="serverDetailBox" class="user-info-title bordertopgrey">
							<span class="disblock p015 f14 lineheightnormal">
								<span class="list-name grey">服务详情</span><br/>
								<c:if test="${hasItemsList}">
							    	<table class="service_detail mtop5">
										<c:forEach items="${dataValue.dredgeProductSpecList }" var="item"> 
								        	<tr>
												<td class="td1">${item.specName}</td>
												<td class="td2">
													￥<fmt:formatNumber value="${(item.specPrice+0.00000000001)/100.0 + 0.000001}" type="currency" pattern="0.00" maxFractionDigits="2"/>
													<c:if test="${not empty item.specUnit}">
														/${item.specUnit}
													</c:if>
													<c:if test="${not empty item.specNum}">
														 ×${item.specNum}
													</c:if>
												</td>
											</tr>
								        </c:forEach>
								        <c:forEach items="${dataValue.selfBuyProductList }" var="item"> 
								        	<tr>
												<td class="td1">${item.name}</td>
												<td class="td2">￥ ${item.price} x${item.quantity}</td>
											</tr>
								        </c:forEach>
									</table>
								</c:if>
								<!-- 先付款订单，没有人工费，所以不显示 -->
								<c:if test="${dataValue.billType != 5}">
							    	<table class="service_detail ptop5 mtop5">
								        <c:forEach items="${dataValue.amountList }" var="item">
											<c:if test="${item.feeAmount > 0}">
									        	<tr>
													<td class="td1 fee-name">${item.feeName}</td>
													<td class="td2">￥ <span class="fee-amount">${item.feeAmount }</span></td>
												</tr>
											</c:if>
								        </c:forEach>
									</table>
								</c:if>
						    </span>
						</section>
					</c:if>
					
					<section class="user-info-title orderDetailsBox bordertopgrey">
						<span class="disblock p015 f14 lineheightnormal">
							<span class="list-name grey">订单详情</span><br/>
							<table class="other mtop5">
								<tr>
									<td class="td1 grey">预约地址</td>
									<td class="td2">${dataValue.address }</td>
								</tr>
								<tr>
									<td class="td1 grey">预约时间</td>
									<td class="td2">${dataValue.expectDate }</td>
								</tr>
								<tr>
									<td class="td1 grey">联系信息</td>
									<td class="td2">${dataValue.userPhone} ${dataValue.linkName}</td>
								</tr>
								<tr style="vertical-align: top;">
									<td class="td1 grey">订单备注</td>
									<td class="td2">
										<c:if test="${not empty dataValue.content}">
											${dataValue.content }<br>
										</c:if>
										
										<c:if test="${not empty dataValue.picInfo and fn:length(dataValue.picInfo)>0 }">
										    <section class="bgwhite image-view pb20">
										        <div class="my-gallery" itemscope>
										        	<c:forEach items="${dataValue.picInfo }" var="picInfo"> 
											            <figure class="mtop5" itemprop="associatedMedia" itemscope>
											              <a href="${picInfo }<c:choose><c:when test="${fn:contains(picInfo, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,w_640/format,jpg/interlace,1/quality,q_80" data-size="640x540">
											                  <img src="${picInfo }<c:choose><c:when test="${fn:contains(picInfo, '?')}">&</c:when><c:otherwise>?</c:otherwise></c:choose>x-oss-process=image/resize,m_fill,w_110,h_110/format,jpg/interlace,1/quality,q_80" itemprop="thumbnail" alt="Image description" />
											              </a>
											            </figure>
										        	</c:forEach>
										        </div>
										    </section>
										</c:if>
									</td>
								</tr>
							</table>
					    </span>
					</section>
					
				    <!-- Root element of PhotoSwipe. Must have class pswp. -->
				    <div class="pswp" tabindex="-1" role="dialog" aria-hidden="true">
				        <!-- Background of PhotoSwipe. 
				             It's a separate element, as animating opacity is faster than rgba(). -->
				        <div class="pswp__bg"></div>
				        <!-- Slides wrapper with overflow:hidden. -->
				        <div class="pswp__scroll-wrap">
				            <!-- Container that holds slides. PhotoSwipe keeps only 3 slides in DOM to save memory. -->
				            <!-- don't modify these 3 pswp__item elements, data is added later on. -->
				            <div class="pswp__container">
				                <div class="pswp__item"></div>
				                <div class="pswp__item"></div>
				                <div class="pswp__item"></div>
				            </div>
				            <!-- Default (PhotoSwipeUI_Default) interface on top of sliding area. Can be changed. -->
				            <div class="pswp__ui pswp__ui--hidden">
			                    <div class="pswp__top-bar">
			                        <!--  Controls are self-explanatory. Order can be changed. -->
			                        <div class="pswp__counter"></div>
			                        <button class="pswp__button pswp__button--close" title="Close (Esc)"></button>
			                        <button class="pswp__button pswp__button--zoom" title="Zoom in/out"></button>
			                        <!-- Preloader demo http://codepen.io/dimsemenov/pen/yyBWoR -->
			                        <!-- element will get class pswp__preloader--active when preloader is running -->
			                        <div class="pswp__preloader">
			                            <div class="pswp__preloader__icn">
			                              <div class="pswp__preloader__cut">
			                                <div class="pswp__preloader__donut"></div>
			                              </div>
			                            </div>
			                        </div>
			                    </div>
			                    <div class="pswp__share-modal pswp__share-modal--hidden pswp__single-tap">
			                        <div class="pswp__share-tooltip"></div> 
			                    </div>
			                    <button class="pswp__button pswp__button--arrow--left" title="Previous (arrow left)">
			                    </button>
			                    <button class="pswp__button pswp__button--arrow--right" title="Next (arrow right)">
			                    </button>
			                    <div class="pswp__caption">
			                        <div class="pswp__caption__center"></div>
			                    </div>
				             </div>
				        </div>
				    </div>
					
					<c:if test="${not empty dataValue.payAmount }">
					<div class="payStatus statusInfo">
						<section class="user-info-title bordertopgrey">
							<span class="disblock p015 f14 lineheightnormal">
						    	<span class="list-name red">已优惠</span><span class="red">-${dataValue.totalCouponAmount }</span><br/>
						        <span class="list-name">支付方式</span>
					                <c:if test="${dataValue.payMethod eq 1}">微信支付</c:if>
					                <c:if test="${dataValue.payMethod eq 2}">支付宝钱包支付</c:if>
					                <c:if test="${dataValue.payMethod eq 3}">银联支付</c:if>
					                <c:if test="${dataValue.payMethod eq 4}">纯粮票支付</c:if>
					                <c:if test="${dataValue.payMethod eq 5}">纯积分支付</c:if>
					                <c:if test="${dataValue.payMethod eq 6}">微信轻应用支付</c:if>
					                <c:if test="${dataValue.payMethod eq 7}">纯消费券支付</c:if><br/>
						        <span class="list-name">交易时间</span>${dataValue.tradeTime }<br/>
						        <span class="list-name">订单号</span>${dataValue.billNo }</span>
						</section>
					</div>
					</c:if>
					
					<div class="cancelStatus statusInfo orderDetailsBox">
						<section class="divide-box bordertopgrey"></section>
					    <div class="list-box bgwhite bordertbgrey">
					        <div class="displaybox">
					            <div class="item-standard-name height36 f16 boxflex01">联系客服</div>
					            <div class="boxflex01 f16 t-right grey"><a class="green" href="tel:${dataValue.servePhone}">${dataValue.servePhone}</a></div>
					        </div>
					    </div>
					</div>
					<div class="hasBtnStatus statusInfo hide">
						<section class="divide-box h57"></section>
					</div>
	           </div>
	        </div>
	    </div>
	</section>
</section>

<section class="sectionBox wrap-bg evaluate_box hide">
	<div class="wp100 wrap-bg evaluate_bg"></div>
	<div class="tips-box f16 pb20">
		<div class="tip-close-text">轻触关闭</div>
		<div class="t-center mtop15 ptb20">请评价本次服务</div>
		
		<c:forEach items="${dataValue.cpList}" var="item">
			<ul class="displaybox stars_list">
			<li class="ptb10 mright10">${item.name}</li>
			<li class="boxflex01">
				<ul class="displaybox evaluate_stars" data-itemId="${item.id}" data-starsNum="">
					<li class="boxflex01 pointer"><img src="images/star_grey.png"/></li>
					<li class="boxflex01 pointer"><img src="images/star_grey.png"/></li>
					<li class="boxflex01 pointer"><img src="images/star_grey.png"/></li>
					<li class="boxflex01 pointer"><img src="images/star_grey.png"/></li>
					<li class="boxflex01 pointer"><img src="images/star_grey.png"/></li>
				</ul>	
			</li>
			</ul>
		</c:forEach>
		
		<div id="evaluateSubmitBtn" class="pointer"><input class="btn-submit bgred white" type="button" value="提交" /></div>
		<div class="wp100 t-center f14"><a class="blue" href="tel:400-960-2228">投诉</a></div>
	</div>
</section>

<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePathHttps}/commonjs/Validform_v5.3.2.js"></script>
<script>
	$(function(){
		//旧订单没有服务详情，隐藏
		if($('#serverDetailBox').find('tr').length === 0){
			$('#serverDetailBox').hide()
		}
		
		//确认完成按钮
		$('#billComplete').click(function(){
			if(!confirm('确认完成订单？')){
				return false;
			}
			
			$.ajax({
				type:"post",
				url:"../common/toUrl.do",
				data:{"detailUrl" : "/dredge/finishBill.json", dredgeBillId: '${dataValue.id}'},
				dataType:"json",
				success:function(data){
					location.href = location.href + '&fromPay=true';
				},  
	            error: function(){  
	            	$.Showmsg('网络不给力，请稍后重试'); 
	            } 
			});
		});
		
		//处理耗材费多余小数问题
		$('#costOfItems').text(($('#costOfItems').text()*1).toFixed(2));
		
		//支付按钮
		$('.btnSubmit').click(function(){
			$('#wrapBox').addClass('heightp100');
			$('#checkTips').removeClass('hide');
		});
		$('.back-btn').click(function(){
			$('#wrapBox').removeClass('heightp100');
			$('#checkTips').addClass('hide');
		});
		
		//取消订单
		$('.cancelOrderBtn').click(function(){
			if(!confirm('是否取消订单？')){
				return false;
			}

			$.ajax({
				type:"post",
				url:"cancelDredge.json",
				data:{id : '${dataValue.id}'},
				dataType:"json",
				beforeSend:function(){
					$.Showmsg("正在取消…");
				},
				success:function(data){
					if (data.status == '0000') {
						$.Showmsg('订单取消成功！');
						window.location.reload();
					} else {
						$.Showmsg(data.message);

					}
				},  
	            error: function(){  
	            	$.Showmsg('网络不给力，请稍后重试'); 
	            } 
			});
		});

		//判断订单状态
 		var $cancelStatusInfo = $('.cancelStatus');			
		var $myOrderItem = $('.my-order-item');
		var detailStatus = '${dataValue.status}';
		var billType = '${dataValue.billType}';
		
		/*//console.log('detailStatus:',detailStatus,'billType:',${dataValue.billType});
		if(detailStatus == 1 && ${dataValue.billType != 3}){ //派单中
			$('.order-steps, .hasBtnStatus.statusInfo').removeClass('hide');
			$myOrderItem.find('.order-title-buff-icon').attr("src", "images/icons-shop-clock-grey.png");
			$myOrderItem.find('.order-title-buff').removeClass('orange').addClass("grey");	
		
		}else if((detailStatus == 2 || detailStatus == 7 || detailStatus == 8) && ${dataValue.billType != 3}){	//师傅已接单，并且已设置金额，新增状态7/8
			$('.order-steps, div.cancelStatus.statusInfo, .hasBtnStatus.statusInfo').removeClass('hide');
		}else if(detailStatus == 4){ //已取消
			$('.order-steps').removeClass('hide');
			$myOrderItem.find('.order-title-buff-icon').attr("src", "images/icons-shop-done.png");
			$myOrderItem.find('.order-title-buff').removeClass('orange').addClass("grey");
		
		}else if(detailStatus == 3 || detailStatus == 5){ //已完成
			$('.order-steps, div.cancelStatus.statusInfo, div.payStatus.statusInfo').removeClass('hide');
		}else if(detailStatus == 6){ //物业关闭
			$('.close-desc-info-box').removeClass('hide');
			$myOrderItem.find('.order-title-buff-icon').attr("src", "images/icons-shop-done.png");
			$myOrderItem.find('.order-title-buff').removeClass('orange').addClass("grey");
		}else if(detailStatus == 5){ //物业维修，已完成
			$('div.cancelStatus.statusInfo.masterInfo').removeClass('hide');
		}
		
		if(${dataValue.billType == 3}){
			$myOrderItem.find('.dataValueType').text('物业报修');
			$myOrderItem.find('.icon-maintain').removeClass('hide');
		} */

		//调整外容器高度样式
		$('.my-gallery figure').click(function(){
			$('#wrapBox').addClass('heightp100');
			var removeHeight = setInterval(function(){
				if($('.pswp').is(':hidden') && $('#wrapBox').hasClass('heightp100')){
					$('#wrapBox').removeClass('heightp100');
				}
			},100)
		});
		
		var wrapWidth = $('#wrapBox').width();
		$('.wrap-bg').css({'margin-left':-wrapWidth/2, 'left':'50%'});
		
		//关闭评价浮层
		$('.evaluate_bg, .tip-close-text').click(function(){
			$('#wrapBox').removeClass('heightp100');
			$('.wrap-bg').addClass('hide');
		});
		
		//判断是否从支付完成到此页面
		if(isFromPay()){
			
			//先付款订单，完成支付后，显示支付完成页面
			if(billType == 5 && detailStatus == 1){
				$('#commonStatusBox').addClass('hide');
				$('.orderDetailsBox').addClass('hide');
				$('#paySuccessBox').removeClass('hide');
			}else{
				$('#wrapBox').addClass('heightp100');
				$('.evaluate_box').removeClass('hide');
			}
			
			//替换历史记录，后退回到列表页
			history.replaceState({}, '我的订单', '../dredge/myAppointment.do');
		}
		
		//提交评价
		var issubmited = false;
		$('#evaluateSubmitBtn').click(function(){
			var starsNumTotal = 0;
			var evaluateNum = 0;
			var pointsListParams = {};
			
			//获取维度星级，维度个数
			$('.evaluate_stars').each(function(){
				var thisStarsNum = $(this).data('starsNum');
				var thisItemId = $(this).attr('data-itemId');
				
				if(thisStarsNum){
					pointsListParams[thisItemId] = thisStarsNum;
					
					evaluateNum += 1;
					starsNumTotal += thisStarsNum*1;
				}

			});
			
			pointsListParams = JSON.stringify(pointsListParams);
			
			var levelAverage = starsNumTotal/evaluateNum;
			
			//防止二次提交
			if(issubmited){
				return false;
			}
			$.ajax({
				url:"../common/toUrl.do",
				data:{"detailUrl" : "/comments/postComment.json", userId:'${sessionScope.regist3rdResponse.userId}', goalType:'122', goalId:'${dataValue.masterId}', goalId2nd:'${dataValue.id}', starLevel:levelAverage, pointsList:pointsListParams},
				dataType : "json",
				async:false,
				success:function(data){
					
					//已成功提交，设置issubmited为true
					issubmited = true;
					
					$.Showmsg('提交成功');
					
					$('#wrapBox').removeClass('heightp100');
					$('.wrap-bg').addClass('hide');
				}
			});
		});
		
		//选择星级
		$(document).on('click', '.evaluate_stars li', function(){
			
			var $this = $(this);
			var $thisEvaluateStars = $this.parent('.evaluate_stars');
			var thisIndex = $this.index();
			
			if($this.hasClass('on') && !$this.next('li').hasClass('on')){
				$this.removeClass('on').find('img').attr('src', 'images/star_grey.png');
				if(thisIndex === 0){
					thisIndex = '';
				}
				$thisEvaluateStars.data('starsNum', thisIndex);
				
			}else if($this.hasClass('on') && $this.next('li').hasClass('on')){
				$this.nextAll('li').removeClass('on').find('img').attr('src', 'images/star_grey.png');
				$thisEvaluateStars.data('starsNum', thisIndex + 1);
				
			}else{
				$this.addClass('on').find('img').attr('src', 'images/star_red.png');
				$this.prevAll('li').addClass('on').find('img').attr('src', 'images/star_red.png');	
				$thisEvaluateStars.data('starsNum', thisIndex + 1);
			}
			
			
		});
		
	});
	
	//判断是否从支付完成到此页面
	function isFromPay(){
		var thisSearch = location.search;
		if(thisSearch.indexOf('fromPay') > -1){
			return true;
		}else{
			return false;
		}
	}
	
</script>
<script src="js/shopping.common.js"></script>
<script src="${resourcePathHttps}/dist/photoswipe.min.js"></script>
<script src="${resourcePathHttps}/dist/photoswipe-ui-default.min.js"></script>
<script src="${resourcePathHttps}/dist/index.js"></script>

</body>
</html>