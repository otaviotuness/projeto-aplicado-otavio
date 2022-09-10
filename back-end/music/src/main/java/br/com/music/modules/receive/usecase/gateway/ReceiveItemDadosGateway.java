package br.com.music.modules.receive.usecase.gateway;

import br.com.music.modules.receive.usecase.domain.ReceiveItem;
import java.util.List;

public interface ReceiveItemDadosGateway {

  void saveAll(List<ReceiveItem> receiveDto);

  void deleteAll(List<ReceiveItem> items);
}
