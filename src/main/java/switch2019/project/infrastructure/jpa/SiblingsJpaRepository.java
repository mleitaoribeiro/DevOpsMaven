package switch2019.project.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2019.project.dataModel.entities.SiblingsJpa;

import java.util.Optional;

public interface SiblingsJpaRepository extends JpaRepository<SiblingsJpa, String> {
    Optional<SiblingsJpa> findByOwnerEmailAndAndSiblingEmail(String ownerEmail, String siblingEmail);
}
