package br.com.music.modules.receive.controller;

import br.com.music.modules.receive.controller.dto.ReceiveDto;
import br.com.music.modules.receive.controller.mapper.ReceiveMapper;
import br.com.music.modules.receive.dataprovider.repository.ReceiveItemRepository;
import br.com.music.modules.receive.usecase.ReceiveUseCase;
import br.com.music.modules.receive.usecase.domain.Receive;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReceiveController {

  private final ReceiveMapper receiveMapper;
  private final ReceiveUseCase receiveUseCase;
  private final ReceiveItemRepository receiveItemRepository;

  @GetMapping("/receive/{id}")
  public Receive findById(@PathVariable Integer id) {

    var receiveDomain = receiveUseCase.findById(id);

    return receiveDomain;
  }

  @GetMapping("/receives")
  public ResponseEntity<List<Receive>> findAll() {

    var receives = receiveUseCase.findAll();

    return ResponseEntity.ok(receives);
  }

  @PostMapping("/receive")
  public ResponseEntity<String> save(@Valid @RequestBody ReceiveDto receiveDto) {

    var receive = receiveMapper.toDomain(receiveDto);

    receiveUseCase.save(receive);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @DeleteMapping("/receive/{id}")
  public ResponseEntity<String> deleteById(@PathVariable Integer id) {

    receiveUseCase.deleteById(id);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }
}
