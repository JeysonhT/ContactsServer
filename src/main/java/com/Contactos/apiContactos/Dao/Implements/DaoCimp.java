package com.Contactos.apiContactos.Dao.Implements;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.Contactos.apiContactos.Dao.Interfaces.DaoContacto;
import com.Contactos.apiContactos.Models.Contacto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Repository
@org.springframework.transaction.annotation.Transactional
public class DaoCimp implements DaoContacto {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Contacto> getContactos() {
        String query = "FROM Contacto";
        return entityManager.createQuery(query, Contacto.class).getResultList();
    }

    @Override
    public void deleteContacto(int Id) {
        Contacto contacto = entityManager.find(Contacto.class, Id);
        if (contacto != null) {
            entityManager.remove(contacto);
        }
    }

    @Override
    public Contacto getContactById(int Id) {
        String query = "FROM Contacto WHERE Id =: Id";
        List<Contacto> lista = entityManager.createQuery(query, Contacto.class).setParameter("Id", Id).getResultList();
        return lista.get(0);
    }

    @Override
    public ResponseEntity<String> UpdateContact(Map<String, Object> c) {
        int Id = (int) c.get("id");
        String Nombre = (String) c.get("nombre");
        int Telefono = (int) c.get("telefono");

        Contacto ctc = entityManager.find(Contacto.class, Id);

        ctc.setNombre(Nombre);
        ctc.setTelefono(Telefono);

        entityManager.merge(ctc);
        entityManager.flush();

        return ResponseEntity.ok("new Telefono: " + Telefono + " new Nombre: " + Nombre);

    }

    @Override
    public ResponseEntity<String> postContact(Contacto c) {
        if (Comprobar(c)) {
            return ResponseEntity.badRequest().body("El contacto ya existe!");
        }
        if (validar(c.getTelefono())) {
            if (c.getNombre() != null && c.getTelefono() != 0) {
            entityManager.merge(c);
                return ResponseEntity.ok("El Contacto fue agregado");
            } else {
                return ResponseEntity.badRequest().body("Nombre y Telefono Vacios");
            }
        } else {
            return ResponseEntity.badRequest().body("El numero no es valido");
        }
        
    }
    
    private boolean Comprobar(Contacto c) {
        String query = "FROM Contacto WHERE Telefono =: Telefono";
        List<Contacto> lista = entityManager.createQuery(query, Contacto.class).setParameter("Telefono", c.getTelefono())
                .getResultList();
        try{
            Contacto contacto = lista.get(0);
            return contacto.getTelefono() == c.getTelefono();
        }catch (IndexOutOfBoundsException e){
            return false;
        }
            
    }

    private boolean validar(int Telefono) {
        String numero = String.valueOf(Telefono);
        return numero.length() >= 8;
    }

    //cuando se precisa obtener un solo contacto por pantalla, su parametro requiere una palabra
    //clave para buscar un contacto especifico.
    @Override
    public Contacto findContact(String Clave) {
        String query = "FROM Contacto WHERE Nombre =: Clave";

        try {
            return entityManager.createQuery(query, Contacto.class).setParameter("Clave", Clave).getResultList()
                    .get(0);
        } catch (IndexOutOfBoundsException e) {
            return FindbyAddress(Clave);
        }

    }

    //Busqueda por numero de Telefono
    @Override
    public Contacto findContact(int Clave) {
        String query = "FROM Contacto WHERE Telefono =: Clave";

        try{
            return entityManager.createQuery(query, Contacto.class).setParameter("Clave", Clave).getResultList()
                    .get(0);   
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    private Contacto FindbyAddress(String Clave){

        String query = "FROM Contacto WHERE Direccion =: Clave";

        try{
            return entityManager.createQuery(query, Contacto.class).setParameter("Clave", Clave).getResultList()
                    .get(0);

        }catch (IndexOutOfBoundsException e){
            return null;
        }
  
    }


    
    
}
