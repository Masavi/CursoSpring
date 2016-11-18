package com.example.services;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.config.JpaIntegrationConfig;
import com.example.dominio.Consumidor;
import com.example.servicios.ConsumidorService;

import com.example.dominio.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class ConsumidorServiceJpaDaoImplTest {
	private ConsumidorService customerService;

    @Autowired
    public void setConsumidorService(ConsumidorService customerService) {
        this.customerService = customerService;
    }

    @Test
    public void listarTest() throws Exception {
        List<Consumidor> customers = (List<Consumidor>) customerService.listarTodos();

        assert customers.size() == 3;

    }	
    
    @Test
    public void testSaveWithUser() {

        Consumidor customer = new Consumidor();
        User user = new User();
        user.setUsername("This is my user name");
        user.setPassword("MyAwesomePassword");
        customer.setUser(user);

        Consumidor savedConsumidor = customerService.guardarActualizar(customer);

        assert savedConsumidor.getUser().getId() != null;
    }
}
