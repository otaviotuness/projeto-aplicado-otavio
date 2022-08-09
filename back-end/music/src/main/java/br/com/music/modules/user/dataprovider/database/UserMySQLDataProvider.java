package br.com.music.modules.user.dataprovider.database;

import br.com.music.modules.user.dataprovider.repository.UserRepository;
import br.com.music.modules.user.usecase.domain.UserDomain;
import br.com.music.modules.user.usecase.gateway.UserDadosGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserMySQLDataProvider implements UserDadosGateway {

    private final UserRepository userRepository;

    @Override
    public void saveUser(UserDomain userDomain) {
        log.info("Save user in DB.");

        userRepository.save(userDomain);

        log.info("Save user ind DB successfully!");
    }

    @Override
    public List<UserDomain> findAll() {
        log.info("Search users.");

        List<UserDomain> usuarios = userRepository.findAll();

        log.info("Search users successfully.");

        return usuarios;
    }

    @Override
    public UserDomain findById(Integer id) {
        log.info("Search users.");

        var user = userRepository.findById(id);

        log.info("Search users successfully.");

        return user;
    }

    @Override
    public void deleteUserById(Integer id){
        log.info("Delete user by id: [{}].", id);

        userRepository.deleteById(id);

        log.info("Delete user by id: [{}] with sucessfully.", id);
    }
}
