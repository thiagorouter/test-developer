package br.netshoes.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity public class Usuario implements BaseEntity, UserDetails {

        @Id @GeneratedValue private Long id;

        @Column(unique = true, length = 16, nullable = false) private String name;

        @Column(length = 1000, nullable = false) private String password;

        @ElementCollection(fetch = FetchType.EAGER) private Set<String> roles = new HashSet<String>();

        @ManyToOne(cascade = CascadeType.ALL) private Endereco enderecos;

        protected Usuario() {
        }

        public Usuario(String name, String passwordHash) {
                this.name = name;
                this.password = passwordHash;
        }

        public Long getId() {
                return this.id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getName() {
                return this.name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Set<String> getRoles() {
                return this.roles;
        }

        public void setRoles(Set<String> roles) {
                this.roles = roles;
        }

        public void addRole(String role) {
                this.roles.add(role);
        }

        @Override public String getPassword() {
                return this.password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public Endereco getEnderecos() {
                return enderecos;
        }

        public void setEnderecos(Endereco enderecos) {
                this.enderecos = enderecos;
        }

        @Override public Collection<? extends GrantedAuthority> getAuthorities() {
                Set<String> roles = this.getRoles();

                if (roles == null) {
                        return Collections.emptyList();
                }

                Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
                for (String role : roles) {
                        authorities.add(new SimpleGrantedAuthority(role));
                }

                return authorities;
        }

        @Override public String getUsername() {
                return this.name;
        }

        @Override public boolean isAccountNonExpired() {
                return true;
        }

        @Override public boolean isAccountNonLocked() {
                return true;
        }

        @Override public boolean isCredentialsNonExpired() {
                return true;
        }

        @Override public boolean isEnabled() {
                return true;
        }

        @Override public boolean equals(Object o) {
                if (this == o)
                        return true;
                if (!(o instanceof Usuario))
                        return false;

                Usuario usuario = (Usuario)o;

                if (!name.equals(usuario.name))
                        return false;

                return true;
        }

        @Override public int hashCode() {
                return name.hashCode();
        }
}
