package com.EGGNews.EGGNEWS.repositorios;

import com.EGGNews.EGGNEWS.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodistaRepositorio extends JpaRepository<Usuario, Long> {
}
