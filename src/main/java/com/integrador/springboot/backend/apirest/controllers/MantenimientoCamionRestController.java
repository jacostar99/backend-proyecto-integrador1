package com.integrador.springboot.backend.apirest.controllers;

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

import com.integrador.springboot.backend.apirest.models.entity.Insumo;
import com.integrador.springboot.backend.apirest.models.entity.Mantenimiento;
import com.integrador.springboot.backend.apirest.models.entity.MantenimientoCamion;
import com.integrador.springboot.backend.apirest.models.service.IOperarioService;

@CrossOrigin(origins= {"*"})
@RestController
@RequestMapping("/api")
public class MantenimientoCamionRestController {
	
	@Autowired
	private IOperarioService operarioService;
	
	@GetMapping("/mantenimientosc") // URL DE SEGUNDO NIVEL
	public List<MantenimientoCamion> index() {
		return operarioService.findAllMantenimientosC();
	}
	
	@GetMapping("/mantenimientosc/{id}")
	@ResponseStatus(code=HttpStatus.OK)
	public MantenimientoCamion show(@PathVariable Long id) {	
		return operarioService.findMantenimientoCById(id);
	}
	
	@DeleteMapping("/mantenimientosc/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteC(@PathVariable Long id) {
		operarioService.deleteMantenimientoCById(id);
		
	}
	
	@GetMapping("/mantenimientosc/filtrar-insumos/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<Insumo> filtrarInsumos(@PathVariable String term){
		return operarioService.findInsumoByNombre(term);
		
		
	}
	
	@PostMapping("/mantenimientosc")
	@ResponseStatus(HttpStatus.CREATED)
	public MantenimientoCamion crear(@RequestBody MantenimientoCamion mantenimientoC) {
		return operarioService.saveMantenimientoC(mantenimientoC);
		
		
	}

}
