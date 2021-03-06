package switch2019.project.infrastructure.dataBaseRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import switch2019.project.dataModel.dataAssemblers.PersonDomainDataAssembler;
import switch2019.project.dataModel.entities.AddressJpa;
import switch2019.project.dataModel.entities.PersonJpa;
import switch2019.project.dataModel.entities.SiblingsJpa;
import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.domain.repositories.PersonRepository;
import switch2019.project.infrastructure.jpa.AddressJpaRepository;
import switch2019.project.infrastructure.jpa.PersonJpaRepository;
import switch2019.project.infrastructure.jpa.SiblingsJpaRepository;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Component
@Primary

public class PersonDbRepository implements PersonRepository {

    @Autowired
    PersonJpaRepository personJpaRepository;

    @Autowired
    SiblingsJpaRepository siblingsJpaRepository;

    @Autowired
    AddressJpaRepository addressJpaRepository;

    //String literal - Exceptions
    private static final String PERSON_NOT_FOUND = "No person found with that email.";

    public Person createPerson(String name, DateAndTime birthDate, Address birthPlace, Address homeAddress, Email email) {

        Optional<AddressJpa> addressJpa = addressJpaRepository.findByCityAndStreetAndPostalCode
                (homeAddress.getCity(), homeAddress.getStreet(), homeAddress.getPostalCode());

        Person person = new Person(name, birthDate, birthPlace, homeAddress, email);
        PersonJpa personJpa;

        if(addressJpa.isPresent())
            personJpa = personJpaRepository.save(PersonDomainDataAssembler.toData(person, addressJpa.get()));

        else personJpa = personJpaRepository.save(PersonDomainDataAssembler.toData(person));

        return PersonDomainDataAssembler.toDomain(personJpa);
    }

    public Person createPerson(String name, DateAndTime birthDate, Address birthPlace, Address homeAddress,
                               PersonID mother, PersonID father, Email email) {

        Optional<AddressJpa> addressJpa = addressJpaRepository.findByCityAndStreetAndPostalCode
                (homeAddress.getCity(), homeAddress.getStreet(), homeAddress.getPostalCode());

        Person person = new Person(name, birthDate, birthPlace, homeAddress, mother, father, email);
        PersonJpa personJpa;

        if(addressJpa.isPresent())
            personJpa = personJpaRepository.save(PersonDomainDataAssembler.toData(person, addressJpa.get()));

        else personJpa = personJpaRepository.save(PersonDomainDataAssembler.toData(person));

        return PersonDomainDataAssembler.toDomain(personJpa);
    }

    public Person getByID(ID personID) {
        Optional<PersonJpa> personJpa = personJpaRepository.findById(personID.toString());
        if(personJpa.isPresent()) {
            return getPerson(personJpa.get(), personID.toString());
        } else throw new ArgumentNotFoundException(PERSON_NOT_FOUND);
    }

    private Person getPerson(PersonJpa personJpa, String email) {
        List<SiblingsJpa> siblingsJpas = siblingsJpaRepository.findAllById_OwnerEmail_Email(email);
        HashSet<Person> siblings = new HashSet<>();

        for(SiblingsJpa siblingsJpa : siblingsJpas) {
            Optional <PersonJpa> personJpa1 = personJpaRepository.findById(siblingsJpa.getSiblingEmail());
            personJpa1.ifPresent(jpa -> siblings.add(PersonDomainDataAssembler.
                    toDomain(jpa)));
        }
        return PersonDomainDataAssembler.toDomain(personJpa, siblings);
    }

    public Person findPersonByEmail(Email personEmail) {
        Optional<PersonJpa> personJpa = personJpaRepository.findByEmail(personEmail.toString());
        if(personJpa.isPresent()) {
            return getPerson(personJpa.get(), personEmail.toString());
        } throw new ArgumentNotFoundException(PERSON_NOT_FOUND);
    }

    public boolean isPersonEmailOnRepository(Email personEmail) {
        Optional<PersonJpa> personJpa = personJpaRepository.findByEmail(personEmail.toString());
        return personJpa.isPresent();
    }

    @Override
    public boolean isIDOnRepository (ID personID) {
        Optional<PersonJpa> personJpa = personJpaRepository.findById(personID.toString());
        return personJpa.isPresent();
    }

    public long repositorySize () {
        return personJpaRepository.count();
    }

    @Transactional
    public boolean addSibling(Person person, String siblingID) {
        List<SiblingsJpa> siblingsJpas = siblingsJpaRepository.findAllById_OwnerEmail_Email(person.getID().getEmail());

        // owner
        PersonJpa personjpa = PersonDomainDataAssembler.toData(person);
        SiblingsJpa siblingsJpa = new SiblingsJpa(personjpa, siblingID);

        // sibling
        Optional<PersonJpa> personJpa2 = personJpaRepository.findById(siblingID);
        SiblingsJpa siblingsJpa2 = new SiblingsJpa(personjpa, siblingID);

        if (!siblingsJpas.contains(siblingsJpa) && personJpa2.isPresent()) {
            // add sibling to owners siblings list
            siblingsJpaRepository.save(siblingsJpa);
            personjpa.addSibling(siblingID);

            // add owner to sibling siblings list
            siblingsJpaRepository.save(siblingsJpa2);
            personJpa2.get().addSibling(person.getID().toString());

            return true;
        }
        return false;
    }
}
