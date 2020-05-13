package switch2019.project.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2019.project.dataModel.entities.CategoryIdJpa;
import switch2019.project.dataModel.entities.CategoryJpa;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.shared.CategoryID;

import java.util.List;
import java.util.Optional;

public interface CategoryJpaRepository extends JpaRepository<CategoryJpa, String> {
    List<CategoryJpa> findAll();
    Optional<CategoryJpa> findById(String id);
    Optional<CategoryJpa>  findByCategoryIdJpa (CategoryIdJpa categoryIdjpa);
    List<CategoryJpa> findAllByCategoryIdJpa_Owner (String ownerID);
}


