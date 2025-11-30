package modelo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import enums.Categoria;
import modelo.Autor;

@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne
    private Autor autor;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private int anoPublicacao;
    private double mediaNotas;
    private int numeroPaginas;
    private String sinopse;
    private Double notaMedia;
    private Long totalNotas;
    private Long contadorAvaliacoes;
    
    
    public void setNotaMedia(Double notaMedia) {
        this.notaMedia = notaMedia;
    }

    public void setTotalNotas(Long totalNotas) {
        this.totalNotas = totalNotas;
    }

    public void setContadorAvaliacoes(Long contadorAvaliacoes) {
        this.contadorAvaliacoes = contadorAvaliacoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }


    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

	public int getNotaMedia() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Categoria getCategoria() {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getTotalNotas() {
	        return totalNotas;
	    }
	public Long getContadorAvaliacoes() {
	        return contadorAvaliacoes;
	    }
}