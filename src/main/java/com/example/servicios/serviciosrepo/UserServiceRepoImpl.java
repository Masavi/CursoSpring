package com.example.servicios.serviciosrepo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.dominio.User;
import com.example.repositorios.UserRepository;
import com.example.servicios.UserService;

@Service
@Profile("springdatajpa")
public class UserServiceRepoImpl implements UserService {


	UserRepository userRepository;
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<?> listarTodos() {
		List<User> productos = new ArrayList<>();
		userRepository.findAll().forEach(productos::add);
		return productos;
	}

	@Override
	public User obtenerPorId(Integer id) {
		return userRepository.findOne(id);
	}

	@Override
	public User guardarActualizar(User domainObject) {
		return userRepository.save(domainObject);
	}

	@Override
	public void borrar(Integer id) {
		userRepository.delete(id);
	}

}
