package org.serratec.exercicioAula08.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.serratec.exercicioAula08.dtos.UsuarioDTO;
import org.serratec.exercicioAula08.dtos.UsuarioInserirDTO;
import org.serratec.exercicioAula08.entities.Perfil;
import org.serratec.exercicioAula08.entities.Usuario;
import org.serratec.exercicioAula08.entities.UsuarioPerfil;
import org.serratec.exercicioAula08.exceptions.EmailException;
import org.serratec.exercicioAula08.exceptions.SenhaException;
import org.serratec.exercicioAula08.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PerfilService perfilService;

	public List<UsuarioDTO> findAll() {

		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();

		for (Usuario usuario : usuarios) {
			usuariosDTO.add(new UsuarioDTO(usuario));
		}

		return usuariosDTO;
	}

	@Transactional
	public UsuarioDTO inserir(UsuarioInserirDTO user) {

		if (!user.getSenha().equalsIgnoreCase(user.getConfirmaSenha())) {
			throw new SenhaException("Senha e Confirma Senha não são iguais");
		}

		if (usuarioRepository.findByEmail(user.getEmail()) != null) {
			throw new EmailException("Email já existente");
		}

		Usuario usuario = new Usuario();
		
		usuario.setNome(user.getNome());
		usuario.setEmail(user.getEmail());
		usuario.setSenha(user.getSenha());

		Set<UsuarioPerfil> perfis = new HashSet<>();
		
		for (Perfil perfil : user.getPerfis()) {
			perfil = perfilService.buscar(perfil.getId());
			UsuarioPerfil usuarioPerfil = new UsuarioPerfil(usuario, perfil, LocalDate.now());
			perfis.add(usuarioPerfil);
		}

		usuario.setUsuarioPerfis(perfis);
		usuario = usuarioRepository.save(usuario);
		
		return new UsuarioDTO(usuario);
	}
}
