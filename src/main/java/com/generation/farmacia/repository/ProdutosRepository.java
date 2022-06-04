package com.generation.farmacia.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.farmacia.model.Produtos;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long> {
	
	public List<Produtos> findAllById (@Param ("id") Long id);
	
	public List<Produtos> findAllByNomeContainingIgnoreCase (@Param ("nome") String nome);
	// MySQL: select * from tb_produtos where tb_produtos.preco > precoInput order by preco desc
	
	public List<Produtos> findByPrecoGreaterThanOrderByPreco(@Param ("preco") BigDecimal preco);
	// MySQL: select * from tb_produtos where tb_produtos.preco > precoInput order by preco desc
	
	public List<Produtos> findByPrecoLessThanOrderByPreco(@Param ("preco") BigDecimal preco);
	// MySQL: select * from tb_produtos where tb_produtos.preco < precoInput order by preco desc
	
	public List<Produtos> findByNomeAndLaboratorio(@Param ("nome") String nome, @Param ("laboratorio") String laboratorio);
	// MySQL: select * from tb_produtos where tb_produtos.nome == nomeInput AND tb_produtos.laboratorio == laboratorioInput
	
	public List<Produtos> findByNomeOrLaboratorio(@Param ("nome") String nome, @Param ("laboratorio") String laboratorio);
	// MySQL: select * from tb_produtos where tb_produtos.nome == nomeInput OR tb_produtos.laboratorio == laboratorioInput
	
	public List <Produtos> findByPrecoBetween (BigDecimal preco1,BigDecimal  preco2);
	//MySQL: select * from tb_produtos where tb_produtos.preco1 BETWEEN tb_produtos.preco2

}
