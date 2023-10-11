package com.EGGNews.EGGNEWS.controladores;

import com.EGGNews.EGGNEWS.entidades.Noticia;
import com.EGGNews.EGGNEWS.servicios.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Source;
import java.util.List;

@Controller
@RequestMapping("/Noticia")
public class NoticiaControler {
    @Autowired
    NoticiaServicio Ns;

    @PostMapping("/panel-admin")
    public String crearNoticia(@RequestParam String titulo, @RequestParam String cuerpo) throws Exception {
        try {
            Ns.crearNoticia(titulo, cuerpo);
            System.out.println("Noticia CARGADA CORRECTAMENTE");
            return "panelAdmin.html";
        } catch (Exception e) {
            System.out.println("Noticia NO CARGADA");
            e.getStackTrace();
            return "panelAdmin.html";
        }

    }

    @GetMapping("/panel-admin")
    public String mostrarPanel() {
        return "panelAdmin.html";
    }

 /*   @GetMapping("/noticias")
     public ResponseEntity<List<Noticia>> obtenerNoticias(){
        List<Noticia> noticias = Ns.consultaNoticias();
        return ResponseEntity.ok(noticias);
    }
    @PostMapping("/noticias")
    public ResponseEntity<Noticia> crearNoticia(@RequestBody Noticia n) {
        Ns.crearNoticia(n.getId() ,n.getTitulo(), n.getCuerpo());
        return ResponseEntity.ok(n);
    }*/
}
