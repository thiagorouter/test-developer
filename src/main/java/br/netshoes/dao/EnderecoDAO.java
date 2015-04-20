package br.netshoes.dao;

import br.netshoes.entity.Endereco;
import org.springframework.stereotype.Service;

/**
 * Created by Thiago on 15/04/2015.
 */
@Service public class EnderecoDAO extends BaseDAO<Endereco, Long> {

        public EnderecoDAO() {
                super(Endereco.class);
        }
        
}
