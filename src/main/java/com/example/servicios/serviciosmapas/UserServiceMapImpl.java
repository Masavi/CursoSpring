package com.example.servicios.serviciosmapas;

import com.example.dominio.ObjetoDominio;
import com.example.dominio.User;
import com.example.servicios.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jt on 12/14/15.
 */
@Service
@Profile("map")
public class UserServiceMapImpl extends MapaAbstractoService implements UserService {

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
}
