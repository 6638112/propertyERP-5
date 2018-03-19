var share = {
     /**
      * 跨框架数据共享接口
      * @param    {String}    存储的数据名
      * @param    {Any}        将要存储的任意数据(无此项则返回被查询的数据)
      */
     data: function (name, value) {
         var top = window.top,
             cache = top['_CACHE'] || {};
         top['_CACHE'] = cache;
         
         return value !== undefined ? cache[name] = value : cache[name];
     },
     /**
      * 数据共享删除接口
      * @param    {String}    删除的数据名
      */
     remove: function (name) {
         var cache = window.top['_CACHE'];
         var value = null;
         if (cache && cache[name]){
             value = cache[name];
             delete cache[name];
         }
         return value;
     }
 };


function myCallBack(obj) {
   alert("父窗口弹出："+obj);
}


//此方法由子窗口调用，故window.name是iframe页面的name
 function closeWin(data){
     //取得callback
	 var callback = share.remove(parent.layer.getFrameIndex(window.name));
	 if(callback && typeof(callback) === "function"){
	 //callback初始是由父窗口创建的，所以此时回调时，仍然是父窗口执行的。
	     callback(data);
	 }
	 //关闭窗口
     //parent.layer.close(parent.layer.getFrameIndex(window.name));
}

