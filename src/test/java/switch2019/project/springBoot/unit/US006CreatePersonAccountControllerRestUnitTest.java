package switch2019.project.springBoot.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2019.project.applicationLayer.US006CreatePersonAccountService;
import switch2019.project.controllerLayer.controllersCli.US006CreatePersonAccountController;


@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)

public class US006CreatePersonAccountControllerRestUnitTest {

    @Mock @Autowired private US006CreatePersonAccountService service;
    @Autowired private US006CreatePersonAccountController controller;


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

    @DisplayName("Test If User Account was created  - email invalid - empty")
    @Test
    void test5() {
        //test here
    }

    @DisplayName("Test If User Account was created  - email invalid - invalid format")
    @Test
    void test6() {
        //test here
    }

    @DisplayName("Test If User Account was created  - account invalid - null")
    @Test
    void test7() {
        //test here
    }

    @DisplayName("Test If User Account was created - account invalid - empty")
    @Test
    void test8() {
        //test here
    }




}
