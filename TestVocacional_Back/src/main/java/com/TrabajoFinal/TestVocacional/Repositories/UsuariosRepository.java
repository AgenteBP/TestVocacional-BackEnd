package com.TrabajoFinal.TestVocacional.Repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TrabajoFinal.TestVocacional.Models.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer>{

    Optional<Usuarios> findByIdAndActiveTrue(Integer usuarioId);

    Page<Usuarios> findAllByActiveTrue(Pageable pageable);

    Usuarios findByEmail(String email);
}
