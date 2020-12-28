package platform.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import platform.service.model.Program;

import java.util.UUID;

@Repository
public interface ProgramRepository extends JpaRepository<Program, UUID> {

    @Query("SELECT p FROM Program p WHERE p.restricted = false ORDER BY p.created DESC")
    Page<Program> findNotRestricted(Pageable pageable);
}
