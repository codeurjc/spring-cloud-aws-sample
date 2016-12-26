package es.urjc.code.daw.tablonanuncios;

import java.net.URL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Anuncio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nombre;
	private String asunto;
	private String comentario;
	private URL foto;

	public Anuncio() {}

	public Anuncio(String nombre, String asunto, String comentario) {
		super();
		this.nombre = nombre;
		this.asunto = asunto;
		this.comentario = comentario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	public URL getFoto() {
		return foto;
	}
	
	public void setFoto(URL foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {
		return "Anuncio [nombre=" + nombre + ", asunto=" + asunto + ", comentario=" + comentario + "]";
	}

}
