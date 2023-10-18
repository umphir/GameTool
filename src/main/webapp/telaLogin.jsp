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

    <title>Login</title>
</head>
<body class="bg-dark">
   

    
    <div class="dark-overlay"></div>
    
    <div class="container-fluid">
    <div class="background-image"></div>
        <div class="row justify-content-center">
         
            <div class="col-md-8 col-lg-6 col-xl-4 col-sm-10 col-12 login-container mt-4">
                <h2 class="mb-4 text-white">Bem-vindo! Faça Login</h2>
                <span class="text-danger" style="margin-left:auto" >${erro} </span>
                <form action="login" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" id="login-usuario" placeholder="E-Mail" name="email" required>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="login-senha" placeholder="Senha" name="senha" required>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Login</button>
                    <p class="mt-3 text-white">Ainda não tem uma conta? <a href="telaCadastro.jsp" id="signup-link" class="text-warning">Cadastre-se</a></p>
                </form>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>