package com.example.servicios.serviciosrepo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.dominio.Order;
import com.example.repositorios.OrderRepository;
import com.example.servicios.OrderService;

@Service
@Profile("springdatajpa")
public class OrderServiceRepoImpl implements OrderService {

	OrderRepository orderRepository;
	
	@Autowired
	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public List<?> listarTodos() {
		List<Order> productos = new ArrayList<>();
		orderRepository.findAll().forEach(productos::add);
		return productos;
	}

	@Override
	public Order obtenerPorId(Integer id) {
		return orderRepository.findOne(id);
	}

	@Override
	public Order guardarActualizar(Order domainObject) {
		return orderRepository.save(domainObject);
	}

	@Override
	public void borrar(Integer id) {
		orderRepository.delete(id);
	}

}
