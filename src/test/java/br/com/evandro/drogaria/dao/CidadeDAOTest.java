package br.com.evandro.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.evandro.drogaria.domain.Cidade;
import br.com.evandro.drogaria.domain.Estado;

public class CidadeDAOTest {

	EstadoDAO estadoDAO = new EstadoDAO();
	CidadeDAO cidadeDAO = new CidadeDAO();

	@Test
	@Ignore
	public void salvar() {
		Estado estado = estadoDAO.buscar(1L);

		Cidade cidade = new Cidade();
		cidade.setNome("Porto Alegre");
		cidade.setEstado(estado);
		
		cidadeDAO.salvar(cidade);
	}

	@Test
	@Ignore
	public void listar() {
		List<Cidade> resultado = cidadeDAO.listar();

		for (Cidade cidade : resultado) {
			System.out.println("Código da Cidade: " + cidade.getCodigo());
			System.out.println("Nome da Cidade: " + cidade.getNome());
			System.out.println("Código do Estado: " + cidade.getEstado().getCodigo());
			System.out.println("Sigla do Estado: " + cidade.getEstado().getSigla());
			System.out.println("Nome do Estado: " + cidade.getEstado().getNome());
			System.out.println();
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 4L;

		Cidade cidade = cidadeDAO.buscar(codigo);

		System.out.println("Código da Cidade: " + cidade.getCodigo());
		System.out.println("Nome da Cidade: " + cidade.getNome());
		System.out.println("Código do Estado: " + cidade.getEstado().getCodigo());
		System.out.println("Sigla do Estado: " + cidade.getEstado().getSigla());
		System.out.println("Nome do Estado: " + cidade.getEstado().getNome());
	}

	@Test
	public void excluir() {
		Long codigo = 4L;

		cidadeDAO.excluir(cidadeDAO.buscar(codigo));

		System.out.println("Cidade removida");
	}

}
