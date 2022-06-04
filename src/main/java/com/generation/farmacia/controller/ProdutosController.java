package com.generation.farmacia.controller;

import java.math.BigDecimal;
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

import com.generation.farmacia.model.Produtos;
import com.generation.farmacia.repository.ProdutosRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class ProdutosController {
	
	@Autowired
	public ProdutosRepository produtosRepository;

	@GetMapping
	public ResponseEntity <List<Produtos>> getAll(){
		return ResponseEntity.ok(produtosRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produtos> getById (@PathVariable Long id) {
		return produtosRepository.findById(id)
				.map(answer -> ResponseEntity.ok().body(answer))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity <List<Produtos>> getByNome (@PathVariable String nome) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(produtosRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@GetMapping("/nomeANDlaboratorio/{nome}/{laboratorio}")
	public ResponseEntity<List<Produtos>> getByNomeANDLaboratorio (@PathVariable String nome, @PathVariable String laboratorio) {
		return ResponseEntity.ok(produtosRepository.findByNomeAndLaboratorio(nome, laboratorio));
	}
	
	@GetMapping("/nomeORlaboratorio/{nome}/{laboratorio}")
	public ResponseEntity<List<Produtos>> getByNomeORLaboratorio (@PathVariable String nome, @PathVariable String laboratorio) {
		return ResponseEntity.ok(produtosRepository.findByNomeOrLaboratorio(nome, laboratorio));
	}
	
	@GetMapping("/precoBetween/{preco1}/{preco2}")
	public ResponseEntity<List<Produtos>> getByBetweenPrecos (@PathVariable BigDecimal preco1, @PathVariable BigDecimal preco2) {
		return ResponseEntity.ok(produtosRepository.findByPrecoBetween(preco1, preco2));
	}
	
	@PostMapping
	public ResponseEntity<Produtos>post(@Valid @RequestBody Produtos produtos){
		return ResponseEntity.status(HttpStatus.CREATED).body(produtosRepository.save(produtos));
	}
	
	@PutMapping
	public ResponseEntity<Produtos> put (@RequestBody Produtos produtos) {
		if(!produtosRepository.existsById(produtos.getId()) || produtos.getId() == null) {
			ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(produtos);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Produtos> delete (@PathVariable Long id) {
		if (id == null || !produtosRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		produtosRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
