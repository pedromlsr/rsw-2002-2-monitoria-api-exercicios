package org.serratec.cliente.controllers;

import java.util.List;

import org.serratec.cliente.entities.Cliente;
import org.serratec.cliente.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	@GetMapping
	public List<Cliente> findAllClientes() {
		
		return clienteService.findAllClientes();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findClienteById(@PathVariable Long id) {
		
		Cliente cliente = clienteService.findClienteById(id);
		
		return (cliente != null) ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente) {
		
		Cliente clienteCadastrado = clienteService.saveCliente(cliente);
		
		return new ResponseEntity<>(clienteCadastrado, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente cliente, @PathVariable Long id) {
		
		Cliente clienteAtualizado = clienteService.updateCliente(cliente, id);
		
		return (clienteAtualizado != null) ? ResponseEntity.ok(clienteAtualizado) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCliente(@PathVariable Long id) {
		
		if (clienteService.findClienteById(id) == null) {
			return ResponseEntity.notFound().build();
		}
		
		clienteService.deleteCliente(id);
		
		return ResponseEntity.noContent().build();
	}
	
}
