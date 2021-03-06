package switch2019.project.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2019.project.dataModel.entities.AdminsJpa;
import switch2019.project.domain.domainEntities.shared.GroupID;

import java.util.List;

public interface AdminsJpaRepository extends JpaRepository<AdminsJpa, GroupID> {


    List<AdminsJpa> findAll();

    List<AdminsJpa> findAllById_GroupID_Id (String id);
}
