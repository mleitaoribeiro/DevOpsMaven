package switch2019.project.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2019.project.dataModel.entities.PersonJpa;

import java.util.List;
import java.util.Optional;

public interface PersonJpaRepository extends JpaRepository<PersonJpa, String> {
    List<PersonJpa> findAll();
    Optional<PersonJpa> findById(String id);
    Optional<PersonJpa> findByEmail(String id);
}
