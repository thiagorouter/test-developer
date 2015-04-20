package br.netshoes.service.commons;

import br.netshoes.dao.usuario.UsuarioDAO;
import br.netshoes.entity.*;
import br.netshoes.service.EnderecoService;
import br.netshoes.service.LogradouroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.Column;

@Service
public class DataBaseInitializer {

    @Autowired
    private UsuarioDAO usuarioDAO;
    @Autowired
    private LogradouroService logradouroService;
    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    protected DataBaseInitializer() {
    }

    public DataBaseInitializer(UsuarioDAO usuarioDAO, PasswordEncoder passwordEncoder) {
        this.usuarioDAO = usuarioDAO;
        this.passwordEncoder = passwordEncoder;
    }

    public void initDataBase() {

        populateDataBase();

    }

    private void populateDataBase() {

        createUserAdmin();
    }

    private void createUserAdmin() {
        Usuario adminUsuario = new Usuario("admin", this.passwordEncoder.encode("admin"));
        adminUsuario.addRole("usuario");
        adminUsuario.addRole("admin");
        this.usuarioDAO.save(adminUsuario);
    }
}
