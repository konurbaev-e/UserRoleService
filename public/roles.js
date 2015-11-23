var module = angular.module('myapp', ['ngResource']);

module.factory('Role', function($resource) {
  return $resource('user/:username/roles', { username: '@username' });
})
.controller('UserRolesController', function($scope, Role) {

	var url = function() {
		return {
			username:$scope.username || 'konurbaev-e'
		};
	};
	
	var update = function() {
		console.log(url());
		$scope.roles = Role.query(url());
	};
	
	$scope.update = update;
	
	update();
});
