package br.com.odontoCorretorProposta.interfaces;

import java.util.List;

import br.com.odontoCorretorProposta.entity.Proposta;

public interface IPropostaDAO {
	
	public void incluir(Proposta proposta);
	
	public List<Proposta> listar(Proposta proposta);
	
	public Proposta detalhar(int idProposta);
	
	public void excluir(int idProposta);
	
	public void alterar(Proposta proposta);

}
