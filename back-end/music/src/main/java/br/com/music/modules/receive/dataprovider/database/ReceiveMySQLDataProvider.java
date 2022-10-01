package br.com.music.modules.receive.dataprovider.database;

import br.com.music.modules.commum.exceptions.DataIntegrityException;
import br.com.music.modules.commum.exceptions.NotFoundException;
import br.com.music.modules.commum.utils.UserInfo;
import br.com.music.modules.receive.dataprovider.repository.ReceiveItemRepository;
import br.com.music.modules.receive.dataprovider.repository.ReceiveRepository;
import br.com.music.modules.receive.usecase.domain.ReceiveDomain;
import br.com.music.modules.receive.usecase.gateway.ReceiveDadosGateway;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ReceiveMySQLDataProvider implements ReceiveDadosGateway {

  private final ReceiveRepository receiveRepository;
  private final ReceiveItemRepository receiveItemRepository;
  private final UserInfo userInfo;

  @Override
  public ReceiveDomain save(ReceiveDomain receiveDomain) {
    log.info("Save receive.");

    final var receive = Optional.ofNullable(receiveDomain.getItems());

    //    final var totalValue =
    //        receiveDomain.getItems().stream()
    //            .map(ReceiveItemDomain::getValue)
    //            .reduce(BigDecimal::add)
    //            .orElse(BigDecimal.ZERO);
    //
    //    final var totalValue = receive.stream().map(ReceiveItemDomain::getValue);

    receiveDomain.setTotalValueReceive(totalValue);

    final var newReceive = receiveRepository.save(receiveDomain);

    final var items = receiveDomain.getItems();

    if (!items.isEmpty()) {
      items.forEach(i -> i.setReceiveDomain(newReceive));

      final var oldItems =
          receiveRepository.findById(newReceive.getId()).orElse(new ReceiveDomain()).getItems();

      if (!oldItems.isEmpty()) {
        receiveItemRepository.deleteAll(oldItems);
      }

      receiveItemRepository.saveAll(items);
    }

    log.info("Save receive successfully!");

    return newReceive;
  }

  @Override
  public List<ReceiveDomain> findAll() {
    log.info("Find all receives");

    var receive =
        receiveRepository.findAllReceive(
            userInfo.isAdmin(), userInfo.getUserId(), userInfo.getUserIdMaster());

    log.info("Find all receives successfully");

    return receive;
  }

  @Override
  public ReceiveDomain findById(Integer id) {
    log.info("Find receive by id: [{}}.", id);

    var receiveDomain =
        receiveRepository.findById(id).orElseThrow(() -> NotFoundException.with(id));

    log.info("Find successfully receive by id: [{}}.", id);

    return receiveDomain;
  }

  @Override
  public void deleteById(Integer id) {
    log.info("Delete receive by id: [{}}.", id);

    try {
      receiveRepository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException(
          "Não é possível excluir porque há recebimentos relacionados");
    }

    log.info("Delete successfully receive by id: [{}}.", id);
  }
}
