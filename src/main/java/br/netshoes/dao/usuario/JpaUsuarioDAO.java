package br.netshoes.dao.usuario;

import br.netshoes.dao.BaseDAO;
import br.netshoes.entity.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class JpaUsuarioDAO extends BaseDAO<Usuario, Long> implements UsuarioDAO {

    public JpaUsuarioDAO() {
        super(Usuario.class);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.findByName(username);
        if (null == usuario) {
            throw new UsernameNotFoundException("The usuario with name " + username + " was not found");
        }

        return usuario;
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByName(String name) {
        final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<Usuario> criteriaQuery = builder.createQuery(this.entityClass);

        Root<Usuario> root = criteriaQuery.from(this.entityClass);
        Path<String> namePath = root.get("name");
        criteriaQuery.where(builder.equal(namePath, name));

        TypedQuery<Usuario> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
        List<Usuario> usuarios = typedQuery.getResultList();

        if (usuarios.isEmpty()) {
            return null;
        }

        return usuarios.iterator().next();
    }

}
