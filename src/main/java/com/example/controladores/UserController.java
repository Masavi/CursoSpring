package com.example.controladores;

import com.example.dominio.User;
import com.example.servicios.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jt on 12/17/15.
 */
@RequestMapping("/usuarios")
@Controller
public class UserController {

    private UserService usuariosService;

    @Autowired
    public void setUserService(UserService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @RequestMapping({"/lista", "/"})
    public String listUsers(Model model){
        model.addAttribute("users", usuariosService.listarTodos());
        return "usuarios/lista";
    }

    @RequestMapping("/usuario/{id}")
    public String getUser(@PathVariable Integer id, Model model){
        model.addAttribute("user", usuariosService.obtenerPorId(id));
        return "usuarios/usuario";
    }

    @RequestMapping("usuario/editar/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("user", usuariosService.obtenerPorId(id));
        return "usuarios/usuarioform";
    }

    @RequestMapping("/usuario/nuevo")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        return "usuarios/usuariosform";
    }

    @RequestMapping(value = "/usuario", method = RequestMethod.POST)
    public String saveOrUpdate(User usuarios){
        User savedUser = usuariosService.guardarActualizar(usuarios);
        return "redirect:/usuarios/usuario/" + savedUser.getId();
    }

    @RequestMapping("/usuario/borrar/{id}")
    public String delete(@PathVariable Integer id){
        usuariosService.borrar(id);
        return "redirect:/usuarios/";
    }
}
