<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src='resources/js/jquery-3.4.1.min.js' type='text/javascript'></script>
<script src='resources/js/inicio.js' type='text/javascript'></script> 
<link type='text/css' href='resources/style/bootstrap.min.css' rel='stylesheet'>
<link type='text/css' href='resources/style/master.css' rel='stylesheet'>


<title>Excluir Proposta</title>
</head>


<body>
	
	<div class="mx-3">
		<h3>Exclusao Proposta</h3>
	 	<form action="confirmaExclusao" method="post">
			
		    <table class="confirmaExclusao">
			  		
			  		<tr>
				 		<td>Corretor (Nome / CPF):</td>
				 		<td>${proposta.corretor.nome} - ${proposta.corretor.cpfCnpj}</td>
				 		<td><input name=propostaId value="${proposta.numeroProposta}" class="oculto"></input></td>
				 		
				 	<tr>  	
			  		
			  	    <tr>
				 		<td>Produto (Codigo / Descriçao):</td>
				 		<td>${proposta.produto.codigo} - ${proposta.produto.descricao}</td>
				 	<tr>  		
				 			
		 			<tr>
				 		<td>Nome Segurado:</td>
				 		<td>${proposta.nomeSegurado}</td>
				 	<tr>

		 		    <tr>
				 		<td>Data de Assinatura:</td>
				 		<td>${proposta.dataAssinaturaString}</td>
				 	 <tr>

			 		
	 			</table>
	
	 		
	 		<br/>
	 		 <input type="button" value="Voltar" onclick="window.location.href = 'inicio'">
	 		 <input type="submit" value="Confirma Exclusao">
	 	 </form>	
	

    </div>
    
</body>
   
</html>