package com.integrador.springboot.backend.apirest.models.service;

import java.util.List;

import com.integrador.springboot.backend.apirest.models.entity.Reporte;

public interface IReporteService {
	
	public List<Reporte> findAllReportes();
	
	public Reporte findReporteById(Long id);
	
	public Reporte saveReporte(Reporte reporte);
	
	public void deleteReporte(Long id); //RECIBE EL ID DEL REPORTE QUE SE QUIERE ELIMINAR

}
