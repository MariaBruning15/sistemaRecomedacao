package modelo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import modelo.Usuario;
import modelo.Livro;   

@Entity
public class Recomendacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Usuario usuarioAlvo;
    @ManyToOne
    private Livro livroRecomendado;
    private int avaliareRecomendacao;
}
