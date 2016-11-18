package com.example.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.example.dominio.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Integer> {

}
