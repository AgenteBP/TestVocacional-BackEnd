package com.TrabajoFinal.TestVocacional.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Objects;

import com.TrabajoFinal.TestVocacional.Models.Email;

// import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;



    // public Email enviarCorreo(Email email, MultipartFile archivoAdjunto) throws jakarta.mail.MessagingException, IOException {

    //     jakarta.mail.internet.MimeMessage mensaje = javaMailSender.createMimeMessage();
    //     MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);

    //     // email.setDestinatario(destinatario);
    //     email.setAsunto("Información sobre la carrera obtenida");
    //     email.setCuerpo("La información necesaria para saber más sobre la carrera es: https://fmn.unsl.edu.ar/carreras-de-informatica/");

    //     helper.setTo(email.getDestinatario());
    //     helper.setSubject(email.getAsunto());
    //     helper.setText(email.getCuerpo());

    //     // Adjunta el archivo al mensaje si es necesario
    //     if (archivoAdjunto != null && !Objects.requireNonNull(archivoAdjunto.getOriginalFilename()).isEmpty()) {
    //         InputStreamSource source = new ByteArrayResource(archivoAdjunto.getBytes());
    //         helper.addAttachment(archivoAdjunto.getOriginalFilename(), source);
    //     }

    //     javaMailSender.send(mensaje);

    //     return email;
    // }

    public Email enviarCorreo(Email email, MultipartFile archivoAdjunto) throws jakarta.mail.MessagingException, IOException {

        jakarta.mail.internet.MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);
        String linkCarerra = null;

        // email.setDestinatario(destinatario);
        email.setAsunto("Información sobre la carrera obtenida");
        switch (email.getTypeofPDF()) {
            case 1:
                linkCarerra = "https://carreras.unsl.edu.ar/carreras/tec-web";
                break;
            case 2:
                linkCarerra = "https://carreras.unsl.edu.ar/carreras/tec-redes-comp";
                break;
            case 3:
                linkCarerra = "https://carreras.unsl.edu.ar/carreras/prof-cs-comp";
                break;
            case 4:
                linkCarerra = "https://carreras.unsl.edu.ar/carreras/lic-cs-comp";
                break;
            case 5:
                linkCarerra = "https://carreras.unsl.edu.ar/carreras/ing-informatica";
                break;
            case 6:
                linkCarerra = "https://carreras.unsl.edu.ar/carreras/ing-computacion";
                break;
        }
        email.setCuerpo("La información necesaria para saber más sobre la carrera es: " + linkCarerra);

        helper.setTo(email.getDestinatario());
        helper.setSubject(email.getAsunto());
        helper.setText(email.getCuerpo());

        // Adjunta el archivo al mensaje si es necesario
        if (archivoAdjunto != null && !Objects.requireNonNull(archivoAdjunto.getOriginalFilename()).isEmpty()) {
            InputStreamSource source = new ByteArrayResource(archivoAdjunto.getBytes());
            helper.addAttachment(archivoAdjunto.getOriginalFilename(), source);
        }

        javaMailSender.send(mensaje);

        return email;
    }
}