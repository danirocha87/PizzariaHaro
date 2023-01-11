package com.PizzariaHaro.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

	@Entity //Aqui eu informo que é uma tabela do banco de dados 
	@Table(name = "tb_pedidos") // Aqui eu crio uma tabela e dou o nome para ela
	public class PedidosModel {
	
	@Id // Aqui informo que minha classe é um ID -CHAVE PRIMARIA
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Aqui informo que ela é auto incremente
	private Long id;
	
	
	@NotBlank
	@Size(min = 2 , max = 100)//Aqui eu escolho a quantidade de caracteres, pode ter minimo ou maximo 
	private String nome;
	
	//uso o Date(System.currentTimeMillis()), pois ele vai informar exatamente a data e horario que esta sendo postado
	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis());

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	}
