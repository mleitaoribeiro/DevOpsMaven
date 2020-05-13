package switch2019.project.infrastructure.dataLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.*;

import switch2019.project.infrastructure.GroupDbRepository;
import switch2019.project.infrastructure.dataBaseRepositories.PersonDbRepository;

@Component
public class DataBaseLoader {

    @Autowired
    private PersonDbRepository personRepository;

    @Autowired
    private GroupDbRepository groupRepository;

  /*  @Autowired
    AccountRepository accountRepository;

    @Autowired
    CategoryRepository categoryRepository;*/

    public void dataBaseLoader() {

        /**
         * SWITCH GROUP 2 SET UP
         */

        Person alexandreOliveira = personRepository.createPerson("Alexandre Oliveira",
                new DateAndTime(1996, 03, 04), new Address("Porto"),
                new Address("Travessa 1º de Maio", "Maia", "4475-259"), new Email("alex@isep.ipp.pt"));

        Person dianaDias = personRepository.createPerson("Diana Dias", new DateAndTime(1994, 01, 1),
                new Address("Porto"), new Address(" Rua da Estrada de Tregosa", " Freixieiro", "4905-157"),
                new Email("diana@isep.ipp.pt"));

        Person elsaAlmeida = personRepository.createPerson("Elsa Almeida", new DateAndTime(1990, 02, 06),
                new Address("Lyon"), new Address("Rua Alegria", "Santa Maria da Feira", "4520-105"),
                new Email("elsa@isep.ipp.pt"));

        Person gabrielMoco = personRepository.createPerson("Gabriel Moço", new DateAndTime(1993, 04, 17), new Address("Porto"),
                new Address("Avenida República", "Gaia", "4400-004"), new Email("gabriel@isep.ipp.pt"));

        Person martaPinheiro = personRepository.createPerson("Marta Pinheiro", new DateAndTime(1993, 9, 1),
                new Address("Porto"), new Address("Rua das Flores", "Porto", "4050-262"),
                new Email("marta.p@isep.ipp.pt"));

        Person martaRibeiro = personRepository.createPerson("Marta Ribeiro", new DateAndTime(1993, 9, 1),
                new Address("Porto"), new Address("Rua das Flores", "Porto", "4050-262"),
                new Email("marta.r@isep.ipp.pt"));

        Person raquelSantos = personRepository.createPerson("Raquel Santos", new DateAndTime(1993, 9, 1),
                new Address("Porto"), new Address("Rua das Flores", "Porto", "4050-262"),
                new Email("raquel.r@isep.ipp.pt"));

        Person martaCardoso = personRepository.createPerson("Marta Maria Cardoso", new DateAndTime(1995, 04, 12),
                new Address("Porto"), new Address("Rua de Requeixos", "Vizela", "4620-580"),
                new Email("marta.c0@isep.ipp.pt"));

        Person joaoCardoso = personRepository.createPerson("João Cardoso", new DateAndTime(1993, 9, 1),
                new Address("Porto"), new Address("Rua de Requeixos", "Vizela", "4620-580"),
                new Email("joao@isep.ipp.pt"));
/*
        //Group Creation
        Group switchG2 = groupRepository.createGroup(new Description("SWITCH G2"),
                personRepository.findPersonByEmail(new Email("joao@isep.ipp.pt")).getID());

        switchG2.addMember(alexandreOliveira.getID());
        switchG2.setAdmin(alexandreOliveira.getID());
        switchG2.addMember(dianaDias.getID());
        switchG2.addMember(elsaAlmeida.getID());
        switchG2.addMember(gabrielMoco.getID());
        switchG2.addMember(martaCardoso.getID());
        switchG2.addMember(martaPinheiro.getID());
        switchG2.addMember(martaRibeiro.getID());
        switchG2.addMember(raquelSantos.getID());
*/

      /*  //Categories
        //Group
        categoryRepository.createCategory(new Denomination("GAMES"), new GroupID(new Description("SWITCH G2")));
        categoryRepository.createCategory(new Denomination("ISEP AE"), new GroupID(new Description("SWITCH G2")));
        //Individual
        categoryRepository.createCategory(new Denomination("WORK"),  new PersonID(new Email("diana@isep.ipp.pt")));
        categoryRepository.createCategory(new Denomination("GYM"),  new PersonID(new Email("marta.c@isep.ipp.pt")));

        //Accounts
        //Group Accounts
        accountRepository.createAccount(new Denomination("Account for Games"), new Description("Computer & board games"),
                new GroupID(new Description("SWITCH G2")));

        accountRepository.createAccount(new Denomination("Account for Rides"), new Description("Rides and Car expenses"),
                new GroupID(new Description("SWITCH G2")));

        //Individuals
        accountRepository.createAccount(new Denomination("CTT"), new Description("Daily Expenses"), new PersonID(new Email("raquel@isep.ipp.pt")));
        accountRepository.createAccount(new Denomination("Degiro"), new Description("Investing"), new PersonID(new Email("diana@isep.ipp.pt")));
*/

        /**
         * FAMILY GROUP SET UP
         */

        Person fatherAntonioCardoso = personRepository.createPerson("António Cardoso",
                new DateAndTime(1967, 9, 9), new Address("Porto"),
                new Address("Rua de Requeixos", "Vizela", "4620-580"),
                new Email("antonio.cardoso@gmail.com"));

        Person motherMariaCardoso = personRepository.createPerson("Maria Cardoso",
                new DateAndTime(1964, 1, 19), new Address("Porto"),
                new Address("Rua de Requeixos", "Vizela", "4620-580"),
                new Email("maria.cardoso_1@gmail.com"));
 /*       //Group Creation
        Group familyCardosoGroup = groupRepository.createGroup(new Description("CARDOSO'S FAMILY"),
                personRepository.findPersonByEmail(new Email("antonio.cardoso@gmail.com")).getID());

        familyCardosoGroup.addMember(motherMariaCardoso.getID());
        familyCardosoGroup.setAdmin(motherMariaCardoso.getID());

        //Siblings
        familyCardosoGroup.addMember(martaCardoso.getID());
        martaCardoso.setMother(motherMariaCardoso.getID());
        martaCardoso.setFather(fatherAntonioCardoso.getID());
        familyCardosoGroup.addMember(joaoCardoso.getID());
        joaoCardoso.setMother(motherMariaCardoso.getID());
        joaoCardoso.setFather(fatherAntonioCardoso.getID());
        martaCardoso.addSibling(joaoCardoso);*/
/*

        //Categories
        categoryRepository.createCategory(new Denomination("Online"), new GroupID(new Description("Family Cardoso")));
        categoryRepository.createCategory(new Denomination("Home"), new GroupID(new Description("Family Cardoso")));

        //Accounts
        //Group Account
        accountRepository.createAccount(new Denomination("Revolut"), new Description("Online Expenses"),
                new GroupID(new Description("Family Cardoso")));

        //Individual Account
        accountRepository.createAccount(new Denomination("Mbway"), new Description("Rides"),
                new PersonID(new Email("1191780@isep.ipp.pt")));
*/


        /**
         * Rick and Morty Group Setup
         */

        Person mortySmith = personRepository.createPerson("Mortimer Smith Sr.",
                new DateAndTime(2003, 10, 2), new Address("Seattle"),
                new Address("Smiths house", "Seattle", "4520-266"), new Email("morty@gmail.com"));

        Person rickSanchez = personRepository.createPerson("Richard Sanchez",
                new DateAndTime(1950, 9, 1), new Address("Seattle"),
                new Address("Smiths house", "Seattle", "4520-266"), new Email("rick@gmail.com"));

     /*   //Group Creation
        Group rickMortyGroup = groupRepository.createGroup(new Description("Rick & Morty"),
                personRepository.findPersonByEmail(new Email("rick@gmail.com")).getID());

        rickMortyGroup.addMember(mortySmith.getID());*/
/*
        //Categories
        categoryRepository.createCategory(new Denomination("Adventures"), new GroupID(new Description("Rick & Morty")));
        categoryRepository.createCategory(new Denomination("Laboratory"), new GroupID(new Description("Rick & Morty")));


        //Accounts
        accountRepository.createAccount(new Denomination("Money for Morty"), new Description("Money to compensate morty"),
                new GroupID(new Description("Rick & Morty")));

        accountRepository.createAccount(new Denomination("Bank Loan"), new Description("Important for adventures"),
                new GroupID(new Description("Rick & Morty")));*/

        /**
         * Pokemon Team Rocket Group Setup
         */

        Person jessie = personRepository.createPerson("Jessie",
                new DateAndTime(2003, 10, 2), new Address("Kanto"),
                new Address("Pokemon Street", "Johto", "4000-000"), new Email("jesie@pokemon.com"));

        Person james = personRepository.createPerson("James",
                new DateAndTime(1950, 9, 1), new Address("Kanto"),
                new Address("Pokemon Street", "Johto", "4000-000"), new Email("james@pokemon.com"));

      /*  //Group Creation
        Group teamRocket = groupRepository.createGroup(new Description("TEAM ROCKET"),
                personRepository.findPersonByEmail(new Email("james@pokemon.com")).getID());

        teamRocket.addMember(jessie.getID());
        teamRocket.setAdmin(jessie.getID());*/
/*

        //Categories
        categoryRepository.createCategory(new Denomination("MEOWTH FOOD"), new GroupID(new Description("TEAM ROCKET")));
        categoryRepository.createCategory(new Denomination("TRAVELS"), new GroupID(new Description("TEAM ROCKET")));

        //Accounts
        accountRepository.createAccount(new Denomination("Meowh Savings"), new Description("Saving Account"),
                new GroupID(new Description("TEAM ROCKET")));

        accountRepository.createAccount(new Denomination("Meowth balloons Mechanic"), new Description("Fuel and Repair Costs"),
                new GroupID(new Description("TEAM ROCKET")));

*/


        /**
         * Bonnnie and Clyde Group Setup
         */

        Person bonnie = personRepository.createPerson("Bonnie Parker",
                new DateAndTime(1934, 03, 24), new Address("Rowena"),
                new Address("Bienville Parish Street", "Louisiana", "4000-000"), new Email("bonnie@hotmail.com"));

        Person clyde = personRepository.createPerson("Clyde Barrow",
                new DateAndTime(1950, 9, 1), new Address("Kansas"),
                new Address("Bienville Parish Street", "Louisiana", "4000-000"), new Email("clyde@hotmail.com"));

    /*    //Group Creation
        Group bonnieAndClyde = groupRepository.createGroup(new Description("Bonnie and Clyde"),
                personRepository.findPersonByEmail(new Email("clyde@hotmail.com")).getID());

        bonnieAndClyde.addMember(bonnie.getID());
        bonnieAndClyde.setAdmin(bonnie.getID());*/
/*
        //Categories
        categoryRepository.createCategory(new Denomination("SMALL ROBBERIES"), new GroupID(new Description("Bonnie and Clyde")));
        categoryRepository.createCategory(new Denomination("CAR JACKING"), new GroupID(new Description("Bonnie and Clyde")));


        //Accounts
        accountRepository.createAccount(new Denomination("Texas Gas station"), new Description("Mugging Account"),
                new GroupID(new Description("Bonnie and Clyde")));

        accountRepository.createAccount(new Denomination("Savings of Bank Robbery"), new Description("Special Account"),
                new GroupID(new Description("Bonnie and Clyde")));*/

    }
}
