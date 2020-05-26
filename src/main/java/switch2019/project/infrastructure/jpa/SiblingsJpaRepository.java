package switch2019.project.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2019.project.dataModel.entities.SiblingsJpa;

import java.util.List;
import java.util.Optional;

public interface SiblingsJpaRepository extends JpaRepository<SiblingsJpa, String> {
    Optional<SiblingsJpa> findById_OwnerEmailAndId_SiblingEmail(String ownerEmail, String siblingEmail);
    List<SiblingsJpa> findAllById_OwnerEmail_Email(String ownerEmail);
}
