package com.sv.music.dao;

import org.springframework.data.repository.CrudRepository;

import com.sv.music.model.Cancion;

public interface ICancionDao extends CrudRepository<Cancion, Long>{

}
