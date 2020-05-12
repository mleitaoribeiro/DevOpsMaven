package switch2019.project.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2019.project.dataModel.entities.AddressJpa;
import switch2019.project.domain.domainEntities.person.Address;


import java.util.List;

public interface AddressJpaRepository extends JpaRepository<AddressJpa, Long> {
    AddressJpa findById(long id);
    List<AddressJpa> findAll();
}
