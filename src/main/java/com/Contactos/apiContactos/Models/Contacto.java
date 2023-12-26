package com.Contactos.apiContactos.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Contacto")
@Getter @Setter
public class Contacto {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int Id;

    @Column(name = "Nombre")
    private String Nombre;

    @Column(name = "Apellido")
    private String Apellido;

    @Column(name = "telefono")
    private int Telefono;

    @Column(name = "Correo")
    private String Correo;

    @Column(name = "Direccion")
    private String Direccion;

    public Contacto() {
        
    }
}
