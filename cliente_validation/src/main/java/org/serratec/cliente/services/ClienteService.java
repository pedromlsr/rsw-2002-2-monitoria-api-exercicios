package org.serratec.cliente.services;

import java.util.List;

import org.serratec.cliente.entities.Cliente;
import org.serratec.cliente.exceptions.NotFoundException;
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
		
		if (!clienteRepository.existsById(id)) {
			throw new NotFoundException("O cliente de id " + id + " não foi encontrado.");
		}
		
		return clienteRepository.findById(id).get();
	}

	public Cliente saveCliente(Cliente cliente) {
		
		return clienteRepository.save(cliente);
	}
	
	public Cliente updateCliente(Cliente cliente, Long id) {
		
		if (!clienteRepository.existsById(id)) {
			throw new NotFoundException("O cliente de id " + id + " não foi encontrado.");
		}
		
		cliente.setIdCliente(id);
		
		return clienteRepository.save(cliente);
	}

	public void deleteCliente(Long id) {
		
		if (!clienteRepository.existsById(id)) {
			throw new NotFoundException("O cliente de id " + id + " não foi encontrado.");
		}
		
		clienteRepository.deleteById(id);
	}
}
