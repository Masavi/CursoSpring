package com.example.servicios;

import java.util.List;

public interface CRUDService<T> {
	List<?> listarTodos();

    T obtenerPorId(Integer id);

    T guardarActualizar(T domainObject);

    void borrar(Integer id);
}
