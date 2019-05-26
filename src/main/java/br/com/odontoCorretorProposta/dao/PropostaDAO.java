package br.com.odontoCorretorProposta.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.odontoCorretorProposta.convert.CalendarToString;
import br.com.odontoCorretorProposta.entity.Proposta;
import br.com.odontoCorretorProposta.interfaces.IPropostaDAO;

@Repository
@Transactional
public class PropostaDAO implements IPropostaDAO {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private CalendarToString calendarToString;
	
	private int corretorDe;
	private int corretorAte;
	private int produtoDe;
	private int produtoAte;
	
	@Override
	public void incluir(Proposta proposta) {
		manager.persist(proposta);
	}
	

	@Override
	public List<Proposta> listar(Proposta proposta) {
		
		preparaRanges(proposta);
		
		List<Proposta> listarRetorno;
		
		listarRetorno = manager.createQuery("select a from Proposta a "
				+ " where a.corretor.id  >= :corretorDe"
				+ " and a.corretor.id    <= :corretorAte"
				+ " and a.produto.codigo >= :produtoDe"
				+ " and a.produto.codigo <= :produtoAte"
				+ " and a.nomeSegurado like :nomeSegurado"
				+ " and date(a.dataAssinatura) >= :dataAssinaturaDe"
				+ " and date(a.dataAssinatura) <= :dataAssinaturaAte",
				Proposta.class)
				.setParameter("corretorDe", this.corretorDe)
				.setParameter("corretorAte",this.corretorAte)
				.setParameter("produtoDe", this.produtoDe)
				.setParameter("produtoAte", this.produtoAte)
				.setParameter("nomeSegurado", "%" + proposta.getNomeSegurado() + "%")
				.setParameter("dataAssinaturaDe", proposta.getDataAssinaturaDe().getTime())
				.setParameter("dataAssinaturaAte", proposta.getDataAssinaturaAte().getTime())
				.getResultList();
		
		listarRetorno = calendarToString.converteLista(listarRetorno, "dd/MM/yyyy");
		
		return calendarToString.converteLista(listarRetorno, "dd/MM/yyyy");
	}


	private void preparaRanges(Proposta proposta) {
		
		if (proposta.getCorretor().getId() == 0) {
			this.corretorDe  = Integer.MIN_VALUE;
			this.corretorAte = Integer.MAX_VALUE;
		} else {
			this.corretorDe = proposta.getCorretor().getId();
			this.corretorAte = proposta.getCorretor().getId();
		}
		
		if (proposta.getProduto().getCodigo() == 0) {
			this.produtoDe  = Integer.MIN_VALUE;
			this.produtoAte = Integer.MAX_VALUE;
		} else {
			this.produtoDe  = proposta.getProduto().getCodigo();
			this.produtoAte = proposta.getProduto().getCodigo();
		}

		
	}


	@Override
	public Proposta detalhar(int idProposta) {
        
		Proposta propostaDetalhe = new Proposta();
		propostaDetalhe =  manager.createQuery("select a from Proposta a "
				+ " where a.numeroProposta  = :numeroProposta ", Proposta.class)
				.setParameter("numeroProposta", idProposta)
				.getSingleResult();
		
		propostaDetalhe.setDataAssinaturaString(calendarToString.converteData(propostaDetalhe.getDataAssinatura(), "dd/MM/yyyy"));
		return propostaDetalhe;

	 }


	@Override
	public void excluir(int idProposta) {

		Proposta propostaGerenciada = manager.find(Proposta.class, idProposta);
		manager.remove(propostaGerenciada);
		
	}

	@Override
	public void alterar(Proposta proposta) {
			
		Proposta propostaGerenciada = manager.find(Proposta.class, proposta.getNumeroProposta());
		manager.merge(propostaGerenciada);
		propostaGerenciada.setCorretor(proposta.getCorretor());
		propostaGerenciada.setProduto(proposta.getProduto());
		propostaGerenciada.setNomeSegurado(proposta.getNomeSegurado());
		propostaGerenciada.setDataAssinatura(proposta.getDataAssinatura());
				
		
	}
	
	    
}
