package com.sptech.school.exemploIntegracao;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UsuarioClientDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        return switch (response.status()) {
            case 400, 422 -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_CONTENT);
            case 404 -> new ResponseStatusException(HttpStatus.NOT_FOUND);
            default -> new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
        };
    }
}
