package com.ferdox.ferremaxspring.repository;

import com.ferdox.ferremaxspring.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
