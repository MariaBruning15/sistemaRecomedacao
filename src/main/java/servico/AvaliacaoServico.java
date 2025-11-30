package servico;

import modelo.Livro;

public class AvaliacaoServico {
	// Assumindo que este método é chamado de dentro de um serviço
	// após salvar uma nova Avaliacao no repositório.

	public void atualizarNotaMedia(Livro livro, int novaNota) {
	    
	    long novoContador = livro.getContadorAvaliacoes() + 1;
	    long novoTotalNotas = livro.getTotalNotas() + novaNota;
	    
	    double novaMedia = (double) novoTotalNotas / novoContador;
	    
	    livro.setContadorAvaliacoes(novoContador);
	    livro.setTotalNotas(novoTotalNotas);
	    livro.setNotaMedia(novaMedia);
	    
	}
}
