
package repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import connection.ConnectionFactory;
import model.Usuario;

public class UsuarioDAO  {

	private Connection conexao;

	public UsuarioDAO() {
		this.conexao = new ConnectionFactory().pegaConexao();
	}
	public void inserir(Usuario usuario) {
		try {
			String sql = "INSERT INTO usuario_jogo (nome_usuario, data_nasc,email_cliente,senha_cliente) VALUES(?,?,?,?)";
			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setString(1, usuario.getNome());
			java.sql.Date data = new java.sql.Date(usuario.getDataNasc().getTimeInMillis());
			stmt.setDate(2, data);
			stmt.setString(3, usuario.getEmail());
			stmt.setString(4, usuario.getSenha());

			stmt.execute();
			stmt.close();
			conexao.close();

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao executar SQL: " + e.getMessage(), e);
		}
	}

	public void alterar(String nome, String email, String senha) {
		try {
			String sql = "update  usuario_jogo set usuario_jogo.nome_usuario = ?, usuario_jogo.email_cliente = ?, usuario_jogo.senha_cliente =? where id_usuario = ? ";
			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setString(1, nome);
			stmt.setString(2, email);
			stmt.setString(3, senha);
		

			stmt.execute();
			stmt.close();
			conexao.close();
			
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	public void excluir(int idUsuario) {

		try {

			String sql = "delete from usuario_jogo where id_usuario = ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setInt(1, idUsuario);
			stmt.execute();
			stmt.close();
			conexao.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public Usuario SelectById(int idUsuario) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Usuario usuario = null;
		try {
			String sql = "SELECT * FROM USUARIO_JOGO WHERE nome_usuario=?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idUsuario);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt(1));
				usuario.setNome(rs.getString(2));
				java.sql.Date data = rs.getDate(3);
				Calendar dataNascimento = Calendar.getInstance();
				dataNascimento.setTimeInMillis(data.getTime());
				usuario.setDataNasc(dataNascimento);
				usuario.setEmail(rs.getString(4));
				usuario.setSenha(rs.getString(5));
			}
			
			rs.close();
			stmt.close();
			conexao.close();
			return usuario;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Usuario SelectByEmail(String email) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Usuario usuario = null;
		try {
			String sql = "SELECT * FROM USUARIO_JOGO WHERE email_cliente=?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt(1));
				usuario.setNome(rs.getString(2));
				java.sql.Date data = rs.getDate(3);
				Calendar dataNascimento = Calendar.getInstance();
				dataNascimento.setTimeInMillis(data.getTime());
				usuario.setDataNasc(dataNascimento);
				usuario.setEmail(rs.getString(4));
				usuario.setSenha(rs.getString(5));
			}
			
			rs.close();
			stmt.close();
			conexao.close();
			return usuario;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean validarUsuario(Usuario usuario) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conexao.prepareStatement("SELECT * FROM USUARIO_JOGO WHERE email_cliente = ? AND senha_cliente = ?");
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getSenha());
			rs = stmt.executeQuery();

			if (rs.next()) {
				usuario.setId(rs.getInt(1));
				usuario.setNome(rs.getString(2));
				java.sql.Date data = rs.getDate(3);
				Calendar dataNascimento = Calendar.getInstance();
				dataNascimento.setTimeInMillis(data.getTime());
				usuario.setDataNasc(dataNascimento);
				usuario.setEmail(rs.getString(4));
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				if(rs != null)
					rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public Boolean verificarEmail(String email) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT email_cliente FROM USUARIO_JOGO WHERE email_cliente=?";
			stmt = conexao.prepareStatement(sql);	
			stmt.setString(1, email);
			rs = stmt.executeQuery();
					
			while(rs.next()) {
					return true;
			}

			stmt.close();
			rs.close();
			conexao.close();
			return false;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
