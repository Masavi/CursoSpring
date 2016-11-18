package com.example.servicios;


import com.example.dominio.Consumidor;

import com.example.comandos.ConsumidorForm;

public interface ConsumidorService extends CRUDService<Consumidor>{

    Consumidor guardarActualizarConsumidorForm(ConsumidorForm customerForm);
}
