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
import repository.FeedbackDAO;
import repository.JogoDAO;

@WebServlet("/paginaInicial")
public class PaginaInicialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PaginaInicialServlet() { 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JogoDAO jogoDAO = new JogoDAO();

        FeedbackDAO feedbackDAO = new FeedbackDAO();
        
        List<Jogo> listaJogo = jogoDAO.jogoPorNota();
        List<Feedback> listaFeedback = feedbackDAO.listarRecentes();
        

    	request.setAttribute("listaJogo", listaJogo);
    	request.setAttribute("listaFeedback", listaFeedback);
    	
    	
    	System.out.println("PaginaInicialServlet");

    	
    	RequestDispatcher rd = request.getRequestDispatcher("paginaInicial.jsp");
    	rd.forward(request, response);
    	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
