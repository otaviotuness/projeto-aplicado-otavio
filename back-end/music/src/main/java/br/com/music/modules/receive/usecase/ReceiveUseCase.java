package br.com.music.modules.receive.usecase;

import br.com.music.modules.receive.usecase.domain.ReceiveDomain;
import br.com.music.modules.receive.usecase.gateway.ReceiveDadosGateway;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiveUseCase {

  private final ReceiveDadosGateway receiveDadosGateway;

  public void save(ReceiveDomain receiveDomain) {
    receiveDadosGateway.save(receiveDomain);
  }

  public List<ReceiveDomain> findAll() {
    return receiveDadosGateway.findAll();
  }

  public ReceiveDomain findById(Integer id) {
    return receiveDadosGateway.findById(id);
  }

  public void deleteById(Integer id) {
    receiveDadosGateway.deleteById(id);
  }
}
