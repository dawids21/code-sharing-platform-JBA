package platform.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import platform.service.model.Program;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {

    Page<Program> findAllByRestrictedFalseOrderByCreatedDesc(Pageable pageable);
}
