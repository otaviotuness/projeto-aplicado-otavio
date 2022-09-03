package br.com.music.modules.receive.usecase;

import br.com.music.modules.receive.usecase.domain.Receive;
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

  public void save(Receive receive) {
    receiveDadosGateway.save(receive);
  }

  public List<Receive> findAll() {
    return receiveDadosGateway.findAll();
  }

  public Receive findById(Integer id) {
    return receiveDadosGateway.findById(id);
  }

  public void deleteById(Integer id) {
    receiveDadosGateway.deleteById(id);
  }
}
