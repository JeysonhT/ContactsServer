package com.Contactos.apiContactos.Controllers;

import java.util.List;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Contactos.apiContactos.Dao.Interfaces.DaoContacto;

import com.Contactos.apiContactos.Models.Contacto;

@RestController
public class UserController {
    
    @Autowired
    DaoContacto daoContacto;
    
    @GetMapping("Api/User")
    public String getUser() {
        return "Hola Usuario Jeysonh";
    }
    
    @GetMapping(value = "Api/Contacto")
    public List<Contacto> getContacts() {
        return daoContacto.getContactos();
    }

    @PostMapping(value = "Api/Contacto")
    public ResponseEntity<String> postContacto(@RequestBody Contacto contacto) {
        return daoContacto.postContact(contacto);
    }
    
    @DeleteMapping(value = "Api/Contacto/{Id}")
    public void deleteContact(@PathVariable int Id) {
        daoContacto.deleteContacto(Id);
    }

    @PutMapping(value = "Api/Contacto")
    public ResponseEntity<String> putContact(@RequestBody Map<String, Object> c) {
        return daoContacto.UpdateContact(c);
    }

    @GetMapping(value = "api/Contacto/Buscar/{Clave}")
    public Contacto findContacto(@PathVariable String Clave) {
        return daoContacto.findContact(Clave);
    }
}
