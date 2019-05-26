<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src='resources/js/jquery-3.4.1.min.js' type='text/javascript'></script>
<script src='resources/js/inicio.js' type='text/javascript'></script> 
<link type='text/css' href='resources/style/bootstrap.min.css' rel='stylesheet'>
<link type='text/css' href='resources/style/master.css' rel='stylesheet'>


<title>Inicio</title>
</head>


<body>
	
	<div class="mx-3">
		<h3>Consultar Proposta</h3>
	 	<form action="consultar" method="post">
			
		    <table class="input-group">
			        <tr>
						<td><label>Corretor (Nome / CPF): </label></td>
						<td><select class="custom-select" name="corretor.id">
						    <option value=0>Todos</option>  
							<c:forEach var="corretor" items="${corretores}">  
				    			<option value="${corretor.id}" <c:if test="${corretor.id==corretorSelecionadoId}">selected</c:if>>
				    			     ${corretor.nome} - ${corretor.cpfCnpj}
				    			</option>  
				  			</c:forEach> 
			  			</select></td>
			  		</tr>
			  			
			  		<tr>
						<td><label>Produto (Codigo / Descriçao):</label> <br /></td>
						<td><select class="custom-select" name="produto.codigo">
						    <option value=0>Todos</option> 
							<c:forEach var="produto" items="${produtos}">  
				    			<option value="${produto.codigo}" <c:if test="${produto.codigo==produtoSelecionadoCodigo}">selected</c:if>>
				    			     ${produto.codigo} - ${produto.descricao}
				    			</option>  
				  			</c:forEach> 
			  			</select></td>
			  		</tr>	  		
 		
		 			<tr>
				 		<td><label>Nome Segurado: </label></td>
				 		<td><input class="form-control" name="nomeSegurado" value="${nomeSegurado}"></input></td>
				 	<tr>

		 		    <tr>
				 		<td><label>Data de Assinatura (De): </label></td>
				 		<td><input class="form-control" placeholder="DD/MM/AAAA" name="dataAssinaturaDe"
				 		     value="${dataAssinaturaDe}"></input></td>
			 		
				 		<td><label>Até: </label><br/></td>
				 		<td><input class="form-control" placeholder="DD/MM/AAAA" name="dataAssinaturaAte"
				 		    value="${dataAssinaturaAte}"></input></td>
				 	</tr>
			 		
	 			</table>
	 		
	 		<br/>
	 		<input type="submit" value="Consultar">
            <input type="button" value="Nova Proposta" onclick="window.location.href = 'novaProposta'">
            
            <div class="alert-info">
			    <c:forEach var="msg" items="${msgs}" >
			        <li>${msg}</li>
			    </c:forEach>
            </div>
                
	 	 </form>	
	 	 <br/>
	 	 <form>
	 	 <div>
	 	     <table class="table">
		            <thead>
			            <tr>
						    <th>Número Proposta</th>
						    <th>Corretor (Nome / CPF):</th>
						    <th>Produto (Cod / Desc)</th>
						    <th>Nome Segurado</th>
						    <th>Data de Assinatura</th>
						    <th>Alterar</th>
						    <th>Excluir</th>
			            </tr>  
		            </thead>
		            <c:forEach items="${propostas}" var="proposta"> 
		            <tbody>	               
			            <tr>
			                <td>${proposta.numeroProposta}</td>
			                <td>${proposta.corretor.nome} - ${proposta.corretor.cpfCnpj}</td>
			                <td>${proposta.produto.codigo} - ${proposta.produto.descricao} </td>
			                <td>${proposta.nomeSegurado}</td>    
			                <td>${proposta.dataAssinaturaString}</td>
			                <td><a href="alterar?id=${proposta.numeroProposta}">Alterar</a></td>
			                <td><a href="excluir?id=${proposta.numeroProposta}">Excluir</a></td>              
			            </tr>
		            </tbody>	
		        </c:forEach>
	        </table>
	        
	 	</div>
	 	</form>
 	

    </div>
    
</body>
   
</html>