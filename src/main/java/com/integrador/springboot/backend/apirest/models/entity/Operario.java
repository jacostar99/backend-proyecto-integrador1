package com.integrador.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // SE INDICA QUE ES UNA CLASE ENTITY DE JPA
@Table(name = "operarios") // INDICAR EL NOMBRE DE LA TABLA

public class Operario implements Serializable { // SE PUEDE CONVERTIR DE UN OBJETO JAVA , A UNA ESTRUCTURA JSON Y
												// TAMBIEN PERMITE GUARDAR EL OBJETO EN UNA SESION HTTP

	@Id // indicar que el campo id va a ser la llave primaria de la base de datos
	@GeneratedValue(strategy = GenerationType.IDENTITY) // INDICAR COMO SE GENERA ESTA LLAVE EN LA BASE DE DATOS
	private long id;

	// validaciones anotaciones de api validator
	@NotEmpty
	@Size(min = 4, max = 12)
	@Column(nullable = false)
	private String nombre;

	@NotEmpty
	@Column(nullable = false)
	private String apellido;

	@NotEmpty
	private String estado;
	
	@Temporal(TemporalType.TIME)
	private Date horaFinTurno;

	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;
	
	private String foto;

	@Column(unique = true, length = 20)
	private String username;

	@Column(length = 60)
	private String password;

    
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "operarios_roles", joinColumns = @JoinColumn(name = "operario_id"), inverseJoinColumns = @JoinColumn(name = "role_id"), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "operario_id", "role_id" }) })
	public List<Role> roles;

	@NotNull(message = "La region no puede estar vacia")
	@ManyToOne(fetch = FetchType.LAZY) // carga perezosa , cada vez que se invoque el metodo o atributo region o //
										// getRegion ahi se va a realizar la carga
	@JoinColumn(name = "region_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" }) // se ignoran los atributos propios de hibernate //
																		// del objeto proxy que esta relacionado a //
																		// objeto region
	private Region region; // la relacion es atravez del atributo

	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "operario" }, allowSetters = true)
	@ManyToMany(fetch = FetchType.LAZY,  mappedBy = "operario", cascade = CascadeType.ALL)	
	private List<Mantenimiento> mantenimientos;

	

	public Operario() {
		this.mantenimientos = new ArrayList<>();
	
	}

	// METODOS ACCESORES

	@PrePersist
	public void prePersist() {
		fechaCreacion = new Date();
		this.roles = Arrays.asList(new Role((long)1, "ROLE_USER"));
	}
	

	public String getNombre() {
		return nombre;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	

	public Date getHoraFinTurno() {
		return horaFinTurno;
	}

	public void setHoraFinTurno(Date horaFinTurno) {
		this.horaFinTurno = horaFinTurno;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public List<Mantenimiento> getMantenimientos() {
		return mantenimientos;
	}

	public void setMantenimientos(List<Mantenimiento> mantenimientos) {
		this.mantenimientos = mantenimientos;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {

		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		BCryptPasswordEncoder en = new BCryptPasswordEncoder();
		String pe = en.encode(password);

		this.password = pe;
	}

	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}



	// ATRIBUTO ESTATICO QUE ES REQUERIDO CUANDO SE IMPLEMENTA EL SERIALIZABLE
	private static final long serialVersionUID = 1L;

}
