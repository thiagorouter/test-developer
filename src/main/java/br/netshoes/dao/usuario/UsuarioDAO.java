package br.netshoes.dao.usuario;

import br.netshoes.dao.Dao;
import br.netshoes.entity.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioDAO extends Dao<Usuario, Long>, UserDetailsService {

    Usuario findByName(String name);

}
