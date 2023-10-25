package com.EGGNews.EGGNEWS.repositorios;

import com.EGGNews.EGGNEWS.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository <Usuario, Long>  {

    @Query("SELECT u from Usuario u WHERE u.nombreUsuario = :aux")
    public Usuario buscarPorNombreUsuario(@Param ("aux")String nombreUsuario);



}
