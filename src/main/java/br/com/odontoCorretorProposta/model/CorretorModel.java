package br.com.odontoCorretorProposta.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.odontoCorretorProposta.entity.Corretor;

@Component
public class CorretorModel {

	public List<String> validaInclusao(Corretor corretor) {
		
		 List<String> retorno = new ArrayList<String>();
		 
		 if (corretor == null) {
			 retorno.add("Conteudo de entrada nulo");
			 
		 }else {
			 
			 if (corretor.getNome().isEmpty()
		     ||  corretor.getNome().length() < 5) {
				 retorno.add("Nome do corretor deve possuir pelo menos 5 caracteres");
		     }
			 
			 if (corretor.getCpfCnpj() <= 10) {
				retorno.add("Cpf/CNPJ deve possuir pelo menos 10 caracteres");
			 }	 
       }
		 return retorno;
   }
	
	public List<String> validaAlteracao(Corretor corretor) {
		
		 List<String> retorno = new ArrayList<String>();
		 
		 if (corretor == null) {
			 retorno.add("Conteudo de entrada nulo");
			 
		 }else {
			 
			 if (corretor.getId() == 0) {
			     retorno.add("Campo Identificador e obrigatorio");
			 }
			 
			 if (corretor.getNome().isEmpty()
		     ||  corretor.getNome().length() <= 5) {
				 retorno.add("Nome do corretor deve possuir pelo menos 5 posicoes");
		     }
			 
			 if (corretor.getCpfCnpj() <= 10) {
				retorno.add("Cpf/CNPJ deve possuir pelo menos 10 posicoes");
			 }	 
      }
		 return retorno;
    }
	
	public List<String> validaExclusao(Corretor corretor) {
		
		 List<String> retorno = new ArrayList<String>();
		 
		 if (corretor == null) {
			 retorno.add("Conteudo de entrada nulo");
			 
		 }else {
			 
			 
			 if (corretor.getId() == 0) {
			     retorno.add("Campo Identificador e obrigatorio");
			 }
      }
		 return retorno;
  }
}	
