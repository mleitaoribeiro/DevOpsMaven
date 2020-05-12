package switch2019.project.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2019.project.dataModel.entities.PersonJpa;
import switch2019.project.domain.domainEntities.shared.PersonID;

import java.util.List;
import java.util.Optional;

public interface PersonJpaRepository extends JpaRepository<PersonJpa, PersonID> {
    List<PersonJpa> findAll();
    Optional<PersonJpa> findById(PersonID id);
}
