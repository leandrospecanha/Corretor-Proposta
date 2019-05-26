package br.com.odontoCorretorProposta.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.odontoCorretorProposta.entity.Corretor;
import br.com.odontoCorretorProposta.interfaces.ICorretorDAO;

@Repository
@Transactional
public class CorretorDAO implements ICorretorDAO {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Corretor> listaTodos() {

		return manager.createQuery("select a from Corretor a ORDER BY a.nome", Corretor.class).getResultList();

	}

	@Override
	public void inserir(Corretor corretor) {
		manager.persist(corretor);
	}


	@Override
	public void alterar(Corretor corretor) {

		Corretor corretorGerenciado = manager.find(Corretor.class, corretor.getId());
		manager.merge(corretorGerenciado);
		corretorGerenciado.setNome(corretor.getNome());
		corretorGerenciado.setCpfCnpj(corretor.getCpfCnpj());
	}

	@Override
	public void excluir(Corretor corretor) {
		
		Corretor corretorGerenciado = manager.find(Corretor.class, corretor.getId());
		manager.remove(corretorGerenciado);

		
	}
}
