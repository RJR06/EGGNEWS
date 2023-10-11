package com.EGGNews.EGGNEWS.repositorios;

import com.EGGNews.EGGNEWS.entidades.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepositorio extends JpaRepository <Noticia, Integer> {
    @Query("SELECT n FROM Noticia n WHERE n.titulo = :aux")
    Noticia buscarNoticiaPorTitulo(@Param("aux") String texto);
}
