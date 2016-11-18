package com.example.servicios.serviciosrepo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dominio.Consumidor;
import com.example.repositorios.ConsumidorRepository;
import com.example.servicios.ConsumidorService;

import com.example.comandos.ConsumidorForm;
import com.example.convertidores.ConsumidorFormAConsumidor;
import com.example.repositorios.UserRepository;

@Service
@Profile("springdatajpa")
public class ConsumidorServiceRepoImpl implements ConsumidorService {

	ConsumidorRepository consumidorRepository;
	private UserRepository userRepository;
    private ConsumidorFormAConsumidor customerFormToConsumidor;
	
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

    @Autowired
	public void setConsumidorFormToConsumidor(ConsumidorFormAConsumidor customerFormToConsumidor) {
		this.customerFormToConsumidor = customerFormToConsumidor;
	}

	@Autowired
	public void setConsumidorRepository(ConsumidorRepository consumidorRepository) {
		this.consumidorRepository = consumidorRepository;
	}

	@Override
	public List<?> listarTodos() {
		List<Consumidor> productos = new ArrayList<>();
		consumidorRepository.findAll().forEach(productos::add);
		return productos;
	}

	@Override
	public Consumidor obtenerPorId(Integer id) {
		return consumidorRepository.findOne(id);
	}

	@Override
	public Consumidor guardarActualizar(Consumidor domainObject) {
		return consumidorRepository.save(domainObject);
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

	@Override
	@Transactional
	public void borrar(Integer id) {
		Consumidor customer = consumidorRepository.findOne(id);

        userRepository.delete(customer.getUser());
        consumidorRepository.delete(customer);
	}

}
