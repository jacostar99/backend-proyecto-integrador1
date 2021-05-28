package com.integrador.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "mantenimientosc")
public class MantenimientoCamion implements Serializable {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String descripcion;

	private String empresa;
    
	@Column(name = "fecha_asignada")
	@Temporal(TemporalType.DATE)
	private Date fechaAsignada;
    
	@Temporal(TemporalType.TIME)
	private Date horaInicio;
	
	private String horaAproximadaDuracion;


	private String direccion;

	@ManyToOne(fetch = FetchType.LAZY) // carga perezosa , cada vez que se invoque el metodo o atributo region o //
										// getRegion ahi se va a realizar la carga
	@JoinColumn(name = "regionC_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" }) // se ignoran los atributos propios de hibernate
	private Region region;

	private String prioridad;

	private String tipo;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "mantenimientoc_id")
	private List<ItemMantenimiento> items;
	
	public MantenimientoCamion() {
		items = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEmpresa() {
		return empresa;
	}
	

	public String getHoraAproximadaDuracion() {
		return horaAproximadaDuracion;
	}

	public void setHoraAproximadaDuracion(String horaAproximadaDuracion) {
		this.horaAproximadaDuracion = horaAproximadaDuracion;
	}
    
	
	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public Date getFechaAsignada() {
		return fechaAsignada;
	}

	public void setFechaAsignada(Date fechaAsignada) {
		this.fechaAsignada = fechaAsignada;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
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

	

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
