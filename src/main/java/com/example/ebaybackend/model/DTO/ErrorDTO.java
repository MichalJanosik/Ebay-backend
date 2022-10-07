package com.example.ebaybackend.model.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class ErrorDTO {
    private String error;
}
