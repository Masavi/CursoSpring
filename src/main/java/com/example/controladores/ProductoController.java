package com.example.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.dominio.Producto;
import com.example.servicios.ProductoService;

import com.example.comandos.ProductoForm;

import com.example.convertidores.ProductoAProductoForm;

@Controller
public class ProductoController {
	private ProductoService productoService;
	
    private ProductoAProductoForm productToProductForm;
    
    @Autowired
	public void setProductToProductForm(ProductoAProductoForm productToProductForm) {
		this.productToProductForm = productToProductForm;
	}


	@Autowired
	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}


	@RequestMapping("/productos")
	public String listaProductos(Model model){
		model.addAttribute("productos", productoService.listarTodos());
		
		return "productos/lista";
	}
	
	@RequestMapping("/producto/{id}")
	public String getProducto(@PathVariable Integer id,Model model){
		model.addAttribute("producto", productoService.obtenerPorId(id));
		
		return "productos/producto";
	}
	
	@RequestMapping("/producto/nuevo")
	public String nuevoProducto(Model model){
		model.addAttribute("productoForm", new ProductoForm());
		
		return "productos/productoform";
	}
	
	@RequestMapping("/producto/editar/{id}")
	public String editarProducto(@PathVariable Integer id, Model model){
		Producto product = productoService.obtenerPorId(id);
        ProductoForm productForm = productToProductForm.convert(product);

        model.addAttribute("productoForm", productForm);
		return "productos/productoform";
	}
	
	@RequestMapping("/producto/eliminar/{id}")
	public String borrar(@PathVariable Integer id){
		productoService.borrar(id);
		return "redirect:/productos";
	}
	
	@RequestMapping(value = "/producto", method = RequestMethod.POST)
	public String guardarActualizarProducto(@Valid ProductoForm productoForm, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()){
            return "productos/productoform";
        }

		Producto productoGuardado = productoService.guardarActualizarProductoForm(productoForm);
		
		return "redirect:/producto/" + productoGuardado.getId();
	}
}
