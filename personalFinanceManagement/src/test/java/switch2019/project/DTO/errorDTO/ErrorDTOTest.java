package switch2019.project.DTO.errorDTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ErrorDTOTest {
        @Test
        void get() {

            ErrorDTO errorDTO = new ErrorDTO(HttpStatus.UNPROCESSABLE_ENTITY, "This category already exists", "This category already exists");
            HttpStatus statusExpected = HttpStatus.UNPROCESSABLE_ENTITY;
            String messageExpected = "This category already exists";
            String errorExpected = "This category already exists";

            //Act
            HttpStatus statusActual = errorDTO.getStatus();
            String messageActual = errorDTO.getMessage();
            String errorActual = errorDTO.getMessage();
            //Assert

            Assertions.assertAll(
                    () -> assertEquals(statusExpected, statusActual),
                    () -> assertEquals(messageExpected, messageActual),
                    () -> assertEquals(errorExpected, errorActual)
            );
        }


    @Test
    void set() {

        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.UNPROCESSABLE_ENTITY, "This category already exists", "This category already exists");
        HttpStatus statusExpected = HttpStatus.UNPROCESSABLE_ENTITY;
        String messageExpected = "This category already exists";
        String errorExpected = "This category already exists";

        //Act
        errorDTO.setStatus(statusExpected);
        HttpStatus statusActual = errorDTO.getStatus();

        errorDTO.setMessage(messageExpected);
        String messageActual = errorDTO.getMessage();

        errorDTO.setMessage(messageActual);
        String errorActual = errorDTO.getMessage();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(statusExpected, statusActual),
                () -> assertEquals(messageExpected, messageActual),
                () -> assertEquals(errorExpected, errorActual)
        );
    }
    }

