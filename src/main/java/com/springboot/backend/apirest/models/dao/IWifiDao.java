package com.springboot.backend.apirest.models.dao;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.backend.apirest.models.entity.Wifi;

public interface IWifiDao extends JpaRepository<Wifi, Long>{
	
	
	
	public List<Wifi> findByColonia(String term);

	
	@Query(value = "SELECT w.*, " +
            "(6371 * acos(cos(radians(:lat)) * cos(radians(w.latitud)) " +
            "* cos(radians(w.longitud) - radians(:lon)) " +
            "+ sin(radians(:lat)) * sin(radians(w.latitud)))) AS distance " +
            "FROM wifi w " +
            "ORDER BY distance", 
    nativeQuery = true)
Page<Wifi> findByProximity(@Param("lat") double lat, 
                        @Param("lon") double lon, 
                        Pageable pageable);


	
	
	//@Query("select p from Wifi p where p.colonia like %?1%")
	//public List<Wifi> findByNombreContainingIgnoreCase(String term);

}
