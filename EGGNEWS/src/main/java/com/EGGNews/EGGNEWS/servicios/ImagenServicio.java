package com.EGGNews.EGGNEWS.servicios;

import com.EGGNews.EGGNEWS.entidades.Imagen;
import com.EGGNews.EGGNEWS.excepciones.Exception1;
import com.EGGNews.EGGNEWS.repositorios.ImagenRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;


@Service
public class ImagenServicio {
    @Autowired
    private ImagenRepositorio IMR;

    public Imagen guardar(MultipartFile archivo) throws Exception1 {
        if (archivo != null) {
            try {
                Imagen imagen = new Imagen();
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());

                return IMR.save(imagen);

            }catch (Exception e){
                System.err.println(e.getMessage());
            }

        }
        return null;
    }

    public Imagen actualizar(MultipartFile archivo, Integer idImagen) throws Exception1{
        if (archivo != null) {
            try {
                Imagen imagen = new Imagen();

                if(idImagen != null){
                    Optional<Imagen> respuesta = IMR.findById(idImagen);

                    if(respuesta.isPresent()){
                        imagen = respuesta.get();
                    }
                 }
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());

                return IMR.save(imagen);

            }catch (Exception e){
                System.err.println(e.getMessage());
            }

        }  return null;
    }
}
