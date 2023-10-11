package com.EGGNews.EGGNEWS.servicios;

import com.EGGNews.EGGNEWS.entidades.Noticia;
import com.EGGNews.EGGNEWS.repositorios.NoticiaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoticiaServicio {


    @Autowired
    private NoticiaRepositorio NR;


    public List<Noticia> consultaNoticias() {
        return NR.findAll();
    }

    @Transactional
    public void crearNoticia(String titulo, String cuerpo) {
        Noticia N = new Noticia();
        System.out.println(N.getId());
        N.setTitulo(titulo);
        N.setCuerpo(cuerpo);
        N.setAlta(true);
        NR.save(N);
    }

    @Transactional
    public void modificarNoticia(Integer id, String titulo, String cuerpo) {

        Optional<Noticia> resp = NR.findById(id);
        if (resp.isPresent()) {
            Noticia Nm = new Noticia();
            Nm = resp.get();
            Nm.setTitulo(titulo);
            Nm.setCuerpo(cuerpo);
            NR.save(Nm);
        }
    }

    @Transactional
    public void cambiarEstado(Integer id) {
        Optional<Noticia> resp = NR.findById(id);
        if (resp.isPresent()) {
            Noticia N = resp.get();
            N.setAlta(!N.isAlta());
        }
    }

}
