package com.example.bootstrap;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.example.dominio.Consumidor;
import com.example.dominio.Producto;
import com.example.servicios.ConsumidorService;
import com.example.servicios.ProductoService;

import com.example.servicios.RoleService;
import com.example.servicios.UserService;

import com.example.dominio.Address;

import com.example.dominio.Cart;
import com.example.dominio.CartDetail;
import com.example.dominio.Order;
import com.example.dominio.OrderDetail;
import com.example.enums.OrderStatus;

import com.example.dominio.User;
import com.example.dominio.seguridad.Role;

@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent>{
	private ProductoService productoService;
	private UserService userService;
    private RoleService roleService;
    
    @Autowired
    public void setProductoService(ProductoService productoService) {
        this.productoService = productoService;
    }
    
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		loadProductos();
        loadUsuariosConsumidores();
        loadCarts();
        loadOrderHistory();
        loadRoles();
        assignUsersToDefaultRole();
	}
	
	private void assignUsersToDefaultRole() {
        List<Role> roles = (List<Role>) roleService.listarTodos();
        List<User> users = (List<User>) userService.listarTodos();

        roles.forEach(role ->{
            if(role.getRole().equalsIgnoreCase("CUSTOMER")){
                users.forEach(user -> {
                    user.addRole(role);
                    userService.guardarActualizar(user);
                });
            }
        });
    }
	
	private void assignUsersToAdminRole() {
        List<Role> roles = (List<Role>) roleService.listarTodos();
        List<User> users = (List<User>) userService.listarTodos();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("ADMIN")) {
                users.forEach(user -> {
                    if (user.getUsername().equals("fglenanne")) {
                        user.addRole(role);
                        userService.guardarActualizar(user);
                    }
                });
            }
        });
    }
	
    private void loadRoles() {
    	Role role = new Role();
        role.setRole("CUSTOMER");
        roleService.guardarActualizar(role);

        Role adminRole = new Role();
        adminRole.setRole("ADMIN");
        roleService.guardarActualizar(adminRole);
    }

    private void loadOrderHistory() {
        List<User> users = (List<User>) userService.listarTodos();
        List<Producto> products = (List<Producto>) productoService.listarTodos();

        users.forEach(user ->{
            Order order = new Order();
            order.setConsumidor(user.getConsumidor());
            order.setOrderStatus(OrderStatus.SHIPPED);

            products.forEach(product -> {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(product);
                orderDetail.setQuantity(1);
                order.addToOrderDetails(orderDetail);
            });
        });
    }

    private void loadCarts() {
        List<User> users = (List<User>) userService.listarTodos();
        List<Producto> products = (List<Producto>) productoService.listarTodos();

        users.forEach(user -> {
            user.setCart(new Cart());
            CartDetail cartDetail = new CartDetail();
            cartDetail.setProduct(products.get(0));
            cartDetail.setQuantity(2);
            user.getCart().addCartDetail(cartDetail);
            userService.guardarActualizar(user);
        });
    }

	
	public void loadProductos(){
		Producto producto1 = new Producto();
        producto1.setDescription("producto 1");
        producto1.setPrice(new BigDecimal("12.99"));
        producto1.setImageUrl("http://example.com/producto1");
        productoService.guardarActualizar(producto1);

        Producto producto2 = new Producto();
        producto2.setDescription("producto 2");
        producto2.setPrice(new BigDecimal("14.99"));
        producto2.setImageUrl("http://example.com/producto2");
        productoService.guardarActualizar(producto2);

        Producto producto3 = new Producto();
        producto3.setDescription("producto 3");
        producto3.setPrice(new BigDecimal("34.99"));
        producto3.setImageUrl("http://example.com/producto3");
        productoService.guardarActualizar(producto3);

        Producto producto4 = new Producto();
        producto4.setDescription("producto 4");
        producto4.setPrice(new BigDecimal("44.99"));
        producto4.setImageUrl("http://example.com/producto4");
        productoService.guardarActualizar(producto4);

        Producto producto5 = new Producto();
        producto5.setDescription("producto 5");
        producto5.setPrice(new BigDecimal("25.99"));
        producto5.setImageUrl("http://example.com/producto5");
        productoService.guardarActualizar(producto5);
	}
	
    public void loadUsuariosConsumidores() {
    	User user1 = new User();
        user1.setUsername("mweston");
        user1.setPassword("password");
        
        Consumidor customer1 = new Consumidor();
        customer1.setNombre("Micheal");
        customer1.setApellidos("Weston");
        customer1.setBillingAddress(new Address());
        customer1.getBillingAddress().setAddressLine1("1 Main St");
        customer1.getBillingAddress().setCity("Miami");
        customer1.getBillingAddress().setState("Florida");
        customer1.getBillingAddress().setZipCode("33101");
        customer1.setCorreo("micheal@burnnotice.com");
        customer1.setTelefono("305.333.0101");
        user1.setConsumidor(customer1);
        userService.guardarActualizar(user1);
        
        
        User user2 = new User();
        user2.setUsername("fglenanne");
        user2.setPassword("password");
        Consumidor customer2 = new Consumidor();
        customer2.setNombre("Fiona");
        customer2.setApellidos("Glenanne");
        customer1.getBillingAddress().setAddressLine1("1 Main St");
        customer1.getBillingAddress().setCity("Miami");
        customer1.getBillingAddress().setState("Florida");
        customer1.getBillingAddress().setZipCode("33101");
        customer2.setCorreo("fiona@burnnotice.com");
        customer2.setTelefono("305.323.0233");
        user2.setConsumidor(customer2);
        userService.guardarActualizar(user2);
        
        User user3 = new User();
        user3.setUsername("saxe");
        user3.setPassword("password");
        Consumidor customer3 = new Consumidor();
        customer3.setNombre("Sam");
        customer3.setApellidos("Axe");
        customer1.getBillingAddress().setAddressLine1("1 Main St");
        customer1.getBillingAddress().setCity("Miami");
        customer1.getBillingAddress().setState("Florida");
        customer1.getBillingAddress().setZipCode("33101");
        customer3.setCorreo("sam@burnnotice.com");
        customer3.setTelefono("305.426.9832");
        user3.setConsumidor(customer3);
        userService.guardarActualizar(user3);
    }
}
