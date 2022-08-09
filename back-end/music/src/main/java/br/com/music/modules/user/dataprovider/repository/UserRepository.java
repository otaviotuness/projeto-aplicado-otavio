package br.com.music.modules.user.dataprovider.repository;


import br.com.music.modules.user.usecase.domain.UserDomain;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

import static br.com.music.modules.shared.GeneratorObj.EASY_RANDOM;

@Repository
public class UserRepository {

    public void save(UserDomain userDomain){};

    public List<UserDomain> findAll() {
        return Arrays.asList(EASY_RANDOM.nextObject(UserDomain.class));
    }

    public UserDomain findById(Integer id){
        return EASY_RANDOM.nextObject(UserDomain.class);
    }

    public void deleteById(Integer id){};

}
