package br.com.odontoCorretorProposta.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

@Entity
@Repository
public class Proposta {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int numeroProposta;
	
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
	private Corretor corretor;
	
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
	private Produto produto;
	
	@NotNull
	private String nomeSegurado;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar dataAssinatura;
	
	@Transient
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar dataAssinaturaDe;
	
	@Transient	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar dataAssinaturaAte;

	@Transient	
	private String dataAssinaturaString;
	
	public int getNumeroProposta() {
		return numeroProposta;
	}

	public void setNumeroProposta(int numeroProposta) {
		this.numeroProposta = numeroProposta;
	}

	public Corretor getCorretor() {
		return corretor;
	}

	public void setCorretor(Corretor corretor) {
		this.corretor = corretor;
	}

	public String getNomeSegurado() {
		return nomeSegurado;
	}

	public void setNomeSegurado(String nomeSegurado) {
		this.nomeSegurado = nomeSegurado;
	}

	public Calendar getDataAssinatura() {
		return dataAssinatura;
	}

	public void setDataAssinatura(Calendar dataAssinatura) {
		this.dataAssinatura = dataAssinatura;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Calendar getDataAssinaturaDe() {
		return dataAssinaturaDe;
	}

	public void setDataAssinaturaDe(Calendar dataAssinaturaDe) {
		this.dataAssinaturaDe = dataAssinaturaDe;
	}

	public Calendar getDataAssinaturaAte() {
		return dataAssinaturaAte;
	}

	public void setDataAssinaturaAte(Calendar dataAssinaturaAte) {
		this.dataAssinaturaAte = dataAssinaturaAte;
	}

	public String getDataAssinaturaString() {
		return dataAssinaturaString;
	}

	public void setDataAssinaturaString(String dataAssinaturaString) {
		this.dataAssinaturaString = dataAssinaturaString;
	}


}
