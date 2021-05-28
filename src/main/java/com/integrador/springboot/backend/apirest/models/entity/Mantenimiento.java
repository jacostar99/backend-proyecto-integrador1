package com.integrador.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "mantenimientos")
public class Mantenimiento implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descripcion;
	
	
	@Column(name = "fecha_asignada")
	@Temporal(TemporalType.DATE)
	private Date fechaAsignada;
	
	private String direccion; //HACER LISTA DE SUCURSALES
	
	@ManyToOne(fetch = FetchType.LAZY) // carga perezosa , cada vez que se invoque el metodo o atributo region o							// getRegion ahi se va a realizar la carga
	@JoinColumn(name = "region_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" }) // se ignoran los atributos propios de hibernate																	// del objeto proxy que esta relacionado a																// objeto region
	private Region region; // la relacion es atravez del atributo

	
	
	@JsonIgnoreProperties(value={ "hibernateLazyInitializer", "handler" , "mantenimientos"},allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY) 
	private Operario operario;     
	
	private String tipo; 
	
	@Temporal(TemporalType.TIME)
	private Date horaInicio;
	@Temporal(TemporalType.TIME)
	private Date horaAproximadaDuracion;
	private String prioridad;
    
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "mantenimiento_id")
	private List<ItemMantenimiento> items;

	public Mantenimiento() {
		items = new ArrayList<>();
	}
	// GETTERS AND SETTERS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaAsignada() {
		return fechaAsignada;
	}

	public void setFechaAsignada(Date fechaAsignada) {
		this.fechaAsignada = fechaAsignada;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	
	public Operario getOperario() {
		return operario;
	}

	public void setOperario(Operario operario) {
		this.operario = operario;
	}
	
	

	public String getTipo() {
		return tipo;
	}
 
	

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
     
	
	
	

	public Date getHoraAproximadaDuracion() {
		return horaAproximadaDuracion;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public void setHoraAproximadaDuracion(Date horaAproximadaDuracion) {
		this.horaAproximadaDuracion = horaAproximadaDuracion;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	

	public List<ItemMantenimiento> getItems() {
		return items;
	}

	public void setItems(List<ItemMantenimiento> items) {
		this.items = items;
	}
	
	
	

	public Double getTotal() {
		Double total =0.00;
		for(ItemMantenimiento item:items) {
			total += item.getImporte();
			
		}
		
		return total;
	}


	private static final long serialVersionUID = 1L;

}
