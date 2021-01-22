package nl.hva.miw.internetbanking.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiError {
    
    private static final String DEFAULT_MESSAGE = "Onverwachte fout";
    
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    
    private ApiError() {
        timestamp = LocalDateTime.now();
        message = DEFAULT_MESSAGE;
    }
    
    public ApiError(HttpStatus status) {
        this();
        this.status = status;
    }
    
    public ApiError(HttpStatus status, Throwable throwable) {
        this();
        this.status = status;
        message = throwable.getLocalizedMessage();
    }
    
    public ApiError(HttpStatus status, String message) {
        this();
        this.status = status;
        this.message = message;
    }
    
}
