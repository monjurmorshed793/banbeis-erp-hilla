//package bd.gov.banbeis.data.generator;
//
//import bd.gov.banbeis.data.RoleEnum;
//import bd.gov.banbeis.data.entity.Role;
//import bd.gov.banbeis.data.entity.User;
//import bd.gov.banbeis.data.repository.RoleRepository;
//import bd.gov.banbeis.data.repository.UserRepository;
//import com.vaadin.flow.spring.annotation.SpringComponent;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Set;
//
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.transaction.annotation.Transactional;
//
//@SpringComponent
//@RequiredArgsConstructor
//public class DataGenerator {
//
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//
//    private final PasswordEncoder passwordEncoder;
//
//
//
//    @Bean
//    @Transactional
//    public CommandLineRunner loadData(PasswordEncoder passwordEncoder, UserRepository userRepository) {
//        return args -> {
//            Logger logger = LoggerFactory.getLogger(getClass());
//            if (userRepository.count() != 0L) {
//                logger.info("Using existing database");
//                return;
//            }
//            Role roleAdmin = new Role();
//            roleAdmin.setRole("ADMIN");
//            roleAdmin = roleRepository.save(roleAdmin);
//            Role roleAdminstration = new Role();
//            roleAdminstration.setRole("ADMINISTRATION");
//            roleAdminstration = roleRepository.save(roleAdminstration);
//            Role roleAP = new Role();
//            roleAP.setRole("AP");
//            roleAP = roleRepository.save(roleAP);
//
//            User adminUser = new User();
//            adminUser.setRoles(Arrays.asList(roleAdmin, roleAP, roleAdminstration));
//            adminUser.setUsername("admin");
//            adminUser.setEmail("monjurmorshed794@outlook.com");
//            adminUser.setFullName("Super User");
//            adminUser.setHashedPassword(passwordEncoder.encode("password"));
//            userRepository.save(adminUser);
//
//            User apUser = new User();
//            apUser.setRoles(Arrays.asList(roleAP));
//            apUser.setUsername("ap_sylhetsadar");
//            apUser.setEmail("ap_sylhetsadar@banbeis.gov.bd");
//            apUser.setFullName("MONJUR-E-MORSHED");
//            apUser.setHashedPassword(passwordEncoder.encode("password"));
//            userRepository.save(apUser);
//        };
//    }
//
//}