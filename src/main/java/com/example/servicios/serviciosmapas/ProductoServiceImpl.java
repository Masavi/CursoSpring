package com.example.servicios.serviciosmapas;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.dominio.ObjetoDominio;
import com.example.dominio.Producto;
import com.example.servicios.ProductoService;

import com.example.comandos.ProductoForm;
import com.example.convertidores.ProductoFormAProducto;

@Service
@Profile("map")
public class ProductoServiceImpl extends MapaAbstractoService implements ProductoService{
	
    private ProductoFormAProducto productFormToProduct;
    
    @Autowired
	public void setProductoFormAProducto(ProductoFormAProducto productFormToProduct) {
		this.productFormToProduct = productFormToProduct;
	}

	public List<ObjetoDominio> listarTodos() {
		return super.listarTodos();
	}

	@Override
	public Producto obtenerPorId(Integer id) {
		
		return (Producto) super.obtenerPorId(id);
	}

	@Override
	public Producto guardarActualizar(Producto producto) {
		return (Producto) super.guardarActualizar(producto);
	}
	
	@Override
    public Producto guardarActualizarProductoForm(ProductoForm productForm) {
        return guardarActualizar(productFormToProduct.convert(productForm));
    }

	@Override
	public void borrar(Integer id) {
		super.borrar(id);
	}
	
}
