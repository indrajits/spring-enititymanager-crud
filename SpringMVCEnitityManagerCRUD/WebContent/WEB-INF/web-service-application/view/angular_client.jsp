<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.4/angular.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.4/angular-sanitize.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.4/angular-animate.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/1.3.3/ui-bootstrap-tpls.min.js"></script>
	<!-- <script type="text/javascript" src="/AngularJsClient/angularPaging/js/crud.js"></script> -->
	
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="icon" href="http://www.interrait.com/interrait/wp-content/uploads/2016/01/cropped-logo-1-32x32.png" sizes="32x32" />	
    
<title>Sample Pagination</title>
</head>
<body>
	<div id="angularContents" class="container" ng-app="app" ng-controller="angularCrudApplication">
		<br />
		<div ng-show="progress_percent_visibility">
			<uib-progressbar class="progress-striped active" value="dynamic" type="{{type}}">
				<i ng-show="showWarning">{{progress_percent}}</i>
			</uib-progressbar>           
	    </div>
	    
	    <!-- <div class="modal-header">
            <h3 class="modal-title" id="modal-title">I'm a modal!</h3>
        </div>
        <div class="modal-body" id="modal-body">
        	<div ng-show="progress_percent_visibility">
	            <uib-progressbar class="progress-striped active" value="dynamic" type="{{type}}">
					<i ng-show="showWarning">{{progress_percent}}</i>
				</uib-progressbar>
			</div>
        </div> -->
	    
		<div ng-show="!progress_percent_visibility" ng-if="!isNewUser" class="col-md-4 text-center"> 
		    <button class="btn btn-info" ng-click="newUser()">Add User</button> &nbsp;&nbsp;
		    <button class="btn btn-danger" ng-click="deleteSelected()">Delete Users</button>
		</div>		
		<br /><br />
		<table ng-show="!progress_percent_visibility" class="table table-striped table-hover" ng-if="!isNewUser">
			<thead>
				<tr>
					<th style="text-align: center;"><input type="checkbox" ng-model="$parent.allSelected" ng-change="toggleAll()" /></th>
					<th><a href="javascript:void(0)" ng-click="sortUsers()">ID</a></th>
					<th>Name</th>
					<th>
					  	<div class="col-lg-9">
						    <div class="input-group">
						      <input type="text" on-enter-blur="filterUserByEmail(this)" id="inputSearchEmail" ng-model="inputSearchEmail" class="form-control" placeholder="Search Email">
						      <span class="glyphicon glyphicon-search form-control-feedback"></span>
						    </div>
						</div>						
					</th>
					<th>Country</th>
					<th>DOB</th>
					<th>In-line Actions</th>
					<th>Actions</th>
				</tr>
			<thead>
			<tbody>
				<!-- u in filteredUser = (userRecords) u in filteredUser = (userRecords | filter: {email : searchEmail}) | start: (currentPage - 1) * perPage | limitTo: perPage -->
				<tr ng-repeat="u in userRecords">
				
					<td style="text-align: center;"><input type="checkbox" ng-model="u.selected" ng-change="selectedCheck()" /></td>
					<td>{{u.user_sno}}</td>
					<td>
						<span ng-hide="editMode">{{u.fname}}</span>
						<input type="text" ng-show="editMode" ng-model="user.fname" />
					</td>
					<td>
						<span ng-hide="editMode">{{u.email}}</span>
						<input type="text" ng-show="editMode" ng-model="user.email" />
					</td>
					<td>
						<span ng-hide="editMode">{{u.country.country_name}}</span>
						<select class="form-control" id="country" ng-model="user.country" ng-show="editMode"
							ng-options="countryDetails.country_code as (countryDetails.country_name) for countryDetails in countryList">
	    				</select>
					</td>					
					<td>
						<span ng-hide="editMode">{{u.dob}}</span>
						<input type="date" ng-show="editMode" class="form-control" id="dob" ng-model="user.dob">
					</td>					
					<td>
						<span ng-hide="editMode">
							<a href="javascript:void(0)" ng-click="editMode = true; editUserInline(u)">Edit</a>
						</span>						
						
						<span ng-show="editMode">
							<a href="javascript:void(0)" ng-click="editMode = false; updateUserData()">Update</a> |
							<a href="javascript:void(0)" ng-click="editMode = false;">Cancel</a>
						</span>						
					</td>
					<td>
						<a href="javascript:void(0)" ng-click="editUser(u)">Edit</a> | 
						<a href="javascript:void(0)" ng-click="deleteUser(u)">Delete</a>
					</td>
					
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2"></td>					
					<td><input type="text" id="txtFnameInline" ng-model="user.fname" /></td>
					<td><input type="text" id="txtEmailInline" ng-model="user.email" /></td>
					<td><select class="form-control" id="ddlCountryInline" ng-model="user.country" 
							ng-options="countryDetails.country_code as (countryDetails.country_name) for countryDetails in countryList">
	    			</select></td>
					<td><input type="date" class="form-control" id="dobInline" ng-model="user.dob"></td>
					<td colspan="2"><input type="button" class="btn btn-success" ng-click="submitUserData(this)" value="Add" /></td>				
				</tr>
				<tr>
					<td colspan="8">
						<div class="pull-left">
							<br />
							<select id="ddlPageSize" class="form-control"  
                                    ng-model="$parent.pageSizeSelected"  
                                    ng-change="changePageSize()">
                                 <option value="5">5</option>  
                                 <option value="10">10</option>  
                                 <option value="25">25</option>  
                                 <option value="50">50</option>  
                             </select>
						</div>
						<div class="pull-right">
							<uib-pagination total-items="totalCount" 
										ng-model="$parent.pageIndex"
										ng-change="pageChanged()" 
										items-per-page="pageSizeSelected"
										max-size="2"										
										direction-links="true" previous-text = "&lsaquo;" next-text = "&rsaquo;"
										boundary-links="true" first-text = "&laquo;" last-text = "&raquo;"
										rotate="false"
										num-pages="numPages"></uib-pagination>
						</div>						
					</td>
				</tr>				
			</tfoot>
		</table>
				
		<div ng-show="!progress_percent_visibility" class="form-horizontal" id="form" ng-if="isNewUser">
			<span ng-bind-html="msg"></span>{{user.fname}} {{user.email}} {{user.country}}<br><br>
			<div class="form-group">
			    <label class="control-label col-sm-2" for="fname">Name:</label>
			    <div class="col-sm-10">
			    	<input class="form-control" id="fname" ng-model="user.fname">
			    </div>
		  	</div>
		  	<div class="form-group">
			    <label class="control-label col-sm-2" for="email">Email:</label>
			    <div class="col-sm-10">			        
			    	<input type="text" class="form-control" id="email" ng-model="user.email">
			    </div>			    
		  	</div>
		  	<div class="form-group">
			    <label class="control-label col-sm-2" for="country">Country:</label>
			    <div class="col-sm-10">
				    <select class="form-control" id="country" ng-model="user.country" 
							ng-options="countryDetails.country_code as (countryDetails.country_name) for countryDetails in countryList">
	    			</select>
	    		</div>
		  	</div>
		  	<div class="form-group">
			    <label class="control-label col-sm-2" for="dob">Date of Birth:</label>
			    <div class="col-sm-10">
			    	<input type="date" class="form-control" id="dob" ng-model="user.dob">
			    </div>
		  	</div>
			<div class="form-group">
    			<div class="col-sm-offset-2 col-sm-10">
    				<input ng-if="!isExistingUser" class="btn btn-success" type="button" value="Submit" ng-click="submitData()" />
    				<input ng-if="isExistingUser" class="btn btn-primary" type="button" value="Update" ng-click="updateUserData()" />
    				&nbsp;&nbsp;<input class="btn btn-default" type="button" value="Cancel" ng-click="cancelOperation()" />
    			</div>
    		</div>			
		</div>
		
	</div>
	<script type="text/javascript">
		application = angular.module('app', [ 'ngSanitize', 'ui.bootstrap' ]);
		application.controller('angularCrudApplication', function($scope, $filter, $http, validationInline) {
			$scope.serviceUrl = '/SpringMVCEnitityManagerCRUD/';
			$scope.serviceUrlTemp = 'http://lowcost-env.vjvvf8aux7.ap-south-1.elasticbeanstalk.com/';
			$scope.isNewUser = false;
			$scope.isExistingUser = false;
			$scope.userRecords = [];
			$scope.countryList = [];
			$scope.sortById = 'asc';

			$scope.pageIndex = 1;
			$scope.totalCount = 0;
			$scope.pageSizeSelected = 5;
			
			$scope.progress_percent = null;
	    	$scope.progress_percent_visibility = false;

			function getUserList() {
				var page_data = [ $scope.pageIndex,
						$scope.pageSizeSelected,
						$scope.sortById ];
				
				/* loadDataProcess(); */
				/* $http.post($scope.serviceUrl + 'get_users_by_page', page_data).then(
					function(response) {
						//angular.copy(response.data.artists.items, $scope.tracks);

						$scope.userRecords = response.data.userDTOList;
						$scope.totalCount = response.data.user_counts;													
				}); */
				
				$scope.progress_percent_visibility = true;
				$http({
				    url: $scope.serviceUrl + 'get_users_by_page',
				    method: 'POST',
				    data: page_data,
				    eventHandlers: {
				    	progress: function(e) {
				             if (e.lengthComputable) {
				            	progress = Math.round(e.loaded * 100 / e.total);
					                
				                var value = progress;
				                var type;
				                if (value < 100) type = 'info';								                
								else type = 'success';
				                
				                $scope.showWarning = type === 'info' || type === 'success';
				                $scope.dynamic = value;
				                $scope.type = type;	
				                
				                if(progress < 100)
				                	$scope.progress_percent = progress + '% loaded...';		
				                else
				                	$scope.progress_percent = 'Page Successfully Loaded! Please Wait...';
				            }
				        },
				    	load: function(e) {						
				    		console.log("Page Load success!");
						},
				        readystatechange: function(event) {
	                        if(event.currentTarget.readyState === 4)
	                            console.log("readyState=4: Server has finished extra work!");
	                    }
				    }
				}).then(function(response) {									
					$scope.userRecords = response.data.userDTOList;
					$scope.totalCount = response.data.user_counts;
					$scope.progress_percent_visibility = false;
				});
			}

			function filterUserListByEmail(email) {
				var page_data = [ $scope.pageIndex,
						$scope.pageSizeSelected, email,
						$scope.sortById ];
				$scope.progress_percent_visibility = true;
				$http.post( $scope.serviceUrl + 'filter_user_by_email', page_data).then(
					function(response) {
						//angular.copy(response.data.artists.items, $scope.tracks);

						$scope.userRecords = response.data.userDTOList;
						$scope.totalCount = response.data.user_counts;	
						$scope.progress_percent_visibility = false;
				});
			}

			getUserList();

			//get another portions of data on page changed
			$scope.pageChanged = function() {
				getUserList();
			};

			//This method is calling from dropDown  
			$scope.changePageSize = function() {
				$scope.pageIndex = 1;
				var email = document
						.getElementById('inputSearchEmail').value;

				if (email == "" || email == null)
					getUserList();
				else
					filterUserListByEmail(email);
			};

			GetCountrylist();

			function GetCountrylist() {
				$http.post($scope.serviceUrl + 'countryList').then(function(res) {
					$scope.countryList = res.data;
				});
			}

			$scope.newUser = function() {
				GetCountrylist();
				// Assign new user object
				$scope.user = {
					id : 0,
					fname : null,
					email : null,
					country : null,
					dob : null
				};
				$scope.msg = "Details: ";
				// Set flag as new user
				$scope.isNewUser = true;
			};

			$scope.newInlineUser = function() {
				$scope.user = {
					id : 0,
					fname : null,
					email : null,
					country : null,
					dob : null
				};
			};

			$scope.newInlineUser();

			$scope.deleteSelected = function() {
				var data = [];
				angular.forEach($scope.userRecords, function(val, key) {
					if (val.selected) data.push(val.user_sno);
				});
				
				$scope.progress_percent_visibility = true;
				$http.post( $scope.serviceUrl + 'delete_user', data).then(
					function(res) {
						for (var i = 0; i < res.data.length; i++) {
							var indexObj = $filter('filter')($scope.userRecords, { user_sno : res.data[i] }, true)[0];
							var index = $scope.userRecords.indexOf(indexObj);	
							$scope.userRecords.splice(index, 1);
						}									
						$scope.progress_percent_visibility = false;
				});
			};

			$scope.selectedCheck = function() {
				var x = true;
				angular.forEach($scope.userRecords,
						function(u) {
							if (!u.selected)
								x = false;
						});
				$scope.allSelected = x;
			};

			$scope.toggleAll = function() {
				//console.log($scope.allSelected);
				if ($scope.allSelected) {
					angular.forEach($scope.userRecords,
							function(u) {
								u.selected = true;
							});
				} else {
					angular.forEach($scope.userRecords,
						function(u) {
							u.selected = false;
					});
				}
			};

			$scope.filterUserByEmail = function(element) {
				$scope.pageIndex = 1;
				$scope.pageSizeSelected = 5;

				if (element.inputSearchEmail == "") getUserList();
				else filterUserListByEmail(element.inputSearchEmail);
			};

			$scope.sortUsers = function() {
				$scope.pageIndex = 1;
				$scope.pageSizeSelected = 5;

				if ($scope.sortById == 'asc') $scope.sortById = 'desc';
				else $scope.sortById = 'asc';

				var email = document.getElementById('inputSearchEmail').value;

				if (email == "" || email == null) getUserList();
				else filterUserListByEmail(email);
			}

			$scope.editUser = function(obj) {
				GetCountrylist();
				$scope.user = {
					user_sno : obj.user_sno,
					fname : obj.fname,
					email : obj.email,
					country : obj.country.country_code,
					dob : new Date(obj.dob)
				};
				$scope.isNewUser = true;
				$scope.isExistingUser = true;
			};

			$scope.editUserInline = function(obj) {
				$scope.user = {
					user_sno : obj.user_sno,
					fname : obj.fname,
					email : obj.email,
					country : obj.country.country_code,
					dob : new Date(obj.dob)
				};
			};

			$scope.deleteUser = function(obj) {
				console.log(obj);
				var index = $scope.userRecords.indexOf(obj);
				var jsonData = [ obj.user_sno ];
				
				$scope.progress_percent_visibility = true;
				$http.post($scope.serviceUrl + 'delete_user',
						jsonData).then(function(res) {
					var user_sno = res.data[0];
					if (user_sno == obj.user_sno)
						$scope.userRecords.splice(index, 1);
					$scope.progress_percent_visibility = false;
				});
				return;
			};

			$scope.cancelOperation = function() {
				$scope.user = {
					user_sno : '',
					fname : '',
					email : '',
					country : '',
					dob : ''
				};
				$scope.isNewUser = false;
				$scope.isExistingUser = false;
			};

			$scope.submitUserData = function(element) {
				if (validationInline.validate(element)) {
					var indexCountry = $filter('filter')
							(
									$scope.countryList,
									{
										country_code : $scope.user.country
									}, true)[0];

					var jsonData = [ {
						user_sno : null,
						fname : $scope.user.fname,
						lname : '',
						email : $scope.user.email,
						conatact : '',
						country : {
							country_code : $scope.user.country,
							country_name : indexCountry.country_name
						},
						password : '',
						dob : $filter('date')($scope.user.dob,
								'yyyy-MM-dd')
					} ];
					
					$scope.progress_percent_visibility = true;
					$http.post( $scope.serviceUrl + 'create_user', jsonData).then(
						function(res) {

							var user_sno = res.data[0].user_sno;
							if (user_sno != null
									&& user_sno != '')
								$scope.userRecords
										.push(res.data[0]);
							$scope.user = {
								user_sno : '',
								fname : '',
								email : '',
								country : '',
								dob : ''
							};
							$scope.progress_percent_visibility = false;
					});
					$scope.isNewUser = false;
					$scope.isExistingUser = false;
				}
			};							
			
			$scope.submitData = function() {								
				var indexCountry = $filter('filter')($scope.countryList, { country_code : $scope.user.country }, true)[0];

				var jsonData = [ {
					user_sno : null,
					fname : $scope.user.fname,
					lname : '',
					email : $scope.user.email,
					conatact : '',
					country : {
						country_code : $scope.user.country,
						country_name : indexCountry.country_name
					},
					password : '',
					dob : $filter('date')($scope.user.dob,
							'yyyy-MM-dd')
				} ];
				
				$scope.progress_percent_visibility = true;
				$http({
				    url: $scope.serviceUrl + 'create_user',
				    method: 'POST',
				    data: jsonData,
				    eventHandlers: {
				    	progress: function(e) {
				             if (e.lengthComputable) {
				                progress = Math.round(e.loaded * 100 / e.total);
				                
				                var value = progress;
				                var type;
				                if (value < 100) type = 'info';								                
								else type = 'success';
				                
				                $scope.showWarning = type === 'info' || type === 'success';
				                $scope.dynamic = value;
				                $scope.type = type;	
				                
				                if(progress < 100)
				                	$scope.progress_percent = progress + '% saved...';		
				                else
				                	$scope.progress_percent = 'Created Successfully! Please Wait...';
				            }
				        },
				    	load: function(e) {						
				    		console.log("Page Load success!");
						},
				        readystatechange: function(event) {
	                        if(event.currentTarget.readyState === 4)
	                            console.log("readyState=4: Server has finished extra work!");
	                    }
				    }
				}).then(function(res) {									
					var user_sno = res.data[0].user_sno;
					if (user_sno != null && user_sno != '')
						$scope.userRecords.push(res.data[0]);
					$scope.user = {
						user_sno : '',
						fname : '',
						email : '',
						country : '',
						dob : ''
					};
					$scope.progress_percent_visibility = false;
				});
				$scope.isNewUser = false;
				$scope.isExistingUser = false;
			};

			$scope.updateUserData = function() {
				var indexObj = $filter('filter')($scope.userRecords, { user_sno : $scope.user.user_sno }, true)[0];
				var indexCountry = $filter('filter')(
						$scope.countryList, {
							country_code : $scope.user.country
						}, true)[0];

				var index = $scope.userRecords
						.indexOf(indexObj);

				var jsonData = [ {
					user_sno : $scope.user.user_sno,
					fname : $scope.user.fname,
					lname : $scope.userRecords[index].fname,
					email : $scope.user.email,
					conatact : $scope.userRecords[index].conatact,
					country : {
						country_code : $scope.user.country,
						country_name : indexCountry.country_name
					},
					password : $scope.userRecords[index].password,
					dob : $filter('date')($scope.user.dob,
							'yyyy-MM-dd')
				} ];

				console.log(jsonData);	
				$scope.progress_percent_visibility = true;
				$http.post( $scope.serviceUrl + 'update_user', jsonData).then(
					function(res) {
						if (res.data == true) {
							//$scope.userRecords[index] = jsonData;
							$scope.userRecords[index].fname = $scope.user.fname;
							$scope.userRecords[index].email = $scope.user.email;
							$scope.userRecords[index].country = {
								country_code : $scope.user.country,
								country_name : indexCountry.country_name
							};
						}
						$scope.user = {
							user_sno : '',
							fname : '',
							email : '',
							country : '',
							dob : ''
						};	
						$scope.progress_percent_visibility = false;
				});
				$scope.isNewUser = false;
				$scope.isExistingUser = false;
			};
		});

		application.service('validationInline', function() {
			this.validate = function(element) {
				var res = true;
				if (element.user.fname == null || element.user.fname == '') res = false;	
				if (element.user.email == null || element.user.email == '') res = false;
				if (element.user.country == null || element.user.country == '') res = false;
				if (element.user.dob == null || element.user.dob == '')  res = false;
				return res;
			}
		});

		application.directive('onEnterBlur', function($parse) {
			return function(scope, element, attr) {
				var fn = $parse(attr['onEnterBlur']);

				element.bind("keydown keypress", function(event) {
					if (event.which === 13) {
						scope.$apply(function() {
							fn(scope, {
								$event : event
							});
						});
						event.preventDefault();
					}
				});
			};
		});
	</script>
</body>
</html>