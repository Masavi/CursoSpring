package com.example.controladores;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.dominio.Consumidor;
import com.example.servicios.ConsumidorService;

import com.example.dominio.Address;

public class ConsumidorControllerTest {
	@Mock 
    private ConsumidorService consumidorService;

    @InjectMocks 
    private ConsumidorController consumidorController;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this); 

        mockMvc = MockMvcBuilders.standaloneSetup(consumidorController).build();
    }

    @Test
    public void testLista() throws Exception{

        List<Consumidor> consumidores = new ArrayList<>();
        consumidores.add(new Consumidor());
        consumidores.add(new Consumidor());

        when(consumidorService.listarTodos()).thenReturn((List) consumidores); 

        mockMvc.perform(get("/consumidores/lista"))
                .andExpect(status().isOk())
                .andExpect(view().name("consumidores/lista"))
                .andExpect(model().attribute("consumidores", hasSize(2)));
    }

    @Test
    public void testMostrar() throws Exception{
        Integer id = 1;

        when(consumidorService.obtenerPorId(id)).thenReturn(new Consumidor());

        mockMvc.perform(get("/consumidores/consumidor/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("consumidores/consumidor"))
                .andExpect(model().attribute("consumidor", instanceOf(Consumidor.class)));
    }

    @Test
    public void testEditar() throws Exception{
        Integer id = 1;

        when(consumidorService.obtenerPorId(id)).thenReturn(new Consumidor());

        mockMvc.perform(get("/consumidores/consumidor/editar/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("consumidores/consumidorform"))
                .andExpect(model().attribute("consumidor", instanceOf(Consumidor.class)));
    }

    @Test
    public void testNuevo() throws Exception {

        verifyZeroInteractions(consumidorService);

        mockMvc.perform(get("/consumidores/consumidor/nuevo"))
                .andExpect(status().isOk())
                .andExpect(view().name("consumidores/consumidorform"))
                .andExpect(model().attribute("consumidor", instanceOf(Consumidor.class)));
    }

    @Test
    public void testGuardar() throws Exception {
        Consumidor returnConsumidor = new Consumidor();
        Integer id=1;
        String nombre="consumidor 1";
        String apell="Apellidos 1";
        String corre="Correo 1";
        String tel="Teléfono 1";
        String dir="Dir 1.1";
        String dir2="Dir 1.2";
        String ciud="Ciudad 1";
        String estado="Estado 1";
        String cp="CP 1";
        
        returnConsumidor.setId(id);
		returnConsumidor.setNombre(nombre);
		returnConsumidor.setApellidos(apell);
		returnConsumidor.setCorreo(corre);
		returnConsumidor.setTelefono(tel);
        returnConsumidor.setBillingAddress(new Address());
		returnConsumidor.getBillingAddress().setAddressLine1(dir);
        returnConsumidor.getBillingAddress().setAddressLine2(dir2);
        returnConsumidor.getBillingAddress().setCity(ciud);
        returnConsumidor.getBillingAddress().setState(estado);
        returnConsumidor.getBillingAddress().setZipCode(cp);

        when(consumidorService.guardarActualizar(Matchers.<Consumidor>any())).thenReturn(returnConsumidor);

        mockMvc.perform(post("/consumidores/consumidor")
                .param("id", "1")
                .param("nombre", nombre)
                .param("apellidos", apell)
                .param("correo", corre)
                .param("telefono", tel)
                .param("shippingAddress.addressLine1", dir)
                .param("shippingAddress.addressLine2", dir2)
                .param("shippingAddress.city", ciud)
                .param("shippingAddress.state", estado)
                .param("shippingAddress.zipCode", cp))
                        .andExpect(status().is3xxRedirection())
                        .andExpect(view().name("redirect:/consumidores/consumidor/1"))
                        .andExpect(model().attribute("consumidor", instanceOf(Consumidor.class)))
                        .andExpect(model().attribute("consumidor", hasProperty("nombre", is(nombre))))
                        .andExpect(model().attribute("consumidor", hasProperty("apellidos", is(apell))))
                        .andExpect(model().attribute("consumidor", hasProperty("correo", is(corre))))
                        .andExpect(model().attribute("consumidor", hasProperty("telefono", is(tel))))
                        .andExpect(model().attribute("consumidor", hasProperty("shippingAddress", hasProperty("addressLine1", is(dir)))))
                        .andExpect(model().attribute("consumidor", hasProperty("shippingAddress", hasProperty("addressLine2", is(dir2)))))
                        .andExpect(model().attribute("consumidor", hasProperty("shippingAddress", hasProperty("city", is(ciud)))))
                        .andExpect(model().attribute("consumidor", hasProperty("shippingAddress", hasProperty("state", is(estado)))))
                        .andExpect(model().attribute("consumidor", hasProperty("shippingAddress", hasProperty("zipCode", is(cp)))));
        
        ArgumentCaptor<Consumidor> boundConsumidor = ArgumentCaptor.forClass(Consumidor.class);
        verify(consumidorService).guardarActualizar(boundConsumidor.capture());

        assertEquals(id, boundConsumidor.getValue().getId());
        assertEquals(nombre, boundConsumidor.getValue().getNombre());
        assertEquals(apell, boundConsumidor.getValue().getApellidos());
        assertEquals(corre, boundConsumidor.getValue().getCorreo());
        assertEquals(tel, boundConsumidor.getValue().getTelefono());
        assertEquals(dir, boundConsumidor.getValue().getShippingAddress().getAddressLine1());
        assertEquals(dir2, boundConsumidor.getValue().getShippingAddress().getAddressLine2());
        assertEquals(ciud, boundConsumidor.getValue().getShippingAddress().getCity());
        assertEquals(estado, boundConsumidor.getValue().getShippingAddress().getState());
        assertEquals(cp, boundConsumidor.getValue().getShippingAddress().getZipCode());
    }

    @Test
    public void testDelete() throws Exception{
        Integer id = 1;

        mockMvc.perform(get("/consumidores/consumidor/borrar/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/consumidores/lista"));

        verify(consumidorService, times(1)).borrar(id);
    }
}
