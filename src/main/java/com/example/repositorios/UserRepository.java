package com.example.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.example.dominio.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
