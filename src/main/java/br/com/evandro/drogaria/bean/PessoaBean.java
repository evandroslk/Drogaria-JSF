package br.com.evandro.drogaria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Messages;

import com.google.gson.Gson;

import br.com.evandro.drogaria.dao.CidadeDAO;
import br.com.evandro.drogaria.dao.EstadoDAO;
import br.com.evandro.drogaria.dao.PessoaDAO;
import br.com.evandro.drogaria.domain.Cidade;
import br.com.evandro.drogaria.domain.Estado;
import br.com.evandro.drogaria.domain.Pessoa;

@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pessoa pessoa;
	private Estado estado;

	private List<Pessoa> pessoas;
	private List<Estado> estados;
	private List<Cidade> cidades;

	@PostConstruct
	public void listar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as pessoas");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			pessoa = new Pessoa();
			estado = new Estado();
			
			Client cliente = ClientBuilder.newClient();
			WebTarget caminho = cliente.target("http://127.0.0.1:8080/Drogaria/rest/estado");
			String json = caminho.request().get(String.class);
			Gson gson = new Gson();
			Estado[] vetor = gson.fromJson(json, Estado[].class);
			estados = Arrays.asList(vetor);

			cidades = new ArrayList<Cidade>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar uma nova pessoa");
			erro.printStackTrace();
		}
	}

	public void popular() {
		try {
			if (estado != null) {
				
				Client cliente = ClientBuilder.newClient();
				WebTarget caminho = cliente.target("http://127.0.0.1:8080/Drogaria/rest/cidade/{estadoCodigo}")
						.resolveTemplate("estadoCodigo", estado.getCodigo());
				String json = caminho.request().get(String.class);
				Gson gson = new Gson();
				Cidade[] vetor = gson.fromJson(json, Cidade[].class);
				cidades = Arrays.asList(vetor);
			} else {
				cidades = new ArrayList<>();
			}
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar filtrar as cidades");
			e.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");

			estado = pessoa.getCidade().getEstado();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");
			
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar uma cidade");
			e.printStackTrace();
		}
	}

	public void salvar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.merge(pessoa);

			pessoas = pessoaDAO.listar("nome");

			pessoa = new Pessoa();

			estado = new Estado();

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");

			cidades = new ArrayList<>();

			Messages.addGlobalInfo("Pessoa cadastrada com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a pessoa");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.excluir(pessoa);

			pessoas = pessoaDAO.listar("nome");

			Messages.addGlobalInfo("Pessoa removida com sucesso");
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover a pessoa");
			e.printStackTrace();
		}
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
