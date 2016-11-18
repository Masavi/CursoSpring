package com.example.servicios;

import com.example.dominio.Producto;

import com.example.comandos.ProductoForm;

public interface ProductoService extends CRUDService<Producto>{
    Producto guardarActualizarProductoForm(ProductoForm productForm);
}
