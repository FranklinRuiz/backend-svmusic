package com.sv.music.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sv.music.dao.ICancionDao;
import com.sv.music.model.Cancion;
import com.sv.music.service.ICancionService;

@Service
public class CancionServiceImpl  implements ICancionService{

	@Autowired
	private ICancionDao cancionDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cancion> listaCanciones() {
		return (List<Cancion>) cancionDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Cancion cancion(Long idCancion) {
		return cancionDao.findById(idCancion).orElse(null);
	}

}
