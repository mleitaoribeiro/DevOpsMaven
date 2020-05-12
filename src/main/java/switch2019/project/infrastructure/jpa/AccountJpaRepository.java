package switch2019.project.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import switch2019.project.dataModel.entities.AccountJpa;
import switch2019.project.domain.domainEntities.shared.AccountID;

import java.util.List;

@Repository
public interface AccountJpaRepository extends JpaRepository<AccountJpa, AccountID> {
    List<AccountJpa> findAll();
}