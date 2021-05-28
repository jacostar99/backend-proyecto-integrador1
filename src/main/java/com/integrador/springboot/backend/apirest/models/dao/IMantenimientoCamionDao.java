package com.integrador.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.integrador.springboot.backend.apirest.models.entity.MantenimientoCamion;

public interface IMantenimientoCamionDao extends CrudRepository<MantenimientoCamion, Long> {

}
