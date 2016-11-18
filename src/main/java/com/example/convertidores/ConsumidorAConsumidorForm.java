package com.example.convertidores;

import com.example.comandos.ConsumidorForm;
import com.example.dominio.Consumidor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 9/26/16.
 */
@Component
public class ConsumidorAConsumidorForm implements Converter<Consumidor, ConsumidorForm> {

    @Override
    public ConsumidorForm convert(Consumidor customer) {
        ConsumidorForm customerForm = new ConsumidorForm();

        customerForm.setCustomerId(customer.getId());
        customerForm.setCustomerVersion(customer.getVersion());
        customerForm.setCorreo(customer.getCorreo());
        customerForm.setNombre(customer.getNombre());
        customerForm.setApellidos(customer.getApellidos());
        customerForm.setTelefono(customer.getTelefono());
        customerForm.setUserId(customer.getUser().getId());
        customerForm.setUserName(customer.getUser().getUsername());
        customerForm.setUserVersion(customer.getUser().getVersion());

        return customerForm;
    }
}
