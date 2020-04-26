package switch2019.project.error;

import com.sun.tools.javac.util.List;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class ApiError {
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ApiError() {
        super();

    }

    public ApiError(final HttpStatus status, final String message, final List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(final HttpStatus status, final String message, final String error) {
        super();
        this.status = status;
        this.message = message;
        errors = (List<String>) Arrays.asList(error);
    }


    public HttpStatus getStatus() {
        return status;
    }


    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}