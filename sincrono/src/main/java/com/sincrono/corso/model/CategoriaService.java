package com.sincrono.corso.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoriaService extends JpaRepository<Categoria, CategoriaPK> {

}
