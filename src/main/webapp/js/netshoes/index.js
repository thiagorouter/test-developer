function IndexController($scope, NewsService) {

   /* $scope.newsEntries = NewsService.query();

    $scope.deleteEntry = function (newsEntry) {
        newsEntry.$remove(function () {
            $scope.newsEntries = NewsService.query();
        });
    };*/
}


function EditController($scope, $routeParams, $location, NewsService) {

    $scope.newsEntry = NewsService.get({id: $routeParams.id});

    $scope.save = function () {
        $scope.newsEntry.$save(function () {
            $location.path('/');
        });
    };
}


function CreateController($scope, $location, NewsService) {

    $scope.newsEntry = new NewsService();

    $scope.save = function () {
        $scope.newsEntry.$save(function () {
            $location.path('/');
        });
    };
}


function LoginController($scope, $rootScope, $location, $cookieStore, UserService) {

    $scope.login = function () {
        UserService.authenticate(
            $.param({username: $scope.username, password: $scope.password}), function (authenticationResult) {

                var authToken = authenticationResult.token;

                $rootScope.authToken = authToken;

                if ($scope.rememberMe) {
                    $cookieStore.put('authToken', authToken);
                }

                UserService.get(function (user) {
                    $rootScope.user = user;
                    $location.path("/");
                });
            });
    };
}

