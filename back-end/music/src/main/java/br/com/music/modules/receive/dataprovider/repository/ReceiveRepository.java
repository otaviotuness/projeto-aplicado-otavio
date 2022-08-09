package br.com.music.modules.receive.dataprovider.repository;

import br.com.music.modules.receive.usecase.domain.ReceiveDomain;
import br.com.music.modules.receive.usecase.domain.ReceiveItemDomain;
import org.springframework.stereotype.Repository;

import java.util.List;

import static br.com.music.modules.shared.GeneratorObj.EASY_RANDOM;

@Repository
public class ReceiveRepository {

    public void save(ReceiveDomain receiveDomain) {}

    public ReceiveDomain findById(Integer id) {
        var receiveDomain = EASY_RANDOM.nextObject(ReceiveDomain.class);
        receiveDomain.setReceiveItem(List.of(EASY_RANDOM.nextObject(ReceiveItemDomain.class)));

        return receiveDomain;
    }

    public List<ReceiveDomain> findAll(){
        var receives = List.of(EASY_RANDOM.nextObject(ReceiveDomain.class));
        receives.get(0).setReceiveItem(List.of(EASY_RANDOM.nextObject(ReceiveItemDomain.class)));

        return receives;
    }
    public void deleteById(Integer id) {}
}
