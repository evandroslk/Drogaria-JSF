package br.com.evandro.drogaria.bean;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.omnifaces.util.Faces;
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
//			Path path = Paths.get(Faces.getRealPath("//resources//images//branco.jpeg"));
//			InputStream stream = Files.newInputStream(path);
//			foto = new DefaultStreamedContent(stream);
			stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/images/branco.jpeg");
		} else {
//			Path path = Paths.get(caminho);
//			InputStream stream = Files.newInputStream(path);
//			foto = new DefaultStreamedContent(stream);
			stream = new FileInputStream(caminho);
		}
		foto = new DefaultStreamedContent(stream);
		return foto;
	}

	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}

}
