package com.example.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.example.dominio.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {

}
