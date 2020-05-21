package switch2019.project.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2019.project.dataModel.entities.TransactionJpa;

import java.util.List;
import java.util.Optional;

public interface TransactionJpaRepository extends JpaRepository<TransactionJpa, Long> {
    List<TransactionJpa> findAll();
    Optional<TransactionJpa> findByTransactionIDJpa_LedgerIdAndTransactionIDJpa_Id(String ledgerId, Long id);
    List<TransactionJpa> findAllByTransactionIDJpa_LedgerId(String ledgerId);

}
