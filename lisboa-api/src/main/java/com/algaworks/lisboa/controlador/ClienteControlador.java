package com.algaworks.lisboa.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.lisboa.dominio.Cliente;
import com.algaworks.lisboa.repositorio.ClienteRepositorio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteControlador {
	
	private ClienteRepositorio clienteRepositorio;
	
	
	@GetMapping//("/clientes")
	public List<Cliente> listar() {
		return clienteRepositorio.findAll();
		
	}
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
	  return clienteRepositorio.findById(clienteId)
	  .map(ResponseEntity::ok)
	  .orElse(ResponseEntity.notFound().build());
	

	  //  if(cliente.isPresent()) {
		//  return ResponseEntity.ok(cliente.get());
		  
//	  }return ResponseEntity.notFound().build();
		
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar (@RequestBody Cliente cliente) {
		return clienteRepositorio.save(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @RequestBody Cliente cliente){
		if( !clienteRepositorio.existsById(clienteId)) {
			return  ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId);
		cliente = clienteRepositorio.save(cliente);
		return ResponseEntity.ok(cliente);
		
	}
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> excluir(@PathVariable Long clienteId){
		if(!clienteRepositorio.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		} 
		clienteRepositorio.deleteById(clienteId);
		return ResponseEntity.ok(null);
	}

}
