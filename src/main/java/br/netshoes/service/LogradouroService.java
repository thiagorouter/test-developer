package br.netshoes.service;

import br.netshoes.dao.LogradouroDAO;
import br.netshoes.entity.Logradouro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogradouroService implements BaseService<Logradouro, Long> {

    private final Logger logger = LoggerFactory.getLogger(LogradouroService.class);

    @Autowired
    private LogradouroDAO logradouroDAO;

    public Logradouro byCep(final String cep) {
        return this.logradouroDAO.byCep(cep);
    }

    public Logradouro save(final Logradouro logradouro) {
        return this.logradouroDAO.save(logradouro);
    }

    @Override
    public Logradouro find(Long id) {
        return this.logradouroDAO.find(id);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Logradouro> findAll() {
        return this.logradouroDAO.findAll();
    }

    @Override
    public List<Logradouro> findAll(final Integer maxResult) {
        return this.logradouroDAO.findAll(maxResult);
    }

}

