package com.TrabajoFinal.TestVocacional.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.TrabajoFinal.TestVocacional.Models.Usuarios;
import com.TrabajoFinal.TestVocacional.Services.UsuarioService;
import com.TrabajoFinal.TestVocacional.Urls.UrlFront;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = {UrlFront.urlLocal, UrlFront.urlNetlify})
public class UsuarioController {
    private final int DEFAULT_PAGE_NUMBER = 0;
    private final int DEFAULT_QUANTITY_PER_PAGE = 100;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/usuarios/{id}")
    public Usuarios getUsuarios(@PathVariable int id) {
        Usuarios usuarios = this.usuarioService.getOne(id);
        return usuarios;
    }

    @GetMapping(value = "/usuarios")
    public Page<Usuarios> getAllUsuarios(@RequestParam Map<String, String> map) {
        
        Page<Usuarios> page;

        if (!map.containsKey("page") && !map.containsKey("quantity")) {
            page = this.usuarioService.getAll(this.DEFAULT_PAGE_NUMBER,
                    this.DEFAULT_QUANTITY_PER_PAGE);
        } else if (map.containsKey("page") && !map.containsKey("quantity")) {
            page = this.usuarioService.getAll(Integer.parseInt(map.get("page")),
                    this.DEFAULT_QUANTITY_PER_PAGE);
        } else if (!map.containsKey("page") && map.containsKey("quantity")) {
            page = this.usuarioService.getAll(this.DEFAULT_PAGE_NUMBER,
                    Integer.parseInt(map.get("quantity")));
        } else {
            page = this.usuarioService.getAll(Integer.parseInt(map.get("page")),
                    Integer.parseInt(map.get("quantity")));
        }
        return page;
    }

    // @PostMapping(value = "/usuarios")
    // public Usuarios postUsuarios(@RequestBody Usuarios usuarios) {
    //     Usuarios u = usuarioService.insert(usuarios);
    //     return u;
    // }

    @PostMapping(value = "/usuarios")
    public ResponseEntity<Usuarios> postUsuarios(@RequestBody Usuarios usuarios) {
        Usuarios usuarioInsertado = usuarioService.insert(usuarios);

        // Verifica si la inserción fue exitosa
        if (usuarioInsertado != null && usuarioInsertado.getId() != null) {
            // Devuelve el usuario con la ID generada y un código de estado 201 (CREATED)
            return new ResponseEntity<>(usuarioInsertado, HttpStatus.CREATED);
        } else {
            // Si la inserción falla, puedes devolver un código de estado 500 (INTERNAL_SERVER_ERROR)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
