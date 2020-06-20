package com.sv.music.controler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sv.music.model.Cancion;
import com.sv.music.service.ICancionService;

@RestController
@RequestMapping("/api")
public class MusicController {

	@Autowired
	private ICancionService cancionServicio;

	@GetMapping("/lista")
	public ResponseEntity<List<Cancion>> lista() {
		List<Cancion> cancion = new ArrayList<>();
		try {
			cancion = cancionServicio.listaCanciones();
		} catch (Exception e) {
			return new ResponseEntity<List<Cancion>>(cancion, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Cancion>>(cancion, HttpStatus.OK);
	}

	@GetMapping("/mp3/{idCancion}")
	public ResponseEntity<ByteArrayResource> download(@PathVariable("idCancion") Long idCancion) throws IOException {

		Cancion cancion = new Cancion();
		File file = null;
		ByteArrayResource resource = null;
		try {
			cancion = cancionServicio.cancion(idCancion);
			file = new File("D:\\canciones\\" + cancion.getPath());
			Path path = Paths.get(file.getAbsolutePath());
			resource = new ByteArrayResource(Files.readAllBytes(path));
		} catch (Exception e) {
			return new ResponseEntity<ByteArrayResource>(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
				.contentType(MediaType.parseMediaType("audio/mpeg"))
				.contentLength(file.length())
				.body(resource);
	}

	@GetMapping("/video")
	public ResponseEntity<ByteArrayResource> movie() throws IOException {

		File file = null;
		ByteArrayResource resource = null;
		try {
			file = new File("D:\\canciones\\Cover by Raon Lee.mp4");
			Path path = Paths.get(file.getAbsolutePath());
			resource = new ByteArrayResource(Files.readAllBytes(path));
		} catch (Exception e) {
			return new ResponseEntity<ByteArrayResource>(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
				.contentType(MediaType.parseMediaType("video/mp4"))
				.contentLength(file.length())
				.body(resource);
	}
	
}
