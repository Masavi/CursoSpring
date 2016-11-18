package com.example.servicios.serviciosjpa;

import java.util.List;

import javax.persistence.EntityManager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.dominio.Consumidor;
import com.example.servicios.ConsumidorService;

import com.example.servicios.seguridad.EncryptionService;

import com.example.comandos.ConsumidorForm;
import com.example.convertidores.ConsumidorFormAConsumidor;

@Service
@Profile("jpadao")
public class ConsumidorServiceJpaDaoImpl extends AbstractJpaDaoService implements ConsumidorService {

    private EncryptionService encryptionService;
    private ConsumidorFormAConsumidor customerFormToConsumidor;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }
    
    @Override
    public List<Consumidor> listarTodos() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Consumidor", Consumidor.class).getResultList();
    }

    @Override
    public Consumidor obtenerPorId(Integer id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Consumidor.class, id);
    }

    @Override
    public Consumidor guardarActualizar(Consumidor domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        
        if (domainObject.getUser() != null && domainObject.getUser().getPassword() != null) {
            domainObject.getUser().setEncryptedPassword(
                    encryptionService.encryptString(domainObject.getUser().getPassword()));
        }
        
        Consumidor savedConsumidor = em.merge(domainObject);
        em.getTransaction().commit();

        return savedConsumidor;
    }

    @Override
    public void borrar(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Consumidor.class, id));
        em.getTransaction().commit();
    }
    
    @Override
    public Consumidor guardarActualizarConsumidorForm(ConsumidorForm customerForm) {
        Consumidor newConsumidor = customerFormToConsumidor.convert(customerForm);

        //enhance if saved
        if(newConsumidor.getUser().getId() != null){
            Consumidor existingConsumidor = obtenerPorId(newConsumidor.getUser().getId());

            //set enabled flag from db
            newConsumidor.getUser().setEnabled(existingConsumidor.getUser().getEnabled());
        }

        return guardarActualizar(newConsumidor);
    }

}
