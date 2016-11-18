package com.example.servicios.serviciosrepo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.dominio.seguridad.Role;
import com.example.repositorios.RoleRepository;
import com.example.servicios.RoleService;


@Service
@Profile("springdatajpa")
public class RoleServiceRepoImpl implements RoleService {

	RoleRepository roleRepository;
	
	@Autowired
	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public List<?> listarTodos() {
		List<Role> productos = new ArrayList<>();
		roleRepository.findAll().forEach(productos::add);
		return productos;
	}

	@Override
	public Role obtenerPorId(Integer id) {
		return roleRepository.findOne(id);
	}

	@Override
	public Role guardarActualizar(Role domainObject) {
		return roleRepository.save(domainObject);
	}

	@Override
	public void borrar(Integer id) {
		roleRepository.delete(id);
	}
}
