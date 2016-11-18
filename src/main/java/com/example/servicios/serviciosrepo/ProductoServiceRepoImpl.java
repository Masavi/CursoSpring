package com.example.servicios.serviciosrepo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.dominio.Producto;
import com.example.repositorios.ProductoRepository;
import com.example.servicios.ProductoService;

import com.example.comandos.ProductoForm;
import com.example.convertidores.ProductoFormAProducto;

@Service
@Profile({"springdatajpa","jpadao"})
public class ProductoServiceRepoImpl implements ProductoService {

	private ProductoRepository productoRepository;
    private ProductoFormAProducto productFormToProduct;
	
    @Autowired
	public void setProductFormToProduct(ProductoFormAProducto productFormToProduct) {
		this.productFormToProduct = productFormToProduct;
	}

	@Autowired
	public void setProductoRepository(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}

	@Override
	public List<?> listarTodos() {
		List<Producto> productos = new ArrayList<>();
		productoRepository.findAll().forEach(productos::add);
		return productos;
	}

	@Override
	public Producto obtenerPorId(Integer id) {
		return productoRepository.findOne(id);
	}

	@Override
	public Producto guardarActualizar(Producto domainObject) {
		return productoRepository.save(domainObject);
	}

	@Override
	public void borrar(Integer id) {
		productoRepository.delete(id);
	}

	@Override
    public Producto guardarActualizarProductoForm(ProductoForm productForm) {
        return guardarActualizar(productFormToProduct.convert(productForm));
    }

	
}
