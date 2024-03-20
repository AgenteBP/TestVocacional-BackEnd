package com.TrabajoFinal.TestVocacional.Security.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    String userName;
    String password;
    String firstName;
    String lastName;
    boolean active; 
}
