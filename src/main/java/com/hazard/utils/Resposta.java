package com.hazard.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Resposta {
    private int code;
    private String message;
    private Object body;
}
