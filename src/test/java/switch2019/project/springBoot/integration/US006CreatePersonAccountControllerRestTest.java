package switch2019.project.springBoot.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import switch2019.project.AbstractTest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class US006CreatePersonAccountControllerRestTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUP(){
        super.setUP();
    }

    //ISSUE 814

    @DisplayName("Test If User Account is created - Main Scenario")
    @Test
    void test1() {
        //test here
    }



}
