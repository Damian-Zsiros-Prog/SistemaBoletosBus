package com.nuevo.springboot.reservas.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuevo.springboot.reservas.app.models.dao.IBoletoDao;
import com.nuevo.springboot.reservas.app.models.entity.Boleto;


@Service
public class BoletoService implements GenericDataService <Boleto> {

	@Autowired
	private IBoletoDao boletoDao;
	
	@Override
	@Transactional
	public void save(Boleto boleto) {
		boletoDao.save(boleto);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Boleto findOne(Integer id) {
		return boletoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		boletoDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Boleto> findAll() {
		return (List<Boleto>) boletoDao.findAll();
	}

	
}
