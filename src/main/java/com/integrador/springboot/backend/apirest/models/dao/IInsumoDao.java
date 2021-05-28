package com.integrador.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.integrador.springboot.backend.apirest.models.entity.Insumo;

public interface IInsumoDao extends CrudRepository<Insumo,Long> {
	
	
	@Query("select i from Insumo i where i.nombre like %?1%") //porcentaje se agrega en ambo lados para que busque en cualquier parte de la cadena
	public List<Insumo> findByNombre(String term);
	
	//Buscar en cualquier parte de la cadena
	public List<Insumo> findByNombreContainingIgnoreCase(String term);
	
	//Filtra  las palabras que inicien en un caracter
	public List<Insumo> findByNombreStartingWithIgnoreCase(String term);

	
	


}
