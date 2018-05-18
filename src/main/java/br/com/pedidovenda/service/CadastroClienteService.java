package br.com.pedidovenda.service;

import javax.inject.Inject;

import br.com.pedidovenda.model.Cliente;
import br.com.pedidovenda.repository.Clientes;
import br.com.pedidovenda.util.jpa.Transactional;

public class CadastroClienteService {

	@Inject
	private Clientes clientes;
	
	@Transactional
	public Cliente guardar(Cliente cliente) {
		return this.clientes.guardar(cliente);
	}
}
