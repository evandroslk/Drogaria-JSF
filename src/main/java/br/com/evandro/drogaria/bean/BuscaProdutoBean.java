package br.com.evandro.drogaria.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.evandro.drogaria.dao.ProdutoDAO;
import br.com.evandro.drogaria.domain.Produto;

@ManagedBean
@ViewScoped
public class BuscaProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Produto produto;
	private Boolean exibePainelDados;

	@PostConstruct
	public void novo() {
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

		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar buscar o produto");
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

}
