package model;

import java.util.Calendar;

import util.CriptografiaUtils;

public class Usuario {
	private int id;
	private String nome;
	private String email;
	private Calendar dataNasc;
	private String senha;
	
	
	public Usuario(String nome, Calendar dataNasc, String email, String senha) {
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.email = email;
		setSenha(senha);
	}
	
	public Usuario() {}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Calendar getDataNasc() {
		return dataNasc;
	}


	public void setDataNasc(Calendar dataNasc) {
		this.dataNasc = dataNasc;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		try {
			this.senha = CriptografiaUtils.criptografar(senha);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
