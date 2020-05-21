package switch2019.project.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2019.project.dataModel.entities.LedgerJpa;

import java.util.List;
import java.util.Optional;

public interface LedgerJpaRepository  extends JpaRepository <LedgerJpa, String>{

    List<LedgerJpa> findAll();
    Optional<LedgerJpa> findByLedgerIdJpa_Owner(String owner);

}
