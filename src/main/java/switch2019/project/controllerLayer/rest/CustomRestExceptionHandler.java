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
import switch2019.project.DTO.errorDTO.ErrorDTO;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.NoPermissionException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

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

        //Construction of the error:
        String error = errors.toString();

        //Construction of the ErrorDTO with all the errors present:
        final ErrorDTO apiError = new ErrorDTO (HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), error);

        //Return of the HandleExceptionInternal:
        return handleExceptionInternal(exception, apiError, headers, apiError.getStatus(), request);
    }

    /**
     * Exception handler for MethodArgumentTypeMismatchExceptions
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(
            final MethodArgumentTypeMismatchException exception,
            final WebRequest request) {

        //Construction of the error message:
        final String error = exception.getName() + " should be of of type " + exception.getRequiredType().getName();

        //Construction of the ErrorDTO:
        final ErrorDTO apiError = new ErrorDTO(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), error);

        //Returning a ResponseEntity with the ApiError, the http header of the error and the status of the current ApiError:
        return new ResponseEntity<Object>(apiError,new HttpHeaders(), apiError.getStatus());
    }

    /**
     * Exception handler for IllegalArgumentExceptions
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity<Object> handleIllegalArgumentException(
            final IllegalArgumentException exception,
            final WebRequest request) {

        //Construction of the error message:
        final String error = exception.getMessage();

        //Construction of the ErrorDTO:
        final ErrorDTO apiError = new ErrorDTO(HttpStatus.UNPROCESSABLE_ENTITY, "One of the parameters is invalid or is missing.", error);

        //Returning a ResponseEntity with the ApiError, the http header of the error and the status of the current ApiError:
        return new ResponseEntity<Object>(apiError,new HttpHeaders(), apiError.getStatus());
    }

    /**
     * Exception handler for ResourceAlreadyExistsExceptions
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler({ ResourceAlreadyExistsException.class })
    public ResponseEntity<Object> handleResourceAlreadyExistsException(
            final ResourceAlreadyExistsException exception,
            final WebRequest request) {

        //Construction of the error message:
        final String error = exception.getMessage();

        //Construction of the ErrorDTO:
        final ErrorDTO apiError = new ErrorDTO(HttpStatus.CONFLICT, "This resource already exists.", error);

        //Returning a ResponseEntity with the ApiError, the http header of the error and the status of the current ApiError:
        return new ResponseEntity<Object>(apiError,new HttpHeaders(), apiError.getStatus());
    }

    /**
     * Exception handler for ArgumentNotFoundExceptions
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler({ ArgumentNotFoundException.class })
    public ResponseEntity<Object> handleArgumentNotFoundException(
            final ArgumentNotFoundException exception,
            final WebRequest request) {

        //Construction of the error message:
        final String error = exception.getMessage();

        //Construction of the ErrorDTO:
        final ErrorDTO apiError = new ErrorDTO(HttpStatus.UNPROCESSABLE_ENTITY, "This resource was not found.", error);

        //Returning a ResponseEntity with the ApiError, the http header of the error and the status of the current ApiError:
        return new ResponseEntity<Object>(apiError,new HttpHeaders(), apiError.getStatus());
    }

    /**
     * Exception handler for NoPermissionExceptions
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler({ NoPermissionException.class })
    public ResponseEntity<Object> handleNoPermissionException(
            final NoPermissionException exception,
            final WebRequest request) {

        //Construction of the error message:
        final String error = exception.getMessage();

        //Construction of the ErrorDTO:
        final ErrorDTO apiError = new ErrorDTO(HttpStatus.FORBIDDEN, "No permission for this operation.", error);

        //Returning a ResponseEntity with the ApiError, the http header of the error and the status of the current ApiError:
        return new ResponseEntity<Object>(apiError,new HttpHeaders(), apiError.getStatus());
    }
}


