package br.com.music.modules.receive.controller;

import br.com.music.modules.receive.controller.dto.ReceiveDto;
import br.com.music.modules.receive.controller.dto.ReceiveResponseDto;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface Receive {

  @GetMapping("/receive/{id}")
  ResponseEntity<ReceiveResponseDto> findById(@PathVariable Integer id);

  @GetMapping("/receives")
  List<ReceiveResponseDto> findAll();

  @PostMapping("/receive")
  ResponseEntity<String> save(@Valid @RequestBody ReceiveDto receiveDto);

  @DeleteMapping("/receive/{id}")
  ResponseEntity<String> deleteById(@PathVariable Integer id);
}
