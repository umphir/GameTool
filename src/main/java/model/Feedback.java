package model;

import java.util.Date;

public class Feedback {
	
	private int idFeedback;
	private String descricao;
	private Date data;
	private int nota;
	private Jogo jogo;
	private Usuario usuario;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Feedback() {
		super();
	}

	public Feedback(String descricao, int nota, Jogo jogo, Usuario usuario) {
		this.descricao = descricao;
		this.nota = nota;
		this.data = new Date(System.currentTimeMillis());
		this.jogo = jogo;
		this.usuario = usuario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public int getIdFeedback() {
		return idFeedback;
	}

	public void setIdFeedback(int idFeedback) {
		this.idFeedback = idFeedback;
	}

}
