package com.integrador.springboot.backend.apirest.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.integrador.springboot.backend.apirest.models.dao.IReporteDao;
import com.integrador.springboot.backend.apirest.models.entity.Reporte;

@Service
public class ReporteServiceImpl implements IReporteService {

	@Autowired
	private IReporteDao reporteDao;

	@Override
	@Transactional
	public Reporte saveReporte(Reporte reporte) {
		return reporteDao.save(reporte);
	}

	@Override
	@Transactional(readOnly = true) // SOLO LECTURA
	public Reporte findReporteById(Long id) {
		return reporteDao.findById(id).orElse(null);

	}

	@Override
	public List<Reporte> findAllReportes() {
		return (List<Reporte>) reporteDao.findAll();
	}

	@Override
	public void deleteReporte(Long id) {
		reporteDao.deleteById(id);
	}

}
