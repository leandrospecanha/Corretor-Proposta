package br.com.odontoCorretorProposta.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.odontoCorretorProposta.entity.Produto;
import br.com.odontoCorretorProposta.interfaces.IProdutoDAO;

@Repository
@Transactional
public class ProdutoDAO implements IProdutoDAO{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Produto> listaTodos() {
		return manager.createQuery("select a from Produto a", Produto.class).getResultList();

	}

}
