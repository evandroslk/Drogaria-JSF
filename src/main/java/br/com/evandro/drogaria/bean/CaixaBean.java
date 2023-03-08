package br.com.evandro.drogaria.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import br.com.evandro.drogaria.dao.CaixaDAO;
import br.com.evandro.drogaria.dao.FuncionarioDAO;
import br.com.evandro.drogaria.domain.Caixa;
import br.com.evandro.drogaria.domain.Funcionario;

@ManagedBean
@ViewScoped
public class CaixaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Caixa caixa;

	private List<Funcionario> funcionarios;

	private ScheduleModel caixas;

	@PostConstruct
	public void listar() {
		caixas = new DefaultScheduleModel();
	}

	public void novo(SelectEvent evento) {
		
		caixa = new Caixa();
		caixa.setDataAbertura((Date) evento.getObject());
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarios = funcionarioDAO.listar();
	}
	
	public void salvar() {
		CaixaDAO caixaDAO = new CaixaDAO();
		caixaDAO.salvar(caixa);
		Messages.addGlobalInfo("Caixa aberto com sucesso");
	}
	
	public ScheduleModel getCaixas() {
		return caixas;
	}

	public void setCaixas(ScheduleModel caixas) {
		this.caixas = caixas;
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

}
