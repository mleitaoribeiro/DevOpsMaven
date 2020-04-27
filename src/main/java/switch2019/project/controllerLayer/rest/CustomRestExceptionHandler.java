package switch2019.project.controllerLayer.rest;

import java.lang.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import switch2019.project.DTO.error.ErrorDTO;
import java.util.ArrayList;
import java.util.List;

//Global exception handler used for errors associated with the Rest Controllers:
@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Error handler for error status 400
     * @param exception
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override // status 400 - Errors Handling
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException exception,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request) {

        final List<String> errors = new ArrayList<String>();

        //Obtaining the errors for each field:
        for (final FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " +error.getDefaultMessage());
        }

        //Obtaining the errors for the objects associated with the fields:
        for (final ObjectError error : exception.getBindingResult().getFieldErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        //Construction of the ApiError with all the errors present:
        final ErrorDTO apiError = new ErrorDTO (HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), errors);

        //Return of the HandleExceptionInternal:
        return handleExceptionInternal(exception, apiError, headers, apiError.getStatus(), request);
    }

    /**
     * Exception handler for MethodArgumentTypeMismatchException
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            final MethodArgumentTypeMismatchException exception,
            final WebRequest request) {

        //Construction of the error message:
        final String error = exception.getName() + " should be of of type " + exception.getRequiredType().getName();

        //Construction of the ApiError:
        final ErrorDTO apiError = new ErrorDTO(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), error);

        //Returning a ResponseEntity with the ApiError, the http header of the error and the status of the current ApiError:
        return new ResponseEntity<Object>(apiError,new HttpHeaders(), apiError.getStatus());
    }

   
}


