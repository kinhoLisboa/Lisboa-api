package com.algaworks.lisboa.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.algaworks.lisboa.dominio.Cliente;

@Repository
public interface ClienteRepositorio extends JpaRepositoryImplementation<Cliente, Long> {
	
	List<Cliente> findByNome(String nome);
	List<Cliente> findByNomeContaining(String nome);
}
