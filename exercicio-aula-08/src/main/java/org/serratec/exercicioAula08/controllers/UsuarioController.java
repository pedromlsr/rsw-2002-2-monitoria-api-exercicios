package org.serratec.exercicioAula08.controllers;

import java.net.URI;
import java.util.List;

import org.serratec.exercicioAula08.dtos.UsuarioDTO;
import org.serratec.exercicioAula08.dtos.UsuarioInserirDTO;
import org.serratec.exercicioAula08.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listar() {
		
		return ResponseEntity.ok(usuarioService.findAll());
	}

	@PostMapping
	public ResponseEntity<UsuarioDTO> inserir(@RequestBody UsuarioInserirDTO usuario) {
		
		UsuarioDTO usuarioDTO = usuarioService.inserir(usuario);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(usuarioDTO.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(usuarioDTO);
	}
}
