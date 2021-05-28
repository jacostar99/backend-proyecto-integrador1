package com.integrador.springboot.backend.apirest.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.integrador.springboot.backend.apirest.models.entity.Insumo;
import com.integrador.springboot.backend.apirest.models.entity.Mantenimiento;
import com.integrador.springboot.backend.apirest.models.entity.MantenimientoCamion;
import com.integrador.springboot.backend.apirest.models.entity.Operario;
import com.integrador.springboot.backend.apirest.models.entity.Region;
import com.integrador.springboot.backend.apirest.models.entity.Reporte;
import com.integrador.springboot.backend.apirest.models.entity.Role;

public interface IOperarioService {
	//METODOS DEL CRUD
	
		public List<Operario> findAll();
		
		public Page<Operario> findAll(Pageable pageable); //page es parecido al listar pero atravez de rangos
		
		 
		public Operario findById(Long id); //RECIBE EL ID Y LO BUSCA
		
		
        public Operario save(Operario operario); //RECIBE EL OPERARIO QUE SE VA A ALMACENAR Y VA A RETORNAR EL OPERARIO GUARDADO.
        
        
        public void delete(Long id); //RECIBE EL ID DEL OPERARIO QUE SE QUIERE ELIMINAR
        
        public List<Region> findAllRegiones();
        
        public Mantenimiento findMantenimientoById(Long id);
        
        public Mantenimiento saveMantenimiento(Mantenimiento mantenimiento);
        
        public void deleteMantenimientoById(Long id);
        
    	public List<Insumo> findInsumoByNombre(String term);
    	
    	public List<Mantenimiento> findAllMantenimientos();
    	
    	public List<Role> findAllRoles();
    	
    	public MantenimientoCamion findMantenimientoCById(Long id);
    	
    	public MantenimientoCamion saveMantenimientoC(MantenimientoCamion mantenimientoC);
    	
    	public void deleteMantenimientoCById(Long id);
    	
    	public List<MantenimientoCamion> findAllMantenimientosC();
    	
    		
    	public Operario findByUsername(String username);
    	
    	public List <Operario> findOperarioByNombre(String term);

        
        
        
        
        
        
     




}
