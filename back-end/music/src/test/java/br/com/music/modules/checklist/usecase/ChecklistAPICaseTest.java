package br.com.music.modules.checklist.usecase;

import static br.com.music.modules.configTest.GeneratorObj.EASY_RANDOM;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.music.modules.checklist.service.ChecklistService;
import br.com.music.modules.checklist.service.domain.ChecklistDomain;
import br.com.music.modules.checklist.service.gateway.ChecklistDadosGateway;
import br.com.music.modules.commum.utils.UserInfo;
import br.com.music.modules.commum.utils.ValidateRequest;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ChecklistAPICaseTest {

  private static final Integer CHECKLIST_ID = 123;
  private static final Integer USER_ID = 123;

  @InjectMocks ChecklistService checklistService;

  @Mock private ChecklistDadosGateway checklistDadosGateway;

  @Mock private UserInfo userInfo;

  @Mock private ValidateRequest validateRequest;

  @BeforeEach
  void cleanUp() {
    Mockito.reset(checklistDadosGateway);
  }

  @Test
  void givenSave_thenReturnSuccessfully() {
    final var checklist = EASY_RANDOM.nextObject(ChecklistDomain.class);

    doNothing().when(checklistDadosGateway).save(checklist);

    checklistService.save(checklist);

    verify(checklistDadosGateway, times(1)).save(checklist);
  }

  @Test
  void givenFindAll_thenReturnSuccessfully() {
    final var checklist = EASY_RANDOM.nextObject(ChecklistDomain.class);

    when(checklistService.findAll()).thenReturn(List.of(checklist));

    checklistService.findAll();

    verify(checklistDadosGateway, times(1)).findAll();
  }

  @Test
  void givenFindById_thenReturnSuccessfully() {
    final var checklist = EASY_RANDOM.nextObject(ChecklistDomain.class);

    when(checklistDadosGateway.findById(CHECKLIST_ID)).thenReturn(checklist);

    doNothing().when(validateRequest).validate(checklist.getIdUser());

    checklistService.findById(CHECKLIST_ID);

    verify(checklistDadosGateway, times(1)).findById(CHECKLIST_ID);
  }

  @Test
  void givenDeleteById_thenReturnSuccessfully() {
    final var checklist = EASY_RANDOM.nextObject(ChecklistDomain.class);
    checklist.setId(CHECKLIST_ID);

    when(checklistDadosGateway.findById(CHECKLIST_ID)).thenReturn(checklist);

    doNothing().when(checklistDadosGateway).deleteById(CHECKLIST_ID);

    doNothing().when(validateRequest).validate(checklist.getIdUser());

    checklistService.deleteById(CHECKLIST_ID);

    verify(checklistDadosGateway, times(1)).deleteById(CHECKLIST_ID);
  }
}
