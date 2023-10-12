package com.EGGNews.EGGNEWS.controladores;

import com.EGGNews.EGGNEWS.Excepciones.Exception1;
import com.EGGNews.EGGNEWS.servicios.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.SchemaOutputResolver;

@Controller
@RequestMapping("/Noticia")
public class NoticiaControler {
    @Autowired
    NoticiaServicio Ns;

    @GetMapping("/panel-admin")
    public String mostrarPanel() {
        return "panelAdmin.html";
    }
    @PostMapping("/panel-admin")
    public String crearNoticia(@RequestParam String titulo, @RequestParam String cuerpo, ModelMap mod) throws Exception1 {
        try {
            Ns.crearNoticia(titulo, cuerpo);
           mod.put("Exito", "Noticia CARGADA CORRECTAMENTE");
            System.out.println("Noticia CARGADA CORRECTAMENTE");
            return "panelAdmin.html";
        } catch (Exception1 e1) {
           mod.put("Error", e1.getMessage());
            System.out.println("Noticia NO CARGADA");

            e1.getStackTrace();
            return "panelAdmin.html";
        }

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
