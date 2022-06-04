package com.generation.farmacia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.farmacia.model.Categorias;
import com.generation.farmacia.repository.CategoriasRepository;


@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriasController {
	
	@Autowired
	public CategoriasRepository categoriasRepository;
	
	@GetMapping
	public ResponseEntity<List<Categorias>> getAll () {
		return ResponseEntity.ok(categoriasRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categorias> getById (@PathVariable Long id) {
		return categoriasRepository.findById(id).map(answer -> ResponseEntity.ok().body(answer))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Categorias>post(@Valid @RequestBody Categorias categorias){
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriasRepository.save(categorias));
	}
	
	@PutMapping
	public ResponseEntity<Categorias> put (@RequestBody Categorias categorias) {
		if (!categoriasRepository.existsById(categorias.getId()) || categorias.getId() == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		return ResponseEntity.status(HttpStatus.CREATED).body(categorias);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Categorias> delete (@PathVariable Long id) {
		if (!categoriasRepository.existsById(id) || id == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		categoriasRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
