package com.example.servicios.serviciosjpa;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.dominio.Producto;
import com.example.servicios.ProductoService;

import com.example.comandos.ProductoForm;
import com.example.convertidores.ProductoFormAProducto;


@Service
@Profile("jpadao-dontuse")
public class ProductServiceJpaDaoImpl extends AbstractJpaDaoService implements ProductoService{
    private ProductoFormAProducto productFormToProduct;

    @Autowired
    public void setProductFormToProduct(ProductoFormAProducto productFormToProduct) {
		this.productFormToProduct = productFormToProduct;
	}

	@Override
	public List<Producto> listarTodos() {
		EntityManager em= emf.createEntityManager();
		
		return em.createQuery("from Producto",Producto.class).getResultList();
	}

	@Override
	public Producto obtenerPorId(Integer id) {
		EntityManager em= emf.createEntityManager();
		return em.find(Producto.class, id);
	}

	@Override
	public Producto guardarActualizar(Producto domainObject) {
		EntityManager em= emf.createEntityManager();
		
		em.getTransaction().begin();
		Producto productoGuardado=em.merge(domainObject);
		em.getTransaction().commit();
		return productoGuardado;
	}

	@Override
	public void borrar(Integer id) {
		EntityManager em= emf.createEntityManager();
		
		em.getTransaction().begin();
		em.remove(em.find(Producto.class, id));
		em.getTransaction().commit();
	}
	
	@Override
    public Producto guardarActualizarProductoForm(ProductoForm productForm) {
        return guardarActualizar(productFormToProduct.convert(productForm));
    }


}
