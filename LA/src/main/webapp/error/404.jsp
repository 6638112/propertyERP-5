<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<title>404页面</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<style type="text/css">
/*404动画*/
#fofimg{ position:relative;}

#fofman{
  position:absolute;
  top:20%;
  left:50%;
  -webkit-animation: float ease-in-out 2.5s infinite;
  animation: float ease-in-out 2.5s infinite;}
#fofman:before{
  -webkit-animation: shadow ease-in-out 2.5s infinite;
  animation: shadow ease-in-out 2.5s infinite;
  content:'';
  display:block;
  position:relative;
  top:20%;
  left:5%;
  height:8px;
  margin:0 80px;
  border-radius:80% 60%;
  -webkit-box-shadow:0 200px 20px 10px rgba(0,0,0,.7);
  box-shadow:0 200px 20px 10px rgba(0,0,0,.7);
  opacity: .5;
}
#fofdot{ position:absolute; top:15.5%; left:18%; -webkit-animation:float ease-in-out 4s infinite; animation:float ease-in-out 4s infinite;}


 @media only screen and (min-width:560px) {
	.tabBox, .sectionBox, .divide-box, .exchang-fixed { width: 560px; }
}
 @media only screen and (max-width:559px) {
	.tabBox, .sectionBox, .divide-box, .exchang-fixed { width: 100%; }
	.head-img01{ width:36px; height:36px;}
	.head-img01 img{ width:36px; height:36px;}
	.record-txt{ min-width:140px;font-size:12px;}
	.record-txt .f18{ font-size:14px !important;}
	.money{ width:60px; font-weight:normal; font-size:16px; color:#fff;}
}
 @media only screen and (max-width:519px) {
	#fofman:before{
	  top:20%;
	  left:0;
	  height:8px;
	  margin:0 80px;
	  -webkit-box-shadow:0 160px 20px 10px rgba(0,0,0,.7);
	  box-shadow:0 160px 20px 10px rgba(0,0,0,.7);
	}
}
 @media only screen and (max-width:419px) {
	#fofman:before{
	  top:20%;
	  left:0;
	  height:4px;
	  margin:0 60px;
	  -webkit-box-shadow:0 130px 20px 10px rgba(0,0,0,.7);
	  box-shadow:0 130px 10px 6px rgba(0,0,0,.7);
	}
}
 @media only screen and (max-width:349px) {
	#fofman:before{
	  top:20%;
	  left:0;
	  height:4px;
	  margin:0 60px;
	  -webkit-box-shadow:0 110px 10px 5px rgba(0,0,0,.7);
	  box-shadow:0 110px 10px 6px rgba(0,0,0,.7);
	}
}
 @media only screen and (max-width:299px) {
	#fofman:before{
	  top:20%;
	  left:0;
	  height:4px;
	  margin:0 50px;
	  -webkit-box-shadow:0 100px 10px 2px rgba(0,0,0,.7);
	  box-shadow:0 100px 10px 6px rgba(0,0,0,.7);
	}
}



@-webkit-keyframes float {
  0% {
    -webkit-transform: translateY(0);
    transform: translateY(0);
  }
  50% {
    -webkit-transform: translateY(8px);
    transform: translateY(8px);
  }
  100% {
    -webkit-transform: translateY(0);
    transform: translateY(0);
  }
}

@keyframes float {
  0% {
    -webkit-transform: translateY(0);
    transform: translateY(0);
  }
  50% {
    -webkit-transform: translateY(8px);
    transform: translateY(8px);
  }
  100% {
    -webkit-transform: translateY(0);
    transform: translateY(0);
  }
}

@-webkit-keyframes shadow {
  0% {
    -webkit-transform: translateY(0);
    transform: translateY(0);
    opacity: .5;
  }
  50% {
    -webkit-transform: translateY(0.6em) scale(.9);
    transform: translateY(0.6em) scale(.9);
    opacity: 1;
  }
  100% {
    -webkit-transform: translateY(0);
    transform: translateY(0);
    opacity: .5;
  }
}

@keyframes shadow {
  0% {
    -webkit-transform: translateY(0);
    transform: translateY(0);
    opacity: .5;
  }
  50% {
    -webkit-transform: translateY(0.6em) scale(.9);
    transform: translateY(0.6em) scale(.9);
    opacity: 1;
  }
  100% {
    -webkit-transform: translateY(0);
    transform: translateY(0);
    opacity: .5;
  }
}


</style>
</head>

<body>
    <div class="info sectionBox">
       	<div id="fofimg">
           	<div id="fofman"><img style="width:80%" src="../images/fofman.png" /></div>
           	<div id="fofdot"><img src="../images/fofdot.png" /></div>
           	<img class="fof" src="../images/404.jpg" />
        </div>
		<div class="t-center mtop20">对不起，找不到您请求的页面或请求页面出错！</div>
    </div>
</body>

</html>