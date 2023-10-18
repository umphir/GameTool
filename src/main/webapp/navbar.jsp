<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg bg-black">
  <div class="container-fluid">
  
    <a class="navbar-brand fs-5 text-white" href="paginaInicial">
    	
    	<i class="bi bi-controller"></i> GameTool
    	
    </a>
  
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
        <form method="POST" action="feedback">
        	<input type="hidden" name="acao" value="meusFeedbacks">
        	<input type="hidden" name="idLogin" value="${idLogin}">
          	<button type="submit" class="nav-link active text-white">Meus Feedbacks</button>
          </form>
        </li>
      </ul>
      <ul class="navbar-nav">
  		<li class="nav-item"> <span class="nav-link disabled link-light">${emailLogin}</span> </li>
  		<li class="nav-item"> 
  			<form action="logout" method="post">
		  		<button class="btn btn-danger" type="submit">Terminar sessão</button> 
  			</form>
  		</li>
       </ul>
      
    </div>
  </div>
</nav>