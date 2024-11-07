package com.springboot.backend.apirest.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

import com.springboot.backend.apirest.models.entity.Wifi;
import com.springboot.backend.apirest.models.services.IWifiService;


@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class WifiRestController {
	
	@Autowired
	private IWifiService wifiService;

	@GetMapping("/wifies")
	public List<Wifi> index() {
		return wifiService.findAll();
	}
	
	@GetMapping("/wifies/page/{page}")
	public Page<Wifi> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 4);
		return wifiService.findAll(pageable);
	}
	
	@GetMapping("/wifies/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Wifi wifi = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			wifi = wifiService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(wifi == null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Wifi>(wifi, HttpStatus.OK);
	}
	
	@PostMapping("/wifies")
	public ResponseEntity<?> create(@Valid @RequestBody Wifi wifi, BindingResult result) {
		
		Wifi wifiNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			wifiNew = wifiService.save(wifi);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El wifi-point ha sido creado con éxito!");
		response.put("wifi-point", wifiNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/wifies/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Wifi wifi, BindingResult result, @PathVariable Long id) {

		Wifi wifiActual = wifiService.findById(id);

		Wifi wifiUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (wifiActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el wifi ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			wifiActual.setAlcaldia(wifi.getAlcaldia());
			wifiActual.setColonia(wifi.getColonia());
			wifiActual.setFechaInstalacion(wifi.getFechaInstalacion());
			wifiActual.setLatitud(wifi.getLatitud());
			wifiActual.setLongitud(wifi.getLongitud());
			wifiActual.setPrograma(wifi.getPrograma());
			wifiActual.setId(wifi.getId());

			wifiUpdated = wifiService.save(wifiActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el wifi-poin en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El wifi-poin ha sido actualizado con éxito!");
		response.put("wifi-poin", wifiUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/wifies/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
		  wifiService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el wifi-point de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El wifi-point eliminado con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	

	@GetMapping("/wifi/filtrar-colonia/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<Wifi> filtrarColonia(@PathVariable String term){
		return wifiService.findWifiByColonia(term);
	}
	
	
	
	
	 @GetMapping("/wifi/proximidad")
	    @ResponseStatus(HttpStatus.OK)
	    public Page<Wifi> obtenerWifiPorProximidad(@RequestParam double lat, 
	                                               @RequestParam double lon, 
	                                               @RequestParam(defaultValue = "0") int page, 
	                                               @RequestParam(defaultValue = "10") int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        return wifiService.findByProximity(lat, lon, pageable);
	    }
	

	
}
