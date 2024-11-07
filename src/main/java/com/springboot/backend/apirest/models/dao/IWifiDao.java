package com.springboot.backend.apirest.models.dao;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.backend.apirest.models.entity.Wifi;

public interface IWifiDao extends JpaRepository<Wifi, Long>{


	// Método para buscar puntos de acceso WiFi por el nombre de la colonia.
// Este método toma un término de búsqueda y retorna una lista de puntos de acceso WiFi
// que coinciden con la colonia especificada.
	public List<Wifi> findByColonia(String term);

	@Query(
			value = "SELECT w.*, " +
					"(6371 * acos(cos(radians(:lat)) * cos(radians(w.latitud)) " +
					"* cos(radians(w.longitud) - radians(:lon)) " +
					"+ sin(radians(:lat)) * sin(radians(w.latitud)))) AS distance " +
					"FROM wifi w " +
					"ORDER BY distance",
			nativeQuery = true
	)
// Método para buscar puntos de acceso WiFi por proximidad a una coordenada dada (latitud y longitud).
// Utiliza una consulta SQL nativa que calcula la distancia entre el punto WiFi y una ubicación dada usando la fórmula de Haversine.
// La fórmula Haversine calcula la distancia entre dos puntos en una esfera (como la Tierra), basándose en sus coordenadas de latitud y longitud.
// Parámetros:
// - @Param("lat") lat: la latitud de la ubicación de referencia.
// - @Param("lon") lon: la longitud de la ubicación de referencia.
// - Pageable pageable: objeto para aplicar paginación en el resultado.
// Retorna una página de puntos de acceso WiFi ordenados por cercanía a las coordenadas especificadas.
	Page<Wifi> findByProximity(@Param("lat") double lat,
							   @Param("lon") double lon,
							   Pageable pageable);

}
