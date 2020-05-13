package switch2019.project.infrastructure.dataBaseRepositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.repositories.PersonRepository;

import static org.junit.jupiter.api.Assertions.*;

class PersonDbRepositoryTest {


    @Test
    void createPerson() {
     //   Person person = new Person("Marta", , "irthPlace", "homeAddress", "email");

    }

    @Test
    void testCreatePerson() {
    }

    @Test
    void getByID() {
    }

    @Test
    void findPersonByEmail() {
    }

    @Test
    void isPersonEmailOnRepository() {
    }

    @Test
    void isIDOnRepository() {
    }

    @Test
    void repositorySize() {
    }
}