package com.EGGNews.EGGNEWS.repositorios;

import com.EGGNews.EGGNEWS.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepositorio extends JpaRepository<Usuario,Long> {
}
