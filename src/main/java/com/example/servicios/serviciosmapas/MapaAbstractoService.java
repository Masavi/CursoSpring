package com.example.servicios.serviciosmapas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.dominio.ObjetoDominio;

public abstract class MapaAbstractoService {
	protected Map<Integer, ObjetoDominio> mapaDominio;

    public MapaAbstractoService() {
        mapaDominio = new HashMap<>();
    }

    public List<ObjetoDominio> listarTodos() {
        return new ArrayList<>(mapaDominio.values());
    }

    public ObjetoDominio obtenerPorId(Integer id) {
        return mapaDominio.get(id);
    }

    public ObjetoDominio guardarActualizar(ObjetoDominio ObjetoDominio) {
        if (ObjetoDominio != null){

            if (ObjetoDominio.getId() == null){
                ObjetoDominio.setId(obtenerLlaveSiguiente());
            }
            mapaDominio.put(ObjetoDominio.getId(), ObjetoDominio);

            return ObjetoDominio;
        } else {
            throw new RuntimeException("Object Can't be null");
        }
    }

    public void borrar(Integer id) {
        mapaDominio.remove(id);
    }

    private Integer obtenerLlaveSiguiente(){
        return Collections.max(mapaDominio.keySet()) + 1;
    }

}
