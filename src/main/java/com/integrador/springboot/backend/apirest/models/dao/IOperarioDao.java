package com.integrador.springboot.backend.apirest.models.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.integrador.springboot.backend.apirest.models.entity.Mantenimiento;
import com.integrador.springboot.backend.apirest.models.entity.Operario;
import com.integrador.springboot.backend.apirest.models.entity.Region;
import com.integrador.springboot.backend.apirest.models.entity.Role;

public interface IOperarioDao extends JpaRepository<Operario, Long> {

	@Query("from Region") // consulta JPA para que retorne todas las regiones ,
	public List<Region> findAllRegiones();

	@Query("from Role")
	public List<Role> findAllRoles();
	
	
	@Query("select p from Operario p where p.nombre like ?1%")
	public List <Operario> findByNombre(String term);
	
	public List <Operario> findByNombreStartingWithIgnoreCase(String term); //buscar solo al comienzo de la cadena, que comience con



	public Operario findByUsername(String username);

}
