package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Feedback;
import model.Jogo;
import repository.FeedbackDAO;
import repository.JogoDAO;

/**
 * Servlet implementation class JogoControllerServlet
 */
@WebServlet("/jogo")
public class JogoControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JogoControllerServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "listar":
			listar(request, response);
			break;
		case "listarId":
			listarId(request, response);
			break;
		default:
			break;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JogoDAO jogoDAO = new JogoDAO();
		System.out.println("Listando Jogos");
		List<Jogo> listaJogos = jogoDAO.listarTodos();
		request.setAttribute("listaJogo", listaJogos);
		request.getRequestDispatcher("listarJogos.jsp").forward(request, response);
	}
	
	private void listarId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JogoDAO jogoDAO = new JogoDAO();
		int idJogo = Integer.parseInt(request.getParameter("id"));
		System.out.println("Listando Jogo Id " + idJogo);
		Jogo jogo = jogoDAO.jogoPorId(idJogo);
		
		FeedbackDAO feedbackDAO = new FeedbackDAO();
		List<Feedback> feedbacks = feedbackDAO.listarPorJogo(jogo);
		
		request.setAttribute("jogo", jogo);
		request.setAttribute("listaFeedback", feedbacks);
		request.getRequestDispatcher("paginaJogo.jsp").forward(request, response);
	}
	

}
