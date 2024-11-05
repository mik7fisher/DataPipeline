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
public class WifiServiceImpl implements IWifiService{

	
	@Autowired
	private IWifiDao wifiDao;

	@Override
	@Transactional(readOnly = true)
	public List<Wifi> findAll() {
		return (List<Wifi>) wifiDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Wifi> findAll(Pageable pageable) {
		return wifiDao.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Wifi findById(Long id) {
		return wifiDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Wifi save(Wifi wifi) {
		return wifiDao.save(wifi);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		wifiDao.deleteById(id);
	}

}
