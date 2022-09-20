package br.com.music.modules.receive.usecase.gateway;

import br.com.music.modules.receive.usecase.domain.ReceiveItemDomain;
import java.util.List;

public interface ReceiveItemDadosGateway {

  void saveAll(List<ReceiveItemDomain> receiveDto);

  void deleteAll(List<ReceiveItemDomain> items);
}
