package switch2019.project.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2019.project.dataModel.entities.MembersJpa;

import java.util.List;
import java.util.Optional;

public interface MembersJpaRepository extends JpaRepository<MembersJpa, String> {

    Optional<MembersJpa> findById(String id);

    List<MembersJpa> findAll();

    List<MembersJpa> findAllById_GroupID_Id (String id);
}
