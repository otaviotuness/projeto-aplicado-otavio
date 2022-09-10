package br.com.music.modules.receive.dataprovider.database;

import br.com.music.modules.receive.dataprovider.repository.ReceiveItemRepository;
import br.com.music.modules.receive.usecase.domain.ReceiveItem;
import br.com.music.modules.receive.usecase.gateway.ReceiveItemDadosGateway;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ReceiveItemMySQLDataProvider implements ReceiveItemDadosGateway {

  private final ReceiveItemRepository receiveItemRepository;

  @Override
  public void saveAll(List<ReceiveItem> items) {
    log.info("Save receive item.");

    receiveItemRepository.saveAll(items);

    log.info("Save receive successfully!");
  }

  @Override
  public void deleteAll(List<ReceiveItem> items) {
    log.info("Delete receive item.");

    receiveItemRepository.deleteAll(items);

    log.info("Delete receive successfully!");
  }
}
