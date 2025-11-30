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
        raiz = inserirRecursivo(raiz, livro, 0); 
    }

    private No inserirRecursivo(No atual, Livro livro, int depth) {
        
        if (atual == null) {
            return new No(livro); 
        } 

        int dimensao = depth % K; 
        
        if (dimensao == 0) { 
            
            if (Double.compare(livro.getNotaMedia(), atual.livro.getNotaMedia()) < 0) { 
                atual.esquerda = inserirRecursivo(atual.esquerda, livro, depth + 1); 
            } else { 
                atual.direita = inserirRecursivo(atual.direita, livro, depth + 1); 
            }
        } else { 
            String novaCategoria = livro.getCategoria().name();
            String atualCategoria = atual.livro.getCategoria().name();
            
            if (novaCategoria.compareTo(atualCategoria) < 0) { 
                atual.esquerda = inserirRecursivo(atual.esquerda, livro, depth + 1); 
            } else {
                atual.direita = inserirRecursivo(atual.direita, livro, depth + 1); 
            }
        } 
        
        return atual; 
    }
    
    public Livro busca(Livro livroParaBuscar) {
        return buscaRecursivo(raiz, livroParaBuscar, 0);
    }

    private Livro buscaRecursivo(No current, Livro livroParaBuscar, int depth) {
        if (current == null) {
            return null;
        }
        
        if (livroParaBuscar.getId().equals(current.livro.getId())) {
            return current.livro;
        }

        int dimensao = depth % K; 
        
        if (dimensao == 0) { 
            if (Double.compare(livroParaBuscar.getNotaMedia(), current.livro.getNotaMedia()) < 0) {
                return buscaRecursivo(current.esquerda, livroParaBuscar, depth + 1);
            } else {
                return buscaRecursivo(current.direita, livroParaBuscar, depth + 1);
            }
        } else { 
            String buscarCategoria = livroParaBuscar.getCategoria().name();
            String currentCategoria = current.livro.getCategoria().name();
            
            if (buscarCategoria.compareTo(currentCategoria) < 0) {
                return buscaRecursivo(current.esquerda, livroParaBuscar, depth + 1);
            } else {
                return buscaRecursivo(current.direita, livroParaBuscar, depth + 1);
            }
        }
    }
    
    public List<Livro> buscaIntervalo(double minNota, double maxNota, String minCategoria, String maxCategoria) {
        List<Livro> resultados = new ArrayList<>();
        buscaIntervaloRecursivo(raiz, minNota, maxNota, minCategoria, maxCategoria, 0, resultados);
        return resultados;
    }

    private void buscaIntervaloRecursivo(No atual, double minNota, double maxNota, 
                                      String minCategoria, String maxCategoria, 
                                      int depth, List<Livro> resultados) {
        
        if (atual == null) {
            return;
        }

        if (isInsideRange(atual.livro, minNota, maxNota, minCategoria, maxCategoria)) {
            resultados.add(atual.livro);
        }

        int dimensao = depth % K;
        
        String currentCategoryName = atual.livro.getCategoria().name();

        if (dimensao == 0) { 
            if (minNota < atual.livro.getNotaMedia()) {
            	buscaIntervaloRecursivo(atual.esquerda, minNota, maxNota, minCategoria, maxCategoria, depth + 1, resultados);
            }
            if (maxNota > atual.livro.getNotaMedia()) {
            	buscaIntervaloRecursivo(atual.direita, minNota, maxNota, minCategoria, maxCategoria, depth + 1, resultados);
            }
        } else { 
            if (minCategoria.compareTo(currentCategoryName) < 0) {
            	buscaIntervaloRecursivo(atual.esquerda, minNota, maxNota, minCategoria, maxCategoria, depth + 1, resultados);
            }
            if (maxCategoria.compareTo(currentCategoryName) > 0) {
            	buscaIntervaloRecursivo(atual.direita, minNota, maxNota, minCategoria, maxCategoria, depth + 1, resultados);
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
    
 
    public List<Livro> getLivrosMaisPopulares() {
        List<Livro> populares = new ArrayList<>();
        inOrderReverseRecursive(raiz, populares); 
        return populares;
    }

    private void inOrderReverseRecursive(No atual, List<Livro> lista) {
        
        if (atual == null) {
            return;
        }
        
        inOrderReverseRecursive(atual.getDireita(), lista);
        
        lista.add(atual.livro);
        
        inOrderReverseRecursive(atual.getEsquerda(), lista);
    }
    

    private int compareLivrosByDimensao(Livro livro1, Livro livro2, int dimensao) {
        if (dimensao == 0) { 
            return Double.compare(livro1.getNotaMedia(), livro2.getNotaMedia());
        } else { 
            return livro1.getCategoria().name().compareTo(livro2.getCategoria().name());
        }
    }
    

    private No buscaMinRecursiva(No atual, int encontrarDimensao, int depth) {
        if (atual == null) {
            return null;
        }

        int currentDimension = depth % K;

        if (currentDimension == encontrarDimensao) {
          
            if (atual.getEsquerda() == null) {
                return atual;
            }
            return buscaMinRecursiva(atual.getEsquerda(), encontrarDimensao, depth + 1);
        }

        No minNode = atual;
        
        No minEsquerda = buscaMinRecursiva(atual.getEsquerda(), encontrarDimensao, depth + 1);
        if (minEsquerda != null && compareLivrosByDimensao(minEsquerda.livro, minNode.livro, encontrarDimensao) < 0) {
            minNode = minEsquerda;
        }

        No minDireita = buscaMinRecursiva(atual.getDireita(), encontrarDimensao, depth + 1);
        if (minDireita != null && compareLivrosByDimensao(minDireita.livro, minNode.livro, encontrarDimensao) < 0) {
            minNode = minDireita;
        }

        return minNode;
    }
    

    public No removerRecursivo(No atual, Long idLivro, int depth) {
        if (atual == null) {
            return null;
        }
        
        if (idLivro.equals(atual.livro.getId())) {
            
            int dimension = depth % K;
            
            if (atual.getEsquerda() == null && atual.getDireita() == null) {
                return null;
            }

            if (atual.getDireita() != null) {
                No minNode = buscaMinRecursiva(atual.getDireita(), dimension, depth + 1);
                
                atual.livro = minNode.livro; 
                
                atual.setDireita(removerRecursivo(atual.getDireita(), minNode.livro.getId(), depth + 1));
                
            } else { 
                No temp = atual.getEsquerda();
                
                atual.livro = temp.livro; 
                
                atual.setEsquerda(removerRecursivo(atual.getEsquerda(), temp.livro.getId(), depth + 1));
                
                atual.setDireita(atual.getEsquerda());
                atual.setEsquerda(null);
            }
            
            return atual;
        }
        
        atual.setEsquerda(removerRecursivo(atual.getEsquerda(), idLivro, depth + 1));
        
        atual.setDireita(removerRecursivo(atual.getDireita(), idLivro, depth + 1));
        
        return atual;
    }
    
    public void atualizar(Livro livroAtualizado) {
        this.raiz = removerRecursivo(this.raiz, livroAtualizado.getId(), 0);
        
        inserir(livroAtualizado); 
    }
   
} 
