package com.TrabajoFinal.TestVocacional.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.TrabajoFinal.TestVocacional.Models.Usuarios;
import com.TrabajoFinal.TestVocacional.Repositories.UsuariosRepository;
import com.TrabajoFinal.TestVocacional.exceptions.ErrorResponse;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuariosRepository usuariosRepository;

    public Usuarios getOne(int usuarioId){
        Usuarios usuarios = null;
        Optional<Usuarios> usuariosOptional = this.usuariosRepository.findByIdAndActiveTrue(usuarioId);

        if(usuariosOptional.isPresent()){
            usuarios = usuariosOptional.get();
        }
        else{
            throw new ErrorResponse("No hay usuarios con id: " + usuarioId,
             HttpStatus.NOT_FOUND);
        }

        return usuarios;
    }

    public Page<Usuarios> getAll(int page, int quantityPerPage){

        return this.usuariosRepository.findAllByActiveTrue(PageRequest.of(page, quantityPerPage));
    }

    @Transactional
    public Usuarios insert(Usuarios usuarios) {
        
        Usuarios usuarios2 =  new Usuarios();
        Usuarios usuarioExistente = usuariosRepository.findByEmail(usuarios.getEmail());
        try {
            
            if (usuarioExistente != null) {
                // Ya existe un usuario con el mismo correo electrónico
                // Puedes decidir qué hacer en este punto, por ejemplo, actualizar el usuario existente
                usuarioExistente.setEdad(usuarios.getEdad());
                usuarioExistente.setEsResidenteArg(usuarios.getEsResidenteArg());
                usuarioExistente.setProvincia(usuarios.getProvincia());
                if(usuarios.getProvincia().equals("San Luis")){
                    usuarioExistente.setSchoolInSanLuis(usuarios.getSchoolInSanLuis());
                }else{
                    usuarioExistente.setSchoolInSanLuis(null);
                }
                usuarioExistente.setPaisOrigen(usuarios.getPaisOrigen());

                // Guardar el usuario actualizado en la base de datos
                return usuariosRepository.save(usuarioExistente);
            } else {
                // No existe un usuario con el mismo correo electrónico, proceder con la inserción
                if(!usuarios.getEsResidenteArg()){
                    usuarios.setProvincia(null);
                    usuarios.setSchoolInSanLuis(null);
                }
                else{
                    usuarios.setPaisOrigen(null);
                }
                return usuariosRepository.save(usuarios);
            }
            
        } catch (DataIntegrityViolationException e) {
            // e.printStackTrace();
            // throw new ErrorResponse(e.getMostSpecificCause().getMessage(),
            //         HttpStatus.UNPROCESSABLE_ENTITY);
            System.out.println("no funcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            e.printStackTrace();  // Imprime el log completo de la excepción
            System.out.println("Error en la inserción: " + e.getMessage());
        }
        return usuarios2;
    }
}
