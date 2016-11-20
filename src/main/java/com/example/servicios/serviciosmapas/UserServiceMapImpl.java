package com.example.servicios.serviciosmapas;

import com.example.dominio.ObjetoDominio;
import com.example.dominio.User;
import com.example.servicios.UserService;

import com.example.servicios.seguridad.EncryptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Created by jt on 12/14/15.
 */
@Service
@Profile("map")
public class UserServiceMapImpl extends MapaAbstractoService implements UserService {

    private EncryptionService encryptionService;
    
    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Override
    public List<ObjetoDominio> listarTodos() {
        return super.listarTodos();
    }

    @Override
    public User obtenerPorId(Integer id) {
        return (User) super.obtenerPorId(id);
    }

    @Override
    public User guardarActualizar(User domainObject) {
        return (User) super.guardarActualizar(domainObject);
    }

    @Override
    public void borrar(Integer id) {
        super.borrar(id);
    }
    
    @Override
    public User findByUserName(String userName) {

        Optional returnUser =  mapaDominio.values().stream().filter(new Predicate<ObjetoDominio>() {
            @Override
            public boolean test(ObjetoDominio domainObject) {
                User user = (User) domainObject;
                return user.getUsername().equalsIgnoreCase(userName);
            }
        }).findFirst();

        return (User) returnUser.get();
    }
}
