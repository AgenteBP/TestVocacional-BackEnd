package com.TrabajoFinal.TestVocacional.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.TrabajoFinal.TestVocacional.Models.Account;
import com.TrabajoFinal.TestVocacional.Repositories.AccountRepository;
import com.TrabajoFinal.TestVocacional.Security.Jwt.JwtService;
import com.TrabajoFinal.TestVocacional.Security.Jwt.TokenResponse;
import com.TrabajoFinal.TestVocacional.Security.Request.LoginRequest;
import com.TrabajoFinal.TestVocacional.Security.Request.RegisterRequest;
import com.TrabajoFinal.TestVocacional.Security.Role.Role;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AccountRepository accountRepository; 

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //     Account account = accountRepository.findByUsername(username)
    //             .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

    //     return account;
    // }

    // public Account insert(Account account) {

    //     account.setPassword(passwordEncoder.encode(account.getPassword()));
    //     return accountRepository.save(account);
    // }    
    
    public TokenResponse login(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));

        UserDetails userDetails = accountRepository.findByUserName(request.getUserName()).orElseThrow();
        String token = jwtService.getToken(userDetails);

        return TokenResponse.builder().token(token).build();
    }

    public TokenResponse insert(RegisterRequest request) {
        Account account = Account.builder()
            .userName(request.getUserName())
            .password(passwordEncoder.encode( request.getPassword()))
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .active(true)
            .role(Role.ADMIN)
            .build();
            

        accountRepository.save(account);

        return TokenResponse.builder()
            .token(jwtService.getToken(account))
            .build();
        
    }
}
