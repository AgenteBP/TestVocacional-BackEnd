package com.TrabajoFinal.TestVocacional.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.TrabajoFinal.TestVocacional.Security.Jwt.TokenResponse;
import com.TrabajoFinal.TestVocacional.Security.Request.LoginRequest;
import com.TrabajoFinal.TestVocacional.Security.Request.RegisterRequest;
import com.TrabajoFinal.TestVocacional.Services.AccountService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AccountController {

    @Autowired
    private AccountService accountService;


    // @PostMapping("/api/register")
    // public ResponseEntity<?> registerAccount(@RequestBody Account account) throws Exception {
    //     // Verifica si el nombre de usuario ya está en uso
    //     if (accountService.loadUserByUsername(account.getUserName()) != null) {
    //         return new ResponseEntity<>("Nombre de usuario ya en uso", HttpStatus.BAD_REQUEST);
    //     }

    //     accountService.insert(account);

    //     return new ResponseEntity<>("Cuenta registrada exitosamente", HttpStatus.CREATED);
    // }

    // @PostMapping("/api/login")
    // public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
    //     // Autenticación
    //     Authentication authentication = authenticationManager.authenticate(
    //             new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword())
    //     );

    //     // Si la autenticación es exitosa, generamos el token
    //     String token = jwtTokenProvider.generateToken((UserDetails) authentication.getPrincipal());

    //     return ResponseEntity.ok(new JwtResponse(token));
    // }

    @PostMapping(value = "login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(accountService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<TokenResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(accountService.insert(request));
    }
}
