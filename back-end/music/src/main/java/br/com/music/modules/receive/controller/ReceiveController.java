package br.com.music.modules.receive.controller;

import br.com.music.modules.receive.controller.dto.ReceiveDto;
import br.com.music.modules.receive.controller.dto.ReceiveResponseDto;
import br.com.music.modules.receive.controller.mapper.ReceiveMapper;
import br.com.music.modules.receive.usecase.ReceiveUseCase;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReceiveController implements Receive {

  private final ReceiveMapper receiveMapper;
  private final ReceiveUseCase receiveUseCase;

  public ResponseEntity<ReceiveResponseDto> findById(@PathVariable Integer id) {

    final var receive = receiveUseCase.findById(id);

    return ResponseEntity.ok(receiveMapper.toResponseDto(receive));
  }

  public List<ReceiveResponseDto> findAll() {

    var receives = receiveUseCase.findAll();

    return receiveMapper.toListResponseDto(receives);
  }

  public ResponseEntity<String> save(@Valid @RequestBody ReceiveDto receiveDto) {

    var receive = receiveMapper.toDomain(receiveDto);

    receiveUseCase.save(receive);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  public ResponseEntity<String> deleteById(@PathVariable Integer id) {

    receiveUseCase.deleteById(id);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }
}
