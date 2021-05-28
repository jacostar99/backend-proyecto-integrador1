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
import com.integrador.springboot.backend.apirest.models.entity.Operario;
import com.integrador.springboot.backend.apirest.models.service.IOperarioService;

@CrossOrigin(origins= {"*"})
@RestController
@RequestMapping("/api")
public class MantenimientoRestController {
	
	@Autowired
	private IOperarioService operarioService;
	
	
	@GetMapping("/mantenimientos") // URL DE SEGUNDO NIVEL
	public List<Mantenimiento> index() {
		return operarioService.findAllMantenimientos();
	}
	
	
	@GetMapping("/mantenimientos/{id}")
	@ResponseStatus(code=HttpStatus.OK)
	public Mantenimiento show(@PathVariable Long id) {	
		return operarioService.findMantenimientoById(id);
	}
	
	@DeleteMapping("/mantenimientos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		operarioService.deleteMantenimientoById(id);
		
	}
	
	@GetMapping("/mantenimientos/filtrar-insumos/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<Insumo> filtrarInsumos(@PathVariable String term){
		return operarioService.findInsumoByNombre(term);
		
		
	}
    
	@PostMapping("/mantenimientos")
	@ResponseStatus(HttpStatus.CREATED)
	public Mantenimiento crear(@RequestBody Mantenimiento mantenimiento) {
		return operarioService.saveMantenimiento(mantenimiento);
		
		
	}
		
	

}
