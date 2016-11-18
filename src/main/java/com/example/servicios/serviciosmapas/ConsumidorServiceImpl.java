package com.example.servicios.serviciosmapas;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.dominio.Consumidor;
import com.example.dominio.ObjetoDominio;
import com.example.servicios.ConsumidorService;

import com.example.comandos.ConsumidorForm;
import com.example.convertidores.ConsumidorFormAConsumidor;

@Service
@Profile("map")
public class ConsumidorServiceImpl extends MapaAbstractoService implements ConsumidorService {
	
    private ConsumidorFormAConsumidor customerFormToConsumidor;
	
    @Autowired
	public void setConsumidorFormAConsumidor(ConsumidorFormAConsumidor customerFormToConsumidor) {
		this.customerFormToConsumidor = customerFormToConsumidor;
	}

	@Override
	public List<ObjetoDominio> listarTodos() {
		return super.listarTodos();
	}

	@Override
	public Consumidor obtenerPorId(Integer id) {
		return (Consumidor)super.obtenerPorId(id);
	}

	@Override
	public Consumidor guardarActualizar(Consumidor consumidor) {
		return (Consumidor) super.guardarActualizar(consumidor);
	}

	@Override
	public void borrar(Integer id) {
		super.borrar(id);
	}
	
	@Override
    public Consumidor guardarActualizarConsumidorForm(ConsumidorForm customerForm) {
        Consumidor newConsumidor = customerFormToConsumidor.convert(customerForm);

        if(newConsumidor.getUser().getId() != null){
            Consumidor existingConsumidor = obtenerPorId(newConsumidor.getId());

            newConsumidor.getUser().setEnabled(existingConsumidor.getUser().getEnabled());
        }

        return guardarActualizar(newConsumidor);
    }
	
}
