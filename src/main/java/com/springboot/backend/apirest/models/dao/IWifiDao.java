package com.springboot.backend.apirest.models.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.backend.apirest.models.entity.Wifi;

public interface IWifiDao extends JpaRepository<Wifi, Long>{

}
