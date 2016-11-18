package com.example.servicios.serviciosmapas;

import com.example.dominio.ObjetoDominio;
import com.example.dominio.Order;
import com.example.servicios.OrderService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jt on 12/16/15.
 */
@Service
@Profile("map")
public class OrderServiceMapImpl extends MapaAbstractoService implements OrderService {
    @Override
    public List<ObjetoDominio> listarTodos() {
        return super.listarTodos();
    }

    @Override
    public Order obtenerPorId(Integer id) {
        return (Order) super.obtenerPorId(id);
    }

    @Override
    public Order guardarActualizar(Order domainObject) {
        return (Order) super.guardarActualizar(domainObject);
    }

    @Override
    public void borrar(Integer id) {
        super.borrar(id);
    }
}
