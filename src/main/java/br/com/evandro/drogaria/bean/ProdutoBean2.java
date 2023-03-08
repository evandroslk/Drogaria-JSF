package br.com.evandro.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.evandro.drogaria.dao.FabricanteDAO;
import br.com.evandro.drogaria.dao.ProdutoDAO;
import br.com.evandro.drogaria.domain.Fabricante;
import br.com.evandro.drogaria.domain.Produto;

@ManagedBean
@ViewScoped
public class ProdutoBean2 implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigoProduto;
	private List<Produto> produtos;
	private Produto produto;
	private List<Fabricante> fabricantes;

	private ProdutoDAO produtoDAO;
	private FabricanteDAO fabricanteDAO;

	@PostConstruct
	public void iniciar() {
		fabricanteDAO = new FabricanteDAO();
		produtoDAO = new ProdutoDAO();
	}

	public void listar() {
		try {
			produtos = produtoDAO.listar("descricao");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os produtos");
			e.printStackTrace();
		}
	}

	public void carregarEdicao() {
		try {
			produto = produtoDAO.buscar(codigoProduto);
			fabricantes = fabricanteDAO.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar os dados para edição");
			e.printStackTrace();
		}
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public Long getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(Long codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

}
