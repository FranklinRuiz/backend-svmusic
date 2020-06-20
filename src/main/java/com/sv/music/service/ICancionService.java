package com.sv.music.service;

import java.util.List;

import com.sv.music.model.Cancion;

public interface ICancionService {

	public List<Cancion> listaCanciones();
	public Cancion cancion(Long idCancion);
	
}
