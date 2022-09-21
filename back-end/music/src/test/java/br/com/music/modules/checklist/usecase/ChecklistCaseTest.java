package br.com.music.modules.checklist.usecase;

import static br.com.music.modules.configTest.GeneratorObj.EASY_RANDOM;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import br.com.music.modules.checklist.usecase.domain.ChecklistDomain;
import br.com.music.modules.checklist.usecase.gateway.ChecklistDadosGateway;
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
class ChecklistCaseTest {

  private static final Integer CHECKLIST_ID = 123;
  private static final Integer USER_ID = 123;

  @InjectMocks ChecklistUseCase checklistUseCase;

  @Mock private ChecklistDadosGateway checklistDadosGateway;

  @Mock private UserInfo userInfo;

  @Mock private ValidateRequest validateRequest;

  @BeforeEach
  void cleanUp() {
    Mockito.reset(checklistDadosGateway);
  }

  @Test
  void givenSave_thenReturnSuccessfully() {
    doNothing().when(checklistDadosGateway).save(any());

    checklistUseCase.save(any());

    verify(checklistDadosGateway, times(1)).save(any());
  }

  @Test
  void givenFindAll_thenReturnSuccessfully() {
    final var checklist = EASY_RANDOM.nextObject(ChecklistDomain.class);

    when(checklistUseCase.findAll()).thenReturn(List.of(checklist));

    checklistUseCase.findAll();

    verify(checklistDadosGateway, times(1)).findAll();
  }

  @Test
  void givenFindById_thenReturnSuccessfully() {
    final var checklist = EASY_RANDOM.nextObject(ChecklistDomain.class);

    when(checklistDadosGateway.findById(CHECKLIST_ID)).thenReturn(checklist);

    doNothing().when(validateRequest).validate(checklist.getIdUser());

    checklistUseCase.findById(CHECKLIST_ID);

    verify(checklistDadosGateway, times(1)).findById(CHECKLIST_ID);
  }

  @Test
  void givenDeleteById_thenReturnSuccessfully() {
    final var checklist = EASY_RANDOM.nextObject(ChecklistDomain.class);
    checklist.setId(CHECKLIST_ID);

    when(checklistDadosGateway.findById(CHECKLIST_ID)).thenReturn(checklist);

    doNothing().when(checklistDadosGateway).deleteById(CHECKLIST_ID);

    doNothing().when(validateRequest).validate(checklist.getIdUser());

    checklistUseCase.deleteById(CHECKLIST_ID);

    verify(checklistDadosGateway, times(1)).deleteById(CHECKLIST_ID);
  }
}
