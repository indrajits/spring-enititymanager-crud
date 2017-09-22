<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="robots" content="noindex">
<link rel="icon" href="/SpringMVCEnitityManagerCRUD/resources/image/cropped-logo-1-32x32.png" sizes="32x32" />
<title>Spring Web Service Application Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<style type="text/css">

</style>
<script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.4/angular.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.4/angular-sanitize.min.js"></script>

<script type="text/javascript">
    window.alert = function(){};
    var defaultCSS = document.getElementById('bootstrap-css');
    function changeCSS(css){
        if(css) $('head > link').filter(':first').replaceWith('<link rel="stylesheet" href="'+ css +'" type="text/css" />'); 
        else $('head > link').filter(':first').replaceWith(defaultCSS); 
    }
    /* $( document ).ready(function() {
      var iframe_height = parseInt($('html').height()); 
      window.parent.postMessage( iframe_height, 'http://localhost:8080');
    }); */
</script>
</head>
<body>
<div class="container" ng-app="WebServiceApplication">
	<div class="row">
	
        <div class="col-md-6 col-md-offset-3">
  <h4 style="border-bottom: 1px solid #c5c5c5;">
    <i class="glyphicon glyphicon-user">
    </i>
    Account Access
  </h4>
  <div style="padding: 20px;" id="form-olvidado">
  <form accept-charset="UTF-8" role="form" id="login-form" method="post" ng-controller="loginCtrl" ng-submit="submitLoginForm()">
      <fieldset>
        <div class="form-group input-group">
          <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
          <input class="form-control" placeholder="username or email" name="login-username" ng-model="form.username" type="text" required="" autofocus="">
        </div>
        <div class="form-group input-group">
          <span class="input-group-addon">
            <i class="glyphicon glyphicon-lock">
            </i>
          </span>
          <input class="form-control" placeholder="Password" name="password" ng-model="form.password" type="password" value="" required="">
        </div>
        <div class="form-group">
        	<input type="submit" value="Access" ngClick="Submit" class="btn btn-primary btn-block" >
          <!-- <button type="submit" class="btn btn-primary btn-block">
            Access
          </button> -->
          <p class="help-block">
            <a class="pull-right text-muted" href="#" id="olvidado"><small>Forgot your password?</small></a>
          </p>
        </div>
      </fieldset>
    </form>
  </div>
  <div style="display: none;" id="form-olvidado">
    <h4 class="">
      Forgot your password?
    </h4>
    <form accept-charset="UTF-8" role="form" id="login-recordar" method="post">
      <fieldset>
        <span class="help-block">
          Username&nbsp;/&nbsp;Email address you use to log in to your account
          <br>
          We'll send you an email with instructions to choose a new password.
        </span>
        <div class="form-group input-group">
          <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
          <input class="form-control" placeholder="username or email" name="login-username" type="text" required="">
        </div>
        <button type="submit" class="btn btn-primary btn-block" id="btn-olvidado">
          Continue
        </button>
        <p class="help-block">
          <a class="text-muted" href="#" id="acceso"><small>Account Access</small></a>
        </p>
      </fieldset>
    </form>
  </div>
</div>
	</div>
</div>
<script type="text/javascript">
var app = angular.module("WebServiceApplication", [ 'ngSanitize' ]);
app.controller("loginCtrl", function($scope, $http, $window, $location) {
	$scope.serviceUrl = $location.protocol()+'://'+location.host+'/SpringMVCEnitityManagerCRUD/';
	var formData = {
			username : "default",
			password : "default"
		};
	
	$scope.submitLoginForm = function() {
		console.log($location.host());
		console.log(location.host);
        console.log("posting data....");
        formData = $scope.form;
        console.log(formData);
        
        
		$http.post($scope.serviceUrl + 'validateUser', formData)
					.then(function(response) {
						
						if (response.data[0] == 'success')
							$window.location.href = response.data[1];
						else if (response.data[0] == 'failed') 
							console.log('failed');
							//$window.location.href = url;
						//console.log(response);
						/* $scope.userRecords = response.data.userDTOList;
						$scope.totalCount = response.data.user_counts;
						loadDataComplete(); */
					});
			//$http.post('form.php', JSON.stringify(data)).success(function(){/*success callback*/});
		};
	});
</script>
<script type="text/javascript">	
	$(document).ready(function() {
		$('#olvidado').click(function(e) {
			e.preventDefault();
			$('div#form-olvidado').toggle('500');
		});
		$('#acceso').click(function(e) {
			e.preventDefault();
			$('div#form-olvidado').toggle('500');
		});
	});
</script>
</body>
</html>