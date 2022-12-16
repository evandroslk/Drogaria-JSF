package br.com.evandro.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.evandro.drogaria.dao.EstadoDAO;
import br.com.evandro.drogaria.domain.Estado;

@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Estado estado;

	private List<Estado> estados;

	public void novo() {
		estado = new Estado();
	}

	@PostConstruct
	public void listar() {
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os estados");
			e.printStackTrace();
		}
	}

	public void salvar() {
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.salvar(estado);

			novo();

			Messages.addGlobalInfo("Estado salvo com sucesso");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o estado");
			e.printStackTrace();
		}

	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

}
