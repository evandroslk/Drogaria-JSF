package br.com.evandro.drogaria.bean;

import java.io.Serializable;

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

	public void novo() {
		estado = new Estado();
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

}
