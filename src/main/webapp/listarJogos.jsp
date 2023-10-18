<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<title>Jogos</title>
<style><jsp:include page="./resources/css/listarJogos.css"></jsp:include></style>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<script>
    function equalizeCardHeights() {
        var cards = document.querySelectorAll('.card');
        var maxHeight = 0;

        cards.forEach(function(card) {
            var cardHeight = card.offsetHeight;
            if (cardHeight > maxHeight) {
                maxHeight = cardHeight;
            }
        });

        cards.forEach(function(card) {
            card.style.height = maxHeight + 'px';
        });
    }
    
    window.addEventListener('load', equalizeCardHeights);
</script>

<jsp:include page="head.jsp"></jsp:include>
</head>
<body>

	<jsp:include page="./navbar.jsp"></jsp:include>
	
	<main class="container-fluid">	
	<div class="background-image"></div>
<div class="d-flex justify-content-center"> 
    <div class="row mb-2">
        
        <c:forEach items="${listaJogo}" var="jogo">
            <div class="col-12 col-md-6 col-lg-4 mt-4"> 
                <div class="card" style="width: 18rem;">
                    <img src="${jogo.img_poster}" class="card-img-top" alt="${jogo.nomeJogo}">
                    <div class="card-body d-flex flex-column align-items-start bg-dark text-white">
                        <h5 class="card-title">${jogo.nomeJogo} <i class="bi bi-star-half"></i></h5>
                        <p class="card-text">${jogo.jogo_descricao}</p>
                        <a href="jogo?acao=listarId&id=${jogo.idJogo}" class="btn btn-warning mt-auto w-100">Acessar</a>
                    </div>
                </div>
            </div>
        </c:forEach>
        
    </div>
</div>
	</main>

</body>
</html>