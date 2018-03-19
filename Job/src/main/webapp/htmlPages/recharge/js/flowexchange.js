function showExchangeMain(moblieNo,uuid){
	$.ajax({
	 	//url:"/foss-portal/wap/lljh/recharge/productQuery.ws",
		url:"../../ebuyNew/mobileQuery.json",
	 	dataType:"json",
	 	async:false,
	 	data:{mobileNo:moblieNo},
	 	beforeSend:function(){
	 	//ajaxLoading();
	 	//$("#zhezhao").show();
	 	},
	 	complete:function(){
	 	   //ajaxLoadEnd();
	 	  //$("#zhezhao").hide();
	 	},
	 	error:function(){
	 		//var val = {"code":"00","msg":"OK","data":{"count":8,"data":[{"packageId":"DX5","operatorCode":"DX","vol":5,"activePeriod":365,"price":1,"oprPackageId":"104217"},{"packageId":"DX10","operatorCode":"DX","vol":10,"activePeriod":365,"price":2,"oprPackageId":"104218"},{"packageId":"DX30","operatorCode":"DX","vol":30,"activePeriod":365,"price":5,"oprPackageId":"104219"},{"packageId":"DX50","operatorCode":"DX","vol":50,"activePeriod":365,"price":7,"oprPackageId":"104220"},{"packageId":"DX100","operatorCode":"DX","vol":100,"activePeriod":365,"price":10,"oprPackageId":"104221"},{"packageId":"DX200","operatorCode":"DX","vol":200,"activePeriod":365,"price":15,"oprPackageId":"104222"},{"packageId":"DX500","operatorCode":"DX","vol":500,"activePeriod":365,"price":30,"oprPackageId":"104223"},{"packageId":"DX1024","operatorCode":"DX","vol":1024,"activePeriod":365,"price":50,"oprPackageId":"104224"}]}};
	 		//var val = {"code":"00","msg":"OK","data":{"count":6,"data":[{"packageId":"YD10","operatorCode":"YD","vol":10,"activePeriod":365,"price":3},{"packageId":"YD30","operatorCode":"YD","vol":30,"activePeriod":365,"price":5},{"packageId":"YD70","operatorCode":"YD","vol":70,"activePeriod":365,"price":10},{"packageId":"YD150","operatorCode":"YD","vol":150,"activePeriod":365,"price":20},{"packageId":"YD500","operatorCode":"YD","vol":500,"activePeriod":365,"price":30},{"packageId":"YD1024","operatorCode":"YD","vol":1024,"activePeriod":365,"price":50}]}};
	 		//doSuccess(val);
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
			var currentId, currentPricse;
			$.each(val.data.data,function(row){ 
				var packageInfo = val.data.data[row];
				
			if(row==0){
 				packageStr+="<li data-denom='50' onclick='showPrice(\""+packageInfo.packageId+"\","+(packageInfo.price*1).toFixed(2)+")' id='" +packageInfo.packageId+"' class='active'><div><strong>"+packageInfo.vol+"</strong>M<s class='s'><s></s></s></div></li>";
 				
 				currentId = packageInfo.packageId;
 				currentPrice =(packageInfo.price*1).toFixed(2);
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
				packageStr+="\n<li data-denom='50' onclick='showPrice(\""+packageInfo.packageId+"\","+(packageInfo.price*1).toFixed(2)+")' id='" +packageInfo.packageId+"' ><div><strong>"+packageInfo.vol+"</strong>M<s class='s'><s></s></s></div></li>";
					
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
	//$(this).parent().find("li").removeClass("active");
	$("#"+packageId).parent().find("li").removeClass("active");
	$("#"+packageId).addClass("active");
	
	$(".price").html("¥"+price);
	$("#price").val(price);
	$("#packageId").val(packageId);
	$(".submit").html("立即充值(¥"+$("#price").val()+")");
	
}
