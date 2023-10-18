<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<title>Meus Feedbacks</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
	<style><jsp:include page="./resources/css/meusFeedbacks.css"></jsp:include></style>
	

<jsp:include page="head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="./navbar.jsp"></jsp:include>

	<main class="container-fluid">

		<div class="background-image"></div>




		<c:forEach items="${meusFeedbacks}" var="feedback">
			<div>

				<div class="card bg-dark mb-4 mt-4 col-md-9 mx-auto">
					<div class="card-header text-white">${feedback.usuario.nome}</div> 
					<div class="card-body">
						<h5 class="card-title text-white">${feedback.jogo.nomeJogo}</h5>
						<p class="card-text text-white">Nota: ${feedback.nota}</p>
						<p class="cart-text text-white">Comentário: ${feedback.descricao}</p>

				<button type="button" class="btn btn-primary btn-xs"
					data-bs-toggle="modal" data-bs-target="#fb-${feedback.idFeedback}">Editar</button>
				<button type="button" class="btn btn-danger btn-xs"
					data-bs-toggle="modal" data-bs-target="#excluirModal"
					onclick="idExcluir.value = ${feedback.idFeedback}">Excluir</button>
					
					</div>
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
							<input type="hidden" name="acao" value="excluir"> <input
								type="hidden" name="idJogo" value="0"> <input
								type="hidden" name="idLogin" value="${idLogin}"> <input
								type="hidden" name="idFeedback" value="${feedback.idFeedback}"
								id="idExcluir">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Cancelar</button>
							<button type="submit" class="btn btn-danger">Excluir</button>
						</form>
					</div>
				</div>
			</div>
		</div>



		<c:forEach items="${meusFeedbacks}" var="feedback">

			<div class="modal fade" id="fb-${feedback.idFeedback}" tabindex="-1"
				aria-labelledby="fb-${feedback.idFeedback}" aria-hidden="true">
				<div class="modal-dialog modal-xl">
					<form action="./feedback" method="POST">
						<div class="modal-content">
							<div class="modal-header d-flex">
								<select name="nota" id="nota" required>
									<option value="1" ${feedback.nota == '1' ? 'selected' : ''}>1</option>
									<option value="2" ${feedback.nota == '2' ? 'selected' : ''}>2</option>
									<option value="3" ${feedback.nota == '3' ? 'selected' : ''}>3</option>
									<option value="4" ${feedback.nota == '4' ? 'selected' : ''}>4</option>
									<option value="5" ${feedback.nota == '5' ? 'selected' : ''}>5</option>
									<option value="6" ${feedback.nota == '6' ? 'selected' : ''}>6</option>
									<option value="7" ${feedback.nota == '7' ? 'selected' : ''}>7</option>
									<option value="8" ${feedback.nota == '8' ? 'selected' : ''}>8</option>
									<option value="9" ${feedback.nota == '9' ? 'selected' : ''}>9</option>
									<option value="10" ${feedback.nota == '10' ? 'selected' : ''}>10</option>
								</select>
								<div class="d-flex justify-content-between">
									<h5 class="modal-title me-3 fw-light">${feedback.usuario.nome}</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
							</div>

							<div class="modal-body">
								<textarea name="descricao" class="form-control" rows="" cols="">${feedback.descricao}</textarea>
								<input type="hidden" name="idFeedback"
									value="${feedback.idFeedback}"> <input type="hidden"
									name="acao" value="editar"> <input type="hidden"
									name="idJogo" value="${feedback.jogo.idJogo}"> <input
									type="hidden" name="idLogin" value="${idLogin}">

							</div>

							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">Cancelar</button>
								<button type="submit" class="btn btn-primary">Salvar
									alterações</button>
							</div>

						</div>
					</form>

				</div>
			</div>
		</c:forEach>
	</main>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>