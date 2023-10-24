package com.EGGNews.EGGNEWS.servicios;

import com.EGGNews.EGGNEWS.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {
    @Autowired
    UsuarioRepositorio UR;

}
