package br.com.evandro.drogaria.bean;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.evandro.drogaria.dao.HistoricoDAO;
import br.com.evandro.drogaria.dao.ProdutoDAO;
import br.com.evandro.drogaria.domain.Historico;
import br.com.evandro.drogaria.domain.Produto;

@ManagedBean
@ViewScoped
public class HistoricoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Produto produto;
	private Boolean exibePainelDados;

	private Historico historico;

	@PostConstruct
	public void novo() {
		historico = new Historico();
		produto = new Produto();
		exibePainelDados = false;
	}

	public void buscar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto resultado = produtoDAO.buscar(produto.getCodigo());

			if (resultado == null) {
				exibePainelDados = false;
				Messages.addGlobalWarn("Não existe produto cadastrado para o código informado");
			} else {
				exibePainelDados = true;
				produto = resultado;
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar buscar o produto");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			historico.setHorario(new Date());
			historico.setProduto(produto);

			HistoricoDAO historicoDAO = new HistoricoDAO();
			historicoDAO.salvar(historico);

			Messages.addGlobalInfo("Histórico salvo com sucesso");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o histórico");
			e.printStackTrace();
		}
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Boolean getExibePainelDados() {
		return exibePainelDados;
	}

	public void setExibePainelDados(Boolean exibePainelDados) {
		this.exibePainelDados = exibePainelDados;
	}

	public Historico getHistorico() {
		return historico;
	}

	public void setHistorico(Historico historico) {
		this.historico = historico;
	}

}
