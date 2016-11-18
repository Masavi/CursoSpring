package com.example.servicios.serviciosjpa;

import com.example.dominio.Order;
import com.example.servicios.OrderService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by jt on 12/16/15.
 */
@Service
@Profile("jpadao")
public class OrderServiceJpaDaoImpl extends AbstractJpaDaoService implements OrderService {

    @Override
    public List<Order> listarTodos() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Order", Order.class).getResultList();
    }

    @Override
    public Order obtenerPorId(Integer id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Order.class, id);
    }

    @Override
    public Order guardarActualizar(Order domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Order savedProduct = em.merge(domainObject);
        em.getTransaction().commit();

        return savedProduct;
    }

    @Override
    public void borrar(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Order.class, id));
        em.getTransaction().commit();
    }
}
