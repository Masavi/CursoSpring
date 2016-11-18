package com.example.servicios.serviciosjpa;

import com.example.dominio.seguridad.Role;
import com.example.servicios.RoleService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by jt on 12/18/15.
 */
@Service
@Profile("jpadao")
public class RoleServiceJpaImpl extends AbstractJpaDaoService implements RoleService {

    @Override
    public List<?> listarTodos() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public Role obtenerPorId(Integer id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Role.class, id);
    }

    @Override
    public Role guardarActualizar(Role domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Role saveRole = em.merge(domainObject);
        em.getTransaction().commit();

        return saveRole;
    }

    @Override
    public void borrar(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Role.class, id));
        em.getTransaction().commit();
    }
}
