package switch2019.project.springBoot.unit.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2019.project.DTO.DeserializationDTO.CreatePersonAccountInfoDTO;
import switch2019.project.DTO.SerializationDTO.AccountDTO;
import switch2019.project.DTO.ServiceDTO.CreatePersonAccountDTO;
import switch2019.project.applicationLayer.US006CreatePersonAccountService;
import switch2019.project.controllerLayer.controllersCli.US006CreatePersonAccountController;
import switch2019.project.controllerLayer.controllersRest.US006CreatePersonAccountControllerRest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)

public class US006CreatePersonAccountControllerRestUnitTest {

    @Mock @Autowired private US006CreatePersonAccountService service;
    @Autowired private US006CreatePersonAccountControllerRest controller;


    //ISSUE 813

    @DisplayName("Test If User Account is created - Main Scenario")
    @Test
    void test1() {
        //test here
    }

    @DisplayName("Test If User Account was created - Account already exists")
    @Test
    void test2() {
        //test here
    }

    @DisplayName("Test If User Account was created - Person doesn't exist on Repository")
    @Test
    void test3() {
        //test here
    }

    @DisplayName("Test If User Account was created - email invalid - null")
    @Test
    void test4() {
        //test here
    }

    @Test
    @DisplayName("Test If User Account was created  - email invalid - empty")
    void testIfUserAccountWasCreated_EmailEmpty() {

        //Arrange
        CreatePersonAccountInfoDTO createPersonAccountInfoDTO = new CreatePersonAccountInfoDTO();
        createPersonAccountInfoDTO.setPersonEmail("");
        createPersonAccountInfoDTO.setAccountDenomination("Revolut");
        createPersonAccountInfoDTO.setAccountDescription("Revolut Account");

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(createPersonAccountInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email it´s not valid");
    }

    @Test
    @DisplayName("Test If User Account was created  - email invalid - invalid format")
    void testIfUserAccountWasCreated_InvalidEmailFormat() {

        //Arrange
        CreatePersonAccountInfoDTO createPersonAccountInfoDTO = new CreatePersonAccountInfoDTO();
        createPersonAccountInfoDTO.setPersonEmail("morty@@gmail.com");
        createPersonAccountInfoDTO.setAccountDenomination("Revolut");
        createPersonAccountInfoDTO.setAccountDescription("Revolut Account");

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(createPersonAccountInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email it´s not valid");
    }

    @Test
    @DisplayName("Test If User Account was created  - account invalid - null")
    void testIfUserAccountWasCreated_AccountDenominationNull() {

        //Arrange
        CreatePersonAccountInfoDTO createPersonAccountInfoDTO = new CreatePersonAccountInfoDTO();
        createPersonAccountInfoDTO.setPersonEmail("morty@gmail.com");
        createPersonAccountInfoDTO.setAccountDenomination(null);
        createPersonAccountInfoDTO.setAccountDescription("Revolut Account");

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(createPersonAccountInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The denomination can´t be null or empty!");
    }

    @Test
    @DisplayName("Test If User Account was created - account invalid - empty")
    void testIfUserAccountWasCreated_AccountDenominationEmpty() {

        //Arrange
        CreatePersonAccountInfoDTO createPersonAccountInfoDTO = new CreatePersonAccountInfoDTO();
        createPersonAccountInfoDTO.setPersonEmail("morty@gmail.com");
        createPersonAccountInfoDTO.setAccountDenomination("");
        createPersonAccountInfoDTO.setAccountDescription("Revolut Account");

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonAccount(createPersonAccountInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The denomination can´t be null or empty!");

    }




}
