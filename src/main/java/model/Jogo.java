package model;

import java.util.List;

public class Jogo {

	private int idJogo;
	private String nomeJogo;
	private Empresa empresa;
	private Categoria categoria;
	private int notaJogo;
	private String img_poster;
	private String img_slide;
	private List<Feedback> feedbacks;
	private String jogo_descricao;
	

	public Jogo( String nomeJogo, Empresa empresa, Categoria categoria, int notaJogo, String img_poster, String img_slide, String jogo_descricao) {
		super();
		this.nomeJogo = nomeJogo;
		this.empresa = empresa;
		this.categoria = categoria;
		this.notaJogo = notaJogo;
		this.img_poster = img_poster;
		this.img_slide = img_slide;
		this.jogo_descricao = jogo_descricao;
	}
	public Jogo() {
		super();
	}
	public int getIdJogo() {
		return idJogo;
	}
	public void setIdJogo(int idJogo) {
		this.idJogo = idJogo;
	}
	public String getNomeJogo() {
		return nomeJogo;
	}
	public void setNomeJogo(String nomeJogo) {
		this.nomeJogo = nomeJogo;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}
	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public int getNotaJogo() {
		return notaJogo;
	}
	public void setNotaJogo(int notaJogo) {
		this.notaJogo = notaJogo;
	}
	public String getImg_poster() {
		return img_poster;
	}
	public void setImg_poster(String img_poster) {
		this.img_poster = img_poster;
	}
	public String getImg_slide() {
		return img_slide;
	}
	public void setImg_slide(String img_slide) {
		this.img_slide = img_slide;
	}
	public String getJogo_descricao() {
		return jogo_descricao;
	}
	public void setJogo_descricao(String jogo_descricao) {
		this.jogo_descricao = jogo_descricao;
	}
	
	
	
}