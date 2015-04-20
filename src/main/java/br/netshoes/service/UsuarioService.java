package br.netshoes.service;

import br.netshoes.dao.usuario.UsuarioDAO;
import br.netshoes.entity.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioService implements BaseService<Usuario, Long> {

    private final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public Usuario save(Usuario usuario) {
        return this.usuarioDAO.save(usuario);
    }

    @Override
    public Usuario find(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Usuario> findAll() {
        return null;
    }

    @Override
    public List<Usuario> findAll(Integer maxResult) {
        return null;
    }
}
