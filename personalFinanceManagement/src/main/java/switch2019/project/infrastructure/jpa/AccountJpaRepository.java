package switch2019.project.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import switch2019.project.dataModel.entities.AccountIDJpa;
import switch2019.project.dataModel.entities.AccountJpa;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountJpaRepository extends JpaRepository<AccountJpa, String> {
    List<AccountJpa> findAll();
    List<AccountJpa> findAllByAccountIDJpa_Owner(String accountIDJpa_owner);
    Optional<AccountJpa> findByAccountIDJpa(AccountIDJpa accountIDJpa);
}