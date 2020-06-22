package switch2019.project.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2019.project.dataModel.entities.AddressJpa;

import java.util.List;
import java.util.Optional;

public interface AddressJpaRepository extends JpaRepository<AddressJpa, Long> {
    AddressJpa findById(long id);
    Optional<AddressJpa> findByCityAndStreetAndPostalCode(String city, String street, String postalCode);
    List<AddressJpa> findAll();
}
