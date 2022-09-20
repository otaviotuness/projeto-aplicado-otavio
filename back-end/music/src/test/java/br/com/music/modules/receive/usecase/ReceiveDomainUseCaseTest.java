package br.com.music.modules.receive.usecase;

import static br.com.music.modules.configTest.GeneratorObj.EASY_RANDOM;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.music.modules.receive.usecase.domain.ReceiveDomain;
import br.com.music.modules.receive.usecase.gateway.ReceiveDadosGateway;
import br.com.music.modules.receive.usecase.gateway.ReceiveItemDadosGateway;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReceiveDomainUseCaseTest {

  private static final Integer RECEIVE_ID = 123;

  @InjectMocks ReceiveUseCase receiveUseCase;

  @Mock private ReceiveDadosGateway receiveDadosGateway;

  @Mock private ReceiveItemDadosGateway receiveItemDadosGateway;

  @BeforeEach
  void cleanUp() {
    Mockito.reset(receiveDadosGateway, receiveItemDadosGateway);
  }

  @Test
  void givenSave_thenReturnSuccessfully() {
    final var receive = EASY_RANDOM.nextObject(ReceiveDomain.class);
    final var receiveExpected = EASY_RANDOM.nextObject((ReceiveDomain.class));

    when(receiveDadosGateway.save(receive)).thenReturn(receiveExpected);

    doNothing().when(receiveItemDadosGateway).saveAll(receiveExpected.getReceiveItemDomain());

    receiveUseCase.save(receive);

    verify(receiveDadosGateway, times(1)).save(receive);
    verify(receiveItemDadosGateway, times(1)).saveAll(receiveExpected.getReceiveItemDomain());
  }

  @Test
  void givenFindAll_thenReturnSuccessfully() {
    final var receive = EASY_RANDOM.nextObject(ReceiveDomain.class);

    when(receiveDadosGateway.findAll()).thenReturn(List.of(receive));

    receiveUseCase.findAll();

    verify(receiveDadosGateway, times(1)).findAll();
  }

  @Test
  void givenFindById_thenReturnSuccessfully() {
    final var receive = EASY_RANDOM.nextObject(ReceiveDomain.class);

    when(receiveDadosGateway.findById(RECEIVE_ID)).thenReturn(receive);

    receiveUseCase.findById(RECEIVE_ID);

    verify(receiveDadosGateway, times(1)).findById(RECEIVE_ID);
  }

  @Test
  void givenDeleteById_thenReturnSuccessfully() {
    final var receive = EASY_RANDOM.nextObject(ReceiveDomain.class);

    when(receiveDadosGateway.findById(RECEIVE_ID)).thenReturn(receive);

    doNothing().when(receiveItemDadosGateway).deleteAll(receive.getReceiveItemDomain());

    receiveUseCase.deleteById(RECEIVE_ID);

    verify(receiveDadosGateway, times(1)).deleteById(RECEIVE_ID);
  }
}
