package com.example.servicios.serviciosrepo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.dominio.User;
import com.example.repositorios.UserRepository;
import com.example.servicios.UserService;
import com.example.servicios.seguridad.EncryptionService;
import com.example.repositorios.ConsumidorRepository;

@Service
@Profile("springdatajpa")
public class UserServiceRepoImpl implements UserService {


	private UserRepository userRepository;
    private ConsumidorRepository customerRepository;
    private EncryptionService servicioEncriptacion;
	
    @Autowired
	public void setServicioEncriptacion(EncryptionService servicioEncriptacion) {
		this.servicioEncriptacion = servicioEncriptacion;
	}

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
		if(domainObject.getPassword() != null){
			domainObject.setEncryptedPassword(servicioEncriptacion.encryptString(domainObject.getPassword()));
		}
		return userRepository.save(domainObject);
	}

	@Override
	public void borrar(Integer id) {
		userRepository.delete(id);
	}
	
	@Override
    public User findByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

}
