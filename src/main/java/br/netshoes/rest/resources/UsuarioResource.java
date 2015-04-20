package br.netshoes.rest.resources;

import br.netshoes.rest.TokenUtils;
import br.netshoes.transfer.TokenTransfer;
import br.netshoes.transfer.UserTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Component
@Path("/usuario")
public class UsuarioResource {

    @Qualifier("jpaUsuarioDAO")
    @Autowired
    private UserDetailsService userService;

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authManager;

    /**
     * Retrieves the currently logged in usuario.
     *
     * @return A transfer containing the username and the roles.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserTransfer getUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();

        if (principal instanceof String && principal.equals("anonymousUser")) {
            throw new WebApplicationException(401);
        }
        UserDetails userDetails = (UserDetails) principal;

        return new UserTransfer(userDetails.getUsername(), this.createRoleMap(userDetails));
    }

    /**
     * Authenticates a usuario and creates an authentication token.
     *
     * @param username The name of the usuario.
     * @param password The password of the usuario.
     * @return A transfer containing the authentication token.
     */
    @Path("authenticate")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public TokenTransfer authenticate(@FormParam("username") String username, @FormParam("password") String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = this.authManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = this.userService.loadUserByUsername(username);

        return new TokenTransfer(TokenUtils.createToken(userDetails));
    }

    private Map<String, Boolean> createRoleMap(UserDetails userDetails) {
        Map<String, Boolean> roles = new HashMap<String, Boolean>();
        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            roles.put(authority.getAuthority(), Boolean.TRUE);
        }

        return roles;
    }

}
