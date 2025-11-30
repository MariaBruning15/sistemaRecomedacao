package servico;

import java.util.List;
import java.util.stream.Collectors;
import modelo.ArvoreBuscaBidimensional;
import modelo.Livro;
import enums.Categoria;


public class RecomendacaoServico {

    private final ArvoreBuscaBidimensional arvore; 
    
    
    public RecomendacaoServico(ArvoreBuscaBidimensional arvore) {
        this.arvore = arvore;
    }

   
    public List<Livro> recomendarPorConteudo(Categoria categoriaPreferida, double minNota, double maxNota, int limite) {
        
       
        String minC = "A"; 
        String maxC = "Z"; 
        
        List<Livro> candidatos = arvore.buscaIntervalo(minNota, maxNota, minC, maxC);
        
        List<Livro> recomendados = candidatos.stream()
            .filter(livro -> livro.getCategoria().equals(categoriaPreferida)) 
            .limit(limite) 
            .collect(Collectors.toList());
            
        return recomendados;
    }
    
   
    public List<Livro> gerarRelatorioPopulares(int limite) {
        return arvore.getLivrosMaisPopulares().stream()
               .limit(limite)
               .collect(Collectors.toList());
    }

    
    public double calcularTaxaDeAcerto() {
        return 0.0; 
    }
}