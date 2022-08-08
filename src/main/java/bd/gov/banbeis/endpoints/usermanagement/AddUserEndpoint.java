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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.ValidationException;

@Endpoint
@AnonymousAllowed
@Transactional
public class AddUserEndpoint {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public @Nonnull List<@Nonnull Role> getRoles(){
        List<Role> roles = roleRepository.findAll();
        return roles;
    }


    public @Nonnull User getUserById(String id){
        Optional<User> user = userRepository.findById(UUID.fromString(id));
        if(user.isEmpty())
            return new User();
        return user.get();
    }


    public void saveUser(User user){
//        user.setRoles(this.selectedRoles);
        if(!user.getPassword().equals(user.getConfirmPassword())){
            throw new ValidationException("Both password and confirm password is needed to be matched");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
    }
}
