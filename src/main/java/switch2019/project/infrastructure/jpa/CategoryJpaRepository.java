package switch2019.project.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import switch2019.project.dataModel.entities.CategoryJpa;

import java.util.List;
import java.util.Optional;

public interface CategoryJpaRepository extends JpaRepository<CategoryJpa, String> {
    List<CategoryJpa> findAll();
    Optional<CategoryJpa> findById(String id);
}


