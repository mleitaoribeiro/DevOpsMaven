package switch2019.project.controllerLayer.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import switch2019.project.DTO.errorDTO.ErrorDTO;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.NoPermissionException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

//Global exception handler used for errors associated with the Rest Controllers:
@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override //status 400
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        final String message = "The request body needed to perform the operation is missing.";

        final String error = "Required request body is missing.";

        ErrorDTO apiError = new ErrorDTO(HttpStatus.BAD_REQUEST, message, error);

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override //status 404
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        ErrorDTO apiError = new ErrorDTO(HttpStatus.NOT_FOUND, "URI does not exist", "The URI you entered cannot be found");
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override //status 415
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        String builder = exception.getContentType() + " media type is not supported.";

        ErrorDTO apiError = new ErrorDTO(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                exception.getLocalizedMessage(), builder);

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override //status 405
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(exception.getMethod());
        builder.append(
                " method is not supported for this request. Supported methods are ");
        exception.getSupportedHttpMethods().forEach(t -> builder.append(t).append(" "));

        ErrorDTO apiError = new ErrorDTO(HttpStatus.METHOD_NOT_ALLOWED,
                exception.getLocalizedMessage(), builder.toString());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({IllegalArgumentException.class}) //status 422
    public ResponseEntity<Object> handleIllegalArgumentException(
            final IllegalArgumentException exception,
            final WebRequest request) {

        //Construction of the error message:
        final String error = exception.getMessage();

        //Construction of the ErrorDTO:
        final ErrorDTO apiError = new ErrorDTO(HttpStatus.UNPROCESSABLE_ENTITY, "One of the parameters is invalid or is missing.", error);

        //Returning a ResponseEntity with the ApiError, the http header of the error and the status of the current ApiError:
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ResourceAlreadyExistsException.class}) //status 409
    public ResponseEntity<Object> handleResourceAlreadyExistsException(
            final ResourceAlreadyExistsException exception,
            final WebRequest request) {

        //Construction of the error message:
        final String error = exception.getMessage();

        //Construction of the ErrorDTO:
        final ErrorDTO apiError = new ErrorDTO(HttpStatus.CONFLICT, "This resource already exists.", error);

        //Returning a ResponseEntity with the ApiError, the http header of the error and the status of the current ApiError:
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ArgumentNotFoundException.class}) //status 422
    public ResponseEntity<Object> handleArgumentNotFoundException(
            final ArgumentNotFoundException exception,
            final WebRequest request) {

        //Construction of the error message:
        final String error = exception.getMessage();

        //Construction of the ErrorDTO:
        final ErrorDTO apiError = new ErrorDTO(HttpStatus.UNPROCESSABLE_ENTITY, "This resource was not found.", error);

        //Returning a ResponseEntity with the ApiError, the http header of the error and the status of the current ApiError:
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({NoPermissionException.class}) //status 403
    public ResponseEntity<Object> handleNoPermissionException(
            final NoPermissionException exception,
            final WebRequest request) {

        //Construction of the error message:
        final String error = exception.getMessage();

        //Construction of the ErrorDTO:
        final ErrorDTO apiError = new ErrorDTO(HttpStatus.FORBIDDEN, "No permission for this operation.", error);

        //Returning a ResponseEntity with the ApiError, the http header of the error and the status of the current ApiError:
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception someException, WebRequest request) {
        ErrorDTO apiError = new ErrorDTO(
                HttpStatus.INTERNAL_SERVER_ERROR, "error occurred", someException.getLocalizedMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

}