package br.com.evandro.drogaria.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.google.gson.Gson;

import br.com.evandro.drogaria.dao.ProdutoDAO;
import br.com.evandro.drogaria.domain.Produto;

@Path("produtos")
public class ProdutoService {

	@GET
	public String listar() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> produtos = produtoDAO.listar();
		
		Gson gson = new Gson();
		String json = gson.toJson(produtos);
		return json;
	}
	
	@POST
	public String salvar(String json) {
		Gson gson = new Gson();
		Produto produto = gson.fromJson(json, Produto.class);
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produto = produtoDAO.merge(produto);
		
		String jsonSaida = gson.toJson(produto);
		return jsonSaida;
	}
	
}
