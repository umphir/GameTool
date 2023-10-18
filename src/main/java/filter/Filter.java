package filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class Filter extends HttpFilter implements javax.servlet.Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		String url = req.getRequestURI();

		if (session.getAttribute("emailLogin") == null && !url.endsWith("login") && !url.contains("telaLogin.jsp") 
				&& !url.contains("telaCadastro.jsp") && !url.contains("cadastro"))  {
			request.setAttribute("erro", "Entre com o usuário e senha!");
			request.getRequestDispatcher("telaLogin.jsp").forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}
}
