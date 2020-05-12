package switch2019.project.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import switch2019.project.dataModel.entities.CategoryJpa;

import java.util.List;

@Repository
public interface CategoryJpaRepository extends JpaRepository<CategoryJpa, Long> {
    List<CategoryJpa> findAll();
}


