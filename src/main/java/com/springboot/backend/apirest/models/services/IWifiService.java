package com.springboot.backend.apirest.models.services;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.backend.apirest.models.entity.Wifi;

public interface IWifiService {

public List<Wifi> findAll();
	
	public Page<Wifi> findAll(Pageable pageable);
	
	public Wifi findById(Long id);
	
	public Wifi save(Wifi wifi);
	
	public void delete(Long id);
	
	public List<Wifi> findWifiByColonia(String term);
	
	public Page<Wifi> findByProximity(double lat, double lon, Pageable pageable);
	
    }

