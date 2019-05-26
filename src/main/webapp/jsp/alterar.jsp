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


<title>Alterar Proposta</title>
</head>


<body>
	
	<div class="mx-3">
		<h3>Altera Proposta</h3>
	 	<form action=confirmarAlteracao method="post">
			
		    <table class="input-group">
			        <tr>
						<td><label>Corretor (Nome / CPF): </label></td>
						<td><select class="custom-select" name="corretor.id">
						    <option value=0>Favor Selecionar Corretor</option> 
						    
						    
							<c:forEach var="corretor" items="${corretores}">  
				    			<option value="${corretor.id}" <c:if test="${corretor.id==corretorSelecionadoId}">selected</c:if>>
				    			     ${corretor.nome} - ${corretor.cpfCnpj}
				    			</option>  
				  			</c:forEach> 
			  			</select></td>
			  		    <td><input name=numeroProposta value="${proposta.numeroProposta}" class="oculto"></input></td>
			  			
			  		</tr>
			  			
			  		<tr>
						<td><label>Produto (Codigo / Descriçao):</label> <br /></td>
						<td><select class="custom-select" name="produto.codigo">
						    <option value=0>Favor Selecionar Produto</option> 
							<c:forEach var="produto" items="${produtos}">  
				    			<option value="${produto.codigo}" <c:if test="${produto.codigo==produtoSelecionadoCodigo}">selected</c:if>>
				    			     ${produto.codigo} - ${produto.descricao}
				    			</option>  
				  			</c:forEach> 
			  			</select></td>
			  		</tr>	  		

		  			  		
		 			<tr>
				 		<td><label>Nome Segurado: </label></td>
				 		<td><input name="nomeSegurado" class="form-control" value="${nomeSegurado}"></input></td>
				 	<tr>

	 		  

		 		    <tr>
				 		<td><label>Data de Assinatura: </label></td>
				 		<td><input class="form-control" placeholder="DD/MM/AAAA" name="dataAssinatura"
				 		    value="${dataAssinatura}"></input></td>
				 	</tr>
			 		
	 			</table>
	 			
	 			<br/>
	 			<div class="alert-danger">
				    <c:forEach var="error" items="${errors}" >
				        <li>${error}</li>
				    </c:forEach>
                </div>
	 			<div class="alert-info">
				    <c:forEach var="msg" items="${msgs}" >
				        <li>${msg}</li>
				    </c:forEach>
                </div>
	 		
	 		<br/>
	 		 <input type="button" value="Voltar" onclick="window.location.href = 'inicio'">
	 		 <input type="submit" value="Confirma Alteração">
	 	 </form>	
	

    </div>
    
</body>
   
</html>