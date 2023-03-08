package br.com.evandro.drogaria.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.NoResultException;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.evandro.drogaria.dao.UsuarioDAO;
import br.com.evandro.drogaria.domain.Pessoa;
import br.com.evandro.drogaria.domain.Usuario;

@ManagedBean
@SessionScoped
public class AutenticacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	private Usuario usuarioLogado;

	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
		usuario.setPessoa(new Pessoa());
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public void autenticar() throws IOException {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioLogado = usuarioDAO.autenticar(usuario.getPessoa().getCpf(), usuario.getSenhaSemCriptografia());

			Faces.redirect("./pages/principal.xhtml");
		} catch (NoResultException e) {
			Messages.addGlobalError("Usuário e/ou senha incorretos");
		} catch (IOException e) {
			e.printStackTrace();
			Messages.addGlobalError(e.getMessage());
		}
	}

}
