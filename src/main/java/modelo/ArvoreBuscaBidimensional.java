package modelo;

import java.util.ArrayList;
import java.util.List;

public class ArvoreBuscaBidimensional {

class No { 
    private Livro livro;
    private No esquerda;
    private No direita;

    public No(Livro livro) {
        this.livro = livro;
        this.esquerda = null;
        this.direita = null;
    }

	public No getEsquerda() {
		return esquerda;
	}

	public void setEsquerda(No esquerda) {
		this.esquerda = esquerda;
	}

	public No getDireita() {
		return direita;
	}

	public void setDireita(No direita) {
		this.direita = direita;
	}
}
    private No raiz;
    private static final int K = 2; 
    
    
    public void inserir(Livro livro) {
        raiz = inserirRecursive(raiz, livro, 0); 
    }

    private No inserirRecursive(No atual, Livro livro, int depth) {
        
        if (atual == null) {
            return new No(livro); 
        } 

        int dimension = depth % K; 
        
        if (dimension == 0) { 
            
            if (Double.compare(livro.getNotaMedia(), atual.livro.getNotaMedia()) < 0) { 
                atual.esquerda = inserirRecursive(atual.esquerda, livro, depth + 1); 
            } else { 
                atual.direita = inserirRecursive(atual.direita, livro, depth + 1); 
            }
        } else { 
            String novaCategoria = livro.getCategoria().name();
            String atualCategoria = atual.livro.getCategoria().name();
            
            if (novaCategoria.compareTo(atualCategoria) < 0) { 
                atual.esquerda = inserirRecursive(atual.esquerda, livro, depth + 1); 
            } else {
                atual.direita = inserirRecursive(atual.direita, livro, depth + 1); 
            }
        } 
        
        return atual; 
    }
    
    public Livro search(Livro livroParaBuscar) {
        return searchRecursive(raiz, livroParaBuscar, 0);
    }

    private Livro searchRecursive(No current, Livro livroParaBuscar, int depth) {
        if (current == null) {
            return null;
        }
        
        if (livroParaBuscar.getId().equals(current.livro.getId())) {
            return current.livro;
        }

        int dimension = depth % K; 
        
        if (dimension == 0) { 
            if (Double.compare(livroParaBuscar.getNotaMedia(), current.livro.getNotaMedia()) < 0) {
                return searchRecursive(current.esquerda, livroParaBuscar, depth + 1);
            } else {
                return searchRecursive(current.direita, livroParaBuscar, depth + 1);
            }
        } else { 
            String buscarCategoria = livroParaBuscar.getCategoria().name();
            String currentCategoria = current.livro.getCategoria().name();
            
            if (buscarCategoria.compareTo(currentCategoria) < 0) {
                return searchRecursive(current.esquerda, livroParaBuscar, depth + 1);
            } else {
                return searchRecursive(current.direita, livroParaBuscar, depth + 1);
            }
        }
    }
    
    public List<Livro> rangeSearch(double minNota, double maxNota, String minCategoria, String maxCategoria) {
        List<Livro> resultados = new ArrayList<>();
        rangeSearchRecursive(raiz, minNota, maxNota, minCategoria, maxCategoria, 0, resultados);
        return resultados;
    }

    private void rangeSearchRecursive(No atual, double minNota, double maxNota, 
                                      String minCategoria, String maxCategoria, 
                                      int depth, List<Livro> resultados) {
        
        if (atual == null) {
            return;
        }

        if (isInsideRange(atual.livro, minNota, maxNota, minCategoria, maxCategoria)) {
            resultados.add(atual.livro);
        }

        int dimension = depth % K;
        
        String currentCategoryName = atual.livro.getCategoria().name();

        if (dimension == 0) { 
            if (minNota < atual.livro.getNotaMedia()) {
                rangeSearchRecursive(atual.esquerda, minNota, maxNota, minCategoria, maxCategoria, depth + 1, resultados);
            }
            if (maxNota > atual.livro.getNotaMedia()) {
                rangeSearchRecursive(atual.direita, minNota, maxNota, minCategoria, maxCategoria, depth + 1, resultados);
            }
        } else { 
            if (minCategoria.compareTo(currentCategoryName) < 0) {
                rangeSearchRecursive(atual.esquerda, minNota, maxNota, minCategoria, maxCategoria, depth + 1, resultados);
            }
            if (maxCategoria.compareTo(currentCategoryName) > 0) {
                rangeSearchRecursive(atual.direita, minNota, maxNota, minCategoria, maxCategoria, depth + 1, resultados);
            }
        }
    }
    

    private boolean isInsideRange(Livro livro, double minN, double maxN, String minC, String maxC) {
        boolean notaOk = livro.getNotaMedia() >= minN && livro.getNotaMedia() <= maxN;
        
        String currentCategoryName = livro.getCategoria().name();
        
        boolean categoriaOk = currentCategoryName.compareTo(minC) >= 0 && 
                              currentCategoryName.compareTo(maxC) <= 0;
                              
        return notaOk && categoriaOk; 
    }
} 
