package com.TrabajoFinal.TestVocacional.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.TrabajoFinal.TestVocacional.Models.Email;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;



    public Email enviarCorreo(Email email) {

        // email.setDestinatario(destinatario);
        email.setAsunto("información sobre la carrera obtenida");
        email.setCuerpo("La información necesaria para saber más sobre la carrera es: https://carreras.unsl.edu.ar/");

        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(email.getDestinatario());
        mensaje.setSubject(email.getAsunto());
        mensaje.setText(email.getCuerpo());

        javaMailSender.send(mensaje);

        return email;
    }
}