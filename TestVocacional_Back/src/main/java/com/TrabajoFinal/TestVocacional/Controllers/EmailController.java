package com.TrabajoFinal.TestVocacional.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TrabajoFinal.TestVocacional.Models.Email;
import com.TrabajoFinal.TestVocacional.Services.EmailService;

@RestController
@RequestMapping("/sendEmail")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class EmailController {

    @Autowired
    private EmailService emailService;
    @PostMapping("/enviar")
    public ResponseEntity<Email>  enviarCorreo(@RequestBody Email email) {

        return ResponseEntity.ok(emailService.enviarCorreo(email));
    }
}
