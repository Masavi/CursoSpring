package com.example.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.dominio.Consumidor;
import com.example.servicios.ConsumidorService;

import com.example.comandos.ConsumidorForm;

import com.example.convertidores.ConsumidorAConsumidorForm;

@RequestMapping("/consumidores")
@Controller
public class ConsumidorController {
	private ConsumidorService consumidorService;
	private Validator customerFormValidator;
    private ConsumidorAConsumidorForm customerToCustomerForm;
	
    @Autowired
    @Qualifier("consumidorFormValidator")

    public void setCustomerFormValidator(Validator customerFormValidator) {
		this.customerFormValidator = customerFormValidator;
	}

    @Autowired
	public void setCustomerToCustomerForm(ConsumidorAConsumidorForm customerToCustomerForm) {
		this.customerToCustomerForm = customerToCustomerForm;
	}

	@Autowired
	public void setConsumidorService(ConsumidorService consumidorService) {
		this.consumidorService = consumidorService;
	}

	@RequestMapping({"/lista","/"})
	public String listarTodosLosconsumidors(Model model){
		
		model.addAttribute("consumidores",consumidorService.listarTodos());	
		
		return "consumidores/lista";
	}
	
	@RequestMapping("/consumidor/{id}")
	public String getProducto(@PathVariable Integer id, Model model){
		
		model.addAttribute("consumidor", consumidorService.obtenerPorId(id));
		
		return "consumidores/consumidor";
	}
	
	@RequestMapping("consumidor/editar/{id}")
	public String editar(@PathVariable Integer id, Model model){
		
		Consumidor customer = consumidorService.obtenerPorId(id);

        model.addAttribute("consumidorForm", customerToCustomerForm.convert(customer));
		
		return "consumidores/consumidorform";
	}
	
	@RequestMapping("/consumidor/nuevo")
	public String nuevoProducto(Model model){
		model.addAttribute("consumidorForm", new ConsumidorForm());
		return "consumidores/consumidorform";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String guardarActualizarconsumidor(@Valid ConsumidorForm consumidorForm, BindingResult bindingResult){
		customerFormValidator.validate(consumidorForm, bindingResult);

        if(bindingResult.hasErrors()){
            return "consumidores/consumidorform";
        }

        Consumidor newCustomer = consumidorService.guardarActualizarConsumidorForm(consumidorForm);
		return "redirect:/consumidores/consumidor/" + newCustomer.getId();
	}
	
	@RequestMapping("/consumidor/borrar/{id}")
	public String borrar(@PathVariable Integer id){
		consumidorService.borrar(id);
		
		return "redirect:/consumidores/lista";
	}
}
