package bd.gov.banbeis.endpoints.usermanagement;

import bd.gov.banbeis.data.entity.Role;
import bd.gov.banbeis.data.repository.RoleRepository;
import bd.gov.banbeis.data.repository.UserRepository;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.Endpoint;
import dev.hilla.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Endpoint
@AnonymousAllowed
public class AddUserEndpoint {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public @Nonnull List<@Nonnull Role> getRoles(){
        List<Role> roles = roleRepository.findAll();
        return roles;
    }
}
