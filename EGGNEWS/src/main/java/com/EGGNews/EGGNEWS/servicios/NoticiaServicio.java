package com.EGGNews.EGGNEWS.servicios;

import com.EGGNews.EGGNEWS.Excepciones.Exception1;
import com.EGGNews.EGGNEWS.entidades.Noticia;
import com.EGGNews.EGGNEWS.repositorios.NoticiaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class NoticiaServicio {


    @Autowired
    NoticiaRepositorio NR;

    public List<Noticia> consultaNoticias() {

        return NR.findAll();
    }

    @Transactional
    public void crearNoticia(String titulo, String cuerpo) throws Exception1 {
        validar(titulo, cuerpo);
        Noticia N = new Noticia();
        System.out.println(N.getId());
        N.setTitulo(titulo);
        N.setCuerpo(cuerpo);
        N.setAlta(true);
        NR.save(N);
    }

    @Transactional
    public void modificarNoticia(Integer id, String titulo, String cuerpo) throws Exception1 {

        Optional<Noticia> resp = NR.findById(id);
        if (resp.isPresent()) {
            Noticia Nm = new Noticia();
            Nm = resp.get();
            Nm.setTitulo(titulo);
            Nm.setCuerpo(cuerpo);
            NR.save(Nm);
        }
    }

    public Noticia mostrarporID(Integer id) {
        return NR.getOne(id);
    }

    @Transactional
    public void cambiarEstado(Integer id) {
        Optional<Noticia> resp = NR.findById(id);
        if (resp.isPresent()) {
            Noticia N = resp.get();
            N.setAlta(!N.isAlta());
        }
    }

    private void validar(String titulo, String cuerpo) throws Exception1 {
        if (titulo.isEmpty() || titulo == null) {
            throw new Exception1("El titulo no puede estar vacio o nulo");
        }
        if (cuerpo.isEmpty() || cuerpo == null) {
            throw new Exception1("El cuerpo no puede estar vacio o nulo");
        }
    }

}
