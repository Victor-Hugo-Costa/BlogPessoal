package org.generation.blogpessoal.model;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// import org.hibernate.annotations.UpdateTimestamp;

@Entity // Transforma em tabela
@Table(name="tb_postagem") // Nomeia a tabela
public class Postagem {
	
	@Id // Responsável por definir o ID como chave primária
	@GeneratedValue(strategy= GenerationType.IDENTITY) // Responsável pelo auto-incremetação do ID
	private Long id; // Define a váriavel ID
	
	@NotNull // Garante a obrigatoriedade da inclusão de dados
	private String titulo; // Define a váriavel titulo
	
	@NotNull // Garante a obrigatoriedade da inclusão de dados
	@Size(min=4, max=1000) // Define os parâmetros para o usuário
	private String texto;
	
	@Temporal(TemporalType.TIMESTAMP)  // @UpdateTimestamp Formata no padrão de data e hora //private LocalDateTime data; // Extrai a data do computador
	private Date date= new java.sql.Date(System.currentTimeMillis());
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}	

}
