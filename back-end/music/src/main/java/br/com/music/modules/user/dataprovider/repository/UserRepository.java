package br.com.music.modules.user.dataprovider.repository;

import static br.com.music.modules.utils.GeneratorObj.EASY_RANDOM;

import br.com.music.modules.user.usecase.domain.UserDomain;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

  public void save(UserDomain userDomain) {}
  ;

  public List<UserDomain> findAll() {
    return Arrays.asList(EASY_RANDOM.nextObject(UserDomain.class));
  }

  public UserDomain findById(Integer id) {
    return EASY_RANDOM.nextObject(UserDomain.class);
  }

  public void deleteById(Integer id) {}
  ;
}
