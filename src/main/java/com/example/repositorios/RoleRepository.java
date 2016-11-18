package com.example.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.example.dominio.seguridad.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

}
