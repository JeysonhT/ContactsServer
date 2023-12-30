package com.Contactos.apiContactos.Dao.Interfaces;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.Contactos.apiContactos.Models.Contacto;

public interface DaoContacto {
    List<Contacto> getContactos();

    void deleteContacto(int Id);

    Contacto getContactById(int Id);

    ResponseEntity<String> UpdateContact(Map<String, Object> c);

    ResponseEntity<String> postContact(Contacto c);

    Contacto findContact(String clave);
    
    Contacto findContact(int clave);

}
