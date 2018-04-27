package edu.usmp.ProyeccionIA.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Person implements Serializable{

	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull
	@Min(10000000)
	@Max(99999999)
	private Integer dni;
	
	@NotNull
	@Size(min=2, max=30,message="mi mensaje")
	private String nombre;
	
	@NotNull
	@Min(0)	
	private Double ingAnual;
	
	private Double impTotal;	
	private Double ingTotal;
	private Double rentGravable;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getDni() {
		return dni;
	}
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	public Double getIngAnual() {
		return ingAnual;
	}
	public void setIngAnual(Double ingAnual) {
		this.ingAnual = ingAnual;
	}
	public Double getImpTotal() {
		return impTotal;
	}
	public void setImpTotal(Double impTotal) {
		this.impTotal = impTotal;
	}
	public Double getIngTotal() {
		return ingTotal;
	}
	public void setIngTotal(Double ingTotal) {
		this.ingTotal = ingTotal;
	}
	public Double getRentGravable() {
		return rentGravable;
	}
	public void setRentGravable(Double rentGravable) {
		this.rentGravable = rentGravable;
	}
			
}
