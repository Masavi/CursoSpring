package com.example.convertidores;

import com.example.comandos.ProductoForm;
import com.example.dominio.Producto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 12/24/15.
 */
@Component
public class ProductoAProductoForm implements Converter<Producto, ProductoForm> {
    @Override
    public ProductoForm convert(Producto product) {
        ProductoForm productForm = new ProductoForm();
        productForm.setId(product.getId());
        productForm.setVersion(product.getVersion());
        productForm.setDescription(product.getDescription());
        productForm.setPrice(product.getPrice());
        productForm.setImageUrl(product.getImageUrl());
        return productForm;
    }
}
