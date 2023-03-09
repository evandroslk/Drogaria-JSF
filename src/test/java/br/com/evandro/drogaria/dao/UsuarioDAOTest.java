package br.com.evandro.drogaria.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

import br.com.evandro.drogaria.domain.Pessoa;
import br.com.evandro.drogaria.domain.Usuario;
import br.com.evandro.drogaria.enums.TipoUsuario;

public class UsuarioDAOTest {
	
	@Test
	public void salvar() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(2L);
		
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setPessoa(pessoa);
		usuario.setSenhaSemCriptografia("12345abc");
		
		SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
		usuario.setSenha(hash.toHex());
		usuario.setTipoUsuario(TipoUsuario.BALCONISTA);
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
	}
	
	@Test
	public void autenticar() {
		String cpf = "222.222.222-22";
		String senha = "12345abc";
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.autenticar(cpf, senha);
		
		System.out.println(usuario.getPessoa().getNome());
	}

}
