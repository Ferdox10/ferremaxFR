package com.ferdox.ferremaxspring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email; 
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Email(message = "El correo debe ser válido")
    @NotEmpty(message = "El correo no puede estar vacío")
    @Column(nullable = false, unique = true)
    private String email;

    @NotEmpty(message = "La contraseña no puede estar vacía")
    @Column(nullable = false)
    private String password;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
