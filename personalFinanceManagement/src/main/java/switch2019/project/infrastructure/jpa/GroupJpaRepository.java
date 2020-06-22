
package switch2019.project.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2019.project.dataModel.entities.GroupJpa;

import java.util.List;
import java.util.Optional;

public interface GroupJpaRepository extends JpaRepository<GroupJpa, String> {

    List <GroupJpa> findAll();
    Optional<GroupJpa> findById(String id);
}
