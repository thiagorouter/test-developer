angular.module('netshoes', ['ngRoute', 'ngCookies', 'netshoes.services'])
    .config(
    ['$routeProvider', '$locationProvider', '$httpProvider', function ($routeProvider, $locationProvider, $httpProvider) {

        $routeProvider.when('/edit/:id', {
            templateUrl: 'webpages/partials/edit.html',
            controller: EditController
        });

        $routeProvider.when('/login', {
            templateUrl: 'webpages/login.html',
            controller: LoginController
        });

        $routeProvider.when('/cadastro/usuario', {
            templateUrl: 'webpages/usuario/cadastro_usuario.html'
        });

        $routeProvider.otherwise({
            templateUrl: 'webpages/autenticado.html',
            controller: IndexController
        });

        $locationProvider.hashPrefix('!');

        /* Definição do provedor de erro para informar mensagens de erro em falhas
         de request ou redirecionar para página de login ao ocorrer requisições sem estar autenticado */
        $httpProvider.interceptors.push(function ($q, $rootScope, $location) {
                return {
                    'responseError': function (rejection) {
                        var status = rejection.status;
                        var config = rejection.config;
                        var method = config.method;
                        var url = config.url;

                        if (status == 401) {
                            $location.path("/login");
                            $rootScope.error = "Usuário/senha inválido, verifique os dados informados."
                        } else {
                            $rootScope.error = method + " on " + url + " failed with status " + status;
                        }

                        return $q.reject(rejection);
                    }
                };
            }
        );

        /* Interceptor que irá ser responsável por propagar o token de autorização,
         o mesmo sempre será passado no header ou por parâmetro em query quando houver usuário autenticado. */
        $httpProvider.interceptors.push(function ($q, $rootScope) {
                return {
                    'request': function (config) {

                        var isRestCall = config.url.indexOf('rest') == 0;

                        if (isRestCall && angular.isDefined($rootScope.authToken)) {
                            var authToken = $rootScope.authToken;

                            if (appConfig.useAuthTokenHeader) {
                                config.headers['X-Auth-Token'] = authToken;
                            } else {
                                config.url = config.url + "?token=" + authToken;
                            }
                        }
                        return config || $q.when(config);
                    }
                };
            }
        );

    }]
).run(function ($rootScope, $location, $cookieStore, UserService) {

        /* Limpa os erros quando uma nova view é carregada.*/
        $rootScope.$on('$viewContentLoaded', function () {
            delete $rootScope.error;
        });


        // utilizado para verificar se usuário autenticado possui acesso ao recurso.
        $rootScope.hasRole = function (role) {

            if ($rootScope.user === undefined) {
                return false;
            }

            if ($rootScope.user.roles[role] === undefined) {
                return false;
            }

            return $rootScope.user.roles[role];
        };


        // executa logout, removendo qualquer informação provinda da autenticação do usuário.
        $rootScope.logout = function () {
            delete $rootScope.user;
            delete $rootScope.authToken;
            $cookieStore.remove('authToken');
            $location.path("/login");
        };

        /* Tentando obter o usuário válido do cookie ou enviar para página de login */
        var originalPath = $location.path();
        $location.path("/login");

        var authToken = $cookieStore.get('authToken');

        if (authToken !== undefined) {

            $rootScope.authToken = authToken;
            UserService.get(function (user) {
                $rootScope.user = user;
                $location.path(originalPath);
            });
        }

        $rootScope.cadastroUsuario = function () {
            $location.path("/cadastro/usuario");
        };

        $rootScope.initialized = true;


    });


