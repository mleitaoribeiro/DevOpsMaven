package switch2019.project.springBoot.unit;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import switch2019.project.AbstractTest;
import switch2019.project.DTO.SerializationDTO.AccountDTO;
import switch2019.project.controllerLayer.controllersCli.US007CreateGroupAccountController;
import switch2019.project.infrastructure.repositories.AccountRepository;
import switch2019.project.infrastructure.repositories.GroupsRepository;
import switch2019.project.infrastructure.repositories.PersonRepository;
import switch2019.project.applicationLayer.US007CreateGroupAccountService;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class US007CreateGroupAccountControllerRestUnitTest extends AbstractTest {

    @Autowired
    PersonRepository personRepo;
    @Autowired
    GroupsRepository groupsRepo;
    @Autowired
    AccountRepository accountRepo;
    @Autowired
    US007CreateGroupAccountService service;

    private US007CreateGroupAccountController controller;

    @Override
    @BeforeEach
    public void setUP(){
        super.setUP();
    }

    //testes Marta - ainda nao acabados

    /**
     * US007
     * Test If group Account is created
     */
    @Test
    @DisplayName("Test If group Account was created - Main Scenario - Happy Case")
    void testIfGroupAccountWasCreatedHappyCase() {

        //Arrange
        controller = new US007CreateGroupAccountController(service);

        String personEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Familia";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";

        AccountDTO accountExpectedDTO = new AccountDTO(groupDescription, accountDenomination, accountDescription);

        //Act
        //AccountDTO accountCreatedDTO = controller.createGroupAccount(personEmail, groupDescription,
                //accountDenomination, accountDescription);

        //Assert
        //assertEquals(accountExpectedDTO, accountCreatedDTO);
    }

    @Test
    @DisplayName("Test If group Account was created - Person is Member but not Admin - Number of accounts has not increased")
    void testIfGroupAccountWasCreatedNotAdminNumberOfAccounts() {

        //Arrange
        String personEmail = "joao.cardoso_12@hotmail.com";
        String groupDescription = "Friends";
        String accountDenomination = "Online";
        String accountDescription = "Online Shopping";


        int numberOfExpectedAccountsInTheRepository = 0;

        //Act
        try {
            controller.createGroupAccount(personEmail, groupDescription,
                    accountDenomination, accountDescription);
        }
        catch (IllegalArgumentException invalid) {

            int realNumberOfAccountsInTheRepository = accountRepo.repositorySize();

            //Assert
            /*Assertions.assertAll(
                    () -> assertEquals("This person is not Admin of this group", invalid.getMessage()),
                    () -> assertEquals(numberOfExpectedAccountsInTheRepository, realNumberOfAccountsInTheRepository)
            );*/
        }
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //testes Diana

    //Já há uma issue para estes testes - #808

}