package br.netshoes.dao;

import br.netshoes.entity.Logradouro;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

/**
 * Created by Thiago on 15/04/2015.
 */
@Service public class LogradouroDAO extends BaseDAO<Logradouro, Long> {
        public LogradouroDAO() {
                super(Logradouro.class);
        }

        public Logradouro byCep(final String cep) {
                final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
                final CriteriaQuery<Logradouro> criteriaQuery = builder.createQuery(Logradouro.class);

                Root<Logradouro> root = criteriaQuery.from(Logradouro.class);
                Path<Integer> cepPath = root.get("cep");
                criteriaQuery.where(builder.equal(cepPath, cep));

                TypedQuery<Logradouro> typedQuery = this.getEntityManager().createQuery(criteriaQuery);

                try {
                        return typedQuery.getSingleResult();
                }
                catch (NoResultException n) {
                        return null;
                }

        }

}
