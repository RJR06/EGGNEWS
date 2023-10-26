package com.EGGNews.EGGNEWS.controladores;

import com.EGGNews.EGGNEWS.excepciones.Exception1;
import com.EGGNews.EGGNEWS.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalControlador {
    @Autowired
    UsuarioServicio US;

    @GetMapping("/")
    public String inicio() {
        return "index.html";
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "registro.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombreUsuario, @RequestParam String password, String password2, ModelMap mod) {

        try {
            US.registrar(nombreUsuario, password, password2);
            mod.put("exito", "Usuario registrado correctamente.");
            return "index.html";

        } catch (Exception1 e) {
            mod.put("error", e.getMessage());
            mod.put("nombreUsuario", nombreUsuario);
            return "registro.html";
        }

    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap mod) {
        if(error != null){
            mod.put("error","Usuario o contraseña incorrectos");
        }
        return "login.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/inicio")
    public String logeado(){

        return "panelAdmin-modificarNoticia.html";
    }
}
