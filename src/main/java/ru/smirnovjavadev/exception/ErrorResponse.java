package ru.smirnovjavadev.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private String uuid;
    private String code;
    private Integer status;
    private String method;
    private String path;
    private String message;
    private LocalDateTime timestamp;
    private Map<String, Object> details;

    public static ErrorResponse build(HttpStatus status, String message) {
        return ErrorResponse.builder()
                .uuid(String.valueOf(UUID.randomUUID()))
                .status(status.value())
                .code(status.name())
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }

}