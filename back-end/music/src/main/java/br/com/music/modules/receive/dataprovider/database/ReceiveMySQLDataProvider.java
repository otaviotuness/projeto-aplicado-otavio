package br.com.music.modules.receive.dataprovider.database;

import br.com.music.modules.receive.dataprovider.repository.ReceiveRepository;
import br.com.music.modules.receive.usecase.domain.Receive;
import br.com.music.modules.receive.usecase.gateway.ReceiveDadosGateway;
import br.com.music.modules.utils.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ReceiveMySQLDataProvider implements ReceiveDadosGateway {

  private final ReceiveRepository receiveRepository;

  @Override
  public void save(Receive receive) {
    log.info("Save receive.");

    receiveRepository.save(receive);

    log.info("Save receive successfully!");
  }

  @Override
  public List<Receive> findAll() {
    log.info("Find all receives");

    var receive = receiveRepository.findAll();

    log.info("Find all receives successfully");

    return receive;
  }

  @Override
  public Receive findById(Integer id) {
    log.info("Find receive by id: [{}}.", id);

    var receiveDomain =
        receiveRepository.findById(id).orElseThrow(() -> NotFoundException.with(id));

    log.info("Find successfully receive by id: [{}}.", id);

    return receiveDomain;
  }

  @Override
  public void deleteById(Integer id) {
    log.info("Delete receive by id: [{}}.", id);

    receiveRepository.deleteById(id);

    log.info("Delete successfully receive by id: [{}}.", id);
  }
}
