package com.example.convertidores;

import com.example.comandos.ConsumidorForm;
import com.example.dominio.Address;
import com.example.dominio.Consumidor;
import com.example.dominio.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 12/22/15.
 */
@Component
public class ConsumidorFormAConsumidor implements Converter<ConsumidorForm, Consumidor> {

    @Override
    public Consumidor convert(ConsumidorForm customerForm) {

        Consumidor customer = new Consumidor();
        customer.setUser(new User());
        customer.setBillingAddress(new Address());
        customer.setShippingAddress(new Address());
        customer.getUser().setId(customerForm.getUserId());
        customer.getUser().setVersion(customerForm.getUserVersion());
        customer.setId(customerForm.getCustomerId());
        customer.setVersion(customerForm.getCustomerVersion());
        customer.getUser().setUsername(customerForm.getUserName());
        customer.getUser().setPassword(customerForm.getPasswordText());
        customer.setNombre(customerForm.getNombre());
        customer.setApellidos(customerForm.getApellidos());
        customer.setCorreo(customerForm.getCorreo());
        customer.setTelefono(customerForm.getTelefono());

        return customer;
    }
}
