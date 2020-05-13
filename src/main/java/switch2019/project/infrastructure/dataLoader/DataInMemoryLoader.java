package switch2019.project.infrastructure.dataLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.infrastructure.inMemoryRepositories.AccountInMemoryRepository;
import switch2019.project.infrastructure.inMemoryRepositories.CategoryInMemoryRepository;
import switch2019.project.infrastructure.inMemoryRepositories.GroupsInMemoryRepository;
import switch2019.project.infrastructure.inMemoryRepositories.PersonInMemoryRepository;

@Component
public class DataInMemoryLoader {

    @Autowired
    PersonInMemoryRepository personRepository;
    @Autowired
    GroupsInMemoryRepository groupRepository;
    @Autowired
    AccountInMemoryRepository accountRepository;
    @Autowired
    CategoryInMemoryRepository categoriesRepository;

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
                joyceSmith.getID(),
                leonardSmith.getID(),
                new Email("jerry.smith@gmail.com"));

        Person bethSmith  = personRepository.createPerson (
                "Beth Smith",
                new DateAndTime(1973, 6, 10),
                new Address("Seattle"),
                new Address("Smiths house", "Seattle", "4520-266"),
                dianeSanchez.getID(),
                rickSanchez.getID(),
                new Email("beth.smith@gmail.com"));

        Person summerSmith = personRepository.createPerson(
                "Summer Smith",
                new DateAndTime(2000, 5, 18),
                new Address("Seattle"),
                new Address("Smiths house", "Seattle", "4520-266"),
                bethSmith.getID(),
                jerrySmith.getID(),
                new Email("summer@gmail.com"));

        Person mortySmith = personRepository.createPerson(
                "Mortimer Smith Sr.",
                new DateAndTime(2003, 10, 2),
                new Address("Seattle"),
                new Address("Smiths house", "Seattle", "4520-266"),
                bethSmith.getID(),
                jerrySmith.getID(),
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
                gwendolyn.getID(),
                mortySmith.getID(),
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
                grandmotherMonaSimpson.getID(),
                grandfatherAbrahamSimpson.getID(),
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
                motherMarjorieSimpson.getID(),
                fatherHomerSimpson.getID(),
                new Email("bart.simpson@gmail.com"));

        Person elizabetSimpson = personRepository.createPerson(
                "Elizabeth Marie Bouvier Simpson",
                new DateAndTime(2002, 9, 10),
                new Address("Springfield"),
                new Address("742 Evergreen Terrace", "Springfield", "4520-233"),
                motherMarjorieSimpson.getID(),
                fatherHomerSimpson.getID(),
                new Email("liza.simpson@hotmail.com"));

        Person margaretSimpson = personRepository.createPerson(
                "Margaret Evelyn Simpson",
                new DateAndTime(2019, 2, 5),
                new Address("Springfield"),
                new Address("742 Evergreen Terrace", "Springfield", "4520-233"),
                motherMarjorieSimpson.getID(),
                fatherHomerSimpson.getID(),
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
                motherMariaCardoso.getID(),
                fatherAntonioCardoso.getID(),
                new Email("1191780@isep.ipp.pt"));

        Person joaoCardoso = personRepository.createPerson(
                "João Cardoso",
                new DateAndTime(1993, 9, 1),
                new Address("Porto"),
                new Address("Rua de Requeixos", "Vizela", "4620-580"),
                motherMariaCardoso.getID(),
                fatherAntonioCardoso.getID(),
                new Email("1110120@isep.ipp.pt"));

        //Azevedo Family

        Person fatherRobertoAzevedo =  personRepository.createPerson (
                "Roberto Azevedo",
                new DateAndTime(1967, 1, 9),
                new Address("Lisboa"),
                new Address("Avenida Antonio Domingues dos Santos", "Senhora da Hora", "4460-237"),
                new Email("roberto@gmail.com"));

        Person motherMargaridaAzevedo =  personRepository.createPerson(
                "Margarida Azevedo",
                new DateAndTime(1964, 12, 1),
                new Address("Guimarães"),
                new Address("Avenida Antonio Domingues dos Santos", "Senhora da Hora", "4460-237"),
                new Email("maria@gmail.com"));

        Person beatrizAzevedo = personRepository.createPerson(
                "Beatriz Azevedo",
                new DateAndTime(1995, 04, 12),
                new Address("Porto"),
                new Address("Avenida Antonio Domingues dos Santos", "Senhora da Hora", "4460-237"),
                motherMargaridaAzevedo.getID(),
                fatherRobertoAzevedo.getID(),
                new Email("beatriz.azevedo@gmail.com"));

        Person margaridaAzevedo = personRepository.createPerson(
                "Margarida Azevedo",
                new DateAndTime(1993, 9, 1),
                new Address("Porto"),
                new Address("Avenida Antonio Domingues dos Santos", "Senhora da Hora", "4460-237"),
                motherMargaridaAzevedo.getID(),
                fatherRobertoAzevedo.getID(),
                new Email("margarida_azevedo@gmail.com"));

        Person hugoAzevedo = personRepository.createPerson(
                "Hugo Azevedo",
                new DateAndTime(1993, 9, 1),
                new Address("Porto"),
                new Address("Rua das Flores", "Porto", "4050-262"),
                motherMargaridaAzevedo.getID(),
                fatherRobertoAzevedo.getID(),
                new Email("hugo.azevedo@gmail.com"));


        /* Add groups to Repository & Members to the groups */

        //Group SWitCH - All members are Group Admin

        Group switchGroup = groupRepository.createGroup( new Description("SWitCH"),
                personRepository.findPersonByEmail(new Email("1110120@isep.ipp.pt")).getID());

        switchGroup.addMember(alexandreOliveira.getID());
        switchGroup.setAdmin(alexandreOliveira.getID());

        switchGroup.addMember(dianaDias.getID());
        switchGroup.setAdmin(dianaDias.getID());

        switchGroup.addMember(elsaAlmeida.getID());
        switchGroup.setAdmin(elsaAlmeida.getID());

        switchGroup.addMember(gabrielMoco.getID());
        switchGroup.setAdmin(gabrielMoco.getID());

        switchGroup.addMember(martaCardoso.getID());
        switchGroup.setAdmin(martaCardoso.getID());

        switchGroup.addMember(martaPinheiro.getID());
        switchGroup.setAdmin(martaPinheiro.getID());

        switchGroup.addMember(martaRibeiro.getID());
        switchGroup.setAdmin(martaRibeiro.getID());

        switchGroup.addMember(raquelSantos.getID());
        switchGroup.setAdmin(raquelSantos.getID());

        //Group Friends - 1 Admin - Two members are family but the other member is not

        Group friendsGroup = groupRepository.createGroup( new Description("Friends"),
                personRepository.findPersonByEmail(new Email("hugo.azevedo@gmail.com")).getID());
        friendsGroup.addMember(beatrizAzevedo.getID());
        friendsGroup.addMember(joaoCardoso.getID());

        //Group Split Expenses - 2 Admin

        Group splitExpensesGroup = groupRepository.createGroup( new Description("Split Expenses"),
                personRepository.findPersonByEmail(new Email("bart.simpson@gmail.com")).getID());

        splitExpensesGroup.addMember(alexandreOliveira.getID());
        splitExpensesGroup.setAdmin(alexandreOliveira.getID());
        splitExpensesGroup.addMember(gabrielMoco.getID());
        splitExpensesGroup.addMember(hugoAzevedo.getID());

        //Group Rick and Morty - 1 Admin - 2 Members

        Group rickAndMortyGroup = groupRepository.createGroup( new Description("Rick and Morty"),
                personRepository.findPersonByEmail(new Email("rick@gmail.com")).getID());

        rickAndMortyGroup.addMember(mortySmith.getID());

        //Group morty - 1 Admin - 1 Member

        Group intergalacticGroup = groupRepository.createGroup( new Description("Intergalactic"),
                personRepository.findPersonByEmail(new Email("mortimer.smith@hotmail.com")).getID());

        //NOT FAMILY - Smith Family - With Grandpa Rick (admin)

        Group smithFamilyGroup = groupRepository.createGroup( new Description("Smith Family"),
                personRepository.findPersonByEmail(new Email("rick@gmail.com")).getID());

        smithFamilyGroup.addMember(jerrySmith.getID());
        smithFamilyGroup.addMember(bethSmith.getID());
        smithFamilyGroup.addMember(summerSmith.getID());
        smithFamilyGroup.addMember(mortySmith.getID());

        //Family group - Family Simpson - 2 Admin
        Group familySimpsonGroup = groupRepository.createGroup( new Description("Family Simpson"),
                personRepository.findPersonByEmail(new Email("marge@hotmail.com")).getID());

        familySimpsonGroup.addMember(fatherHomerSimpson.getID());
        familySimpsonGroup.setAdmin(fatherHomerSimpson.getID());
        familySimpsonGroup.addMember(bartolomewSimpson.getID());
        familySimpsonGroup.addMember(elizabetSimpson.getID());
        familySimpsonGroup.addMember(margaretSimpson.getID());
        //Siblings
        bartolomewSimpson.addSibling(elizabetSimpson);
        bartolomewSimpson.addSibling(margaretSimpson);
        margaretSimpson.addSibling(elizabetSimpson);

        //Family group - Family Cardoso - All members are Group Admin
        Group familyCardosoGroup = groupRepository.createGroup( new Description("Family Cardoso"),
                personRepository.findPersonByEmail(new Email("1110120@isep.ipp.pt")).getID());

        familyCardosoGroup.addMember(martaCardoso.getID());
        familyCardosoGroup.setAdmin(martaCardoso.getID());

        familyCardosoGroup.addMember(fatherAntonioCardoso.getID());
        familyCardosoGroup.setAdmin(fatherAntonioCardoso.getID());

        familyCardosoGroup.addMember(motherMariaCardoso.getID());
        familyCardosoGroup.setAdmin(motherMariaCardoso.getID());
        //Siblings
        martaCardoso.addSibling(joaoCardoso);

        //Family group - Family Azevedo - 1 Admin (Group creator)
        Group familyAzevedoGroup = groupRepository.createGroup( new Description("Family Azevedo"),
                personRepository.findPersonByEmail(new Email("beatriz.azevedo@gmail.com")).getID());

        familyAzevedoGroup.addMember(fatherRobertoAzevedo.getID());
        familyAzevedoGroup.addMember(motherMargaridaAzevedo.getID());
        familyAzevedoGroup.addMember(margaridaAzevedo.getID());
        familyAzevedoGroup.addMember(hugoAzevedo.getID());
        //Siblings
        margaridaAzevedo.addSibling(hugoAzevedo);

        // Additional Set up for siblings Tests
        Person father = personRepository.createPerson("José", new DateAndTime(1995, 12, 13), new Address("Miragaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("father1@isep.ipp.pt"));
        Person father2 = personRepository.createPerson("Rafael", new DateAndTime(1991, 12, 13), new Address("Portimão"),
                new Address("Rua X", "Portimão", "4520-266"), new Email("father2@isep.ipp.pt"));
        Person mother = personRepository.createPerson("Maria", new DateAndTime(1995, 12, 13), new Address("Miragaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("mother1@isep.ipp.pt"));
        Person mother2 = personRepository.createPerson("Mariana", new DateAndTime(1987, 12, 13), new Address("Fafe"),
                new Address("Rua X", "Fafe", "4520-266"), new Email("mother2@isep.ipp.pt"));

        // Siblings - are in each other list of siblings
        father.addSibling(father2);

        // Siblings - same Father and Mother and in each other's list
        Person antonio = personRepository.createPerson("António", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), mother.getID(), father.getID(), new Email("antonio@isep.ipp.pt"));
        Person manuel = personRepository.createPerson("Manuel", new DateAndTime(1995, 12, 13), new Address("Matosinhos"),
                new Address("Rua X", "Porto", "4520-266"), mother.getID(), father.getID(), new Email("manuel@isep.ipp.pt"));
        antonio.addSibling(manuel);

        // Siblings (with antonio) - only same Mother
        personRepository.createPerson("Roberto", new DateAndTime(1995, 12, 13), new Address("Matosinhos"),
                new Address("Rua X", "Porto", "4520-266"), mother.getID(), father2.getID(), new Email("roberto@isep.ipp.pt"));

        // Siblings (with antonio) - only same Father
        personRepository.createPerson("Amália", new DateAndTime(1995, 12, 13), new Address("Penacova"),
                new Address("Rua X", "Porto", "4520-266"), mother2.getID(), father.getID(), new Email("amalia@isep.ipp.pt"));


        /* Add categories to the groups */

        //Group SWitCH - Added some categories

        categoriesRepository.createCategory(new Denomination("GYM"), switchGroup.getID());
        categoriesRepository.createCategory(new Denomination("ISEP"), switchGroup.getID());
        categoriesRepository.createCategory(new Denomination("ONLINE"), smithFamilyGroup.getID());

        /*Add Accounts to Owner ID*/

        //Person Accounts
        accountRepository.createAccount(new Denomination("Homer Snacks"),
                new Description("Money spent on snacks for homer"),
                new PersonID(new Email("marge@hotmail.com")));

        //Account of Group - Family Cardoso
        accountRepository.createAccount(new Denomination("Revolut"),
                new Description("Online Expenses"),
                new GroupID(new Description("Family Cardoso")));
        // Account of Person - Marta Cardoso
        accountRepository.createAccount(new Denomination("Mbway"),
                new Description("Rides"), new PersonID(new Email("1191780@isep.ipp.pt")));

        // Account of Person - Raquel Santos
        accountRepository.createAccount(new Denomination("Mbway"),
                new Description("Friends"), new PersonID(new Email("1191782@isep.ipp.pt")));
        accountRepository.createAccount(new Denomination("CTT"),
                new Description("Work"), new PersonID(new Email("1191782@isep.ipp.pt")));
        accountRepository.createAccount(new Denomination("Home"),
                new Description("Home Expenses"), new PersonID(new Email("1191782@isep.ipp.pt")));

        // Accounts for Group - rickAndMortyGroup:
        accountRepository.createAccount(new Denomination("Money for Morty"),
                new Description("Money to compensate morty"),
                new GroupID(new Description("Rick and Morty")));

        accountRepository.createAccount(new Denomination("Fuel"),
                new Description("Ship fuel station"),
                new GroupID(new Description("Rick and Morty")));

        accountRepository.createAccount(new Denomination("Alcohol"),
                new Description("Important for adventures"),
                new GroupID(new Description("Rick and Morty")));

        accountRepository.createAccount(new Denomination("School"),
                new Description("Materials for school"),
                new GroupID(new Description("Switch")));

        accountRepository.createAccount(new Denomination("Parties"),
                new Description("Money for partying"),
                new GroupID(new Description("Switch")));

        accountRepository.createAccount(new Denomination("Pets"),
                new Description("Money for pet's expenses"),
                new GroupID(new Description("Switch")));

        accountRepository.createAccount(new Denomination("Games"),
                new Description("Computer and board games"),
                new GroupID(new Description("Switch")));

        accountRepository.createAccount(new Denomination("House"),
                new Description("Food and cleaning produts"),
                new GroupID(new Description("Switch")));

    }
}
