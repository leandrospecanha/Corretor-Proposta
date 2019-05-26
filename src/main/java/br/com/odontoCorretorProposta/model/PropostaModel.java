package br.com.odontoCorretorProposta.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import br.com.odontoCorretorProposta.entity.Proposta;
import br.com.odontoCorretorProposta.interfaces.ICorretorDAO;
import br.com.odontoCorretorProposta.interfaces.IProdutoDAO;

@Component
public class PropostaModel {
	
	@Autowired 
	private IProdutoDAO iProdutoDAO;
	
	@Autowired 
	private ICorretorDAO iCorretorDAO;

	private Model carregaCombos(Model model) {
		model.addAttribute("corretores", iCorretorDAO.listaTodos());
		model.addAttribute("produtos", iProdutoDAO.listaTodos());
		return model;
	}
	
	public Model tratamentoInclusaoOk(Model model) {
		model = carregaCombos(model);
		model = carregaData(model);
		model.addAttribute("msgs", msgSucessoInclusao());
		return model;
	}
	
	public Model tratamentoExclusaoOk(Model model) {
		model = carregaCombos(model);
		model = carregaDatas(model);
		model.addAttribute("msgs", msgSucessoExclusao());
		return model;
	}
	
	public Model tratamentoAlteracaoOk(Model model) {
		model = carregaCombos(model);
		model = carregaDatas(model);
		model.addAttribute("msgs", msgSucessoAlteracao());
		return model;
	}	

	
	public Model cargaInicialConsulta(Model model) {
		model = carregaCombos(model);
		model = carregaDatas(model);
		return model;
	}
	
	private Model carregaDatas(Model model) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendarDe = Calendar.getInstance();
		calendarDe.add(Calendar.MONTH, -1);
		
		Calendar calendarAte = Calendar.getInstance();

		model.addAttribute("dataAssinaturaDe", dateFormat.format(calendarDe.getTime()));
		model.addAttribute("dataAssinaturaAte", dateFormat.format(calendarAte.getTime()));

        return model;
	}

	private Model carregaData(Model model) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		
		model.addAttribute("dataAssinatura", dateFormat.format(calendar.getTime()));
        return model;
	}
	
	
	public Model tratamentoErroInlusaoAlteracao(Model model, Proposta proposta, List<String> errosValidacao) {
		
		model.addAttribute("corretores", iCorretorDAO.listaTodos());
		model.addAttribute("corretorSelecionadoId", proposta.getCorretor().getId());
		model.addAttribute("produtos", iProdutoDAO.listaTodos());
		
		model.addAttribute("produtoSelecionadoCodigo", proposta.getProduto().getCodigo());
		model.addAttribute("nomeSegurado", proposta.getNomeSegurado());
		
		if (proposta.getDataAssinatura() != null) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateFormat.format(proposta.getDataAssinatura().getTime());
			model.addAttribute("dataAssinatura", dateFormat.format(proposta.getDataAssinatura().getTime()));
		}
			
		model.addAttribute("errors", errosValidacao);
		return model;
	}

	public Model remontarTelaAlteracao(Model model, Proposta proposta) {
		
		model.addAttribute("corretores", iCorretorDAO.listaTodos());
		model.addAttribute("corretorSelecionadoId", proposta.getCorretor().getId());
		model.addAttribute("produtos", iProdutoDAO.listaTodos());
		
		model.addAttribute("produtoSelecionadoCodigo", proposta.getProduto().getCodigo());
		model.addAttribute("nomeSegurado", proposta.getNomeSegurado());
		
		if (proposta.getDataAssinatura() != null) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateFormat.format(proposta.getDataAssinatura().getTime());
			model.addAttribute("dataAssinatura", dateFormat.format(proposta.getDataAssinatura().getTime()));
		}
		
		return model;
	}	
	
	public List<String> validaInclusao(Proposta proposta) {
	    
		List<String> repostas = new ArrayList<String>();
		
		if (proposta.getCorretor() == null
		||  proposta.getCorretor().getId() == 0) {	
			repostas.add("Favor selecionar Corretor");
		}
		
		if (proposta.getProduto() == null
		||  proposta.getProduto().getCodigo() == 0) {	
			repostas.add("Favor selecionar Produto");
		}	
		
		if (proposta.getNomeSegurado() == null
		||  proposta.getNomeSegurado().length() < 5) {	
			repostas.add("Nome do segurado deve possuir pelo menos 5 caracteres");
		}
		
		if (proposta.getDataAssinatura() == null) {
			repostas.add("Preencher corretamente data de assinatura");
		}
		
		return repostas; 
	}
	
	public List<String> validaAlteracao(Proposta proposta) {
	    
		List<String> repostas = new ArrayList<String>();
		
		if (proposta.getCorretor() == null
		||  proposta.getCorretor().getId() == 0) {	
			repostas.add("Favor Selecionar Corretor");
		}
		
		if (proposta.getProduto() == null
		||  proposta.getProduto().getCodigo() == 0) {	
			repostas.add("Favor selecionar produto");
		}	
		
		if (proposta.getNomeSegurado() == null
		||  proposta.getNomeSegurado().length() < 5) {	
			repostas.add("Nome do segurado deve possuir pelo menos 5 caracteres");
		}
		
		if (proposta.getDataAssinatura() == null) {
			repostas.add("Preencher corretamente data de assinatura");
		}
		
		if (proposta.getNumeroProposta() == 0) {	
			repostas.add("Identificador nao foi carregado");
		}
		
		return repostas; 
	}

	public IProdutoDAO getiProdutoDAO() {
		return iProdutoDAO;
	}

	public void setiProdutoDAO(IProdutoDAO iProdutoDAO) {
		this.iProdutoDAO = iProdutoDAO;
	}

	public ICorretorDAO getiCorretorDAO() {
		return iCorretorDAO;
	}

	public void setiCorretorDAO(ICorretorDAO iCorretorDAO) {
		this.iCorretorDAO = iCorretorDAO;
	}
	
	public List<String> msgSucessoInclusao() {
		List<String> msgs = new ArrayList<String>();
		msgs.add("Registro Incluído Com Sucesso");
		return msgs;
	}

	public List<String> msgSucessoExclusao() {
		List<String> msgs = new ArrayList<String>();
		msgs.add("Registro Excluído Com Sucesso");
		return msgs;
	}
	
	public List<String> msgSucessoAlteracao() {
		List<String> msgs = new ArrayList<String>();
		msgs.add("Registro Alterado Com Sucesso");
		return msgs;
	}

	public List<String> msgRegistroNaoEncontrado() {
		List<String> msgs = new ArrayList<String>();
		msgs.add("Nenhum Registro Encontrado");
		return msgs;
	}
	
	public Model tratamentoConsultaOk(Model model, Proposta proposta) {
		
		model.addAttribute("corretores", iCorretorDAO.listaTodos());
		model.addAttribute("corretorSelecionadoId", proposta.getCorretor().getId());
		
		model.addAttribute("produtos", iProdutoDAO.listaTodos());
		model.addAttribute("produtoSelecionadoCodigo", proposta.getProduto().getCodigo());
		
		model.addAttribute("nomeSegurado", proposta.getNomeSegurado());
		
		if (proposta.getDataAssinaturaDe() != null) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateFormat.format(proposta.getDataAssinaturaDe().getTime());
			model.addAttribute("dataAssinaturaDe", dateFormat.format(proposta.getDataAssinaturaDe().getTime()));
		}
		if (proposta.getDataAssinaturaAte() != null) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateFormat.format(proposta.getDataAssinaturaAte().getTime());
			model.addAttribute("dataAssinaturaAte", dateFormat.format(proposta.getDataAssinaturaAte().getTime()));
		}
	    
		return model;
	}

	public Model cargaInicialInclusao(Model model) {

		model = carregaCombos(model);
		model = carregaData(model);
		
		return model;
	}


}
