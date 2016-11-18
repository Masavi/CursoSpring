package com.example.services;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.config.JpaIntegrationConfig;
import com.example.dominio.Producto;
import com.example.servicios.ProductoService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class ProductoServiceJpaDaoImplTest {
	private ProductoService productoService;

    @Autowired
    public void setProductoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    @Test
    public void testListar() throws Exception {

        List<Producto> Productos = (List<Producto>) productoService.listarTodos();

        assert Productos.size() == 5;

    }
}
