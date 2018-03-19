
//标签切换，加载时显示默认标签
function tabSwap(tabObj, conObj){
	var hashNum;
	
	$(tabObj).click(function(){
		var $this = $(this);
		var thisIndex = $this.index();
		var	state = {
				'url':'#orderNum=' + thisIndex
			};
		
		//点击切换标签
		$this.addClass('on').siblings().removeClass('on');
		$(conObj).eq(thisIndex).show()
				.siblings().hide();
			
		//记录上一次选中的tab
		history.replaceState(state, '', state.url); 
	});
	
	//加载时显示默认标签。如果存在浏览记录，则按照浏览记录显示标签
	if(location.hash){
		hashNum = location.hash.substring(10);
	}else{
	//否则显示第一个标签	
		hashNum = 0;
	}
			
	//页面加载时触发默认显示标签
	$(tabObj).eq(hashNum).click();
}