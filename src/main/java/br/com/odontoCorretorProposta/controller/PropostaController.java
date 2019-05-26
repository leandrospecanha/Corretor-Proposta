package br.com.odontoCorretorProposta.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.odontoCorretorProposta.entity.Proposta;
import br.com.odontoCorretorProposta.interfaces.IPropostaDAO;
import br.com.odontoCorretorProposta.model.PropostaModel;

@Controller
public class PropostaController {
	
	@Autowired
	private IPropostaDAO iPropostaDAO;
	
	@Autowired
	private PropostaModel propostaModel;
	
	@RequestMapping("inicio")
	public String inicio(Model model) {
		
		model = propostaModel.cargaInicialConsulta(model);
		
		return "consultar";
	}
	
	@RequestMapping("/novaProposta")
	public String incluir(Model model) {
		
		model = propostaModel.cargaInicialInclusao(model);
		
		return "incluir";
	}
	
	@RequestMapping("/consultar")
	public String consultar(Proposta proposta, Model model) {
		
		model = propostaModel.tratamentoConsultaOk(model, proposta);
		List<Proposta> listaPropostas = iPropostaDAO.listar(proposta);
		model.addAttribute("propostas", listaPropostas);
    
		if (listaPropostas.size() == 0) {
			model.addAttribute("msgs", propostaModel.msgRegistroNaoEncontrado());
		}
		
		return "consultar";
	}
	
	@RequestMapping("/incluir")
	public String incluir(Proposta proposta, 
		   Model model) {

		List<String> errosValidacao = propostaModel.validaInclusao(proposta);		
		if (errosValidacao.size() > 0) {
			model = propostaModel.tratamentoErroInlusaoAlteracao(model, proposta, errosValidacao);
			return "incluir";
		}
		
		iPropostaDAO.incluir(proposta);
		
		model = propostaModel.tratamentoInclusaoOk(model);
		
		return "incluir";
	}

	@RequestMapping("/alterar")
	public String alterar(Integer id, Model model) {
		
		Proposta proposta = iPropostaDAO.detalhar(id);
		
		model = propostaModel.remontarTelaAlteracao(model, proposta);
		
		model.addAttribute("proposta", proposta);
		
		return "alterar";
	}	
	
	@RequestMapping("/confirmarAlteracao")
	public String confirmarAlteracao(Proposta proposta, Model model) {
		
		List<String> errosValidacao = propostaModel.validaAlteracao(proposta);		
		if (errosValidacao.size() > 0) {
			model = propostaModel.tratamentoErroInlusaoAlteracao(model, proposta, errosValidacao);
			return "alterar";
		}
		
		iPropostaDAO.alterar(proposta);
		
		model = propostaModel.tratamentoAlteracaoOk(model);

		return "consultar";
	}	
	
	@RequestMapping("/excluir")
	public String excluir(Integer id , Model model) {
		
		model.addAttribute("proposta", iPropostaDAO.detalhar(id));
		
		return "excluir";
	}
	
	@RequestMapping("/confirmaExclusao")
	public String confirmarExclusao(Integer propostaId, Model model) {
		
		iPropostaDAO.excluir(propostaId);
		
		model = propostaModel.tratamentoExclusaoOk(model);

		return "consultar";
	}
	

}
