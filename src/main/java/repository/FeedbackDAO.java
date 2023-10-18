package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import connection.ConnectionFactory;
import model.Categoria;
import model.Empresa;
import model.Feedback;
import model.Jogo;
import model.Usuario;

public class FeedbackDAO {

	private Connection conexao;
	public FeedbackDAO() {
		this.conexao = new ConnectionFactory().pegaConexao();
	}

	public void inserir(Feedback feedback) {
		try {
			String sql = "INSERT INTO FEEDBACK_JOGO (descricao,data_feedback, notaJogo,Idusuario, Idjogo) VALUES(?,?,?,?,?)";
			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setString(1, feedback.getDescricao());
			stmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
			stmt.setInt(3, feedback.getNota());
			stmt.setInt(4, feedback.getUsuario().getId());
			stmt.setInt(5, feedback.getJogo().getIdJogo());
			
			stmt.execute();
			stmt.close();
			conexao.close();

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao executar SQL: " + e.getMessage(), e);
		}
	}
	
	public void editar(Feedback feedback) {
		try {
			String sql = "update FEEDBACK_JOGO set descricao = ?, notaJogo =? where idFeedback = ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setString(1, feedback.getDescricao());

			stmt.setInt(2, feedback.getNota());
			stmt.setInt(3, feedback.getIdFeedback());
			
			stmt.execute();
			stmt.close();
			conexao.close();

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao executar SQL: " + e.getMessage(), e);
		}
	}
	
	public List<Feedback> listarRecentes() {
		String sql = "SELECT FJ.*, U.*, J.*, C.*, E.* FROM (SELECT * FROM Feedback_Jogo ORDER BY idFeedback DESC ) FJ JOIN usuario_jogo U ON FJ.idUsuario = U.id_Usuario JOIN jogo J ON J.cod_Jogo = FJ.idJogo JOIN CategoriaJogo C ON J.Categoria = C.cod_Categoria JOIN empresa E ON E.cod_empresa = J.empresa_cod WHERE ROWNUM <= 5 ORDER BY FJ.idFeedback DESC";
		List<Feedback> feedbacks = new ArrayList<Feedback>();
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int fId = rs.getInt("IdFeedback");
				String fDescricao = rs.getString("Descricao");
				Date fData = rs.getDate("Data_Feedback");
				int fNota = rs.getInt("NotaJogo");
				
				int jIdJogo = rs.getInt("IdJogo");
				String jNome = rs.getString("nome_jogo");
				int jIdEmpresa = rs.getInt("empresa_cod");
				int jNota = rs.getInt("nota_jogo");
				String jImg_poster = rs.getString("img_poster");
				String jImg_slide = rs.getString("img_slide");
				String jDescJogo = rs.getString("jogo_descricao");
				
				int eId = rs.getInt("cod_empresa");
				String eNome = rs.getString("nome_empresa");
				
				Empresa empresa = new Empresa(eNome);
				empresa.setIdEmpresa(eId);
				
				int cIdCategoria = rs.getInt("categoria");
				String cNome = rs.getString("nome_categoria");
				
				int uIdUsuario = rs.getInt("IdUsuario");
				String uNome = rs.getString("Nome_Usuario");

				Calendar uNasc = Calendar.getInstance();
				uNasc.setTimeInMillis(rs.getDate("Data_Nasc").getTime());
				
				String uEmail = rs.getString("Email_Cliente");
				
				Usuario usuario = new Usuario(uNome, uNasc, uEmail, null);
				usuario.setId(uIdUsuario);
				
				Categoria categoria = new Categoria(cNome);
				categoria.setIdCategoria(cIdCategoria);
				
				Jogo jogo = new Jogo(jNome, empresa, categoria, jNota, jImg_poster, jImg_slide, jDescJogo);
								
				Feedback feedback = new Feedback(fDescricao, fNota, jogo, usuario);
				feedback.setIdFeedback(fId);
				
				feedbacks.add(feedback);
			}
			
			stmt.execute();
			stmt.close();
			rs.close();
			conexao.close();
			return feedbacks;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Feedback> listarPorJogo(Jogo jogo) {
		String sql = "SELECT FJ.*, U.* FROM feedback_jogo FJ join usuario_jogo U ON FJ.idUsuario = U.id_usuario where FJ.idJogo = ? order by FJ.idFeedback DESC";
		List<Feedback> feedbacks = new ArrayList<Feedback>();
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, jogo.getIdJogo());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int fId = rs.getInt("IdFeedback");
				String fDescricao = rs.getString("Descricao");
				Date fData = rs.getDate("Data_Feedback");
				int fNota = rs.getInt("NotaJogo");		
				
				int uIdUsuario = rs.getInt("IdUsuario");
				String uNome = rs.getString("Nome_Usuario");
				Calendar uNasc = Calendar.getInstance();
				uNasc.setTimeInMillis(rs.getDate("Data_Nasc").getTime());
				String uEmail = rs.getString("Email_Cliente");
				
				Usuario usuario = new Usuario(uNome, uNasc, uEmail, null);
				usuario.setId(uIdUsuario);
								
				Feedback feedback = new Feedback(fDescricao, fNota, jogo, usuario);
				feedback.setIdFeedback(fId);
				
				feedbacks.add(feedback);
			}
			
			stmt.execute();
			stmt.close();
			rs.close();
			conexao.close();
			return feedbacks;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void excluir(int idFeedback) {
		String sql = "DELETE FROM feedback_jogo WHERE idFeedback = ?";
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idFeedback);
			stmt.execute();
			stmt.close();
			conexao.close();

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao executar SQL: " + e.getMessage(), e);
		}
	}
	
	public List<Feedback> FeedbackPorUsuario(int idUsuario) {
		String sql = "SELECT FJ.*, U.*, J.*, C.*, E.* FROM Feedback_Jogo FJ JOIN usuario_jogo U ON FJ.idUsuario = U.id_Usuario JOIN jogo J ON J.cod_Jogo = FJ.idJogo JOIN CategoriaJogo C ON J.Categoria = C.cod_Categoria JOIN empresa E ON E.cod_empresa = J.empresa_cod where FJ.idUsuario = ? ORDER BY FJ.idUsuario DESC";
		List<Feedback> feedbacks = new ArrayList<Feedback>();
		
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idUsuario);
			ResultSet rs = stmt.executeQuery();
					
			while(rs.next()) {
				
				int fId = rs.getInt("idfeedback");
				String fDescricao = rs.getString("descricao");
				Date fData = rs.getDate("data_feedback");
				int fNota = rs.getInt("NotaJogo");
				
				int jIdJogo = rs.getInt("IdJogo");
				String jNome = rs.getString("nome_jogo");
				int jNota = rs.getInt("nota_jogo");
				String jImg_poster = rs.getString("img_poster");
				String jImg_slide = rs.getString("img_slide");
				String jDescJogo = rs.getString("jogo_descricao");
				
				int eId = rs.getInt("cod_empresa");
				String eNome = rs.getString("nome_empresa");
				
				Empresa empresa = new Empresa(eNome);
				empresa.setIdEmpresa(eId);
				
				int cIdCategoria = rs.getInt("cod_categoria");
				String cNome = rs.getString("nome_categoria");
				
				int uIdUsuario = rs.getInt("IdUsuario");
				String uNome = rs.getString("Nome_Usuario");
				Calendar uNasc = Calendar.getInstance();
				uNasc.setTimeInMillis(rs.getDate("Data_Nasc").getTime());
				String uEmail = rs.getString("Email_Cliente");
				
				Usuario usuario = new Usuario(uNome, uNasc, uEmail, null);
				usuario.setId(uIdUsuario);
				
				Categoria categoria = new Categoria(cNome);
				categoria.setIdCategoria(cIdCategoria);
				
				Jogo jogo = new Jogo(jNome, empresa, categoria, jNota, jImg_poster, jImg_slide, jDescJogo);
				jogo.setIdJogo(jIdJogo);
								
				Feedback feedback = new Feedback(fDescricao, fNota, jogo, usuario);
				feedback.setIdFeedback(fId);
				
				feedbacks.add(feedback);
			}
			
			stmt.close();
			conexao.close();
			return feedbacks;

		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException("Erro ao executar SQL: " + e.getMessage(), e);
		}
	}


	

	
}
