package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionFactory;
import model.Categoria;
import model.Empresa;
import model.Jogo;

public class JogoDAO {

	private Connection conexao;

	public JogoDAO() {
		this.conexao = new ConnectionFactory().pegaConexao();
	}

	
	public Jogo jogoPorId(int idJogo) {
		String sql = "SELECT J.*, C.*, E.* FROM jogo J JOIN categoriaJogo C ON C.cod_Categoria = J.Categoria JOIN empresa E ON E.cod_empresa = J.empresa_cod where cod_jogo = ?";
		Jogo jogo = null;
		try {
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, idJogo);
		
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			int jIdJogo = rs.getInt("cod_Jogo");
			String jNome = rs.getString("nome_jogo");
			int jNota = rs.getInt("nota_jogo");
			String jImg_poster = rs.getString("img_poster");
			String jImg_slide = rs.getString("img_slide");
			String jJogo_descricao = rs.getString("jogo_descricao");
			
			int cIdCategoria = rs.getInt("cod_categoria");
			String cNome = rs.getString("nome_categoria");
			
			int eId = rs.getInt("cod_empresa");
			String eNome = rs.getString("nome_empresa");
			
			Empresa empresa = new Empresa(eNome);
			empresa.setIdEmpresa(eId);
			
			Categoria categoria = new Categoria(cNome);
			categoria.setIdCategoria(cIdCategoria);
			
			jogo = new Jogo(jNome, empresa, categoria, jNota, jImg_poster, jImg_slide, jJogo_descricao);
			jogo.setIdJogo(jIdJogo);
		}
		stmt.close();
		rs.close();
		conexao.close();
		
		return jogo;
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}
	
	public List<Jogo> jogoPorNota(){
		String sql = "SELECT J.*, C.*, E.* FROM (SELECT * FROM JOGO ORDER BY Nota_Jogo DESC) J JOIN categoriaJogo C ON C.cod_Categoria = J.Categoria JOIN empresa E ON E.cod_empresa = J.empresa_cod WHERE ROWNUM <= 5";
		List<Jogo> jogos = new ArrayList<Jogo>();
		
		try {
		PreparedStatement stmt = conexao.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			int jIdJogo = rs.getInt("cod_Jogo");
			String jNome = rs.getString("nome_jogo");
			int jNota = rs.getInt("nota_jogo");
			String jImg_poster = rs.getString("img_poster");
			String jImg_slide = rs.getString("img_slide");
			String jJogo_descricao = rs.getString("jogo_descricao");
			
			int cIdCategoria = rs.getInt("cod_categoria");
			String cNome = rs.getString("nome_categoria");
			
			int eId = rs.getInt("cod_empresa");
			String eNome = rs.getString("nome_empresa");
			
			Empresa empresa = new Empresa(eNome);
			empresa.setIdEmpresa(eId);
			
			Categoria categoria = new Categoria(cNome);
			categoria.setIdCategoria(cIdCategoria);
			
			Jogo jogo = new Jogo(jNome, empresa, categoria, jNota, jImg_poster, jImg_slide, jJogo_descricao);
			jogo.setIdJogo(jIdJogo);

			jogos.add(jogo);
		}
		stmt.close();
		rs.close();
		conexao.close();
		
		return jogos;
		} catch (SQLException e) {
			System.out.println("E: "+ e);
			throw new RuntimeException();
		}
	}
		
		
		public List<Jogo> listarTodos() {
			List<Jogo> jogos = new ArrayList<Jogo>();
			String sql = "SELECT J.*, C.*, E.* FROM jogo J JOIN categoriaJogo C ON C.cod_Categoria = J.Categoria JOIN empresa E ON E.cod_empresa = J.empresa_cod";
			
			try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int jIdJogo = rs.getInt("cod_Jogo");
				String jNome = rs.getString("nome_jogo");
				int jNota = rs.getInt("nota_jogo");
				String jImg_poster = rs.getString("img_poster");
				String jImg_slide = rs.getString("img_slide");
				String jJogo_descricao = rs.getString("jogo_descricao");
				
				int eId = rs.getInt("cod_empresa");
				String eNome = rs.getString("nome_empresa");
				
				Empresa empresa = new Empresa(eNome);
				empresa.setIdEmpresa(eId);
				
				int cIdCategoria = rs.getInt("cod_categoria");
				String cNome = rs.getString("nome_categoria");
				
				Categoria categoria = new Categoria(cNome);
				categoria.setIdCategoria(cIdCategoria);
				
				Jogo jogo = new Jogo(jNome, empresa, categoria, jNota, jImg_poster, jImg_slide, jJogo_descricao);
				jogo.setIdJogo(jIdJogo);

				jogos.add(jogo);
			}
			stmt.close();
			rs.close();
			conexao.close();
			
			return jogos;
		}
		catch (SQLException e) {
			System.out.println("Erro: " + e);
			throw new RuntimeException(e);
		}
		
		
	}
}
