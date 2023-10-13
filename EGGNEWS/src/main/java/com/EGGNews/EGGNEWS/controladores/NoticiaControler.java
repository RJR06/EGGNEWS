package com.EGGNews.EGGNEWS.controladores;

import com.EGGNews.EGGNEWS.Excepciones.Exception1;
import com.EGGNews.EGGNEWS.entidades.Noticia;
import com.EGGNews.EGGNEWS.servicios.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.SchemaOutputResolver;
import java.util.List;

@Controller
@RequestMapping("/Noticia")
public class NoticiaControler {
    @Autowired
    NoticiaServicio Ns;

    @GetMapping("/crear-noticia")
    public String mostrarPanel() {

        return "panelAdmin-crearnoticia.html";
    }

    @PostMapping("/noticiacreada")
    public String crearNoticia(@RequestParam String titulo, @RequestParam String cuerpo, ModelMap mod) throws Exception1 {
        try {
            Ns.crearNoticia(titulo, cuerpo);
            mod.put("Exito", "Noticia CARGADA CORRECTAMENTE");
            System.out.println("Noticia CARGADA CORRECTAMENTE");
            return "panelAdmin-crearnoticia.html";
        } catch (Exception1 e1) {
            mod.put("Error", e1.getMessage());
            System.out.println("Noticia NO CARGADA");

            e1.getStackTrace();
            return "panelAdmin-crearnoticia.html";
        }
    }

    @GetMapping("/modificar-noticias")
    public String modificar(ModelMap mod1) {
        List<Noticia> NoticiasCargadas = Ns.consultaNoticias();
        mod1.put("Allnoticias", NoticiasCargadas);
        return "panelAdmin-modificarNoticia.html";
    }

    @GetMapping("/modificando/{id}")
    public String modificando(@PathVariable Integer id, ModelMap mod2) {
        mod2.put("noticiaID", Ns.mostrarporID(id));
        return "panelAdmin-modificandoNoticia.html";
    }


    @PostMapping("/noticiamodificada")
    public String modificado(@RequestParam Integer id, @RequestParam String titulo, @RequestParam String cuerpo, ModelMap mod) throws Exception1 {
        try {
            Ns.modificarNoticia(id, titulo, cuerpo);
            mod.put("Exito", "Noticia CARGADA CORRECTAMENTE");
            System.out.println("Noticia CARGADA CORRECTAMENTE");
            return "panelAdmin-modificandoNoticia.html";
        } catch (Exception1 e1) {
            mod.put("Error", e1.getMessage());
            System.out.println("Noticia NO CARGADA");

            e1.getStackTrace();
            return "panelAdmin-modificandoNoticia.html";
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
