package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Feedback;
import model.Jogo;
import model.Usuario;
import repository.FeedbackDAO;

/**
 * Servlet implementation class FeedbackControllerServlet
 */
@WebServlet("/feedback")
public class FeedbackControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FeedbackControllerServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");

		switch (acao) {
		case "adicionar":
			adicionar(request, response);
			break;
		case "editar":
			editar(request, response);
			break;
		case "meusFeedbacks":
			meusFeedbacks(request, response);
			break;
		case "excluir":
			excluir(request, response);
			break;
		default:
			break;
		}
	}
	
	public void adicionar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idJogo = Integer.parseInt(request.getParameter("idJogo"));
		int idLogin = Integer.parseInt(request.getParameter("idUser"));
		int nota = Integer.parseInt(request.getParameter("nota"));
		String descricao = new String(request.getParameter("descricao").getBytes("ISO-8859-1"), "UTF-8");
		
//		JogoDAO jogoDAO = new JogoDAO();
		Jogo jogo = new Jogo();
		jogo.setIdJogo(idJogo);
		
		Usuario usuario = new Usuario();
		usuario.setId(idLogin);
		
		Feedback feedback = new Feedback();
		feedback.setDescricao(descricao);
		feedback.setJogo(jogo);
		feedback.setUsuario(usuario);
		feedback.setNota(nota);
		
		FeedbackDAO feedbackDAO = new FeedbackDAO();
		feedbackDAO.inserir(feedback);
		
		response.sendRedirect("jogo?acao=listarId&id=" + jogo.getIdJogo());
		
	}
	
	public void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idFeedback = Integer.parseInt(request.getParameter("idFeedback"));
		int idJogo = Integer.parseInt(request.getParameter("idJogo"));
		System.out.println("idJogo "+idJogo);
		FeedbackDAO feedbackDAO = new FeedbackDAO();
		feedbackDAO.excluir(idFeedback);
		System.out.println("idLogin " + request.getParameter("idLogin"));
		request.setAttribute("idLogin", request.getParameter("idLogin"));
		
		System.out.println(idJogo);
		if(idJogo != 0)
			response.sendRedirect("jogo?acao=listarId&id=" + idJogo);
		else
			this.meusFeedbacks(request, response);
	}
	
	public void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" teste Jogo "+ request.getParameter("idJogo") );
		System.out.println("nota " + request.getParameter("nota"));
		int idJogo = Integer.parseInt(request.getParameter("idJogo"));
		int idFeedback = Integer.parseInt(request.getParameter("idFeedback"));
		int idLogin = Integer.parseInt(request.getParameter("idLogin"));
		int nota = Integer.parseInt(request.getParameter("nota"));
		String descricao = new String(request.getParameter("descricao").getBytes("ISO-8859-1"), "UTF-8");
		
		Jogo jogo = new Jogo();
		jogo.setIdJogo(idJogo);
		
		Usuario usuario = new Usuario();
		usuario.setId(idLogin);
		
		Feedback feedback = new Feedback();
		feedback.setIdFeedback(idFeedback);
		feedback.setDescricao(descricao);
		feedback.setJogo(jogo);
		feedback.setUsuario(usuario);
		feedback.setNota(nota);
		
		FeedbackDAO feedbackDAO = new FeedbackDAO();
		feedbackDAO.editar(feedback);
		
		
	}
	
	public void meusFeedbacks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Login id: " +request.getParameter("idLogin"));
		int idLogin = Integer.parseInt(request.getParameter("idLogin"));
		
		FeedbackDAO feedbackDAO = new FeedbackDAO();
		List<Feedback> meusFeedbacks = feedbackDAO.FeedbackPorUsuario(idLogin);
		
		request.setAttribute("meusFeedbacks", meusFeedbacks);
		
		RequestDispatcher rd = request.getRequestDispatcher("meusFeedbacks.jsp");
		rd.forward(request, response);
	}
	
	
	

}
