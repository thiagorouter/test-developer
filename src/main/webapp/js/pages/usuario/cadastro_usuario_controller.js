angular.module("netshoes").controller("CadastroUsuarioController",
    function ($scope, $location, CadastroUsuarioService) {

        //$scope.usuario = new CadastroUsuarioService();

        $scope.clearForm = function () {
            $scope.user = {};
            $scope.form.$setPristine();
        };


        $scope.inserirUsuario = function (isValid) {

            if (true == isValid) {

                $scope.user.$inserir(function () {
                    $location.path('/cadastro/usuario');
                    $scope.clearForm();
                });
            }
        };


        $scope.buscarCep = function (isValid) {

            if (true == isValid) {

                $scope.user.$inserir(function () {
                    $location.path('/logradouro/cep/{cep}');
                    $scope.clearForm();
                });
            }
        };
    });
