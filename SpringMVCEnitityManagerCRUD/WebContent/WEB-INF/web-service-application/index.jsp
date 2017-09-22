<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<% if(session.getAttribute("user") == null || session.getAttribute("user") == "")	{ %>
	<meta http-equiv="refresh" content="0; url=/SpringMVCEnitityManagerCRUD/login" />
<% } else { %>
<meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, minimum-scale=1, user-scalable=no">
<link rel="stylesheet" href="/SpringMVCEnitityManagerCRUD/resources/css/animate.css">
<link href='/SpringMVCEnitityManagerCRUD/resources/css/onepage-scroll.css' rel='stylesheet' type='text/css'>
<link href='/SpringMVCEnitityManagerCRUD/resources/css/style.css' rel='stylesheet' type='text/css'>
<link href='/SpringMVCEnitityManagerCRUD/resources/css/css.css' rel='stylesheet' type='text/css'>
<link href='/SpringMVCEnitityManagerCRUD/resources/css/owl.carousel.css' rel='stylesheet' type='text/css'>
<script type="text/javascript" src="/SpringMVCEnitityManagerCRUD/resources/js/jquery-1.10.1.min.js"></script>

<script type="text/javascript" src="/SpringMVCEnitityManagerCRUD/resources/js/onepagescroll.js"></script>
<script src="/SpringMVCEnitityManagerCRUD/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/SpringMVCEnitityManagerCRUD/resources/js/owl.carousel.js"></script>
<link rel="icon" href="/SpringMVCEnitityManagerCRUD/resources/image/cropped-logo-1-32x32.png" sizes="32x32" />
<title>Spring Web Service Application</title>
<meta name="description" content="I am a mulidisciplinary designer and art director based in Rome, Italy. I am specialized in digital and integrated projects, illustrations and lettering. I am currently working as art director at Gigasweb and as a freelance for little cool projects, I also teach interaction design at AANT.">
</head>
<body>
<div class="loading">
	<div id="pong">
        <div class="pad"></div>
        <div class="ball"></div>
        <div class="pad"></div>
	</div>
</div>
<div class="wrapper">	
	  <div class="main">
          <section class="page1">                
                	<div class="hello" >
                    	 <h1>Hello All! This is a sample web service application<br/>
                    	 <span>build using spring 4.0 / jpa 2.0</span></h1><br/>
                    	 <a href="/SpringMVCEnitityManagerCRUD/userList">Test Service</a><br/>
                    	 <a href="/SpringMVCEnitityManagerCRUD/logout">logout</a>
                    </div>                 
          </section>          
    </div>
</div>
    <script type='text/javascript' src='/SpringMVCEnitityManagerCRUD/resources/js/animations.js'></script>
    <script type='text/javascript' src='/SpringMVCEnitityManagerCRUD/resources/js/owl.carousel.min.js'></script>
</body>
<% } %>
</html>