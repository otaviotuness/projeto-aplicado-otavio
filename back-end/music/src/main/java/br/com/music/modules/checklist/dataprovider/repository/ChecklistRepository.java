package br.com.music.modules.checklist.dataprovider.repository;

import br.com.music.modules.checklist.usecase.domain.ChecklistDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChecklistRepository extends JpaRepository<ChecklistDomain, Integer> {}
