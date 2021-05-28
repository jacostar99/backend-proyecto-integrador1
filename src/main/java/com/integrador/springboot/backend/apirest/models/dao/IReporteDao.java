package com.integrador.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.integrador.springboot.backend.apirest.models.entity.Reporte;

public interface IReporteDao extends CrudRepository<Reporte,Long> {
	

}
