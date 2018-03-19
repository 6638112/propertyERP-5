function showExchangeMain(moblieNo,uuid){
	var curData = null;
	var phoneChargingType = $('#phoneChargingType').val();
	curData = phoneChargingType === '0'?{mobileNo:moblieNo}:{mobileNo:moblieNo, orderType:3};
	$.ajax({
		url:"../../ebuyNew/mobileQuery.json",
	 	dataType:"json",
	 	async:false,
	 	data:curData,
	 	beforeSend:function(){
	 	},
	 	complete:function(){
	 	},
	 	error:function(){
	 		$(".fc_bg").css("display","none");
			$(".fc_box").css("display","none");
	 		alert("充值运营商暂时无法连接，请稍后重试！");
	 	},
	 	success:function(val){
	 		$(".fc_bg").css("display","none");
			$(".fc_box").css("display","none");
	 		doSuccess(val);
	 	}
	});
}
function doSuccess(arrstr){
	var val=JSON.parse(arrstr);
	if(val.code=='00'){
			var packageStr = "";
			var phoneChargingType = $('#phoneChargingType').val();
			var chargingUnit = '';
			var currentId, currentPricse, feePriceType;
			
			//充流量、话费单位
			chargingUnit = phoneChargingType === '0'?'M':'元';
			
			$.each(val.data.data,function(row){ 
				var packageInfo = val.data.data[row];
				feePriceType = phoneChargingType === '0'?packageInfo.price:Math.min(packageInfo.price, packageInfo.salePrice);
				
			if(row==0){
 				packageStr+="<li data-denom='50' onclick='showPrice(\""+packageInfo.packageId+"\","+(feePriceType*1).toFixed(2)+")' id='" +packageInfo.packageId+"' class='active'><div><strong>"+packageInfo.vol+"</strong>"+chargingUnit+"<span class='selling-price-box'>售价：<span class='selling-price'>"+feePriceType+"</span>元</span><s class='s'><s></s></s></div></li>";
 				
 				currentId = packageInfo.packageId;
 				currentPrice =(feePriceType*1).toFixed(2);
 				if(currentId.indexOf("DX")==0){
 					$("#s_carrier").removeClass();
 					$("#s_carrier").addClass("icon-ctcc");
 					$("#s_carrier").html("中国电信");
 				}else if(currentId.indexOf("YD")==0){
 					$("#s_carrier").removeClass();
 					$("#s_carrier").addClass("icon-cmcc");
 					$("#s_carrier").html("中国移动");
 				}else if(currentId.indexOf("LT")==0){
 					$("#s_carrier").removeClass();
 					$("#s_carrier").addClass("icon-cucc");
 					$("#s_carrier").html("中国联通");
 				}
			}else{
				packageStr+="\n<li data-denom='50' onclick='showPrice(\""+packageInfo.packageId+"\","+(feePriceType*1).toFixed(2)+")' id='" +packageInfo.packageId+"' ><div><strong>"+packageInfo.vol+"</strong>"+chargingUnit+"<span class='selling-price-box'>售价：<span class='selling-price'>"+feePriceType+"</span>元</span><s class='s'><s></s></s></div></li>";
					
			}
		});
			packageStr+= "\n<li class='num-fix'></li>";
			$(".j-list-denom").html(packageStr);
			
			$("#div_j_box_step").hide();
			$(".j-box-sku").show();
			showPrice(currentId,currentPrice);
		}else {
			$("#price").val('');
			$("#packageId").val('');
			$(".j-box-sku").hide();
			$("#div_j_box_step").show();
			alert(val.msg); 
			
		}
}
function showPrice(packageId,price){
	$("#"+packageId).parent().find("li").removeClass("active");
	$("#"+packageId).addClass("active");
	
	$(".price").html("¥"+price);
	$("#price").val(price);
	$("#packageId").val(packageId);
	$(".submit").html("立即充值(¥"+$("#price").val()+")");
	
}
