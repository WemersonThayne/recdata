package br.edu.ifpb.excecoes;

public class TamanhoInvalidoException extends Exception {

	public TamanhoInvalidoException() {
		System.err.println("Por Favor, Coloca no M�ximo 30 carater, nosso Banco n�o e o data center do IBGE!");
	}

}
