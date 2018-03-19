
	var dtList; //维修二级分类，包含三级分类内容
	
	//上门服务大类切换	
	function cstTypeChange(){
		var cstId = $("select[name=cstId]").val();
		
		$.ajax({//使用ajax请求
			url : "getDredgeTypeList.json?parentTypeId=" + cstId,
			async : false,
			dataType:"json",
			success : function(data) {
				console.log( "return data: "+ data);
				if(data.status !='0000')
					alert("获取子类信息失败");
				else{
					dtList = data.dataValue.list;
					var dtSelector = $("select[name='dtId']");
					dtSelector.empty();
					dtSelector.append("<option value=''>请选择</option>");
					for(var i = 0; i < data.dataValue.list.length; i++){
						var dt = data.dataValue.list[i];
						if(dtId == dt.id){
							dtSelector.append("<option value='"+ dt.id + "' selected >"+ dt.name +"</option>");
						}else{
							dtSelector.append("<option value='"+ dt.id + "'>"+ dt.name +"</option>");
						}
					}
				}
			}
			});
		}
	
	
	//二级类型切换
	function dtChange(){
		var dtId = $("select[name=dtId]").val();
		var dt2Selector = $("select[name='dt2Id']");
		dt2Selector.empty();
		dt2Selector.append("<option value=''>请选择</option>");
		for(var i = 0; i < dtList.length; i++){
			var dt = dtList[i];
			if(dt.id == dtId){
				for(var j = 0; j < dt.dredgeType2ndList.length; j++){
					var dt2 = dt.dredgeType2ndList[j];
					if(dt2Id == dt2.id){
						dt2Selector.append("<option value='"+ dt2.id + "' selected >"+ dt2.name +"</option>");
					}else{
						dt2Selector.append("<option value='"+ dt2.id + "'>"+ dt2.name +"</option>");
					}
				}
			}
		}
	}
	
