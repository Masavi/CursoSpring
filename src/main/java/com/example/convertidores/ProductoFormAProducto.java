package com.example.convertidores;

import com.example.comandos.ProductoForm;
import com.example.dominio.Producto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 12/24/15.
 */
@Component
public class ProductoFormAProducto implements Converter<ProductoForm, Producto>{

    @Override
    public Producto convert(ProductoForm productForm) {
        Producto product = new Producto();
        product.setId(productForm.getId());
        product.setVersion(productForm.getVersion());
        product.setDescription(productForm.getDescription());
        product.setPrice(productForm.getPrice());
        product.setImageUrl(productForm.getImageUrl());
        return product;
    }
}
