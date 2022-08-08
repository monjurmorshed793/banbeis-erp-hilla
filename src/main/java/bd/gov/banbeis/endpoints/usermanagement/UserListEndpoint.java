package bd.gov.banbeis.endpoints.usermanagement;

import bd.gov.banbeis.data.entity.User;
import bd.gov.banbeis.data.repository.UserRepository;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.Endpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.constraints.NotNull;
import java.util.List;

@Endpoint
@AnonymousAllowed
public class UserListEndpoint {

    @Autowired
    private UserRepository userRepository;

    public String sayHello(){
        return "Hello world!";
    }

    public User addUser(User user){
        return userRepository.save(user);
    }


    class PageResponse<T> {
        public List<T> content;
        public long size;
    }

    public PageResponse<User> getPage(int page, int size){
        var dbPage = userRepository.findAll(PageRequest.of(page, size));
        var response = new PageResponse<User>();
        response.content = dbPage.getContent();
        response.size = dbPage.getSize();
        return response;
    }
}

