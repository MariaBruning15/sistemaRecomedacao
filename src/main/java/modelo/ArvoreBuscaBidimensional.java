package modelo;



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
            
            if (livro.getCategoria().compareTo(atual.livro.getCategoria()) < 0) { 
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
            if (livroParaBuscar.getCategoria().compareTo(current.livro.getCategoria()) < 0) {
                return searchRecursive(current.esquerda, livroParaBuscar, depth + 1);
            } else {
                return searchRecursive(current.direita, livroParaBuscar, depth + 1);
            }
        }
    }
}