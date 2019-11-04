package com.subscriber.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHelper {
    public static <T> ResponseEntity<T> getOkResponseWithBody(T body) {
        return ResponseEntity
                .ok(body);
    }

    public static ResponseEntity<?> getNotFoundResponse() {
        return ResponseEntity
                .notFound()
                .build();
    }

    public static <T> ResponseEntity<T> getNotFoundResponseWithBody(T body) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(body);
    }


    public static <T> ResponseEntity<T> getStatusResponseWithBody(HttpStatus status, T body) {
        return ResponseEntity
                .status(status)
                .body(body);
    }
}
