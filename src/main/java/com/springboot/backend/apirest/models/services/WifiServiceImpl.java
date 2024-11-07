package com.springboot.backend.apirest.models.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.backend.apirest.models.dao.IWifiDao;
import com.springboot.backend.apirest.models.entity.Wifi;



@Service
public class WifiServiceImpl implements IWifiService {


	// Inyección del DAO (Data Access Object) para realizar operaciones CRUD en la base de datos
	@Autowired
	private IWifiDao wifiDao;

	@Override
	@Transactional(readOnly = true) // Transacción de solo lectura para mejorar el rendimiento
	public List<Wifi> findAll() {
		// Retorna una lista de todos los puntos de acceso WiFi
		return (List<Wifi>) wifiDao.findAll();
	}

	@Override
	@Transactional(readOnly = true) // Transacción de solo lectura
	public Page<Wifi> findAll(Pageable pageable) {
		// Retorna una lista paginada de puntos de acceso WiFi
		return wifiDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true) // Transacción de solo lectura
	public Wifi findById(Long id) {
		// Busca un punto de acceso WiFi por su ID, retorna null si no se encuentra
		return wifiDao.findById(id).orElse(null);
	}

	@Override
	@Transactional // Transacción de lectura/escritura
	public Wifi save(Wifi wifi) {
		// Guarda o actualiza un punto de acceso WiFi en la base de datos
		return wifiDao.save(wifi);
	}

	@Override
	@Transactional // Transacción de lectura/escritura
	public void delete(Long id) {
		// Elimina un punto de acceso WiFi de la base de datos por su ID
		wifiDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true) // Transacción de solo lectura
	public List<Wifi> findWifiByColonia(String term) {
		// Busca y retorna una lista de puntos de acceso WiFi que coinciden con el término de búsqueda de colonia
		return wifiDao.findByColonia(term);
	}

	// Método para buscar puntos de acceso WiFi por proximidad a una coordenada dada, con soporte para paginación
	public Page<Wifi> findByProximity(double lat, double lon, Pageable pageable) {
		return wifiDao.findByProximity(lat, lon, pageable);
	}
}