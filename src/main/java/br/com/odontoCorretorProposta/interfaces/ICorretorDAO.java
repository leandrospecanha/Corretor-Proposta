package br.com.odontoCorretorProposta.interfaces;

import java.util.List;

import br.com.odontoCorretorProposta.entity.Corretor;

public interface ICorretorDAO {
	
	public List<Corretor> listaTodos();
	
	public void inserir(Corretor corretor);

	public void alterar(Corretor corretor);
	
	public void excluir(Corretor corretor);


}
