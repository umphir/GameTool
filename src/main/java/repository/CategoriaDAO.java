package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import connection.ConnectionFactory;
import model.Categoria;

public class CategoriaDAO {
	private Connection conexao;

	public CategoriaDAO() {
		this.conexao = new ConnectionFactory().pegaConexao();
	}

	public void inserir(Categoria categoria) {
		try {
			String sql = "INSERT INTO CATEGORIAJOGO (nome_categoria) VALUES(?)";
			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setString(1, categoria.getNome());

			stmt.execute();
			stmt.close();
			conexao.close();

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao executar SQL: " + e.getMessage(), e);
		}
	}

	public void alterar(int idCategoria) {
		try {
			String sql = "update  CATEGORIAJOGO set CATEGORIAJOGO.nome_categoria = ? where cod_categoria =?";
			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setInt(1, idCategoria);

			stmt.execute();
			stmt.close();
			conexao.close();
			
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	public void excluir(int cod_categoria) {

		try {
			String sql = "delete from CATEGORIAJOGO where cod_categoria = ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setInt(1, cod_categoria);
			stmt.execute();
			stmt.close();
			conexao.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Map<Integer, Categoria> listarTodos() {
		
		Map<Integer, Categoria> categorias = new HashMap<>();
		
		try {
			String sql = "SELECT * FROM CategoriaJogo";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setIdCategoria(rs.getInt(1));
				categoria.setNome(rs.getString(2));
				
				categorias.put(categoria.getIdCategoria(), categoria);
			}
			
			stmt.execute();
			stmt.close();
			rs.close();
			conexao.close();
			return categorias;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
}
