package org.serratec.cliente.services;

import java.util.List;

import org.serratec.cliente.entities.Cliente;
import org.serratec.cliente.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	public List<Cliente> findAllClientes() {
		
		return clienteRepository.findAll();
	}

	public Cliente findClienteById(Long id) {
		
		return clienteRepository.existsById(id) ? clienteRepository.findById(id).get() : null;
	}

	public Cliente saveCliente(Cliente cliente) {
		
		return clienteRepository.save(cliente);
	}
	
	public Cliente updateCliente(Cliente cliente, Long id) {
		
		if (clienteRepository.existsById(id)) {
			cliente.setIdCliente(id);
			return clienteRepository.save(cliente);
		}
		
		return null;
	}

	public void deleteCliente(Long id) {
		
		if (clienteRepository.existsById(id)) {
			clienteRepository.deleteById(id);
		}
	}
	
}
