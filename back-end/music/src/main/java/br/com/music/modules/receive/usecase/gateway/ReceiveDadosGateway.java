package br.com.music.modules.receive.usecase.gateway;

import br.com.music.modules.receive.usecase.domain.Receive;
import java.util.List;

public interface ReceiveDadosGateway {

  void save(Receive receiveDto);

  List<Receive> findAll();

  Receive findById(Integer id);

  void deleteById(Integer id);
}
