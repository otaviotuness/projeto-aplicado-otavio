package br.com.music.modules.receive.dataprovider.database;

import br.com.music.modules.commum.exceptions.DataIntegrityException;
import br.com.music.modules.commum.exceptions.NotFoundException;
import br.com.music.modules.commum.utils.UserInfo;
import br.com.music.modules.receive.dataprovider.repository.ReceiveItemRepository;
import br.com.music.modules.receive.dataprovider.repository.ReceiveRepository;
import br.com.music.modules.receive.usecase.domain.ReceiveDomain;
import br.com.music.modules.receive.usecase.domain.ReceiveItemDomain;
import br.com.music.modules.receive.usecase.gateway.ReceiveDadosGateway;
import java.math.BigDecimal;
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

    final var newReceive = receiveRepository.save(receiveDomain);

    verifyOldItems(newReceive.getId());

    Optional.ofNullable(receiveDomain.getItems())
        .ifPresent(
            receiveItems -> {
              final var totalValue =
                  receiveItems.stream()
                      .map(ReceiveItemDomain::getValue)
                      .reduce(BigDecimal::add)
                      .orElse(BigDecimal.ZERO);

              newReceive.setTotalValueReceive(totalValue);

              receiveItems.forEach(i -> i.setReceiveDomain(newReceive));

              receiveItemRepository.saveAll(receiveItems);
            });

    receiveRepository.save(newReceive);

    log.info("Save receive successfully!");

    return newReceive;
  }

  private void verifyOldItems(final Integer receiveId) {
    final var oldItems =
        receiveRepository.findById(receiveId).orElse(new ReceiveDomain()).getItems();

    if (!oldItems.isEmpty()) {
      receiveItemRepository.deleteAll(oldItems);
    }
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
