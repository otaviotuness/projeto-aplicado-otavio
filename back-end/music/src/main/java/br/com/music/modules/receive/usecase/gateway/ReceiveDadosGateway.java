package br.com.music.modules.receive.usecase.gateway;

import br.com.music.modules.receive.usecase.domain.ReceiveDomain;
import java.util.List;

public interface ReceiveDadosGateway {

  ReceiveDomain save(ReceiveDomain receiveDomainDto);

  List<ReceiveDomain> findAll();

  ReceiveDomain findById(Integer id);

  void deleteById(Integer id);
}
