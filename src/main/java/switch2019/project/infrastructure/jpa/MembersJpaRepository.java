package switch2019.project.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2019.project.dataModel.entities.MembersJpa;
import switch2019.project.domain.domainEntities.shared.GroupID;

import java.util.List;
import java.util.Optional;

public interface MembersJpaRepository extends JpaRepository<MembersJpa, GroupID> {

    Optional<MembersJpa> findById(GroupID id);

    List<MembersJpa> findAll();

    List<MembersJpa> findAllById_GroupID (GroupID id);
}
