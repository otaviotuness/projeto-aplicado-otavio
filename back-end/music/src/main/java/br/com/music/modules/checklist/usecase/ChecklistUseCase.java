package br.com.music.modules.checklist.usecase;

import br.com.music.modules.checklist.usecase.domain.ChecklistDomain;
import br.com.music.modules.checklist.usecase.gateway.ChecklistDadosGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChecklistUseCase {

    private final ChecklistDadosGateway checklistDadosGateway;

    public void save(ChecklistDomain checklistDomain) {
        checklistDadosGateway.save(checklistDomain);
    }

    public List<ChecklistDomain> findAll(){
        return checklistDadosGateway.findAll();
    }

    public ChecklistDomain findById(Integer id){
        return checklistDadosGateway.findById(id);
    }

    public void deleteById(Integer id){
        checklistDadosGateway.deleteById(id);
    }

}
