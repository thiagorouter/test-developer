package br.netshoes.service;

import br.netshoes.dao.EnderecoDAO;
import br.netshoes.entity.Endereco;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Thiago on 16/04/2015.
 */
@Service
public class EnderecoService implements BaseService<Endereco, Long> {


    @Autowired
    private EnderecoDAO enderecoDAO;

    public Endereco save(final Endereco endereco) {
        return enderecoDAO.save(endereco);
    }

    public Endereco find(final Long id) {
        return this.enderecoDAO.find(id);
    }

    public void delete(final Long id) {
        this.enderecoDAO.delete(id);
    }

    @Override
    public List<Endereco> findAll() {
        return null;
    }

    @Override
    public List<Endereco> findAll(Integer maxResult) {
        return null;
    }

}
