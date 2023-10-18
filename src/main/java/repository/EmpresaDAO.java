package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.ConnectionFactory;
import model.Empresa;

public class EmpresaDAO {
	
	private Connection conexao;
	public EmpresaDAO() {
		this.conexao = new ConnectionFactory().pegaConexao();
	}

	public void inserir(Empresa empresa) {
		try {
			String sql = "INSERT INTO empresa (nome_empresa) VALUES(?)";
			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setString(1, empresa.getNomeEmpresa());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao executar SQL: " + e.getMessage(), e);
		}
	}

	public void alterar(int idEmpresa) {
		try {
			String sql = "update  empresa set empresa.nome_empresa = ? where cod_empresa =?";
			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setInt(1, idEmpresa);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}

	public void excluir(int cod_empresa) {

		try {

			String sql = "delete from empresa where cod_empresa = ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setInt(1, cod_empresa);
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
