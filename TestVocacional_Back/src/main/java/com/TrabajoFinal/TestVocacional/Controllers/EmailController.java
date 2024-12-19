package com.TrabajoFinal.TestVocacional.Controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.TrabajoFinal.TestVocacional.Models.Email;
import com.TrabajoFinal.TestVocacional.Services.EmailService;
import com.TrabajoFinal.TestVocacional.Urls.UrlFront;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/sendEmail")
@CrossOrigin(origins = {UrlFront.urlLocal, UrlFront.urlNetlify})
public class EmailController {

    @Autowired
    private EmailService emailService;
    @PostMapping("/enviar")
    public ResponseEntity<Email>  enviarCorreo(@RequestParam("destinatario") String destinatario,@RequestParam("typeofPDF") String tipoDePDF,
    @RequestParam("pdf") MultipartFile pdfAdjunto) throws MessagingException, IOException {

        // Construye el objeto Email con los datos recibidos
        // Email email = new Email();
        // email.setDestinatario(destinatario);
        // email.setTypeofPDF(Integer.parseInt(tipoDePDF));


        // return ResponseEntity.ok(emailService.enviarCorreo(email, pdfAdjunto));
        return ResponseEntity.ok(new Email());
    }
}
