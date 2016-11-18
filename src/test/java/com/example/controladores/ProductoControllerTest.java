package com.example.controladores;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import com.example.servicios.ProductoService;
import com.example.dominio.Producto;

public class ProductoControllerTest {
	@Mock 
    private ProductoService productoService;

    @InjectMocks 
    private ProductoController productoController;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this); 

        mockMvc = MockMvcBuilders.standaloneSetup(productoController).build();
    }

    @Test
    public void testLista() throws Exception{

        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto());
        productos.add(new Producto());

        when(productoService.listarTodos()).thenReturn((List) productos); 

        mockMvc.perform(get("/productos"))
                .andExpect(status().isOk())
                .andExpect(view().name("productos/lista"))
                .andExpect(model().attribute("productos", hasSize(2)));
    }

    @Test
    public void testMostrar() throws Exception{
        Integer id = 1;

        when(productoService.obtenerPorId(id)).thenReturn(new Producto());

        mockMvc.perform(get("/producto/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("productos/producto"))
                .andExpect(model().attribute("producto", instanceOf(Producto.class)));
    }

    @Test
    public void testEditar() throws Exception{
        Integer id = 1;

        when(productoService.obtenerPorId(id)).thenReturn(new Producto());

        mockMvc.perform(get("/producto/editar/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("productos/productoform"))
                .andExpect(model().attribute("producto", instanceOf(Producto.class)));
    }

    @Test
    public void testNuevo() throws Exception {
        Integer id = 1;

        verifyZeroInteractions(productoService);

        mockMvc.perform(get("/producto/nuevo"))
                .andExpect(status().isOk())
                .andExpect(view().name("productos/productoform"))
                .andExpect(model().attribute("producto", instanceOf(Producto.class)));
    }

    @Test
    public void testGuardar() throws Exception {
        Integer id = 1;
        String description = "test test";
        BigDecimal price = new BigDecimal("12.00");
        String imageUrl = "example.com";

        Producto returnProducto = new Producto();
        returnProducto.setId(id);
        returnProducto.setDescription(description);
        returnProducto.setPrice(price);
        returnProducto.setImageUrl(imageUrl);

        when(productoService.guardarActualizar(Matchers.<Producto>any())).thenReturn(returnProducto);

        mockMvc.perform(post("/producto")
        .param("id", "1")
        .param("description", description)
        .param("price", "12.00")
        .param("imageUrl", "example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/producto/1"))
                .andExpect(model().attribute("producto", instanceOf(Producto.class)))
                .andExpect(model().attribute("producto", hasProperty("id", is(id))))
                .andExpect(model().attribute("producto", hasProperty("description", is(description))))
                .andExpect(model().attribute("producto", hasProperty("price", is(price))))
                .andExpect(model().attribute("producto", hasProperty("imageUrl", is(imageUrl))));

        //verify properties of bound object
        ArgumentCaptor<Producto> boundProducto = ArgumentCaptor.forClass(Producto.class);
        verify(productoService).guardarActualizar(boundProducto.capture());

        assertEquals(id, boundProducto.getValue().getId());
        assertEquals(description, boundProducto.getValue().getDescription());
        assertEquals(price, boundProducto.getValue().getPrice());
        assertEquals(imageUrl, boundProducto.getValue().getImageUrl());
    }

    @Test
    public void testDelete() throws Exception{
        Integer id = 1;

        mockMvc.perform(get("/producto/eliminar/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/productos"));

        verify(productoService, times(1)).borrar(id);
    }
}
