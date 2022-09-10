package br.com.music.modules.receive.usecase;

import br.com.music.modules.receive.usecase.domain.Receive;
import br.com.music.modules.receive.usecase.gateway.ReceiveDadosGateway;
import br.com.music.modules.receive.usecase.gateway.ReceiveItemDadosGateway;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiveUseCase {

  private final ReceiveDadosGateway receiveDadosGateway;
  private final ReceiveItemDadosGateway receiveItemDadosGateway;

  public void save(Receive receive) {
    final var newReceive = receiveDadosGateway.save(receive);

    final var items = newReceive.getReceiveItem();

    items.forEach(i -> i.setReceive(newReceive));

    receiveItemDadosGateway.saveAll(items);
  }

  public List<Receive> findAll() {
    return receiveDadosGateway.findAll();
  }

  public Receive findById(Integer id) {
    return receiveDadosGateway.findById(id);
  }

  public void deleteById(Integer id) {
    final var receiveItems = receiveDadosGateway.findById(id).getReceiveItem();

    receiveItemDadosGateway.deleteAll(receiveItems);

    receiveDadosGateway.deleteById(id);
  }
}
