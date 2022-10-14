package org.serratec.exercicioAula08.services;

import java.util.Optional;

import org.serratec.exercicioAula08.entities.Perfil;
import org.serratec.exercicioAula08.repositories.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {
	
	@Autowired
	private PerfilRepository perfilRepository;

	public Perfil buscar(Long id) {
		
		Optional<Perfil> perfil = perfilRepository.findById(id);
		
		return perfil.get();
	}
}
