package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Usuario;
import repository.UsuarioDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	String email = request.getParameter("email");
	String senha = request.getParameter("senha");
		
	UsuarioDAO dao = new UsuarioDAO();
	Usuario usuario = new Usuario();
	usuario.setEmail(email);
	usuario.setSenha(senha);
	
	if(dao.validarUsuario(usuario)) {
		HttpSession session = request.getSession();
		session.setAttribute("emailLogin", email);
		dao = new UsuarioDAO();
		Usuario usuarioLogin = dao.SelectByEmail(email);
		session.setAttribute("idLogin", usuarioLogin.getId());
		
		request.getRequestDispatcher("paginaInicial").forward(request, response);
	}else {
		request.setAttribute("erro", "Email ou senha invalidos.");
		request.getRequestDispatcher("telaLogin.jsp").forward(request, response);
	}
	
	}

}
