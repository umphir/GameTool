package model;

public class Empresa {

	private int idEmpresa;
	private String nomeEmpresa;

	public Empresa(String nomeEmpresa) {
		super();
		this.nomeEmpresa = nomeEmpresa;
	}

	public Empresa() {
		super();
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

}
