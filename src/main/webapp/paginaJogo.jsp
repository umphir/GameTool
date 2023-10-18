<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<title>Feedback Jogos</title>
<link rel="stylesheet" href="./resources/css">
<style><jsp:include page="./resources/css/paginaJogo.css"></jsp:include></style>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<jsp:include page="head.jsp"></jsp:include>
</head>
<body>
	
	<jsp:include page="./navbar.jsp"></jsp:include>
	
	<main class="container-fluid">
	<div class="background-image"></div>
	
	<div class="row mb-2">

	<h1 class="text-white mt-4 mb-4">${jogo.nomeJogo}</h1>
	

	<div class="card mb-3 posteres col-md-9 mx-auto bg-dark text-white bg-opacity-75"> 
  		<img src="${jogo.img_slide}" class="card-img-top poster mt-3" alt="..."> 
  		<div class="card-body">
    		<h5 class="card-title">Descrição</h5>
    		<p class="card-text">${jogo.jogo_descricao}</p> 
    		<small class="text-white">${jogo.empresa.nomeEmpresa}</small>
 		 </div>
	</div>
	
	
    </div>

	<form action="feedback" method="POST" class="col-md-9 mx-auto ">
		<div class="input-group justify-content-center">
			<input type="hidden" name="idJogo" value="${jogo.idJogo}">
			<input type="hidden" name="acao" value="adicionar">
			<input type="hidden" name="idUser" value="${idLogin}">
  			<input class="form-control me-2" type="text" name="descricao" placeholder="Escreva a sua opinião..." aria-label="Escreva a sua opinião...">
  			<select class="me-2" name="nota" id="nota" required>
					<option value="" selected disabled>Selecione uma nota</option>
		            <option value="1">1</option>
		            <option value="2">2</option>
		            <option value="3">3</option>
		            <option value="4">4</option>
		            <option value="5">5</option>
		            <option value="6">6</option>
		            <option value="7">7</option>
		            <option value="8">8</option>
		            <option value="9">9</option>
		            <option value="10">10</option>
			</select>
			<div class="	">
  				<button type="reset" class="btn btn-danger" type="button">Limpar</button>
  				<button type="submit" class="btn btn-success" type="button">Enviar</button>
  			</div>
		</div>
	</form>

	

	<c:forEach items="${listaFeedback}" var="feedback">
            
            <div class="card bg-dark mb-4 mt-4 col-md-9 mx-auto">
					<div class="card-header text-white">${feedback.usuario.nome}</div> 
					<div class="card-body">
						
						<p class="cart-text text-white">Nota: ${feedback.nota}</p>
						<p class="cart-text text-white">Comentário: ${feedback.descricao}</p>

				
					

            <c:if test="${feedback.usuario.id == idLogin}">
                <button type="button" class="btn btn-danger btn-xs" data-bs-toggle="modal" data-bs-target="#excluirModal" onclick="idExcluir.value = ${feedback.idFeedback}">Excluir</button>
            </c:if>
					</div>
				</div>

</c:forEach> 


	


	

				<div class="modal fade" id="excluirModal" tabindex="-1" role="dialog"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Confirmar</h5>
							</div>
							<div class="modal-body">Deseja realmente excluir o feedback?</div>
							<div class="modal-footer">
								<form action="feedback" method="post">
									<input type="hidden" name="acao" value="excluir"> 
									<input type="hidden" name="idJogo" value="${jogo.idJogo}">
									<input type="hidden" name="idFeedback" id="idExcluir">
									<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
									<button type="submit" class="btn btn-danger">Excluir</button>
								</form>
							</div>
						</div>
					</div>
				</div>
				

	
	</main>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>