<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/style.css">
    <style><jsp:include page="./resources/css/telaLogin.css"></jsp:include></style>
    <title>Cadastro</title>
</head>
<body class="bg-dark">
   

    
    <div class="dark-overlay"></div>
    
    <div class="container-fluid">
    
        <div class="row justify-content-center">
         
            <div class="col-md-8 col-lg-6 col-xl-4 col-sm-10 col-12 signup-container">
                <h2 class="mb-4 text-white mt-4">Cadastre-se</h2>
                <span class="text-danger" style="margin-left:auto" >${erro} </span>
                <form action="cadastro" method="post">
                    <input type="text" class="form-control form-group" name="nome" placeholder="Nome" id="nome" required>
                    <input type="text" class="form-control form-group" name="email" placeholder="E-Mail" id="signup-email" required>
                    <input type="date" class="form-control form-group" name="data" id="signup-date" min="1923-01-01" max="2023-12-31" required>                        	
                    <input type="password" class="form-control form-group" name="senha" placeholder="Senha" id="signup-senha" required>
                    <button type="submit" class="btn btn-success btn-block">Cadastrar</button>
                    <button type="reset" class="btn btn-danger btn-block" id="limpar">Limpar</button>
                    <p class="mt-3 text-white">Já tem uma conta? <a href="telaLogin.jsp" id="login-link" class="text-warning">Faça o login</a></p>
                </form>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>