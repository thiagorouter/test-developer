angular.module("netshoes").factory('CadastroUsuarioService', function ($resource) {

    return $resource('rest/usuario/:action', {},
        {
            inserir: {
                method: 'POST',
                params: {'action': 'inserir'}
            }
        }
    );


});
