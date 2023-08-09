package com.devcaotics.guardeinocorazon.model.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Desavenca
{
	private long     id;
	private Desafeto desafeto;
	private String   titulo;
	private String   descricao;
	
	public long getId()
	{ return id; }
	
	public void setId(long id)
	{ this.id = id;	}
	
	public Desafeto getDesafeto()
	{ return desafeto; }
	
	public void setDesafeto(Desafeto desafeto)
	{ this.desafeto = desafeto;	}
	
	public String getTitulo()
	{ return titulo; }
	
	public void setTitulo(String titulo)
	{ this.titulo = titulo; }
	
	public String getDescricao()
	{ return descricao; }
	
	public void setDescricao(String descricao)
	{ this.descricao = descricao; }
}
