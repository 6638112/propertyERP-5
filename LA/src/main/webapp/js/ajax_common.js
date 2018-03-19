
function CreateXmlHttp() {
	    var xhrobj = false;
	    try {
	        xhrobj = new ActiveXObject("Msxml2.XMLHTTP"); //ie msxml3.0+
	    }
	    catch (e) {
	        try {
	            xhrobj = new ActiveXObject("Microsoft.XMLHTTP"); //ie msxml 2.6
	        } catch (e2) {
	            xhrobj = false;
	        }
	    }
	    if (!xhrobj && typeof XMLHttpRequest != 'undefined') { //firefox opera 8.0 safari
	        xhrobj = new XMLHttpRequest();
	    }
    return xhrobj;
}

function ajaxPost(url, postData) {
		var xhr = CreateXmlHttp();
		//1、设置请求方式、目标、是否异步
		//1.1 Get方式
		//xhr.open("GET", "GetAreasByAjax.ashx?isAjax=1", true);
		//===============如果是Post方式，请按下面的进行设置====================
		//1.2 Post方式,如果是Post方式，还需要其他一些设置
		xhr.open("POST",url, true);
		//1.2.1设置HTTP的输出内容类型为：application/x-www-form-urlencoded
		xhr.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		//1.2.2设置浏览器不使用缓存，服务器不从缓存中找，重新执行代码，而且服务器返回给浏览器的时候，告诉浏览器也不要保存缓存。
		xhr.setRequestHeader("If-Modified-Since", "0");

		//2、设置回调函数
		xhr.onreadystatechange = wacthing; //wacthing是方法名

		//3、发送请求
		//xhr.send(null); //GET方式
		xhr.send(postData); //POST方式
	}

	//回调函数
	function wacthing(xhr) {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var res = xhr.reponseText; //获得服务器返回的字符串
				alert(res);
			}
		}
	}