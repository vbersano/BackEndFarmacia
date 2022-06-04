package com.generation.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.farmacia.model.Categorias;
import com.generation.farmacia.model.Produtos;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Long> {
	
	public List<Produtos> findAllByTipoContainingIgnoreCase (@Param ("tipo") String tipo);


}
