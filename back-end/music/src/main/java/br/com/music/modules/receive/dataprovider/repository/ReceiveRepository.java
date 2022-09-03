package br.com.music.modules.receive.dataprovider.repository;

import static br.com.music.modules.utils.GeneratorObj.EASY_RANDOM;

import br.com.music.modules.receive.usecase.domain.ReceiveDomain;
import br.com.music.modules.receive.usecase.domain.ReceiveItemDomain;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ReceiveRepository {

  public void save(ReceiveDomain receiveDomain) {}

  public ReceiveDomain findById(Integer id) {
    var receiveDomain = EASY_RANDOM.nextObject(ReceiveDomain.class);
    receiveDomain.setReceiveItem(List.of(EASY_RANDOM.nextObject(ReceiveItemDomain.class)));

    return receiveDomain;
  }

  public List<ReceiveDomain> findAll() {
    var receives = List.of(EASY_RANDOM.nextObject(ReceiveDomain.class));
    receives.get(0).setReceiveItem(List.of(EASY_RANDOM.nextObject(ReceiveItemDomain.class)));

    return receives;
  }

  public void deleteById(Integer id) {}
}
