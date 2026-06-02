package school.sptech.exemplo_mock.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import school.sptech.exemplo_mock.exception.ExternalServiceException;
import school.sptech.exemplo_mock.exception.ResourceNotFoundException;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse(
                "recurso_nao_encontrado",
                ex.getMessage(),
                Map.of()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ExternalServiceException.class)
    public ResponseEntity<ApiErrorResponse> handleExternalService(ExternalServiceException ex) {
        ApiErrorResponse response = new ApiErrorResponse(
                "falha_integracao_externa",
                ex.getMessage(),
                Map.of()
        );

        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> detalhes = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(fieldError -> detalhes.put(fieldError.getField(), fieldError.getDefaultMessage()));

        ApiErrorResponse response = new ApiErrorResponse(
                "dados_invalidos",
                "A requisicao possui campos invalidos.",
                detalhes
        );

        return ResponseEntity.badRequest().body(response);
    }
}
