package com.integrador.springboot.backend.apirest.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.integrador.springboot.backend.apirest.models.entity.Mantenimiento;
import com.integrador.springboot.backend.apirest.models.entity.Reporte;
import com.integrador.springboot.backend.apirest.models.service.IOperarioService;
import com.integrador.springboot.backend.apirest.models.service.IReporteService;

@CrossOrigin(origins= {"*"})
@RestController
@RequestMapping("/api")
public class ReporteRestController {
	
	
	@Autowired
	private IReporteService reporteService;
	

	@GetMapping("/reportes") // URL DE SEGUNDO NIVEL
	public List<Reporte> index() {
		return reporteService.findAllReportes();
	}
	
	@PostMapping("/reportes")
	@ResponseStatus(HttpStatus.CREATED)
	public Reporte crear(@RequestBody Reporte reporte) {
		return reporteService.saveReporte(reporte);		
	}
	
	@GetMapping("/reportes/{id}")
	@ResponseStatus(code=HttpStatus.OK)
	public Reporte show(@PathVariable Long id) {
		return reporteService.findReporteById(id);
	}
	
	@DeleteMapping("/reportes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteReporte(@PathVariable Long id) {
		reporteService.deleteReporte(id);
	}
	
		

}
