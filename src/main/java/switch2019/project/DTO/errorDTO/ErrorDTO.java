package switch2019.project.DTO.errorDTO;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

public class ErrorDTO {
    private String timestamp;
    private int statusCode;
    private HttpStatus status;
    private String error;
    private String message;

    public ErrorDTO() {
        super();
    }

    public ErrorDTO(final HttpStatus status, final String error, final String message) {
        super();
        this.timestamp = LocalDateTime.now().withSecond(0).withNano(0).toString();
        this.statusCode = status.value();
        this.status = status;
        this.message = message;
        this.error = error;
    }




    public HttpStatus getStatus() {
        return status;
    }

    public String getTimestamp() {return timestamp;}

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {return statusCode;}

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}