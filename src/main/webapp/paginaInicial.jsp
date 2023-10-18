<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<title>Início - Jogos</title>
<style><jsp:include page="./resources/css/paginaInicial.css"></jsp:include></style>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<jsp:include page="head.jsp"></jsp:include>
</head>

<body>

	<jsp:include page="./navbar.jsp"></jsp:include>

	<main class="container-fluid">
	<div class="background-image"></div>

		<div class="row mb-2 justify-content-center mt-4">
			<div class="col-10">

				<div id="carouselExampleDark"
					class="carousel carpusel-dark slide posteres"
					data-bs-ride="carousel">
					<div class="carousel-inner">
						<c:set var="firstItem" value="true" />
						<c:forEach items="${listaJogo}" var="jogo">
							<div class="carousel-item poster ${firstItem ? 'active' : ''}">
								<img src="${jogo.img_slide}" class="d-block w-100"
									alt="slide ${jogo.nomeJogo}">
								<div class="carousel-caption d-none d-md-block">
									<h5 class="badge bg-primary text-wrap">${jogo.nomeJogo}</h5>
									<p class="badge bg-primary text-wrap">${jogo.empresa.nomeEmpresa}</p>
								</div>
							</div>
							<c:set var="firstItem" value="false" />
						</c:forEach>
					</div>

					<button class="carousel-control-prev" type="button"
						data-bs-target="#carouselExampleDark" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#carouselExampleDark" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>
			</div>


		
			<div class="col-10 mt-4">

				<div class="list-group">
					<c:forEach items="${listaFeedback}" var="feedback">
						<a href="#" class="list-group-item list-group-item-action list-group-item-dark bg-dark"
							aria-current="true">
							<div class="d-flex w-100 justify-content-between">
								<h5 class="mb-1 text-white">${feedback.getJogo().getNomeJogo()}</h5>
								<small class="text-white">${feedback.getUsuario().getNome()}</small>
							</div>
							<p class="mb-1 text-white">${feedback.getDescricao()}</p> <small class="text-white">${feedback.getJogo().getCategoria().getNome()}</small>
						</a>
					</c:forEach>

				</div>
			</div>

		</div>

		<div class="d-flex justify-content-center">
			<a class="btn btn-outline-warning mt-3 mb-3" href="jogo?acao=listar">Visualizar Jogos</a>
		</div>
		
	</main>


	<jsp:include page="footer.jsp"></jsp:include>


	

</body>
</html>