package com.integrador.springboot.backend.apirest.models.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.integrador.springboot.backend.apirest.models.dao.IInsumoDao;
import com.integrador.springboot.backend.apirest.models.dao.IMantenimientoCamionDao;
import com.integrador.springboot.backend.apirest.models.dao.IMantenimientoDao;
import com.integrador.springboot.backend.apirest.models.dao.IOperarioDao;
import com.integrador.springboot.backend.apirest.models.dao.IReporteDao;
import com.integrador.springboot.backend.apirest.models.entity.Insumo;
import com.integrador.springboot.backend.apirest.models.entity.Mantenimiento;
import com.integrador.springboot.backend.apirest.models.entity.MantenimientoCamion;
import com.integrador.springboot.backend.apirest.models.entity.Operario;
import com.integrador.springboot.backend.apirest.models.entity.Region;
import com.integrador.springboot.backend.apirest.models.entity.Reporte;
import com.integrador.springboot.backend.apirest.models.entity.Role;

@Service
@Transactional
public class OperarioServiceImpl implements IOperarioService, UserDetailsService { // ,UserDetailsService USER DETAILS
																					// ME RETORNA EL USUARIO EL SPRING
																					// SECURITY

	@Autowired // inyeccion de dependencia
	private IOperarioDao operarioDao; // CON ESTE ATRIBUTO ACCEDO A LA LISTA DE OPERARIOS

	private Logger logger = LoggerFactory.getLogger(OperarioServiceImpl.class);

	@Autowired
	private IMantenimientoDao mantenimientoDao;

	@Autowired
	private IInsumoDao insumoDao;

	@Autowired
	private IReporteDao reporteDao;

	@Autowired
	private IMantenimientoCamionDao mantenimientoCamionDao;

	@Override
	@Transactional(readOnly = true) // SOLO LECTURA
	public List<Operario> findAll() {
		// COMO EL METODO RETORNA UN ITERABLE ENTONCES HAY QUE CONVERTIRLO A UN LIST
		// MEDIANTE UN CAST
		return (List<Operario>) operarioDao.findAll();
	}

	@Override
	@Transactional(readOnly = true) // SOLO LECTURA
	public Page<Operario> findAll(Pageable pageable) {
		return operarioDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true) // SOLO LECTURA
	public Operario findById(Long id) {
		return operarioDao.findById(id).orElse(null); // SI LO ENCUENTRA RETORNA EL OBJETO OPERARIO , DE LO CONTRARIO
														// RETORNA NULL
	}

	@Override
	@Transactional
	public Operario save(Operario operario) {
		// operario.setRoles(Arrays.asList( new Role((long)1, "ROLE_USER")));
		operarioDao.save(operario);
		return operario; // VA A RETORNAR LA ENTIDAD GUARDADA EN LA BD QUE CONTIENE EL ID
	}

	@Override
	@Transactional
	public void delete(Long id) {
		operarioDao.deleteById(id); // ELIMINA POR ID
	}

	@Override
	@Transactional(readOnly = true) // SOLO LECTURA
	public List<Region> findAllRegiones() {
		return operarioDao.findAllRegiones();
	}

	@Override
	@Transactional(readOnly = true) // SOLO LECTURA
	public List<Role> findAllRoles() {
		return operarioDao.findAllRoles();
	}

	@Override
	@Transactional(readOnly = true) // SOLO LECTURA
	public Mantenimiento findMantenimientoById(Long id) {
		return mantenimientoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional() // SOLO LECTURA
	public Mantenimiento saveMantenimiento(Mantenimiento mantenimiento) {
		return mantenimientoDao.save(mantenimiento);
	}

	@Override
	@Transactional // SOLO LECTURA
	public void deleteMantenimientoById(Long id) {
		mantenimientoDao.deleteById(id);
	}
	/// DESDE AQUI

	@Override
	@Transactional(readOnly = true) // SOLO LECTURA
	public MantenimientoCamion findMantenimientoCById(Long id) {
		return mantenimientoCamionDao.findById(id).orElse(null);
	}

	@Override
	@Transactional() // SOLO LECTURA
	public MantenimientoCamion saveMantenimientoC(MantenimientoCamion mantenimientoC) {
		return mantenimientoCamionDao.save(mantenimientoC);
	}

	@Override
	@Transactional // SOLO LECTURA
	public void deleteMantenimientoCById(Long id) {
		mantenimientoCamionDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true) // SOLO LECTURA
	public List<Insumo> findInsumoByNombre(String term) {
		return insumoDao.findByNombreContainingIgnoreCase(term);
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Operario operario = operarioDao.findByUsername(username);

		if (operario == null) {
			logger.error("Error en el login: no existe el operario '" + username + "' en el sistema!");
			throw new UsernameNotFoundException(
					"Error en el login: no existe el operario '" + username + "' en el sistema!");
		}

		List<GrantedAuthority> authorities = operario.getRoles() // GRANTEDAUTHORITY para autorizar/controlar un acceso.
				.stream().map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> logger.info("Rol: " + authority.getAuthority())).collect(Collectors.toList());

		return new User(operario.getUsername(), operario.getPassword(), true, true, true, true, authorities);

	}

	@Override
	public List<Mantenimiento> findAllMantenimientos() {
		return (List<Mantenimiento>) mantenimientoDao.findAll();
	}

	@Override
	public List<MantenimientoCamion> findAllMantenimientosC() {
		return (List<MantenimientoCamion>) mantenimientoCamionDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Operario findByUsername(String username) {
		return operarioDao.findByUsername(username);
	}

	@Override
	@Transactional
	public List<Operario> findOperarioByNombre(String term) {
		return operarioDao.findByNombre(term);
	}

}
