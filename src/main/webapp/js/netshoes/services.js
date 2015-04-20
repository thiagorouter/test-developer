var services = angular.module('netshoes.services', ['ngResource']);

services.factory('UserService', function ($resource) {

    return $resource('rest/usuario/:action', {},
        {
            authenticate: {
                method: 'POST',
                params: {'action': 'authenticate'},
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }
    );
});

services.factory('NewsService', function ($resource) {

    return $resource('rest/news/:id', {id: '@id'});
});
