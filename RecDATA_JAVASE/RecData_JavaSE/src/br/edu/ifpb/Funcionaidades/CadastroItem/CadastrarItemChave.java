package br.edu.ifpb.Funcionaidades.CadastroItem;

import java.util.Scanner;


/**
 * @author Wemerson Thayne
 *
 */
public class CadastrarItemChave implements CadastrarItem {

	public String entradaDadoItem() {
		Scanner sc = new Scanner(System.in);
		String status,local;
		System.out.println("Digite Status");
		status=sc.nextLine();
		System.out.println("Digite O local");
		local=sc.nextLine();
		return status+local;	
	
	}

	public boolean enviaDadosValidacaoItem() {
		System.out.println("Os Dados Informados est�o sendo enviados para a valida��o no Servidor\n Por Favor aguarde um estante!");
		// conectar-se com o servidor atraves de socket ou rmi ou outra merda dessa para validar
		return true;
	}

	public String geraIdentificadorItem() {
		// conectar com o banco e  v� a compatibilidade de dados informados pelo usuario se h� algum item igual no banco caso n�o criar novo item 
		System.out.println("Item Tem Identificador Unico, serva criado o novo item, e s� confirmar dados");
		return null;
	}

	public boolean confirmaCadastroItem() {
		// conecta com o banco e cria a nova tupla do item
		System.out.println("Chave  criada com sucesso...");
		return false;
	}

	public String disponibilizaItem() {
		System.out.println("Item Chave est� pronto para a Reserva");
		// item deve ser enviado para uma tabela de itens disponiveis ;)no banco e claro anta!
		return null;
	}


	
}
