package com.integrador.springboot.backend.apirest.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.integrador.springboot.backend.apirest.models.entity.Mantenimiento;
import com.integrador.springboot.backend.apirest.models.entity.Operario;
import com.integrador.springboot.backend.apirest.models.entity.Region;
import com.integrador.springboot.backend.apirest.models.entity.Role;
import com.integrador.springboot.backend.apirest.models.service.IOperarioService;

@CrossOrigin(origins =  "*" ) // SE INDICA EL DOMINIO DEL SERVIDOR O SE DA ACCESO CON "*" , CON ESTO YA SE DA ACCESO PARA
													// ENVIAR Y RECIBIR DATOS , TAMBIEN SE PUEDEN INDICAR LOS METODOS
													// PERMITIDOS
@RestController
@RequestMapping("/api") // SE MAPEA CON API TODOS LOS METODOS DEL REST , URL DE PRIMER NIVEL
public class OperarioRestController {
	@Autowired
	private IOperarioService operarioService;

	// METODO PARA LISTAR LOS OPERARIOS

	@GetMapping("/operarios") // URL DE SEGUNDO NIVEL
	public List<Operario> index() {
		return operarioService.findAll();
	}
	
	
	@GetMapping("/operarios/page/{page}") // URL DE SEGUNDO NIVEL
	public Page<Operario> index(@PathVariable Integer page) {
		return operarioService.findAll(PageRequest.of(page, 4));

	}
    
	//@Secured("ROLE_ADMIN")
	@GetMapping("/operarios/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {

		Operario operario = null;
		Map<String, Object> response = new HashMap<>(); // MAP ES LA INTERFAZ , HASHMAP ES LA IMPLEMENTACION

		try { // INTENTA EJECUTAR , SI SALE BIEN CONTINUA, SI LANZA ERROR SE MANEJA
			operario = operarioService.findById(id);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar la consulta en la base de datos");
			response.put("Mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (operario == null) {
			response.put("Mensaje",
					"El operario con el ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<Operario>(operario, HttpStatus.OK);
	}
	
	//@Secured("ROLE_ADMIN")
	@PostMapping("/operarios")                                                                                // PARA CREAR UN NUEVO REGISTRO	
	public ResponseEntity<?> create(@Valid @RequestBody Operario operario,BindingResult result) {
				
		Operario operarioNew = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {	
			List<String> errors = new ArrayList<>();               //lista que va a contener los mensajes de error
			
			for (FieldError err: result.getFieldErrors()) {               //se itera por cada fielderror , field error //retorna una lista
				errors.add("El campo: '"+ err.getField() +"' "+err.getDefaultMessage());        //se agrega el mensaje de error 			
			}			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		try {

			operarioNew = operarioService.save(operario);
			
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar el insert en la base de datos");
			response.put("Mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("Mensaje", "El operario ha sido creado con exito");
		response.put("Operario ", operarioNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
    
	//@Secured("ROLE_ADMIN")
	@PutMapping("/operarios/{id}") // PARA EDITAR
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid @RequestBody Operario operario,BindingResult result, @PathVariable Long id) {
		Operario operarioActual = operarioService.findById(id);
		Operario operarioUdpated = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = new ArrayList<>(); //lista que va a contener los mensajes de error
			
			for (FieldError err: result.getFieldErrors()) { //se itera por cada fielderror , field error //retorna una lista
				errors.add("El campo: '"+ err.getField() +"' "+err.getDefaultMessage()); //se agrega el mensaje de error 
				
			}
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}


		if (operarioActual == null) {
			response.put("Mensaje", "El operario con el ID: "
					.concat(id.toString().concat(" no existe en la base de datos por lo tanto no se puede editar !!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		}
		try {
			operarioActual.setApellido(operario.getApellido());
			operarioActual.setNombre(operario.getNombre());
			operarioActual.setEstado(operario.getEstado());
			operarioActual.setFechaCreacion(operario.getFechaCreacion());
			operarioActual.setRegion(operario.getRegion());
			operarioActual.setHoraFinTurno(operario.getHoraFinTurno());
			operarioActual.setUsername(operario.getUsername());
			//operarioActual.setRoles(operario.getRoles());


			operarioUdpated = operarioService.save(operarioActual);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al actualizar el operario en la base de datos");
			response.put("Mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("Mensaje", "El operario ha sido actualizado con exito");
		response.put("Operario ", operarioUdpated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
    
	//@Secured("ROLE_ADMIN")
	@DeleteMapping("/operarios/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
        try {
        	Operario operario = operarioService.findById(id);
        	String nombreFotoAnterior = operario.getFoto();
			if (nombreFotoAnterior !=null && nombreFotoAnterior.length()>0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
					
				}
				
			}
        	operarioService.delete(id);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al eliminar el operario en la base de datos");
			response.put("Mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
        response.put("Mensaje", "El operario ha sido eliminado con exito!");
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);

	}
	
	//@Secured("ROLE_ADMIN")
	@PostMapping("/operarios/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo,@RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		Operario operario = operarioService.findById(id);
		
		if (!archivo.isEmpty()) {
			String nombreArchivo = 	UUID.randomUUID().toString()+ "_" +archivo.getOriginalFilename().replace("", ""); //UUID genera un identificador ramdom que va a ser siempre unico.
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			
			try {
				Files.copy(archivo.getInputStream(),rutaArchivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del operario " + nombreArchivo);
				response.put("Mensaje", e.getMessage().concat(": ").concat(e.getCause().getMessage()));

				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

				
			}
			
			String nombreFotoAnterior = operario.getFoto();
			if (nombreFotoAnterior !=null && nombreFotoAnterior.length()>0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
					
				}
				
			}
			operario.setFoto(nombreArchivo);
			operarioService.save(operario);
			
			response.put("operario", operario);
			response.put("mensaje"	, "Has subido correctamente la imagen: " + nombreArchivo);
			
			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
		
	}
	//@Secured("ROLE_ADMIN")
	@GetMapping("uploads/img/{nombreFoto:.+}") //indica que el parametro lleva un punto y la  extension 
	public ResponseEntity<Resource> verFoto (@PathVariable String nombreFoto){
		Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
		Resource recurso = null;
		
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		if (!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error no se pudo cargar la imagen :" + nombreFoto);
			
		}
		
		HttpHeaders cabecera = new HttpHeaders(); //headers para forzar al recurso que se pueda descargar
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+recurso.getFilename() +"\""); //forzar a que se descargue para incluir en el html
		return new ResponseEntity<Resource>(recurso,cabecera,HttpStatus.OK); // se tiene la foto , para forzar la descarga , y el status
		
		
	}
	
	//@Secured({"ROLE_ADMIN" , "ROLE_USER"})
	@GetMapping("/operarios/regiones")
	public List<Region> listarRegiones() {
		return operarioService.findAllRegiones();

	}
	
	//LO QUE AGREGUEEEEEEEEEEEEEEEEEEEE
	@GetMapping("/operarios/roles")
	public List <Role> listarRoles(){
		return operarioService.findAllRoles();
		
	}
	
	@GetMapping("/operarios/filtrar-operarios/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List <Operario> filtrarOperarios(@PathVariable String term){
		return operarioService.findOperarioByNombre(term);
		
	}
	
	
	
	
 
	
}
