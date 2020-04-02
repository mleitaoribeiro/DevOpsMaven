package switch2019.project.dataLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.infrastructure.repositories.AccountRepository;
import switch2019.project.infrastructure.repositories.CategoryRepository;
import switch2019.project.infrastructure.repositories.GroupsRepository;
import switch2019.project.infrastructure.repositories.PersonRepository;

@Component
public class Bootstrapper {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    GroupsRepository groupRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CategoryRepository categoriesRepository;

    public void bootstrapping() {

        /* Add people to the Repository */

        //Persons without Father & Mother

        Person alexandreOliveira = personRepository.createPerson (
                "Alexandre Oliveira",
                new DateAndTime(1993, 9, 1),
                new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4050-262"),
                new Email("1191743@isep.ipp.pt"));

        Person dianaDias = personRepository.createPerson (
                "Diana Dias",
                new DateAndTime(1993, 9, 1),
                new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4050-262"),
                new Email("1191755@isep.ipp.pt"));

        Person elsaAlmeida = personRepository.createPerson (
                "Elsa Almeida",
                new DateAndTime(1993, 9, 1),
                new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4050-262"),
                new Email("1191762@isep.ipp.pt"));

        Person gabrielMoco = personRepository.createPerson (
                "Gabriel Moço",
                new DateAndTime(1993, 9, 1),
                new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4050-262"),
                new Email("1191765@isep.ipp.pt"));

        Person martaPinheiro = personRepository.createPerson (
                "Marta Pinheiro",
                new DateAndTime(1993, 9, 1),
                new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4050-262"),
                new Email("1191778@isep.ipp.pt"));

        Person martaRibeiro = personRepository.createPerson (
                "Marta Ribeiro",
                new DateAndTime(1993, 9, 1),
                new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4050-262"),
                new Email("1191779@isep.ipp.pt"));

        Person raquelSantos = personRepository.createPerson (
                "Raquel Santos",
                new DateAndTime(1993, 9, 1),
                new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4050-262"),
                new Email("1191782@isep.ipp.pt"));


        //Smith Family

        //Beth Smith Parents | Grandparents of Morty Sr. & Summer
        Person rickSanchez  = personRepository.createPerson (
                "Richard Sanchez",
                new DateAndTime(1950, 9, 1),
                new Address("Seattle"),
                new Address("Smiths house", "Seattle", "4520-266"),
                new Email("rick@gmail.com"));

        Person dianeSanchez  = personRepository.createPerson (
                "Diane Sanchez",
                new DateAndTime(1955, 1, 10),
                new Address("Seattle"),
                new Address("First Avenue South", "Seattle", "4520-266"),
                new Email("disanchez@gmail.com"));

        //Jerry Smith Parents | Grandparents of Morty Sr. & Summer

        Person leonardSmith  = personRepository.createPerson (
                "Leonard Smith",
                new DateAndTime(1940, 10, 20),
                new Address("Seattle"),
                new Address("First Avenue Northwest", "Seattle", "4520-266"),
                new Email("leonard.smith@gmail.com"));

        Person joyceSmith  = personRepository.createPerson (
                "Joyce Smith",
                new DateAndTime(1945, 5, 20),
                new Address("Seattle"),
                new Address("First Avenue Northwest", "Seattle", "4520-266"),
                new Email("joyce.smith@gmail.com"));

        //Smith Actual Family
        Person jerrySmith  = personRepository.createPerson (
                "Jerry Smith",
                new DateAndTime(1967, 2, 3),
                new Address("Seattle"),
                new Address("Smiths house", "Seattle", "4520-266"),
                joyceSmith,
                leonardSmith,
                new Email("jerry.smith@gmail.com"));

        Person bethSmith  = personRepository.createPerson (
                "Beth Smith",
                new DateAndTime(1973, 6, 10),
                new Address("Seattle"),
                new Address("Smiths house", "Seattle", "4520-266"),
                dianeSanchez,
                rickSanchez,
                new Email("beth.smith@gmail.com"));

        Person summerSmith = personRepository.createPerson(
                "Summer Smith",
                new DateAndTime(2000, 5, 18),
                new Address("Seattle"),
                new Address("Smiths house", "Seattle", "4520-266"),
                bethSmith,
                jerrySmith,
                new Email("summer@gmail.com"));

        Person mortySmith = personRepository.createPerson(
                "Mortimer Smith Sr.",
                new DateAndTime(2003, 10, 2),
                new Address("Seattle"),
                new Address("Smiths house", "Seattle", "4520-266"),
                bethSmith,
                jerrySmith,
                new Email("morty@gmail.com"));

        //Mortimer Jr. Mother - No Parents
        Person gwendolyn = personRepository.createPerson(
                "Gwendolyn",
                new DateAndTime(2005, 6, 11),
                new Address("Seattle"),
                new Address("Alki Avenue", "Seattle", "4520-233"),
                new Email("gwendolyn@gmail.com"));

        //Morty Smith Sr. & Gwendolyn son
        Person mortimerSmithJr = personRepository.createPerson(
                " Mortimer Smith Jr.",
                new DateAndTime(2010, 4, 3),
                new Address("Seattle"),
                new Address("Alki Avenue", "Seattle", "4520-233"),
                gwendolyn,
                mortySmith,
                new Email("mortimer.smith@hotmail.com"));

        //Simpson Family

        Person grandfatherAbrahamSimpson = personRepository.createPerson (
                "Abraham Simpson",
                new DateAndTime(1940, 5, 20),
                new Address("Springfield "),
                new Address("Springfield Retirement Castle", "Springfield", "4520-266"),
                new Email("abe_simpson@gmail.com"));

        Person grandmotherMonaSimpson  = personRepository.createPerson (
                "Mona Simpson",
                new DateAndTime(1943, 12, 13),
                new Address("Springfield "),
                new Address("742 Evergreen Terrace", "Springfield", "4520-266"),
                new Email("mona.simpson@gmail.com"));

        Person fatherHomerSimpson =  personRepository.createPerson (
                "Homer Jay Simpson",
                new DateAndTime(1956, 5, 12),
                new Address("Springfield"),
                new Address("742 Evergreen Terrace", "Springfield", "4520-233"),
                grandmotherMonaSimpson,
                grandfatherAbrahamSimpson,
                new Email("homer@hotmail.com"));

        Person motherMarjorieSimpson =  personRepository.createPerson(
                "Marjorie Bouvier Simpson",
                new DateAndTime(1956, 5, 12),
                new Address("Springfield"),
                new Address("742 Evergreen Terrace", "Springfield", "4520-233"),
                new Email("marge@hotmail.com"));

        Person bartolomewSimpson = personRepository.createPerson(
                "Bartolomew Jay Simpson",
                new DateAndTime(2000, 4, 3),
                new Address("Springfield"),
                new Address("742 Evergreen Terrace", "Springfield", "4520-233"),
                motherMarjorieSimpson,
                fatherHomerSimpson,
                new Email("bart.simpson@gmail.com"));

        Person elizabetSimpson = personRepository.createPerson(
                "Elizabeth Marie Bouvier Simpson",
                new DateAndTime(2002, 9, 10),
                new Address("Springfield"),
                new Address("742 Evergreen Terrace", "Springfield", "4520-233"),
                motherMarjorieSimpson,
                fatherHomerSimpson,
                new Email("liza.simpson@hotmail.com"));

        Person margaretSimpson = personRepository.createPerson(
                "Margaret Evelyn Simpson",
                new DateAndTime(2019, 2, 5),
                new Address("Springfield"),
                new Address("742 Evergreen Terrace", "Springfield", "4520-233"),
                motherMarjorieSimpson,
                fatherHomerSimpson,
                new Email("maggie.simpson@gmail.com"));

        //Cardoso Family

        Person fatherAntonioCardoso =  personRepository.createPerson (
                "António Cardoso",
                new DateAndTime(1967, 9, 9),
                new Address("Porto"),
                new Address("Rua de Requeixos", "Vizela", "4620-580"),
                new Email("antonio.cardoso@gmail.com"));

        Person motherMariaCardoso =  personRepository.createPerson(
                "Maria Cardoso",
                new DateAndTime(1964, 1, 19),
                new Address("Porto"),
                new Address("Rua de Requeixos", "Vizela", "4620-580"),
                new Email("maria.cardoso_1@gmail.com"));

        Person martaCardoso = personRepository.createPerson(
                "Marta Maria Cardoso",
                new DateAndTime(1995, 04, 12),
                new Address("Porto"),
                new Address("Rua de Requeixos", "Vizela", "4620-580"),
                motherMariaCardoso,
                fatherAntonioCardoso,
                new Email("1191780@isep.ipp.pt"));

        Person joaoCardoso = personRepository.createPerson(
                "João Cardoso",
                new DateAndTime(1993, 9, 1),
                new Address("Porto"),
                new Address("Rua de Requeixos", "Vizela", "4620-580"),
                motherMariaCardoso,
                fatherAntonioCardoso,
                new Email("1110120@isep.ipp.pt"));

        //Azevedo Family

        Person fatherRobertoAzevedo =  personRepository.createPerson (
                "Roberto Azevedo",
                new DateAndTime(1967, 1, 9),
                new Address("Lisboa"),
                new Address("Avenida Antonio Domingues dos Santos", "Senhora da Hora", "4460-237"),
                new Email("roberto@gmail.com"));

        Person motherMargaridaAzevedo =  personRepository.createPerson(
                "Maria Cardoso",
                new DateAndTime(1964, 12, 1),
                new Address("Guimarães"),
                new Address("Avenida Antonio Domingues dos Santos", "Senhora da Hora", "4460-237"),
                new Email("maria@gmail.com"));

        Person beatrizAzevedo = personRepository.createPerson(
                "Beatriz Azevedo",
                new DateAndTime(1995, 04, 12),
                new Address("Porto"),
                new Address("Avenida Antonio Domingues dos Santos", "Senhora da Hora", "4460-237"),
                motherMargaridaAzevedo,
                fatherRobertoAzevedo,
                new Email("beatriz.azevedo@gmail.com"));

        Person margaridaAzevedo = personRepository.createPerson(
                "Margarida Azevedo",
                new DateAndTime(1993, 9, 1),
                new Address("Porto"),
                new Address("Avenida Antonio Domingues dos Santos", "Senhora da Hora", "4460-237"),
                motherMargaridaAzevedo,
                fatherRobertoAzevedo,
                new Email("margarida_azevedo@gmail.com"));

        Person hugoAzevedo = personRepository.createPerson(
                "Hugo Azevedo",
                new DateAndTime(1993, 9, 1),
                new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4050-262"),
                motherMargaridaAzevedo,
                fatherRobertoAzevedo,
                new Email("hugo.azevedo@gmail.com"));


        /* Add groups to Repository & Members to the groups */

        //Group SWitCH - All members are Group Admin

        Group switchGroup = groupRepository.createGroup( new Description("SWitCH"),
                personRepository.findPersonByEmail(new Email("1110120@isep.ipp.pt")));

        switchGroup.addMember(alexandreOliveira);
        switchGroup.setAdmin(alexandreOliveira);

        switchGroup.addMember(dianaDias);
        switchGroup.setAdmin(dianaDias);

        switchGroup.addMember(elsaAlmeida);
        switchGroup.setAdmin(elsaAlmeida);

        switchGroup.addMember(gabrielMoco);
        switchGroup.setAdmin(gabrielMoco);

        switchGroup.addMember(martaCardoso);
        switchGroup.setAdmin(martaCardoso);

        switchGroup.addMember(martaPinheiro);
        switchGroup.setAdmin(martaPinheiro);

        switchGroup.addMember(martaRibeiro);
        switchGroup.setAdmin(martaRibeiro);

        switchGroup.addMember(raquelSantos);
        switchGroup.setAdmin(raquelSantos);

        //Group Friends - 1 Admin - Two members are family but the other member is not

        Group friendsGroup = groupRepository.createGroup( new Description("Friends"),
                personRepository.findPersonByEmail(new Email("hugo.azevedo@gmail.com")));
        friendsGroup.addMember(beatrizAzevedo);
        friendsGroup.addMember(joaoCardoso);

        //Group Split Expenses - 2 Admin

        Group splitExpensesGroup = groupRepository.createGroup( new Description("Split Expenses"),
                personRepository.findPersonByEmail(new Email("bart.simpson@gmail.com")));

        splitExpensesGroup.addMember(alexandreOliveira);
        splitExpensesGroup.setAdmin(alexandreOliveira);
        splitExpensesGroup.addMember(gabrielMoco);
        splitExpensesGroup.addMember(hugoAzevedo);

        //Group Rick and Morty - 1 Admin - 2 Members

        Group rickAndMortyGroup = groupRepository.createGroup( new Description("Rick and Morty"),
                personRepository.findPersonByEmail(new Email("rick@gmail.com")));

        rickAndMortyGroup.addMember(mortySmith);

        //Group morty - 1 Admin - 1 Member

        Group intergalacticGroup = groupRepository.createGroup( new Description("Intergalactic"),
                personRepository.findPersonByEmail(new Email("mortimer.smith@hotmail.com")));

        //NOT FAMILY - Smith Family - With Grandpa Rick (admin)

        Group smithFamilyGroup = groupRepository.createGroup( new Description("Smith Family"),
                personRepository.findPersonByEmail(new Email("rick@gmail.com")));

        smithFamilyGroup.addMember(jerrySmith);
        smithFamilyGroup.addMember(bethSmith);
        smithFamilyGroup.addMember(summerSmith);
        smithFamilyGroup.addMember(mortySmith);

        //Family group - Family Simpson - 2 Admin
        Group familySimpsonGroup = groupRepository.createGroup( new Description("Family Simpson"),
                personRepository.findPersonByEmail(new Email("marge@hotmail.com")));

        familySimpsonGroup.addMember(fatherHomerSimpson);
        familySimpsonGroup.setAdmin(fatherHomerSimpson);
        familySimpsonGroup.addMember(bartolomewSimpson);
        familySimpsonGroup.addMember(elizabetSimpson);
        familySimpsonGroup.addMember(margaretSimpson);

        //Family group - Family Cardoso - All members are Group Admin
        Group familyCardosoGroup = groupRepository.createGroup( new Description("Family Cardoso"),
                personRepository.findPersonByEmail(new Email("1110120@isep.ipp.pt")));

        familyCardosoGroup.addMember(martaCardoso);
        familyCardosoGroup.setAdmin(martaCardoso);

        familyCardosoGroup.addMember(fatherAntonioCardoso);
        familyCardosoGroup.setAdmin(fatherAntonioCardoso);

        familyCardosoGroup.addMember(motherMariaCardoso);
        familyCardosoGroup.setAdmin(motherMariaCardoso);

        //Family group - Family Azevedo - 1 Admin (Group creator)
        Group familyAzevedoGroup = groupRepository.createGroup( new Description("Familia Azevedo"),
                personRepository.findPersonByEmail(new Email("beatriz.azevedo@gmail.com")));

        familyAzevedoGroup.addMember(fatherRobertoAzevedo);
        familyAzevedoGroup.addMember(motherMargaridaAzevedo);
        familyAzevedoGroup.addMember(margaridaAzevedo);
        familyAzevedoGroup.addMember(hugoAzevedo);
    }
}
