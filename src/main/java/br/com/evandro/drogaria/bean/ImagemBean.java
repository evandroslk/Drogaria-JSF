package br.com.evandro.drogaria.bean;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@RequestScoped
public class ImagemBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{param.caminho}")
	private String caminho;

	private StreamedContent foto;

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public StreamedContent getFoto() throws IOException {
		InputStream stream;
		if (caminho == null || caminho.isEmpty()) {
			stream = FacesContext.getCurrentInstance().getExternalContext()
					.getResourceAsStream("/resources/images/branco.jpeg");
		} else {
			stream = new FileInputStream(caminho);
		}
		foto = new DefaultStreamedContent(stream);
		return foto;
	}

	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}

}
