package br.com.music.modules.receive.usecase;

import br.com.music.modules.commum.utils.ValidateRequest;
import br.com.music.modules.receive.usecase.domain.ReceiveDomain;
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
  private final ValidateRequest validateRequest;

  public void save(ReceiveDomain receiveDomain) {
    final var newReceive = receiveDadosGateway.save(receiveDomain);

    final var items = newReceive.getReceiveItemDomain();

    items.forEach(i -> i.setReceiveDomain(newReceive));

    receiveItemDadosGateway.saveAll(items);
  }

  public List<ReceiveDomain> findAll() {
    return receiveDadosGateway.findAll();
  }

  public ReceiveDomain findById(Integer id) {
    final var receive = receiveDadosGateway.findById(id);

    validateRequest.validate(receive.getIdUser());

    return receive;
  }

  public void deleteById(Integer id) {
    final var receive = receiveDadosGateway.findById(id);

    validateRequest.validate(receive.getIdUser());

    final var receiveItems = receive.getReceiveItemDomain();

    receiveItemDadosGateway.deleteAll(receiveItems);

    receiveDadosGateway.deleteById(id);
  }
}
