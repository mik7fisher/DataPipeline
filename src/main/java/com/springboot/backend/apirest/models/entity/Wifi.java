package com.springboot.backend.apirest.models.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="wifi")
public class Wifi implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	

	private String programa;
	private Double latitud;
	private Double longitud;
	private String colonia;
	private String alcaldia;
	
	@Column(name="fechaInstalacion")
	@Temporal(TemporalType.DATE)
	private Date fechaInstalacion;

	@PrePersist
	public void prePersist() {
		fechaInstalacion = new Date();
	}
	
	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getPrograma() {
		return programa;
	}



	public void setPrograma(String programa) {
		this.programa = programa;
	}



	public Double getLatitud() {
		return latitud;
	}



	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}



	public Double getLongitud() {
		return longitud;
	}



	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}



	public String getColonia() {
		return colonia;
	}



	public void setColonia(String colonia) {
		this.colonia = colonia;
	}



	public String getAlcaldia() {
		return alcaldia;
	}



	public void setAlcaldia(String alcaldia) {
		this.alcaldia = alcaldia;
	}



	public Date getFechaInstalacion() {
		return fechaInstalacion;
	}



	public void setFechaInstalacion(Date fechaInstalacion) {
		this.fechaInstalacion = fechaInstalacion;
	}



	private static final long serialVersionUID = 1L;
}
