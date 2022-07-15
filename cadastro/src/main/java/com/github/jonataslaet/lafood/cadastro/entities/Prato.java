package com.github.jonataslaet.lafood.cadastro.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "prato")
public class Prato extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public String nome;
	
	public String descricao;
	
	public Double preco;
	
	@ManyToOne
	@JoinColumn(name = "restaurante_id")
	public Restaurante restaurante;

}
