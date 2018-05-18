package br.com.pedidovenda.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.pedidovenda.model.Cliente;
import br.com.pedidovenda.model.TipoPessoa;
import br.com.pedidovenda.service.CadastroClienteService;
import br.com.pedidovenda.util.jsf.FacesUtil;

@Named("cadastroClienteBean")
@RequestScoped
public class CadastroClienteBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Cliente cliente;

	@Inject
	private CadastroClienteService cadastroClienteService;

	public CadastroClienteBean() {
		limpar();
	}

	public void guardar() {
		try{
			cadastroClienteService.guardar(this.cliente);
			FacesUtil.addInfoMessage("Cliente cadastrado com sucesso!");
			limpar();
		}catch(Exception e){
			FacesUtil.addErrorMessage("Erro ao cadastrar.");
		}
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	private void limpar() {
		this.cliente = new Cliente();
	}

	public TipoPessoa[] getTiposPessoa() {
		return TipoPessoa.values();
	}
}
