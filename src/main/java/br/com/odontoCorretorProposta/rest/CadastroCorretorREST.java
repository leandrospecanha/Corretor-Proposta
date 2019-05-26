package br.com.odontoCorretorProposta.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.odontoCorretorProposta.entity.Corretor;
import br.com.odontoCorretorProposta.interfaces.ICorretorDAO;
import br.com.odontoCorretorProposta.model.CorretorModel;

@RestController
public class CadastroCorretorREST {

	 @Autowired
	 public ICorretorDAO iCorretorDAO;
	 
	 @Autowired
	 public CorretorModel corretorModel;
	
	 @PostMapping("/corretor")
     public List<String> corretorPost (Corretor corretor){
         
		 List<String> mensagems = new ArrayList<String>();
		 try {
			 mensagems = corretorModel.validaInclusao(corretor);
			 if (mensagems.size() == 0) {
			 	iCorretorDAO.inserir(corretor);
				 mensagems.add("incluido com sucesso");
		 	 } 
		 } catch(Exception e) {
			 mensagems.add("Ocorreu uma exececao durante a Tentativa de Inclusao");
		 }	 
		 
		 return mensagems;

	 }
	 
	 @GetMapping("/corretor")
     public List<Corretor> corretorGet(Corretor corretor){

		 return iCorretorDAO.listaTodos();
		 
	 }
	 
	 @PutMapping("/corretor")
     public List<String> corretorPut(Corretor corretor){

		 List<String> mensagems = new ArrayList<String>();
		 try {
			 mensagems = corretorModel.validaAlteracao(corretor);
			 if (mensagems.size() == 0) {
			 	iCorretorDAO.alterar(corretor);
				 mensagems.add("alterado com sucesso");
		 	 } 
		 } catch(Exception e) {
			 mensagems.add("Ocorreu uma exececao durante a Tentativa de Alteracao");
		 }	 
		 
		 return mensagems;
	 }
	 
	 @DeleteMapping("/corretor")
     public List<String> corretorExclui (Corretor corretor){

		 List<String> mensagems = new ArrayList<String>();
		 try {
			 mensagems = corretorModel.validaExclusao(corretor);
			 if (mensagems.size() == 0) {
			 	iCorretorDAO.excluir(corretor);
				 mensagems.add("excluído com sucesso");
		 	 } 
		 } catch(Exception e) {
			 mensagems.add("Ocorreu uma exececao durante a Tentativa de Exclusao");
		 }	 
		 
		 return mensagems;
	 }	 
}
