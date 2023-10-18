package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Usuario;
import repository.UsuarioDAO;

@WebServlet("/cadastro")
public class CadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String nome = request.getParameter("nome");

			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = inputFormat.parse(request.getParameter("data"));
			SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
			String data = outputFormat.format(date);
			Calendar dataNasc = Calendar.getInstance();
			dataNasc.setTime(outputFormat.parse(data));

			String email = request.getParameter("email");
			String senha = request.getParameter("senha");

			Usuario usuario = new Usuario(nome, dataNasc, email, senha);

			UsuarioDAO dao = new UsuarioDAO();

			boolean teste = dao.verificarEmail(usuario.getEmail());
			
			dao = new UsuarioDAO();

			if (!nome.trim().equals("") && !email.trim().equals("") && !senha.trim().equals("")) {
				if (!teste) {
					dao.inserir(usuario);
					request.getRequestDispatcher("telaLogin.jsp").forward(request, response);
					;
				} else {
					request.setAttribute("erro", "E-Mail já cadastrado.");
					request.getRequestDispatcher("telaCadastro.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("erro", "TODOS os campos devem ser preenchidos.");
				request.getRequestDispatcher("telaCadastro.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
