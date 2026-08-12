package com.hospital.DtoRequest;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CompleteTokenRequest {
    @NotBlank(message = "counter code is required")
    private String counterCode;
}
