package bd.gov.banbeis.endpoints.usermanagement;

import bd.gov.banbeis.data.entity.Role;
import bd.gov.banbeis.data.entity.User;
import bd.gov.banbeis.data.repository.RoleRepository;
import bd.gov.banbeis.data.repository.UserRepository;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.Endpoint;
import dev.hilla.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    /**
     * @param id
     * @return
     */
    
    public @Nonnull User getUserById(String id){
        Optional<User> user = userRepository.findById(UUID.fromString(id));
        if(user.isEmpty())
            return new User();
        return user.get();
    }
}
